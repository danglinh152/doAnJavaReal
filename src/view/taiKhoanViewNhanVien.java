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

import DAO.taiKhoanDAO;
import controller.taiKhoanController;
import controller.taiKhoanNhanVienController;
import model.taiKhoan;

import javax.swing.border.MatteBorder;
import java.awt.event.ActionEvent;

public class taiKhoanViewNhanVien {
	public JPanel tab7;
	private JPanel panel_2;
	private JPopupMenu popupMenu;
	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JLabel lblNewLabel_3;
	private DefaultTableModel tableModel;
	private ActionListener ac;
	private KeyListener ke;
	private MouseListener mo;
	private mainViewNhanVien mainViewNhanVien;
	private ArrayList<taiKhoan> taiKhoanData = taiKhoanDAO.getInstance().selectAll();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	public taiKhoanViewNhanVien(mainViewNhanVien mainViewNhanVien) {

		this.mainViewNhanVien = mainViewNhanVien;
		ac = new taiKhoanNhanVienController(this);
		ke = new taiKhoanNhanVienController(this);

		tab7 = new JPanel();
		tab7.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		tab7.setFocusTraversalPolicyProvider(true);
		tab7.setFocusable(false);
		tab7.setBackground(Color.WHITE);
		tab7.setLayout(null);

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

		ArrayList<String[]> data = new ArrayList<>();
		int size = 6;

		for (int i = 0; i < taiKhoanData.size(); i++) {
			String[] tmp = new String[size];
			tmp[0] = String.valueOf(taiKhoanData.get(i).getMaTK());
			tmp[1] = String.valueOf(taiKhoanData.get(i).getNhanVien().getMaNV());
			tmp[2] = String.valueOf(taiKhoanData.get(i).getNhanVien().getHoTen());
			tmp[3] = String.valueOf(taiKhoanData.get(i).getTenTK());
			tmp[4] = String.valueOf(taiKhoanData.get(i).getMatKhau());
			tmp[5] = String.valueOf(taiKhoanData.get(i).getLoaiTK());
			data.add(tmp);
		}

		// Tạo bảng và gắn dữ liệu vào
		String[] columnNames = { "Mã tài khoản", "Mã nhân viên", "Họ Tên", "Tên tài khoản", "Mật khẩu",
				"Loại tài khoản" };
		tableModel = new DefaultTableModel(columnNames, 0);

		for (String[] row : data) {
			tableModel.addRow(row);
		}

		panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(new Color(102, 205, 170));
		panel_2.setBounds(0, 0, 957, 55);
		tab7.add(panel_2);
		panel_2.setLayout(null);

		lblNewLabel_3 = new JLabel("THÔNG TIN CÁ NHÂN");
		lblNewLabel_3.setBounds(54, 10, 903, 45);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("JetBrains Mono", Font.BOLD, 21));
		lblNewLabel_3.setBorder(null);
		lblNewLabel_3.setBackground(Color.BLACK);

		JLabel lblNewLabel = new JLabel("NGÀY SINH");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(250, 285, 125, 29);
		tab7.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("SĐT");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(250, 335, 125, 29);
		tab7.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("EMAIL");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(250, 385, 125, 29);
		tab7.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("ĐỊA CHỈ");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setBounds(250, 435, 125, 29);
		tab7.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("CCCD");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setBounds(250, 485, 125, 29);
		tab7.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("CẤP BẬC");
		lblNewLabel_2_3.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3.setBounds(250, 535, 125, 29);
		tab7.add(lblNewLabel_2_3);

		JLabel lblNewLabel_2_5 = new JLabel("GIỚI TÍNH");
		lblNewLabel_2_5.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel_2_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_5.setBounds(250, 235, 125, 24);
		tab7.add(lblNewLabel_2_5);

		JLabel lblNewLabel_2_6 = new JLabel("HỌ TÊN");
		lblNewLabel_2_6.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel_2_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_6.setBounds(250, 185, 125, 29);
		tab7.add(lblNewLabel_2_6);

