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

import DAO.nhanVienDAO;
import controller.nhanVienController;
import model.nhanVien;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class nhanVienView {
	private mainView mainView;
	public JPanel tab3;
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
	private ActionListener ac;
	private KeyListener ke;
	private MouseListener mo;
	private ArrayList<nhanVien> nhanVienData = nhanVienDAO.getInstance().selectSortByMANVASC();
	private JLabel lblNewLabel;
	private JComboBox thuTuComboBox;
	private JComboBox thuocTinhComboBox;
	private JButton btnXtDuytCp;

	/**
	 * Create the application.
	 */
	public nhanVienView(mainView mainView) {
		this.mainView = mainView;
		ac = new nhanVienController(this);
		ke = new nhanVienController(this);
		mo = new nhanVienController(this);

		tab3 = new JPanel();
		tab3.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		tab3.setFocusTraversalPolicyProvider(true);
		tab3.setFocusable(false);
		tab3.setBackground(Color.WHITE);
		tab3.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 1, 1, 1));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(22, 215, 926, 469);
		tab3.add(panel_1);
		panel_1.setLayout(null);

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
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
		int size = 10;

		for (int i = 0; i < nhanVienData.size(); i++) {
			String[] tmp = new String[size];
			tmp[0] = String.valueOf(nhanVienData.get(i).getMaNV());
			tmp[1] = String.valueOf(nhanVienData.get(i).getHoTen());
			tmp[2] = String.valueOf(nhanVienData.get(i).getGioiTinh());
			tmp[3] = String.valueOf(nhanVienData.get(i).getNgSinh());
			tmp[4] = String.valueOf(nhanVienData.get(i).getSoDienThoai());
			tmp[5] = String.valueOf(nhanVienData.get(i).getEmail());
			tmp[6] = String.valueOf(nhanVienData.get(i).getDiaChi());
			tmp[7] = String.valueOf(nhanVienData.get(i).getCccd());
			tmp[8] = String.valueOf(nhanVienData.get(i).getCapBac());
			tmp[9] = String.valueOf(nhanVienData.get(i).getPhongBan().getMaPB());
			data.add(tmp);
		}

		// Tạo bảng và gắn dữ liệu vào
		String[] columnNames = { "Mã NV", "Họ Tên", "Giới tính", "Ngsinh", "SDT", "Email", "Địa chỉ", "CCCD", "Cấp bậc",
				"Mã PB" };
		tableModel = new DefaultTableModel(columnNames, 0);
		for (String[] row : data) {
			tableModel.addRow(row);
		}
		// table.getTableHeader().addMouseListener(nhanVienController);
		table.setModel(tableModel);

		table.setBounds(0, 0, 1, 1);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(31, 10, 871, 499);
		panel_1.add(scrollPane);
		scrollPane.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);
		scrollPane.setAutoscrolls(true);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(new Color(102, 205, 170));
		panel_2.setBounds(0, 0, 957, 55);
		tab3.add(panel_2);
		panel_2.setLayout(null);

		lblNewLabel_3 = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblNewLabel_3.setBounds(54, 10, 903, 45);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("JetBrains Mono", Font.BOLD, 21));
		lblNewLabel_3.setBorder(null);
		lblNewLabel_3.setBackground(Color.BLACK);

		txtTnPhngBan = new JTextField();
		txtTnPhngBan.addKeyListener(ke);
		txtTnPhngBan.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		txtTnPhngBan.setBounds(614, 115, 259, 37);
		tab3.add(txtTnPhngBan);
		txtTnPhngBan.setColumns(10);
		txtTnPhngBan.setText("Nhập tên nhân viên");
		txtTnPhngBan.setForeground(Color.GRAY);
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

		btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(ac);
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setIcon(new ImageIcon(mainView.class.getResource("/img/search (1).png")));
		btnNewButton_1.setBounds(883, 115, 33, 37);
		tab3.add(btnNewButton_1);

		btnNewButton_2 = new JButton("+ Thêm nhân viên");
		btnNewButton_2.addActionListener(ac);
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 15));
		btnNewButton_2.setBounds(53, 115, 181, 37);
		tab3.add(btnNewButton_2);

		String thuocTinh[] = { "Mã NV", "Họ Tên", "Giới tính", "Ngsinh", "Cấp bậc", "Mã PB" };
		thuocTinhComboBox = new JComboBox(thuocTinh);
		thuocTinhComboBox.addActionListener(ac);
		thuocTinhComboBox.setBounds(53, 184, 119, 25);
		tab3.add(thuocTinhComboBox);

		String thuTu[] = { "Tăng dần", "Giảm dần" };
		thuTuComboBox = new JComboBox(thuTu);
		thuTuComboBox.addActionListener(ac);
		thuTuComboBox.setBounds(291, 184, 90, 25);
		tab3.add(thuTuComboBox);

		lblNewLabel = new JLabel("sắp xếp theo:");
		lblNewLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
		lblNewLabel.setBounds(182, 184, 104, 25);
		tab3.add(lblNewLabel);

		JButton btnNewButton = new JButton("Xuất file excel");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBorder(null);
		btnNewButton.setFocusable(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(ac);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(375, 116, 90, 37);
		tab3.add(btnNewButton);

		btnXtDuytCp = new JButton("Xét duyệt cấp bậc");
		btnXtDuytCp.addActionListener(ac);
		btnXtDuytCp.setFocusPainted(false);
		btnXtDuytCp.setContentAreaFilled(false);
		btnXtDuytCp.setBorderPainted(false);
		btnXtDuytCp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXtDuytCp.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXtDuytCp.setFocusable(false);
		btnXtDuytCp.setBorder(null);
		btnXtDuytCp.setBackground(Color.WHITE);
		btnXtDuytCp.setBounds(250, 115, 119, 37);
		tab3.add(btnXtDuytCp);

		JButton btnXutPdf = new JButton("Xuất PDF");
		btnXutPdf.setVerifyInputWhenFocusTarget(false);
		btnXutPdf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXutPdf.addActionListener(ac);
		btnXutPdf.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXutPdf.setFocusable(false);
		btnXutPdf.setFocusPainted(false);
		btnXutPdf.setContentAreaFilled(false);
		btnXutPdf.setBorderPainted(false);
		btnXutPdf.setBorder(null);
		btnXutPdf.setBackground(Color.WHITE);
		btnXutPdf.setBounds(464, 116, 90, 37);
		tab3.add(btnXutPdf);
	}

	public JPanel gettab3() {
		return tab3;
	}

	public void settab3(JPanel tab3) {
		this.tab3 = tab3;
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

	public JPanel getTab3() {
		return tab3;
	}

	public void setTab3(JPanel tab3) {
		this.tab3 = tab3;
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

	public ArrayList<nhanVien> getNhanVienData() {
		return nhanVienData;
	}

	public void setNhanVienData(ArrayList<nhanVien> nhanVienData) {
		this.nhanVienData = nhanVienData;
	}

	public mainView getMainView() {
		return mainView;
	}

	public void setMainView(mainView mainView) {
		this.mainView = mainView;
	}

	public KeyListener getKe() {
		return ke;
	}

	public void setKe(KeyListener ke) {
		this.ke = ke;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public JComboBox getThuTuComboBox() {
		return thuTuComboBox;
	}

	public void setThuTuComboBox(JComboBox thuTuComboBox) {
		this.thuTuComboBox = thuTuComboBox;
	}

	public JComboBox getThuocTinhComboBox() {
		return thuocTinhComboBox;
	}

	public void setThuocTinhComboBox(JComboBox thuocTinhComboBox) {
		this.thuocTinhComboBox = thuocTinhComboBox;
	}
}
