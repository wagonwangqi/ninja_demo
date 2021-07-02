package mag.gaia.common.etc;

public enum Permission {
    readonly("read-only"),
    readwrite("read-write");

    private String permission;

    private Permission(String permission) {
        this.permission = permission;
    }

    public String toString() {
        return this.permission;
    }

}
