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

	@Override
	public int insertT(yeuCau t) {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}

}
