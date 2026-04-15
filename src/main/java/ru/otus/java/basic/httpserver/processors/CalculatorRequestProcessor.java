package ru.otus.java.basic.httpserver.processors;

import ru.otus.java.basic.httpserver.request.HttpRequest;
import ru.otus.java.basic.httpserver.response.HtmlResponse;

import java.io.IOException;
import java.io.OutputStream;

public class CalculatorRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        int a = Integer.parseInt(request.getParameter("a"));
        int b = Integer.parseInt(request.getParameter("b"));

        String result = a + " + " + b + " = " + (a + b);
        String body = "<html><body><h1>" + result + "</h1></body></html>";

        HtmlResponse response = new HtmlResponse(body);
        output.write(response.getBytes());
    }
}
