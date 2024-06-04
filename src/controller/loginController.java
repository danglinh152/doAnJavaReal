package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import DAO.taiKhoanDAO;
import model.taiKhoan;
import view.mainView;
import view.mainViewNhanVien;
import view.errView;
import view.loginView;

public class loginController implements KeyListener, ActionListener {

	private loginView loginView;

	public loginController(loginView loginView) {
		this.loginView = loginView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Đăng nhập")) {
			String tenDangNhap = loginView.getTextField().getText();
			String matKhau = loginView.getTextField_1().getText();
			taiKhoan tk = new taiKhoan(0, null, tenDangNhap, matKhau, "");
			taiKhoan tkht = taiKhoanDAO.getInstance().selectByID(tk); // truy vấn tài khoản với tendangnhap và matkhau

			if (tkht != null) {
				if (tkht.getLoaiTK().equals("quản lý")) {
					loginView.dispose();
					new mainView(); // mainView quản lý
				} else if (tkht.getLoaiTK().equals("nhân viên")) {
					loginView.dispose();
					new mainViewNhanVien(tkht); // mainView nhân viên
				}
			} else {
				view.errView errView = new errView();
				errView.getLblNewLabel().setText("Tài khoản hoặc mật khẩu không đúng!");
				errView.setVisible(true);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) { // nhấn nút, nhấn nút enter trên bàn phím
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String tenDangNhap = loginView.getTextField().getText();
			String matKhau = loginView.getTextField_1().getText();
			taiKhoan tk = new taiKhoan(0, null, tenDangNhap, matKhau, "");
			taiKhoan tkht = taiKhoanDAO.getInstance().selectByID(tk);
			if (tkht != null) {
				if (tkht.getLoaiTK().equals("quản lý")) {

					loginView.dispose();
					new mainView();
				} else if (tkht.getLoaiTK().equals("nhân viên")) {

					loginView.dispose();
					new mainViewNhanVien(tkht);
				}
			} else {
				view.errView errView = new errView();
				errView.getLblNewLabel().setText("Tài khoản hoặc mật khẩu không đúng!");
				errView.setVisible(true);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
