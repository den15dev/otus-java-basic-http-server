package ru.otus.java.basic.httpserver.processors;

import ru.otus.java.basic.httpserver.request.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestProcessor {
    void execute(HttpRequest request, OutputStream output) throws IOException;
}