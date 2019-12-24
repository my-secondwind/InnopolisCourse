package part5.lesson17.task02.model;

import java.time.LocalDate;

/**
 * User
 * @author Ekaterina Belolipetskaya
 */
public class User {
    private Integer id;
    private String name;
    private LocalDate birthday;
    private Integer loginId;
    private String city;
    private String email;
    private String description;

    public User() {
    }

    public User(Integer id, String name, String birthday, Integer loginId, String city, String email, String description) {
        this.id = id;
        this.birthday = LocalDate.parse(birthday);
        this.name = name;
        this.loginId = loginId;
        this.city = city;
        this.email = email;
        this.description = description;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = LocalDate.parse(birthday);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", loginId=" + loginId +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
