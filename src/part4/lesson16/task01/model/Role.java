package part4.lesson16.task01.model;

import part4.lesson16.task01.model.enums.RolesNames;

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
}
