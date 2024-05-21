package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import DAO.nhanVienDAO;
import DAO.nhanVien_kyNangDAO;
import model.nhanVien;
import model.nhanVien_kyNang;
import model.phongBan;
import view.errView;
import view.mainView;
import view.themTrinhDoView;

public class themTrinhDoController implements ActionListener {
	private themTrinhDoView themTrinhDoView;

	public themTrinhDoController(themTrinhDoView themTrinhDoView) {
		this.themTrinhDoView = themTrinhDoView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Hủy")) {
			mainView mainView = new mainView();
			mainView.setTabTrinhDo();
			this.themTrinhDoView.dispose();
			mainView.setVisible(true);
		} else if (e.getActionCommand().equals("Thêm")) {
			try {
				int maKN = 0;
				int maNV = Integer.parseInt(this.themTrinhDoView.getMaNVTF().getText());
				String tenNV = (nhanVienDAO.getInstance()
						.selectByID(new nhanVien(maNV, "", "", null, "", "", "", "", "", null))).getHoTen();
				String tenKyNang = String.valueOf(this.themTrinhDoView.getKyNangComboBox().getSelectedItem());
				if (tenKyNang.equals("Tin học")) {
					maKN = 1;
				} else {
					maKN = 2;
				}
				String capBacKyNang = String.valueOf(this.themTrinhDoView.getKyNangComboBox_1().getSelectedItem());
				nhanVien_kyNang nhanVien_kyNang = new nhanVien_kyNang(maKN, maNV, tenNV, tenKyNang, capBacKyNang);
				nhanVien_kyNangDAO.getInstance().insertT(nhanVien_kyNang);
				ArrayList<nhanVien_kyNang> newArr = new ArrayList<>();
				changeTableData(newArr);
				mainView mainView = new mainView();
				mainView.setTabTrinhDo();

				view.errView errView = new errView();
				errView.getLblNewLabel().setText("Thêm thành công!");
				this.themTrinhDoView.dispose();
				mainView.setVisible(true);
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				mainView mainView = new mainView();
				mainView.setTabTrinhDo();

				view.errView errView = new errView();
				errView.getLblNewLabel().setText("Thêm thất bại!");
				this.themTrinhDoView.dispose();
				mainView.setVisible(true);
				errView.setVisible(true);
			}

		}

	}

	public void changeTableData(ArrayList<nhanVien_kyNang> newArr) {
		ArrayList<String[]> data = new ArrayList<>();
		int size = 4;

		for (int i = 0; i < newArr.size(); i++) {
			String[] tmp = new String[size];
			tmp[0] = String.valueOf(newArr.get(i).getMaNV());
			tmp[1] = String.valueOf(newArr.get(i).getTenNV());
			tmp[2] = String.valueOf(newArr.get(i).getTenKyNang());
			tmp[3] = String.valueOf(newArr.get(i).getCapBacKyNang());
			data.add(tmp);
		}

		// Tạo bảng và gắn dữ liệu vào
		String[] columnNames = { "Mã NV", "Họ Tên", "Tên kỹ năng", "Cấp bậc" };
		this.themTrinhDoView.getTrinhDoView().setTableModel(new DefaultTableModel(columnNames, 0));
		for (String[] row : data) {
			this.themTrinhDoView.getTrinhDoView().getTableModel().addRow(row);
		}

		this.themTrinhDoView.getTrinhDoView().getTable()
				.setModel(this.themTrinhDoView.getTrinhDoView().getTableModel());
	}

}
