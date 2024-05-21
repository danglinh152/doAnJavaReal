package controller;

import DAO.taiKhoanDAO;
import model.taiKhoan;

public class sessionController {
	private taiKhoan taiKhoan;

	public static taiKhoan taiKhoanHienTai(String tentk) {
		return taiKhoanDAO.getInstance().selectByTENTK(tentk);

	}
}
