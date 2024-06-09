package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import DAO.hopDongDAO;
import DAO.nhanVienDAO;
import DAO.taiKhoanDAO;
import model.nhanVien;
import model.taiKhoan;
import view.capNhatTaiKhoanNhanVienView;
import view.capNhatTaiKhoanView;
import view.errView;
import view.loginView;
import view.mainView;
import view.mainViewNhanVien;

public class capNhatTaiKhoanNhanVienController implements ActionListener {
	private capNhatTaiKhoanNhanVienView capNhatTaiKhoanNhanVienView;

	public capNhatTaiKhoanNhanVienController(capNhatTaiKhoanNhanVienView capNhatTaiKhoanNhanVienView) {
		this.capNhatTaiKhoanNhanVienView = capNhatTaiKhoanNhanVienView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Cập nhật")) {
			try {
				int maTK = this.capNhatTaiKhoanNhanVienView.getMaTK();

				String tenTK = this.capNhatTaiKhoanNhanVienView.getTenTaiKhoanTF().getText();
				String matKhau = this.capNhatTaiKhoanNhanVienView.getMatKhauTF().getText();

				taiKhoan taiKhoan = new taiKhoan(maTK, null, tenTK, matKhau, "nhân viên");
				System.out.println(taiKhoan.getMaTK());
				taiKhoanDAO.getInstance().updateTNhanVien(taiKhoan);

				errView errView = new errView();
				errView.getLblNewLabel().setText("Cập nhật thành công, vui lòng đăng nhập lại");

				this.capNhatTaiKhoanNhanVienView.dispose();
				new loginView();
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();

				mainViewNhanVien mainViewNhanVien = new mainViewNhanVien(
						taiKhoanDAO.getInstance().selectByMATK(this.capNhatTaiKhoanNhanVienView.getMaTK()));
				mainViewNhanVien.setTabThongTin();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Lỗi cập nhật");

				this.capNhatTaiKhoanNhanVienView.dispose();

				mainViewNhanVien.setVisible(true);
				errView.setVisible(true);
			}

		} else if (e.getActionCommand().equals("Hủy"))

		{
			mainViewNhanVien mainViewNhanVien = new mainViewNhanVien(
					taiKhoanDAO.getInstance().selectByMATK(this.capNhatTaiKhoanNhanVienView.getMaTK()));
			mainViewNhanVien.setTabThongTin();

			this.capNhatTaiKhoanNhanVienView.dispose();

			mainViewNhanVien.setVisible(true);
		}
	}
}
