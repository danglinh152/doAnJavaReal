package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import DAO.nhanVienDAO;
import model.nhanVien;
import view.capNhatThongTinViewNhanVien;
import view.errView;
import view.mainView;
import view.mainViewNhanVien;

public class capNhatThongTinNhanVienController implements ActionListener, KeyListener {
	private capNhatThongTinViewNhanVien capNhatThongTinViewNhanVien;

	public capNhatThongTinNhanVienController(capNhatThongTinViewNhanVien capNhatThongTinViewNhanVien) {
		this.capNhatThongTinViewNhanVien = capNhatThongTinViewNhanVien;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Hủy")) {
			mainViewNhanVien mainViewNhanVien = new mainViewNhanVien(this.capNhatThongTinViewNhanVien
					.getTaiKhoanViewNhanVien().getMainViewNhanVien().getTaiKhoanHienTai());
			mainViewNhanVien.setTabNhanVien();
			this.capNhatThongTinViewNhanVien.dispose();
			mainViewNhanVien.setVisible(true);
		} else if (e.getActionCommand().equals("Cập nhật")) {
			try {
				String email = this.capNhatThongTinViewNhanVien.getEmailTF().getText();
				nhanVien nhanVien = nhanVienDAO.getInstance().selectByEmail(email).get(0);
				nhanVienDAO.getInstance().updateT(nhanVien); // fix that, lấy textfield vào update lấy manv thôi

				mainViewNhanVien mainViewNhanVien = new mainViewNhanVien(this.capNhatThongTinViewNhanVien
						.getTaiKhoanViewNhanVien().getMainViewNhanVien().getTaiKhoanHienTai());
				mainViewNhanVien.setTabTaiKhoan();

				errView errView = new errView();
				errView.getLblNewLabel().setText("Cập nhật thành công");

				this.capNhatThongTinViewNhanVien.dispose();

				mainViewNhanVien.setVisible(true);
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				mainViewNhanVien mainViewNhanVien = new mainViewNhanVien(this.capNhatThongTinViewNhanVien
						.getTaiKhoanViewNhanVien().getMainViewNhanVien().getTaiKhoanHienTai());
				mainViewNhanVien.setTabTaiKhoan();

				errView errView = new errView();
				errView.getLblNewLabel().setText("Cập nhật thất bại");

				this.capNhatThongTinViewNhanVien.dispose();

				mainViewNhanVien.setVisible(true);
				errView.setVisible(true);
			}
		}
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

}
