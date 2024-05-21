package model;

public class kyNang {
	private int maKN;
	private String tenKN;

	public kyNang(int maKN, String tenKN) {
		super();
		this.maKN = maKN;
		this.tenKN = tenKN;
	}

	public kyNang() {
		super();
	}

	public int getMaKN() {
		return maKN;
	}

	public void setMaKN(int maKN) {
		this.maKN = maKN;
	}

	public String getTenKN() {
		return tenKN;
	}

	public void setTenKN(String tenKN) {
		this.tenKN = tenKN;
	}

}
