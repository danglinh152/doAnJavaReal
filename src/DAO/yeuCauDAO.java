package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.databaseConnection;
import model.nhanVien;
import model.taiKhoan;
import model.yeuCau;

public class yeuCauDAO implements DAOinterface<yeuCau> {
	public static yeuCauDAO getInstance() {
		return new yeuCauDAO();
	}

	public ArrayList<yeuCau> selectByLike(String txt) {
		return null;

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
			databaseConnection.closeDatabaseConnection(c);
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
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_yc;
	}

	@Override
	public int insertT(yeuCau t) {
		int cnt = 0;
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "INSERT INTO YEUCAU VALUES (?, ?, ?, ?)";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, t.getMaYC());
			st.setInt(2, t.getMaNV());
			st.setString(3, t.getNoiDung());
			if (t.isTrangThai()) {
				st.setInt(4, 1);
			} else {
				st.setInt(4, 0);
			}
			st.execute();
			cnt = 1;
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateT(yeuCau t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteT(yeuCau t) {
		// TODO Auto-generated method stub
		return 0;
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
