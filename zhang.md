```java
public class Signon {
    private String token;
    private String userId;
    private String userCode;
    private String username;
    private String proEndDate;
    private String subscribeFreq;
    private Boolean needSubscribe;
    private String inboxId;
    private Boolean ds;
    private Boolean pro;
    private String subscribeType;

    public Signon() {
        token = "EBD7D0AF0FE1D94C46DCD71D3873E5C71ED4604C4440327D33D359C5A2020ED944F6862865E3647EE7ECE046509A8761F1EE06B0DCDBFB7A572A14850F853842021A6E71DD6FB90A8DC20B77B6A458F22C888CE6A31C4FD2070CAF976CCD0F28F5E1BCA77DD234ACC8901AF457D272CAC3333D292117F15083564240DA5DC072D6976801153F135989FFE3B010F33CA417D8213BBC0E52CFDA653FFD15F9D1026C9E38715C17B74E";
        userId = "1011436981";
        userCode = null;
        username = "zhanghanlun1@foxmail.com";
        proEndDate = "2020-01-01T00:00:00.000+0000";
        subscribeType = "order";
        subscribeFreq = null;
        needSubscribe = false;
        inboxId = "inbox1011436981";
        ds = true;
        pro = true;
    }

    public String getSubscribeType() {
        return subscribeType;
    }

    public void setSubscribeType(String subscribeType) {
        this.subscribeType = subscribeType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProEndDate() {
        return proEndDate;
    }

    public void setProEndDate(String proEndDate) {
        this.proEndDate = proEndDate;
    }

    public String getSubscribeFreq() {
        return subscribeFreq;
    }

    public void setSubscribeFreq(String subscribeFreq) {
        this.subscribeFreq = subscribeFreq;
    }

    public Boolean getNeedSubscribe() {
        return needSubscribe;
    }

    public void setNeedSubscribe(Boolean needSubscribe) {
        this.needSubscribe = needSubscribe;
    }

    public String getInboxId() {
        return inboxId;
    }

    public void setInboxId(String inboxId) {
        this.inboxId = inboxId;
    }

    public Boolean getDs() {
        return ds;
    }

    public void setDs(Boolean ds) {
        this.ds = ds;
    }

    public Boolean getPro() {
        return pro;
    }

    public void setPro(Boolean pro) {
        this.pro = pro;
    }
}

public class Status {
    private String token;
    private String userId;
    private String userCode;
    private String username;
    private String proEndDate;
    private String subscribeType;
    private Boolean needSubscribe;
    private String inboxId;
    private Boolean pro;
    private Boolean ds;
    private String subscribeFreq;

    public Status() {
        token = null;
        userId = "1011436981";
        userCode = null;
        username = "zhanghanlun1@foxmail.com";
        proEndDate = "2020-01-01T00:00:00.000+0000";
        subscribeType = "order";
        subscribeFreq = null;
        needSubscribe = false;
        inboxId = "inbox1011436981";
        pro = true;
        ds = true;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProEndDate() {
        return proEndDate;
    }

    public void setProEndDate(String proEndDate) {
        this.proEndDate = proEndDate;
    }

    public String getSubscribeType() {
        return subscribeType;
    }

    public void setSubscribeType(String subscribeType) {
        this.subscribeType = subscribeType;
    }

    public Boolean getNeedSubscribe() {
        return needSubscribe;
    }

    public void setNeedSubscribe(Boolean needSubscribe) {
        this.needSubscribe = needSubscribe;
    }

    public String getInboxId() {
        return inboxId;
    }

    public void setInboxId(String inboxId) {
        this.inboxId = inboxId;
    }

    public Boolean getPro() {
        return pro;
    }

    public void setPro(Boolean pro) {
        this.pro = pro;
    }

    public Boolean getDs() {
        return ds;
    }

    public void setDs(Boolean ds) {
        this.ds = ds;
    }

    public String getSubscribeFreq() {
        return subscribeFreq;
    }

    public void setSubscribeFreq(String subscribeFreq) {
        this.subscribeFreq = subscribeFreq;
    }
}
    @RequestMapping(value = "/signon")
    @ResponseBody
    public Signon getSignon(HttpServletRequest request){
        Signon signon = new Signon();
        return signon;
    }
    @RequestMapping(value = "/status")
    @ResponseBody
    public Status getWhat(HttpServletRequest request){
        Status status= new Status();
        return status;
    }
```