/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class KyThi {
        private String maKyThi;
    private String tenKyThi;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String hinhThucThi;
    private String ghiChu;
    private String maKH;
    private String maNV;

    public KyThi() {
    }

    public KyThi(String maKyThi, String tenKyThi, Date ngayBatDau, Date ngayKetThuc, String hinhThucThi, String ghiChu, String maKH, String maNV) {
        this.maKyThi = maKyThi;
        this.tenKyThi = tenKyThi;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.hinhThucThi = hinhThucThi;
        this.ghiChu = ghiChu;
        this.maKH = maKH;
        this.maNV = maNV;
    }

    public String getMaKyThi() {
        return maKyThi;
    }

    public void setMaKyThi(String maKyThi) {
        this.maKyThi = maKyThi;
    }

    public String getTenKyThi() {
        return tenKyThi;
    }

    public void setTenKyThi(String tenKyThi) {
        this.tenKyThi = tenKyThi;
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

    public String getHinhThucThi() {
        return hinhThucThi;
    }

    public void setHinhThucThi(String hinhThucThi) {
        this.hinhThucThi = hinhThucThi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    
    
}
