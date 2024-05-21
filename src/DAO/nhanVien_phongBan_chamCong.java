package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.databaseConnection;
import model.nhanVien_phongBan_chamCongClass;
import model.phongBan;

public class nhanVien_phongBan_chamCong {
	public static nhanVien_phongBan_chamCong getInstance() {
		return new nhanVien_phongBan_chamCong();
	}

	public ArrayList<nhanVien_phongBan_chamCongClass> selectByID() {

		ArrayList<nhanVien_phongBan_chamCongClass> arr_nhanVien_phongBan_chamCongClass = new ArrayList<>();

		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "select PB.MAPB, PB.TENPB, PB.MATRUONGPHONG, COUNT(*) AS TOTAL \r\n" + "FROM NHANVIEN NV\r\n"
					+ "INNER JOIN PHONGBAN PB ON PB.MAPB = NV.MAPB\r\n"
					+ "GROUP BY PB.MAPB, PB.TENPB, PB.MATRUONGPHONG ORDER BY PB.MAPB";
			PreparedStatement st = c.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			while (rs.next()) {

				nhanVien_phongBan_chamCongClass nhanVien_phongBan_chamCongClass = new nhanVien_phongBan_chamCongClass(
						rs.getInt("MAPB"), rs.getString("TENPB"), rs.getInt("MATRUONGPHONG"), rs.getInt("TOTAL"),
						phongBanDAO.getInstance().luongTrungBinh(rs.getInt("MAPB")));
				arr_nhanVien_phongBan_chamCongClass.add(nhanVien_phongBan_chamCongClass);
			}
			databaseConnection.closeDatabaseConnection(c);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_nhanVien_phongBan_chamCongClass;
	}
}
