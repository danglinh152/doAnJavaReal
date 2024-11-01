package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.databaseConnection;
import model.nhanVien;
import model.phongBan;
import model.taiKhoan;
import model.yeuCau;
import view.errView;

public class yeuCauDAO implements DAOinterface<yeuCau> {
	public static yeuCauDAO getInstance() {
		return new yeuCauDAO();
	}

	public ArrayList<yeuCau> selectSortByMAYCASC() {
		ArrayList<yeuCau> arr_yc = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM YEUCAU ORDER BY MAYC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maYC = rs.getInt(1);
				int maNV = rs.getInt(2);
				String noiDung = rs.getString(3);
				int trangThai_int = rs.getInt(4);
				boolean trangThai = false;
				if (trangThai_int == 1) {
					trangThai = true;
				} else {
					trangThai = false;
				}
				yeuCau yc = new yeuCau(maYC, maNV, noiDung, trangThai);
				arr_yc.add(yc);
			}
			// databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_yc;
	}

	public ArrayList<yeuCau> selectSortByMAYCDESC() {
		ArrayList<yeuCau> arr_yc = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM YEUCAU ORDER BY MAYC DESC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maYC = rs.getInt(1);
				int maNV = rs.getInt(2);
				String noiDung = rs.getString(3);
				int trangThai_int = rs.getInt(4);
				boolean trangThai = false;
				if (trangThai_int == 1) {
					trangThai = true;
				} else {
					trangThai = false;
				}
				yeuCau yc = new yeuCau(maYC, maNV, noiDung, trangThai);
				arr_yc.add(yc);
			}
			// databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_yc;
	}

	public ArrayList<yeuCau> selectByLike(String t) {
		ArrayList<yeuCau> yeuCauQuery = new ArrayList<>();
		try {
			t = t.toUpperCase();
			t = "%" + t + "%";

			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM YEUCAU WHERE UPPER(NOIDUNG) LIKE ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maYC = rs.getInt(1);
				int maNV = rs.getInt(2);
				String noiDung = rs.getString(3);
				int trangThai_int = rs.getInt(4);
				boolean trangThai = false;
				if (trangThai_int == 1) {
					trangThai = true;
				} else {
					trangThai = false;
				}
				yeuCau yc = new yeuCau(maYC, maNV, noiDung, trangThai);
				yeuCauQuery.add(yc);
			}
			// databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return yeuCauQuery;

	}

	@Override
	public yeuCau selectByID(yeuCau t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<yeuCau> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<yeuCau> arr_yc = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM YEUCAU ORDER BY MAYC ASC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maYC = rs.getInt(1);
				int maNV = rs.getInt(2);
				String noiDung = rs.getString(3);
				int trangThai_int = rs.getInt(4);
				boolean trangThai = false;
				if (trangThai_int == 1) {
					trangThai = true;
				} else {
					trangThai = false;
				}
				yeuCau yc = new yeuCau(maYC, maNV, noiDung, trangThai);
				arr_yc.add(yc);
			}
			// databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_yc;
	}

	public ArrayList<yeuCau> selectAll(int manv) {
		// TODO Auto-generated method stub
		ArrayList<yeuCau> arr_yc = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM YEUCAU WHERE MANV = ? ORDER BY MAYC ASC";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, manv);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maYC = rs.getInt(1);
				int maNV = rs.getInt(2);
				String noiDung = rs.getString(3);
				int trangThai_int = rs.getInt(4);
				boolean trangThai = false;
				if (trangThai_int == 1) {
					trangThai = true;
				} else {
					trangThai = false;
				}
				yeuCau yc = new yeuCau(maYC, maNV, noiDung, trangThai);
				arr_yc.add(yc);
			}
			// databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_yc;
	}

	public ArrayList<yeuCau> selectAllDESC(int manv) {
		// TODO Auto-generated method stub
		ArrayList<yeuCau> arr_yc = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM YEUCAU WHERE MANV = ? ORDER BY MAYC DESC";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, manv);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maYC = rs.getInt(1);
				int maNV = rs.getInt(2);
				String noiDung = rs.getString(3);
				int trangThai_int = rs.getInt(4);
				boolean trangThai = false;
				if (trangThai_int == 1) {
					trangThai = true;
				} else {
					trangThai = false;
				}
				yeuCau yc = new yeuCau(maYC, maNV, noiDung, trangThai);
				arr_yc.add(yc);
			}
			// databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_yc;
	}

	@Override
	public int insertT(yeuCau t) throws Exception {
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();
		c.setAutoCommit(false);
		String sql = "INSERT INTO YEUCAU VALUES (my_sequence_yeucau.NEXTVAL, ?, ?, ?)";
		PreparedStatement st = c.prepareStatement(sql);
		// st.setInt(1, t.getMaYC());
		st.setInt(1, t.getMaNV());
		st.setString(2, t.getNoiDung());
		st.setInt(3, 0);
		st.execute();
//		try {
//			Thread.sleep(15000); // Sleep for 15 seconds //demo non repeat
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		c.commit();
		databaseConnection.closeDatabaseConnection(c);

		return cnt;
	}

	@Override
	public int updateT(yeuCau t) throws Exception {
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

		String sql = "UPDATE YEUCAU SET TRANGTHAI = 1 WHERE MAYC = ?";
		PreparedStatement st = c.prepareStatement(sql);
		st.setInt(1, t.getMaYC());
		if (st.execute()) {
			cnt = 1;
		} else {
			cnt = 0;
		}
		// System.out.println(t.getMaNV() + " " + t.getMaYC());
//		String sql = " Begin quanly_pro(?); end; ";
//		PreparedStatement st = c.prepareStatement(sql);
//		st.setInt(1, t.getMaYC());
//		st.execute();
//		cnt = 1;

		databaseConnection.closeDatabaseConnection(c);

		return cnt;
	}

	public int updateTdemoNR(yeuCau t) throws Exception {
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

		String sql = " Begin pro_capnhatycnonrepeat(?); end;";
		PreparedStatement st = c.prepareStatement(sql);
		st.setInt(1, t.getMaYC());
		if (st.execute()) {
			cnt = 1;
		} else {
			cnt = 0;
		}
		// System.out.println(t.getMaNV() + " " + t.getMaYC());
//		String sql = " Begin quanly_pro(?); end; ";
//		PreparedStatement st = c.prepareStatement(sql);
//		st.setInt(1, t.getMaYC());
//		st.execute();
//		cnt = 1;

		databaseConnection.closeDatabaseConnection(c);

		return cnt;
	}

	// demo phantom read
	public int updateTdemophantom(yeuCau t) throws Exception {
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

//			String sql = "UPDATE YEUCAU SET TRANGTHAI = 1 WHERE MAYC = ?";
//			PreparedStatement st = c.prepareStatement(sql);
//			st.setInt(1, t.getMaYC());
//			if (st.execute()) {
//				cnt = 1;
//			} else {
//				cnt = 0;
//			}
		// System.out.println(t.getMaNV() + " " + t.getMaYC());
		String sql = " Begin pro_duyetYCPT(?); end; ";
		PreparedStatement st = c.prepareStatement(sql);
		st.setInt(1, t.getMaYC());
		st.execute();
		cnt = 1;

		databaseConnection.closeDatabaseConnection(c);

		return cnt;
	}

	public int updateAll() throws Exception {
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

//			String sql = "UPDATE YEUCAU SET TRANGTHAI = 1 WHERE MAYC = ?";
//			PreparedStatement st = c.prepareStatement(sql);
//			st.setInt(1, t.getMaYC());
//			if (st.execute()) {
//				cnt = 1;
//			} else {
//				cnt = 0;
//			}
		// System.out.println(t.getMaNV() + " " + t.getMaYC());
		String sql = "UPDATE YEUCAU SET TRANGTHAI = 1";
		PreparedStatement st = c.prepareStatement(sql);
		st.execute();
		cnt = 1;

		databaseConnection.closeDatabaseConnection(c);

		return cnt;
	}

	public int updateYeuCauNhanVien(yeuCau t) throws Exception {
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

		String sql = "UPDATE YEUCAU SET NOIDUNG = ?, TRANGTHAI = ? WHERE MAYC = ? and MANV = ?";
		PreparedStatement st = c.prepareStatement(sql);
		st.setString(1, t.getNoiDung());
		st.setInt(2, 0);
		st.setInt(3, t.getMaYC());
		st.setInt(4, t.getMaNV());
		st.execute();
		cnt = 1;

		databaseConnection.closeDatabaseConnection(c);

		return cnt;
	}

	// DEMO LOST UPDATE
	public int updateTdemoLU(yeuCau t) throws Exception {
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

//				String sql = "UPDATE YEUCAU SET TRANGTHAI = 1 WHERE MAYC = ?";
//				PreparedStatement st = c.prepareStatement(sql);
//				st.setInt(1, t.getMaYC());
//				if (st.execute()) {
//					cnt = 1;
//				} else {
//					cnt = 0;
//				}
		// System.out.println(t.getMaNV() + " " + t.getMaYC());
		String sql = " Begin pro_duyetYCLU(?); end; ";
		PreparedStatement st = c.prepareStatement(sql);
		st.setInt(1, t.getMaYC());
		st.execute();
		cnt = 1;

		databaseConnection.closeDatabaseConnection(c);

		return cnt;
	}

