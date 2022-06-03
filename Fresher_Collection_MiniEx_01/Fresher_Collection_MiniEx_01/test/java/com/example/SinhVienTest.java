package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class SinhVienTest {

    @Test
    void tinhDiemTrungBinh() {
        Diem d1= new Diem(new MonHoc ("toan",1,2),8);
        Diem d2= new Diem(new MonHoc ("van",1,1),8);
        Set<Diem> dsDiem= new HashSet<>();
        dsDiem.add(d1);
        dsDiem.add(d2);
        SinhVien s= new SinhVien("1","1",dsDiem);
        Assertions.assertEquals(8,s.tinhDiemTrungBinh(),0.000000001);
    }

    @Test
    void bangDiem() {
        Diem d1= new Diem(new MonHoc ("toan",1,2),8);
        Diem d2= new Diem(new MonHoc ("van",1,1),10);
        Set<Diem> dsDiem= new HashSet<>();
        dsDiem.add(d1);
        dsDiem.add(d2);
        SinhVien s= new SinhVien("1","1",dsDiem);
        System.out.println(s.bangDiem());
    }

    @Test
    void xepLoai() {
        Diem d1= new Diem(new MonHoc ("toan",1,2),8);
        Diem d2= new Diem(new MonHoc ("van",1,1),10);
        Set<Diem> dsDiem= new HashSet<>();
        dsDiem.add(d1);
        dsDiem.add(d2);
        SinhVien s= new SinhVien("1","1",dsDiem);
        Assertions.assertSame("GIOI",s.xepLoai());
    }

    @Test
    void themDiem() {
        Diem d1= new Diem(new MonHoc ("toan",1,2),8);
        Diem d2= new Diem(new MonHoc ("van",1,1),8);
        Diem d3= new Diem(new MonHoc ("anh",1,1),10);
        Set<Diem> dsDiem= new HashSet<>();
        SinhVien s= new SinhVien("1","Tien",dsDiem);
        s.themDiem(d1);
        s.themDiem(d2);
        s.themDiem(d3);
        System.out.println(s.bangDiem());
    }
}