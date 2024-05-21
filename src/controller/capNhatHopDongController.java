package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import DAO.hopDongDAO;
import DAO.nhanVienDAO;
import DAO.phongBanDAO;
import model.nhanVien;
import model.phongBan;
import view.mainView;
import view.errView;
import view.capNhatHopDongView;

public class capNhatHopDongController implements ActionListener {
	private capNhatHopDongView capNhatHopDongView;

	public capNhatHopDongController(capNhatHopDongView capNhatHopDongView) {
		this.capNhatHopDongView = capNhatHopDongView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Cập nhật")) {
			try {

				int maNV = Integer.parseInt(capNhatHopDongView.getMaNVTF().getText());
				int maHD = Integer.parseInt(this.capNhatHopDongView.getMaHDTF().getText());
				Date ngbd = Date.valueOf(String.valueOf(this.capNhatHopDongView.getNgBDTF().getText()));
				Date ngkt = Date.valueOf(String.valueOf(this.capNhatHopDongView.getNgKTTF().getText()));
				hopDongDAO.getInstance().updateT(maNV, maHD, ngbd, ngkt);

				mainView mainView = new mainView();
				mainView.setTabHopDong();

				errView errView = new errView();
				errView.getLblNewLabel().setText("Cập nhật thành công");

				this.capNhatHopDongView.dispose();

				mainView.setVisible(true);
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();

				mainView mainView = new mainView();
				mainView.setTabHopDong();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Lỗi cập nhật");

				this.capNhatHopDongView.dispose();

				mainView.setVisible(true);
				errView.setVisible(true);
			}

		} else if (e.getActionCommand().equals("Hủy"))

		{
			mainView mainView = new mainView();
			mainView.setTabHopDong();

			this.capNhatHopDongView.dispose();

			mainView.setVisible(true);
		}

	}

}
