package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import DAO.hopDongDAO;
import DAO.nhanVienDAO;
import model.hopDong;
import model.nhanVien;
import view.errView;
import view.mainView;
import view.themHopDongView;

public class themHopDongController implements ActionListener {
	private themHopDongView themHopDongView;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Hủy")) {
			mainView mainView = new mainView();
			mainView.setTabHopDong();
			mainView.setVisible(true);
			this.themHopDongView.dispose();
		} else if (e.getActionCommand().equals("Thêm")) {
			try {
				int maHD = hopDongDAO.getInstance().seq_num() + 1;
				int maNV = Integer.parseInt(this.themHopDongView.getMaNVTF().getText());
				nhanVien nhanVien = nhanVienDAO.getInstance()
						.selectByID(new nhanVien(maNV, "", "", null, "", "", "", "", "", null));
				Date ngBDHD = Date.valueOf(this.themHopDongView.getNgBDTF().getText());
				Date ngKTHD = Date.valueOf(this.themHopDongView.getNgKTTF().getText());
				hopDong hopDong = new hopDong(maHD, nhanVien, ngBDHD, ngKTHD);
				hopDongDAO.getInstance().insertT(hopDong);
				mainView mainView = new mainView();
				mainView.setTabHopDong();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Thêm thành công");
				this.themHopDongView.dispose();

				mainView.setVisible(true);
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				mainView mainView = new mainView();
				mainView.setTabHopDong();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Thêm thất bại");
				this.themHopDongView.dispose();
				mainView.setVisible(true);
				errView.setVisible(true);
			}

		}

	}

	public themHopDongController(themHopDongView themHopDongView) {
		this.themHopDongView = themHopDongView;
	}
}
