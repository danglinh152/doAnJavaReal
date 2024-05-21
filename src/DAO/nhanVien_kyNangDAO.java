package DAO;

import model.nhanVien;
import model.nhanVien_kyNang;
import model.phongBan;
import view.errView;
import view.mainView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.databaseConnection;

public class nhanVien_kyNangDAO implements DAOinterface<nhanVien_kyNang> {
	public static nhanVien_kyNangDAO getInstance() {
		return new nhanVien_kyNangDAO();
	}

	@Override
	public nhanVien_kyNang selectByID(nhanVien_kyNang t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<nhanVien_kyNang> selectAll() {
		ArrayList<nhanVien_kyNang> arr_nvkn = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT NV.MANV, KN.MAKN, NV.HOTEN, KN.TENKN, NVKN.CAPBAC FROM NHANVIEN NV\n"
					+ "INNER JOIN NHANVIEN_KYNANG NVKN\n" + "ON NV.MANV = NVKN.MANV\n" + "INNER JOIN KYNANG KN\n"
					+ "ON KN.MAKN = NVKN.MAKN\n" + "ORDER BY NV.MANV";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				int maKN = rs.getInt("MAKN");
				String tenNV = rs.getString("HOTEN");
				String tenKyNang = rs.getString("TENKN");
				String capBacKyNang = rs.getString("CAPBAC");
				nhanVien_kyNang nvkn = new nhanVien_kyNang(maKN, maNV, tenNV, tenKyNang, capBacKyNang);
				arr_nvkn.add(nvkn);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_nvkn;
	}

	@Override
	public int insertT(nhanVien_kyNang t) {
		int cnt = 0;
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "INSERT INTO nhanVien_kyNang VALUES (?, ?, ?)";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, t.getMaKN());
			st.setInt(2, t.getMaNV());
			st.setString(3, t.getCapBacKyNang());
			st.execute();
			cnt = 1;

			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		return cnt;
	}

	@Override
	public int updateT(nhanVien_kyNang t) {
		int cnt = 0;
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "UPDATE NHANVIEN_KYNANG SET CAPBAC = ?" + " WHERE MANV = ? AND MAKN = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getCapBacKyNang());
			st.setInt(2, t.getMaNV());
			st.setInt(3, t.getMaKN());

			st.execute();
			cnt = 1;

			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			errView errView = new errView();
//			errView.setVisible(true);
			// errView.getLblNewLabel().setText("Không thể xóa phòng ban vì có nhân viên
			// đang trực thuộc phòng ban này!");
		}
		return cnt;
	}

	@Override
	public int deleteT(nhanVien_kyNang t) {
		int cnt = 0;
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "DELETE FROM NHANVIEN_KYNANG WHERE MANV = ? AND MAKN = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, t.getMaNV());
			st.setInt(2, t.getMaKN());
			st.execute();
			cnt = 1;

			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		return cnt;
	}

	@Override
	public int seq_num() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int count_nv(int makn) {
		int cnt = 0;
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT KN.MAKN, COUNT(KN.MAKN) AS COUNT_KYNANG FROM NHANVIEN NV INNER JOIN NHANVIEN_KYNANG NVKN ON NV.MANV = NVKN.MANV INNER JOIN KYNANG KN ON KN.MAKN = NVKN.MAKN WHERE KN.MAKN = ? GROUP BY KN.MAKN ORDER BY COUNT_KYNANG DESC";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, makn);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				cnt = rs.getInt("COUNT_KYNANG");
			}

			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cnt;

	}

	public ArrayList<nhanVien_kyNang> selectByLike(String t) {
		ArrayList<nhanVien_kyNang> nvknQuery = new ArrayList<>();
		try {
			t = t.toUpperCase();
			t = "%" + t + "%";

			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT KN.MAKN, NV.MANV, NV.HOTEN, KN.TENKN, NVKN.CAPBAC FROM NHANVIEN NV INNER JOIN NHANVIEN_KYNANG NVKN ON NV.MANV = NVKN.MANV INNER JOIN KYNANG KN ON KN.MAKN = NVKN.MAKN WHERE UPPER(HOTEN) LIKE ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maKN = rs.getInt("MAKN");
				int maNV = rs.getInt("MANV");
				String tenNV = rs.getString("HOTEN");
				String tenKyNang = rs.getString("TENKN");
				String capBacKyNang = rs.getString("CAPBAC");
				nhanVien_kyNang nvkn = new nhanVien_kyNang(maKN, maNV, tenNV, tenKyNang, capBacKyNang);
				nvknQuery.add(nvkn);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nvknQuery;
	}

}
