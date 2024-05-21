package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import DAO.taiKhoanDAO;
import model.taiKhoan;
import view.mainView;
import view.errView;
import view.errViewQMK;
import view.quenMatKhauView;

public class quenMatKhauController implements KeyListener, ActionListener {

	private quenMatKhauView quenMatKhauView;

	public quenMatKhauController(quenMatKhauView quenMatKhauView) {
		this.quenMatKhauView = quenMatKhauView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Xác nhận")) {
			try {
				sendMail se = new sendMail();
				String tenDangNhap = quenMatKhauView.getTextField().getText();

				taiKhoan tk = taiKhoanDAO.getInstance().selectByTENTK(tenDangNhap);
				String str = se.send(tk.getNhanVien().getEmail());
				errViewQMK errViewQMK = new errViewQMK(str, tk.getMatKhau(), this.quenMatKhauView);
				errViewQMK.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				errView errView = new errView();
				errView.getLblNewLabel().setText("Email của nhân viên không tồn tại!");
				errView.setVisible(true);
			}
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
			try {
				sendMail se = new sendMail();
				String tenDangNhap = quenMatKhauView.getTextField().getText();

				taiKhoan tk = taiKhoanDAO.getInstance().selectByTENTK(tenDangNhap);
				String str = se.send(tk.getNhanVien().getEmail());
				errViewQMK errViewQMK = new errViewQMK(str, tk.getMatKhau(), this.quenMatKhauView);
				errViewQMK.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				errView errView = new errView();
				errView.getLblNewLabel().setText("Email của nhân viên không tồn tại!");
				errView.setVisible(true);
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
