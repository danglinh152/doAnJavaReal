package controller;

import view.capNhatNhanVienView;
import view.capNhatPhongBanView;
import view.errView;
import view.nhanVienView;
import view.themNhanVienView;
import view.xetDuyetCapBacView;

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
import model.nhanVien;
import model.phongBan;

public class nhanVienController implements ActionListener, MouseListener, KeyListener {
	private nhanVienView nhanVienView;

	public nhanVienController(nhanVienView nhanVienView) {
		this.nhanVienView = nhanVienView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		int column = this.nhanVienView.getTable().columnAtPoint(e.getPoint());
//		String headerValue = (String) this.nhanVienView.getTable().getColumnModel().getColumn(column).getHeaderValue();
//		if (headerValue.equals("Mã PB")) {
//			ArrayList<nhanVien> arr_pb = nhanVienDAO.getInstance().selectSortByMAPB();
//			changeTableData(arr_pb);
//		} else if (headerValue.equals("Tên PB")) {
//			ArrayList<nhanVien> arr_pb = nhanVienDAO.getInstance().selectSortByTENPB();
//			changeTableData(arr_pb);
//		} else if (headerValue.equals("NgThanhLap")) {
//			ArrayList<nhanVien> arr_pb = nhanVienDAO.getInstance().selectSortByNGTHANHLAP();
//			changeTableData(arr_pb);
//		} else if (headerValue.equals("MaTruongPhong")) {
//			ArrayList<nhanVien> arr_pb = nhanVienDAO.getInstance().selectSortByMATRUONGPHONG();
//			changeTableData(arr_pb);
//		} else if (headerValue.equals("NgNhanChuc")) {
//			ArrayList<nhanVien> arr_pb = nhanVienDAO.getInstance().selectSortByNGNHANCHUC();
//			changeTableData(arr_pb);
//		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Xuất file excel")) {
			try {
				excelController ex = new excelController();
				ex.exportFileNV();
				errView errView = new errView();
				errView.getLblNewLabel().setText("Xuất file excel thành công!");
				errView.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				errView errView = new errView();
				errView.getLblNewLabel().setText("Xuất file excel thất bại!");
				errView.setVisible(true);

			}
		} else if (e.getActionCommand().equals("Xóa")) {
			TableModel model = nhanVienView.getTable().getModel();

			int maNV = Integer.parseInt((String) model.getValueAt(nhanVienView.getTable().getSelectedRows()[0], 0));
			nhanVien nv_tmp = new nhanVien(maNV, "", "", null, "", "", "", "", "", null);
			nhanVienDAO.getInstance().deleteT(nv_tmp);
			ArrayList<nhanVien> nhanVienData = nhanVienDAO.getInstance().selectSortByMANVASC();
			nhanVienView.setNhanVienData(nhanVienData);
			changeTableData(nhanVienView.getNhanVienData());
		} else if (e.getActionCommand().equals("")) {
			String txt = nhanVienView.getTxtTnPhngBan().getText();

			if (txt.equals("") || txt == null) {
				ArrayList<nhanVien> nhanVienData = nhanVienDAO.getInstance().selectSortByMANVASC();
				nhanVienView.setNhanVienData(nhanVienData);
				changeTableData(nhanVienView.getNhanVienData());
			} else {
				ArrayList<nhanVien> arr_pb = nhanVienDAO.getInstance().selectByLike(txt);
				changeTableData(arr_pb);
			}
		} else if (e.getActionCommand().equals("Cập nhật")) {
			int selectedRow = this.nhanVienView.getTable().getSelectedRow();
			Object[] rowData = new Object[this.nhanVienView.getTable().getColumnCount()];
			for (int i = 0; i < this.nhanVienView.getTable().getColumnCount(); i++) {
				rowData[i] = this.nhanVienView.getTable().getValueAt(selectedRow, i);
			}
			int maNV = Integer.parseInt(String.valueOf(rowData[0]));
			String tenNV = String.valueOf(rowData[1]);
			String gioiTinh = String.valueOf(rowData[2]);
			String ngaySinh = String.valueOf(rowData[3]);
			String sdt = String.valueOf(rowData[4]);
			String email = String.valueOf(rowData[5]);
			String diaChi = String.valueOf(rowData[6]);
			String cccd = String.valueOf(rowData[7]);
			String capBac = String.valueOf(rowData[8]);
			int maPB = Integer.parseInt(String.valueOf(rowData[9]));
			phongBan phongBan = phongBanDAO.getInstance().selectByID(new phongBan(maPB, "", null, 0, null));

			nhanVien nhanVien = new nhanVien(maNV, tenNV, gioiTinh, Date.valueOf(ngaySinh), sdt, email, diaChi, cccd,
					capBac, phongBan);
			new capNhatNhanVienView(nhanVien);
			this.nhanVienView.getMainView().dispose();
		} else if (e.getActionCommand().equals("+ Thêm nhân viên")) {
			new themNhanVienView();
			this.nhanVienView.getMainView().dispose();
		} else if (e.getActionCommand().equals("Xét duyệt cấp bậc")) {
			System.out.println("test");
			new xetDuyetCapBacView();
			this.nhanVienView.getMainView().dispose();
		}

