/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.CaHocDAO;
import DAO.DSHocVienVangDAO;
import DAO.HocVienDAO;
import DAO.LopDAO;
import MODEL.CaHoc;
import MODEL.DShocvienvang;
import MODEL.HocVien;
import MODEL.Lop;
import MODEL.TaiKhoan;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class frmDiemDanh extends javax.swing.JFrame {

private TaiKhoan taiKhoan;

    private String maLopDangChon;

    /**
     * Creates new form frmDiemDanh
     */
    public frmDiemDanh(TaiKhoan taiKhoan) {
        initComponents();
        setTitle("Điểm danh");
        this.taiKhoan = taiKhoan;
    if (taiKhoan != null) {
        if ("GV".equals(taiKhoan.getLoaiTaiKhoan())) {
            lblMaGV.setText("Mã GV: " + taiKhoan.getMaGV());
            loadCaHocLenTable(taiKhoan.getMaGV());
        } else {
            lblMaGV.setText("Mã NV: " + taiKhoan.getMaNV());
//            loadTatCaCaHoc(); 
        }
    } else {
        lblMaGV.setText("Chưa đăng nhập");
    }
//        loadTableCaHoc();
    }

    public frmDiemDanh() {
        this(null);
    }

    private void loadCaHocLenTable(String maGV) {
        try {
            CaHocDAO dao = new CaHocDAO();
            List<CaHoc> list = dao.getCaHocTheoGiaoVien(maGV);

            DefaultTableModel model = (DefaultTableModel) tblCaHoc.getModel();
            model.setRowCount(0); // Xoá dữ liệu cũ

            for (CaHoc ch : list) {
                model.addRow(new Object[]{
                    ch.getMaCaHoc(),
                    ch.getTenCaHoc(),
                    ch.getTgianBatDau(),
                    ch.getTgianKetThuc()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load ca học: " + e.getMessage());
        }
    }

//    private void loadTableCaHoc() {
//        try {
//            CaHocDAO dao = new CaHocDAO();
//            List<CaHoc> list = dao.getAllCaHoc();
//
//            DefaultTableModel model = (DefaultTableModel) tblCaHoc.getModel();
//            model.setRowCount(0); // Xoá dữ liệu cũ
//
//            for (CaHoc ch : list) {
//                model.addRow(new Object[]{
//                    ch.getMaCaHoc(),
//                    ch.getTenCaHoc(),
//                    ch.getTgianBatDau(),
//                    ch.getTgianKetThuc()
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Lỗi khi load dữ liệu ca học!");
//        }
//    }
    private void loadTableLop() {
        try {
            LopDAO dao = new LopDAO();
            List<Lop> list = dao.getAllLop();

            DefaultTableModel model = (DefaultTableModel) tblLop.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            for (Lop lop : list) {
                model.addRow(new Object[]{
                    lop.getMaLop(),
                    lop.getTenLop(),
                    lop.getSoLgHV(),
                    lop.getMaCaHoc(),
                    lop.getMaKH(),
                    lop.getMaGV(),
                    lop.getMaPH()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load danh sách lớp học: " + e.getMessage());
        }
    }

    private void loadLopTheoCaHoc(String maCaHoc) {
        try {
            LopDAO dao = new LopDAO();
            List<Lop> list = dao.getLopByMaCaHoc(maCaHoc);

            DefaultTableModel model = (DefaultTableModel) tblLop.getModel();
            model.setRowCount(0); // Xoá dữ cũ

            for (Lop lop : list) {
                model.addRow(new Object[]{
                    lop.getMaLop(),
                    lop.getTenLop(),
                    lop.getSoLgHV(),
                    lop.getMaCaHoc(),
                    lop.getMaKH(),
                    lop.getMaGV(),
                    lop.getMaPH()

                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load lớp theo ca học!");
        }
    }

    private void loadHocVienByLop(String maLop) {
        try {
            HocVienDAO dao = new HocVienDAO();
            List<HocVien> list = dao.getHocVienByMaLop(maLop);

            DefaultTableModel model = (DefaultTableModel) tblHocVien.getModel();
            model.setRowCount(0); // Xoá dữ liệu cũ

            for (HocVien hv : list) {
                model.addRow(new Object[]{
                    hv.getMaHV(),
                    hv.getHoTen(),
                    hv.getNgaySinh(),
                    hv.getGioiTinh(),
                    hv.getSdt(),
                    hv.getDiaChi(),
                    hv.getNgayDangKy(),
                    hv.getMaLop()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load học viên: " + e.getMessage());
        }
    }

    private void loadDanhSachVang(String maLop) {
        try {
            DSHocVienVangDAO dao = new DSHocVienVangDAO();
            List<DShocvienvang> list = dao.getDanhSachVangByMaLop(maLop);

            DefaultTableModel model = (DefaultTableModel) tblDSHocVienVang.getModel();
            model.setRowCount(0); // Xoá dữ cũ

            for (DShocvienvang hvv : list) {
                model.addRow(new Object[]{
                    hvv.getMaLop(), // thêm mã lớp vào đây
                    hvv.getMaHV(),
                    hvv.getNgayDiemDanh(),
                    hvv.getGhiChu()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load danh sách vắng!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLop = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHocVien = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        datechooserNgayDiemDanh = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaGhiChu = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDSHocVienVang = new javax.swing.JTable();
        btnThemSVvaoDSVang = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCaHoc = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        lblMaGV = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblLop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã lớp", "Tên lớp", "Số Lg HV", "Mã ca học", "Mã Khóa học", "Mã GV", "Mã Phòng học"
            }
        ));
        tblLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLopMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLop);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Chọn lớp");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("Điểm danh");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Danh sách học viên");

        tblHocVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã học viên", "Tên HV", "Ngày sinh", "Giới tính", "SDT", "Địa chỉ", "Ngày Đăng ký", "Mã lớp"
            }
        ));
        jScrollPane2.setViewportView(tblHocVien);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Danh sách học viên vắng");

        jLabel5.setText("Ngày điểm danh:");

        jLabel6.setText("Ghi chú:");

        txtAreaGhiChu.setColumns(20);
        txtAreaGhiChu.setRows(5);
        jScrollPane3.setViewportView(txtAreaGhiChu);

        tblDSHocVienVang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã lớp", "Mã học viên", "Ngày điểm danh", "Ghi chú"
            }
        ));
        jScrollPane4.setViewportView(tblDSHocVienVang);

        btnThemSVvaoDSVang.setText("Thêm học viên vào DS vắng");
        btnThemSVvaoDSVang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSVvaoDSVangActionPerformed(evt);
            }
        });

        jButton2.setText("Thoát");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnThemSVvaoDSVang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datechooserNgayDiemDanh, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addComponent(datechooserNgayDiemDanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSVvaoDSVang)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tblCaHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã ca học", "Tên ca học", "Thời gian bắt đầu", "Thời gian kết thúc"
            }
        ));
        tblCaHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCaHocMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblCaHoc);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Chọn ca học:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(23, 23, 23))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLopMouseClicked
        // TODO add your handling code here:
