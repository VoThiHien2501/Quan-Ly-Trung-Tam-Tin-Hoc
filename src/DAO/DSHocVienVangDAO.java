/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import MODEL.DShocvienvang;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author ASUS
 */
public class DSHocVienVangDAO {
     public void insertHocVienVang(String maLop, String maHV, Date ngayDiemDanh, String ghiChu) throws Exception {
        String sql = "INSERT INTO DSHocVienVang (MaLop, MaHV, NgayDiemDanh, GhiChu) VALUES (?, ?, ?, ?)";
        try (Connection conn = KetNoiCSDL.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maLop);
            stmt.setString(2, maHV);
            stmt.setDate(3, new java.sql.Date(ngayDiemDanh.getTime()));
            stmt.setString(4, ghiChu);
            stmt.executeUpdate();
        }
    }

    public List<DShocvienvang> getDanhSachVangByMaLop(String maLop) throws Exception {
        List<DShocvienvang> list = new ArrayList<>();
        String sql = "SELECT * FROM DSHocVienVang WHERE MaLop = ?";
        try (Connection conn = KetNoiCSDL.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maLop);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DShocvienvang hvv = new DShocvienvang(
                    rs.getString("MaLop"),
                    rs.getString("MaHV"),
                    rs.getDate("NgayDiemDanh"),
                    rs.getString("GhiChu")
                );
                list.add(hvv);
            }
        }
        return list;
    }
}
