package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.yeuCauDAO;
import model.taiKhoan;
import model.yeuCau;
import view.errView;
import view.mainViewNhanVien;
import view.themYeuCauViewNhanVien;

public class themYeuCauNhanVienController implements ActionListener {
	private themYeuCauViewNhanVien themYC;

	public themYeuCauNhanVienController(themYeuCauViewNhanVien themYC) {
		this.themYC = themYC;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Gửi")) {
			try {
				errView errView = new errView();
				errView.getLblNewLabel().setText("Đã gửi yêu cầu!");
				yeuCau yeuCau = new yeuCau(1, this.themYC.getTkht().getNhanVien().getMaNV(),
						this.themYC.getTextArea().getText(), false);
				yeuCauDAO.getInstance().insertT(yeuCau);
				mainViewNhanVien mainViewNhanVien = new mainViewNhanVien(this.themYC.getTkht());
				mainViewNhanVien.setTabNhanVien();
				this.themYC.dispose();
				mainViewNhanVien.setVisible(true);
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
			}

		} else if (e.getActionCommand().equals("Hủy")) {
			mainViewNhanVien mainViewNhanVien = new mainViewNhanVien(this.themYC.getTkht());
			mainViewNhanVien.setTabNhanVien();
			this.themYC.dispose();
			mainViewNhanVien.setVisible(true);
		}

	}

}
