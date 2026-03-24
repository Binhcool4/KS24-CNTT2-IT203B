package Bai4;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        DashboardService service = new DashboardService();
        List<BenhNhanDTO> list = service.getDashboardData();

        System.out.println("Số bệnh nhân: " + list.size());
    }
}
