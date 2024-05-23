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
			System.out.println(this.capNhatThongTinViewNhanVien.getTaiKhoanViewNhanVien().getMainViewNhanVien()
					.getTaiKhoanHienTai().getNhanVien().getPhongBan().getMaPB());
			try {
				System.out
						.println(String.valueOf(this.capNhatThongTinViewNhanVien.getMaPBComboBox().getSelectedItem()));
				String email = this.capNhatThongTinViewNhanVien.getEmailTF().getText();
				nhanVien nhanVien = nhanVienDAO.getInstance().selectByEmail(email).get(0);
				phongBan phongBan = phongBanDAO.getInstance()
						.selectByID(new phongBan(
								Integer.parseInt(
										(String) this.capNhatThongTinViewNhanVien.getMaPBComboBox().getSelectedItem()),
								"", null, 0, null));
				nhanVien nhanVienNew = new nhanVien(0, this.capNhatThongTinViewNhanVien.getTenNVTF().getText(),
						String.valueOf(this.capNhatThongTinViewNhanVien.getGioiTinhComboBox().getSelectedItem()),
						Date.valueOf(this.capNhatThongTinViewNhanVien.getNgSinhTF().getText()),
						this.capNhatThongTinViewNhanVien.getSdtTF().getText(),
						this.capNhatThongTinViewNhanVien.getEmailTF().getText(),
						this.capNhatThongTinViewNhanVien.getDiaChiTF().getText(),
						this.capNhatThongTinViewNhanVien.getCccdTF().getText(),
						String.valueOf(this.capNhatThongTinViewNhanVien.getCapBacComboBox().getSelectedItem()),
						phongBan);
				nhanVienDAO.getInstance().updateT(nhanVienNew, nhanVien.getMaNV());

				errView errView = new errView();
				errView.getLblNewLabel().setText("Cập nhật thành công, Vui lòng đăng nhập lại");

				this.capNhatThongTinViewNhanVien.dispose();
				new loginView();

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

//	public void changeData(nhanVien nv) {
//
//	}
}
