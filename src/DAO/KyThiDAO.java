/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import MODEL.KyThi;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author ASUS
 */
public class KyThiDAO {
    public List<String> getAllMaKyThi() throws Exception {
        List<String> list = new ArrayList<>();
        String sql = "SELECT MaKyThi FROM KyThi";
        try (Connection conn = KetNoiCSDL.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("MaKyThi"));
            }
        }
        return list;
    }
     // Thêm kỳ thi mới
    public boolean themKyThi(KyThi kyThi) throws Exception {
        String sql = "INSERT INTO KyThi (MaKyThi, TenKyThi, NgayBatDau, NgayKetThuc, HinhThucThi, GhiChu, MaKH, MaNV) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = KetNoiCSDL.openConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, kyThi.getMaKyThi());
            stmt.setString(2, kyThi.getTenKyThi());
            stmt.setDate(3, new java.sql.Date(kyThi.getNgayBatDau().getTime()));
            stmt.setDate(4, new java.sql.Date(kyThi.getNgayKetThuc().getTime()));
            stmt.setString(5, kyThi.getHinhThucThi());
            stmt.setString(6, kyThi.getGhiChu());
            stmt.setString(7, kyThi.getMaKH());
            stmt.setString(8, kyThi.getMaNV());

            return stmt.executeUpdate() > 0;
        }
    }

    // Cập nhật kỳ thi
    public boolean capNhatKyThi(KyThi kyThi) throws Exception {
        String sql = "UPDATE KyThi SET TenKyThi = ?, NgayBatDau = ?, NgayKetThuc = ?, "
                   + "HinhThucThi = ?, GhiChu = ?, MaKH = ?, MaNV = ? WHERE MaKyThi = ?";

        try (Connection con = KetNoiCSDL.openConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, kyThi.getTenKyThi());
            stmt.setDate(2, new java.sql.Date(kyThi.getNgayBatDau().getTime()));
            stmt.setDate(3, new java.sql.Date(kyThi.getNgayKetThuc().getTime()));
            stmt.setString(4, kyThi.getHinhThucThi());
            stmt.setString(5, kyThi.getGhiChu());
            stmt.setString(6, kyThi.getMaKH());
            stmt.setString(7, kyThi.getMaNV());
            stmt.setString(8, kyThi.getMaKyThi());

            return stmt.executeUpdate() > 0;
        }
    }
    
    public List<KyThi> getAllKyThi2() throws Exception {
    List<KyThi> list = new ArrayList<>();
    String sql = "SELECT * FROM KyThi";
    try (Connection conn = KetNoiCSDL.openConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            KyThi kt = new KyThi(
                rs.getString("MaKyThi"),
                rs.getString("TenKyThi"),
                rs.getDate("NgayBatDau"),
                rs.getDate("NgayKetThuc"),
                rs.getString("HinhThucThi"),
                rs.getString("GhiChu"),
                rs.getString("MaKH"),
                rs.getString("MaNV")
            );
            list.add(kt);
        }
    }
    return list;
}

    // Xóa kỳ thi
    public boolean xoaKyThi(String maKyThi) throws Exception {
        String sql = "DELETE FROM KyThi WHERE MaKyThi = ?";

        try (Connection con = KetNoiCSDL.openConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maKyThi);
            return stmt.executeUpdate() > 0;
        }
    }

    // Lấy danh sách tất cả kỳ thi
    public List<KyThi> layDanhSachKyThi() throws Exception {
        List<KyThi> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM KyThi";

        try (Connection con = KetNoiCSDL.openConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                KyThi kt = new KyThi();
                kt.setMaKyThi(rs.getString("MaKyThi"));
                kt.setTenKyThi(rs.getString("TenKyThi"));
                kt.setNgayBatDau(rs.getDate("NgayBatDau"));
                kt.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                kt.setHinhThucThi(rs.getString("HinhThucThi"));
                kt.setGhiChu(rs.getString("GhiChu"));
                kt.setMaKH(rs.getString("MaKH"));
                kt.setMaNV(rs.getString("MaNV"));
                danhSach.add(kt);
            }
        }

        return danhSach;
    }

    // Tìm kỳ thi theo mã
    public KyThi timKyThiTheoMa(String maKyThi) throws Exception {
        String sql = "SELECT * FROM KyThi WHERE MaKyThi = ?";

        try (Connection con = KetNoiCSDL.openConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maKyThi);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    KyThi kt = new KyThi();
                    kt.setMaKyThi(rs.getString("MaKyThi"));
                    kt.setTenKyThi(rs.getString("TenKyThi"));
                    kt.setNgayBatDau(rs.getDate("NgayBatDau"));
                    kt.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                    kt.setHinhThucThi(rs.getString("HinhThucThi"));
                    kt.setGhiChu(rs.getString("GhiChu"));
                    kt.setMaKH(rs.getString("MaKH"));
                    kt.setMaNV(rs.getString("MaNV"));
                    return kt;
                }
            }
        }

        return null;
    }
    
}
