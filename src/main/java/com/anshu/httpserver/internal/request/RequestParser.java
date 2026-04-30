package com.anshu.httpserver.internal.request;

public class RequestParser {

    public RequestLine parseRequestLineStrict(String line) throws IllegalAccessException {
        if(line == null || line.isEmpty()){
            throw new IllegalAccessException("Request line cannot be null or empty");
        }

        String[] details = line.split(" ");
        if(details.length != 3){
            throw new IllegalAccessException("Invalid request line");
        }

        String method = details[0];
        String requestTarget = details[1];
        String httpVersion = details[2];

        if(!method.matches("^[A-Z]+$")){
            throw new IllegalAccessException("Invalid request method");
        }

        if(!httpVersion.contains("HTTP/")){
            throw new IllegalAccessException("Invalid HTTP version");
        }

        String version = httpVersion.substring("HTTP/".length());
        if(!version.equals("1.1")) {
            throw new IllegalAccessException("Unsupported HTTP version (only 1.1 is supported)");
        }

        return new RequestLine(method, requestTarget, version);
    }
}
