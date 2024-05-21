package model;

public class nhanVien_phongBan_chamCongClass {
	private int maPB;
	private String tenPB;
	private int maTP;
	private int tongSoNhanVien;
	private double mucLuongTrungBinh = 0.0;

	public int getMaPB() {
		return maPB;
	}

	public void setMaPB(int maPB) {
		this.maPB = maPB;
	}

	public String getTenPB() {
		return tenPB;
	}

	public void setTenPB(String tenPB) {
		this.tenPB = tenPB;
	}

	public int getMaTP() {
		return maTP;
	}

	public void setMaTP(int maTP) {
		this.maTP = maTP;
	}

	public int getTongSoNhanVien() {
		return tongSoNhanVien;
	}

	public void setTongSoNhanVien(int tongSoNhanVien) {
		this.tongSoNhanVien = tongSoNhanVien;
	}

	public double getMucLuongTrungBinh() {
		return mucLuongTrungBinh;
	}

	public void setMucLuongTrungBinh(double mucLuongTrungBinh) {
		this.mucLuongTrungBinh = mucLuongTrungBinh;
	}

	public nhanVien_phongBan_chamCongClass(int maPB, String tenPB, int maTP, int tongSoNhanVien,
			double mucLuongTrungBinh) {
		super();
		this.maPB = maPB;
		this.tenPB = tenPB;
		this.maTP = maTP;
		this.tongSoNhanVien = tongSoNhanVien;
		this.mucLuongTrungBinh = mucLuongTrungBinh;
	}

}
