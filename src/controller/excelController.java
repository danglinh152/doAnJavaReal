package controller;

import DAO.chamCongDAO;
import DAO.nhanVienDAO;
import model.chamCongClass;
import model.nhanVien;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelController {
	public void exportFileCC() {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("danhsachchamcong");
		XSSFRow row = null;

		Cell cell = null;

		row = sheet.createRow(0);

		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Mã nhân viên");

		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Tháng làm việc");

		cell = row.createCell(3, CellType.STRING);
		cell.setCellValue("Số ngày làm việc");

		cell = row.createCell(4, CellType.STRING);
		cell.setCellValue("Số ngày nghỉ");

		cell = row.createCell(5, CellType.STRING);
		cell.setCellValue("Số giờ tăng ca");

		cell = row.createCell(6, CellType.STRING);
		cell.setCellValue("Số ngày đi trễ");

		cell = row.createCell(7, CellType.STRING);
		cell.setCellValue("Tổng lương");

		ArrayList<chamCongClass> arr_cc = chamCongDAO.getInstance().selectAll();

		for (int i = 0; i < arr_cc.size(); i++) {
			row = sheet.createRow(1 + i);

			cell = row.createCell(0, CellType.NUMERIC);
			cell.setCellValue(arr_cc.get(i).getMaNV());

			cell = row.createCell(1, CellType.NUMERIC);
			cell.setCellValue(arr_cc.get(i).getThangLamViec());

			cell = row.createCell(2, CellType.NUMERIC);
			cell.setCellValue(arr_cc.get(i).getSoNgayLamViec());

			cell = row.createCell(3, CellType.NUMERIC);
			cell.setCellValue(arr_cc.get(i).getSoNgayNghi());

			cell = row.createCell(4, CellType.NUMERIC);
			cell.setCellValue(arr_cc.get(i).getSoGioTangCa());

			cell = row.createCell(5, CellType.NUMERIC);
			cell.setCellValue(arr_cc.get(i).getSoNgayDiTre());

			DecimalFormat df = new DecimalFormat("#,###");
			String result = df.format(nhanVienDAO.getInstance().tinhLuongNhanVien(arr_cc.get(i).getMaNV(),
					arr_cc.get(i).getThangLamViec()));
			cell = row.createCell(6, CellType.NUMERIC);
			cell.setCellValue(result);
		}

		File f = new File("E://reportChamCong.xlsx");
		try {
			FileOutputStream fis = new FileOutputStream(f);
			workbook.write(fis);
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void exportFileNV() {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("danhsachnhanvien");
		XSSFRow row = null;

		Cell cell = null;

		row = sheet.createRow(0);

		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Mã NV");

		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Họ tên");

		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Giới tính");

		cell = row.createCell(3, CellType.STRING);
		cell.setCellValue("Ngày sinh");

		cell = row.createCell(4, CellType.STRING);
		cell.setCellValue("SDT");

		cell = row.createCell(5, CellType.STRING);
		cell.setCellValue("Email");

		cell = row.createCell(6, CellType.STRING);
		cell.setCellValue("Địa chỉ");

		cell = row.createCell(7, CellType.STRING);
		cell.setCellValue("CCCD");

		cell = row.createCell(8, CellType.STRING);
		cell.setCellValue("Cấp bậc");

		cell = row.createCell(9, CellType.STRING);
		cell.setCellValue("Phòng ban");

		ArrayList<nhanVien> arr_nv = nhanVienDAO.getInstance().selectSortByMANVASC();

		for (int i = 0; i < arr_nv.size(); i++) {
			row = sheet.createRow(1 + i);
			cell = row.createCell(0, CellType.NUMERIC);
			cell.setCellValue(arr_nv.get(i).getMaNV());

			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(arr_nv.get(i).getHoTen());

			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue(arr_nv.get(i).getGioiTinh());

			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue(String.valueOf(arr_nv.get(i).getNgSinh()));

			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(arr_nv.get(i).getSoDienThoai());

			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue(arr_nv.get(i).getEmail());

			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue(arr_nv.get(i).getDiaChi());

			cell = row.createCell(7, CellType.STRING);
			cell.setCellValue(arr_nv.get(i).getCccd());

			cell = row.createCell(8, CellType.STRING);
			cell.setCellValue(arr_nv.get(i).getCapBac());

			cell = row.createCell(9, CellType.STRING);
			cell.setCellValue(arr_nv.get(i).getPhongBan().getTenPB());

		}

		File f = new File("E://reportNhanVien.xlsx");
		try {
			FileOutputStream fis = new FileOutputStream(f);
			workbook.write(fis);
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
