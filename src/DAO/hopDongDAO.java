package DAO;

import model.hopDong;
import model.nhanVien;
import model.phongBan;
import view.errView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.databaseConnection;

public class hopDongDAO implements DAOinterface<hopDong> {
	public static hopDongDAO getInstance() {
		return new hopDongDAO();
	}

	@Override
	public hopDong selectByID(hopDong t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<hopDong> selectAll() {
		ArrayList<hopDong> arr_hd = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM HOPDONG HD INNER JOIN NHANVIEN NV ON HD.MANV = NV.MANV ORDER BY HD.MAHD";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maHD = rs.getInt("MAHD");
				int maNV = rs.getInt("MANV");
				nhanVien nhanVien = nhanVienDAO.getInstance()
						.selectByID(new nhanVien(maNV, "", "", null, "", "", "", "", "", null));
				Date ngayBDHD = rs.getDate("NGAYBDHD");
				Date ngayKTHD = rs.getDate("NGAYKTHD");
				hopDong hd = new hopDong(maHD, nhanVien, ngayBDHD, ngayKTHD);
				arr_hd.add(hd);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_hd;
	}

	@Override
	public int insertT(hopDong t) {
		int cnt = 0;
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "INSERT INTO HOPDONG VALUES (my_sequence_hopdong.NEXTVAL, ?, ?, ?)";
			PreparedStatement st = c.prepareStatement(sql);
			// st.setInt(1, t.getMaHD());
			st.setInt(1, t.getNhanVien().getMaNV());
			st.setDate(2, t.getNgayBDHD());
			st.setDate(3, t.getNgayKTHD());
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
	public int updateT(hopDong t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateT(int manv, int mahd, Date ngbd, Date ngkt) {
		int cnt = 0;
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "UPDATE HOPDONG SET NGAYBDHD = ?, NGAYKTHD = ?" + "WHERE MANV = ? AND MAHD = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(3, manv);
			st.setInt(4, mahd);
			st.setDate(1, ngbd);
			st.setDate(2, ngkt);

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
	public int deleteT(hopDong t) {
		// TODO Auto-generated method stub
		int cnt = 0;
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "DELETE FROM HOPDONG WHERE MAHD = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, t.getMaHD());
			if (st.execute()) {
				cnt = 1;
			} else {
				cnt = 0;
			}

			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			errView errView = new errView();
//			errView.setVisible(true);
//			errView.getLblNewLabel().setText("Không thể xóa hợp đồng vì có nhân viên đang trực thuộc phòng ban này!");
		}
		return cnt;
	}

	@Override
	public int seq_num() {
		// TODO Auto-generated method stub
		int cnt = 0;

		try {
			Connection c = databaseConnection.getDatabaseConnection();
			String sql = "SELECT * FROM HOPDONG";
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

	public ArrayList<hopDong> selectByLike(String t) {
		ArrayList<hopDong> hopDongQuery = new ArrayList<>();
		try {
			t = t.toUpperCase();
			t = "%" + t + "%";

			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM HOPDONG HD INNER JOIN NHANVIEN NV ON HD.MANV = NV.MANV WHERE UPPER(NV.HOTEN) LIKE ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maHD = rs.getInt("MAHD");
				int maNV = rs.getInt("MANV");
				nhanVien nhanVien = nhanVienDAO.getInstance()
						.selectByID(new nhanVien(maNV, "", "", null, "", "", "", "", "", null));
				Date ngayBDHD = rs.getDate("NGAYBDHD");
				Date ngayKTHD = rs.getDate("NGAYKTHD");
				hopDong hd = new hopDong(maHD, nhanVien, ngayBDHD, ngayKTHD);
				hopDongQuery.add(hd);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hopDongQuery;
	}

	public ArrayList<hopDong> selectSortByMAHDASC() {
		ArrayList<hopDong> arr_hd = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM HOPDONG HD INNER JOIN NHANVIEN NV ON HD.MANV = NV.MANV ORDER BY HD.MAHD";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maHD = rs.getInt("MAHD");
				int maNV = rs.getInt("MANV");
				nhanVien nhanVien = nhanVienDAO.getInstance()
						.selectByID(new nhanVien(maNV, "", "", null, "", "", "", "", "", null));
				Date ngayBDHD = rs.getDate("NGAYBDHD");
				Date ngayKTHD = rs.getDate("NGAYKTHD");
				hopDong hd = new hopDong(maHD, nhanVien, ngayBDHD, ngayKTHD);
				arr_hd.add(hd);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_hd;
	}

}
