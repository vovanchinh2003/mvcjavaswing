/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pxu.views;

import Dialogchek.sheardatta;
import com.phuxuan.quanlyktx.connectJDBC.Databaseee;
import com.pxu.dao.CheckoutDao;
import com.pxu.dao.StudentDao;
import com.pxu.dao.RoomrentalDao;
import com.pxu.dao.RoomtransferDao;
import com.pxu.model.CheckoutModel;
import com.pxu.model.StudentModel;
import com.pxu.model.RoomrentalModel;
import com.pxu.model.RoomtransferModel;
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
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chinh
 */
public class Trangchu extends javax.swing.JFrame implements MouseListener {

    /**
     * Creates new form Trangchu
     */
    public Trangchu() throws SQLException {
        initComponents();
        taophong();
        pro();
        showTime();
        showdate();
        rightmouse();
        loadcombomaphong();
    }

    private void pro() {
        txtmand.setText(sheardatta.nguoiDangNhap.getFull_name());
        txtvaitro.setText(sheardatta.nguoiDangNhap.getPosition());
        if (sheardatta.nguoiDangNhap.getPosition().equals("Quản lý")) {

        } else if (sheardatta.nguoiDangNhap.getPosition().equals("Nhân viên")) {
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
            jButton7.setEnabled(false);
            jButton8.setEnabled(false);
        }
    }

