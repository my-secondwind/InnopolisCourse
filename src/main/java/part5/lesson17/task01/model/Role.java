package part5.lesson17.task01.model;

import part5.lesson17.task01.model.enums.RolesNames;

import java.util.Objects;

/**
 * Role
 *
 * @author Ekaterina Belolipetskaya
 */
public class Role {
    private Integer id;
    private RolesNames name;
    private String description;

    public Role() {
    }

    public Role(Integer id, RolesNames name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RolesNames getName() {
        return name;
    }

    public void setName(RolesNames name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = RolesNames.valueOf(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + name +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                name == role.name &&
                Objects.equals(description, role.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
