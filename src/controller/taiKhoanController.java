package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.spec.KeySpec;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.hopDongDAO;
import DAO.nhanVienDAO;
import DAO.phongBanDAO;
import DAO.taiKhoanDAO;
import model.hopDong;
import model.nhanVien;
import model.phongBan;
import model.taiKhoan;
import view.capNhatTaiKhoanView;
import view.errView;
import view.mainView;
import view.taiKhoanView;
import view.themTaiKhoanView;

public class taiKhoanController implements ActionListener, KeyListener {
	private taiKhoanView taiKhoanView;

	public taiKhoanController(taiKhoanView taiKhoanView) {
		this.taiKhoanView = taiKhoanView;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String txt = this.taiKhoanView.getTxtTnPhngBan().getText();
			if (txt.trim().equals("") || txt == null) {
				changeTableData(taiKhoanView.getTaiKhoanData());
			} else {
				ArrayList<taiKhoan> arr_tk = taiKhoanDAO.getInstance().selectByLike(txt);
				changeTableData(arr_tk);
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
		if (e.getActionCommand().equals("")) {
			String txt = this.taiKhoanView.getTxtTnPhngBan().getText();
			if (txt.trim().equals("") || txt == null) {
				changeTableData(taiKhoanView.getTaiKhoanData());
			} else {
				ArrayList<taiKhoan> arr_tk = taiKhoanDAO.getInstance().selectByLike(txt);
				changeTableData(arr_tk);
			}
		} else if (e.getActionCommand().equals("+ Thêm tài khoản")) {
			this.taiKhoanView.getMainView().dispose();
			new themTaiKhoanView();
		} else if (e.getActionCommand().equals("Cập nhật")) {
			this.taiKhoanView.getMainView().dispose();
			int selectedRow = this.taiKhoanView.getTable().getSelectedRow();
			Object[] rowData = new Object[this.taiKhoanView.getTable().getColumnCount()];
			for (int i = 0; i < this.taiKhoanView.getTable().getColumnCount(); i++) {
				rowData[i] = this.taiKhoanView.getTable().getValueAt(selectedRow, i);
			}
			int maTK = Integer.parseInt(String.valueOf(rowData[0]));
			int maNV = Integer.parseInt(String.valueOf(rowData[1]));
			nhanVien nhanVien = nhanVienDAO.getInstance()
					.selectByID(new nhanVien(maNV, "", "", null, "", "", "", "", "", null));
			String tenTK = String.valueOf(rowData[2]);
			String matKhau = String.valueOf(rowData[3]);
			String loaiTaiKhoan = String.valueOf(rowData[4]);
			taiKhoan taiKhoan = new taiKhoan(maTK, nhanVien, tenTK, matKhau, loaiTaiKhoan);
			new capNhatTaiKhoanView(taiKhoan);
		} else if (e.getActionCommand().equals("Xóa")) {
			try {
				errView errView = new errView();
				TableModel model = taiKhoanView.getTable().getModel();

				int maTK = Integer.parseInt((String) model.getValueAt(taiKhoanView.getTable().getSelectedRows()[0], 0));
				taiKhoan tk_tmp = new taiKhoan(maTK, null, "", "", "");
				taiKhoanDAO.getInstance().deleteT(tk_tmp);
				ArrayList<taiKhoan> taiKhoanData = taiKhoanDAO.getInstance().selectAll();
				taiKhoanView.setTaiKhoanData(taiKhoanData);
				changeTableData(taiKhoanView.getTaiKhoanData());
				errView.getLblNewLabel().setText("Xóa tài khoản thành công!");
				errView.setVisible(true);
			} catch (Exception e2) {
				errView errView = new errView();
				errView.getLblNewLabel().setText("Xóa tài khoản thất bại!");
				errView.setVisible(true);
			}
		}
	}

	public void changeTableData(ArrayList<taiKhoan> newArr) {
		ArrayList<String[]> data = new ArrayList<>();
		int size = 6;
		for (int i = 0; i < newArr.size(); i++) {
			String[] tmp = new String[size];
			tmp[0] = String.valueOf(newArr.get(i).getMaTK());
			tmp[1] = String.valueOf(newArr.get(i).getNhanVien().getMaNV());
			tmp[2] = String.valueOf(newArr.get(i).getNhanVien().getHoTen());
			tmp[3] = String.valueOf(newArr.get(i).getTenTK());
			tmp[4] = String.valueOf(newArr.get(i).getMatKhau());
			tmp[5] = String.valueOf(newArr.get(i).getLoaiTK());
			data.add(tmp);
		}

		// Tạo bảng và gắn dữ liệu vào
		String[] columnNames = { "Mã tài khoản", "Mã nhân viên", "Họ Tên", "Tên tài khoản", "Mật khẩu",
				"Loại tài khoản" };
		this.taiKhoanView.setTableModel(new DefaultTableModel(columnNames, 0));

		for (String[] row : data) {
			this.taiKhoanView.getTableModel().addRow(row);
		}

		// table.getTableHeader().addMouseListener(nhanVienController);
		this.taiKhoanView.getTable().setModel(this.taiKhoanView.getTableModel());
		this.taiKhoanView.getTable().setModel(this.taiKhoanView.getTableModel());

		this.taiKhoanView.getTable().setBounds(0, 0, 1, 1);

	}
}
