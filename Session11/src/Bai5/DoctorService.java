package Bai5;

import Bai5.DoctorDAO;
import Bai5.Doctor;

import java.util.List;
import java.util.Map;

public class DoctorService {
    private DoctorDAO dao = new DoctorDAO();

    public List<Doctor> getAll() {
        return dao.getAllDoctors();
    }

    public boolean addDoctor(Doctor d) throws Exception {
        if (d.getId().isEmpty())
            throw new Exception("Mã không được rỗng");

        if (d.getName().isEmpty())
            throw new Exception("Tên không được rỗng");

        if (d.getSpecialty().isEmpty())
            throw new Exception("Chuyên khoa không được rỗng");

        if (d.getSpecialty().length() > 50)
            throw new Exception("Chuyên khoa quá dài");

        return dao.insertDoctor(d);
    }

    public Map<String, Integer> stats() {
        return dao.countBySpecialty();
    }
}