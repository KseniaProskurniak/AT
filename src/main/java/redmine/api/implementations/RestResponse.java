package redmine.api.implementations;

import io.restassured.http.Header;
import lombok.Data;

import redmine.api.interfaces.Response;

import java.util.Map;
import java.util.stream.Collectors;

@Data
public class RestResponse implements Response {
    private int statusCode;
    private Map<String, String> headers;
    private Object body;

    public RestResponse(int statusCode, Map<String, String> headers, Object body) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public RestResponse(io.restassured.response.Response restAssuredResponse) {
        this.statusCode = restAssuredResponse.getStatusCode();
        this.headers = restAssuredResponse.getHeaders().asList().stream()
                .collect(Collectors.toMap(Header::getName, Header::getValue));
        this.body = restAssuredResponse.getBody().asString();
    }

    @Override
    public int getStatusCode() {
        return 0;
    }

    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @Override
    public Object getBody() {
        return null;
    }

    @Override
    public <T> T getBody(Class<T> clazz) {
        return clazz.cast(body);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(statusCode).append(System.lineSeparator());
        headers.forEach((key, value) -> sb.append(key).append("=").append(value).append(System.lineSeparator()));
        sb.append(System.lineSeparator());
        if (body != null) {
            sb.append(body.toString());
        }
        return sb.toString();
    }
}

