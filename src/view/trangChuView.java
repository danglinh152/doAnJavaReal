package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import chart.PieChart;
import model.nhanVien_phongBan_chamCongClass;
import model.phongBan;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import DAO.nhanVien_phongBan_chamCong;
import DAO.phongBanDAO;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import chart.ModelPieChart;
import chart.Chart;
import chart.ModelChart;
import javax.swing.SwingConstants;

public class trangChuView {
	private chart.PieChart pieChart1;
	private chart.Chart chart;
	public JPanel tab1;
	private JPanel panel_1;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JPanel panel;
	private ArrayList<nhanVien_phongBan_chamCongClass> trangChuData = nhanVien_phongBan_chamCong.getInstance()
			.selectByID();
	private JLabel lblNewLabel;
	// private ArrayList<phongBan> phongBanData
	// phongBanDAO.getInstance().selectSortByMAPB();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * /** Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public trangChuView() {

		tab1 = new JPanel();
		tab1.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		tab1.setFocusTraversalPolicyProvider(true);
		tab1.setFocusable(false);
		tab1.setBackground(new Color(255, 255, 255));
		tab1.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(10, 0, 1051, 365);
		panel_1.setBorder(new EmptyBorder(0, 1, 1, 1));
		panel_1.setBackground(Color.WHITE);
		tab1.add(panel_1);

		ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectAll();

		pieChart1 = new PieChart();
		pieChart1.setBounds(580, 15, 418, 329);

		pieChart1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

		pieChart1.setChartType(PieChart.PeiChartType.DEFAULT);

		for (int i = 0; i < arr_pb.size(); i++) {
			Random random = new Random();

			// Tạo màu ngẫu nhiên
			int r = random.nextInt(256); // Giá trị đỏ (0-255)
			int g = random.nextInt(256); // Giá trị xanh lá (0-255)
			int b = random.nextInt(256); // Giá trị xanh da trời (0-255)

			// Tạo một đối tượng Color với giá trị RGB ngẫu nhiên
			Color randomColor = new Color(r, g, b);
			pieChart1.addData(new ModelPieChart(arr_pb.get(i).getTenPB(),
					phongBanDAO.getInstance().totalNhanVien(arr_pb.get(i).getMaPB()), randomColor));

		}
		panel_1.setLayout(null);
		panel_1.add(pieChart1);

		chart = new Chart();
		chart.setBounds(0, 15, 570, 344);

		double[] luongTrungBinh = new double[arr_pb.size()];

		for (int i = 0; i < arr_pb.size(); i++) {
			Random random = new Random();

			// Tạo màu ngẫu nhiên
			int r = random.nextInt(256); // Giá trị đỏ (0-255)
			int g = random.nextInt(256); // Giá trị xanh lá (0-255)
			int b = random.nextInt(256); // Giá trị xanh da trời (0-255)

			// Tạo một đối tượng Color với giá trị RGB ngẫu nhiên
			Color randomColor = new Color(r, g, b);

			chart.addLegend(arr_pb.get(i).getTenPB(), randomColor);
			luongTrungBinh[i] = phongBanDAO.getInstance().luongTrungBinh(arr_pb.get(i).getMaPB());
		}

//		chart.addLegend("Marketing", new Color(245, 189, 135));
//		chart.addLegend("CNTT", new Color(135, 189, 245));
//		chart.addLegend("Logistic", new Color(189, 135, 245));
//		chart.addLegend("HR", new Color(139, 229, 222));
//		chart.addLegend("test", new Color(245, 189, 100));
		chart.addData(new ModelChart(" ", luongTrungBinh));
		panel_1.add(chart);
		tab1.add(panel_1);

		lblNewLabel = new JLabel("Biểu đồ Phòng Ban trong Công ty");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(580, 313, 418, 42);
		panel_1.add(lblNewLabel);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 364, 1051, 381);

		table = new JTable();

		table.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setRowMargin(0);
		table.setFillsViewportHeight(true);
		table.setOpaque(false);
		table.setEnabled(true);
		table.setGridColor(new Color(0, 0, 0));
		table.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
		table.setSelectionBackground(Color.LIGHT_GRAY);
		table.setRowHeight(35);
		table.setBorder(null);
		table.getTableHeader().setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
		table.getTableHeader().setBackground(new Color(102, 205, 170));
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setBorder(new LineBorder(Color.BLACK, 2));
		table.setBackground(new Color(255, 255, 255));
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultEditor(Object.class, null);
		table.setFocusable(false);

		ArrayList<String[]> data = new ArrayList<>();
		int size = 5;
		String maTruongPhong = "";
		for (int i = 0; i < trangChuData.size(); i++) {
			String[] tmp = new String[size];
			tmp[0] = String.valueOf(trangChuData.get(i).getMaPB());
			tmp[1] = String.valueOf(trangChuData.get(i).getTenPB());
			if (trangChuData.get(i).getMaTP() == 0) {
				maTruongPhong = "Chưa Có";
			} else {
				maTruongPhong = String.valueOf(trangChuData.get(i).getMaTP());
			}

			tmp[2] = maTruongPhong;
			tmp[3] = String.valueOf(trangChuData.get(i).getTongSoNhanVien());

			DecimalFormat df = new DecimalFormat("#,###");
			String result = df.format(trangChuData.get(i).getMucLuongTrungBinh());
			if (result.equals("NaN")) {
				tmp[4] = "0";
			} else {
				tmp[4] = result;
			}
			data.add(tmp);
		}

		// Tạo bảng và gắn dữ liệu vào
		String[] columnNames = { "Mã Phòng Ban", "Tên Phòng Ban", "Trưởng Phòng", "Tổng Số Nhân Viên",
				"Mức Lương Trung Bình (VND)" };
		tableModel = new DefaultTableModel(columnNames, 0);
		for (String[] row : data) {
			tableModel.addRow(row);
		}
		panel.setLayout(null);

		table.setModel(tableModel);

		table.setBounds(0, 0, 1, 1);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 5, 915, 352);
		panel.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);
		scrollPane.setAutoscrolls(true);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		tab1.add(panel);
	}
}
