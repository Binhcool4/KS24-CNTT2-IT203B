enum Role { ADMIN, MODERATOR, USER }
enum Action { DELETE_USER, LOCK_USER, VIEW_PROFILE }

record User(String username, Role role) {}

public class Cau5 {
    public boolean canPerformAction(User user, Action action) {
        if (user == null || action == null) return false;

        return switch (user.role()) {
            case ADMIN -> true;
            case MODERATOR -> action == Action.LOCK_USER || action == Action.VIEW_PROFILE;
            case USER -> action == Action.VIEW_PROFILE;
        };
    }
}