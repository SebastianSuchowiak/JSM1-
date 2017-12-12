package Task1;

public abstract class AbstractEmployee implements Employee {

    private final String name;
    private final String role;

    AbstractEmployee(String role, String name) {
        this.role = role;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "[name=" + name
                + ", role=" + role
                + "]";
    }
}
