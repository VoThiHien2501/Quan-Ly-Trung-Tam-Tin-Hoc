/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.util.Date;
import java.sql.Time;

/**
 *
 * @author ASUS
 */
public class LichThi {
        private String maLichThi;
    private Date ngayThi;
    private Time tgianBatDau;
    private Time tgianKetThuc;
    private String maGV;
    private String maKyThi;
    private String maPhong;

    public LichThi() {
    }

    public LichThi(String maLichThi, Date ngayThi, Time tgianBatDau, Time tgianKetThuc, String maGV, String maKyThi, String maPhong) {
        this.maLichThi = maLichThi;
        this.ngayThi = ngayThi;
        this.tgianBatDau = tgianBatDau;
        this.tgianKetThuc = tgianKetThuc;
        this.maGV = maGV;
        this.maKyThi = maKyThi;
        this.maPhong = maPhong;
    }

    public String getMaLichThi() {
        return maLichThi;
    }

    public void setMaLichThi(String maLichThi) {
        this.maLichThi = maLichThi;
    }

    public Date getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(Date ngayThi) {
        this.ngayThi = ngayThi;
    }

    public Time getTgianBatDau() {
        return tgianBatDau;
    }

    public void setTgianBatDau(Time tgianBatDau) {
        this.tgianBatDau = tgianBatDau;
    }

    public Time getTgianKetThuc() {
        return tgianKetThuc;
    }

    public void setTgianKetThuc(Time tgianKetThuc) {
        this.tgianKetThuc = tgianKetThuc;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getMaKyThi() {
        return maKyThi;
    }

    public void setMaKyThi(String maKyThi) {
        this.maKyThi = maKyThi;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }
    
    
    
}
