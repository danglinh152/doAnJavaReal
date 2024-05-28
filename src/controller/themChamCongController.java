package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import DAO.chamCongDAO;
import model.chamCongClass;
import view.errView;
import view.mainView;
import view.themChamCongView;

public class themChamCongController implements ActionListener, KeyListener {
	private themChamCongView themChamCongView;

	public themChamCongController(themChamCongView themChamCongView) {
		this.themChamCongView = themChamCongView;
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
		if (e.getActionCommand().equals("Chấm công")) {

			try {
				int maNV = Integer.parseInt(this.themChamCongView.getMaNVTF().getText());
				String hoTen = "";
				int thangLamViec = Integer.parseInt(this.themChamCongView.getThangLamViecTF().getText());
				int soNgayLamViec = Integer.parseInt(this.themChamCongView.getSoNgayLamViecTF().getText());
				int soNgayNghi = Integer.parseInt(this.themChamCongView.getSoNgayNghiTF().getText());
				double soGioTangCa = Double.parseDouble(this.themChamCongView.getSoGioTangCaTF().getText());
				int soNgayDiTre = Integer.parseInt(this.themChamCongView.getSoNgayDiTreTF().getText());
				chamCongClass chamCongClass = new chamCongClass(0, maNV, hoTen, thangLamViec, soNgayLamViec, soNgayNghi,
						soGioTangCa, soNgayDiTre);
				System.out.println(chamCongClass.getMaNV() + " " + chamCongClass.getMaCC());
				chamCongDAO.getInstance().insertT(chamCongClass);
				mainView mainView = new mainView();
				mainView.setTabChamCong();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Chấm công thành công!");
				this.themChamCongView.dispose();
				mainView.setVisible(true);
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				mainView mainView = new mainView();
				mainView.setTabChamCong();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Chấm công thất bại");
				this.themChamCongView.dispose();
				mainView.setVisible(true);
				errView.setVisible(true);

			}
		} else if (e.getActionCommand().equals("Hủy")) {
			view.mainView mainView = new mainView();
			mainView.setTabChamCong();
			this.themChamCongView.dispose();
			mainView.setVisible(true);
		}

	}

}
