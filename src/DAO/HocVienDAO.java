/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.HocVien;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class HocVienDAO {

    public List<HocVien> getHocVienByMaLop(String maLop) throws Exception {
        List<HocVien> list = new ArrayList<>();
        String sql = "SELECT * FROM HocVien WHERE MaLop = ?";

        try (Connection con = KetNoiCSDL.openConnection(); PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setString(1, maLop);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                HocVien hv = new HocVien();
                hv.setMaHV(rs.getString("MaHV"));
                hv.setHoTen(rs.getString("HoTen"));
                hv.setNgaySinh(rs.getDate("NgaySinh"));
                hv.setGioiTinh(rs.getString("GioiTinh"));
                hv.setSdt(rs.getString("SDT"));
                hv.setDiaChi(rs.getString("DiaChi"));
                hv.setNgayDangKy(rs.getDate("NgayDangKy"));
                hv.setMaLop(rs.getString("MaLop"));
                list.add(hv);
            }
        }

        return list;
    }

    public void insertHocVien(HocVien hv) throws Exception {
        String sql = "INSERT INTO HocVien (MaHV, HoTen, NgaySinh, GioiTinh, SDT, DiaChi, NgayDangKy, MaLop) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = KetNoiCSDL.openConnection(); PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, hv.getMaHV());
            pstm.setString(2, hv.getHoTen());
            pstm.setDate(3, new java.sql.Date(hv.getNgaySinh().getTime()));      // Sửa ở đây
            pstm.setString(4, hv.getGioiTinh());
            pstm.setString(5, hv.getSdt());
            pstm.setString(6, hv.getDiaChi());
            pstm.setDate(7, new java.sql.Date(hv.getNgayDangKy().getTime()));   // Và ở đây
            pstm.setString(8, hv.getMaLop());
            pstm.executeUpdate();
        }
    }
    
    public List<HocVien> getHocVienByMaLop1(String maLop) throws Exception {
        List<HocVien> list = new ArrayList<>();
        String sql = "SELECT * FROM HocVien WHERE MaLop = ?";
        try (Connection conn = KetNoiCSDL.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maLop);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HocVien hv = new HocVien(
                    rs.getString("MaHV"),
                    rs.getString("HoTen"),
                    rs.getDate("NgaySinh"),
                    rs.getString("GioiTinh"),
                    rs.getString("SDT"),
                    rs.getString("DiaChi"),
                    rs.getDate("NgayDangKy"),
                    rs.getString("MaLop")
                );
                list.add(hv);
            }
        }
        return list;
    }

}
