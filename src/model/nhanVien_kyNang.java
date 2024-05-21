package model;

public class nhanVien_kyNang {
	private int maKN;
	private int maNV;
	private String tenNV;
	private String tenKyNang;
	private String capBacKyNang;

	public int getMaNV() {
		return maNV;
	}

	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getTenKyNang() {
		return tenKyNang;
	}

	public void setTenKyNang(String tenKyNang) {
		this.tenKyNang = tenKyNang;
	}

	public String getCapBacKyNang() {
		return capBacKyNang;
	}

	public void setCapBacKyNang(String capBacKyNang) {
		this.capBacKyNang = capBacKyNang;
	}

	public int getMaKN() {
		return maKN;
	}

	public void setMaKN(int maKN) {
		this.maKN = maKN;
	}

	public nhanVien_kyNang(int maKN, int maNV, String tenNV, String tenKyNang, String capBacKyNang) {
		super();
		this.maKN = maKN;
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.tenKyNang = tenKyNang;
		this.capBacKyNang = capBacKyNang;
	}

}
