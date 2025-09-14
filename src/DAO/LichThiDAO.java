/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import MODEL.LichThi;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author ASUS
 */
public class LichThiDAO {
    public boolean themLichThi(LichThi lt) throws Exception {
        String sql = "INSERT INTO LichThi (MaLichThi, NgayThi, TgianBatDau, TgianKetThuc, MaGV, MaKyThi, MaPhong) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = KetNoiCSDL.openConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, lt.getMaLichThi());
            stmt.setDate(2, new java.sql.Date(lt.getNgayThi().getTime()));
            stmt.setTime(3, lt.getTgianBatDau());
            stmt.setTime(4, lt.getTgianKetThuc());
            stmt.setString(5, lt.getMaGV());
            stmt.setString(6, lt.getMaKyThi());
            stmt.setString(7, lt.getMaPhong());
            return stmt.executeUpdate() > 0;
        }
    }

    // Cập nhật lịch thi
    public boolean capNhatLichThi(LichThi lt) throws Exception {
        String sql = "UPDATE LichThi SET NgayThi=?, TgianBatDau=?, TgianKetThuc=?, MaGV=?, MaKyThi=?, MaPhong=? "
                   + "WHERE MaLichThi=?";
        try (Connection con = KetNoiCSDL.openConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(lt.getNgayThi().getTime()));
            stmt.setTime(2, lt.getTgianBatDau());
            stmt.setTime(3, lt.getTgianKetThuc());
            stmt.setString(4, lt.getMaGV());
            stmt.setString(5, lt.getMaKyThi());
            stmt.setString(6, lt.getMaPhong());
            stmt.setString(7, lt.getMaLichThi());
            return stmt.executeUpdate() > 0;
        }
    }

    // Xóa lịch thi theo mã
    public boolean xoaLichThi(String maLichThi) throws Exception {
        String sql = "DELETE FROM LichThi WHERE MaLichThi=?";
        try (Connection con = KetNoiCSDL.openConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maLichThi);
            return stmt.executeUpdate() > 0;
        }
    }

    // Lấy danh sách tất cả lịch thi
    public List<LichThi> getAllLichThi() throws Exception {
        List<LichThi> list = new ArrayList<>();
        String sql = "SELECT * FROM LichThi";
        try (Connection con = KetNoiCSDL.openConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                LichThi lt = new LichThi();
                lt.setMaLichThi(rs.getString("MaLichThi"));
                lt.setNgayThi(rs.getDate("NgayThi"));
                lt.setTgianBatDau(rs.getTime("TgianBatDau"));
                lt.setTgianKetThuc(rs.getTime("TgianKetThuc"));
                lt.setMaGV(rs.getString("MaGV"));
                lt.setMaKyThi(rs.getString("MaKyThi"));
                lt.setMaPhong(rs.getString("MaPhong"));
                list.add(lt);
            }
        }
        return list;
    }

    // Lấy lịch thi theo mã
    public LichThi getLichThiById(String maLichThi) throws Exception {
        String sql = "SELECT * FROM LichThi WHERE MaLichThi=?";
        try (Connection con = KetNoiCSDL.openConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maLichThi);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LichThi lt = new LichThi();
                    lt.setMaLichThi(rs.getString("MaLichThi"));
                    lt.setNgayThi(rs.getDate("NgayThi"));
                    lt.setTgianBatDau(rs.getTime("TgianBatDau"));
                    lt.setTgianKetThuc(rs.getTime("TgianKetThuc"));
                    lt.setMaGV(rs.getString("MaGV"));
                    lt.setMaKyThi(rs.getString("MaKyThi"));
                    lt.setMaPhong(rs.getString("MaPhong"));
                    return lt;
                }
            }
        }
        return null;
    }
    
     public List<LichThi> getLichThiByMaKyThi(String maKyThi) throws Exception {
        List<LichThi> list = new ArrayList<>();
        String sql = "SELECT * FROM LichThi WHERE MaKyThi = ?";
        try (Connection conn = KetNoiCSDL.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKyThi);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LichThi lt = new LichThi(
                    rs.getString("MaLichThi"),
                    rs.getDate("NgayThi"),
                    rs.getTime("TgianBatDau"),
                    rs.getTime("TgianKetThuc"),
                    rs.getString("MaGV"),
                    rs.getString("MaKyThi"),
                    rs.getString("MaPhong")
                );
                list.add(lt);
            }
        }
        return list;
    }
    
     
          // Phương thức để lấy danh sách giáo viên rảnh vào một khoảng thời gian cụ thể
    public List<String> getGiaoVienRanh(java.sql.Date ngayThi, Time tgianBatDau, Time tgianKetThuc) throws Exception {
        List<String> danhSachGV = new ArrayList<>();
        // Cập nhật SQL để sử dụng CONVERT(TIME, ?) cho các tham số thời gian
        String sql = "SELECT DISTINCT GV.MaGV, GV.TenGV " +
                     "FROM GiaoVien GV " +
                     "WHERE GV.MaGV NOT IN ( " +
                     "    SELECT LT.MaGV " +
                     "    FROM LichThi LT " +
                     "    WHERE LT.NgayThi = ? " +
                     "    AND ( CONVERT(TIME, ?) < LT.TgianKetThuc AND CONVERT(TIME, ?) > LT.TgianBatDau ) " + // Điều kiện trùng lặp
                     ")";

        try (Connection con = KetNoiCSDL.openConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDate(1, ngayThi); // Tham số 1 cho NgayThi
            stmt.setTime(2, tgianBatDau); // Tham số 2 cho CONVERT(TIME, ?) đầu tiên (thời gian bắt đầu mới)
            stmt.setTime(3, tgianKetThuc);  // Tham số 3 cho CONVERT(TIME, ?) thứ hai (thời gian kết thúc mới)

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    danhSachGV.add(rs.getString("MaGV") + " - " + rs.getString("TenGV"));
                }
            }
        }
        return danhSachGV;
    }

    // Phương thức để lấy danh sách phòng học rảnh vào một khoảng thời gian cụ thể
    public List<String> getPhongHocRanh(java.sql.Date ngayThi, Time tgianBatDau, Time tgianKetThuc) throws Exception {
        List<String> danhSachPH = new ArrayList<>();
        // Cập nhật SQL để sử dụng CONVERT(TIME, ?) cho các tham số thời gian
        String sql = "SELECT DISTINCT PH.MaPhong, PH.TenPhong " +
                     "FROM PhongHoc PH " +
                     "WHERE PH.TinhTrang = N'Hoạt động' AND PH.MaPhong NOT IN ( " +
                     "    SELECT LT.MaPhong " +
                     "    FROM LichThi LT " +
                     "    WHERE LT.NgayThi = ? " +
                     "    AND ( CONVERT(TIME, ?) < LT.TgianKetThuc AND CONVERT(TIME, ?) > LT.TgianBatDau ) " + // Điều kiện trùng lặp
                     ")";

        try (Connection con = KetNoiCSDL.openConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDate(1, ngayThi); // Tham số 1 cho NgayThi
            stmt.setTime(2, tgianBatDau); // Tham số 2 cho CONVERT(TIME, ?) đầu tiên (thời gian bắt đầu mới)
            stmt.setTime(3, tgianKetThuc);  // Tham số 3 cho CONVERT(TIME, ?) thứ hai (thời gian kết thúc mới)

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    danhSachPH.add(rs.getString("MaPhong") + " - " + rs.getString("TenPhong"));
                }
            }
        }
        return danhSachPH;
    }
    
    public String generateNewMaLichThi() throws Exception {
        String maxId = null;
        String sql = "SELECT MAX(MaLichThi) FROM LichThi";
        try (Connection con = KetNoiCSDL.openConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                maxId = rs.getString(1);
            }
        }

        if (maxId == null) {
            return "LT001"; // Mã đầu tiên nếu chưa có lịch thi nào
        } else {
            // Tách phần số từ mã (ví dụ: "LT001" -> 1)
            int num = Integer.parseInt(maxId.substring(2)); 
            num++; // Tăng lên 1
            // Định dạng lại thành "LT" + số có 3 chữ số (ví dụ: 1 -> "001")
            return String.format("LT%03d", num); 
        }
    }
}
