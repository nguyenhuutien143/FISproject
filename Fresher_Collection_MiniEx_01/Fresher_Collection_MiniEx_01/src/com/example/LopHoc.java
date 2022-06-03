package com.example;

import java.util.*;

import java.util.stream.Collectors;
public class LopHoc {
    private String ten;
    private String giaoVien;
    private List<SinhVien> dsLop = new ArrayList<SinhVien>();

    public LopHoc(String ten, String giaoVien,List<SinhVien> dsLop) {
        this.ten = ten;
        this.giaoVien = giaoVien;
        this.dsLop=dsLop;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(String giaoVien) {
        this.giaoVien = giaoVien;
    }

    public List<SinhVien> getDsLop() {
        return dsLop;
    }

    public void setDsLop(List<SinhVien> dsLop) {
        this.dsLop = dsLop;
    }

    public boolean them(SinhVien svMoi) {
        return dsLop.add(svMoi);
    }

    //TODO Cau 4
    public String inDiem() {
    /*
    Danh Sach Diem Lop : ten
    Giao Vien Chu Nhiem : giaoVien
    STT      MSSV        Ten              Diem TB   XepLoai
    1        123456      Nguyen Van A     8.4       GIOI
    2        678919      Nguyen Van B     6         TB-KHA
    3        112456      Nguyen Van C     7         KHA
    */
        //...
        StringBuilder inDiem=new StringBuilder();
        inDiem.append("Danh Sach Diem Lop :"+this.ten+"\nGiao Vien Chu Nhiem: "+this.giaoVien+"\nSTT|MSSV|Ten|DiemTB|XepLoai\n");
        int i=1;
        for (SinhVien s : this.dsLop){
            inDiem.append(""+i+++" "+ s.getMssv()+" "+s.getTen()+" "+s.tinhDiemTrungBinh()+" "+s.xepLoai()+"\n");

        }
        return inDiem.toString();
    }

    @Override
    public String toString() {
        return "LopHoc{" +
                "ten='" + ten + '\'' +
                ", giaoVien='" + giaoVien + '\'' +
                ", dsLop=" + dsLop +
                '}';
    }

    //TODO Cau 5
    public List<SinhVien> top10() {
        //Tra ve danh sach 10 sinh vien co diem trung binh lon nhat lop
        //...

        //ArrayList<SinhVien> dssv = getUnsortedEmployeeList();

        // sắp xếp 2 sinh viên theo điểm tb
       /* Comparator<SinhVien> compareByDiemtb =
                (SinhVien o1, SinhVien o2) -> o1.tinhDiemTrungBinh().compareTo( o2.tinhDiemTrungBinh() );
        //sắp xếp dssv theo diểm trung bình
        Collections.sort(dsLop, compareByDiemtb);
        //tra lai 10 sinh vien co diem tb lon nhat
        return dsLop.subList(0, 9);;*/
        Collections.sort(this.dsLop,(o1, o2) -> {
            Double d1 = o1.tinhDiemTrungBinh();
            Double d2 = o2.tinhDiemTrungBinh();
            return d2.compareTo(d1);
        });
        List<SinhVien> list = new ArrayList<>();
        if(dsLop.size() > 10){
            for (int i = 0;i<10;i++){
                list.add(this.dsLop.get(i));
            }
        }else{
            list = dsLop;
        }
        return list;
    }

    //TODO Cau 6
    public List<SinhVien> sinhVienYeu() {
        //Tra ve danh sach vien vien xep loai YEU
        //...
        List<SinhVien> list = this.dsLop.stream().filter(sinhVien -> sinhVien.xepLoai().equals("YEU")).collect(Collectors.toList());
        return list;
    }
    // xay dung 1 class sap xep theo diem tb
    /*public class StudentSortDtb implements Comparator<SinhVien>{

    }*/
}
