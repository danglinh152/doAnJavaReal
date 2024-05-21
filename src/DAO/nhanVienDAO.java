package DAO;

import model.nhanVien;
import model.phongBan;
import model.taiKhoan;
import view.errView;
import view.mainView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.databaseConnection;

public class nhanVienDAO implements DAOinterface<nhanVien> {
	public static nhanVienDAO getInstance() {
		return new nhanVienDAO();
	}

	public ArrayList<nhanVien> selectSortByMANVASC() {
		ArrayList<nhanVien> nhanVienQuery = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV INNER JOIN PHONGBAN PB ON NV.MAPB = PB.MAPB ORDER BY MANV";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");

				phongBan phongBanReal = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac,
						phongBanReal);
				nhanVienQuery.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanVienQuery;
	}

	public ArrayList<nhanVien> selectSortByMANVDESC() {
		ArrayList<nhanVien> nhanVienQuery = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV INNER JOIN PHONGBAN PB ON NV.MAPB = PB.MAPB ORDER BY MANV DESC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");

				phongBan phongBanReal = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac,
						phongBanReal);
				nhanVienQuery.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanVienQuery;
	}

	public ArrayList<nhanVien> selectSortByHOTENASC() {
		ArrayList<nhanVien> nhanVienQuery = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV INNER JOIN PHONGBAN PB ON NV.MAPB = PB.MAPB ORDER BY HOTEN";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");

				phongBan phongBanReal = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac,
						phongBanReal);
				nhanVienQuery.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanVienQuery;
	}

	public ArrayList<nhanVien> selectSortByHOTENDESC() {
		ArrayList<nhanVien> nhanVienQuery = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV INNER JOIN PHONGBAN PB ON NV.MAPB = PB.MAPB ORDER BY HOTEN DESC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");

				phongBan phongBanReal = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac,
						phongBanReal);
				nhanVienQuery.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanVienQuery;
	}

	public ArrayList<nhanVien> selectSortByGIOITINHASC() {
		ArrayList<nhanVien> nhanVienQuery = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV INNER JOIN PHONGBAN PB ON NV.MAPB = PB.MAPB ORDER BY GIOITINH";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");

				phongBan phongBanReal = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac,
						phongBanReal);
				nhanVienQuery.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanVienQuery;
	}

	public ArrayList<nhanVien> selectSortByGIOITINHDESC() {
		ArrayList<nhanVien> nhanVienQuery = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV INNER JOIN PHONGBAN PB ON NV.MAPB = PB.MAPB ORDER BY GIOITINH DESC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");

				phongBan phongBanReal = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac,
						phongBanReal);
				nhanVienQuery.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanVienQuery;
	}

	public ArrayList<nhanVien> selectSortByNGAYSINHASC() {
		ArrayList<nhanVien> nhanVienQuery = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV INNER JOIN PHONGBAN PB ON NV.MAPB = PB.MAPB ORDER BY NGSINH ASC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");

				phongBan phongBanReal = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac,
						phongBanReal);
				nhanVienQuery.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanVienQuery;
	}

	public ArrayList<nhanVien> selectSortByNGAYSINHDESC() {
		ArrayList<nhanVien> nhanVienQuery = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV INNER JOIN PHONGBAN PB ON NV.MAPB = PB.MAPB ORDER BY NGSINH DESC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");

				phongBan phongBanReal = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac,
						phongBanReal);
				nhanVienQuery.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanVienQuery;
	}

	public ArrayList<nhanVien> selectSortByCAPBACASC() {
		ArrayList<nhanVien> nhanVienQuery = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV INNER JOIN PHONGBAN PB ON NV.MAPB = PB.MAPB ORDER BY CAPBAC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");

				phongBan phongBanReal = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac,
						phongBanReal);
				nhanVienQuery.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanVienQuery;
	}

	public ArrayList<nhanVien> selectSortByCAPBACDESC() {
		ArrayList<nhanVien> nhanVienQuery = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV INNER JOIN PHONGBAN PB ON NV.MAPB = PB.MAPB ORDER BY CAPBAC DESC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");

				phongBan phongBanReal = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac,
						phongBanReal);
				nhanVienQuery.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanVienQuery;
	}

	public ArrayList<nhanVien> selectSortByMAPBASC() {
		ArrayList<nhanVien> nhanVienQuery = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV INNER JOIN PHONGBAN PB ON NV.MAPB = PB.MAPB ORDER BY PB.MAPB";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");

				phongBan phongBanReal = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac,
						phongBanReal);
				nhanVienQuery.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanVienQuery;
	}

	public ArrayList<nhanVien> selectSortByMAPBDESC() {
		ArrayList<nhanVien> nhanVienQuery = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV INNER JOIN PHONGBAN PB ON NV.MAPB = PB.MAPB ORDER BY PB.MAPB DESC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");

				phongBan phongBanReal = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac,
						phongBanReal);
				nhanVienQuery.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanVienQuery;
	}

	public ArrayList<nhanVien> selectByLike(String t) {
		ArrayList<nhanVien> nhanVienQuery = new ArrayList<>();
		try {
			t = t.toUpperCase();
			t = "%" + t + "%";

			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV INNER JOIN PHONGBAN PB ON NV.MAPB = PB.MAPB WHERE UPPER(HOTEN) LIKE ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");

				phongBan phongBanReal = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac,
						phongBanReal);
				nhanVienQuery.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanVienQuery;
	}

	@Override
	public nhanVien selectByID(nhanVien t) {
		// TODO Auto-generated method stub
		nhanVien nv = null;

		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV INNER JOIN PHONGBAN PB ON NV.MAPB = PB.MAPB WHERE MANV = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, t.getMaNV());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");

				phongBan phongBanReal = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));

				nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac, phongBanReal);
			}
			databaseConnection.closeDatabaseConnection(c);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nv;
	}

	@Override
	public ArrayList<nhanVien> selectAll() {
		ArrayList<nhanVien> arr_nv = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN ORDER BY MANV ASC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");
				int maPB = rs.getInt("MAPB");

				phongBan pb_tmp = phongBanDAO.getInstance().selectByID(new phongBan(maPB, "", null, 0, null));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac, pb_tmp);
				arr_nv.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_nv;
	}

	@Override
	public int insertT(nhanVien t) {
		int cnt = 0;
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "INSERT INTO NHANVIEN VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, t.getMaNV());
			st.setString(2, t.getHoTen());
			st.setString(3, t.getGioiTinh());
			st.setDate(4, t.getNgSinh());
			st.setString(5, t.getSoDienThoai());
			st.setString(6, t.getEmail());
			st.setString(7, t.getDiaChi());
			st.setString(8, t.getCccd());
			st.setString(9, t.getCapBac());
			st.setInt(10, t.getPhongBan().getMaPB());

			st.execute();
			cnt = 1;

			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mainView mainView = new mainView();
			mainView.setTabNhanVien();
			mainView.setVisible(true);
			errView errView = new errView();
			errView.getLblNewLabel().setText("Thêm thất bại");
			errView.setVisible(true);

		}
		return cnt;

	}

	@Override
	public int updateT(nhanVien t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteT(nhanVien t) {
		// TODO Auto-generated method stub
		int cnt = 0;
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "DELETE FROM NHANVIEN WHERE MANV = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, t.getMaNV());
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
			errView.getLblNewLabel().setText("Không thể xóa nhân viên là trưởng phòng trong công ty!");
		}
		return cnt;
	}

	@Override
	public int seq_num() {
		int cnt = 0;

		try {
			Connection c = databaseConnection.getDatabaseConnection();
			String sql = "SELECT * FROM NHANVIEN";
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

	public double tinhLuongTBNhanVien(int maNV) {
		double total = 0;
		int soNgayCong = 0;
		double heSoLuong = 0;
		double heSoTangCa = 0;
		int soNgayDiTre = 0;
		double soGioTangCa = 0;
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV\n" + "INNER JOIN CHAMCONG CC ON NV.MANV = CC.MANV\n"
					+ "INNER JOIN PHONGBAN PB ON PB.MAPB = NV.MAPB WHERE NV.MANV = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setDouble(1, maNV);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				soNgayCong = rs.getInt("SONGAYLAMVIEC");
				if (rs.getString("CAPBAC").equals("Leader")) {
					heSoLuong = 1.75;
				} else if (rs.getString("CAPBAC").equals("Senior")) {
					heSoLuong = 1.5;
				} else if (rs.getString("CAPBAC").equals("Junior")) {
					heSoLuong = 1.3;
				} else if (rs.getString("CAPBAC").equals("Fresher")) {
					heSoLuong = 1;
				}
				soNgayDiTre = rs.getInt("SONGAYDITRE");
				soGioTangCa = rs.getDouble("SOGIOTANGCA");
				if (rs.getString("CAPBAC").equals("Leader")) {
					heSoTangCa = 2;
				} else if (rs.getString("CAPBAC").equals("Senior")) {
					heSoTangCa = 1.75;
				} else if (rs.getString("CAPBAC").equals("Junior")) {
					heSoTangCa = 1.5;
				} else if (rs.getString("CAPBAC").equals("Fresher")) {
					heSoTangCa = 1.25;
				}
				total += (soNgayCong * 300000 * heSoLuong) + (soNgayDiTre * 0.85 * 100000 * heSoLuong)
						+ (soGioTangCa * 50000 * heSoTangCa);
			}
			databaseConnection.closeDatabaseConnection(c);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return total;
	}

	public double tinhLuongNhanVien(int maNV, int thang) {
		double total = 0;
		int soNgayCong = 0;
		double heSoLuong = 0;
		double heSoTangCa = 0;
		int soNgayDiTre = 0;
		double soGioTangCa = 0;
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM NHANVIEN NV\n" + "INNER JOIN CHAMCONG CC ON NV.MANV = CC.MANV\n"
					+ "INNER JOIN PHONGBAN PB ON PB.MAPB = NV.MAPB WHERE NV.MANV = ? AND CC.THANGLAMVIEC = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setDouble(1, maNV);
			st.setInt(2, thang);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				soNgayCong = rs.getInt("SONGAYLAMVIEC");
				if (rs.getString("CAPBAC").equals("Leader")) {
					heSoLuong = 1.75;
				} else if (rs.getString("CAPBAC").equals("Senior")) {
					heSoLuong = 1.5;
				} else if (rs.getString("CAPBAC").equals("Junior")) {
					heSoLuong = 1.3;
				} else if (rs.getString("CAPBAC").equals("Fresher")) {
					heSoLuong = 1;
				}
				soNgayDiTre = rs.getInt("SONGAYDITRE");
				soGioTangCa = rs.getDouble("SOGIOTANGCA");
				if (rs.getString("CAPBAC").equals("Leader")) {
					heSoTangCa = 2;
				} else if (rs.getString("CAPBAC").equals("Senior")) {
					heSoTangCa = 1.75;
				} else if (rs.getString("CAPBAC").equals("Junior")) {
					heSoTangCa = 1.5;
				} else if (rs.getString("CAPBAC").equals("Fresher")) {
					heSoTangCa = 1.25;
				}
				total += (soNgayCong * 300000 * heSoLuong) + (soNgayDiTre * 0.85 * 100000 * heSoLuong)
						+ (soGioTangCa * 50000 * heSoTangCa);
			}
			databaseConnection.closeDatabaseConnection(c);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return total;
	}

	public ArrayList<nhanVien> selectNVCC() {
		ArrayList<nhanVien> arr_nv = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "select MANV, HOTEN, EMAIL, DIACHI, CCCD, CAPBAC, NV.GIOITINH, NV.NGSINH, NV.SDT, PB.MAPB\n"
					+ "FROM NHANVIEN NV\n" + "INNER JOIN PHONGBAN PB\n" + "ON NV.MAPB = PB.MAPB ORDER BY NV.MANV ASC";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");
				int maPB = rs.getInt("MAPB");

				phongBan pb_tmp = phongBanDAO.getInstance().selectByID(new phongBan(maPB, "", null, 0, null));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac, pb_tmp);
				arr_nv.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_nv;
	}

	public ArrayList<nhanVien> selectNVCCBYTENNV(String t) {
		ArrayList<nhanVien> arr_nv = new ArrayList<>();
		t = t.toUpperCase();
		t = "%" + t + "%";
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "select MANV, HOTEN, EMAIL, DIACHI, CCCD, CAPBAC, NV.GIOITINH, NV.NGSINH, NV.SDT, PB.MAPB\n"
					+ "FROM NHANVIEN NV\n" + "INNER JOIN PHONGBAN PB\n"
					+ "ON NV.MAPB = PB.MAPB WHERE UPPER(HOTEN) LIKE ? ORDER BY MANV ASC";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String sdt = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");
				int maPB = rs.getInt("MAPB");

				phongBan pb_tmp = phongBanDAO.getInstance().selectByID(new phongBan(maPB, "", null, 0, null));

				nhanVien nv = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, sdt, email, diaChi, cccd, capBac, pb_tmp);
				arr_nv.add(nv);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_nv;
	}

}
