package com.anshu.httpserver.internal.request;

public class RequestLine {
    private final String method;
    private final String requestTarget;
    private final String httpVersion;

    public RequestLine(String method, String requestTarget, String httpVersion) {
        this.method = method;
        this.requestTarget = requestTarget;
        this.httpVersion = httpVersion;
    }

    public String getRequestTarget() {
        return requestTarget;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public String getMethod() {
        return method;
    }
}