    private void rightmouse() {
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem xoaphong = new JMenuItem("Chuyển phòng");
                    xoaphong.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jFrame3.setVisible(true);
                        }
                    });
                    popup.add(xoaphong);
                    JMenuItem XEMHDD = new JMenuItem("Trả phòng");
                    XEMHDD.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            jFrame4.setVisible(true);
                        }
                    });
                    popup.add(XEMHDD);
                    popup.show(jTable1, e.getX(), e.getY());
                }
            }
        });
    }

    private void showdate() {
        java.util.Date d = new java.util.Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String dat = s.format(d);
        lbtDate.setText(dat);

    }

    private void showTime() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.Date d = new java.util.Date();
                SimpleDateFormat s = new SimpleDateFormat("hh - mm - ss");
                String tim = s.format(d);
                lblTime.setText(tim);
            }
        }).start();
    }

    private void taophong() {
        try {
            Connection connection = Databaseee.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from room";
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
                    laysinhvien();
                    jFrame2.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        popup.add(XEMHDD);
        JMenuItem themp = new JMenuItem("Thuê phòng");
        themp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jFrame1.setVisible(true);
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
        panel.setLayout(new GridLayout(6, 1, 6, 3));
        panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        int numberOfGuests = resultSet.getInt("occupancy_count");
        Color backgroundColor = (numberOfGuests > 0) ? new Color(235, 33, 35) : new Color(34, 139, 34);
        panel.setBackground(backgroundColor);
        return panel;
    }

    private void setRoomDetails(ResultSet resultSet, JPanel panel) {
        try {
            String maphong = resultSet.getString("room_id");
            int songuoi = resultSet.getInt("occupancy_count");
            int songiuong = resultSet.getInt("bed_count");
            double giatien = resultSet.getInt("room_price");
            String loaiphong = resultSet.getString("room_type");
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
            String sql = "SELECT * FROM room";
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

    private void capnhatsonguoiphongmoi() {
        try {
            String sqlsqlc = "select * from room where room_id=N'" + txtmp.getText() + "'";
            Connection conn = Databaseee.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlsqlc);
            if (rs.next()) {
                String sqlUpdate = "UPDATE room SET occupancy_count=" + (rs.getInt("occupancy_count") + 1) + " where  room_id=N'" + txtmp.getText() + "'";
                stmt.executeUpdate(sqlUpdate);
            }
//            JOptionPane.showMessageDialog(rootPane, "Thuê phòng thành công !!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadcombomaphong() throws SQLException {
        String sql = "select room_id from room";
        Connection conn = Databaseee.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            combomap.addItem(rs.getString(1));
        }
        rs.close();
        conn.close();
        stmt.close();
    }

    private boolean chekngayvao() {
        try {
            String sqlsqlc = "select * from student where student_id=N'" + txtmasv1.getText() + "'";
            Connection conn = Databaseee.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlsqlc);
            while (rs.next()) {
                if (datengaychuyen.getDate().after(rs.getDate("check_in_date"))) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Ngày chuyển không được nhỏ hơn ngày vào !!!");
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    private boolean checksonguoiophongmoi() {
        try {
            String sqlsqlc = "select * from room where room_id=N'" + combomap.getSelectedItem() + "'";
            Connection conn = Databaseee.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlsqlc);
            while (rs.next()) {
                if (rs.getInt("occupancy_count") < rs.getInt("bed_count")) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Phòng đã đủ người !!!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void capnhatsonguoiphongcu() {
        try {
            String sqlsqlc = "select * from room where room_id=N'" + txtmp.getText() + "'";
            Connection conn = Databaseee.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlsqlc);
            if (rs.next()) {
                String sqlUpdate = "UPDATE room SET occupancy_count=" + (rs.getInt("occupancy_count") - 1) + " where  room_id=N'" + txtmp.getText() + "'";
                stmt.executeUpdate(sqlUpdate);
            }
//            JOptionPane.showMessageDialog(rootPane, "Trả phòng thành công !!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void capnhatsonguoiphongmoiii() {
        try {
            String sqlsqlc = "select * from room where room_id=N'" + combomap.getSelectedItem() + "'";
            Connection conn = Databaseee.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlsqlc);
            if (rs.next()) {
                String sqlUpdate = "UPDATE room SET occupancy_count=" + (rs.getInt("occupancy_count") + 1) + " where  room_id=N'" + combomap.getSelectedItem() + "'";
                stmt.executeUpdate(sqlUpdate);
            }
//            JOptionPane.showMessageDialog(rootPane, "Thuê phòng thành công !!!");
        } catch (Exception ex) {
            ex.printStackTrace();
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

        jFrame2 = new javax.swing.JFrame();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jFrame1 = new javax.swing.JFrame();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        txtmp = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtmasv = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txthoten = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        combokhoa = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        combonganh = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        datengaysinh = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        txtcmnd = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        comgioitinh = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        txtquequan = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        datengayvao = new com.toedter.calendar.JDateChooser();
        jButton8 = new javax.swing.JButton();
        jFrame3 = new javax.swing.JFrame();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtmasv1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        combomap = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        datengaychuyen = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lydo = new javax.swing.JTextPane();
        jButton9 = new javax.swing.JButton();
        jFrame4 = new javax.swing.JFrame();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtmasv2 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        datengaychuyen1 = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lydo1 = new javax.swing.JTextPane();
        jButton10 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        jButton7 = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        jPanel25 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtmand = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        lbtDate = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtvaitro = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        scrollPane1 = new java.awt.ScrollPane();
        jPanel2 = new java.awt.Panel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jFrame2.setTitle("Chi tiết sinh viên thuê phòng");
        jFrame2.setLocation(new java.awt.Point(200, 50));
        jFrame2.setMinimumSize(new java.awt.Dimension(1000, 580));

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

        jFrame1.setTitle("Thuê phòng");
        jFrame1.setLocation(new java.awt.Point(500, 100));
        jFrame1.setMinimumSize(new java.awt.Dimension(440, 545));

        jPanel9.setBackground(new java.awt.Color(204, 0, 204));
        jPanel9.setLayout(new java.awt.BorderLayout());

        txtmp.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtmp.setForeground(new java.awt.Color(255, 255, 255));
        txtmp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtmp.setText("0");
        jPanel9.add(txtmp, java.awt.BorderLayout.CENTER);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Thông tin sinh viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel10.setLayout(new java.awt.GridLayout(10, 0, 0, 10));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Mã sinh viên:");
        jPanel10.add(jLabel7);
        jPanel10.add(txtmasv);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Họ tên sinh viên:");
        jPanel10.add(jLabel8);
        jPanel10.add(txthoten);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Khoa:");
        jPanel10.add(jLabel9);

        combokhoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CN-KD" }));
        jPanel10.add(combokhoa);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Ngành:");
        jPanel10.add(jLabel10);

        combonganh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CNTT", "QT-KD" }));
        jPanel10.add(combonganh);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Ngày sinh:");
        jPanel10.add(jLabel11);
        jPanel10.add(datengaysinh);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Cmnd:");
        jPanel10.add(jLabel12);
        jPanel10.add(txtcmnd);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("SĐT:");
        jPanel10.add(jLabel13);
        jPanel10.add(txtsdt);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Gioi tính:");
        jPanel10.add(jLabel14);

        comgioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        jPanel10.add(comgioitinh);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Quê quán:");
        jPanel10.add(jLabel15);
        jPanel10.add(txtquequan);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Ngày vào:");
        jPanel10.add(jLabel16);
        jPanel10.add(datengayvao);

        jButton8.setBackground(new java.awt.Color(51, 255, 51));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Thuê phòng");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton8)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jFrame1.getContentPane().add(jPanel8, java.awt.BorderLayout.CENTER);

        jFrame3.setTitle("Chuyển phòng");
        jFrame3.setLocation(new java.awt.Point(550, 300));
        jFrame3.setMinimumSize(new java.awt.Dimension(400, 300));

        jPanel13.setLayout(new java.awt.GridLayout(3, 0, 0, 10));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Tên sinh viên:");
        jPanel13.add(jLabel17);
        jPanel13.add(txtmasv1);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Mã phòng:");
        jPanel13.add(jLabel18);

        jPanel13.add(combomap);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Ngày chuyển:");
        jPanel13.add(jLabel19);
        jPanel13.add(datengaychuyen);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Lý do:");

        jScrollPane2.setViewportView(lydo);

        jButton9.setBackground(new java.awt.Color(51, 255, 51));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Chuyển phòng");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addGap(32, 32, 32))
        );

        jFrame3.getContentPane().add(jPanel12, java.awt.BorderLayout.CENTER);

        jFrame4.setTitle("Trả phòng");
        jFrame4.setLocation(new java.awt.Point(550, 300));
        jFrame4.setMinimumSize(new java.awt.Dimension(400, 300));

        jPanel15.setLayout(new java.awt.GridLayout(3, 0, 0, 10));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Mã sinh viên:");
        jPanel15.add(jLabel21);
        jPanel15.add(txtmasv2);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Ngày trả:");
        jPanel15.add(jLabel23);
        jPanel15.add(datengaychuyen1);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Lý do:");

        jScrollPane3.setViewportView(lydo1);

        jButton10.setBackground(new java.awt.Color(51, 255, 51));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Trả Phòng");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton10)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addGap(32, 32, 32))
        );

        jFrame4.getContentPane().add(jPanel14, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Phòng");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);
        jToolBar1.add(jSeparator2);

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Sinh viên");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);
        jToolBar1.add(jSeparator3);

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setText("Kỷ luật");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);
        jToolBar1.add(jSeparator4);

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("Nhà xe");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);
        jToolBar1.add(jSeparator5);

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setText("Tài khoản");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton5);
        jToolBar1.add(jSeparator7);

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton6.setText("Hoá đơn");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton6);
        jToolBar1.add(jSeparator8);

        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton7.setText("Thống kê");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton7);
        jToolBar1.add(jSeparator9);

        jPanel25.setLayout(new java.awt.GridLayout(2, 0));
        jPanel25.add(jLabel1);
        jPanel25.add(jLabel5);
        jPanel25.add(jLabel3);

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel36.setText("Tên quản lý: ");
        jPanel25.add(jLabel36);

        txtmand.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtmand.setText("jLabel37");
        jPanel25.add(txtmand);

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("Ngày: ");
        jPanel25.add(jLabel40);

        lbtDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbtDate.setText("jLabel39");
        jPanel25.add(lbtDate);
        jPanel25.add(jLabel2);
        jPanel25.add(jLabel4);
        jPanel25.add(jLabel6);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("Vai trò: ");
        jPanel25.add(jLabel38);

        txtvaitro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtvaitro.setText("jLabel41");
        jPanel25.add(txtvaitro);

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText("Giờ: ");
        jPanel25.add(jLabel42);

        lblTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTime.setText("jLabel43");
        jPanel25.add(lblTime);

        jToolBar1.add(jPanel25);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new java.awt.GridLayout(7, 5, 4, 4));
        scrollPane1.add(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1526, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1526, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(591, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 77, Short.MAX_VALUE)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jMenu1.setText("Hệ thống");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SPACE, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Đổi mật khẩu");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Đăng xuất");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator6);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Thoát");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Phòng ktx");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
