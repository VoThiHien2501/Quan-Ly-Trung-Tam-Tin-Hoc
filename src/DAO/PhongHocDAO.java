/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import MODEL.PhongHoc;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author ASUS
 */
public class PhongHocDAO {
    public List<String> getAllMaPhong() throws Exception {
        List<String> list = new ArrayList<>();
        String sql = "SELECT MaPhong FROM PhongHoc";
        try (Connection conn = KetNoiCSDL.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("MaPhong"));
            }
        }
        return list;
    }
}
