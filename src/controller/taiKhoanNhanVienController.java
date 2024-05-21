package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.spec.KeySpec;
import java.sql.Date;
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
import view.capNhatNhanVienView;
import view.errView;
import view.mainView;
import view.taiKhoanViewNhanVien;
import view.capNhatThongTinViewNhanVien;

public class taiKhoanNhanVienController implements ActionListener, KeyListener {
	private taiKhoanViewNhanVien taiKhoanViewNhanVien;

	public taiKhoanNhanVienController(taiKhoanViewNhanVien taiKhoanViewNhanVien) {
		this.taiKhoanViewNhanVien = taiKhoanViewNhanVien;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Cập nhật")) {
			try {
				String tenNV = String.valueOf(this.taiKhoanViewNhanVien.getTextField().getText());
				String gioiTinh = String.valueOf(this.taiKhoanViewNhanVien.getTextField_1().getText());
				String ngaySinh = String.valueOf(this.taiKhoanViewNhanVien.getTextField_2().getText());
				String sdt = String.valueOf(this.taiKhoanViewNhanVien.getTextField_3().getText());
				String email = String.valueOf(this.taiKhoanViewNhanVien.getTextField_4().getText());
				String diaChi = String.valueOf(this.taiKhoanViewNhanVien.getTextField_5().getText());
				String cccd = String.valueOf(this.taiKhoanViewNhanVien.getTextField_6().getText());
				String capBac = String.valueOf(this.taiKhoanViewNhanVien.getTextField_7().getText());

				nhanVien nhanVien = new nhanVien(0, tenNV, gioiTinh, Date.valueOf(ngaySinh), sdt, email, diaChi, cccd,
						capBac, null);
				new capNhatThongTinViewNhanVien(this.taiKhoanViewNhanVien, nhanVien);
				this.taiKhoanViewNhanVien.getMainViewNhanVien().dispose();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

	}

}
