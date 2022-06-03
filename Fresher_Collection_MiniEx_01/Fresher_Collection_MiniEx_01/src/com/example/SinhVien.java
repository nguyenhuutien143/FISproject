package com.example;

import java.util.*;

public class SinhVien {
    private String mssv;
    private String ten;

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Set<Diem> getMonDaHoc() {
        return monDaHoc;
    }

    public void setMonDaHoc(Set<Diem> monDaHoc) {
        this.monDaHoc = monDaHoc;
    }

    private Set<Diem> monDaHoc = new HashSet<Diem>();

    public SinhVien(String mssv, String ten,Set<Diem> monDaHoc) {
        this.mssv = mssv;
        this.ten = ten;
        this.monDaHoc=monDaHoc;
    }

    public boolean themDiem(Diem diemMoi) {

        return this.monDaHoc.add(diemMoi);
    }

    //TODO Cau 1
    public double tinhDiemTrungBinh() {
        //Giong nhu cach tinh hien tai cua truong

        //...cach 1
        /*float dtb=0;
        int tongDiem=0;
        int tongTinChi=0;
        for(Diem diem : monDaHoc){
        tongDiem=tongDiem+diem.getDiem()*(diem.getMon().getTcLT()+diem.getMon().getTcTH());
        tongTinChi=tongTinChi+diem.getMon().getTcLT()+diem.getMon().getTcTH();
        }
        dtb=tongDiem/tongTinChi;
        DecimalFormat f = new DecimalFormat("##.00");
        return f.format(dtb);
         */
        //cach 2
        double dtb=0;
        int tongDiem=0;
        int tongTC=0;
        tongDiem=monDaHoc.stream().mapToInt(Diem::tinhDiemTong).sum();
        tongTC=monDaHoc.stream().mapToInt(Diem::tinhSTC).sum();
        dtb=tongDiem/tongTC;
        return dtb;
    }


    //TODO Cau 2
    public String bangDiem() {
    /*
     MSSV : 0203044
     Ten  : Nguyen Van A
     Danh Sach Diem
     STT  Ten  3Mon             Diem       So Tin Chi
     1    Cau Truc Du Lieu 1  8          3
     2    Cau Truc Du Lieu 2  8
     Diem Trung Binh   8.0
    */
        //...
        //StringBuilder
        int stt=1;
        int tinChi=0;
        StringBuilder bangDiem= new StringBuilder();
        bangDiem.append("MSSV:"+ this.mssv+"\n"+"Ten:"+this.ten+"\n"+"Danh Sach Diem"+"\n"+"STT    ten mon hoc   Diem    So Tin Chi \n");
        for(Diem diem:monDaHoc){
            bangDiem.append(stt+"  ");
            tinChi=diem.getMon().getTcLT()+diem.getMon().getTcTH();
            bangDiem.append(diem.getMon().getTen()+"  "+diem.getDiem()+" "+tinChi+"\n");
            stt++;
        }
        return bangDiem.toString();
    }


    //TODO Cau 3
    public String xepLoai() {
    /*
    Quy tac xep loai nhu sau
        DiemTB < 5 -> YEU
        DiemTB >= 5 va DiemTB < 6 -> TB
        DiemTB >= 6 va DiemTB < 7 -> TB-KHA
        DiemTB >= 7 va DiemTB < 8 -> KHA
        DiemTB >= 8 -> GIOI
    */

        //...

            if(this.tinhDiemTrungBinh()<5) return("YEU");
            else if((this.tinhDiemTrungBinh()>=5)&&((this.tinhDiemTrungBinh()<6))) return ("TB");
            else if((this.tinhDiemTrungBinh()>=6)&&((this.tinhDiemTrungBinh()<7))) return ("TB-KHA");
            else if((this.tinhDiemTrungBinh()>=7)&&((this.tinhDiemTrungBinh()<8))) return ("KHA");
            else return ("GIOI");
        }




}
