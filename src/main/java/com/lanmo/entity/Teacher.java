package com.lanmo.entity;


import org.hibernate.annotations.Proxy;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="sys_teacher")
//@Lazy(false)
@Proxy(lazy=false)
public class Teacher implements Serializable {


    /**
     * ERROR: Table 'dev.hibernate_sequence' doesn't exist
     */
//    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="username")
    private String userName;
    @Column(name="sex")
    private Character sex;
    @Column(name="age")
    private Integer age;

    public Teacher() {
    }

    public Teacher(Integer id, String userName, Character sex, Integer age) {
        this.id = id;
        this.userName = userName;
        this.sex = sex;
        this.age = age;
    }

    public Teacher(String userName, Character sex, Integer age) {
        this.userName = userName;
        this.sex = sex;
        this.age = age;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }

}
