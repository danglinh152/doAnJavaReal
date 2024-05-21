package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.databaseConnection;
import model.chamCongClass;
import model.nhanVien;
import model.phongBan;
import view.errView;

public class chamCongDAO implements DAOinterface<chamCongClass> {
	public static chamCongDAO getInstance() {
		return new chamCongDAO();
	}

	@Override
	public chamCongClass selectByID(chamCongClass t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<chamCongClass> selectAll() {
		ArrayList<chamCongClass> chamCongQuery = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM CHAMCONG CC INNER JOIN NHANVIEN NV ON CC.MANV = NV.MANV ORDER BY CC.MACC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maCC = rs.getInt("MACC");
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				int thangLamViec = rs.getInt("THANGLAMVIEC");
				int soNgayLamViec = rs.getInt("SONGAYLAMVIEC");
				int soNgayNghi = rs.getInt("SONGAYNGHI");
				double soGioTangCa = rs.getInt("SOGIOTANGCA");
				int soNgayDiTre = rs.getInt("SONGAYDITRE");
				chamCongClass chamCongClass = new chamCongClass(maCC, maNV, hoTen, thangLamViec, soNgayLamViec,
						soNgayNghi, soGioTangCa, soNgayDiTre);
				chamCongQuery.add(chamCongClass);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chamCongQuery;
	}

	public ArrayList<chamCongClass> selectByMANV(int manv) {
		ArrayList<chamCongClass> chamCongQuery = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM CHAMCONG CC INNER JOIN NHANVIEN NV ON CC.MANV = NV.MANV WHERE NV.MANV = ? ORDER BY CC.MACC";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, manv);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maCC = rs.getInt("MACC");
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				int thangLamViec = rs.getInt("THANGLAMVIEC");
				int soNgayLamViec = rs.getInt("SONGAYLAMVIEC");
				int soNgayNghi = rs.getInt("SONGAYNGHI");
				double soGioTangCa = rs.getInt("SOGIOTANGCA");
				int soNgayDiTre = rs.getInt("SONGAYDITRE");
				chamCongClass chamCongClass = new chamCongClass(maCC, maNV, hoTen, thangLamViec, soNgayLamViec,
						soNgayNghi, soGioTangCa, soNgayDiTre);
				chamCongQuery.add(chamCongClass);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chamCongQuery;
	}

	public ArrayList<chamCongClass> selectByTENNV(String t) {
		ArrayList<chamCongClass> chamCongQuery = new ArrayList<>();
		t = t.toUpperCase();
		t = "%" + t + "%";
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM CHAMCONG CC INNER JOIN NHANVIEN NV ON CC.MANV = NV.MANV WHERE UPPER(HOTEN) LIKE ? ORDER BY MACC";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maCC = rs.getInt("MACC");
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				int thangLamViec = rs.getInt("THANGLAMVIEC");
				int soNgayLamViec = rs.getInt("SONGAYLAMVIEC");
				int soNgayNghi = rs.getInt("SONGAYNGHI");
				double soGioTangCa = rs.getInt("SOGIOTANGCA");
				int soNgayDiTre = rs.getInt("SONGAYDITRE");
				chamCongClass chamCongClass = new chamCongClass(maCC, maNV, hoTen, thangLamViec, soNgayLamViec,
						soNgayNghi, soGioTangCa, soNgayDiTre);
				chamCongQuery.add(chamCongClass);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chamCongQuery;
	}

	@Override
	public int insertT(chamCongClass t) {
		int cnt = 0;
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "INSERT INTO CHAMCONG VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, t.getMaCC());
			st.setInt(2, t.getMaNV());
			st.setInt(3, t.getThangLamViec());
			st.setInt(4, t.getSoNgayLamViec());
			st.setInt(5, t.getSoNgayNghi());
			st.setDouble(6, t.getSoGioTangCa());
			st.setInt(7, t.getSoNgayDiTre());
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
	public int updateT(chamCongClass t) {
		int cnt = 0;
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "UPDATE CHAMCONG SET THANGLAMVIEC = ?, SONGAYLAMVIEC = ?, SONGAYNGHI = ?, SOGIOTANGCA = ?, SONGAYDITRE = ?"
					+ "WHERE MACC = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, t.getThangLamViec());
			st.setInt(2, t.getSoNgayLamViec());
			st.setInt(3, t.getSoNgayNghi());
			st.setDouble(4, t.getSoGioTangCa());
			st.setInt(5, t.getSoNgayDiTre());
			st.setInt(6, t.getMaCC());
			st.execute();
			cnt = 1;

			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			// errView.getLblNewLabel().setText("Không thể xóa phòng ban vì có nhân viên
			// đang trực thuộc phòng ban này!");
		}
		return cnt;
	}

	public int deleteT(int macc) {
		int cnt = 0;
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "DELETE FROM CHAMCONG WHERE MACC = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, macc);
			if (st.execute()) {
				cnt = 1;
			} else {
				cnt = 0;
			}

			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			errView errView = new errView();
			errView.setVisible(true);
			errView.getLblNewLabel().setText("Không thể xóa chấm công này!");
		}
		return cnt;
	}

	@Override
	public int seq_num() {
		// TODO Auto-generated method stub
		int cnt = 0;

		try {
			Connection c = databaseConnection.getDatabaseConnection();
			String sql = "SELECT * FROM CHAMCONG";
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

	@Override
	public int deleteT(chamCongClass t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
