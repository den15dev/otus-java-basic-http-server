package ru.otus.java.basic.httpserver.processors;

import ru.otus.java.basic.httpserver.request.HttpRequest;
import ru.otus.java.basic.httpserver.response.HtmlResponse;

import java.io.IOException;
import java.io.OutputStream;

public class HelloRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String body = "<html><body><h1>Hello World!!!</h1></body></html>";
        HtmlResponse response = new HtmlResponse(body);

        output.write(response.getBytes());
    }
}
