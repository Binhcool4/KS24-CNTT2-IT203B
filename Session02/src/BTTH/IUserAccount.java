package BTTH;

public interface IUserAccount {
    String getRole();

    default boolean checkAccess() {
        return "ADMIN".equalsIgnoreCase(getRole());
    }

    static boolean isStandardLength(String username) {
        return username != null && username.length() > 5;
    }
}
