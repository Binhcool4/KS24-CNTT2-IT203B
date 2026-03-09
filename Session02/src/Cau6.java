public class Cau6 {
    @FunctionalInterface
    interface UserProcessor {
        String process(User u);
    }

    static class User {
        private String username;

        public User(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }

    class UserUtils {
        public static String convertToUpperCase(User u) {
            if (u == null || u.getUsername() == null) {
                return "";
            }
            return u.getUsername().toUpperCase();
        }
    }

    public static void main(String[] args) {
        User myUser = new User("loi_bui_duc");

        UserProcessor processor = UserUtils::convertToUpperCase;

        String result = processor.process(myUser);

        System.out.println("Username gốc: " + myUser.getUsername());
        System.out.println("Sau khi xử lý: " + result);
    }
}
