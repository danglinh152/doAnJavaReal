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

public class nhanVienNhanVienController implements ActionListener, MouseListener, KeyListener {
	private guiYeuCauViewNhanVien nhanVienViewNhanVien;

	public nhanVienNhanVienController(guiYeuCauViewNhanVien nhanVienViewNhanVien) {
		this.nhanVienViewNhanVien = nhanVienViewNhanVien;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		int column = this.nhanVienViewNhanVien.getTable().columnAtPoint(e.getPoint());
//		String headerValue = (String) this.nhanVienViewNhanVien.getTable().getColumnModel().getColumn(column).getHeaderValue();
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
		} else if (e.getActionCommand().equals("")) {
			String txt = nhanVienViewNhanVien.getTxtTnPhngBan().getText();

			if (txt.equals("") || txt == null) {
				changeTableData(nhanVienViewNhanVien.getYeuCauData());
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
		} else if (e.getActionCommand().equals("+ Thêm yêu cầu")) {
			new themYeuCauViewNhanVien(this.nhanVienViewNhanVien.getMainViewNhanVien().getTaiKhoanHienTai());
			this.nhanVienViewNhanVien.getMainViewNhanVien().dispose();
		} else if (e.getActionCommand().equals("Xóa")) {
			TableModel model = nhanVienViewNhanVien.getTable().getModel();

			int maYC = Integer
					.parseInt((String) model.getValueAt(nhanVienViewNhanVien.getTable().getSelectedRows()[0], 0));
			yeuCau yeuCau = new yeuCau(maYC, 0, "", false);
			yeuCauDAO.getInstance().deleteT(yeuCau);
			ArrayList<yeuCau> arr_yc = yeuCauDAO.getInstance().selectSortByMAYCASC();
			changeTableData(arr_yc);
		}
		String thuocTinh = (String) this.nhanVienViewNhanVien.getThuocTinhComboBox().getSelectedItem();
		String thuTu = (String) this.nhanVienViewNhanVien.getThuTuComboBox().getSelectedItem();
		if (thuocTinh.equals("Mã Yêu Cầu") && thuTu.equals("Tăng dần")) {
			ArrayList<yeuCau> arr_yc = yeuCauDAO.getInstance().selectSortByMAYCASC();
			changeTableData(arr_yc);
		} else if (thuocTinh.equals("Mã Yêu Cầu") && thuTu.equals("Giảm dần")) {
			ArrayList<yeuCau> arr_yc = yeuCauDAO.getInstance().selectSortByMAYCDESC();
			changeTableData(arr_yc);
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
			String txt = nhanVienViewNhanVien.getTxtTnPhngBan().getText();

			if (txt.trim().equals("") || txt == null) {
				changeTableData(nhanVienViewNhanVien.getYeuCauData());
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
		int size = 3;

		for (int i = 0; i < newArr.size(); i++) {
			String[] tmp = new String[size];
			tmp[0] = String.valueOf(newArr.get(i).getMaYC());
			tmp[1] = String.valueOf(newArr.get(i).getNoiDung());
			if (newArr.get(i).isTrangThai()) {
				tmp[2] = "Đã được duyệt";
			} else {
				tmp[2] = "Chưa được duyệt";
			}
			data.add(tmp);
		}

		// Tạo bảng và gắn dữ liệu vào
		String[] columnNames = { "Mã Yêu Cầu", "Nội Dung", "Trạng Thái" };
		nhanVienViewNhanVien.setTableModel(new DefaultTableModel(columnNames, 0));
		for (String[] row : data) {
			nhanVienViewNhanVien.getTableModel().addRow(row);
		}

		nhanVienViewNhanVien.getTable().setModel(nhanVienViewNhanVien.getTableModel());

		nhanVienViewNhanVien.getTable().getColumnModel().getColumn(1).setPreferredWidth(600);

		// Set custom renderer cho cột "Nội Dung"
		nhanVienViewNhanVien.getTable().getColumnModel().getColumn(1).setCellRenderer(new rendererTableGuiYC());

		// nhanVienViewNhanVien.getTable().setBounds(0, 0, 1, 1);
	}

}