//        try {
//            Doimatkhau h = new Doimatkhau();
//            h.setVisible(true);
//        } catch (SQLException ex) {
//            Logger.getLogger(Hoatdongg.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Hoatdongg.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
//        Dangnhapp h = new Dangnhapp();
//        h.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            int row = jTable1.getSelectedRow();
            if (row >= 0) {
                String maSV = (String) jTable1.getValueAt(row, 0);
                StudentDao dao = new StudentDao();
                StudentModel p = dao.FindManv(maSV);
                if (dao != null) {
                    txtmasv1.setText(p.getStudent_id());
                    txtmasv2.setText(p.getStudent_id());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "looix");
        }
    }//GEN-LAST:event_jTable1MouseClicked
    private boolean chek() {
        try {
            String sqlsqlc = "select * from room where room_id=N'" + txtmp.getText() + "'";
            Connection conn = Databaseee.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlsqlc);
            while (rs.next()) {
                if (rs.getInt("occupancy_count") < rs.getInt("bed_count")) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Phòng đã đủ người !!!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (chek()) {
            try {
                StringBuilder sb = new StringBuilder();
                if (sb.length() > 0) {
                    JOptionPane.showMessageDialog(rootPane, sb);
                    return;
                }
                /// thêm sinh viên
                StudentModel s = new StudentModel();
                s.setStudent_id(txtmasv.getText());
                s.setStudent_name(txthoten.getText());
                s.setFaculty(combokhoa.getSelectedItem().toString());
                s.setMajor(combonganh.getSelectedItem().toString());
                SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
                String ngaysinh = date.format(datengaysinh.getDate());
                s.setBirth_date(Date.valueOf(ngaysinh));
                s.setId_card(txtcmnd.getText());
                s.setPhone_number(txtsdt.getText());
                s.setGender(comgioitinh.getSelectedItem().toString());
                s.setHometown(txtquequan.getText());
                s.setRoom_id(txtmp.getText());
                s.setViolation_count(0);
                String ngayvao = date.format(datengayvao.getDate());
                s.setCheck_in_date(Date.valueOf(ngayvao));
                s.setStatus("ĐANG THUÊ");
                StudentDao dao = new StudentDao();
                dao.insert(s);
                /// thêm vào hóa đơn thuê phòng
                String sql = "select * from user where full_name=N'" + txtmand.getText() + "'";
                Connection conn = Databaseee.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    RoomrentalModel t = new RoomrentalModel();
                    t.setStudent_id(txtmasv.getText());
                    t.setUser_id(rs.getString("user_id"));
                    t.setRoom_id(txtmp.getText());
                    String ngaythue = date.format(datengayvao.getDate());
                    t.setRental_date(Date.valueOf(ngaythue));
                    t.setStatus("ĐANG THUÊ");
                    RoomrentalDao daoo = new RoomrentalDao();
                    daoo.insert(t);
                    ///Cập nhật số người ở phòng mới
                    capnhatsonguoiphongmoi();
                    JOptionPane.showMessageDialog(rootPane, "Thuê Phòng thành công !!!");
                }
                reloadRoomStatus();
            } catch (SQLException ex) {
                Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
        }
        jFrame1.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (checksonguoiophongmoi()) {
            if (chekngayvao()) {
                try {
                    /// cập nhật số phòng của khách hàng đang ở hiện tại

                    StudentModel s = new StudentModel();
                    s.setStudent_id(txtmasv1.getText());
                    s.setRoom_id(combomap.getSelectedItem().toString());
                    StudentDao daoo = new StudentDao();
                    daoo.update(s);
                    //// cập nhật số người ở phòng cũ
                    capnhatsonguoiphongcu();
                    //// cập nhật số người ở phòng mới
                    capnhatsonguoiphongmoiii();
                    /// thêm vào hóa đơn chuyển phòng
                    String sql = "select * from user where full_name=N'" + txtmand.getText() + "'";
                    Connection conn = Databaseee.getConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        RoomtransferModel c = new RoomtransferModel();
                        c.setStudent_id(txtmasv1.getText());
                        c.setUser_id(rs.getString("user_id"));
                        c.setPrevious_room_id(txtmp.getText());
                        c.setRoom_id(combomap.getSelectedItem().toString());
                        SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
                        String ngaychuyen = date.format(datengaychuyen.getDate());
                        c.setTransfer_date(Date.valueOf(ngaychuyen));
                        c.setReason(lydo.getText());
                        RoomtransferDao dao = new RoomtransferDao();
                        dao.insert(c);
                        JOptionPane.showMessageDialog(rootPane, "Chuyển phòng thành công !!!");
                    }
                    reloadRoomStatus();
                } catch (SQLException ex) {
                    Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
        }
        jFrame3.dispose();
        jFrame2.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            /// cập nhật số phòng của khách hàng đang ở hiện tại
            StudentModel s = new StudentModel();
            s.setStudent_id(txtmasv1.getText());
            s.setStatus("ĐÃ TRẢ");
            StudentDao daoo = new StudentDao();
            daoo.updatetrangthai(s);
            //// cập nhật số người ở phòng cũ
            capnhatsonguoiphongcu();
            /// thêm vào hóa đơn trả phòng
            String sql = "select * from user where full_name=N'" + txtmand.getText() + "'";
            Connection conn = Databaseee.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CheckoutModel c = new CheckoutModel();
                c.setStudent_id(txtmasv2.getText());
                c.setUser_id(rs.getString("user_id"));
                c.setRoom_id(txtmp.getText());
                SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
                String ngaytra = date.format(datengaychuyen1.getDate());
                c.setCheckout_date(Date.valueOf(ngaytra));
                c.setReason(lydo1.getText());
                CheckoutDao dao = new CheckoutDao();
                dao.insert(c);
                JOptionPane.showMessageDialog(rootPane, "Trả phòng thành công !!!");
            }
            reloadRoomStatus();
        } catch (SQLException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
        jFrame4.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Trangchu().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combokhoa;
    private javax.swing.JComboBox<String> combomap;
    private javax.swing.JComboBox<String> combonganh;
    private javax.swing.JComboBox<String> comgioitinh;
    private com.toedter.calendar.JDateChooser datengaychuyen;
    private com.toedter.calendar.JDateChooser datengaychuyen1;
    private com.toedter.calendar.JDateChooser datengaysinh;
    private com.toedter.calendar.JDateChooser datengayvao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JFrame jFrame4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private java.awt.Panel jPanel2;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lbtDate;
    private javax.swing.JTextPane lydo;
    private javax.swing.JTextPane lydo1;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JTextField txtcmnd;
    private javax.swing.JTextField txthoten;
    private javax.swing.JLabel txtmand;
    private javax.swing.JTextField txtmasv;
    private javax.swing.JTextField txtmasv1;
    private javax.swing.JTextField txtmasv2;
    private javax.swing.JLabel txtmp;
    private javax.swing.JTextField txtquequan;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JLabel txtvaitro;
    // End of variables declaration//GEN-END:variables
 private void laysinhvien() throws SQLException, ClassNotFoundException {
        Connection conn = Databaseee.getConnection();
        try {
            String sqll = "select * from student where status=N'ĐANG THUÊ' and room_id =N'" + txtmp.getText() + "'";
            String[] aray = {"Mã sinh viên", "Họ tên sinh viên", "Mã phòng", "Ngày vào", "Số lần vi phạm", "Trạng thái"};
            DefaultTableModel model = new DefaultTableModel(aray, 0);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqll);
            while (rs.next()) {
                Vector vector = new Vector();
                vector.add(rs.getString("student_id"));
                vector.add(rs.getString("student_name"));
                vector.add(rs.getString("room_id"));
                vector.add(rs.getDate("check_in_date"));
                vector.add(rs.getInt("violation_count"));
                vector.add(rs.getString("status"));
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