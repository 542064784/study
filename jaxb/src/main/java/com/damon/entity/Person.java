package com.damon.entity;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
/**
 * @author Damon Chen
 * @date 2018/11/29
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    @XmlAttribute(name = "id")
    private Integer id;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "age")
    private Integer age;
    @XmlAttribute(name = "sex")
    private String sex;

    public Person() {
    }

    public Person(Integer id, String name, Integer age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
