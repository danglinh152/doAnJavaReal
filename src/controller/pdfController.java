package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

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
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import DAO.chamCongDAO;
import DAO.nhanVienDAO;
import model.chamCongClass;
import view.mainView;

public class pdfController {
	public void exportFileCC() {
		String fileName = "E://reportChamCong.pdf";
		Document doc = new Document(PageSize.A4);
		Font font = null;
		Font fontTitle = null;

		try {
			String fontPath = "fonts/ARIAL.ttf";
			BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			font = new Font(bf, 12);
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
				Image logo = Image.getInstance(mainView.class.getResource("/img/cc.png"));

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
			String[] columnTitles = { "Mã nhân viên", "Tháng làm việc", "Số ngày làm việc", "Số ngày nghỉ",
					"Số giờ tăng ca", "Số ngày đi trễ", "Tổng lương" };
			table.setSpacingBefore(10);
			table.setSpacingAfter(10);
			for (String title : columnTitles) {
				PdfPCell cell = new PdfPCell(new Paragraph(title, font));
//				cell.setPaddingTop(5); // Thêm khoảng cách trên
//				cell.setPaddingBottom(5); // Thêm
				table.addCell(cell);
			}
			ArrayList<chamCongClass> arr_cc = chamCongDAO.getInstance().selectAll();
			// Thêm các giá trị vào bảng
			for (int i = 0; i < arr_cc.size(); i++) {

				PdfPCell cellmanv = new PdfPCell(new Paragraph(arr_cc.get(i).getMaNV()));
				cellmanv.setPaddingTop(10); // Thêm khoảng cách trên
				cellmanv.setPaddingBottom(10); // Thêm
				table.addCell(cellmanv);

				PdfPCell cellthanglamviec = new PdfPCell(new Paragraph(arr_cc.get(i).getThangLamViec()));
				cellthanglamviec.setPaddingTop(10); // Thêm khoảng cách trên
				cellthanglamviec.setPaddingBottom(10); // Thêm
				table.addCell(cellthanglamviec);

				PdfPCell cellsongaylamviec = new PdfPCell(new Paragraph(arr_cc.get(i).getSoNgayLamViec()));
				cellsongaylamviec.setPaddingTop(10); // Thêm khoảng cách trên
				cellsongaylamviec.setPaddingBottom(10); // Thêm
				table.addCell(cellsongaylamviec);

				PdfPCell cellsongaynghi = new PdfPCell(new Paragraph(arr_cc.get(i).getSoNgayNghi()));
				cellsongaynghi.setPaddingTop(10); // Thêm khoảng cách trên
				cellsongaynghi.setPaddingBottom(10); // Thêm
				table.addCell(cellsongaynghi);

				PdfPCell cellsogiotangca = new PdfPCell(new Paragraph(String.valueOf(arr_cc.get(i).getSoGioTangCa())));
				cellsogiotangca.setPaddingTop(10); // Thêm khoảng cách trên
				cellsogiotangca.setPaddingBottom(10); // Thêm
				table.addCell(cellsogiotangca);

				PdfPCell cellsongayditre = new PdfPCell(new Paragraph(arr_cc.get(i).getSoNgayDiTre()));
				cellsongayditre.setPaddingTop(10); // Thêm khoảng cách trên
				cellsongayditre.setPaddingBottom(10); // Thêm
				table.addCell(cellsongayditre);

				DecimalFormat df = new DecimalFormat("#,###");
				String result = df.format(nhanVienDAO.getInstance().tinhLuongNhanVien(arr_cc.get(i).getMaNV(),
						arr_cc.get(i).getThangLamViec()));
				PdfPCell celltongluong = new PdfPCell(new Paragraph(String.valueOf(result)));
				celltongluong.setPaddingTop(10); // Thêm khoảng cách trên
				celltongluong.setPaddingBottom(10); // Thêm
				table.addCell(celltongluong);

				// Chỉnh sửa vị trí của bảng
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.setSpacingBefore(20);
				table.setSpacingAfter(20);

				// Chỉnh sửa độ rộng của các cột
				try {
					table.setWidths(new float[] { 4f, 4f, 4f, 4f, 4f, 4f, 6f });
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			// Thêm bảng vào document
			try {

				doc.add(table);
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