//        int selectedRow = tblLop.getSelectedRow();
//        if (selectedRow >= 0) {
//            String maLop = tblLop.getValueAt(selectedRow, 0).toString(); // cột 0 là MaLop
//            loadHocVienByLop(maLop);
//        }

        int selectedRow = tblLop.getSelectedRow();
        if (selectedRow >= 0) {
            maLopDangChon = tblLop.getValueAt(selectedRow, 0).toString(); // cột 0 là MaLop
            loadHocVienByLop(maLopDangChon); // load danh sách học viên theo lớp
            loadDanhSachVang(maLopDangChon); // load danh sách vắng nếu cần
        }
    }//GEN-LAST:event_tblLopMouseClicked

    private void btnThemSVvaoDSVangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSVvaoDSVangActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblHocVien.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn học viên!");
            return;
        }

        // Lấy mã học viên và mã lớp từ bảng (giả sử mã lớp nằm ở cột thứ 6 - chỉ số 6)
        String maHV = tblHocVien.getValueAt(selectedRow, 0).toString(); // cột Mã HV
        String maLop = tblHocVien.getValueAt(selectedRow, 6).toString(); // cột Mã Lớp

        // Lấy ngày điểm danh từ DateChooser
        Date ngayVang = datechooserNgayDiemDanh.getDate();
        if (ngayVang == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày vắng!");
            return;
        }

        // Chuyển đổi sang java.sql.Date để dùng trong DAO
        java.sql.Date sqlNgayVang = new java.sql.Date(ngayVang.getTime());

        // Lấy ghi chú
        String ghiChu = txtAreaGhiChu.getText().trim();

        // Gọi DAO để thêm vào danh sách học viên vắng
        try {
            DSHocVienVangDAO dao = new DSHocVienVangDAO();
            dao.insertHocVienVang(maLop, maHV, sqlNgayVang, ghiChu);

            JOptionPane.showMessageDialog(this, "Đã thêm vào danh sách vắng!");

            // Load lại danh sách vắng sau khi thêm
            loadDanhSachVang(maLop);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnThemSVvaoDSVangActionPerformed

    private void tblCaHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCaHocMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblCaHoc.getSelectedRow();
        if (selectedRow >= 0) {
            String maCaHoc = tblCaHoc.getValueAt(selectedRow, 0).toString(); // cột 0 là MaCaHoc
            loadLopTheoCaHoc(maCaHoc);
        }
    }//GEN-LAST:event_tblCaHocMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmDiemDanh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDiemDanh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDiemDanh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDiemDanh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDiemDanh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThemSVvaoDSVang;
    private com.toedter.calendar.JDateChooser datechooserNgayDiemDanh;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblMaGV;
    private javax.swing.JTable tblCaHoc;
    private javax.swing.JTable tblDSHocVienVang;
    private javax.swing.JTable tblHocVien;
    private javax.swing.JTable tblLop;
    private javax.swing.JTextArea txtAreaGhiChu;
    // End of variables declaration//GEN-END:variables
}
