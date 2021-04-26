package model;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

public class StaffForm {
    private Long id;
    private String name;
    private Date birthday;
    private MultipartFile avatar;
    private String gender;

    public StaffForm() {
    }

    public StaffForm(Long id, String name, Date birthday, MultipartFile avatar, String gender) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.avatar = avatar;
        this.gender = gender;
    }

    public StaffForm(Long id, String name, Date birthday, String gender) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
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

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "StaffForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", avatar=" + avatar +
                ", gender='" + gender + '\'' +
                '}';
    }
}
