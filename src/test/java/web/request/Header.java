package web.request;

public class Header {
    private final String Authorization;

    public Header(String Authorization){
        this.Authorization = Authorization;
    }
    public String getAuthorization() {
        return Authorization;
    }

    public String getUserAgent() {
        return "Apifox/1.0.0 (https://apifox.com)";
    }

    public String getContentType() {
        return "application/json";
    }
}
