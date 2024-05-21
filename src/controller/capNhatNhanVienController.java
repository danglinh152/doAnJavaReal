package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.capNhatNhanVienView;
import view.mainView;

public class capNhatNhanVienController implements ActionListener, KeyListener {
	private capNhatNhanVienView capNhatNhanVienView;

	public capNhatNhanVienController(capNhatNhanVienView capNhatNhanVienView) {
		this.capNhatNhanVienView = capNhatNhanVienView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Hủy")) {
			mainView mainView = new mainView();
			mainView.setTabNhanVien();
			this.capNhatNhanVienView.dispose();
			mainView.setVisible(true);
		}
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

}
