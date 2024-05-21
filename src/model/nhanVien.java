package model;

import java.sql.Date;

public class nhanVien {
	private int maNV;
	private String hoTen;
	private String gioiTinh;
	private Date ngSinh;
	private String soDienThoai;
	private String email;
	private String diaChi;
	private String cccd;
	private String capBac;
	private phongBan phongBan;

	public nhanVien(int maNV, String hoTen, String gioiTinh, Date ngSinh, String soDienThoai, String email,
			String diaChi, String cccd, String capBac, phongBan phongBan) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngSinh = ngSinh;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.diaChi = diaChi;
		this.cccd = cccd;
		this.capBac = capBac;
		this.phongBan = phongBan;
	}

	public nhanVien() {
		super();
	}

	public int getMaNV() {
		return maNV;
	}

	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgSinh() {
		return ngSinh;
	}

	public void setNgSinh(Date ngSinh) {
		this.ngSinh = ngSinh;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public String getCapBac() {
		return capBac;
	}

	public void setCapBac(String capBac) {
		this.capBac = capBac;
	}

	public phongBan getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(phongBan phongBan) {
		this.phongBan = phongBan;
	}

}
