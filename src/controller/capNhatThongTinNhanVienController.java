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
import view.capNhatThongTinViewNhanVien;
import view.errView;
import view.loginView;
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
			String email = "";
			nhanVien nhanVien = null;
			phongBan phongBan = null;
			nhanVien nhanVienNew = null;
			try {

				email = this.capNhatThongTinViewNhanVien.getEmailTF().getText();
				nhanVien = nhanVienDAO.getInstance().selectByEmail(email).get(0);
				phongBan = phongBanDAO.getInstance()
						.selectByID(new phongBan(
								Integer.parseInt(
										(String) this.capNhatThongTinViewNhanVien.getMaPBComboBox().getSelectedItem()),
								"", null, 0, null));
				nhanVienNew = new nhanVien(0, this.capNhatThongTinViewNhanVien.getTenNVTF().getText(),
						String.valueOf(this.capNhatThongTinViewNhanVien.getGioiTinhComboBox().getSelectedItem()),
						Date.valueOf(this.capNhatThongTinViewNhanVien.getNgSinhTF().getText()),
						this.capNhatThongTinViewNhanVien.getSdtTF().getText(),
						this.capNhatThongTinViewNhanVien.getEmailTF().getText(),
						this.capNhatThongTinViewNhanVien.getDiaChiTF().getText(),
						this.capNhatThongTinViewNhanVien.getCccdTF().getText(),
						String.valueOf(this.capNhatThongTinViewNhanVien.getCapBacComboBox().getSelectedItem()),
						phongBan);

			} catch (Exception e2) {
				// TODO: handle exception

			}
			try {
				nhanVienDAO.getInstance().updateT(nhanVienNew, nhanVien.getMaNV());
				errView errView = new errView();
				errView.getLblNewLabel().setText("Cập nhật thành công, Vui lòng đăng nhập lại");
				this.capNhatThongTinViewNhanVien.dispose();
				new loginView();

				errView.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

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

//	public void changeData(nhanVien nv) {
//
//	}
}
