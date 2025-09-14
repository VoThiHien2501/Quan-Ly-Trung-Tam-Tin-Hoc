/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class KetQua {
      private String maHV;
    private String maKyThi;
    private String maGV;
    private BigDecimal diem;
    private Date ngayCham;
    private String ghiChu;

    public KetQua() {
    }

    public KetQua(String maHV, String maKyThi, String maGV, BigDecimal diem, Date ngayCham, String ghiChu) {
        this.maHV = maHV;
        this.maKyThi = maKyThi;
        this.maGV = maGV;
        this.diem = diem;
        this.ngayCham = ngayCham;
        this.ghiChu = ghiChu;
    }

    public String getMaHV() {
        return maHV;
    }

    public void setMaHV(String maHV) {
        this.maHV = maHV;
    }

    public String getMaKyThi() {
        return maKyThi;
    }

    public void setMaKyThi(String maKyThi) {
        this.maKyThi = maKyThi;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public BigDecimal getDiem() {
        return diem;
    }

    public void setDiem(BigDecimal diem) {
        this.diem = diem;
    }

    public Date getNgayCham() {
        return ngayCham;
    }

    public void setNgayCham(Date ngayCham) {
        this.ngayCham = ngayCham;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
}
