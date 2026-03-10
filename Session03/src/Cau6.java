import java.util.List;
import java.util.stream.Collectors;

class Post {
    List<String> tags;

    public Post(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }
}

public class Cau6 {
    public static void main(String[] args) {

        List<Post> posts = List.of(
                new Post(List.of("java", "backend")),
                new Post(List.of("python", "data"))
        );

        List<String> allTags = posts.stream()
                .flatMap(p -> p.getTags().stream())
                .collect(Collectors.toList());

        System.out.println(allTags);
    }
}