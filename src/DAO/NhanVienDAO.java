/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author ASUS
 */
public class NhanVienDAO {
public List<String> getAllMaNV() throws SQLException, Exception {
    List<String> list = new ArrayList<>();
    String sql = "SELECT MaNV FROM NhanVien";

    try (Connection con = KetNoiCSDL.openConnection();
         PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            list.add(rs.getString("MaNV"));
        }
    }

    return list;
}
}
