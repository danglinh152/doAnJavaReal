package view;

import controller.phongBanController;
import model.phongBan;

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
import javax.swing.JComboBox;
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
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import DAO.phongBanDAO;

public class phongBanView {
	private JFrame frame;
	public JPanel tab2;
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
	private ActionListener ac;
	private MouseListener mo;
	private KeyListener ke;
	private phongBanController phongBanController;
	private ArrayList<phongBan> phongBanData = phongBanDAO.getInstance().selectSortByMAPBASC();
	private DefaultTableModel tableModel;
	private mainView mainView;
	private JLabel lblNewLabel;
	private JComboBox thuocTinhComboBox;
	private JComboBox thuTuComboBox;

	/**
	 * Create the application.
	 */
	public phongBanView(mainView mainView) {
		this.mainView = mainView;
		ac = new phongBanController(this);
		ke = new phongBanController(this);
		mo = new phongBanController(this);

		tab2 = new JPanel();
		tab2.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		tab2.setFocusTraversalPolicyProvider(true);
		tab2.setFocusable(false);
		tab2.setBackground(Color.WHITE);
		tab2.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 1, 1, 1));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(53, 219, 863, 465);
		tab2.add(panel_1);
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
		int size = 5;
		String maTruongPhong = "";
		String ngNhanChuc = "Chưa Có";
		for (int i = 0; i < phongBanData.size(); i++) {
			String[] tmp = new String[size];
			tmp[0] = String.valueOf(phongBanData.get(i).getMaPB());
			tmp[1] = String.valueOf(phongBanData.get(i).getTenPB());
			tmp[2] = String.valueOf(phongBanData.get(i).getNgThanhLap());
			if (phongBanData.get(i).getMaTruongPhong() > 0) {
				maTruongPhong = String.valueOf(phongBanData.get(i).getMaTruongPhong());
			} else {
				maTruongPhong = "Chưa Có";
			}
			if (phongBanData.get(i).getNgNhanChuc() == null) {
				ngNhanChuc = "Chưa Có";
			} else if (phongBanData.get(i).getNgNhanChuc() != null) {
				ngNhanChuc = String.valueOf(phongBanData.get(i).getNgNhanChuc());
			}
			tmp[3] = maTruongPhong;
			tmp[4] = ngNhanChuc;
			data.add(tmp);
		}

		// Tạo bảng và gắn dữ liệu vào
		String[] columnNames = { "Mã Phòng Ban", "Tên Phòng Ban", "Ngày Thành Lập", "Mã Trưởng Phòng",
				"Ngày Nhận Chức" };
		tableModel = new DefaultTableModel(columnNames, 0);
		for (String[] row : data) {
			tableModel.addRow(row);
		}
		table.setModel(tableModel);

		table.setBounds(0, 0, 1, 1);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 10, 863, 499);
		panel_1.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);
		scrollPane.setAutoscrolls(true);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panel_2 = new JPanel();
		panel_2.setForeground(new Color(255, 255, 255));
		panel_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(new Color(102, 205, 170));
		panel_2.setBounds(0, 0, 957, 55);
		tab2.add(panel_2);
		panel_2.setLayout(null);

		lblNewLabel_3 = new JLabel("QUẢN LÝ PHÒNG BAN");
		lblNewLabel_3.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel_3.setBounds(54, 10, 903, 45);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("JetBrains Mono", Font.BOLD, 21));
		lblNewLabel_3.setBorder(null);
		lblNewLabel_3.setBackground(new Color(255, 255, 255));

		txtTnPhngBan = new JTextField();
		txtTnPhngBan.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		txtTnPhngBan.setBounds(614, 115, 259, 37);
		txtTnPhngBan.addKeyListener(ke); // add ke
		tab2.add(txtTnPhngBan);
		txtTnPhngBan.setColumns(10);
		txtTnPhngBan.setText("Nhập tên phòng ban");
		txtTnPhngBan.setForeground(Color.GRAY);
		txtTnPhngBan.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				if (txtTnPhngBan.getText().equals("Nhập tên phòng ban")) {
					txtTnPhngBan.setText("");
					txtTnPhngBan.setForeground(Color.BLACK);
				}
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				if (txtTnPhngBan.getText().isEmpty()) {
					txtTnPhngBan.setText("Nhập tên phòng ban");
					txtTnPhngBan.setForeground(Color.GRAY);
				}
			}
		});

		btnNewButton_1 = new JButton("");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.addActionListener(ac); // add ac
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setIcon(new ImageIcon(mainView.class.getResource("/img/search (1).png")));
		btnNewButton_1.setBounds(883, 115, 33, 37);
		tab2.add(btnNewButton_1);

		btnNewButton_2 = new JButton("+ Thêm phòng ban");
		btnNewButton_2.addActionListener(ac);
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		btnNewButton_2.setBounds(53, 115, 181, 37);
		tab2.add(btnNewButton_2);

		String thuocTinh[] = { "Mã Phòng Ban", "Tên Phòng Ban", "Ngày Thành Lập", "Mã Trưởng Phòng", "Ngày Nhận Chức" };
		thuocTinhComboBox = new JComboBox(thuocTinh);
		thuocTinhComboBox.addMouseListener(mo);
		thuocTinhComboBox.addActionListener(ac);
		thuocTinhComboBox.setBounds(53, 184, 142, 25);
		tab2.add(thuocTinhComboBox);

		String thuTu[] = { "Tăng dần", "Giảm dần" };
		thuTuComboBox = new JComboBox(thuTu);
		thuTuComboBox.setBounds(319, 184, 90, 25);
		thuTuComboBox.addMouseListener(mo);
		thuTuComboBox.addActionListener(ac);
		tab2.add(thuTuComboBox);

		lblNewLabel = new JLabel("sắp xếp theo:");
		lblNewLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
		lblNewLabel.setBounds(205, 184, 104, 25);
		tab2.add(lblNewLabel);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JPanel getTab2() {
		return tab2;
	}

	public void setTab2(JPanel tab2) {
		this.tab2 = tab2;
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

	public KeyListener getKe() {
		return ke;
	}

	public void setKe(KeyListener ke) {
		this.ke = ke;
	}

	public phongBanController getPhongBanController() {
		return phongBanController;
	}

	public void setPhongBanController(phongBanController phongBanController) {
		this.phongBanController = phongBanController;
	}

	public ArrayList<phongBan> getPhongBanData() {
		return phongBanData;
	}

	public void setPhongBanData(ArrayList<phongBan> phongBanData) {
		this.phongBanData = phongBanData;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public mainView getMainView() {
		return mainView;
	}

	public void setMainView(mainView mainView) {
		this.mainView = mainView;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	public JComboBox getThuocTinhComboBox() {
		return thuocTinhComboBox;
	}

	public void setThuocTinhComboBox(JComboBox thuocTinhComboBox) {
		this.thuocTinhComboBox = thuocTinhComboBox;
	}

	public JComboBox getThuTuComboBox() {
		return thuTuComboBox;
	}

	public void setThuTuComboBox(JComboBox thuTuComboBox) {
		this.thuTuComboBox = thuTuComboBox;
	}

}
