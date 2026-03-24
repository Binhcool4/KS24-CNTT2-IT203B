package Bai4;

import java.sql.*;
import java.util.*;

public class DashboardService {

    public List<BenhNhanDTO> getDashboardData() {

        List<BenhNhanDTO> result = new ArrayList<>();
        Map<Integer, BenhNhanDTO> map = new HashMap<>();

        String sql = "SELECT p.patient_id, p.name, s.service_id, s.service_name " +
                "FROM Patients p " +
                "LEFT JOIN Services_Used s ON p.patient_id = s.patient_id";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("patient_id");

                BenhNhanDTO bn = map.get(id);
                if (bn == null) {
                    bn = new BenhNhanDTO(id, rs.getString("name"));
                    map.put(id, bn);
                }

                int serviceId = rs.getInt("service_id");

                // bệnh nhân chưa có dịch vụ
                if (!rs.wasNull()) {
                    DichVu dv = new DichVu(serviceId, rs.getString("service_name"));
                    bn.getDsDichVu().add(dv);
                }
            }

            result.addAll(map.values());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
