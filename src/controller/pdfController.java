package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFileChooser;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import DAO.chamCongDAO;
import DAO.nhanVienDAO;
import model.chamCongClass;
import model.nhanVien;
import view.mainView;

public class pdfController {
	public void exportFileCC(ArrayList<chamCongClass> arr_cc) {
		String fileName = "E://reportChamCong.pdf";
		Document doc = new Document(PageSize.A4);
		Font font = null;
		Font fontTitle = null;
		PdfPTable footer = null;
		Image logo = null;

		try {
			String fontPath = "fonts/ARIAL.ttf";
			BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			font = new Font(bf, 10);
			fontTitle = new Font(bf, 23, font.BOLD);

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// Tạo một đối tượng PdfWriter để ghi dữ liệu vào file
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			PdfWriter writer = null;
			try {
				writer = PdfWriter.getInstance(doc, fileOutputStream);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			doc.open();

			try {
				logo = Image.getInstance(mainView.class.getResource("/img/cc.png"));

				logo.scaleAbsolute(75, 75);
				// Chỉnh sửa vị trí của logo
				logo.setAlignment(Element.ALIGN_LEFT);
				logo.setAbsolutePosition(20, 750);

				Paragraph prgTieuDe = new Paragraph("DANH SÁCH BẢNG CHẤM CÔNG", fontTitle);
				prgTieuDe.setAlignment(Element.ALIGN_CENTER);

				// Chỉnh sửa vị trí của logo

				// Chỉnh sửa vị trí của tiêu đề
				prgTieuDe.setAlignment(Element.ALIGN_CENTER);
				prgTieuDe.setSpacingBefore(20);
				prgTieuDe.setSpacingAfter(20);

				try {
					doc.add(prgTieuDe);
					doc.add(logo);
				} catch (Exception e) {
					// TODO: handle exception
				}

			} catch (BadElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			PdfPTable table = new PdfPTable(7);
			String[] columnTitles = { "Mã NV", "Tháng", "Số ngày làm việc", "Số ngày nghỉ", "Giờ tăng ca",
					"Số ngày đi trễ", "Tổng lương" };
			table.setSpacingBefore(20);
			table.setSpacingAfter(5);
			for (String title : columnTitles) {
				PdfPCell cell = new PdfPCell(new Paragraph(title, font));
//				cell.setPaddingTop(5); // Thêm khoảng cách trên
//				cell.setPaddingBottom(5); // Thêm
				table.addCell(cell);
			}

			// Thêm các giá trị vào bảng
			for (int i = 0; i < arr_cc.size(); i++) {

				PdfPCell cellmanv = new PdfPCell(new Paragraph(String.valueOf(arr_cc.get(i).getMaNV()), font));
				cellmanv.setPaddingTop(10); // Thêm khoảng cách trên
				cellmanv.setPaddingBottom(10); // Thêm
				table.addCell(cellmanv);

				PdfPCell cellthanglamviec = new PdfPCell(
						new Paragraph(String.valueOf(arr_cc.get(i).getThangLamViec()), font));
				cellthanglamviec.setPaddingTop(10); // Thêm khoảng cách trên
				cellthanglamviec.setPaddingBottom(10); // Thêm
				table.addCell(cellthanglamviec);

				PdfPCell cellsongaylamviec = new PdfPCell(
						new Paragraph(String.valueOf(arr_cc.get(i).getSoNgayLamViec()), font));
				cellsongaylamviec.setPaddingTop(10); // Thêm khoảng cách trên
				cellsongaylamviec.setPaddingBottom(10); // Thêm
				table.addCell(cellsongaylamviec);

				PdfPCell cellsongaynghi = new PdfPCell(
						new Paragraph(String.valueOf(arr_cc.get(i).getSoNgayNghi()), font));
				cellsongaynghi.setPaddingTop(10); // Thêm khoảng cách trên
				cellsongaynghi.setPaddingBottom(10); // Thêm
				table.addCell(cellsongaynghi);

				PdfPCell cellsogiotangca = new PdfPCell(
						new Paragraph(String.valueOf(arr_cc.get(i).getSoGioTangCa()), font));
				cellsogiotangca.setPaddingTop(10); // Thêm khoảng cách trên
				cellsogiotangca.setPaddingBottom(10); // Thêm
				table.addCell(cellsogiotangca);

				PdfPCell cellsongayditre = new PdfPCell(
						new Paragraph(String.valueOf(arr_cc.get(i).getSoNgayDiTre()), font));
				cellsongayditre.setPaddingTop(10); // Thêm khoảng cách trên
				cellsongayditre.setPaddingBottom(10); // Thêm
				table.addCell(cellsongayditre);

				DecimalFormat df = new DecimalFormat("#,###");
				String result = df.format(nhanVienDAO.getInstance().tinhLuongNhanVien(arr_cc.get(i).getMaNV(),
						arr_cc.get(i).getThangLamViec()));
				PdfPCell celltongluong = new PdfPCell(new Paragraph(String.valueOf(result), font));
				celltongluong.setPaddingTop(10); // Thêm khoảng cách trên
				celltongluong.setPaddingBottom(10); // Thêm
				table.addCell(celltongluong);

				// Chỉnh sửa vị trí của bảng
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.setSpacingBefore(50);
				table.setSpacingAfter(20);

				// Chỉnh sửa độ rộng của các cột
				try {
					table.setWidths(new float[] { 2.5f, 3f, 8f, 6f, 6f, 6f, 7f });
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Tạo một PdfPTable để chứa footer
				footer = new PdfPTable(1);
				footer.setWidthPercentage(100);

				footer.setWidthPercentage(100);
				footer.setSpacingBefore(120f);

				// Tạo cell chứa thông tin công ty
				PdfPCell infoCell = new PdfPCell(new Phrase(
						"TenPM - Quản lý nguồn nhân sự\nĐường Hàn Thuyên, khu phố 6, P. Thủ Đức, Tp.HCM", font));
				infoCell.setBorder(Rectangle.NO_BORDER);
				infoCell.setHorizontalAlignment(Element.ALIGN_CENTER);

				// Tạo cell chứa thông tin ngày tháng
				String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
				PdfPCell dateCell = new PdfPCell(new Phrase("Ngày " + currentDate));
				dateCell.setBorder(Rectangle.NO_BORDER);
				dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

				// Thêm các cell vào table footer

				footer.addCell(infoCell);
				footer.addCell(dateCell);
			}

			// Thêm bảng vào document
			try {
				doc.add(table);
				doc.add(footer);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			doc.close();
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void exportFileNV(ArrayList<nhanVien> arr_nv) {
		String fileName = "E://reportNhanVien.pdf";
		Document doc = new Document(PageSize.A4);
		Font font = null;
		Font fontTitle = null;
		PdfPTable footer = null;
		Image logo = null;

		try {
			String fontPath = "fonts/ARIAL.ttf";
			BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			font = new Font(bf, 10);
			fontTitle = new Font(bf, 23, font.BOLD);

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// Tạo một đối tượng PdfWriter để ghi dữ liệu vào file
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			PdfWriter writer = null;
			try {
				writer = PdfWriter.getInstance(doc, fileOutputStream);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			doc.open();

			try {
				logo = Image.getInstance(mainView.class.getResource("/img/cc.png"));

				logo.scaleAbsolute(75, 75);
				// Chỉnh sửa vị trí của logo
				logo.setAlignment(Element.ALIGN_LEFT);
				logo.setAbsolutePosition(20, 750);

				Paragraph prgTieuDe = new Paragraph("DANH SÁCH NHÂN VIÊN", fontTitle);
				prgTieuDe.setAlignment(Element.ALIGN_CENTER);

				// Chỉnh sửa vị trí của logo

				// Chỉnh sửa vị trí của tiêu đề
				prgTieuDe.setAlignment(Element.ALIGN_CENTER);
				prgTieuDe.setSpacingBefore(20);
				prgTieuDe.setSpacingAfter(20);

				try {
					doc.add(prgTieuDe);
					doc.add(logo);
				} catch (Exception e) {
					// TODO: handle exception
				}

			} catch (BadElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			PdfPTable table = new PdfPTable(7);
			String[] columnTitles = { "Mã NV", "Họ Tên", "NgSinh", "SDT", "Email", "CCCD", "Cấp bậc" };
			table.setSpacingBefore(20);
			table.setSpacingAfter(5);
			for (String title : columnTitles) {
				PdfPCell cell = new PdfPCell(new Paragraph(title, font));
//				cell.setPaddingTop(5); // Thêm khoảng cách trên
//				cell.setPaddingBottom(5); // Thêm
				table.addCell(cell);
			}

			// Thêm các giá trị vào bảng
			for (int i = 0; i < arr_nv.size(); i++) {

				PdfPCell cellmanv = new PdfPCell(new Paragraph(String.valueOf(arr_nv.get(i).getMaNV()), font));
				cellmanv.setPaddingTop(10); // Thêm khoảng cách trên
				cellmanv.setPaddingBottom(10); // Thêm
				table.addCell(cellmanv);

				PdfPCell cellHoTen = new PdfPCell(new Paragraph(String.valueOf(arr_nv.get(i).getHoTen()), font));
				cellHoTen.setPaddingTop(10); // Thêm khoảng cách trên
				cellHoTen.setPaddingBottom(10); // Thêm
				table.addCell(cellHoTen);

				PdfPCell cellNgSinh = new PdfPCell(new Paragraph(String.valueOf(arr_nv.get(i).getNgSinh()), font));
				cellNgSinh.setPaddingTop(10); // Thêm khoảng cách trên
				cellNgSinh.setPaddingBottom(10); // Thêm
				table.addCell(cellNgSinh);

				PdfPCell cellSDT = new PdfPCell(new Paragraph(String.valueOf(arr_nv.get(i).getSoDienThoai()), font));
				cellSDT.setPaddingTop(10); // Thêm khoảng cách trên
				cellSDT.setPaddingBottom(10); // Thêm
				table.addCell(cellSDT);

				PdfPCell cellemail = new PdfPCell(new Paragraph(String.valueOf(arr_nv.get(i).getEmail()), font));
				cellemail.setPaddingTop(10); // Thêm khoảng cách trên
				cellemail.setPaddingBottom(10); // Thêm
				table.addCell(cellemail);

				PdfPCell cellcccd = new PdfPCell(new Paragraph(String.valueOf(arr_nv.get(i).getCccd()), font));
				cellcccd.setPaddingTop(10); // Thêm khoảng cách trên
				cellcccd.setPaddingBottom(10); // Thêm
				table.addCell(cellcccd);

				PdfPCell cellcapbac = new PdfPCell(new Paragraph(String.valueOf(arr_nv.get(i).getCapBac()), font));
				cellcapbac.setPaddingTop(10); // Thêm khoảng cách trên
				cellcapbac.setPaddingBottom(10); // Thêm
				table.addCell(cellcapbac);

				// Chỉnh sửa vị trí của bảng
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.setSpacingBefore(50);
				table.setSpacingAfter(20);

				// Chỉnh sửa độ rộng của các cột
				try {
					table.setWidths(new float[] { 2f, 8f, 3.5f, 4.5f, 5f, 5f, 3f });
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Tạo một PdfPTable để chứa footer
				footer = new PdfPTable(1);
				footer.setWidthPercentage(100);

				footer.setWidthPercentage(100);
				footer.setSpacingBefore(120f);

				// Tạo cell chứa thông tin công ty
				PdfPCell infoCell = new PdfPCell(new Phrase(
						"TenPM - Quản lý nguồn nhân sự\nĐường Hàn Thuyên, khu phố 6, P. Thủ Đức, Tp.HCM", font));
				infoCell.setBorder(Rectangle.NO_BORDER);
				infoCell.setHorizontalAlignment(Element.ALIGN_CENTER);

				// Tạo cell chứa thông tin ngày tháng
				String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
				PdfPCell dateCell = new PdfPCell(new Phrase("Ngày " + currentDate));
				dateCell.setBorder(Rectangle.NO_BORDER);
				dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

				// Thêm các cell vào table footer

				footer.addCell(infoCell);
				footer.addCell(dateCell);
			}

			// Thêm bảng vào document
			try {
				doc.add(table);
				doc.add(footer);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			doc.close();
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
