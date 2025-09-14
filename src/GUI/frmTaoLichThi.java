/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.KyThiDAO;
import DAO.LichThiDAO;
import MODEL.KyThi;
import MODEL.LichThi;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Time;
import java.util.Calendar;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author ASUS
 */
public class frmTaoLichThi extends javax.swing.JFrame {
    
    private LichThiDAO lichThiDAO;
    private KyThiDAO kyThiDAO;

    /**
     * Creates new form frmTaoLichThi
     */
    public frmTaoLichThi() {
        initComponents();
        setTitle("Tạo lịch thi");
        btnLuu.setEnabled(false);
        loadTableKyThi();
        lichThiDAO = new LichThiDAO();
        kyThiDAO = new KyThiDAO();
        setupSpinners();
        lblKyThi.setText("Chưa chọn");
        updateAvailableResources();
    }

    private void loadTableKyThi() {
        try {
            KyThiDAO dao = new KyThiDAO();
            List<KyThi> list = dao.getAllKyThi2();
            DefaultTableModel model = (DefaultTableModel) tblKyThi.getModel();
            model.setRowCount(0);
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có kỳ thi nào trong cơ sở dữ liệu!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                btnLuu.setEnabled(false);
            } else {
                for (KyThi kt : list) {
                    model.addRow(new Object[]{
                        kt.getMaKyThi(),
                        kt.getTenKyThi(),
                        kt.getNgayBatDau(),
                        kt.getNgayKetThuc(),
                        kt.getHinhThucThi(),
                        kt.getGhiChu(),
                        kt.getMaKH(),
                        kt.getMaNV()
                    });
                }
                btnLuu.setEnabled(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi load bảng Kỳ Thi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void loadLichThiTheoKyThi(String maKyThi) {
        try {
            LichThiDAO dao = new LichThiDAO();
            List<LichThi> list = dao.getLichThiByMaKyThi(maKyThi);

            DefaultTableModel model = (DefaultTableModel) tblLichThi.getModel();
            model.setRowCount(0); // Clear table

            for (LichThi lt : list) {
                model.addRow(new Object[]{
                    lt.getMaLichThi(),
                    lt.getNgayThi(),
                    lt.getTgianBatDau(),
                    lt.getTgianKetThuc(),
                    lt.getMaGV(),
                    lt.getMaPhong(),
                    lt.getMaKyThi()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load lịch thi: " + e.getMessage());
        }

    }
    
    // Cài đặt Spinner cho thời gian
    private void setupSpinners() {
        // Spinner cho thời gian bắt đầu (jSpinner1)
        SpinnerDateModel sm1 = new SpinnerDateModel();
        // Đặt mặc định là 8:00 AM
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.HOUR_OF_DAY, 8);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        sm1.setValue(cal1.getTime());
        jSpinner1.setModel(sm1);
        JSpinner.DateEditor de1 = new JSpinner.DateEditor(jSpinner1, "HH:mm");
        jSpinner1.setEditor(de1);

        // Spinner cho thời gian kết thúc (jSpinner2)
        SpinnerDateModel sm2 = new SpinnerDateModel();
        // Đặt mặc định là 10:00 AM
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.HOUR_OF_DAY, 10);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        sm2.setValue(cal2.getTime());
        jSpinner2.setModel(sm2);
        JSpinner.DateEditor de2 = new JSpinner.DateEditor(jSpinner2, "HH:mm");
        jSpinner2.setEditor(de2);
        
        // Thêm Listener để cập nhật combobox khi thời gian thay đổi
        jSpinner1.addChangeListener(e -> updateAvailableResources());
        jSpinner2.addChangeListener(e -> updateAvailableResources());
    }

    private void updateAvailableResources() {
        Date ngayThiUtil = jDateChooser1.getDate();

        // Kiểm tra xem ngày thi đã được chọn chưa
        if (ngayThiUtil == null) {
            cboMaGV.removeAllItems();
            cboMaGV.addItem("--Chọn Ngày Thi--");
            cboMaPT.removeAllItems();
            cboMaPT.addItem("--Chọn Ngày Thi--");
            return;
        }

        java.sql.Date sqlNgayThi = new java.sql.Date(ngayThiUtil.getTime());

        SpinnerDateModel model1 = (SpinnerDateModel) jSpinner1.getModel();
        Date time1 = model1.getDate();
        java.sql.Time tgianBatDau = new java.sql.Time(time1.getTime());

        SpinnerDateModel model2 = (SpinnerDateModel) jSpinner2.getModel();
        Date time2 = model2.getDate();
        java.sql.Time tgianKetThuc = new java.sql.Time(time2.getTime());

        // Kiểm tra logic thời gian kết thúc phải sau thời gian bắt đầu
        if (tgianKetThuc.before(tgianBatDau) || tgianKetThuc.equals(tgianBatDau)) {
            // Không cần JOptionPane ở đây để tránh làm phiền người dùng liên tục
            // khi họ đang điều chỉnh spinner. Chỉ cần xóa item và thoát.
            cboMaGV.removeAllItems();
            cboMaGV.addItem("--Thời gian không hợp lệ--");
            cboMaPT.removeAllItems();
            cboMaPT.addItem("--Thời gian không hợp lệ--");
            return;
        }

        // Load Giáo viên
        try {
            LichThiDAO dao = new LichThiDAO();
            List<String> listGV = dao.getGiaoVienRanh(sqlNgayThi, tgianBatDau, tgianKetThuc);

            cboMaGV.removeAllItems();
            cboMaGV.addItem("--Chọn Giáo viên--");
            for (String gv : listGV) {
                cboMaGV.addItem(gv);
            }
            if (listGV.isEmpty()) {
                cboMaGV.addItem("--Không có GV rảnh--");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load giáo viên rảnh: " + e.getMessage());
        }

        // Load Phòng thi
        try {
            LichThiDAO dao = new LichThiDAO();
            List<String> listPH = dao.getPhongHocRanh(sqlNgayThi, tgianBatDau, tgianKetThuc);

            cboMaPT.removeAllItems();
            cboMaPT.addItem("--Chọn Phòng thi--");
            for (String ph : listPH) {
                cboMaPT.addItem(ph);
            }
            if (listPH.isEmpty()) {
                cboMaPT.addItem("--Không có Phòng rảnh--");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load phòng học rảnh: " + e.getMessage());
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
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKyThi = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMaLichThi = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        cboMaGV = new javax.swing.JComboBox<>();
        cboMaPT = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLichThi = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnTaoMoi = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lblKyThi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Chọn Kỳ thi");

        tblKyThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã KT", "Tên KT", "Ngày bắt đầu", "Ngày kết thúc", "Hình thức thi", "ghi chú", "Mã KH", "Mã NV"
            }
        ));
        tblKyThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKyThiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKyThi);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Tạo lịch thi");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tạo lịch thi");

        jLabel4.setText("Mã lịch thi: ");

        jLabel5.setText("Ngày thi:");

        jLabel6.setText("Thời gian bắt đầu:");

        jLabel8.setText("Thời gian kết thúc:");

        jLabel9.setText("Mã giáo viên:");

        jLabel10.setText("Mã phòng thi:");

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        cboMaPT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSpinner2)
                                    .addComponent(jSpinner1)
                                    .addComponent(cboMaGV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboMaPT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(txtMaLichThi, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(45, 45, 45))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaLichThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(cboMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cboMaPT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        tblLichThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Lịch thi", "Ngày thi", "Tgian bắt đầu", "Tgain kết thúc", "Mã GV", "Mã phòng thi", "Mã Kỳ thi"
            }
        ));
        jScrollPane2.setViewportView(tblLichThi);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Danh sách lịch thi của kỳ thi:");

        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnTaoMoi.setText("Tạo mới");
        btnTaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnLuu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTaoMoi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblKyThi, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(39, 39, 39))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu)
                    .addComponent(btnTaoMoi)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblKyThi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblKyThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKyThiMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblKyThi.getSelectedRow();
        if (selectedRow >= 0) {
            String maKyThi = tblKyThi.getValueAt(selectedRow, 0).toString();
            String tenKyThi = tblKyThi.getValueAt(selectedRow, 1).toString();
            lblKyThi.setText(maKyThi + " - " + tenKyThi);
            btnLuu.setEnabled(true); // Kích hoạt nút Lưu
            loadLichThiTheoKyThi(maKyThi);
            updateAvailableResources();
            System.out.println("DEBUG: tblKyThiMouseClicked - lblKyThi sau khi set: " + lblKyThi.getText());
        }
    }//GEN-LAST:event_tblKyThiMouseClicked

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:
        if ("date".equals(evt.getPropertyName())) {
            updateAvailableResources();
        }
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        String maKyThi = null;
        try {
        System.out.println("DEBUG: btnLuuActionPerformed - Đã nhấn nút Lưu.");

        // 1. Lấy mã kỳ thi đã chọn
        String maKyThiFull = lblKyThi.getText();
        System.out.println("DEBUG: btnLuuActionPerformed - Giá trị hiện tại của lblKyThi: '" + maKyThiFull + "'");

        if (maKyThiFull == null || maKyThiFull.isEmpty() || maKyThiFull.equals("Chưa chọn") || !maKyThiFull.contains(" - ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một kỳ thi hợp lệ từ bảng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        maKyThi = maKyThiFull.split(" - ")[0];
        System.out.println("DEBUG: btnLuuActionPerformed - Giá trị maKyThi trước khi lưu: " + maKyThi);

        // Kiểm tra xem MaKyThi có tồn tại trong bảng KyThi
        KyThiDAO kyThiDAO = new KyThiDAO();
        KyThi kyThi = kyThiDAO.timKyThiTheoMa(maKyThi);
        if (kyThi == null) {
            JOptionPane.showMessageDialog(this, "Mã kỳ thi " + maKyThi + " không tồn tại trong cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Lấy ngày thi
        Date ngayThiUtil = jDateChooser1.getDate();
        if (ngayThiUtil == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày thi.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        java.sql.Date ngayThi = new java.sql.Date(ngayThiUtil.getTime());

        // 3. Lấy thời gian bắt đầu và kết thúc
        Date thoiGianBatDauUtil = (Date) jSpinner1.getValue();
        Date thoiGianKetThucUtil = (Date) jSpinner2.getValue();

        if (thoiGianBatDauUtil == null || thoiGianKetThucUtil == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thời gian bắt đầu và kết thúc.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        java.sql.Time tgianBatDau = new java.sql.Time(thoiGianBatDauUtil.getTime());
        java.sql.Time tgianKetThuc = new java.sql.Time(thoiGianKetThucUtil.getTime());

        // 4. Kiểm tra thời gian hợp lệ
        if (tgianBatDau.after(tgianKetThuc) || tgianBatDau.equals(tgianKetThuc)) {
            JOptionPane.showMessageDialog(this, "Thời gian kết thúc phải sau thời gian bắt đầu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 5. Lấy giáo viên coi thi
        String selectedGV = (String) cboMaGV.getSelectedItem();
        if (selectedGV == null || selectedGV.isEmpty() || selectedGV.equals("--Không có GV rảnh--") || selectedGV.equals("--Chọn Giáo viên--")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giáo viên coi thi hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String maGV = selectedGV.split(" - ")[0];

        // 6. Lấy phòng thi
        String selectedPT = (String) cboMaPT.getSelectedItem();
        if (selectedPT == null || selectedPT.isEmpty() || selectedPT.equals("--Không có Phòng rảnh--") || selectedPT.equals("--Chọn Phòng thi--")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng thi hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String maPT = selectedPT.split(" - ")[0];

        // 7. Tạo mã lịch thi mới
        String maLichThi = lichThiDAO.generateNewMaLichThi();

        // 8. Tạo đối tượng LichThi
        LichThi newLichThi = new LichThi(maLichThi, ngayThi, tgianBatDau, tgianKetThuc, maGV, maKyThi, maPT);

        // 9. Lưu lịch thi vào cơ sở dữ liệu
        if (lichThiDAO.themLichThi(newLichThi)) {
            JOptionPane.showMessageDialog(this, "Tạo lịch thi thành công!");
            loadLichThiTheoKyThi(maKyThi);
            btnTaoMoiActionPerformed(evt);
        } else {
            JOptionPane.showMessageDialog(this, "Tạo lịch thi thất bại. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception e) {
        String errorMessage = e.getMessage();
        if (errorMessage.contains("FOREIGN KEY constraint")) {
            errorMessage = "Mã kỳ thi " + maKyThi + " không tồn tại trong cơ sở dữ liệu. Vui lòng chọn kỳ thi hợp lệ.";
        }
        JOptionPane.showMessageDialog(this, "Lỗi khi tạo lịch thi: " + errorMessage, "Lỗi", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnTaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiActionPerformed
        // TODO add your handling code here:
        lblKyThi.setText("Chưa chọn");
        btnLuu.setEnabled(false); // Vô hiệu hóa nút Lưu
        jDateChooser1.setDate(new Date());
        
        // Đặt lại spinner về thời gian mặc định (ví dụ 8:00 và 10:00)
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.HOUR_OF_DAY, 8);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        jSpinner1.setValue(cal1.getTime());

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.HOUR_OF_DAY, 10);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        jSpinner2.setValue(cal2.getTime());

        cboMaGV.removeAllItems(); // Xóa các mục trong combobox
        cboMaPT.removeAllItems(); // Xóa các mục trong combobox
        
        txtMaLichThi.setText("");
        
        // Xóa dữ liệu trong bảng lịch thi
        DefaultTableModel modelLichThi = (DefaultTableModel) tblLichThi.getModel();
        modelLichThi.setRowCount(0);

        // Gọi lại updateAvailableResources để tải lại danh sách rảnh với ngày/giờ mặc định
        updateAvailableResources();
    }//GEN-LAST:event_btnTaoMoiActionPerformed

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
            java.util.logging.Logger.getLogger(frmTaoLichThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTaoLichThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTaoLichThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTaoLichThi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmTaoLichThi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnTaoMoi;
    private javax.swing.JComboBox<String> cboMaGV;
    private javax.swing.JComboBox<String> cboMaPT;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JLabel lblKyThi;
    private javax.swing.JTable tblKyThi;
    private javax.swing.JTable tblLichThi;
    private javax.swing.JTextField txtMaLichThi;
    // End of variables declaration//GEN-END:variables
}