		textField = new JTextField();
		textField.setText(this.mainViewNhanVien.getTaiKhoanHienTai().getNhanVien().getHoTen());
		textField.setFocusable(false);
		textField.setDisabledTextColor(new Color(255, 255, 255));
		textField.setEditable(false);
		textField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField.setFont(new Font("Tahoma", Font.ITALIC, 18));
		textField.setBounds(385, 185, 346, 29);
		tab7.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setText(this.mainViewNhanVien.getTaiKhoanHienTai().getNhanVien().getGioiTinh());
		textField_1.setFocusable(false);
		textField_1.setDisabledTextColor(new Color(255, 255, 255));
		textField_1.setEditable(false);
		textField_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(385, 230, 346, 29);
		tab7.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setText(String.valueOf(this.mainViewNhanVien.getTaiKhoanHienTai().getNhanVien().getNgSinh()));
		textField_2.setFocusable(false);
		textField_2.setDisabledTextColor(new Color(255, 255, 255));
		textField_2.setEditable(false);
		textField_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(385, 285, 346, 29);
		tab7.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setText(this.mainViewNhanVien.getTaiKhoanHienTai().getNhanVien().getSoDienThoai());
		textField_3.setFocusable(false);
		textField_3.setDisabledTextColor(new Color(255, 255, 255));
		textField_3.setEditable(false);
		textField_3.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_3.setFont(new Font("Tahoma", Font.ITALIC, 18));
		textField_3.setColumns(10);
		textField_3.setBounds(385, 330, 346, 29);
		tab7.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setText(this.mainViewNhanVien.getTaiKhoanHienTai().getNhanVien().getEmail());
		textField_4.setFocusable(false);
		textField_4.setDisabledTextColor(new Color(255, 255, 255));
		textField_4.setEditable(false);
		textField_4.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_4.setFont(new Font("Tahoma", Font.ITALIC, 18));
		textField_4.setColumns(10);
		textField_4.setBounds(385, 385, 346, 29);
		tab7.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setText(this.mainViewNhanVien.getTaiKhoanHienTai().getNhanVien().getDiaChi());
		textField_5.setFocusable(false);
		textField_5.setDisabledTextColor(new Color(255, 255, 255));
		textField_5.setEditable(false);
		textField_5.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_5.setFont(new Font("Tahoma", Font.ITALIC, 18));
		textField_5.setColumns(10);
		textField_5.setBounds(385, 430, 346, 29);
		tab7.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setText(this.mainViewNhanVien.getTaiKhoanHienTai().getNhanVien().getCccd());
		textField_6.setFocusable(false);
		textField_6.setDisabledTextColor(new Color(255, 255, 255));
		textField_6.setEditable(false);
		textField_6.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_6.setFont(new Font("Tahoma", Font.ITALIC, 18));
		textField_6.setColumns(10);
		textField_6.setBounds(385, 485, 346, 29);
		tab7.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setText(this.mainViewNhanVien.getTaiKhoanHienTai().getNhanVien().getCapBac());
		textField_7.setFocusable(false);
		textField_7.setDisabledTextColor(new Color(255, 255, 255));
		textField_7.setEditable(false);
		textField_7.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_7.setFont(new Font("Tahoma", Font.ITALIC, 18));
		textField_7.setColumns(10);
		textField_7.setBounds(385, 530, 346, 29);
		tab7.add(textField_7);

		JButton btnNewButton = new JButton("Cập nhật");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(ac);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(634, 595, 97, 29);
		tab7.add(btnNewButton);
	}

	public JPanel gettab7() {
		return tab7;
	}

	public void settab7(JPanel tab7) {
		this.tab7 = tab7;
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

	public JPanel getTab7() {
		return tab7;
	}

	public void setTab7(JPanel tab7) {
		this.tab7 = tab7;
	}

	public KeyListener getKe() {
		return ke;
	}

	public void setKe(KeyListener ke) {
		this.ke = ke;
	}

	public ArrayList<taiKhoan> getTaiKhoanData() {
		return taiKhoanData;
	}

	public void setTaiKhoanData(ArrayList<taiKhoan> taiKhoanData) {
		this.taiKhoanData = taiKhoanData;
	}

	public mainViewNhanVien getMainViewNhanVien() {
		return mainViewNhanVien;
	}

	public void setMainViewNhanVien(mainViewNhanVien mainViewNhanVien) {
		this.mainViewNhanVien = mainViewNhanVien;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public void setTextField_1(JTextField textField_1) {
		this.textField_1 = textField_1;
	}

	public JTextField getTextField_2() {
		return textField_2;
	}

	public void setTextField_2(JTextField textField_2) {
		this.textField_2 = textField_2;
	}

	public JTextField getTextField_3() {
		return textField_3;
	}

	public void setTextField_3(JTextField textField_3) {
		this.textField_3 = textField_3;
	}

	public JTextField getTextField_4() {
		return textField_4;
	}

	public void setTextField_4(JTextField textField_4) {
		this.textField_4 = textField_4;
	}

	public JTextField getTextField_5() {
		return textField_5;
	}

	public void setTextField_5(JTextField textField_5) {
		this.textField_5 = textField_5;
	}

	public JTextField getTextField_6() {
		return textField_6;
	}

	public void setTextField_6(JTextField textField_6) {
		this.textField_6 = textField_6;
	}

	public JTextField getTextField_7() {
		return textField_7;
	}

	public void setTextField_7(JTextField textField_7) {
		this.textField_7 = textField_7;
	}
}
