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
import view.errView;
import view.mainView;
import view.themNhanVienView;

public class themNhanVienController implements ActionListener, KeyListener {
	private themNhanVienView themNhanVienView;

	public themNhanVienController(themNhanVienView themNhanVienView) {
		this.themNhanVienView = themNhanVienView;
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
		if (e.getActionCommand().equals("Hủy")) {
			mainView mainView = new mainView();
			mainView.setTabNhanVien();
			this.themNhanVienView.dispose();
			mainView.setVisible(true);
		} else if (e.getActionCommand().equals("Thêm")) {
			try {
				int maNV = nhanVienDAO.getInstance().seq_num() + 1;
				String hoTen = this.themNhanVienView.getTenNVTF().getText();
				String gioiTinh = String.valueOf(this.themNhanVienView.getGioiTinhComboBox().getSelectedItem());
				Date ngSinh = Date.valueOf(this.themNhanVienView.getNgSinhTF().getText());
				String soDienThoai = this.themNhanVienView.getSdtTF().getText();
				String email = this.themNhanVienView.getSdtTF().getText();
				String diaChi = this.themNhanVienView.getDiaChiTF().getText();
				String cccd = this.themNhanVienView.getCccdTF().getText();
				String capBac = String.valueOf(this.themNhanVienView.getCapBacComboBox().getSelectedItem());
				phongBan phongBan = phongBanDAO.getInstance()
						.selectByID(new phongBan(
								Integer.parseInt(
										String.valueOf(this.themNhanVienView.getMaPBComboBox().getSelectedItem())),
								"", null, 0, null));

				nhanVien nhanVien = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, soDienThoai, email, diaChi, cccd,
						capBac, phongBan);
				mainView mainView = new mainView();
				mainView.setTabNhanVien();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Thêm thành công");
				this.themNhanVienView.dispose();
				mainView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				mainView mainView = new mainView();
				mainView.setTabNhanVien();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Thêm thất bại");
				this.themNhanVienView.dispose();
				mainView.setVisible(true);
			}
		}

	}

}
