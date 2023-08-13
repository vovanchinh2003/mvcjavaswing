/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pxu.com.model;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ngoc
 */
public class TableUserModel extends DefaultTableModel {

    public TableUserModel() {
        super();
        this.addColumn("Mã Nhân Sự ");
        this.addColumn("Tên Nhân Sự");
        this.addColumn("Số Điện thoại ");
        this.addColumn("Địa Chỉ");
        this.addColumn("Tên tài khoản");
        this.addColumn("Vai Trò");
        this.addColumn("Mật Khẩu");
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            case 7:
                return String.class;
            default:
                return String.class;
        }
    }
}
