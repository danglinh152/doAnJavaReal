package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.spec.KeySpec;
import java.sql.Date;
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
import view.capNhatNhanVienView;
import view.capNhatTaiKhoanNhanVienView;
import view.errView;
import view.mainView;
import view.taiKhoanViewNhanVien;
import view.capNhatThongTinViewNhanVien;

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
		if (e.getActionCommand().equals("Đổi mật khẩu")) {

			try {

				taiKhoan taiKhoan = this.taiKhoanViewNhanVien.getMainViewNhanVien().getTaiKhoanHienTai();
				new capNhatTaiKhoanNhanVienView(taiKhoan);
				this.taiKhoanViewNhanVien.getMainViewNhanVien().dispose();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

	}

}
