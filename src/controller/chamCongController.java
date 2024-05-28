package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import DAO.chamCongDAO;
import DAO.nhanVienDAO;
import model.nhanVien;
import model.phongBan;
import model.chamCongClass;
import view.capNhatChamCongView;
import view.capNhatPhongBanView;
import view.chamCongView;
import view.errView;
import view.themChamCongView;

public class chamCongController implements ActionListener, KeyListener {
	private chamCongView chamCongView;

	public chamCongController(chamCongView chamCongView) {
		this.chamCongView = chamCongView;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		String tennv = chamCongView.getTenNVTF().getText();
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!tennv.equals("Nhập mã nhân viên") || !tennv.trim().equals("")) {
				ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectNVCCBYTENNV(tennv);
				ArrayList<chamCongClass> arr_cc = chamCongDAO.getInstance().selectByTENNV(tennv);

				changeTableData(arr_cc, arr_nv);
			} else if (tennv.equals("Nhập mã nhân viên") || tennv.trim().equals("")) {
				changeTableData(chamCongView.getChamCongData(), chamCongView.getNhanVienData());
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("In phiếu chấm công")) {
			try {
				excelController ex = new excelController();
				ex.exportFileCC();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Xuất file excel thành công!");
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				errView errView = new errView();
				errView.getLblNewLabel().setText("Xuất file excel thất bại!");
				errView.setVisible(true);

			}
		} else if (e.getActionCommand().equals("")) {
			String tennv = chamCongView.getTenNVTF().getText();
			if (!tennv.equals("Nhập mã nhân viên") || !tennv.trim().equals("")) {
				ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectNVCCBYTENNV(tennv);
				ArrayList<chamCongClass> arr_cc = chamCongDAO.getInstance().selectByTENNV(tennv);

				changeTableData(arr_cc, arr_nv);
			} else if (tennv.equals("Nhập mã nhân viên") || tennv.trim().equals("")) {
				changeTableData(chamCongView.getChamCongData(), chamCongView.getNhanVienData());
			}
		} else if (e.getActionCommand().equals("Chấm công")) {
			themChamCongView themChamCongView = new themChamCongView();
			int selectedRow = this.chamCongView.getNvTable().getSelectedRow();
			Object[] rowData = new Object[this.chamCongView.getNvTable().getColumnCount()];
			for (int i = 0; i < this.chamCongView.getNvTable().getColumnCount(); i++) {
				rowData[i] = this.chamCongView.getNvTable().getValueAt(selectedRow, i);
			}
			themChamCongView.getMaNVTF().setText(String.valueOf(rowData[0]));
			themChamCongView.setVisible(true);
			this.chamCongView.getMainView().dispose();
		} else if (e.getActionCommand().equals("Sửa")) {
			int selectedRow = this.chamCongView.getChamCongtable().getSelectedRow();
			Object[] rowData = new Object[this.chamCongView.getChamCongtable().getColumnCount()];
			for (int i = 0; i < this.chamCongView.getChamCongtable().getColumnCount(); i++) {
				rowData[i] = this.chamCongView.getChamCongtable().getValueAt(selectedRow, i);
			}

			// int maCC = Integer.parseInt(String.valueOf(rowData[0]));
			int maNV = Integer.parseInt(String.valueOf(rowData[0]));
//			String hoTen = 
//			int thangLamViec = Integer.parseInt(String.valueOf(rowData[2]));
//			int soNgayLamViec = Integer.parseInt(String.valueOf(rowData[3]));
//			int soNgayNghi = Integer.parseInt(String.valueOf(rowData[4]));
//			double soGioTangCa = Double.parseDouble(String.valueOf(rowData[5]));
//			int soNgayDiTre = Integer.parseInt(String.valueOf(rowData[6]));
			chamCongDAO.getInstance().selectByMANV(maNV);

			chamCongClass chamCongClass = (chamCongDAO.getInstance().selectByMANV(maNV)).get(0);
			new capNhatChamCongView(chamCongClass);
			this.chamCongView.getMainView().dispose();
		} else if (e.getActionCommand().equals("Xóa")) {
			int selectedRow = this.chamCongView.getChamCongtable().getSelectedRow();
			Object[] rowData = new Object[this.chamCongView.getChamCongtable().getColumnCount()];
			for (int i = 0; i < this.chamCongView.getChamCongtable().getColumnCount(); i++) {
				rowData[i] = this.chamCongView.getChamCongtable().getValueAt(selectedRow, i);
			}

			int maNV = Integer.parseInt(String.valueOf(rowData[0]));
			int thanglamviec = Integer.parseInt(String.valueOf(rowData[1]));
			// int maNV = Integer.parseInt(String.valueOf(rowData[1]));
//		String hoTen = 
//		int thangLamViec = Integer.parseInt(String.valueOf(rowData[2]));
//		int soNgayLamViec = Integer.parseInt(String.valueOf(rowData[3]));
//		int soNgayNghi = Integer.parseInt(String.valueOf(rowData[4]));
//		double soGioTangCa = Double.parseDouble(String.valueOf(rowData[5]));
//		int soNgayDiTre = Integer.parseInt(String.valueOf(rowData[6]));

			try {
				chamCongDAO.getInstance().deleteT(maNV, thanglamviec);
				errView errView = new errView();
				ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectAll();
				ArrayList<chamCongClass> arr_cc = chamCongDAO.getInstance().selectAll();

				changeTableData(arr_cc, arr_nv);
				errView.getLblNewLabel().setText("Xóa thành công!");
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				errView errView = new errView();
				errView.getLblNewLabel().setText("Xóa thất bại!");
				errView.setVisible(true);
			}
		}

	}

	public void changeTableData(ArrayList<chamCongClass> arr_cc, ArrayList<nhanVien> arr_nv) {
		ArrayList<String[]> data_cc = new ArrayList<>();
		int size_cc = 8;

		for (int i = 0; i < arr_cc.size(); i++) {
			String[] tmp = new String[size_cc];
			tmp[0] = String.valueOf(arr_cc.get(i).getMaNV());
			tmp[1] = String.valueOf(arr_cc.get(i).getThangLamViec());
			tmp[2] = String.valueOf(arr_cc.get(i).getSoNgayLamViec());
			tmp[3] = String.valueOf(arr_cc.get(i).getSoNgayNghi());
			tmp[4] = String.valueOf(arr_cc.get(i).getSoGioTangCa());
			tmp[5] = String.valueOf(arr_cc.get(i).getSoNgayDiTre());

			DecimalFormat df = new DecimalFormat("#,###");
			String result = df.format(nhanVienDAO.getInstance().tinhLuongNhanVien(arr_cc.get(i).getMaNV(),
					arr_cc.get(i).getThangLamViec()));
			if (result.equals("NaN")) {
				tmp[6] = "0";
			} else {
				tmp[6] = result;
			}
			data_cc.add(tmp);
		}

		String[] columnNames_cc = { "Mã nhân viên", "Tháng làm việc", "Số ngày làm việc", "Số ngày nghỉ",
				"Số giờ tăng ca", "Số ngày đi trễ", "Tổng lương" };
		chamCongView.setTableModel_cc(new DefaultTableModel(columnNames_cc, 0));
		for (String[] row : data_cc) {
			chamCongView.getTableModel_cc().addRow(row);
		}
		chamCongView.getChamCongtable().setModel(chamCongView.getTableModel_cc());

		ArrayList<String[]> data_nv = new ArrayList<>();
		int size_nv = 7;

		for (int i = 0; i < arr_nv.size(); i++) {
			String[] tmp = new String[size_nv];
			tmp[0] = String.valueOf(arr_nv.get(i).getMaNV());
			tmp[1] = String.valueOf(arr_nv.get(i).getHoTen());
			tmp[2] = String.valueOf(arr_nv.get(i).getEmail());
			tmp[3] = String.valueOf(arr_nv.get(i).getDiaChi());
			tmp[4] = String.valueOf(arr_nv.get(i).getCccd());
			tmp[5] = String.valueOf(arr_nv.get(i).getCapBac());
			tmp[6] = String.valueOf(arr_nv.get(i).getPhongBan().getMaPB());
			data_nv.add(tmp);
		}

		String[] columnNames_nv = { "Mã NV", "Họ Tên", "Email", "Địa chỉ", "CCCD", "Cấp bậc", "Mã PB" };
		chamCongView.setTableModel_nv(new DefaultTableModel(columnNames_nv, 0));
		for (String[] row : data_nv) {
			chamCongView.getTableModel_nv().addRow(row);
		}
		// table.getTableHeader().addMouseListener(nhanVienController);
		chamCongView.getNvTable().setModel(chamCongView.getTableModel_nv());

	}

}
