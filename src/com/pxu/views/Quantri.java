/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pxu.views;

import com.phuxuan.quanlyktx.connectJDBC.Databaseee;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chinh
 */
public class Quantri extends javax.swing.JFrame implements MouseListener {

    /**
     * Creates new form Quantri
     */
    public Quantri() {
        initComponents();
        taophong();
//        chuotphai();

    }

    private void taophong() {
        try {
            Connection connection = Databaseee.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from phong";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                createRoomPanel(resultSet);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        pack();
        setLocationRelativeTo(null);
    }

    // Modify the createRoomPanel method
    private void createRoomPanel(ResultSet resultSet) {
        try {
            JPanel panel = createBasicRoomPanel(resultSet);
            setRoomDetails(resultSet, panel);

            // Add the context menu for each room panel
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        // Replace this comment with the code to show the context menu
                        JPopupMenu popup = createContextMenu();
                        popup.show(panel, e.getX(), e.getY());
                    }
                }
            });
            // Add the panel to the main panel
            addRoomToMainPanel(panel);
        } catch (SQLException ex) {
            Logger.getLogger(Quantri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Add this method to create the context menu
    private JPopupMenu createContextMenu() {
        JPopupMenu popup = new JPopupMenu();

//         Replace these comments with your actual menu items
        JMenuItem XEMHDD = new JMenuItem("Xem chi tiết thuê phòng");
        XEMHDD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    jFrame2.setVisible(true);
                    laysinhvien();
                } catch (SQLException ex) {
                    Logger.getLogger(Quantri.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Quantri.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        popup.add(XEMHDD);
        JMenuItem themp = new JMenuItem("Thuê phòng");
        themp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                capnhatsonguoiphongmoi();
                reloadRoomStatus();
            }
        });
        popup.add(themp);
        JMenuItem xoaphong = new JMenuItem("Tính tiền điện, nước");
        xoaphong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle Tính tiền điện, nước
            }
        });
        popup.add(xoaphong);
        return popup;
    }

    private JPanel createBasicRoomPanel(ResultSet resultSet) throws SQLException {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 5, 3));
        panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        int numberOfGuests = resultSet.getInt("songuoi");
        Color backgroundColor = (numberOfGuests > 0) ? new Color(235, 33, 35) : new Color(34, 139, 34);
        panel.setBackground(backgroundColor);

        return panel;
    }

    private void capnhatsonguoiphongmoi() {
        try {
            String sqlsqlc = "select * from phong where maphong=N'" + txtmp.getText() + "'";
            Connection conn = Databaseee.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlsqlc);
            if (rs.next()) {
                String sqlUpdate = "UPDATE phong SET songuoi=" + (rs.getInt("songuoi") + 1) + " where  maphong=N'" + txtmp.getText() + "'";
                stmt.executeUpdate(sqlUpdate);
            }
//            JOptionPane.showMessageDialog(rootPane, "Thuê phòng thành công !!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setRoomDetails(ResultSet resultSet, JPanel panel) {
        try {
            String maphong = resultSet.getString("maphong");
            int songuoi = resultSet.getInt("songuoi");
            int songiuong = resultSet.getInt("sogiuong");
            double giatien = resultSet.getInt("giatien");
            String loaiphong = resultSet.getString("loaiphong");
            JLabel label1 = createLabel(maphong);
            JLabel label2 = createLabel("Số người: " + String.valueOf(songuoi));
            JLabel label3 = createLabel("Loại phòng: " + String.valueOf(loaiphong));
            JLabel label4 = createLabel("Số giường: " + String.valueOf(songiuong));
            panel.addMouseListener(this);

            panel.add(label1);
            panel.add(label2);
            panel.add(label3);
            panel.add(label4);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("", Font.BOLD, 13));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.CENTER);

        return label;
    }

    private void addRoomToMainPanel(JPanel roomPanel) {
        jPanel2.add(roomPanel);

    }

    private void reloadRoomStatus() {
        jPanel2.removeAll(); // Xóa các phòng hiện tại trên giao diện
        try {
            Connection connection = Databaseee.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM phong";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                createRoomPanel(resultSet);
            }

            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        jPanel2.revalidate(); // Cập nhật giao diện sau khi thêm lại các phòng
        jPanel2.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame2 = new javax.swing.JFrame();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        scrollPane1 = new java.awt.ScrollPane();
        jPanel2 = new java.awt.Panel();
        txtmp = new javax.swing.JLabel();

        jFrame2.setTitle("Chi tiết sinh viên thuê phòng");
        jFrame2.setLocation(new java.awt.Point(400, 250));
        jFrame2.setMinimumSize(new java.awt.Dimension(800, 380));

        jPanel11.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel11.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jFrame2.getContentPane().add(jPanel11, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new java.awt.GridLayout(7, 5, 4, 4));
        scrollPane1.add(jPanel2);

        txtmp.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(541, Short.MAX_VALUE)
                .addComponent(txtmp, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txtmp)
                .addContainerGap(552, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(71, Short.MAX_VALUE)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
//        try {
//            int row = jTable1.getSelectedRow();
//            if (row >= 0) {
//                String maSV = (String) jTable1.getValueAt(row, 0);
//                Sinhviendao dao = new Sinhviendao();
//                Sinhvienmodel p = dao.FindManv(maSV);
//                if (dao != null) {
//                    txtmasv1.setText(p.getMasv());
//                    txtmasv2.setText(p.getMasv());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(rootPane, "looix");
//        }
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(Quantri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quantri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quantri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quantri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Quantri().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame jFrame2;
    private javax.swing.JPanel jPanel11;
    private java.awt.Panel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JLabel txtmp;
    // End of variables declaration//GEN-END:variables
 private void laysinhvien() throws SQLException, ClassNotFoundException {
        Connection conn = Databaseee.getConnection();
        try {
            String sqll = "select * from SINH_VIEN where trangthai=N'ĐANG THUÊ' and maphong =N'" + txtmp.getText() + "'";
            String[] aray = {"Mã sinh viên", "Họ tên sinh viên", "Mã phòng", "Ngày vào", "Số lần vi phạm", "Trạng thái"};
            DefaultTableModel model = new DefaultTableModel(aray, 0);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqll);
            while (rs.next()) {
                Vector vector = new Vector();
                vector.add(rs.getString("maSV"));
                vector.add(rs.getString("hoteSV"));
                vector.add(rs.getString("maphong"));
                vector.add(rs.getDate("ngayvao"));
                vector.add(rs.getInt("solanvipham"));
                vector.add(rs.getString("trangthai"));
                model.addRow(vector);
            }
            jTable1.setModel(model);
        } catch (SQLException e) {
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            // Get the clicked JPanel
            JPanel clickedPanel = (JPanel) e.getSource();

            // Get the first JLabel within the clicked panel
            JLabel roomNumberLabel = (JLabel) clickedPanel.getComponent(0);

            // Extract the room number from the label
            String roomNumber = roomNumberLabel.getText();

            // Use the room number as needed
            // For example, you can display it in a text field
            txtmp.setText(roomNumber);

            // Call the method to fetch additional information (laychisocu method)
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
