package model;

public class chamCongClass {
	private int maNV;
	private String hoTen;
	private int thangLamViec;
	private int soNgayLamViec;
	private int soNgayNghi;
	private double soGioTangCa;
	private int soNgayDiTre;

	public int getMaNV() {
		return maNV;
	}

	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}

	public int getThangLamViec() {
		return thangLamViec;
	}

	public void setThangLamViec(int thangLamViec) {
		this.thangLamViec = thangLamViec;
	}

	public int getSoNgayLamViec() {
		return soNgayLamViec;
	}

	public void setSoNgayLamViec(int soNgayLamViec) {
		this.soNgayLamViec = soNgayLamViec;
	}

	public int getSoNgayNghi() {
		return soNgayNghi;
	}

	public void setSoNgayNghi(int soNgayNghi) {
		this.soNgayNghi = soNgayNghi;
	}

	public double getSoGioTangCa() {
		return soGioTangCa;
	}

	public void setSoGioTangCa(double soGioTangCa) {
		this.soGioTangCa = soGioTangCa;
	}

	public int getSoNgayDiTre() {
		return soNgayDiTre;
	}

	public void setSoNgayDiTre(int soNgayDiTre) {
		this.soNgayDiTre = soNgayDiTre;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public chamCongClass(int maNV, String hoTen, int thangLamViec, int soNgayLamViec, int soNgayNghi,
			double soGioTangCa, int soNgayDiTre) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.thangLamViec = thangLamViec;
		this.soNgayLamViec = soNgayLamViec;
		this.soNgayNghi = soNgayNghi;
		this.soGioTangCa = soGioTangCa;
		this.soNgayDiTre = soNgayDiTre;
	}

}
