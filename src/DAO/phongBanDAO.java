package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.databaseConnection;
import model.nhanVien;
import model.phongBan;
import view.errView;

public class phongBanDAO implements DAOinterface<phongBan> {
	public static phongBanDAO getInstance() {
		return new phongBanDAO();
	}

	public phongBan selectMaPBByTenPB(String tenPBcantim) {
		phongBan pb = null;
		tenPBcantim = tenPBcantim.toUpperCase();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM PHONGBAN WHERE UPPER(TENPB) = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, tenPBcantim);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int maPB = rs.getInt("MAPB");
				String tenPB = rs.getString("TENPB");
				Date ngThanhLap = rs.getDate("NGTHANHLAP");
				int maTruongPhong = rs.getInt("MATRUONGPHONG");
				Date ngNhanChuc = rs.getDate("NGAYNHANCHUC");
				pb = new phongBan(maPB, tenPB, ngThanhLap, maTruongPhong, ngNhanChuc);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pb;
	}

	public ArrayList<phongBan> selectSortByMAPBASC() {
		ArrayList<phongBan> arr_pb = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM PHONGBAN ORDER BY MAPB";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maPB = rs.getInt("MAPB");
				String tenPB = rs.getString("TENPB");
				Date ngThanhLap = rs.getDate("NGTHANHLAP");
				int maTruongPhong = rs.getInt("MATRUONGPHONG");
				Date ngNhanChuc = rs.getDate("NGAYNHANCHUC");
				phongBan pb = new phongBan(maPB, tenPB, ngThanhLap, maTruongPhong, ngNhanChuc);
				arr_pb.add(pb);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_pb;
	}

	public ArrayList<phongBan> selectSortByMAPBDESC() {
		ArrayList<phongBan> arr_pb = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM PHONGBAN ORDER BY MAPB DESC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maPB = rs.getInt("MAPB");
				String tenPB = rs.getString("TENPB");
				Date ngThanhLap = rs.getDate("NGTHANHLAP");
				int maTruongPhong = rs.getInt("MATRUONGPHONG");
				Date ngNhanChuc = rs.getDate("NGAYNHANCHUC");
				phongBan pb = new phongBan(maPB, tenPB, ngThanhLap, maTruongPhong, ngNhanChuc);
				arr_pb.add(pb);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_pb;
	}

	public ArrayList<phongBan> selectSortByTENPBASC() {
		ArrayList<phongBan> arr_pb = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM PHONGBAN ORDER BY TENPB";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maPB = rs.getInt("MAPB");
				String tenPB = rs.getString("TENPB");
				Date ngThanhLap = rs.getDate("NGTHANHLAP");
				int maTruongPhong = rs.getInt("MATRUONGPHONG");
				Date ngNhanChuc = rs.getDate("NGAYNHANCHUC");
				phongBan pb = new phongBan(maPB, tenPB, ngThanhLap, maTruongPhong, ngNhanChuc);
				arr_pb.add(pb);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_pb;
	}

	public ArrayList<phongBan> selectSortByTENPBDESC() {
		ArrayList<phongBan> arr_pb = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM PHONGBAN ORDER BY TENPB DESC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maPB = rs.getInt("MAPB");
				String tenPB = rs.getString("TENPB");
				Date ngThanhLap = rs.getDate("NGTHANHLAP");
				int maTruongPhong = rs.getInt("MATRUONGPHONG");
				Date ngNhanChuc = rs.getDate("NGAYNHANCHUC");
				phongBan pb = new phongBan(maPB, tenPB, ngThanhLap, maTruongPhong, ngNhanChuc);
				arr_pb.add(pb);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_pb;
	}

	public ArrayList<phongBan> selectSortByNGTHANHLAPASC() {
		ArrayList<phongBan> arr_pb = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM PHONGBAN ORDER BY NGTHANHLAP";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maPB = rs.getInt("MAPB");
				String tenPB = rs.getString("TENPB");
				Date ngThanhLap = rs.getDate("NGTHANHLAP");
				int maTruongPhong = rs.getInt("MATRUONGPHONG");
				Date ngNhanChuc = rs.getDate("NGAYNHANCHUC");
				phongBan pb = new phongBan(maPB, tenPB, ngThanhLap, maTruongPhong, ngNhanChuc);
				arr_pb.add(pb);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_pb;
	}

	public ArrayList<phongBan> selectSortByNGTHANHLAPDESC() {
		ArrayList<phongBan> arr_pb = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM PHONGBAN ORDER BY NGTHANHLAP DESC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maPB = rs.getInt("MAPB");
				String tenPB = rs.getString("TENPB");
				Date ngThanhLap = rs.getDate("NGTHANHLAP");
				int maTruongPhong = rs.getInt("MATRUONGPHONG");
				Date ngNhanChuc = rs.getDate("NGAYNHANCHUC");
				phongBan pb = new phongBan(maPB, tenPB, ngThanhLap, maTruongPhong, ngNhanChuc);
				arr_pb.add(pb);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_pb;
	}

	public ArrayList<phongBan> selectSortByMATRUONGPHONGASC() {
		ArrayList<phongBan> arr_pb = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM PHONGBAN ORDER BY MATRUONGPHONG";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maPB = rs.getInt("MAPB");
				String tenPB = rs.getString("TENPB");
				Date ngThanhLap = rs.getDate("NGTHANHLAP");
				int maTruongPhong = rs.getInt("MATRUONGPHONG");
				Date ngNhanChuc = rs.getDate("NGAYNHANCHUC");
				phongBan pb = new phongBan(maPB, tenPB, ngThanhLap, maTruongPhong, ngNhanChuc);
				arr_pb.add(pb);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_pb;
	}

	public ArrayList<phongBan> selectSortByMATRUONGPHONGDESC() {
		ArrayList<phongBan> arr_pb = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM PHONGBAN ORDER BY MATRUONGPHONG DESC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maPB = rs.getInt("MAPB");
				String tenPB = rs.getString("TENPB");
				Date ngThanhLap = rs.getDate("NGTHANHLAP");
				int maTruongPhong = rs.getInt("MATRUONGPHONG");
				Date ngNhanChuc = rs.getDate("NGAYNHANCHUC");
				phongBan pb = new phongBan(maPB, tenPB, ngThanhLap, maTruongPhong, ngNhanChuc);
				arr_pb.add(pb);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_pb;
	}

	public ArrayList<phongBan> selectSortByNGNHANCHUCASC() {
		ArrayList<phongBan> arr_pb = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM PHONGBAN ORDER BY NGAYNHANCHUC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maPB = rs.getInt("MAPB");
				String tenPB = rs.getString("TENPB");
				Date ngThanhLap = rs.getDate("NGTHANHLAP");
				int maTruongPhong = rs.getInt("MATRUONGPHONG");
				Date ngNhanChuc = rs.getDate("NGAYNHANCHUC");
				phongBan pb = new phongBan(maPB, tenPB, ngThanhLap, maTruongPhong, ngNhanChuc);
				arr_pb.add(pb);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_pb;
	}

	public ArrayList<phongBan> selectSortByNGNHANCHUCDESC() {
		ArrayList<phongBan> arr_pb = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM PHONGBAN ORDER BY NGAYNHANCHUC DESC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maPB = rs.getInt("MAPB");
				String tenPB = rs.getString("TENPB");
				Date ngThanhLap = rs.getDate("NGTHANHLAP");
				int maTruongPhong = rs.getInt("MATRUONGPHONG");
				Date ngNhanChuc = rs.getDate("NGAYNHANCHUC");
				phongBan pb = new phongBan(maPB, tenPB, ngThanhLap, maTruongPhong, ngNhanChuc);
				arr_pb.add(pb);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_pb;
	}

	@Override
	public phongBan selectByID(phongBan t) {
		// TODO Auto-generated method stub
		phongBan pb = null;

		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM PHONGBAN WHERE MAPB = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, t.getMaPB());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int maPB = rs.getInt("MAPB");
				String tenPB = rs.getString("TENPB");
				Date ngThanhLap = rs.getDate("NGTHANHLAP");
				int maTruongPhong = rs.getInt("MATRUONGPHONG");
				Date ngNhanChuc = rs.getDate("NGAYNHANCHUC");
				pb = new phongBan(maPB, tenPB, ngThanhLap, maTruongPhong, ngNhanChuc);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pb;
	}

	public ArrayList<phongBan> selectByLike(String t) {
		ArrayList<phongBan> phongBanQuery = new ArrayList<>();
		try {
			t = t.toUpperCase();
			t = "%" + t + "%";

			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM PHONGBAN WHERE UPPER(TENPB) LIKE ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maPB = rs.getInt("MAPB");
				String tenPB = rs.getString("TENPB");
				Date ngThanhLap = rs.getDate("NGTHANHLAP");
				int maNV = rs.getInt("MATRUONGPHONG");
				nhanVien nvQuery = nhanVienDAO.getInstance()
						.selectByID(new nhanVien(maNV, "", "", null, "", "", "", "", "", null));
				int maTruongPhong = rs.getInt("MATRUONGPHONG");
				Date ngNhanChuc = rs.getDate("NGAYNHANCHUC");
				phongBan pb = new phongBan(maPB, tenPB, ngThanhLap, maTruongPhong, ngNhanChuc);
				phongBanQuery.add(pb);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phongBanQuery;
	}

	@Override
	public ArrayList<phongBan> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<phongBan> arr_pb = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM PHONGBAN ORDER BY MAPB ASC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maPB = rs.getInt("MAPB");
				String tenPB = rs.getString("TENPB");
				Date ngThanhLap = rs.getDate("NGTHANHLAP");
				int maNV = rs.getInt("MATRUONGPHONG");
				nhanVien nvQuery = nhanVienDAO.getInstance()
						.selectByID(new nhanVien(maNV, "", "", null, "", "", "", "", "", null));
				int maTruongPhong = rs.getInt("MATRUONGPHONG");
				Date ngNhanChuc = rs.getDate("NGAYNHANCHUC");
				phongBan pb = new phongBan(maPB, tenPB, ngThanhLap, maTruongPhong, ngNhanChuc);
				arr_pb.add(pb);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_pb;
	}

	@Override
	public int insertT(phongBan t) throws Exception {
		// TODO Auto-generated method stub
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

		String sql = "INSERT INTO PHONGBAN VALUES (my_sequence_phongban.NEXTVAL, ?, ?, ?, ?)";
		PreparedStatement st = c.prepareStatement(sql);
		if (t.getMaTruongPhong() == 0 || t.getNgNhanChuc() == null) {
			// st.setInt(1, t.getMaPB());
			st.setString(1, t.getTenPB());
			st.setDate(2, t.getNgThanhLap());
			st.setObject(3, null);
			st.setDate(4, null);
		} else {
			// st.setInt(1, t.getMaPB());
			st.setString(1, t.getTenPB());
			st.setDate(2, t.getNgThanhLap());
			st.setInt(3, t.getMaTruongPhong());
			st.setDate(4, t.getNgNhanChuc());
		}
		if (st.execute()) {
			cnt = 1;
		} else {
			cnt = 0;
		}

		return cnt;

	}

	@Override
	public int updateT(phongBan t) throws Exception {
		// TODO Auto-generated method stub
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

		String sql = "UPDATE PHONGBAN SET TENPB = ?, NGTHANHLAP = ?, MATRUONGPHONG = ?, NGAYNHANCHUC = ?"
				+ "WHERE MAPB = ?";
		PreparedStatement st = c.prepareStatement(sql);
		if (t.getMaTruongPhong() != 0 || t.getNgNhanChuc() != null) {
			st.setString(1, t.getTenPB());
			st.setDate(2, t.getNgThanhLap());
			st.setInt(3, t.getMaTruongPhong());
			st.setDate(4, t.getNgNhanChuc());
			st.setInt(5, t.getMaPB());
		} else {
			st.setString(1, t.getTenPB());
			st.setDate(2, t.getNgThanhLap());
			st.setObject(3, null);
			st.setObject(4, null);
			st.setInt(5, t.getMaPB());
		}
		if (st.execute()) {
			cnt = 1;
		} else {
			cnt = 0;
		}

		databaseConnection.closeDatabaseConnection(c);

		return cnt;
	}

	@Override
	public int deleteT(phongBan t) throws Exception {
		// TODO Auto-generated method stub
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

		String sql = "DELETE FROM PHONGBAN WHERE MAPB = ?";
		PreparedStatement st = c.prepareStatement(sql);
		st.setInt(1, t.getMaPB());
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
		// TODO Auto-generated method stub
		int cnt = 0;

		try {
			Connection c = databaseConnection.getDatabaseConnection();
			String sql = "SELECT * FROM PHONGBAN";
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

	public double luongTrungBinh(int maPhongBan) throws Exception {
		double total = 0;
		int maNV = 0;
		double soNhanVien = 0;

		Connection c = databaseConnection.getDatabaseConnection();

		String sql = "SELECT * FROM NHANVIEN NV\r\n" + "INNER JOIN CHAMCONG CC ON NV.MANV = CC.MANV\r\n"
				+ "INNER JOIN PHONGBAN PB ON PB.MAPB = NV.MAPB WHERE PB.MAPB = ?";
		PreparedStatement st = c.prepareStatement(sql);
		st.setInt(1, maPhongBan);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			maNV = rs.getInt("MANV");
			total += nhanVienDAO.getInstance().tinhLuongTBNhanVien(maNV);
			soNhanVien += 1;
		}
		databaseConnection.closeDatabaseConnection(c);

		return (total / soNhanVien);
	}

	public int totalNhanVien(int maPhongBanPara) throws Exception {
		int total = 0;

		Connection c = databaseConnection.getDatabaseConnection();

		String sql = "SELECT COUNT(*) AS TOTAL\n" + "FROM NHANVIEN NV\n"
				+ "INNER JOIN PHONGBAN PB ON PB.MAPB = NV.MAPB\n" + "WHERE PB.MAPB = ?\n" + "GROUP BY PB.MAPB";
		PreparedStatement st = c.prepareStatement(sql);
		st.setInt(1, maPhongBanPara);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			total = rs.getInt("TOTAL");
		}
		databaseConnection.closeDatabaseConnection(c);

		return total;
	}

}
