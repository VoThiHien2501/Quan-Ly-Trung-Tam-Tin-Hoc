/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import MODEL.CaHoc;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author ASUS
 */
public class CaHocDAO {
     public List<CaHoc> getAllCaHoc() throws Exception {
        List<CaHoc> list = new ArrayList<>();
        String sql = "SELECT * FROM CaHoc";

        try (Connection conn = KetNoiCSDL.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CaHoc ch = new CaHoc(
                    rs.getString("MaCaHoc"),
                    rs.getString("TenCaHoc"),
                    rs.getTime("TgianBatDau"),
                    rs.getTime("TgianKetThuc")
                );
                list.add(ch);
            }
        }

        return list;
    }
     
     public List<CaHoc> getCaHocTheoGiaoVien(String maGV) throws Exception {
    List<CaHoc> list = new ArrayList<>();
    String sql = "SELECT DISTINCT ch.MaCaHoc, ch.TenCaHoc, ch.TgianBatDau, ch.TgianKetThuc " +
                 "FROM CaHoc ch " +
                 "JOIN Lop l ON ch.MaCaHoc = l.MaCaHoc " +
                 "WHERE l.MaGV = ?";

    try (Connection conn = KetNoiCSDL.openConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, maGV);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            CaHoc caHoc = new CaHoc(
                rs.getString("MaCaHoc"),
                rs.getString("TenCaHoc"),
                rs.getTime("TgianBatDau"),
                rs.getTime("TgianKetThuc")
            );
            list.add(caHoc);
        }
    }
    return list;
}

}
