package Bai4;

import java.util.ArrayList;
import java.util.List;

public class BenhNhanDTO {

    private final int id;
    private final String name;
    private final List<DichVu> dsDichVu;

    public BenhNhanDTO(int id, String name) {
        this.id = id;
        this.name = name;
        this.dsDichVu = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<DichVu> getDsDichVu() {
        return dsDichVu;
    }
}
