package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.spec.KeySpec;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.hopDongDAO;
import DAO.nhanVienDAO;
import DAO.phongBanDAO;
import DAO.taiKhoanDAO;
import model.hopDong;
import model.nhanVien;
import model.phongBan;
import model.taiKhoan;
import view.errView;
import view.mainView;
import view.taiKhoanViewNhanVien;

public class taiKhoanNhanVienController implements ActionListener, KeyListener {
	private taiKhoanViewNhanVien taiKhoanViewNhanVien;

	public taiKhoanNhanVienController(taiKhoanViewNhanVien taiKhoanViewNhanVien) {
		this.taiKhoanViewNhanVien = taiKhoanViewNhanVien;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
