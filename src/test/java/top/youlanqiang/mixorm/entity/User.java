package top.youlanqiang.mixorm.entity;

import top.youlanqiang.mixorm.annotation.DbColumn;
import top.youlanqiang.mixorm.annotation.DbId;
import top.youlanqiang.mixorm.annotation.DbTable;
import top.youlanqiang.mixorm.annotation.IdType;

@DbTable("user")
public class User {

    @DbId(value = "id",type = IdType.INCREMENT)
    private int id;

    @DbColumn("name")
    private String name;

    @DbColumn("email")
    private String email;

    @DbColumn("age")
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
