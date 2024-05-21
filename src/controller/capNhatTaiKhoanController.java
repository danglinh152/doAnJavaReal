package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import DAO.hopDongDAO;
import DAO.nhanVienDAO;
import DAO.taiKhoanDAO;
import model.nhanVien;
import model.taiKhoan;
import view.capNhatTaiKhoanView;
import view.errView;
import view.mainView;

public class capNhatTaiKhoanController implements ActionListener {
	private capNhatTaiKhoanView capNhatTaiKhoanView;

	public capNhatTaiKhoanController(capNhatTaiKhoanView capNhatTaiKhoanView) {
		this.capNhatTaiKhoanView = capNhatTaiKhoanView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Cập nhật")) {
			try {
				int maTK = this.capNhatTaiKhoanView.getMaTK();
				int maNV = Integer.parseInt(this.capNhatTaiKhoanView.getMaNVTF().getText());
				nhanVien nhanVien = nhanVienDAO.getInstance()
						.selectByID(new nhanVien(maNV, "", "", null, "", "", "", "", "", null));
				String tenTK = this.capNhatTaiKhoanView.getTenTaiKhoanTF().getText();
				String matKhau = this.capNhatTaiKhoanView.getMatKhauTF().getText();
				String loaiTaiKhoan = String.valueOf(this.capNhatTaiKhoanView.getLoaiTKComboBox().getSelectedItem());
				taiKhoan taiKhoan = new taiKhoan(maTK, nhanVien, tenTK, matKhau, loaiTaiKhoan);
				taiKhoanDAO.getInstance().updateT(taiKhoan);

				mainView mainView = new mainView();
				mainView.setTabTaiKhoan();

				errView errView = new errView();
				errView.getLblNewLabel().setText("Cập nhật thành công");

				this.capNhatTaiKhoanView.dispose();

				mainView.setVisible(true);
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();

				mainView mainView = new mainView();
				mainView.setTabTaiKhoan();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Lỗi cập nhật");

				this.capNhatTaiKhoanView.dispose();

				mainView.setVisible(true);
				errView.setVisible(true);
			}

		} else if (e.getActionCommand().equals("Hủy"))

		{
			mainView mainView = new mainView();
			mainView.setTabTaiKhoan();

			this.capNhatTaiKhoanView.dispose();

			mainView.setVisible(true);
		}
	}
}
