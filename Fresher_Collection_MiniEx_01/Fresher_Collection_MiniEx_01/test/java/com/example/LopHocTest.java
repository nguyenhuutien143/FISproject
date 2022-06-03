package com.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LopHocTest {
        LopHoc lopHoc;
        Set<Diem> diems;
        Set<Diem> diems2;

        public LopHocTest() {
            List<SinhVien> sinhViens = new ArrayList<>();
            lopHoc = new LopHoc("FIS_RA", "Phuoc", sinhViens);
            diems = new HashSet<>();
            diems.add(new Diem(new MonHoc("Toan", 1, 2), 7));
            diems.add(new Diem(new MonHoc("Ly", 2, 3), 6));
            diems.add(new Diem(new MonHoc("Hoa", 5, 4), 5));
            diems2= new HashSet<>();
            diems2.add(new Diem(new MonHoc("Anh",1,1),4));
            sinhViens.add(new SinhVien("1", "Tien", diems));
            sinhViens.add(new SinhVien("2", "A", diems));
            sinhViens.add((new SinhVien("3", "B", diems)));
            sinhViens.add(new SinhVien("4","C",diems2));
            lopHoc.setDsLop(sinhViens);


        }

        @Test
        void inDiem() {
        /*Diem d1= new Diem(new MonHoc ("toan",1,2),8);
        Diem d2= new Diem(new MonHoc ("van",1,1),8);
        Diem d3= new Diem(new MonHoc ("anh",1,1),10);
        Set<Diem> dsDiem= new HashSet<>();
        Set<Diem> dsDiem1= new HashSet<>();
        dsDiem.add(d1);
        dsDiem1.add(d2);
        dsDiem1.add(d3);
        SinhVien s= new SinhVien("1","Tien",dsDiem);
        SinhVien s1= new SinhVien("2","T",dsDiem1);
        List<SinhVien> dsLop= new ArrayList<>();
        LopHoc Java= new LopHoc("java","PhuocNT",dsLop);
         */
            System.out.println(lopHoc.inDiem());
        }

    @Test
    void top10() {
        System.out.println(lopHoc.top10());
    }

    @Test
    void sinhVienYeu() {
        System.out.println(lopHoc.sinhVienYeu());
    }
}
