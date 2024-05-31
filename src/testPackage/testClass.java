package testPackage;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import DAO.nhanVienDAO;
import DAO.phongBanDAO;
import model.nhanVien;
import model.phongBan;
import view.mainView;
import view.mainViewNhanVien;
import view.quenMatKhauView;
import view.trangChuView;
import controller.sendMail;

public class testClass extends Thread {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mainView mainView = new mainView();
				mainView.setVisible(true);
			}
		});
	}

}
