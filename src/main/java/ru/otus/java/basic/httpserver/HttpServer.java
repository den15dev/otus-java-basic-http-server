package ru.otus.java.basic.httpserver;

import ru.otus.java.basic.httpserver.request.HttpRequest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private final int port;
    private final Dispatcher dispatcher;

    public HttpServer(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            ExecutorService service = Executors.newFixedThreadPool(10);

            while (true) {
                Socket socket = serverSocket.accept();

                service.execute(() -> {
                    try (Socket clientSocket = socket) {
                        byte[] buffer = new byte[8192];
                        int n = clientSocket.getInputStream().read(buffer);
                        if (n < 1) {
                            return;
                        }

                        System.out.println("Thread: " + Thread.currentThread().getName());
                        String rawRequest = new String(buffer, 0, n);
                        HttpRequest request = new HttpRequest(rawRequest);
                        request.info(true);
                        System.out.println("-----\n");

                        dispatcher.execute(request, clientSocket.getOutputStream());

                    } catch (IOException e) {
                        System.err.println("Произошла ошибка при обработке запроса: " + e.getMessage());
                        throw new RuntimeException(e);
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
