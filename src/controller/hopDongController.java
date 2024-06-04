package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.hopDongDAO;
import DAO.nhanVienDAO;
import DAO.phongBanDAO;
import model.hopDong;
import model.nhanVien;
import model.phongBan;
import view.capNhatHopDongView;
import view.capNhatPhongBanView;
import view.errView;
import view.hopDongView;
import view.themHopDongView;
import view.themPhongBanView;

public class hopDongController implements ActionListener, KeyListener {
	private hopDongView hopDongView;

	public hopDongController(hopDongView hopDongView) {
		this.hopDongView = hopDongView;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String txt = hopDongView.getTxtTnPhngBan().getText();

			if (txt.trim().equals("") || txt == null) {
				changeTableData(hopDongView.getHopDongData());
			} else {
				ArrayList<hopDong> arr_hd = hopDongDAO.getInstance().selectByLike(txt);
				changeTableData(arr_hd);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Xóa")) {
			try {
				TableModel model = this.hopDongView.getTable().getModel();

				int maHD = Integer
						.parseInt((String) model.getValueAt(this.hopDongView.getTable().getSelectedRows()[0], 0));
				hopDong hd_tmp = new hopDong(maHD, null, null, null);
				hopDongDAO.getInstance().deleteT(hd_tmp);
				ArrayList<hopDong> hopDongData = hopDongDAO.getInstance().selectSortByMAHDASC();
				hopDongView.setHopDongData(hopDongData);
				changeTableData(hopDongView.getHopDongData());
				errView errView = new errView();
				errView.setVisible(true);
				errView.getLblNewLabel().setText("Xóa thành công!");
			} catch (Exception e2) {
				// TODO: handle exception
				errView errView = new errView();
				errView.setVisible(true);
				errView.getLblNewLabel()
						.setText("Không thể xóa hợp đồng vì có nhân viên đang trực thuộc phòng ban này!");
			}

		} else if (e.getActionCommand().equals("Cập nhật")) {
			int selectedRow = this.hopDongView.getTable().getSelectedRow();
			Object[] rowData = new Object[this.hopDongView.getTable().getColumnCount()];
			for (int i = 0; i < this.hopDongView.getTable().getColumnCount(); i++) {
				rowData[i] = this.hopDongView.getTable().getValueAt(selectedRow, i);
			}
			int maHD = Integer.parseInt(String.valueOf(rowData[0]));
			int maNV = Integer.parseInt(String.valueOf(rowData[1]));
			nhanVien nhanVien = nhanVienDAO.getInstance()
					.selectByID(new nhanVien(maNV, "", "", null, "", "", "", "", "", null));
			Date ngayBDHD = Date.valueOf(String.valueOf(rowData[3]));
			Date ngayKTHD = Date.valueOf(String.valueOf(rowData[4]));
			hopDong hopDong = new hopDong(maHD, nhanVien, ngayBDHD, ngayKTHD);
			new capNhatHopDongView(hopDong);
			this.hopDongView.getMainView().dispose();
		} else if (e.getActionCommand().equals("")) {
			String txt = hopDongView.getTxtTnPhngBan().getText();

			if (txt.equals("") || txt == null) {
				changeTableData(hopDongView.getHopDongData());
			} else {
				ArrayList<hopDong> arr_hd = hopDongDAO.getInstance().selectByLike(txt);
				changeTableData(arr_hd);
			}
		} else if (e.getActionCommand().equals("+ Thêm hợp đồng")) {
			new themHopDongView();
			this.hopDongView.getMainView().dispose();
		} else if (e.getActionCommand().equals("Hợp đồng sắp tới hạn")) {
			ArrayList<hopDong> hopDongHetHanData = hopDongDAO.getInstance().selectAllHetHanHopDong();
			changeTableData(hopDongHetHanData);
		} else if (e.getActionCommand().equals("Gửi mail nhắc tới hạn HĐ")) {
			ArrayList<hopDong> hopDongHetHanData = hopDongDAO.getInstance().selectAllHetHanHopDong();
			for (int i = 0; i < hopDongHetHanData.size(); i++) {
				sendMail.sendHetHopDong(hopDongHetHanData.get(i).getNhanVien().getEmail());

			}
			errView errView = new errView();
			errView.getLblNewLabel().setText("Gửi mail nhắc thành công");
			errView.setVisible(true);
		}

	}

	public void changeTableData(ArrayList<hopDong> newArr) {
		ArrayList<String[]> data = new ArrayList<>();
		int size = 5;

		for (int i = 0; i < newArr.size(); i++) {
			String[] tmp = new String[size];
			tmp[0] = String.valueOf(newArr.get(i).getMaHD());
			tmp[1] = String.valueOf(newArr.get(i).getNhanVien().getMaNV());
			tmp[2] = String.valueOf(newArr.get(i).getNhanVien().getHoTen());
			tmp[3] = String.valueOf(newArr.get(i).getNgayBDHD());
			tmp[4] = String.valueOf(newArr.get(i).getNgayKTHD());
			data.add(tmp);
		}

		// Tạo bảng và gắn dữ liệu vào
		String[] columnNames = { "Mã hợp đồng", "Mã nhân viên", "Họ Tên", "Ngày bắt đầu hợp đồng",
				"Ngày kết thúc hợp đồng" };
		hopDongView.setTableModel(new DefaultTableModel(columnNames, 0));
		for (String[] row : data) {
			hopDongView.getTableModel().addRow(row);
		}

		hopDongView.getTable().setModel(hopDongView.getTableModel());
		this.hopDongView.getTable().setModel(this.hopDongView.getTableModel());

		this.hopDongView.getTable().setBounds(0, 0, 1, 1);
		this.hopDongView.getTable().getColumnModel().getColumn(0).setPreferredWidth(40);
		this.hopDongView.getTable().getColumnModel().getColumn(1).setPreferredWidth(40);
		this.hopDongView.getTable().getColumnModel().getColumn(2).setPreferredWidth(20);
	}
}
