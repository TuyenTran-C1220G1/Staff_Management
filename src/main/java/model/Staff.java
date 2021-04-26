package model;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Table(name="staffs")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date birthday;
    private String avatar;
    private String gender;

    public Staff() {
    }

    public Staff(String name, Date birthday, String avatar, String gender) {
        this.name = name;
        this.birthday = birthday;
        this.avatar = avatar;
        this.gender = gender;
    }

    public Staff(Long id, String name, Date birthday, String avatar, String gender) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.avatar = avatar;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
