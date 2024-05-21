package model;

public class taiKhoan {
	private int maTK;
	private nhanVien nhanVien;
	private String tenTK;
	private String matKhau;
	private String loaiTK;

	public taiKhoan(int maTK, model.nhanVien nhanVien, String tenTK, String matKhau, String loaiTK) {
		super();
		this.maTK = maTK;
		this.nhanVien = nhanVien;
		this.tenTK = tenTK;
		this.matKhau = matKhau;
		this.loaiTK = loaiTK;
	}

	public taiKhoan() {
		super();
	}

	public int getMaTK() {
		return maTK;
	}

	public void setMaTK(int maTK) {
		this.maTK = maTK;
	}

	public nhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(nhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public String getTenTK() {
		return tenTK;
	}

	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getLoaiTK() {
		return loaiTK;
	}

	public void setLoaiTK(String loaiTK) {
		this.loaiTK = loaiTK;
	}

}