// DEMO LOST UPDATE
	public int updateYeuCauNhanViendemo(yeuCau t) throws Exception {
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

		String sql = " begin pro_capNhatYCLU(?, ?, ?); end;";
		PreparedStatement st = c.prepareStatement(sql);
		st.setInt(1, t.getMaNV());
		st.setInt(2, t.getMaYC());
		st.setString(3, t.getNoiDung());
		st.execute();
		cnt = 1;

		databaseConnection.closeDatabaseConnection(c);

		return cnt;
	}

	@Override
	public int deleteT(yeuCau t) throws Exception {
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

		String sql = "DELETE FROM YEUCAU WHERE MAYC = ?";
		PreparedStatement st = c.prepareStatement(sql);
		st.setInt(1, t.getMaYC());
		if (st.execute()) {
			cnt = 1;
		} else {
			cnt = 0;
		}

		databaseConnection.closeDatabaseConnection(c);

		return cnt;
	}

// demo phantom read	
	public int deleteTdemo(yeuCau t) throws Exception {
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

		String sql = "BEGIN pro_xoaYCPT(?); end;";
		PreparedStatement st = c.prepareStatement(sql);
		st.setInt(1, t.getMaYC());
		if (st.execute()) {
			cnt = 1;
		} else {
			cnt = 0;
		}

		databaseConnection.closeDatabaseConnection(c);

		return cnt;
	}

	@Override
	public int seq_num() {
		int cnt = 0;

		try {
			Connection c = databaseConnection.getDatabaseConnection();
			String sql = "SELECT * FROM YEUCAU";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				cnt += 1;
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cnt;
	}

}