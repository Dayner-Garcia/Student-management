package domain.Models.Students;

public class PresentialStudent extends Student {
    public PresentialStudent(String name, String code) {
        super(name, code);
    }

    @Override
    public String getType() {
        return "Presencial";
    }
}
