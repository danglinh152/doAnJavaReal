package model;

public class yeuCau {
	private int maYC;
	private int maNV;
	private String noiDung;
	private boolean trangThai;

	public yeuCau(int maYC, int maNV, String noiDung, boolean trangThai) {
		super();
		this.maYC = maYC;
		this.maNV = maNV;
		this.noiDung = noiDung;
		this.trangThai = trangThai;
	}

	public int getMaYC() {
		return maYC;
	}

	public void setMaYC(int maYC) {
		this.maYC = maYC;
	}

	public int getMaNV() {
		return maNV;
	}

	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

}
