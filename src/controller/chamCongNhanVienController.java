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
import view.chamCongViewNhanVien;
import view.errView;
import view.themChamCongView;

public class chamCongNhanVienController implements ActionListener, KeyListener {
	private chamCongViewNhanVien chamCongViewNhanVien;

	public chamCongNhanVienController(chamCongViewNhanVien chamCongViewNhanVien) {
		this.chamCongViewNhanVien = chamCongViewNhanVien;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		String tennv = chamCongViewNhanVien.getTenNVTF().getText();
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!tennv.equals("Nhập mã nhân viên") || !tennv.trim().equals("")) {
				ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectNVCCBYTENNV(tennv);
				ArrayList<chamCongClass> arr_cc = chamCongDAO.getInstance().selectByTENNV(tennv);

				changeTableData(arr_cc, arr_nv);
			} else if (tennv.equals("Nhập mã nhân viên") || tennv.trim().equals("")) {
				changeTableData(chamCongViewNhanVien.getChamCongData(), chamCongViewNhanVien.getNhanVienData());
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
			String tennv = chamCongViewNhanVien.getTenNVTF().getText();
			if (!tennv.equals("Nhập mã nhân viên") || !tennv.trim().equals("")) {
				ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectNVCCBYTENNV(tennv);
				ArrayList<chamCongClass> arr_cc = chamCongDAO.getInstance().selectByTENNV(tennv);

				changeTableData(arr_cc, arr_nv);
			} else if (tennv.equals("Nhập mã nhân viên") || tennv.trim().equals("")) {
				changeTableData(chamCongViewNhanVien.getChamCongData(), chamCongViewNhanVien.getNhanVienData());
			}
		}
	}

	public void changeTableData(ArrayList<chamCongClass> arr_cc, ArrayList<nhanVien> arr_nv) {
		ArrayList<String[]> data_cc = new ArrayList<>();
		int size_cc = 8;

		for (int i = 0; i < arr_cc.size(); i++) {
			String[] tmp = new String[size_cc];
			tmp[0] = String.valueOf(arr_cc.get(i).getMaCC());
			tmp[1] = String.valueOf(arr_cc.get(i).getMaNV());
			tmp[2] = String.valueOf(arr_cc.get(i).getThangLamViec());
			tmp[3] = String.valueOf(arr_cc.get(i).getSoNgayLamViec());
			tmp[4] = String.valueOf(arr_cc.get(i).getSoNgayNghi());
			tmp[5] = String.valueOf(arr_cc.get(i).getSoGioTangCa());
			tmp[6] = String.valueOf(arr_cc.get(i).getSoNgayDiTre());

			DecimalFormat df = new DecimalFormat("#,###");
			String result = df.format(nhanVienDAO.getInstance().tinhLuongNhanVien(arr_cc.get(i).getMaNV(),
					arr_cc.get(i).getThangLamViec()));
			if (result.equals("NaN")) {
				tmp[7] = "0";
			} else {
				tmp[7] = result;
			}
			data_cc.add(tmp);
		}

		String[] columnNames_cc = { "Mã chấm công", "Mã nhân viên", "Tháng làm việc", "Số ngày làm việc",
				"Số ngày nghỉ", "Số giờ tăng ca", "Số ngày đi trễ", "Tổng lương" };
		chamCongViewNhanVien.setTableModel_cc(new DefaultTableModel(columnNames_cc, 0));
		for (String[] row : data_cc) {
			chamCongViewNhanVien.getTableModel_cc().addRow(row);
		}
		chamCongViewNhanVien.getChamCongtable().setModel(chamCongViewNhanVien.getTableModel_cc());

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
		chamCongViewNhanVien.setTableModel_nv(new DefaultTableModel(columnNames_nv, 0));
		for (String[] row : data_nv) {
			chamCongViewNhanVien.getTableModel_nv().addRow(row);
		}
		// table.getTableHeader().addMouseListener(nhanVienController);
		chamCongViewNhanVien.getNvTable().setModel(chamCongViewNhanVien.getTableModel_nv());

	}

}
