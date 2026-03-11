package Cau2;

public class UserService {

    public boolean checkRegistrationAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Tuổi không được phép là số âm: " + age);
        }

        return age >= 18;
    }
}
