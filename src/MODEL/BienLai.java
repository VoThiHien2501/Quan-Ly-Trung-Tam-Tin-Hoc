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
public class BienLai {
        private String maBL;
    private Date ngayTao;
    private BigDecimal soTien;
    private String phuongThucThanhToan;
    private String maHV;
    private String maNV;

    public BienLai() {
    }

    public BienLai(String maBL, Date ngayTao, BigDecimal soTien, String phuongThucThanhToan, String maHV, String maNV) {
        this.maBL = maBL;
        this.ngayTao = ngayTao;
        this.soTien = soTien;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.maHV = maHV;
        this.maNV = maNV;
    }

    public String getMaBL() {
        return maBL;
    }

    public void setMaBL(String maBL) {
        this.maBL = maBL;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public BigDecimal getSoTien() {
        return soTien;
    }

    public void setSoTien(BigDecimal soTien) {
        this.soTien = soTien;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public String getMaHV() {
        return maHV;
    }

    public void setMaHV(String maHV) {
        this.maHV = maHV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    
    
    
}
