package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

/**
 *
 */
public class CustomerController {
    public static void main(String[] args) {
        HashMap<String, Customer> customers = new HashMap<>();
        Gson gson = new Gson();
        String jsonFileAsAString = getJsonFile();
        Dataset dataset = gson.fromJson(jsonFileAsAString, Dataset.class);

        for (Event event : dataset.events()) {
            if (!customers.containsKey(event.customerId())) {
                if (event.eventType().equals("start")) {
                    customers.put(event.customerId(), new Customer(event.customerId(), - event.timestamp()));
                } else {
                    customers.put(event.customerId(), new Customer(event.customerId(), event.timestamp()));
                }

            } else {
                if (event.eventType().equals("start")) {
                    customers.get(event.customerId()).setConsumption(customers.get(event.customerId()).getConsumption() - event.timestamp());
                } else {
                    customers.get(event.customerId()).setConsumption(customers.get(event.customerId()).getConsumption() + event.timestamp());
                }
            }
        }
        Result result = new Result(customers.values());
        String newResult = gson.toJson(result);

        sendResult(newResult);
    }

    public static String getJsonFile(){
        try (InputStream inputStream = new URI("http://localhost:8080/v1/dataset").toURL().openStream()) {
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            return json.toString();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendResult(String result) {
        HttpRequest request = null;
        try (HttpClient client = HttpClient.newHttpClient()){
            request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/v1/result"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(result))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException(response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}