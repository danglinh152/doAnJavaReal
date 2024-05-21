package model;

import java.sql.Date;

public class phongBan {
	private int maPB;
	private String tenPB;
	private Date ngThanhLap;
	private int maTruongPhong;
	private Date ngNhanChuc;

	public phongBan(int maPB, String tenPB, Date ngThanhLap, int maTruongPhong, Date ngNhanChuc) {
		super();
		this.maPB = maPB;
		this.tenPB = tenPB;
		this.ngThanhLap = ngThanhLap;
		this.maTruongPhong = maTruongPhong;
		this.ngNhanChuc = ngNhanChuc;
	}

	public phongBan() {
		super();
	}

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

	public Date getNgThanhLap() {
		return ngThanhLap;
	}

	public void setNgThanhLap(Date ngThanhLap) {
		this.ngThanhLap = ngThanhLap;
	}

	public int getMaTruongPhong() {
		return maTruongPhong;
	}

	public void setMaTruongPhong(int maTruongPhong) {
		this.maTruongPhong = maTruongPhong;
	}

	public Date getNgNhanChuc() {
		return ngNhanChuc;
	}

	public void setNgNhanChuc(Date ngNhanChuc) {
		this.ngNhanChuc = ngNhanChuc;
	}

}
