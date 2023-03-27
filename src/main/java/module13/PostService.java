package module13;

import module13.models.Comment;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PostService {
    private static final String POST_URL = "https://jsonplaceholder.typicode.com/posts";

    private final HttpRequestService requestService;

    public PostService() {
        this.requestService = new HttpRequestService();
    }

    public List<Comment> getCommentsByPostId(Integer postId) {
        String url = POST_URL + "/" + postId + "/comments";
        HttpResponse<String> response = requestService.makeRequest(url, "GET", HttpRequest.BodyPublishers.noBody());
        requestService.assertResponseStatus(response, 200);
        return JsonMapper.mapToList(response.body(), Comment.class);
    }
}
