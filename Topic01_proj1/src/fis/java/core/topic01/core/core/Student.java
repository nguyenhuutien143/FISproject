package fis.java.core.topic01.core.core;

import java.util.Date;
public class Student implements Comparable<Student> {
    private int code;
    private String name;
    private Date birthDate;


    public Student(int code, String name, Date birthDate) throws IllegalArgumentException{
        this.code = code;
        this.name = name;
        if(!isValidBirthdate(birthDate)) throw new IllegalArgumentException();
        this.birthDate = birthDate;
    }


    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(Date birthDate) throws IllegalArgumentException{
        if(isValidBirthdate(birthDate)) throw new IllegalArgumentException();
        this.birthDate = birthDate;
    }
    private boolean isValidBirthdate(Date birthDate){
        return true;
    }
    @Override
    public String toString() {
        return "Student{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }


    @Override
    public int compareTo(Student student) {
        return 0;
    }
}

