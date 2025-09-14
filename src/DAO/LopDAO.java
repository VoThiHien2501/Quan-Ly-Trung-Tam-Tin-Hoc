/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Lop;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class LopDAO {

    public List<Lop> getLopByMaKH(String maKH) throws Exception {
        List<Lop> list = new ArrayList<>();
        String sql = "SELECT * FROM Lop WHERE MaKH = ?";

        try (Connection con = KetNoiCSDL.openConnection(); PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setString(1, maKH);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Lop lop = new Lop();
                lop.setMaLop(rs.getString("MaLop"));
                lop.setTenLop(rs.getString("TenLop"));
                lop.setSoLgHV(rs.getInt("SoLgHV"));
                lop.setMaCaHoc(rs.getString("MaCaHoc"));
                lop.setMaKH(rs.getString("MaKH"));
                lop.setMaGV(rs.getString("MaGV"));
                lop.setMaPH(rs.getString("MaPH"));
                list.add(lop);
            }
        }

        return list;
    }

    public List<Lop> getAllLop() throws Exception {
        List<Lop> list = new ArrayList<>();
        String sql = "SELECT * FROM Lop";
        try (Connection conn = KetNoiCSDL.openConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Lop lop = new Lop(
                        rs.getString("MaLop"),
                        rs.getString("TenLop"),
                        rs.getInt("SoLgHV"),
                        rs.getString("MaCaHoc"),
                        rs.getString("MaKH"),
                        rs.getString("MaGV"),
                        rs.getString("MaPH")
                );
                list.add(lop);
            }
        }
        return list;
    }

    public List<Lop> getLopByMaCaHoc(String maCaHoc) throws Exception {
        List<Lop> list = new ArrayList<>();
        String sql = "SELECT * FROM Lop WHERE MaCaHoc = ?";

        try (Connection conn = KetNoiCSDL.openConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maCaHoc);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Lop lop = new Lop(
                        rs.getString("MaLop"),
                        rs.getString("TenLop"),
                        rs.getInt("SoLgHV"),
                        rs.getString("MaCaHoc"),
                        rs.getString("MaKH"),
                        rs.getString("MaGV"),
                        rs.getString("MaPH")
                );
                list.add(lop);
            }
        }
        return list;
    }

}
