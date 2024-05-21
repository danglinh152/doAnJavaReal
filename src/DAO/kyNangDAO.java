package DAO;

import model.kyNang;
import model.nhanVien_kyNang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.databaseConnection;

public class kyNangDAO implements DAOinterface<kyNang> {
	public static kyNangDAO getInstance() {
		return new kyNangDAO();
	}

	@Override
	public kyNang selectByID(kyNang t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<kyNang> selectAll() {
		ArrayList<kyNang> arr_kn = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM KYNANG";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maKN = rs.getInt("MAKN");
				String tenKyNang = rs.getString("TENKN");
				kyNang kn = new kyNang(maKN, tenKyNang);
				arr_kn.add(kn);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_kn;
	}

	@Override
	public int insertT(kyNang t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateT(kyNang t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteT(kyNang t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int seq_num() {
		// TODO Auto-generated method stub
		return 0;
	}

}
