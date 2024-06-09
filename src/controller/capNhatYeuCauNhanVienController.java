package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.yeuCauDAO;
import model.yeuCau;
import view.capNhatYeuCauViewNhanVien;
import view.errView;
import view.mainViewNhanVien;

public class capNhatYeuCauNhanVienController implements ActionListener {
	private capNhatYeuCauViewNhanVien capNhatYeuCauViewNhanVien;

	public capNhatYeuCauNhanVienController(capNhatYeuCauViewNhanVien capNhatYeuCauViewNhanVien) {
		this.capNhatYeuCauViewNhanVien = capNhatYeuCauViewNhanVien;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Sửa")) {
			try {
				int mayc = Integer.parseInt(this.capNhatYeuCauViewNhanVien.getLblNewLabel().getText());
				int manv = this.capNhatYeuCauViewNhanVien.getTkht().getNhanVien().getMaNV();
				String noiDung = this.capNhatYeuCauViewNhanVien.getTextArea().getText();
				boolean trangThai = false;

				yeuCau yeuCau = new yeuCau(mayc, manv, noiDung, trangThai);
				System.out.println(yeuCau.getMaYC() + " " + yeuCau.getMaNV() + " " + yeuCau.getNoiDung());
				yeuCauDAO.getInstance().updateYeuCauNhanViendemo(yeuCau); // ne
				mainViewNhanVien mainViewNhanVien = new mainViewNhanVien(this.capNhatYeuCauViewNhanVien.getTkht());
				mainViewNhanVien.setTabNhanVien();
				view.errView errView = new errView();
				errView.getLblNewLabel().setText("Sửa yêu cầu thành công");
				this.capNhatYeuCauViewNhanVien.dispose();
				mainViewNhanVien.setVisible(true);
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				mainViewNhanVien mainViewNhanVien = new mainViewNhanVien(this.capNhatYeuCauViewNhanVien.getTkht());
				mainViewNhanVien.setTabNhanVien();
				view.errView errView = new errView();
				errView.getLblNewLabel().setText("Sửa yêu cầu thất bại");
				this.capNhatYeuCauViewNhanVien.dispose();
				mainViewNhanVien.setVisible(true);
				errView.setVisible(true);
			}
		} else if (e.getActionCommand().equals("Hủy")) {
			this.capNhatYeuCauViewNhanVien.dispose();
			new mainViewNhanVien(this.capNhatYeuCauViewNhanVien.getTkht()).setTabNhanVien();

		}
	}

}
