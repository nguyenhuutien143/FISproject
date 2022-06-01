package fis.java.core.topic01.core.dao;

import fis.java.core.topic01.core.core.Student;

public class StudentDAO {
    public final int Max=100;
    private Student[] students;
    private int count;
    private ISortStrategy sortStrategy;

    public StudentDAO() {
        this.students = new Student[Max];
        this.count = 0;
        this.sortStrategy = new SelectionSortStrategy();
    }

    public void addStudent(Student student){
        //students.add(student);
    }

    public Student removeStudent(int code) {
        //todo
    return null;
    }
    public void display(){

    }
    public void sort(){

    }
    public void setSortStrategy(ISortStrategy sortStrategy){
    this.sortStrategy=sortStrategy;
    }
}
