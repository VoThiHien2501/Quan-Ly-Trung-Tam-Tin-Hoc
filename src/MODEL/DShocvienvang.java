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
public class DShocvienvang {
        private String maLop;
    private String maHV;
    private Date ngayDiemDanh;
    private String ghiChu;

    public DShocvienvang() {
    }

    public DShocvienvang(String maLop, String maHV, Date ngayDiemDanh, String ghiChu) {
        this.maLop = maLop;
        this.maHV = maHV;
        this.ngayDiemDanh = ngayDiemDanh;
        this.ghiChu = ghiChu;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getMaHV() {
        return maHV;
    }

    public void setMaHV(String maHV) {
        this.maHV = maHV;
    }

    public Date getNgayDiemDanh() {
        return ngayDiemDanh;
    }

    public void setNgayDiemDanh(Date ngayDiemDanh) {
        this.ngayDiemDanh = ngayDiemDanh;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
}
