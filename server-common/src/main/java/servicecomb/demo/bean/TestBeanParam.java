package servicecomb.demo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.ws.rs.*;

public class TestBeanParam {
//    @DefaultValue("defaultQueryValue")
    @QueryParam("querySwaggerStr")
    private String queryStr;

    private Integer headerInt;

    private String pathStr;

    @CookieParam("cookieSwaggerLong")
    private long cookieLong;

    @JsonIgnore
    private String ignoredField;

    public String getQueryStr() {
        return queryStr;
    }

    public void setQueryStr(String queryStr) {
        this.queryStr = queryStr;
    }

    public Integer getHeaderInt() {
        return headerInt;
    }

    @DefaultValue("12")
    @HeaderParam("headerSwaggerInt")
    public void setHeaderInt(Integer headerInt) {
        this.headerInt = headerInt;
    }

    public String getPathStr() {
        return pathStr;
    }

    @PathParam("pathSwaggerStr")
    public void setPathStr(String pathStr) {
        this.pathStr = pathStr;
    }

    public long getCookieLong() {
        return cookieLong;
    }

    public void
    setCookieLong(long cookieLong) {
        this.cookieLong = cookieLong;
    }

    public String getIgnoredField() {
        return ignoredField;
    }

    public void setIgnoredField(String ignoredField) {
        this.ignoredField = ignoredField;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TestBeanParameter{");
        sb.append("queryStr='").append(queryStr).append('\'');
        sb.append(", headerInt=").append(headerInt);
        sb.append(", pathStr='").append(pathStr).append('\'');
        sb.append(", cookieLong=").append(cookieLong);
        sb.append('}');
        return sb.toString();
    }
}
