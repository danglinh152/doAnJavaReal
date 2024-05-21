package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.mainViewNhanVien;
import view.loginView;

public class logoutNhanVienController implements ActionListener {
	private mainViewNhanVien mainViewNhanVien;

	public logoutNhanVienController(mainViewNhanVien mainViewNhanVien) {
		// TODO Auto-gthenerated constructor stub
		this.mainViewNhanVien = mainViewNhanVien;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("ĐĂNG XUẤT")) {
			this.mainViewNhanVien.dispose();
			new loginView();
		}
	}

}
