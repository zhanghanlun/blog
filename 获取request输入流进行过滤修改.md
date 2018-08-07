# 前言
在SpringMVC开发过程中，我们进行要进行请求的拦截来进行XSS过滤拦截。这里涉及两个知识点。

>* 如何获取POST请求的Body，以及修改请求的Body
>* 如何进行XSS过滤拦截

# 获取POST请求的输入流，并修改

一般在POST请求中我们携带的信息是application/json格式的。在一些场景中我们要对这些POST请求中的application/json信息进行获取、解析。
先看代码：

```java
public class XssRequestWrapper extends HttpServletRequestWrapper {

    private static Policy policy = null;

    private static final AntiSamy antiSamy = new AntiSamy();
    /**
     * 请求Body中的String类型
     */
    private String body;
    /**
     * application/json转成的Map类型
     */
    private Map<String, String> parameters = new HashMap<String, String>();
    /**
     * 字符串的流
     */
    private byte[] bytes;
    /**
     * 判断是否进行了数据的初始化
     */
    private boolean isInit = false;
    /**
     * 默认的字符串格式
     */
    private static final String DEFAULT_CHARSET_NAME = "UTF-8";
    private ObjectMapper objectMapper = new ObjectMapper();

    public XssRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        try {
            String contentType = request.getContentType();
            if(contentType.contains(";")){
                contentType = contentType.substring(0,contentType.indexOf(";"));
            }
            //初始化bytes中的数据
            initBytes();
            //解析application/json中的内容
            if(contentType.equals(MediaType.APPLICATION_JSON_VALUE)){
                parseJsonParameters();
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException", e);
        }
    }
    /**
     * 第一次执行要进行数据的初始化
     * @throws IOException
     */
    private void initBytes() throws IOException {
        isInit = true;
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = this.getHttpServletRequest().getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        body = buffer.toString();
        //获取输入流到本地
        bytes = body.getBytes(DEFAULT_CHARSET_NAME);
    }
    /**
     * 解析ApplicationJson数据，并且经过XSS过滤
     * @throws IOException
     */
    private void parseJsonParameters() throws IOException {
        StringBuilder stringBuilder = new StringBuilder("{");
        if (null != body && body.length() > 0) {
            JsonNode node = objectMapper.readTree(body);
            Iterator<String> fieldNames = node.getFieldNames();
            for (; fieldNames.hasNext();) {
                String key = fieldNames.next();
                String value = node.get(key).toString();
                if (value.length() > 2 && value.startsWith("\"")) {
                    String valueTemp = value.substring(1,value.length()-1);
                    valueTemp = StringEscapeUtils.unescapeJava(valueTemp);
                    //XSS白名单过滤
                    valueTemp = Jsoup.clean(valueTemp,Whitelist.basicWithImages().addAttributes("img","class","style","emotion-id"));
                    valueTemp = StringEscapeUtils.escapeJava(valueTemp);
                    valueTemp = "\"" + valueTemp + "\"";
                    parameters.put(key, valueTemp);
                } else {
                    parameters.put(key, value);
                }
            }
        }
        for(String key:parameters.keySet()){
            String value = parameters.get(key);
            stringBuilder.append("\""+key+"\":"+value+",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("}");
        try {
            bytes = stringBuilder.toString().getBytes(DEFAULT_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    private HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest) super.getRequest();
    }
    /**
     * 重写getReader方法
     * @return
     * @throws IOException
     */
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
    /**
     * 重写getInputStream()方法
     * 可以修改body内容
     * @return
     * @throws IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (!isInit)
            initBytes();
        final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }
```
分析：获取Post请求输入流，又保证数据不丢失的步骤如下：

>* 继承父类的构造方法，在自己的方法中进行数据的初始化
>* 数据的初始化就是，获取输入流，写入本类的char[] bytes中
>* 重新getInputStream()方法和getReader()方法，其中getInputStream()方法直接读取bytes字节流信息
>* 解析application/json数据，利用JsonNode类

# XSS过滤拦截

在这里我们接受Jsoup框架来进行信息的拦截，而Jsoup是基于白名单来进行过滤拦截的。
只有在白名单中的标签和属性可以通过，其他的进行删除操作。

```java
String a = "<img src=\"zjhanh\" alt=\"zhanghanlun\">";
//创建基础白名单
Whitelist whitelist = new Whitelist().basic();
//添加标签和对应的属性
whitelist.addAttributes("a","class","style");
whitelist.addAttributes("img", "style","src");
String clean = Jsoup.clean(a, whitelist);
System.out.println(clean);
```
结果：
```java
<img src="zjhanh">
```

在上面的代码中，显示了Jsoup进行HTML内容清理基本步骤。
在Whiteilist类中有几个基本的方法。
basic()方法
```java
public static Whitelist basic() {
        return new Whitelist()
                .addTags(
                        "a", "b", "blockquote", "br", "cite", "code", "dd", "dl", "dt", "em",
                        "i", "li", "ol", "p", "pre", "q", "small", "span", "strike", "strong", "sub",
                        "sup", "u", "ul")

                .addAttributes("a", "href")
                .addAttributes("blockquote", "cite")
                .addAttributes("q", "cite")
		//给标签中的属性添加限定值
                .addProtocols("a", "href", "ftp", "http", "https", "mailto")
                .addProtocols("blockquote", "cite", "http", "https")
                .addProtocols("cite", "cite", "http", "https")
		//给标签添加强制属性和默认值
                .addEnforcedAttribute("a", "rel", "nofollow")
                ;

    }
```
