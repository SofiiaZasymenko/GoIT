package module13;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import module13.models.Address;
import module13.models.Comment;
import module13.models.Company;
import module13.models.Post;
import module13.models.Task;
import module13.models.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Comparator;
import java.util.List;

public class HomeworkTasks {
    private static final String FILE_PATH = "src/main/java/module13";

    public static void main(String[] args) throws IOException, InterruptedException {
        UserService userService = new UserService();
        PostService postService = new PostService();
        System.out.println("--- Task 1 ---");
        System.out.println("--- Create User ---");
        System.out.println(userService.createUser(getTestUser()));
        System.out.println("--- Update User ---");
        System.out.println(userService.updateUser(7, getTestUser()));
        System.out.println("--- Delete User ---");
        userService.deleteUser(3);
        System.out.println("--- Get all Users ---");
        System.out.println(userService.getAllUsers());
        System.out.println("--- Get User by ID ---");
        System.out.println(userService.getUserById(5));
        System.out.println("--- Get Users by username ---");
        System.out.println(userService.getUserByUsername("Delphine"));

        System.out.println("--- Task 2 ---");
        Integer userId = 1;
        List<Post> posts = userService.getPostsByUserId(userId);
        if (!posts.isEmpty()) {
            Integer lastPostId = posts.stream()
                    .map(Post::getId)
                    .max(Comparator.naturalOrder())
                    .get();
            List<Comment> comments = postService.getCommentsByPostId(lastPostId);
            String fileName = String.format("%s/user-%s-post-%s-comments.json", FILE_PATH, userId, lastPostId);
            System.out.println("Writing comments into the file: " + fileName);
            writeToFile(fileName, comments);
        } else {
            System.out.println("The user " + userId + " doesn't have posts");
        }

        System.out.println("--- Task 3 ---");
        List<Task> allTasks = userService.getTasksByUserId(userId, null);
        List<Task> uncompletedTasks = userService.getTasksByUserId(userId, false);
        System.out.println("--- All tasks ---");
        System.out.println(allTasks);
        System.out.println("--- Open tasks ---");
        System.out.println(uncompletedTasks);
    }

    public static void writeToFile(String fileName, List<Comment> comments) {
        try (Writer writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(comments, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static User getTestUser() {
        User user = new User();
        Address address = new Address();
        Address.Geo geo = new Address.Geo();
        Company company = new Company();
        user.setName("Kurtis Weissnat");
        user.setUsername("Elwyn.Skiles");
        user.setEmail("Telly.Hoeger@billy.biz");
        address.setStreet("Rex Trail");
        address.setSuite("Suite 280");
        address.setCity("Howemouth");
        address.setZipcode("58804-1099");
        geo.setLat(24.8918);
        geo.setLng(21.8984);
        address.setGeo(geo);
        user.setAddress(address);
        user.setPhone("210.067.6132");
        user.setWebsite("elvis.io");
        company.setName("Johns Group");
        company.setCatchPhrase("Configurable multimedia task-force");
        company.setBs("generate enterprise e-tailers");
        user.setCompany(company);
        return user;
    }
}
