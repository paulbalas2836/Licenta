package licenta.project.Struct;

public enum Provider {
    LOCAL("L"), GOOGLE("G");

    private final String code;

    Provider(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
