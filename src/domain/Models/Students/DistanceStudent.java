package domain.Models.Students;

public class DistanceStudent extends Student {
    public DistanceStudent(String name, String code) {
        super(name, code);
    }

    @Override
    public String getType() {
        return "Distancia";
    }
}
