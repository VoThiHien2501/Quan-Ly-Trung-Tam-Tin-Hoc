/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.sql.Time;

/**
 *
 * @author ASUS
 */
public class CaHoc {
        private String maCaHoc;
    private String tenCaHoc;
    private java.sql.Time tgianBatDau;
    private java.sql.Time tgianKetThuc;

    public CaHoc() {
    }

    public CaHoc(String maCaHoc, String tenCaHoc, Time tgianBatDau, Time tgianKetThuc) {
        this.maCaHoc = maCaHoc;
        this.tenCaHoc = tenCaHoc;
        this.tgianBatDau = tgianBatDau;
        this.tgianKetThuc = tgianKetThuc;
    }

    public String getMaCaHoc() {
        return maCaHoc;
    }

    public void setMaCaHoc(String maCaHoc) {
        this.maCaHoc = maCaHoc;
    }

    public String getTenCaHoc() {
        return tenCaHoc;
    }

    public void setTenCaHoc(String tenCaHoc) {
        this.tenCaHoc = tenCaHoc;
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
    
}
