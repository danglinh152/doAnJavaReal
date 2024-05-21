package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import DAO.hopDongDAO;
import DAO.nhanVien_kyNangDAO;
import model.nhanVien_kyNang;
import view.capNhatTrinhDoView;
import view.errView;
import view.mainView;

public class capNhatTrinhDoController implements ActionListener {
	private capNhatTrinhDoView capNhatTrinhDoView;

	public capNhatTrinhDoController(capNhatTrinhDoView capNhatTrinhDoView) {
		this.capNhatTrinhDoView = capNhatTrinhDoView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Cập nhật")) {
			try {
				int maNV = Integer.parseInt(this.capNhatTrinhDoView.getMaNVTF().getText());

				String tenKN = String.valueOf(this.capNhatTrinhDoView.getKyNangComboBox().getSelectedItem());
				String capBac = String.valueOf(this.capNhatTrinhDoView.getCapBacComboBox().getSelectedItem());
				int maKN = 0;
				if (tenKN.equals("Tin học")) {
					maKN = 1;
				} else if (tenKN.equals("Tiếng anh")) {
					maKN = 2;
				}
				nhanVien_kyNang nvkn = new nhanVien_kyNang(maKN, maNV, "", "", capBac);
				nhanVien_kyNangDAO.getInstance().updateT(nvkn);

				mainView mainView = new mainView();
				mainView.setTabTrinhDo();

				errView errView = new errView();
				errView.getLblNewLabel().setText("Cập nhật thành công");

				this.capNhatTrinhDoView.dispose();

				mainView.setVisible(true);
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();

				mainView mainView = new mainView();
				mainView.setTabTrinhDo();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Lỗi cập nhật");

				this.capNhatTrinhDoView.dispose();

				mainView.setVisible(true);
				errView.setVisible(true);
			}
		} else if (e.getActionCommand().equals("Hủy")) {
			mainView mainView = new mainView();
			mainView.setTabTrinhDo();

			this.capNhatTrinhDoView.dispose();

			mainView.setVisible(true);
		}

	}

}
