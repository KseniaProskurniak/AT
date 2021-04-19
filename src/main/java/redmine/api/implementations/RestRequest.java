package redmine.api.implementations;

import redmine.api.interfaces.HttpMethods;
import redmine.api.interfaces.Request;

import java.util.Map;

public class RestRequest implements Request {
    private String uri;
    private HttpMethods method;
    private Map<String, String> parameters;
    private Map<String, String> headers;

    @Override
    public String getUri() {
        return null;
    }

    @Override
    public HttpMethods getMethod() {
        return null;
    }

    @Override
    public Map<String, String> getParameters() {
        return null;
    }

    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @Override
    public Object getBody() {
        return null;
    }
}
