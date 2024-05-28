package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import DAO.chamCongDAO;
import model.chamCongClass;
import view.mainView;
import view.capNhatChamCongView;
import view.errView;

public class capNhatChamCongController implements ActionListener, KeyListener {
	private capNhatChamCongView capNhatChamCongView;

	public capNhatChamCongController(capNhatChamCongView capNhatChamCongView) {
		this.capNhatChamCongView = capNhatChamCongView;
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
		if (e.getActionCommand().equals("Cập nhật")) {
			try {

				int maNV = Integer.parseInt(this.capNhatChamCongView.getMaNVTF().getText());
				String hoTen = "";
				int thangLamViec = Integer.parseInt(this.capNhatChamCongView.getThangLamViecTF().getText());
				int soNgayLamViec = Integer.parseInt(this.capNhatChamCongView.getSoNgayLamViecTF().getText());
				int soNgayNghi = Integer.parseInt(this.capNhatChamCongView.getSoNgayNghiTF().getText());
				double soGioTangCa = Double.parseDouble(this.capNhatChamCongView.getSoGioTangCaTF().getText());
				int soNgayDiTre = Integer.parseInt(this.capNhatChamCongView.getSoNgayDiTreTF().getText());
				chamCongClass t = new chamCongClass(maNV, hoTen, thangLamViec, soNgayLamViec, soNgayNghi, soGioTangCa,
						soNgayDiTre);
				chamCongDAO.getInstance().updateT(t);
				mainView mainView = new mainView();
				mainView.setTabChamCong();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Cập nhật thành công!");
				this.capNhatChamCongView.dispose();
				mainView.setVisible(true);
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				mainView mainView = new mainView();
				mainView.setTabChamCong();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Cập nhật thất bại!");
				this.capNhatChamCongView.dispose();
				mainView.setVisible(true);
				errView.setVisible(true);
			}
		} else if (e.getActionCommand().equals("Hủy")) {
			view.mainView mainView = new mainView();
			mainView.setTabChamCong();
			this.capNhatChamCongView.dispose();
			mainView.setVisible(true);
		}

	}

}