		String thuocTinh = (String) this.nhanVienView.getThuocTinhComboBox().getSelectedItem();
		String thuTu = (String) this.nhanVienView.getThuTuComboBox().getSelectedItem();
		if (thuocTinh.equals("Mã NV") && thuTu.equals("Tăng dần")) {
			ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectSortByMANVASC();
			changeTableData(arr_nv);
		} else if (thuocTinh.equals("Họ Tên") && thuTu.equals("Tăng dần")) {
			ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectSortByHOTENASC();
			changeTableData(arr_nv);
		} else if (thuocTinh.equals("Giới tính") && thuTu.equals("Tăng dần")) {
			ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectSortByGIOITINHASC();
			changeTableData(arr_nv);
		} else if (thuocTinh.equals("Ngsinh") && thuTu.equals("Tăng dần")) {
			ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectSortByNGAYSINHASC();
			changeTableData(arr_nv);
		} else if (thuocTinh.equals("Cấp bậc") && thuTu.equals("Tăng dần")) {
			ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectSortByCAPBACASC();
			changeTableData(arr_nv);
		} else if (thuocTinh.equals("Mã PB") && thuTu.equals("Tăng dần")) {
			ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectSortByMAPBASC();
			changeTableData(arr_nv);
		} else if (thuocTinh.equals("Mã NV") && thuTu.equals("Giảm dần")) {
			ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectSortByMANVDESC();
			changeTableData(arr_nv);
		} else if (thuocTinh.equals("Họ Tên") && thuTu.equals("Giảm dần")) {
			ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectSortByHOTENDESC();
			changeTableData(arr_nv);
		} else if (thuocTinh.equals("Giới tính") && thuTu.equals("Giảm dần")) {
			ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectSortByGIOITINHDESC();
			changeTableData(arr_nv);
		} else if (thuocTinh.equals("Ngsinh") && thuTu.equals("Giảm dần")) {
			ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectSortByNGAYSINHDESC();
			changeTableData(arr_nv);
		} else if (thuocTinh.equals("Cấp bậc") && thuTu.equals("Giảm dần")) {
			ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectSortByCAPBACDESC();
			changeTableData(arr_nv);
		} else if (thuocTinh.equals("Mã PB") && thuTu.equals("Giảm dần")) {
			ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectSortByMAPBDESC();
			changeTableData(arr_nv);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String txt = nhanVienView.getTxtTnPhngBan().getText();

			if (txt.trim().equals("") || txt == null) {
				ArrayList<nhanVien> nhanVienData = nhanVienDAO.getInstance().selectSortByMANVASC();
				nhanVienView.setNhanVienData(nhanVienData);
				changeTableData(nhanVienView.getNhanVienData());
			} else {
				ArrayList<nhanVien> arr_pb = nhanVienDAO.getInstance().selectByLike(txt);
				changeTableData(arr_pb);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void changeTableData(ArrayList<nhanVien> newArr) {
		ArrayList<String[]> data = new ArrayList<>();
		int size = 10;

		for (int i = 0; i < newArr.size(); i++) {
			String[] tmp = new String[size];
			tmp[0] = String.valueOf(newArr.get(i).getMaNV());
			tmp[1] = String.valueOf(newArr.get(i).getHoTen());
			tmp[2] = String.valueOf(newArr.get(i).getGioiTinh());
			tmp[3] = String.valueOf(newArr.get(i).getNgSinh());
			tmp[4] = String.valueOf(newArr.get(i).getSoDienThoai());
			tmp[5] = String.valueOf(newArr.get(i).getEmail());
			tmp[6] = String.valueOf(newArr.get(i).getDiaChi());
			tmp[7] = String.valueOf(newArr.get(i).getCccd());
			tmp[8] = String.valueOf(newArr.get(i).getCapBac());
			tmp[9] = String.valueOf(newArr.get(i).getPhongBan().getMaPB());
			data.add(tmp);
		}

		// Tạo bảng và gắn dữ liệu vào
		String[] columnNames = { "Mã NV", "Họ Tên", "Giới tính", "Ngsinh", "SDT", "Email", "Địa chỉ", "CCCD", "Cấp bậc",
				"Mã PB" };
		nhanVienView.setTableModel(new DefaultTableModel(columnNames, 0));
		for (String[] row : data) {
			nhanVienView.getTableModel().addRow(row);
		}

		nhanVienView.getTable().setModel(nhanVienView.getTableModel());
	}

}
