package ru.otus.java.basic.httpserver.processors;

import ru.otus.java.basic.httpserver.request.HttpRequest;
import ru.otus.java.basic.httpserver.response.HtmlResponse;
import ru.otus.java.basic.httpserver.response.HttpStatus;

import java.io.IOException;
import java.io.OutputStream;

public class DefaultNotFoundRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String body = "<html><body><h1>404 Page Not Found</h1></body></html>";
        HtmlResponse response = new HtmlResponse(HttpStatus.NOT_FOUND, body);
        output.write(response.getBytes());
    }
}
