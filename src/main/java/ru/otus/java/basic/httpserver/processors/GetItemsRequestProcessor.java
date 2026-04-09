package ru.otus.java.basic.httpserver.processors;

import com.google.gson.Gson;
import ru.otus.java.basic.httpserver.request.HttpRequest;
import ru.otus.java.basic.httpserver.app.ItemsService;
import ru.otus.java.basic.httpserver.response.JsonResponse;

import java.io.IOException;
import java.io.OutputStream;


public class GetItemsRequestProcessor implements RequestProcessor {
    private ItemsService itemsService;

    public GetItemsRequestProcessor(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        Gson gson = new Gson();
        String result = gson.toJson(itemsService.getItems());
        JsonResponse response = new JsonResponse(result);
        output.write(response.getBytes());
    }
}
