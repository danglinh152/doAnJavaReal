package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.table.TableModel;

import org.apache.poi.util.SystemOutLogger;

import DAO.chamCongDAO;
import DAO.nhanVienDAO;
import model.chamCongClass;
import model.nhanVien;
import model.yeuCau;
import view.errView;
import view.mainView;
import view.themChamCongView;
import view.xetDuyetCapBacView;

public class themXetDuyetYeuCauController implements ActionListener {
	private xetDuyetCapBacView xetDuyetCapBacView;

	public themXetDuyetYeuCauController(xetDuyetCapBacView xetDuyetCapBacView) {
		this.xetDuyetCapBacView = xetDuyetCapBacView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Phê Duyệt")) {

			try {
				TableModel model = xetDuyetCapBacView.getTable().getModel();

				int maNV = Integer
						.parseInt((String) model.getValueAt(xetDuyetCapBacView.getTable().getSelectedRows()[0], 0));
				String capBac = String.valueOf(model.getValueAt(xetDuyetCapBacView.getTable().getSelectedRows()[0], 8));

				nhanVien nv = new nhanVien(maNV, "", "", null, "", "", "", "", capBac, null);
				nhanVienDAO.getInstance().updateTCAPBAC(nv, capBac);
				mainView mainView = new mainView();
				mainView.setTabNhanVien();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Duyệt thành công!");
				this.xetDuyetCapBacView.dispose();
				mainView.setVisible(true);
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				mainView mainView = new mainView();
				mainView.setTabNhanVien();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Duyệt thất bại");
				this.xetDuyetCapBacView.dispose();
				mainView.setVisible(true);
				errView.setVisible(true);

			}
		} else if (e.getActionCommand().equals("Hủy")) {
			view.mainView mainView = new mainView();
			mainView.setTabNhanVien();
			this.xetDuyetCapBacView.dispose();
			mainView.setVisible(true);
		}

	}

}
