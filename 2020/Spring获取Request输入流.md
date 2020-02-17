---
title: Spring获取Request输入流
date: 2018-08-12 17:24:45
tags:
	- Java
	- Spring
categories: Java
---
# 前言

我们在进行请求进行拦截的时候经常会碰上这样一个问题，我们想要在拦截器filter中获取request的请求，如果使用请求中默认的getInputStream()方法或者getReader()方法获取数据，但是在后面的Controller中使用@ResquestBody注解，我们读取不到request的body中的值，这是因为**request的body中的数据只能通过getInputStream()和getReader()方法读取一次，要解决这个问题，我们要对重写request请求的getInputStream()和getReader()方法。**

<!-- more -->


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
                    //TODO:XSS白名单过滤
                   
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
# 总结
分析：获取Post请求输入流，又保证数据不丢失的步骤如下：

>* 继承父类的构造方法，在自己的方法中进行数据的初始化
>* 数据的初始化就是，获取输入流，写入本类的char[] bytes中
>* 重新getInputStream()方法和getReader()方法，其中getInputStream()方法直接读取bytes字节流信息
>* 解析application/json数据，利用JsonNode类
