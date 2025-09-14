/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import MODEL.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author ASUS
 */
public class TaiKhoanDAO {
   public TaiKhoan checkLogin(String maTK, String matKhau) throws Exception {
        String sql = "SELECT * FROM TaiKhoan WHERE MaTK = ? AND MatKhau = ?";
        try (Connection con = KetNoiCSDL.openConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, maTK);
            pstm.setString(2, matKhau);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    TaiKhoan tk = new TaiKhoan();
                    tk.setMaTK(rs.getString("MaTK"));
                    tk.setMatKhau(rs.getString("MatKhau"));
                    tk.setLoaiTaiKhoan(rs.getString("LoaiTaiKhoan"));
                    tk.setMaNV(rs.getString("MaNV"));  // thêm dòng này
                    tk.setMaGV(rs.getString("MaGV"));  // thêm nếu cần
                    return tk;
                }
            }
        }
        return null;
    }
}
