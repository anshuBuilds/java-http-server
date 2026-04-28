package com.anshu.httpserver.internal.server;

import com.anshu.httpserver.internal.request.Request;
import com.anshu.httpserver.internal.response.Writer;

import java.io.IOException;

@FunctionalInterface
public interface Handler {
    void handle(Writer writer, Request request) throws IOException;
}
