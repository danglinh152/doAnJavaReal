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
import DAO.nhanVien_kyNangDAO;
import DAO.phongBanDAO;
import model.hopDong;
import model.nhanVien_kyNang;
import model.phongBan;
import view.capNhatPhongBanView;
import view.capNhatTrinhDoView;
import view.errView;
import view.mainView;
import view.themPhongBanView;
import view.themTrinhDoView;
import view.trinhDoView;

public class trinhDoController implements ActionListener, KeyListener {
	private trinhDoView trinhDoView;

	public trinhDoController(trinhDoView trinhDoView) {
		this.trinhDoView = trinhDoView;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String txt = trinhDoView.getTxtTnPhngBan().getText();

			if (txt.trim().equals("") || txt == null) {
				changeTableData(trinhDoView.getData_nvkn());
			} else {
				ArrayList<nhanVien_kyNang> arr_nvkn = nhanVien_kyNangDAO.getInstance().selectByLike(txt);
				changeTableData(arr_nvkn);
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
		if (e.getActionCommand().equals("+ Thêm kỹ năng cho NV")) {
			this.trinhDoView.getMainView().dispose();
			new themTrinhDoView(this.trinhDoView);
		} else if (e.getActionCommand().equals("")) {
			String txt = trinhDoView.getTxtTnPhngBan().getText();

			if (txt.equals("") || txt == null) {
				changeTableData(trinhDoView.getData_nvkn());
			} else {
				ArrayList<nhanVien_kyNang> arr_nvkn = nhanVien_kyNangDAO.getInstance().selectByLike(txt);
				changeTableData(arr_nvkn);
			}
		} else if (e.getActionCommand().equals("Xóa")) {
			try {
				TableModel model = this.trinhDoView.getTable().getModel();

				int maNV = Integer.parseInt((String) model.getValueAt(trinhDoView.getTable().getSelectedRows()[0], 0));

				String tenKN = (String) model.getValueAt(trinhDoView.getTable().getSelectedRows()[0], 2);
				int maKN = 0;
				if (tenKN.equals("tin học")) {
					maKN = 1;
				} else if (tenKN.equals("ngoại ngữ")) {
					maKN = 2;
				}
				nhanVien_kyNang nvkn = new nhanVien_kyNang(maKN, maNV, "", "", "");
				nhanVien_kyNangDAO.getInstance().deleteT(nvkn);

				ArrayList<nhanVien_kyNang> nhanVien_kyNangData = nhanVien_kyNangDAO.getInstance().selectAll();
				trinhDoView.setData_nvkn(nhanVien_kyNangData);
				changeTableData(trinhDoView.getData_nvkn());

				mainView mainView = new mainView();
				mainView.setTabTrinhDo();
				this.trinhDoView.getMainView().dispose();
				errView errView = new errView();
				errView.setVisible(true);
				errView.getLblNewLabel().setText("Xóa thành công!");
				if (!errView.isDisplayable()) {
					mainView.setVisible(true);
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				errView errView = new errView();
				errView.setVisible(true);
				errView.getLblNewLabel().setText("Xóa thất bại!");
			}
		} else if (e.getActionCommand().equals("Cập nhật")) {
			int selectedRow = this.trinhDoView.getTable().getSelectedRow();
			Object[] rowData = new Object[this.trinhDoView.getTable().getColumnCount()];
			for (int i = 0; i < this.trinhDoView.getTable().getColumnCount(); i++) {
				rowData[i] = this.trinhDoView.getTable().getValueAt(selectedRow, i);
			}
			int maKN = 0;
			int maNV = Integer.parseInt(String.valueOf(rowData[0]));
			String tenNV = String.valueOf(rowData[1]);
			String tenKyNang = String.valueOf(rowData[2]);
			String capBacKyNang = String.valueOf(rowData[3]);
			if (tenKyNang.equals("tin học")) {
				maKN = 1;
			} else if (tenKyNang.equals("ngoại ngữ")) {
				maKN = 2;
			}

			nhanVien_kyNang nhanVien_kyNang = new nhanVien_kyNang(maKN, maNV, tenNV, tenKyNang, capBacKyNang);
			new capNhatTrinhDoView(nhanVien_kyNang);
			this.trinhDoView.getMainView().dispose();
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
		trinhDoView.setTableModel(new DefaultTableModel(columnNames, 0));
		for (String[] row : data) {
			trinhDoView.getTableModel().addRow(row);
		}

		trinhDoView.getTable().setModel(trinhDoView.getTableModel());
	}

}
