package module13;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequestService {
    public HttpResponse<String> makeRequest(String url, String method, HttpRequest.BodyPublisher bodyPublisher) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .method(method, bodyPublisher)
                .build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void assertResponseStatus(HttpResponse<?> response, int expectedStatus) {
        if (response.statusCode() != expectedStatus) {
            throw new RuntimeException(String.format("The status code is not %s: %s. Response: %s",
                    expectedStatus, response.statusCode(), response.body()));
        }
    }
}
