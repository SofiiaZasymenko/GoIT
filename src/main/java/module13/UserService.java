package module13;

import module13.models.Post;
import module13.models.Task;
import module13.models.User;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class UserService {
    private static final String USER_URL = "https://jsonplaceholder.typicode.com/users";

    private final HttpRequestService requestService;

    public UserService() {
        this.requestService = new HttpRequestService();
    }

    public User createUser(User user) {
        String body = JsonMapper.toJson(user);
        HttpResponse<String> response = requestService.makeRequest(USER_URL, "POST", HttpRequest.BodyPublishers.ofString(body));
        requestService.assertResponseStatus(response, 201);
        return JsonMapper.map(response.body(), User.class);
    }

    public User updateUser(Integer id, User user) {
        String url = USER_URL + "/" + id;
        String body = JsonMapper.toJson(user);
        HttpResponse<String> response = requestService.makeRequest(url, "PUT", HttpRequest.BodyPublishers.ofString(body));
        requestService.assertResponseStatus(response, 200);
        return JsonMapper.map(response.body(), User.class);
    }

    public void deleteUser(Integer id) {
        String url = USER_URL + "/" + id;
        HttpResponse<String> response = requestService.makeRequest(url, "DELETE", HttpRequest.BodyPublishers.noBody());
        requestService.assertResponseStatus(response, 200);
    }

    public List<User> getAllUsers() {
        HttpResponse<String> response = requestService.makeRequest(USER_URL, "GET", HttpRequest.BodyPublishers.noBody());
        requestService.assertResponseStatus(response, 200);
        return JsonMapper.mapToList(response.body(), User.class);
    }

    public User getUserById(Integer id) {
        String url = USER_URL + "/" + id;
        HttpResponse<String> response = requestService.makeRequest(url, "GET", HttpRequest.BodyPublishers.noBody());
        requestService.assertResponseStatus(response, 200);
        return JsonMapper.map(response.body(), User.class);
    }

    public List<User> getUserByUsername(String username) {
        String url = USER_URL + "?username=" + username;
        HttpResponse<String> response = requestService.makeRequest(url, "GET", HttpRequest.BodyPublishers.noBody());
        requestService.assertResponseStatus(response, 200);
        return JsonMapper.mapToList(response.body(), User.class);
    }

    public List<Post> getPostsByUserId(Integer userId) {
        String url = USER_URL + "/" + userId + "/posts";
        HttpResponse<String> response = requestService.makeRequest(url, "GET", HttpRequest.BodyPublishers.noBody());
        requestService.assertResponseStatus(response, 200);
        return JsonMapper.mapToList(response.body(), Post.class);
    }

    public List<Task> getTasksByUserId(Integer userId, Boolean completed) {
        String completedParam = completed != null
                ? String.format("?completed=%b", completed)
                : "";
        String url = USER_URL + "/" + userId + "/todos" + completedParam;
        HttpResponse<String> response = requestService.makeRequest(url, "GET", HttpRequest.BodyPublishers.noBody());
        requestService.assertResponseStatus(response, 200);
        return JsonMapper.mapToList(response.body(), Task.class);
    }
}