public class Cau5 {
    static class InvalidAgeException extends Exception {

        public InvalidAgeException(String msg) {
            super(msg);
        }
    }

    static class User {
        private String name;
        private int age;

        public void setAge(int age) throws InvalidAgeException {
            if (age < 0 || age > 120) {
                throw new InvalidAgeException("Lỗi nghiệp vụ: Tuổi " + age + " không hợp lệ (Phải từ 0-120)!");
            }
            this.age = age;
        }

        public int getAge() {
            return age;
        }
    }

    public static void main(String[] args) {
        User user = new User();

        System.out.println("--- THỬ NGHIỆM CUSTOM EXCEPTION ---");

        try {
            user.setAge(150);
            System.out.println("Tuổi đã được thiết lập: " + user.getAge());

        } catch (InvalidAgeException e) {
            System.out.println(">>> THÔNG BÁO HỆ THỐNG: " + e.getMessage());

             e.printStackTrace();
        }

        System.out.println("Chương trình tiếp tục vận hành...");
    }
}
