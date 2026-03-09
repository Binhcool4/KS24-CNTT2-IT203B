public class Cau5 {
    interface UserActions {
        default void logActivity(String activity) {
            System.out.println("[USER LOG]: " + activity);
        }
    }

    interface AdminActions {
        default void logActivity(String activity) {
            System.out.println("[ADMIN LOG]: " + activity);
        }
    }

    static class SuperAdmin implements UserActions, AdminActions {

        @Override
        public void logActivity(String activity) {
             System.out.println("[SUPER LOG]: " + activity);

            AdminActions.super.logActivity(activity);
        }
    }

        public static void main(String[] args) {
            SuperAdmin sa = new SuperAdmin();


            sa.logActivity("Đăng nhập vào hệ thống tối cao");
        }

}
