/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class KhoaHoc {
    private String maKH;
    private String tenKH;
    private java.sql.Date ngayBatDau;
    private java.sql.Date ngayKetThuc;
    private double hocPhi;
    private int soLgLop;
    private int soBuoiHoc;
    private String maNV;

    public KhoaHoc() {
    }

    public KhoaHoc(String maKH, String tenKH, Date ngayBatDau, Date ngayKetThuc, double hocPhi, int soLgLop, int soBuoiHoc, String maNV) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.hocPhi = hocPhi;
        this.soLgLop = soLgLop;
        this.soBuoiHoc = soBuoiHoc;
        this.maNV = maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getSoLgLop() {
        return soLgLop;
    }

    public void setSoLgLop(int soLgLop) {
        this.soLgLop = soLgLop;
    }

    public int getSoBuoiHoc() {
        return soBuoiHoc;
    }

    public void setSoBuoiHoc(int soBuoiHoc) {
        this.soBuoiHoc = soBuoiHoc;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    
}
