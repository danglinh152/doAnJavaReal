package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.mainView;
import view.loginView;

public class logoutController implements ActionListener{
	private mainView QLNV;
	public logoutController(mainView QLNV) {
		// TODO Auto-gthenerated constructor stub
		this.QLNV = QLNV;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("ĐĂNG XUẤT")) {
			this.QLNV.dispose();
			new loginView();
		}
	}
	
}
