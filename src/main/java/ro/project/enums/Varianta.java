package ro.project.enums;

public enum Varianta {
    DA(2),
    NU(0);

    public final int label;

    private Varianta(int label) {
        this.label = label;
    }
}
