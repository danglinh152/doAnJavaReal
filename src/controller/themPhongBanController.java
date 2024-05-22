package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import DAO.nhanVienDAO;
import DAO.phongBanDAO;
import model.nhanVien;
import model.phongBan;
import view.mainView;
import view.errView;
import view.themPhongBanView;

public class themPhongBanController implements ActionListener {
	private themPhongBanView themPhongBanView;

	public themPhongBanController(themPhongBanView themPhongBanView) {
		this.themPhongBanView = themPhongBanView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Thêm")) {
			try {
				String tenPB = this.themPhongBanView.getTenPhongBanTextField().getText();
				System.out.println(this.themPhongBanView.getNgThanhLapTF().getText());
				Date ngayThanhLap = Date.valueOf(this.themPhongBanView.getNgThanhLapTF().getText());

				int maTP = 0;
				nhanVien truongPhong = null;

				try {
					maTP = Integer.parseInt(this.themPhongBanView.getMaTruongPhongTF().getText());
					truongPhong = nhanVienDAO.getInstance()
							.selectByID(new nhanVien(maTP, "", "", null, "", "", "", "", "", null));
				} catch (Exception e2) {
					// TODO: handle exception
					// error
				}

				Date ngayNhanChuc = Date.valueOf(this.themPhongBanView.getNgNhanChucTF().getText());
				if (truongPhong == null && maTP != 0) {
					mainView mainView = new mainView();
					mainView.setTabPhongBan();
					errView errView = new errView();
					errView.getLblNewLabel().setText("Mã trưởng phòng không tồn tại");

					this.themPhongBanView.dispose();
					mainView.setVisible(true);
					errView.setVisible(true);

				} else {
					try {
						phongBanDAO.getInstance().insertT(new phongBan(phongBanDAO.getInstance().seq_num() + 1, tenPB,
								ngayThanhLap, maTP, ngayNhanChuc));
						mainView mainView = new mainView();
						mainView.setTabPhongBan();
//						errView errView = new errView();
//						errView.getLblNewLabel().setText("Thêm thành công");
						this.themPhongBanView.dispose();

						mainView.setVisible(true);
						// errView.setVisible(true);
					} catch (Exception e2) {
						// TODO: handle exception
						mainView mainView = new mainView();
						mainView.setTabPhongBan();
						errView errView = new errView();
						errView.getLblNewLabel().setText("Thêm thất bại");
						this.themPhongBanView.dispose();

						mainView.setVisible(true);
						errView.setVisible(true);
					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
				mainView mainView = new mainView();
				mainView.setTabPhongBan();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Lỗi thêm");
				this.themPhongBanView.dispose();
				mainView.setVisible(true);
				errView.setVisible(true);
			}

		} else if (e.getActionCommand().equals("Hủy")) {
			mainView mainView = new mainView();
			mainView.setTabPhongBan();
			this.themPhongBanView.dispose();
			mainView.setVisible(true);
		}

	}

}
