/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author ASUS
 */
public class PhongHoc {
        private String maPhong;
    private String tenPhong;
    private String tinhTrang;
    private int sucChua;

    public PhongHoc() {
    }

    public PhongHoc(String maPhong, String tenPhong, String tinhTrang, int sucChua) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.tinhTrang = tinhTrang;
        this.sucChua = sucChua;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }
    
}
