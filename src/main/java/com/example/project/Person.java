package com.example.project;

public class Person implements Comparable<Person> {
    private int id;
    private String  name;
    private int age;
    private Gender gender;
    private String description;
    private Department department;
    public Person(String name ,int age, Gender gender, int id){
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.id=id;
    }
    public void setDescription(String description){
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public int compareTo(Person o) {

        if(this.name.equals(o.name) && this.age==o.age &&this.gender==o.gender)
            return 0;
        if(!this.name.equals(o.name))
            return 1;
        if(this.name.equals(o.name) && this.age!=o.age)
            return 1;
        if(this.name.equals(o.name) && this.age==o.age && this.gender!=o.gender)
            return 1;
        if(this.name.equals(o.name) && this.age==o.age && this.gender==o.gender )
            return 1;
        return -1;
    }
    public String toString(){
        return "ID: "+ id+" Student: "+name+" has age: "+age+" gender: "+gender+" and is enrolled at department:";
    }
    public void setId(int id){
        this.id=id;
    }
}
