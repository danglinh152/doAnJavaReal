package controller;

import view.capNhatPhongBanView;
import view.phongBanView;
import view.themPhongBanView;

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

import DAO.phongBanDAO;
import model.phongBan;

public class phongBanController implements ActionListener, MouseListener, KeyListener {
	private phongBanView phongBanView;

	public phongBanController(phongBanView phongBanView) {
		this.phongBanView = phongBanView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		String thuocTinh = (String) this.phongBanView.getThuocTinhComboBox().getSelectedItem();
//		String thuTu = (String) this.phongBanView.getThuTuComboBox().getSelectedItem();
//		if (thuocTinh.equals("Mã Phòng Ban") && thuTu.equals("Tăng dần")) {
//			System.out.println(thuocTinh + " " + thuTu);
////			ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectSortByMAPB();
////			changeTableData(arr_pb);
//		} else if (thuocTinh.equals("Tên Phòng Ban") && thuTu.equals("Tăng dần")) {
////			ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectSortByTENPB();
////			changeTableData(arr_pb);
//			System.out.println(thuocTinh + " " + thuTu);
//		} else if (thuocTinh.equals("Ngày Thành Lập") && thuTu.equals("Tăng dần")) {
////			ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectSortByNGTHANHLAP();
////			changeTableData(arr_pb);
//			System.out.println(thuocTinh + " " + thuTu);
//		} else if (thuocTinh.equals("Mã Trưởng Phòng") && thuTu.equals("Tăng dần")) {
////			ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectSortByMATRUONGPHONG();
////			changeTableData(arr_pb);
//			System.out.println(thuocTinh + " " + thuTu);
//		} else if (thuocTinh.equals("Ngày Nhận Chức") && thuTu.equals("Tăng dần")) {
////			ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectSortByNGNHANCHUC();
////			changeTableData(arr_pb);
//			System.out.println(thuocTinh + " " + thuTu);
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
		if (e.getActionCommand().equals("Xóa")) {
			TableModel model = phongBanView.getTable().getModel();

			int maPB = Integer.parseInt((String) model.getValueAt(phongBanView.getTable().getSelectedRows()[0], 0));
			phongBan pb_tmp = new phongBan(maPB, "", null, 0, null);
			phongBanDAO.getInstance().deleteT(pb_tmp);
			ArrayList<phongBan> phongBanData = phongBanDAO.getInstance().selectSortByMAPBASC();
			phongBanView.setPhongBanData(phongBanData);
			changeTableData(phongBanView.getPhongBanData());

		} else if (e.getActionCommand().equals("Cập nhật")) {
			int selectedRow = this.phongBanView.getTable().getSelectedRow();
			Object[] rowData = new Object[this.phongBanView.getTable().getColumnCount()];
			for (int i = 0; i < this.phongBanView.getTable().getColumnCount(); i++) {
				rowData[i] = this.phongBanView.getTable().getValueAt(selectedRow, i);
			}
			int maPB = Integer.parseInt(String.valueOf(rowData[0]));
			String tenPB = String.valueOf(rowData[1]);
			Date ngThanhLap = Date.valueOf(String.valueOf(rowData[2]));
			int maTP;
			if (rowData[3] != null && !rowData[3].equals("Chưa Có")) {
				maTP = Integer.parseInt(String.valueOf(rowData[3]));
			} else {
				maTP = 0;
			}

			Date ngayNhanChuc = null;

			if (rowData[4] != null && !rowData[4].equals("Chưa Có")) {
				ngayNhanChuc = Date.valueOf(String.valueOf(rowData[4]));
			} else {
				ngayNhanChuc = null;
			}

			phongBan phongBan = new phongBan(maPB, tenPB, ngThanhLap, maTP, ngayNhanChuc);
			new capNhatPhongBanView(phongBan);
			this.phongBanView.getMainView().dispose();
		} else if (e.getActionCommand().equals("")) {
			String txt = phongBanView.getTxtTnPhngBan().getText();

			if (txt.equals("") || txt == null) {
				changeTableData(phongBanView.getPhongBanData());
			} else {
				ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectByLike(txt);
				changeTableData(arr_pb);
			}
		} else if (e.getActionCommand().equals("+ Thêm phòng ban")) {
			new themPhongBanView();
			this.phongBanView.getMainView().dispose();
		}

		String thuocTinh = (String) this.phongBanView.getThuocTinhComboBox().getSelectedItem();
		String thuTu = (String) this.phongBanView.getThuTuComboBox().getSelectedItem();
		if (thuocTinh.equals("Mã Phòng Ban") && thuTu.equals("Tăng dần")) {
			ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectSortByMAPBASC();
			changeTableData(arr_pb);
		} else if (thuocTinh.equals("Tên Phòng Ban") && thuTu.equals("Tăng dần")) {
			ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectSortByTENPBDESC();
			changeTableData(arr_pb);
		} else if (thuocTinh.equals("Ngày Thành Lập") && thuTu.equals("Tăng dần")) {
			ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectSortByNGTHANHLAPASC();
			changeTableData(arr_pb);
		} else if (thuocTinh.equals("Mã Trưởng Phòng") && thuTu.equals("Tăng dần")) {
			ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectSortByMATRUONGPHONGASC();
			changeTableData(arr_pb);
		} else if (thuocTinh.equals("Ngày Nhận Chức") && thuTu.equals("Tăng dần")) {
			ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectSortByNGNHANCHUCASC();
			changeTableData(arr_pb);
		} else if (thuocTinh.equals("Mã Phòng Ban") && thuTu.equals("Giảm dần")) {
			ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectSortByMAPBDESC();
			changeTableData(arr_pb);
		} else if (thuocTinh.equals("Tên Phòng Ban") && thuTu.equals("Giảm dần")) {
			ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectSortByTENPBDESC();
			changeTableData(arr_pb);
		} else if (thuocTinh.equals("Ngày Thành Lập") && thuTu.equals("Giảm dần")) {
			ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectSortByNGTHANHLAPDESC();
			changeTableData(arr_pb);
		} else if (thuocTinh.equals("Mã Trưởng Phòng") && thuTu.equals("Giảm dần")) {
			ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectSortByMATRUONGPHONGDESC();
			changeTableData(arr_pb);
		} else if (thuocTinh.equals("Ngày Nhận Chức") && thuTu.equals("Giảm dần")) {
			ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectSortByNGNHANCHUCDESC();
			changeTableData(arr_pb);
		}

		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String txt = phongBanView.getTxtTnPhngBan().getText();

			if (txt.trim().equals("") || txt == null) {
				changeTableData(phongBanView.getPhongBanData());
			} else {
				ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectByLike(txt);
				changeTableData(arr_pb);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void changeTableData(ArrayList<phongBan> newArr) {
		ArrayList<String[]> data = new ArrayList<>();
		int size = 5;
		String maTruongPhong = "";
		String ngNhanChuc = "Chưa Có";
		for (int i = 0; i < newArr.size(); i++) {
			String[] tmp = new String[size];
			tmp[0] = String.valueOf(newArr.get(i).getMaPB());
			tmp[1] = String.valueOf(newArr.get(i).getTenPB());
			tmp[2] = String.valueOf(newArr.get(i).getNgThanhLap());
			if (newArr.get(i).getMaTruongPhong() > 0) {
				maTruongPhong = String.valueOf(newArr.get(i).getMaTruongPhong());
			} else {
				maTruongPhong = "Chưa Có";
			}
			if (newArr.get(i).getNgNhanChuc() == null) {
				ngNhanChuc = "Chưa Có";
			} else if (newArr.get(i).getNgNhanChuc() != null) {
				ngNhanChuc = String.valueOf(newArr.get(i).getNgNhanChuc());
			}
			tmp[3] = maTruongPhong;
			tmp[4] = ngNhanChuc;
			data.add(tmp);
		}

		// Tạo bảng và gắn dữ liệu vào
		String[] columnNames = { "Mã Phòng Ban", "Tên Phòng Ban", "Ngày Thành Lập", "Mã Trưởng Phòng",
				"Ngày Nhận Chức" };
		phongBanView.setTableModel(new DefaultTableModel(columnNames, 0));
		for (String[] row : data) {
			phongBanView.getTableModel().addRow(row);
		}

		phongBanView.getTable().setModel(phongBanView.getTableModel());
	}

}
