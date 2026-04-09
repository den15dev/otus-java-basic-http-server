package ru.otus.java.basic.httpserver.processors;

import ru.otus.java.basic.httpserver.request.HttpRequest;
import ru.otus.java.basic.httpserver.response.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultStaticResourceProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        // 1.txt
        String filename = request.getUri().substring(1);
        Path filePath = Paths.get("static/", filename);
        byte[] fileData = Files.readAllBytes(filePath);

        Response response = new Response("");
        response.addHeader("Content-Length", String.valueOf(fileData.length));

        output.write(response.getBytes());
        output.write(fileData);
    }
}
