package fis.java.core.topic01.core;

import fis.java.core.topic01.core.core.Student;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class StudentTest {

    @org.junit.jupiter.api.Test
    void getCode() {
        Student student = new Student(code=1,name="tien",new Date());
        System.out.println();
    }
}