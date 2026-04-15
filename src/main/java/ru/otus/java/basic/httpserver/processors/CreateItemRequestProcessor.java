package ru.otus.java.basic.httpserver.processors;

import com.google.gson.Gson;
import ru.otus.java.basic.httpserver.request.HttpRequest;
import ru.otus.java.basic.httpserver.app.Item;
import ru.otus.java.basic.httpserver.app.ItemsService;
import ru.otus.java.basic.httpserver.response.HttpStatus;
import ru.otus.java.basic.httpserver.response.JsonResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class CreateItemRequestProcessor implements RequestProcessor {
    private ItemsService itemsService;

    public CreateItemRequestProcessor(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        Gson gson = new Gson();
        Item item = gson.fromJson(request.getBody(), Item.class);
        itemsService.addItem(item);

        String body = gson.toJson(Map.of("id", item.getId()));
        JsonResponse response = new JsonResponse(HttpStatus.CREATED, body);

        output.write(response.getBytes());
    }
}
