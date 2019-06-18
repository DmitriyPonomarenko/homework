package part3.lesson15.entity;

public class Role {
    private RoleName name;
    private String description;

    public Role(RoleName roleName) {
        setName(roleName);
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public Integer getId() {
        if (name == null) return null;
        return name.number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
