/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pxu.com.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import pxu.com.dao.CrarentalDao;
import pxu.com.dialogchek.showuser;
import pxu.com.model.CrarentalModel;

/**
 *
 * @author chinh
 */
public class crarental extends javax.swing.JFrame {

    private ArrayList<CrarentalModel> quanlynhaxemodels;
    private DefaultTableModel tblModel;
    private TableRowSorter sorter;

    /**
     * Creates new form Quanlynhaxe
     */
    public crarental() throws SQLException, ClassNotFoundException {
        initComponents();
        initTable();
        LoadnhaxeTable();
        timkiem();
        chuotphaijtable();

    }

    private void chuotphaijtable() {
        tablenhaxe.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem capnhat = new JMenuItem("Cập nhật");
                    capnhat.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jFrame2.setVisible(true);
                        }
                    });
                    capnhat.setForeground(Color.ORANGE);
                    popup.add(capnhat);
                    JMenuItem xoa = new JMenuItem("Xoá");
                    xoa.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                if (JOptionPane.showConfirmDialog(rootPane, "Mời bạn chọn !") == JOptionPane.NO_OPTION) {
                                    return;
                                }
                                CrarentalModel x = new CrarentalModel();
                                x.setCar_id(Integer.parseInt(txtmaxe1.getText()));
                                CrarentalDao dao = new CrarentalDao();
                                dao.delete(x);
                                LoadnhaxeTable();
                                JOptionPane.showMessageDialog(rootPane, "Xoá thành công !!!");
                            } catch (SQLException ex) {
                                Logger.getLogger(crarental.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(crarental.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    xoa.setForeground(Color.RED);
                    popup.add(xoa);
                    popup.show(tablenhaxe, e.getX(), e.getY());
                }
            }
        });
    }

    private void initTable() {
        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(new String[]{"Mã xe", "Mã sinh viên", "Mã quản lý", "Loại xe", "BIển số xe", "Ngày gửi"});
        tablenhaxe.setModel(tblModel);
    }

    private void LoadnhaxeTable() throws SQLException, ClassNotFoundException {
        quanlynhaxemodels = CrarentalDao.getall();
        sorter = new TableRowSorter(tblModel);
        tablenhaxe.setRowSorter(sorter);
        tblModel.setRowCount(0);
        for (CrarentalModel m : quanlynhaxemodels) {
            Object[] object = new Object[]{m.getCar_id(), m.getStudent_id(), m.getUser_id(), m.getCar_type(), m.getLicense_plate(), m.getSubmission_date()};
            tblModel.addRow(object);
        }
        tblModel.fireTableDataChanged();
    }

    private void timkiem() {
        txtTimHoTen.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(txtTimHoTen.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(txtTimHoTen.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(txtTimHoTen.getText());
            }
        });
    }

    //Ham tim kiem theo chuoi str
    private void search(String str) {
        if (str.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(str));
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

        jFrame1 = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtmasv = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtloaixe = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtbienso = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        datengaygui = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jFrame2 = new javax.swing.JFrame();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtmaxe1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtmasv1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtloaixe1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtbienso1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        datengaygui1 = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtTimHoTen = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablenhaxe = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();

        jFrame1.setTitle("Thêm thông tin nhà xe");
        jFrame1.setLocation(new java.awt.Point(600, 200));
        jFrame1.setMinimumSize(new java.awt.Dimension(360, 435));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm thông tin nhà xe");
        jPanel4.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel5.setLayout(new java.awt.CardLayout(10, 35));

        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel6.setLayout(new java.awt.GridLayout(6, 10, 0, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Mã sinh viên:");
        jPanel6.add(jLabel3);
        jPanel6.add(txtmasv);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Loại xe:");
        jPanel6.add(jLabel5);
        jPanel6.add(txtloaixe);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Biển số xe:");
        jPanel6.add(jLabel6);
        jPanel6.add(txtbienso);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Ngày gửi:");
        jPanel6.add(jLabel7);
        jPanel6.add(datengaygui);
        jPanel6.add(jLabel8);

        jButton1.setBackground(new java.awt.Color(51, 255, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Xác nhận");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);

        jPanel5.add(jPanel6, "card2");

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jFrame1.getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        jFrame2.setTitle("Cập nhật thông tin nhà xe");
        jFrame2.setLocation(new java.awt.Point(600, 200));
        jFrame2.setMinimumSize(new java.awt.Dimension(360, 435));

        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel10.setDisplayedMnemonic('C');
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Cập nhật thông tin nhà xe");
        jPanel13.add(jLabel10, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel13, java.awt.BorderLayout.PAGE_START);

        jPanel14.setLayout(new java.awt.CardLayout(10, 35));

        jPanel15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel15.setLayout(new java.awt.GridLayout(7, 10, 0, 20));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Mã xe:");
        jPanel15.add(jLabel11);
        jPanel15.add(txtmaxe1);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Mã sinh viên:");
        jPanel15.add(jLabel12);
        jPanel15.add(txtmasv1);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Loại xe:");
        jPanel15.add(jLabel14);
        jPanel15.add(txtloaixe1);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Biển số xe:");
        jPanel15.add(jLabel15);
        jPanel15.add(txtbienso1);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Ngày gửi:");
        jPanel15.add(jLabel16);
        jPanel15.add(datengaygui1);
        jPanel15.add(jLabel17);

        jButton6.setBackground(new java.awt.Color(51, 255, 0));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Xác nhận");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton6);

        jPanel14.add(jPanel15, "card2");

        jPanel12.add(jPanel14, java.awt.BorderLayout.CENTER);

        jFrame2.getContentPane().add(jPanel12, java.awt.BorderLayout.CENTER);

        setTitle("Quản lý nhà xe");
        setMinimumSize(new java.awt.Dimension(710, 422));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.CardLayout(100, 15));

        jPanel7.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        jButton2.setBackground(new java.awt.Color(0, 255, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Thêm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton2);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Tìm kiếm:");
        jPanel7.add(jLabel18);
        jPanel7.add(txtTimHoTen);

        jPanel2.add(jPanel7, "card2");

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.CardLayout(10, 5));

        tablenhaxe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablenhaxe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablenhaxeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablenhaxe);

        jPanel9.add(jScrollPane1, "card2");

        jPanel8.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel10.setLayout(new java.awt.CardLayout(50, 10));

        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Quản ký nhà xe");
        jPanel11.add(jLabel19, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel11, "card2");

        jPanel1.add(jPanel10, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jFrame1.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            StringBuilder sb = new StringBuilder();
            if (sb.length() > 0) {
                JOptionPane.showMessageDialog(rootPane, sb);
                return;
            }
            CrarentalModel x = new CrarentalModel();
            x.setStudent_id(txtmasv.getText());
            x.setUser_id(showuser.nguoiDangNhap.getUser_id());
            x.setCar_type(txtloaixe.getText());
            x.setLicense_plate(txtbienso.getText());
            SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
            String ngaygui = date.format(datengaygui.getDate());
            x.setSubmission_date(Date.valueOf(ngaygui));
            CrarentalDao dao = new CrarentalDao();
            dao.insertin(x);
            LoadnhaxeTable();
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công !!!");
        } catch (SQLException ex) {
            Logger.getLogger(crarental.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(crarental.class.getName()).log(Level.SEVERE, null, ex);
        }
        jFrame1.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablenhaxeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablenhaxeMouseClicked
        try {
            int row = tablenhaxe.getSelectedRow();
            if (row >= 0) {
                int maxe = (int) tablenhaxe.getValueAt(row, 0);
                CrarentalDao dao = new CrarentalDao();
                CrarentalModel b = dao.FindManv(maxe);
                if (dao != null) {
                    txtmaxe1.setText(Integer.valueOf(b.getCar_id()).toString());
                    txtmasv1.setText(b.getStudent_id());
                    txtloaixe1.setText(b.getCar_type());
                    txtbienso1.setText(b.getLicense_plate());
                    datengaygui1.setDate(b.getSubmission_date());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "looix");
        }
    }//GEN-LAST:event_tablenhaxeMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            if (JOptionPane.showConfirmDialog(rootPane, "Mời bạn chọn !") == JOptionPane.NO_OPTION) {
                return;
            }
            CrarentalModel x = new CrarentalModel();
            x.setCar_id(Integer.valueOf(txtmaxe1.getText()));
            x.setStudent_id(txtmasv1.getText());
            x.setUser_id(showuser.nguoiDangNhap.getUser_id());
            x.setCar_type(txtloaixe1.getText());
            x.setLicense_plate(txtbienso1.getText());
            SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
            String ngaygui = date.format(datengaygui1.getDate());
            x.setSubmission_date(Date.valueOf(ngaygui));
            CrarentalDao dao = new CrarentalDao();
            dao.update(x);
            LoadnhaxeTable();
            JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công !!!");
        } catch (SQLException ex) {
            Logger.getLogger(crarental.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(crarental.class.getName()).log(Level.SEVERE, null, ex);
        }
        jFrame2.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(crarental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(crarental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(crarental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(crarental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new crarental().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(crarental.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(crarental.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser datengaygui;
    private com.toedter.calendar.JDateChooser datengaygui1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablenhaxe;
    private javax.swing.JTextField txtTimHoTen;
    private javax.swing.JTextField txtbienso;
    private javax.swing.JTextField txtbienso1;
    private javax.swing.JTextField txtloaixe;
    private javax.swing.JTextField txtloaixe1;
    private javax.swing.JTextField txtmasv;
    private javax.swing.JTextField txtmasv1;
    private javax.swing.JTextField txtmaxe1;
    // End of variables declaration//GEN-END:variables
}
