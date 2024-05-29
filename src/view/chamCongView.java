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
import java.text.DecimalFormat;
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

import DAO.chamCongDAO;
import DAO.nhanVienDAO;
import DAO.phongBanDAO;
import controller.chamCongController;
import controller.nhanVienController;
import model.chamCongClass;
import model.nhanVien;
import javax.swing.border.MatteBorder;
import java.awt.Component;

public class chamCongView {
	public JPanel tab4;
	private JTextField tenNVTF;
	private JTable nvTable;
	private JButton tenNVbutton;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPopupMenu popupMenu;
	private JPopupMenu popupMenu2;
	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private JScrollPane bangNhanVienScrollPane;
	private JLabel lblNewLabel_3;
	private DefaultTableModel tableModel_nv;
	private DefaultTableModel tableModel_cc;
	private JTable chamCongtable;
	private JLabel lblNewLabel_3_2;
	private ArrayList<nhanVien> nhanVienData = nhanVienDAO.getInstance().selectNVCC();
	private ArrayList<chamCongClass> chamCongData = chamCongDAO.getInstance().selectAll();
	private ActionListener ac;
	private KeyListener ke;
	private mainView mainView;
	private JButton btnInPhiuChm;

	public chamCongView(mainView mainView) {

		this.mainView = mainView;

		ac = new chamCongController(this);
		ke = new chamCongController(this);

		tab4 = new JPanel();
		tab4.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		tab4.setFocusTraversalPolicyProvider(true);
		tab4.setFocusable(false);
		tab4.setBackground(Color.WHITE);
		tab4.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 1, 1, 1));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(22, 129, 926, 584);
		tab4.add(panel_1);
		panel_1.setLayout(null);

		popupMenu = new JPopupMenu();
		popupMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuItem1 = new JMenuItem("Chấm công");
		menuItem1.addActionListener(ac); // add ac
		menuItem1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		popupMenu.add(menuItem1);

		// Tạo bảng và gắn dữ liệu vào
		String[] columnNames = { "Mã NV", "Họ Tên", "Email", "Địa chỉ", "CCCD", "Cấp bậc", "Mã PB" };
		tableModel_nv = new DefaultTableModel(columnNames, 0);

		popupMenu2 = new JPopupMenu();
		popupMenu2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuItem2 = new JMenuItem("Sửa");
		menuItem2.addActionListener(ac); // add ac
		menuItem2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		popupMenu2.add(menuItem2);
		menuItem3 = new JMenuItem("Xóa");
		menuItem3.addActionListener(ac); // add ac
		menuItem3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		popupMenu2.add(menuItem3);

		chamCongtable = new JTable();
		chamCongtable.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chamCongtable.setShowVerticalLines(false);
		chamCongtable.setShowHorizontalLines(false);
		chamCongtable.setRowMargin(0);
		chamCongtable.setFillsViewportHeight(true);
		chamCongtable.setOpaque(false);
		chamCongtable.setEnabled(true);
		chamCongtable.setGridColor(new Color(0, 0, 0));
		chamCongtable.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
		chamCongtable.setSelectionBackground(Color.LIGHT_GRAY);
		chamCongtable.setRowHeight(35);
		chamCongtable.setBorder(null);
		chamCongtable.getTableHeader().setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
		chamCongtable.getTableHeader().setBackground(new Color(102, 205, 170));
		chamCongtable.getTableHeader().setResizingAllowed(false);
		chamCongtable.getTableHeader().setBorder(new LineBorder(Color.BLACK, 2));
		chamCongtable.setBackground(new Color(255, 255, 255));
		chamCongtable.getTableHeader().setReorderingAllowed(false);
		chamCongtable.setDefaultEditor(Object.class, null);
		chamCongtable.setFocusable(false);

		chamCongtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) { // Bấm chuột phải
					int row = chamCongtable.rowAtPoint(e.getPoint());
					if (row != -1) {
						chamCongtable.setRowSelectionInterval(row, row);
						popupMenu2.show(chamCongtable, e.getX(), e.getY());
					}
				}
			}
		});

		ArrayList<String[]> data_cc = new ArrayList<>();
		int size_cc = 7;

		for (int i = 0; i < chamCongData.size(); i++) {
			String[] tmp = new String[size_cc];
			tmp[0] = String.valueOf(chamCongData.get(i).getMaNV());
			tmp[1] = String.valueOf(chamCongData.get(i).getThangLamViec());
			tmp[2] = String.valueOf(chamCongData.get(i).getSoNgayLamViec());
			tmp[3] = String.valueOf(chamCongData.get(i).getSoNgayNghi());
			tmp[4] = String.valueOf(chamCongData.get(i).getSoGioTangCa());
			tmp[5] = String.valueOf(chamCongData.get(i).getSoNgayDiTre());

			DecimalFormat df = new DecimalFormat("#,###");
			String result = df.format(nhanVienDAO.getInstance().tinhLuongNhanVien(chamCongData.get(i).getMaNV(),
					chamCongData.get(i).getThangLamViec()));
			if (result.equals("NaN")) {
				tmp[6] = "0";
			} else {
				tmp[6] = result;
			}
			data_cc.add(tmp);
		}

		String[] columnNames_cc = { "Mã nhân viên", "Tháng làm việc", "Số ngày làm việc", "Số ngày nghỉ",
				"Số giờ tăng ca", "Số ngày đi trễ", "Tổng lương" };
		tableModel_cc = new DefaultTableModel(columnNames_cc, 0);
		for (String[] row : data_cc) {
			tableModel_cc.addRow(row);
		}

		// table.getTableHeader().addMouseListener(nhanVienController);
		chamCongtable.setModel(tableModel_cc);

		chamCongtable.setBounds(0, 0, 1, 1);

		chamCongtable.getTableHeader().getColumnModel().getColumn(6).setCellRenderer(new MyCustomRenderer());
		JScrollPane bangChamCongcrollPane_1 = new JScrollPane(chamCongtable);
		bangChamCongcrollPane_1.setViewportBorder(null);
		bangChamCongcrollPane_1.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
		bangChamCongcrollPane_1.setBorder(null);
		bangChamCongcrollPane_1.setAutoscrolls(true);
		bangChamCongcrollPane_1.setBounds(31, 328, 871, 233);
		panel_1.add(bangChamCongcrollPane_1);

		nvTable = new JTable();
		nvTable.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nvTable.setShowVerticalLines(false);
		nvTable.setShowHorizontalLines(false);
		nvTable.setRowMargin(0);
		nvTable.setFillsViewportHeight(true);
		nvTable.setOpaque(false);
		nvTable.setEnabled(true);
		nvTable.setGridColor(new Color(0, 0, 0));
		nvTable.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
		nvTable.setSelectionBackground(Color.LIGHT_GRAY);
		nvTable.setRowHeight(35);
		nvTable.setBorder(null);
		nvTable.getTableHeader().setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
		nvTable.getTableHeader().setBackground(new Color(102, 205, 170));
		nvTable.getTableHeader().setResizingAllowed(false);
		nvTable.getTableHeader().setBorder(new LineBorder(Color.BLACK, 2));
		nvTable.setBackground(new Color(255, 255, 255));
		nvTable.getTableHeader().setReorderingAllowed(false);
		nvTable.setDefaultEditor(Object.class, null);
		nvTable.setFocusable(false);

		nvTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) { // Bấm chuột phải
					int row = nvTable.rowAtPoint(e.getPoint());
					if (row != -1) {
						nvTable.setRowSelectionInterval(row, row);
						popupMenu.show(nvTable, e.getX(), e.getY());
					}
				}
			}
		});

		ArrayList<String[]> data_nv = new ArrayList<>();
		int size_nv = 7;

		for (int i = 0; i < nhanVienData.size(); i++) {
			String[] tmp = new String[size_nv];
			tmp[0] = String.valueOf(nhanVienData.get(i).getMaNV());
			tmp[1] = String.valueOf(nhanVienData.get(i).getHoTen());
			tmp[2] = String.valueOf(nhanVienData.get(i).getEmail());
			tmp[3] = String.valueOf(nhanVienData.get(i).getDiaChi());
			tmp[4] = String.valueOf(nhanVienData.get(i).getCccd());
			tmp[5] = String.valueOf(nhanVienData.get(i).getCapBac());
			tmp[6] = String.valueOf(nhanVienData.get(i).getPhongBan().getMaPB());
			data_nv.add(tmp);
		}

		String[] columnNames_nv = { "Mã NV", "Họ Tên", "Email", "Địa chỉ", "CCCD", "Cấp bậc", "Mã PB" };
		tableModel_nv = new DefaultTableModel(columnNames_nv, 0);
		for (String[] row : data_nv) {
			tableModel_nv.addRow(row);
		}
		// table.getTableHeader().addMouseListener(nhanVienController);
		nvTable.setModel(tableModel_nv);

		nvTable.setBounds(0, 0, 1, 1);
		bangNhanVienScrollPane = new JScrollPane(nvTable);
		bangNhanVienScrollPane.setBounds(31, 10, 871, 233);
		panel_1.add(bangNhanVienScrollPane);
		bangNhanVienScrollPane.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
		bangNhanVienScrollPane.setBorder(null);
		bangNhanVienScrollPane.setViewportBorder(null);
		bangNhanVienScrollPane.setAutoscrolls(true);

		JLabel lblNewLabel_3_1 = new JLabel("BẢNG CHẤM CÔNG:");
		lblNewLabel_3_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1.setForeground(Color.BLACK);
		lblNewLabel_3_1.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
		lblNewLabel_3_1.setBorder(null);
		lblNewLabel_3_1.setBackground(Color.BLACK);
		lblNewLabel_3_1.setBounds(31, 276, 177, 45);
		panel_1.add(lblNewLabel_3_1);

		btnInPhiuChm = new JButton("In phiếu chấm công");
		btnInPhiuChm.setContentAreaFilled(false);
		btnInPhiuChm.setBorderPainted(false);
		btnInPhiuChm.setBackground(new Color(255, 255, 255));
		btnInPhiuChm.setBorder(null);
		btnInPhiuChm.setFocusable(false);
		btnInPhiuChm.addActionListener(ac);
		btnInPhiuChm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInPhiuChm.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnInPhiuChm.setBounds(216, 285, 159, 25);
		panel_1.add(btnInPhiuChm);

		JButton btnXutPdf = new JButton("Xuất PDF");
		btnXutPdf.setBorderPainted(false);
		btnXutPdf.setContentAreaFilled(false);
		btnXutPdf.setFocusPainted(false);
		btnXutPdf.setBackground(new Color(255, 255, 255));
		btnXutPdf.setBorder(null);
		btnXutPdf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXutPdf.addActionListener(ac);
		btnXutPdf.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXutPdf.setFocusable(false);
		btnXutPdf.setBounds(374, 285, 159, 25);
		panel_1.add(btnXutPdf);

		panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(new Color(102, 205, 170));
		panel_2.setBounds(0, 0, 957, 55);
		tab4.add(panel_2);
		panel_2.setLayout(null);

		lblNewLabel_3 = new JLabel("QUẢN LÝ CHẤM CÔNG");
		lblNewLabel_3.setBounds(54, 10, 903, 45);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("JetBrains Mono", Font.BOLD, 21));
		lblNewLabel_3.setBorder(null);
		lblNewLabel_3.setBackground(Color.BLACK);

		tenNVTF = new JTextField();
		tenNVTF.addKeyListener(ke);
		tenNVTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		tenNVTF.setBounds(622, 82, 259, 37);
		tab4.add(tenNVTF);
		tenNVTF.setColumns(10);
		tenNVTF.setText("Nhập tên nhân viên");
		tenNVTF.setForeground(Color.GRAY);
		tenNVTF.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				if (tenNVTF.getText().equals("Nhập tên nhân viên")) {
					tenNVTF.setText("");
					tenNVTF.setForeground(Color.BLACK);
				}
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				if (tenNVTF.getText().isEmpty()) {
					tenNVTF.setText("Nhập tên nhân viên");
					tenNVTF.setForeground(Color.GRAY);
				}
			}
		});

		tenNVbutton = new JButton("");
		tenNVbutton.addActionListener(ac);
		tenNVbutton.setFocusable(false);
		tenNVbutton.setBorder(null);
		tenNVbutton.setBackground(new Color(255, 255, 255));
		tenNVbutton.setIcon(new ImageIcon(mainView.class.getResource("/img/search (1).png")));
		tenNVbutton.setBounds(891, 82, 33, 37);
		tab4.add(tenNVbutton);

		lblNewLabel_3_2 = new JLabel("BẢNG NHÂN VIÊN:");
		lblNewLabel_3_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_2.setForeground(Color.BLACK);
		lblNewLabel_3_2.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
		lblNewLabel_3_2.setBorder(null);
		lblNewLabel_3_2.setBackground(Color.BLACK);
		lblNewLabel_3_2.setBounds(50, 82, 268, 45);
		tab4.add(lblNewLabel_3_2);
	}

	public JPanel getTab4() {
		return tab4;
	}

	public void setTab4(JPanel tab4) {
		this.tab4 = tab4;
	}

	public JTextField getTenNVTF() {
		return tenNVTF;
	}

	public void setTenNVTF(JTextField tenNVTF) {
		this.tenNVTF = tenNVTF;
	}

	public JTable getNvTable() {
		return nvTable;
	}

	public void setNvTable(JTable nvTable) {
		this.nvTable = nvTable;
	}

	public JButton getTenNVbutton() {
		return tenNVbutton;
	}

	public void setTenNVbutton(JButton tenNVbutton) {
		this.tenNVbutton = tenNVbutton;
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

	public JPopupMenu getPopupMenu2() {
		return popupMenu2;
	}

	public void setPopupMenu2(JPopupMenu popupMenu2) {
		this.popupMenu2 = popupMenu2;
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

	public JMenuItem getMenuItem3() {
		return menuItem3;
	}

	public void setMenuItem3(JMenuItem menuItem3) {
		this.menuItem3 = menuItem3;
	}

	public JScrollPane getBangNhanVienScrollPane() {
		return bangNhanVienScrollPane;
	}

	public void setBangNhanVienScrollPane(JScrollPane bangNhanVienScrollPane) {
		this.bangNhanVienScrollPane = bangNhanVienScrollPane;
	}

	public JLabel getLblNewLabel_3() {
		return lblNewLabel_3;
	}

	public void setLblNewLabel_3(JLabel lblNewLabel_3) {
		this.lblNewLabel_3 = lblNewLabel_3;
	}

	public DefaultTableModel getTableModel_nv() {
		return tableModel_nv;
	}

	public void setTableModel_nv(DefaultTableModel tableModel_nv) {
		this.tableModel_nv = tableModel_nv;
	}

	public DefaultTableModel getTableModel_cc() {
		return tableModel_cc;
	}

	public void setTableModel_cc(DefaultTableModel tableModel_cc) {
		this.tableModel_cc = tableModel_cc;
	}

	public JTable getChamCongtable() {
		return chamCongtable;
	}

	public void setChamCongtable(JTable chamCongtable) {
		this.chamCongtable = chamCongtable;
	}

	public JLabel getLblNewLabel_3_2() {
		return lblNewLabel_3_2;
	}

	public void setLblNewLabel_3_2(JLabel lblNewLabel_3_2) {
		this.lblNewLabel_3_2 = lblNewLabel_3_2;
	}

	public ArrayList<nhanVien> getNhanVienData() {
		return nhanVienData;
	}

	public void setNhanVienData(ArrayList<nhanVien> nhanVienData) {
		this.nhanVienData = nhanVienData;
	}

	public ArrayList<chamCongClass> getChamCongData() {
		return chamCongData;
	}

	public void setChamCongData(ArrayList<chamCongClass> chamCongData) {
		this.chamCongData = chamCongData;
	}

	public ActionListener getAc() {
		return ac;
	}

	public void setAc(ActionListener ac) {
		this.ac = ac;
	}

	public KeyListener getKe() {
		return ke;
	}

	public void setKe(KeyListener ke) {
		this.ke = ke;
	}

	public mainView getMainView() {
		return mainView;
	}

	public void setMainView(mainView mainView) {
		this.mainView = mainView;
	}
}
