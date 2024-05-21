package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import DAO.hopDongDAO;
import DAO.nhanVienDAO;
import DAO.taiKhoanDAO;
import model.hopDong;
import model.nhanVien;
import model.taiKhoan;
import view.errView;
import view.mainView;
import view.themTaiKhoanView;

public class themTaiKhoanController implements ActionListener {
	private themTaiKhoanView themTaiKhoanView;

	public themTaiKhoanController(themTaiKhoanView themTaiKhoanView) {
		this.themTaiKhoanView = themTaiKhoanView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Hủy")) {
			mainView mainView = new mainView();
			mainView.setTabTaiKhoan();
			this.themTaiKhoanView.dispose();
			mainView.setVisible(true);
		} else if (e.getActionCommand().equals("Thêm")) {
			try {
				int maTK = hopDongDAO.getInstance().seq_num() + 1;
				int maNV = Integer.parseInt(this.themTaiKhoanView.getMaNVTF().getText());
				nhanVien nhanVien = nhanVienDAO.getInstance()
						.selectByID(new nhanVien(maNV, "", "", null, "", "", "", "", "", null));
				String tenTK = this.themTaiKhoanView.getTenTaiKhoanTF().getText();
				String matKhau = this.themTaiKhoanView.getMatKhauTF().getText();
				String loaiTaiKhoan = String.valueOf(this.themTaiKhoanView.getLoaiTKComboBox().getSelectedItem());
				taiKhoan taiKhoan = new taiKhoan(maTK, nhanVien, tenTK, matKhau, loaiTaiKhoan);
				taiKhoanDAO.getInstance().insertT(taiKhoan);
				mainView mainView = new mainView();
				mainView.setTabTaiKhoan();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Thêm thành công");
				this.themTaiKhoanView.dispose();

				mainView.setVisible(true);
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				mainView mainView = new mainView();
				mainView.setTabTaiKhoan();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Thêm thất bại");
				this.themTaiKhoanView.dispose();
				mainView.setVisible(true);
				errView.setVisible(true);
			}
		}

	}
}
