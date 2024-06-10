package controller;

import view.capNhatPhongBanView;
import view.errView;
import view.guiYeuCauViewNhanVien;
import view.rendererTableGuiYC;
import view.themNhanVienView;
import view.themYeuCauViewNhanVien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.nhanVienDAO;
import DAO.phongBanDAO;
import DAO.yeuCauDAO;
import model.nhanVien;
import model.phongBan;
import model.yeuCau;

import view.yeuCauView;

public class yeuCauController implements ActionListener, KeyListener {
	private yeuCauView yeuCauView;

	public yeuCauController(yeuCauView yeuCauView) {
		this.yeuCauView = yeuCauView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("")) {
			String txt = yeuCauView.getTxtTnPhngBan().getText();

			if (txt.equals("") || txt == null) {
				ArrayList<yeuCau> arr_yc = yeuCauDAO.getInstance().selectSortByMAYCASC();
				changeTableData(arr_yc);
			} else {
				ArrayList<yeuCau> arr_yc = yeuCauDAO.getInstance().selectByLike(txt);
				changeTableData(arr_yc);
			}
//		} else if (e.getActionCommand().equals("Cập nhật")) {
//			int selectedRow = this.nhanVienViewNhanVien.getTable().getSelectedRow();
//			Object[] rowData = new Object[this.nhanVienViewNhanVien.getTable().getColumnCount()];
//			for (int i = 0; i < this.nhanVienViewNhanVien.getTable().getColumnCount(); i++) {
//				rowData[i] = this.nhanVienViewNhanVien.getTable().getValueAt(selectedRow, i);
//			}
//			int maNV = Integer.parseInt(String.valueOf(rowData[0]));
//			String tenNV = String.valueOf(rowData[1]);
//			String gioiTinh = String.valueOf(rowData[2]);
//			String ngaySinh = String.valueOf(rowData[3]);
//			String sdt = String.valueOf(rowData[4]);
//			String email = String.valueOf(rowData[5]);
//			String diaChi = String.valueOf(rowData[6]);
//			String cccd = String.valueOf(rowData[7]);
//			String capBac = String.valueOf(rowData[8]);
//			int maPB = Integer.parseInt(String.valueOf(rowData[9]));
//			phongBan phongBan = phongBanDAO.getInstance().selectByID(new phongBan(maPB, "", null, 0, null));
//
//			nhanVien nhanVien = new nhanVien(maNV, tenNV, gioiTinh, Date.valueOf(ngaySinh), sdt, email, diaChi, cccd,
//					capBac, phongBan);
//			new capNhatnhanVienViewNhanVien(nhanVien);
//			this.nhanVienViewNhanVien.getMainViewNhanVien().dispose();
//		}
		} else if (e.getActionCommand().equals("Duyệt")) {
			try {
				TableModel model = yeuCauView.getTable().getModel();

				int maYC = Integer.parseInt((String) model.getValueAt(yeuCauView.getTable().getSelectedRows()[0], 0));
				yeuCau yeuCau = new yeuCau(maYC, 0, "", false);
				yeuCauDAO.getInstance().updateT(yeuCau); // ne
				ArrayList<yeuCau> arr_yc = yeuCauDAO.getInstance().selectSortByMAYCASC();
				changeTableData(arr_yc);
			} catch (Exception e2) {
				// TODO: handle exception
				errView errView = new errView();
				// errView.getLblNewLabel().setText("Không thể duyệt lúc này!");
				errView.getLblNewLabel().setText(e2.getMessage());
				errView.setVisible(true);
			}
		} else if (e.getActionCommand().equals("Duyệt tất cả")) {
			try {
				yeuCauDAO.getInstance().updateAll(); // ne
				ArrayList<yeuCau> arr_yc = yeuCauDAO.getInstance().selectSortByMAYCASC();
				changeTableData(arr_yc);
			} catch (Exception e2) {
				// TODO: handle exception
				errView errView = new errView();
				// errView.getLblNewLabel().setText("Không thể duyệt lúc này!");
				errView.getLblNewLabel().setText(e2.getMessage());
				errView.setVisible(true);
			}
		}

		String thuocTinh = (String) this.yeuCauView.getThuocTinhComboBox().getSelectedItem();
		String thuTu = (String) this.yeuCauView.getThuTuComboBox().getSelectedItem();
		if (thuocTinh.equals("Mã Yêu Cầu") && thuTu.equals("Tăng dần")) {
			ArrayList<yeuCau> arr_yc = yeuCauDAO.getInstance().selectSortByMAYCASC();
			changeTableData(arr_yc);
		} else if (thuocTinh.equals("Mã Yêu Cầu") && thuTu.equals("Giảm dần")) {
			ArrayList<yeuCau> arr_yc = yeuCauDAO.getInstance().selectSortByMAYCDESC();
			changeTableData(arr_yc);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String txt = yeuCauView.getTxtTnPhngBan().getText();

			if (txt.equals("") || txt == null) {
				ArrayList<yeuCau> arr_yc = yeuCauDAO.getInstance().selectSortByMAYCASC();
				changeTableData(arr_yc);
			} else {
				ArrayList<yeuCau> arr_yc = yeuCauDAO.getInstance().selectByLike(txt);
				changeTableData(arr_yc);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void changeTableData(ArrayList<yeuCau> newArr) {
		ArrayList<String[]> data = new ArrayList<>();
		int size = 4;

		for (int i = 0; i < newArr.size(); i++) {
			String[] tmp = new String[size];
			tmp[0] = String.valueOf(newArr.get(i).getMaYC());
			tmp[1] = String.valueOf(newArr.get(i).getMaNV());
			tmp[2] = String.valueOf(newArr.get(i).getNoiDung());
			if (newArr.get(i).isTrangThai()) {
				tmp[3] = "Đã được duyệt";
			} else {
				tmp[3] = "Chưa được duyệt";
			}
			data.add(tmp);
		}

		// Tạo bảng và gắn dữ liệu vào
		String[] columnNames = { "Mã Yêu Cầu", "Mã NV", "Nội Dung", "Trạng Thái" };
		yeuCauView.setTableModel(new DefaultTableModel(columnNames, 0));
		for (String[] row : data) {
			yeuCauView.getTableModel().addRow(row);
		}

		yeuCauView.getTable().setModel(yeuCauView.getTableModel());

		yeuCauView.getTable().getColumnModel().getColumn(2).setPreferredWidth(600);

		// Set custom renderer cho cột "Nội Dung"
		yeuCauView.getTable().getColumnModel().getColumn(2).setCellRenderer(new rendererTableGuiYC());

		// nhanVienViewNhanVien.getTable().setBounds(0, 0, 1, 1);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
