/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author ASUS
 */
public class Lop {
    private String maLop;
    private String tenLop;
    private int soLgHV;
    private String maCaHoc;
    private String maKH;
    private String maGV;
    private String maPH;

    public Lop() {
    }

    public Lop(String maLop, String tenLop, int soLgHV, String maCaHoc, String maKH, String maGV, String maPH) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.soLgHV = soLgHV;
        this.maCaHoc = maCaHoc;
        this.maKH = maKH;
        this.maGV = maGV;
        this.maPH = maPH;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public int getSoLgHV() {
        return soLgHV;
    }

    public void setSoLgHV(int soLgHV) {
        this.soLgHV = soLgHV;
    }

    public String getMaCaHoc() {
        return maCaHoc;
    }

    public void setMaCaHoc(String maCaHoc) {
        this.maCaHoc = maCaHoc;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getMaPH() {
        return maPH;
    }

    public void setMaPH(String maPH) {
        this.maPH = maPH;
    }
    
}
