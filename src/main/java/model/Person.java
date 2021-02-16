package model;

import java.io.Serializable;
import java.util.Objects;


public class Person implements Serializable {
    private int id;
    private String fname;
    private String lname;
    private int age;
    private String city;

    public Person() {
    }

    public Person(int id, String fname, String lname, int age, String city) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.city = city;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                age == person.age &&
                fname.equals(person.fname) &&
                lname.equals(person.lname) &&
                city.equals(person.city);
    }

    @Override
    public String toString() {
        return String.format("%s, %s,  %s,  %s, %s.",
                id, fname, lname, age, city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fname, lname, age, city);
    }
}