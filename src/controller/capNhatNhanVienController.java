package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;

import DAO.nhanVienDAO;
import DAO.phongBanDAO;
import model.nhanVien;
import model.phongBan;
import view.capNhatNhanVienView;
import view.errView;
import view.loginView;
import view.mainView;
import view.mainViewNhanVien;

public class capNhatNhanVienController implements ActionListener, KeyListener {
	private capNhatNhanVienView capNhatNhanVienView;

	public capNhatNhanVienController(capNhatNhanVienView capNhatNhanVienView) {
		this.capNhatNhanVienView = capNhatNhanVienView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Hủy")) {
			mainView mainView = new mainView();
			mainView.setTabNhanVien();
			this.capNhatNhanVienView.dispose();
			mainView.setVisible(true);
		} else if (e.getActionCommand().equals("Cập nhật")) {

			try {
				System.out.println(String.valueOf(this.capNhatNhanVienView.getMaPBComboBox().getSelectedItem()));
				String email = this.capNhatNhanVienView.getEmailTF().getText();
				nhanVien nhanVien = nhanVienDAO.getInstance().selectByEmail(email).get(0);
				phongBan phongBan = phongBanDAO.getInstance()
						.selectByID(new phongBan(
								Integer.parseInt((String) this.capNhatNhanVienView.getMaPBComboBox().getSelectedItem()),
								"", null, 0, null));
				nhanVien nhanVienNew = new nhanVien(0, this.capNhatNhanVienView.getTenNVTF().getText(),
						String.valueOf(this.capNhatNhanVienView.getGioiTinhComboBox().getSelectedItem()),
						Date.valueOf(this.capNhatNhanVienView.getNgSinhTF().getText()),
						this.capNhatNhanVienView.getSdtTF().getText(), this.capNhatNhanVienView.getEmailTF().getText(),
						this.capNhatNhanVienView.getDiaChiTF().getText(),
						this.capNhatNhanVienView.getCccdTF().getText(),
						String.valueOf(this.capNhatNhanVienView.getCapBacComboBox().getSelectedItem()), phongBan);
				nhanVienDAO.getInstance().updateT(nhanVienNew, nhanVien.getMaNV());

				errView errView = new errView();
				errView.getLblNewLabel().setText("Cập nhật thành công, Vui lòng đăng nhập lại");

				this.capNhatNhanVienView.dispose();
				new loginView();
				errView.setVisible(true);

			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				mainView mainView = new mainView();
				mainView.setTabTaiKhoan();

				errView errView = new errView();
				errView.getLblNewLabel().setText("Cập nhật thất bại");

				this.capNhatNhanVienView.dispose();

				mainView.setVisible(true);
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
