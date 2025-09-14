/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.KhoaHoc;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class KhoaHocDAO {

    public List<KhoaHoc> getAllKhoaHoc() throws Exception {
        List<KhoaHoc> list = new ArrayList<>();
        String sql = "SELECT MaKH, TenKH, NgayBatDau, NgayKetThuc, HocPhi, SoLgLop, SoBuoiHoc, MaNV FROM KhoaHoc";

        try (Connection con = KetNoiCSDL.openConnection(); PreparedStatement pstm = con.prepareStatement(sql); ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                KhoaHoc kh = new KhoaHoc();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setNgayBatDau(rs.getDate("NgayBatDau"));
                kh.setNgayKetThuc(rs.getDate("NgayKetThuc"));
            kh.setHocPhi(rs.getDouble("HocPhi"));
                kh.setSoLgLop(rs.getInt("SoLgLop"));
                kh.setSoBuoiHoc(rs.getInt("SoBuoiHoc"));
                kh.setMaNV(rs.getString("MaNV"));
                list.add(kh);
            }
        }
        return list;
    }
    
    public KhoaHoc findByMaKH(String maKH) throws Exception {
    String sql = "SELECT * FROM KhoaHoc WHERE MaKH = ?";
    try (Connection con = KetNoiCSDL.openConnection();
         PreparedStatement pstm = con.prepareStatement(sql)) {
        
        pstm.setString(1, maKH);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            KhoaHoc kh = new KhoaHoc();
            kh.setMaKH(rs.getString("MaKH"));
            kh.setTenKH(rs.getString("TenKH"));
            kh.setNgayBatDau(rs.getDate("NgayBatDau"));
            kh.setNgayKetThuc(rs.getDate("NgayKetThuc"));
            kh.setHocPhi(rs.getDouble("HocPhi"));
            kh.setSoLgLop(rs.getInt("SoLgLop"));
            kh.setSoBuoiHoc(rs.getInt("SoBuoiHoc"));
            kh.setMaNV(rs.getString("MaNV"));
            return kh;
        }
    }
    return null;
}

    public boolean themKhoaHoc(KhoaHoc kh) throws Exception {
        String sql = "INSERT INTO KhoaHoc (MaKH, TenKH, NgayBatDau, NgayKetThuc, HocPhi, SoLgLop, SoBuoiHoc, MaNV) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = KetNoiCSDL.openConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, kh.getMaKH());
            stmt.setString(2, kh.getTenKH());
            stmt.setDate(3, new java.sql.Date(kh.getNgayBatDau().getTime()));
            stmt.setDate(4, new java.sql.Date(kh.getNgayKetThuc().getTime()));
            stmt.setDouble(5, kh.getHocPhi());
            stmt.setInt(6, kh.getSoLgLop());
            stmt.setInt(7, kh.getSoBuoiHoc());
            stmt.setString(8, kh.getMaNV());
            return stmt.executeUpdate() > 0;
        }
    }
    
public boolean capNhatKhoaHoc(KhoaHoc kh) throws Exception {
    String sql = "UPDATE KhoaHoc SET TenKH = ?, NgayBatDau = ?, NgayKetThuc = ?, HocPhi = ?, SoLgLop = ?, SoBuoiHoc = ?, MaNV = ? "
               + "WHERE MaKH = ?";
    try (Connection con = KetNoiCSDL.openConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, kh.getTenKH());
        stmt.setDate(2, new java.sql.Date(kh.getNgayBatDau().getTime()));
        stmt.setDate(3, new java.sql.Date(kh.getNgayKetThuc().getTime()));
        stmt.setDouble(4, kh.getHocPhi());
        stmt.setInt(5, kh.getSoLgLop());
        stmt.setInt(6, kh.getSoBuoiHoc());
        stmt.setString(7, kh.getMaNV());
        stmt.setString(8, kh.getMaKH()); // WHERE MaKH = ?

        return stmt.executeUpdate() > 0;
    }
}

    public boolean xoaKhoaHoc(String maKH) throws Exception {
        String sql = "DELETE FROM KhoaHoc WHERE MaKH = ?";
        try (Connection con = KetNoiCSDL.openConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maKH);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean suaKhoaHoc(KhoaHoc kh) throws Exception {
        String sql = "UPDATE KhoaHoc SET TenKH = ?, NgayBatDau = ?, NgayKetThuc = ?, HocPhi = ?, SoLgLop = ?, SoBuoiHoc = ?, MaNV = ? "
                + "WHERE MaKH = ?";
        try (Connection con = KetNoiCSDL.openConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, kh.getTenKH());
            stmt.setDate(2, new java.sql.Date(kh.getNgayBatDau().getTime()));
            stmt.setDate(3, new java.sql.Date(kh.getNgayKetThuc().getTime()));
            stmt.setDouble(4, kh.getHocPhi());
            stmt.setInt(5, kh.getSoLgLop());
            stmt.setInt(6, kh.getSoBuoiHoc());
            stmt.setString(7, kh.getMaNV());
            stmt.setString(8, kh.getMaKH());
            return stmt.executeUpdate() > 0;
        }
    }

    // (Tùy chọn) Lấy danh sách tất cả khóa học
    public List<KhoaHoc> layDanhSachKhoaHoc() throws Exception {
        List<KhoaHoc> ds = new ArrayList<>();
        String sql = "SELECT * FROM KhoaHoc";
        try (Connection con = KetNoiCSDL.openConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                KhoaHoc kh = new KhoaHoc();
                kh.setMaKH(rs.getString("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setNgayBatDau(rs.getDate("NgayBatDau"));
                kh.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                kh.setHocPhi(rs.getDouble("HocPhi"));
                kh.setSoLgLop(rs.getInt("SoLgLop"));
                kh.setSoBuoiHoc(rs.getInt("SoBuoiHoc"));
                kh.setMaNV(rs.getString("MaNV"));
                ds.add(kh);
            }
        }
        return ds;
    }
}
