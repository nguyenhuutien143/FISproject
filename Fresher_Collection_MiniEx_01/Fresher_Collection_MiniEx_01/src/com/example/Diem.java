package com.example;

import java.io.Serializable;

public class Diem implements Comparable<Diem>, Serializable {
    private MonHoc mon;
    private int diem;

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public Diem(MonHoc mon, int diem) {
        this.mon = mon;
        this.diem = diem;
    }
    public int tinhDiemTong(){
        int diemTong=0;
        diemTong=this.getDiem()*(this.getMon().getTcTH()+this.getMon().getTcLT());
        return diemTong;
    }
    public int tinhSTC(){
        int stc;
        stc= this.getMon().getTcLT()+this.getMon().getTcTH();
        return stc;
    }

    public boolean equals(Object that) {
        // Hai diem duoc goi la bang nhau neu cua cung mot mon
        if (that instanceof Diem) {
            return this.mon.equals(((Diem) that).mon);
        }
        return false;
    }

    @Override
    public int compareTo(Diem o) {
        // TODO Auto-generated method stub

        return 0;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public Diem clone() {
        return null;
    }

    public MonHoc getMon() {
        return mon;
    }

    public void setMon(MonHoc mon) {
        this.mon = mon;
    }
}
