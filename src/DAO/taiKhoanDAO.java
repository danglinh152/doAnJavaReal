package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.databaseConnection;
import model.hopDong;
import model.nhanVien;
import model.phongBan;
import model.taiKhoan;
import view.errView;

public class taiKhoanDAO implements DAOinterface<taiKhoan> {
	public static taiKhoanDAO getInstance() {
		return new taiKhoanDAO();
	}

	@Override
	public taiKhoan selectByID(taiKhoan t) {
		// TODO Auto-generated method stub
		taiKhoan tk = null;

		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM TAIKHOAN TK INNER JOIN NHANVIEN NV ON  TK.MANV = NV.MANV INNER JOIN PHONGBAN PB ON PB.MAPB = NV.MAPB WHERE TENTK = ? AND MATKHAU = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t.getTenTK());
			st.setString(2, t.getMatKhau());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maTK = rs.getInt("MATK");
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String soDienThoai = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");
				phongBan phongBan = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));
				nhanVien nvReal = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, soDienThoai, email, diaChi, cccd, capBac,
						phongBan);

				String tenTK = rs.getString("TENTK");
				String matKhau = rs.getString("MATKHAU");
				String loaiTK = rs.getString("LOAITAIKHOAN");
				tk = new taiKhoan(maTK, nvReal, tenTK, matKhau, loaiTK);

			}

			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tk;
	}

	public taiKhoan selectByTENTK(String tentk) {
		taiKhoan tk = null;

		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM TAIKHOAN TK INNER JOIN NHANVIEN NV ON  TK.MANV = NV.MANV INNER JOIN PHONGBAN PB ON PB.MAPB = NV.MAPB WHERE TENTK = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, tentk);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maTK = rs.getInt("MATK");
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String soDienThoai = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");
				phongBan phongBan = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));
				nhanVien nvReal = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, soDienThoai, email, diaChi, cccd, capBac,
						phongBan);

				String tenTK = rs.getString("TENTK");
				String matKhau = rs.getString("MATKHAU");
				String loaiTK = rs.getString("LOAITAIKHOAN");
				tk = new taiKhoan(maTK, nvReal, tenTK, matKhau, loaiTK);
			}

			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tk;
	}

	public taiKhoan selectByMATK(int matk) {
		taiKhoan tk = null;

		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM TAIKHOAN TK INNER JOIN NHANVIEN NV ON  TK.MANV = NV.MANV INNER JOIN PHONGBAN PB ON PB.MAPB = NV.MAPB WHERE MATK = ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, matk);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maTK = rs.getInt("MATK");
				int maNV = rs.getInt("MANV");
				String hoTen = rs.getString("HOTEN");
				String gioiTinh = rs.getString("GIOITINH");
				Date ngSinh = rs.getDate("NGSINH");
				String soDienThoai = rs.getString("SDT");
				String email = rs.getString("EMAIL");
				String diaChi = rs.getString("DIACHI");
				String cccd = rs.getString("CCCD");
				String capBac = rs.getString("CAPBAC");
				phongBan phongBan = new phongBan(rs.getInt("MAPB"), rs.getString("TENPB"), rs.getDate("NGTHANHLAP"),
						rs.getInt("MATRUONGPHONG"), rs.getDate("NGAYNHANCHUC"));
				nhanVien nvReal = new nhanVien(maNV, hoTen, gioiTinh, ngSinh, soDienThoai, email, diaChi, cccd, capBac,
						phongBan);

				String tenTK = rs.getString("TENTK");
				String matKhau = rs.getString("MATKHAU");
				String loaiTK = rs.getString("LOAITAIKHOAN");
				tk = new taiKhoan(maTK, nvReal, tenTK, matKhau, loaiTK);
			}

			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tk;
	}

	@Override
	public ArrayList<taiKhoan> selectAll() {
		ArrayList<taiKhoan> arr_tk = new ArrayList<>();
		try {
			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM TAIKHOAN TK INNER JOIN NHANVIEN NV ON TK.MANV = NV.MANV ORDER BY TK.MATK";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maTK = rs.getInt("MATK");
				int maNV = rs.getInt("MANV");
				nhanVien nhanVien = nhanVienDAO.getInstance()
						.selectByID(new nhanVien(maNV, "", "", null, "", "", "", "", "", null));
				String tenTK = rs.getString("TENTK");
				String matKhau = rs.getString("MATKHAU");
				String loaiTK = rs.getString("LOAITAIKHOAN");
				taiKhoan tk = new taiKhoan(maTK, nhanVien, tenTK, matKhau, loaiTK);
				arr_tk.add(tk);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_tk;
	}

	public ArrayList<taiKhoan> selectByLike(String t) {
		ArrayList<taiKhoan> taiKhoanQuery = new ArrayList<>();
		try {
			t = t.toUpperCase();
			t = "%" + t + "%";

			Connection c = databaseConnection.getDatabaseConnection();

			String sql = "SELECT * FROM TAIKHOAN TK INNER JOIN NHANVIEN NV ON TK.MANV = NV.MANV WHERE UPPER(NV.HOTEN) LIKE ?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, t);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int maTK = rs.getInt("MATK");
				int maNV = rs.getInt("MANV");
				nhanVien nhanVien = nhanVienDAO.getInstance()
						.selectByID(new nhanVien(maNV, "", "", null, "", "", "", "", "", null));
				String tenTK = rs.getString("TENTK");
				String matKhau = rs.getString("MATKHAU");
				String loaiTK = rs.getString("LOAITAIKHOAN");
				taiKhoan tk = new taiKhoan(maTK, nhanVien, tenTK, matKhau, loaiTK);
				taiKhoanQuery.add(tk);
			}
			databaseConnection.closeDatabaseConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taiKhoanQuery;
	}

	@Override
	public int insertT(taiKhoan t) throws Exception {
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

		String sql = "INSERT INTO TAIKHOAN VALUES (my_sequence_taikhoan.NEXTVAL, ?, ?, ?, ?)";
		PreparedStatement st = c.prepareStatement(sql);
		// st.setInt(1, t.getMaTK());
		st.setInt(1, t.getNhanVien().getMaNV());
		st.setString(2, t.getTenTK());
		st.setString(3, t.getMatKhau());
		st.setString(4, t.getLoaiTK());
		st.execute();
		cnt = 1;

		databaseConnection.closeDatabaseConnection(c);

		return cnt;
	}

	@Override
	public int updateT(taiKhoan t) throws Exception {
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

		String sql = "UPDATE TAIKHOAN SET TENTK = ?, MATKHAU = ?, LOAITAIKHOAN = ?" + " WHERE MATK = ?";
		PreparedStatement st = c.prepareStatement(sql);

		st.setString(1, t.getTenTK());
		st.setString(2, t.getMatKhau());
		st.setString(3, t.getLoaiTK());
		st.setInt(4, t.getMaTK());

		st.execute();
		cnt = 1;

		databaseConnection.closeDatabaseConnection(c);

		return cnt;
	}

	public int updateTNhanVien(taiKhoan t) throws Exception {
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

		String sql = "UPDATE TAIKHOAN SET TENTK = ?, MATKHAU = ?, LOAITAIKHOAN = ?" + " WHERE MATK = ?";
		PreparedStatement st = c.prepareStatement(sql);

		st.setString(1, t.getTenTK());
		st.setString(2, t.getMatKhau());
		st.setString(3, t.getLoaiTK());
		st.setInt(4, t.getMaTK());

		st.execute();
		cnt = 1;

		databaseConnection.closeDatabaseConnection(c);

		return cnt;
	}

	@Override
	public int deleteT(taiKhoan t) throws Exception {
		int cnt = 0;

		Connection c = databaseConnection.getDatabaseConnection();

		String sql = "DELETE FROM TAIKHOAN WHERE MATK = ?";
		PreparedStatement st = c.prepareStatement(sql);
		st.setInt(1, t.getMaTK());
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
			String sql = "SELECT * FROM TAIKHOAN";
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
