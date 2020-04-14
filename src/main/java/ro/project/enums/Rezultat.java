package ro.project.enums;

public enum Rezultat {
    INVENTIV(1),
    DIRECTIV(2),
    ORGANIZAT(3);

    public final int label;

    private Rezultat(int label) {
        this.label = label;
    }
}
