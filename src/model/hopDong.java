package model;

import java.sql.Date;

public class hopDong {
	private int maHD;
	private nhanVien nhanVien;
	private Date ngayBDHD;
	private Date ngayKTHD;

	public hopDong(int maHD, model.nhanVien nhanVien, Date ngayBDHD, Date ngayKTHD) {
		super();
		this.maHD = maHD;
		this.nhanVien = nhanVien;
		this.ngayBDHD = ngayBDHD;
		this.ngayKTHD = ngayKTHD;
	}

	public hopDong() {
		super();
	}

	public int getMaHD() {
		return maHD;
	}

	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}

	public nhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(nhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public Date getNgayBDHD() {
		return ngayBDHD;
	}

	public void setNgayBDHD(Date ngayBDHD) {
		this.ngayBDHD = ngayBDHD;
	}

	public Date getNgayKTHD() {
		return ngayKTHD;
	}

	public void setNgayKTHD(Date ngayKTHD) {
		this.ngayKTHD = ngayKTHD;
	}

}
