package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DAO.kyNangDAO;
import DAO.nhanVien_kyNangDAO;
import DAO.phongBanDAO;
import chart.ModelPieChart;
import chart.PieChart;
import controller.trinhDoController;
import model.kyNang;
import model.nhanVien_kyNang;
import model.phongBan;

import javax.swing.border.MatteBorder;
import java.awt.event.ActionEvent;

import chart.PieChart;

public class trinhDoView {
	public JPanel tab5;
	private JTextField txtTnPhngBan;
	private JTable table;
	private JButton btnNewButton_2;
	private JButton btnNewButton_1;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPopupMenu popupMenu;
	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_3;
	private DefaultTableModel tableModel;
	private ArrayList<nhanVien_kyNang> data_nvkn = nhanVien_kyNangDAO.getInstance().selectAll();
	private ActionListener ac;
	private KeyListener ke;
	private MouseListener mo;
	private PieChart pieChart1;
	private mainView mainView;

	public trinhDoView(mainView mainView) {

		this.mainView = mainView;
		ac = new trinhDoController(this);
		ke = new trinhDoController(this);

		tab5 = new JPanel();
		tab5.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		tab5.setFocusTraversalPolicyProvider(true);
		tab5.setFocusable(false);
		tab5.setBackground(Color.WHITE);
		tab5.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 1, 1, 1));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(22, 118, 418, 659);
		tab5.add(panel_1);
		panel_1.setLayout(null);

		table = new JTable();
		table.getTableHeader().addMouseListener(mo);
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

		popupMenu = new JPopupMenu();
		popupMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuItem1 = new JMenuItem("Xóa");
		menuItem1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuItem1.addActionListener(ac); // add ac
		menuItem2 = new JMenuItem("Cập nhật");
		menuItem2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuItem2.addActionListener(ac); // add ac
		popupMenu.add(menuItem1);
		popupMenu.add(menuItem2);

		// Thêm MouseListener vào JTable
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) { // Bấm chuột phải
					int row = table.rowAtPoint(e.getPoint());
					if (row != -1) {
						table.setRowSelectionInterval(row, row);
						popupMenu.show(table, e.getX(), e.getY());
					}
				}
			}
		});

		ArrayList<String[]> data = new ArrayList<>();
		int size = 4;

		for (int i = 0; i < data_nvkn.size(); i++) {
			String[] tmp = new String[size];
			tmp[0] = String.valueOf(data_nvkn.get(i).getMaNV());
			tmp[1] = String.valueOf(data_nvkn.get(i).getTenNV());
			tmp[2] = String.valueOf(data_nvkn.get(i).getTenKyNang());
			tmp[3] = String.valueOf(data_nvkn.get(i).getCapBacKyNang());
			data.add(tmp);
		}

		// Tạo bảng và gắn dữ liệu vào
		String[] columnNames = { "Mã NV", "Họ Tên", "Tên kỹ năng", "Cấp bậc" };
		tableModel = new DefaultTableModel(columnNames, 0);
		for (String[] row : data) {
			tableModel.addRow(row);
		}
		// table.getTableHeader().addMouseListener(nhanVienController);
		table.setModel(tableModel);

		table.setBounds(0, 0, 1, 1);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(31, 57, 377, 452);
		panel_1.add(scrollPane);
		scrollPane.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);
		scrollPane.setAutoscrolls(true);

		txtTnPhngBan = new JTextField();
		txtTnPhngBan.setBounds(31, 10, 259, 37);
		panel_1.add(txtTnPhngBan);
		txtTnPhngBan.addKeyListener(ke);
		txtTnPhngBan.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		txtTnPhngBan.setColumns(10);
		txtTnPhngBan.setText("Nhập tên nhân viên");
		txtTnPhngBan.setForeground(Color.GRAY);

		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(295, 10, 33, 37);
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(ac);
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setIcon(new ImageIcon(mainView.class.getResource("/img/search (1).png")));
		txtTnPhngBan.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				if (txtTnPhngBan.getText().equals("Nhập tên nhân viên")) {
					txtTnPhngBan.setText("");
					txtTnPhngBan.setForeground(Color.BLACK);
				}
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				if (txtTnPhngBan.getText().isEmpty()) {
					txtTnPhngBan.setText("Nhập tên nhân viên");
					txtTnPhngBan.setForeground(Color.GRAY);
				}
			}
		});

		panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(new Color(102, 205, 170));
		panel_2.setBounds(0, 0, 957, 55);
		tab5.add(panel_2);
		panel_2.setLayout(null);

		lblNewLabel_3 = new JLabel("QUẢN LÝ TRÌNH ĐỘ NHÂN VIÊN");
		lblNewLabel_3.setBounds(54, 10, 903, 45);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("JetBrains Mono", Font.BOLD, 21));
		lblNewLabel_3.setBorder(null);
		lblNewLabel_3.setBackground(Color.BLACK);

		btnNewButton_2 = new JButton("+ Thêm kỹ năng cho NV");
		btnNewButton_2.addActionListener(ac);
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		btnNewButton_2.setBounds(54, 80, 181, 37);
		tab5.add(btnNewButton_2);

		ArrayList<kyNang> arr_kn = kyNangDAO.getInstance().selectAll();

		pieChart1 = new PieChart();
		pieChart1.setBounds(464, 15, 534, 525);

		pieChart1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

		pieChart1.setChartType(PieChart.PeiChartType.DEFAULT);

		for (int i = 0; i < arr_kn.size(); i++) {
			Random random = new Random();

			// Tạo màu ngẫu nhiên
			int r = random.nextInt(256); // Giá trị đỏ (0-255)
			int g = random.nextInt(256); // Giá trị xanh lá (0-255)
			int b = random.nextInt(256); // Giá trị xanh da trời (0-255)

			// Tạo một đối tượng Color với giá trị RGB ngẫu nhiên
			Color randomColor = new Color(r, g, b);
			pieChart1.addData(new ModelPieChart(arr_kn.get(i).getTenKN(),
					nhanVien_kyNangDAO.getInstance().count_nv(arr_kn.get(i).getMaKN()), randomColor));

		}
		tab5.add(pieChart1);

	}

	public JPanel gettab5() {
		return tab5;
	}

	public void settab5(JPanel tab5) {
		this.tab5 = tab5;
	}

	public JTextField getTxtTnPhngBan() {
		return txtTnPhngBan;
	}

	public void setTxtTnPhngBan(JTextField txtTnPhngBan) {
		this.txtTnPhngBan = txtTnPhngBan;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getBtnNewButton_2() {
		return btnNewButton_2;
	}

	public void setBtnNewButton_2(JButton btnNewButton_2) {
		this.btnNewButton_2 = btnNewButton_2;
	}

	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

	public void setBtnNewButton_1(JButton btnNewButton_1) {
		this.btnNewButton_1 = btnNewButton_1;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

	public void setPanel_1(JPanel panel_1) {
		this.panel_1 = panel_1;
	}

	public JPanel getPanel_2() {
		return panel_2;
	}

	public void setPanel_2(JPanel panel_2) {
		this.panel_2 = panel_2;
	}

	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}

	public void setPopupMenu(JPopupMenu popupMenu) {
		this.popupMenu = popupMenu;
	}

	public JMenuItem getMenuItem1() {
		return menuItem1;
	}

	public void setMenuItem1(JMenuItem menuItem1) {
		this.menuItem1 = menuItem1;
	}

	public JMenuItem getMenuItem2() {
		return menuItem2;
	}

	public void setMenuItem2(JMenuItem menuItem2) {
		this.menuItem2 = menuItem2;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JLabel getLblNewLabel_3() {
		return lblNewLabel_3;
	}

	public void setLblNewLabel_3(JLabel lblNewLabel_3) {
		this.lblNewLabel_3 = lblNewLabel_3;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public ActionListener getAc() {
		return ac;
	}

	public void setAc(ActionListener ac) {
		this.ac = ac;
	}

	public MouseListener getMo() {
		return mo;
	}

	public void setMo(MouseListener mo) {
		this.mo = mo;
	}

	public JPanel getTab5() {
		return tab5;
	}

	public void setTab5(JPanel tab5) {
		this.tab5 = tab5;
	}

	public ArrayList<nhanVien_kyNang> getData_nvkn() {
		return data_nvkn;
	}

	public void setData_nvkn(ArrayList<nhanVien_kyNang> data_nvkn) {
		this.data_nvkn = data_nvkn;
	}

	public KeyListener getKe() {
		return ke;
	}

	public void setKe(KeyListener ke) {
		this.ke = ke;
	}

	public PieChart getPieChart1() {
		return pieChart1;
	}

	public void setPieChart1(PieChart pieChart1) {
		this.pieChart1 = pieChart1;
	}

	public mainView getMainView() {
		return mainView;
	}

	public void setMainView(mainView mainView) {
		this.mainView = mainView;
	}

}
