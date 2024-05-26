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
import controller.thongTinNhanVienController;
import model.taiKhoan;

import javax.swing.border.MatteBorder;
import java.awt.event.ActionEvent;

public class taiKhoanViewNhanVien {
	public JPanel tab5;
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
	private JButton btnNewButton;

	public taiKhoanViewNhanVien(mainViewNhanVien mainViewNhanVien) {

		this.mainViewNhanVien = mainViewNhanVien;
		ac = new taiKhoanNhanVienController(this);
		ke = new taiKhoanNhanVienController(this);

		tab5 = new JPanel();
		tab5.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		tab5.setFocusTraversalPolicyProvider(true);
		tab5.setFocusable(false);
		tab5.setBackground(Color.WHITE);
		tab5.setLayout(null);

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
		tab5.add(panel_2);
		panel_2.setLayout(null);

		lblNewLabel_3 = new JLabel("THÔNG TIN TÀI KHOẢN");
		lblNewLabel_3.setBounds(54, 10, 903, 45);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("JetBrains Mono", Font.BOLD, 21));
		lblNewLabel_3.setBorder(null);
		lblNewLabel_3.setBackground(Color.BLACK);

		JLabel lblNewLabel_2_5 = new JLabel("MẬT KHẨU");
		lblNewLabel_2_5.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel_2_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_5.setBounds(252, 340, 125, 24);
		tab5.add(lblNewLabel_2_5);

		JLabel lblNewLabel_2_6 = new JLabel("TÀI KHOẢN");
		lblNewLabel_2_6.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel_2_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_6.setBounds(252, 290, 125, 29);
		tab5.add(lblNewLabel_2_6);

		textField = new JTextField();
		textField.setText(this.mainViewNhanVien.getTaiKhoanHienTai().getTenTK());
		textField.setFocusable(false);
		textField.setDisabledTextColor(new Color(255, 255, 255));
		textField.setEditable(false);
		textField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField.setFont(new Font("Tahoma", Font.ITALIC, 18));
		textField.setBounds(387, 290, 346, 29);
		tab5.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setText(this.mainViewNhanVien.getTaiKhoanHienTai().getMatKhau());
		textField_1.setFocusable(false);
		textField_1.setDisabledTextColor(new Color(255, 255, 255));
		textField_1.setEditable(false);
		textField_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_1.setFont(new Font("Tahoma", Font.ITALIC, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(387, 335, 346, 29);
		tab5.add(textField_1);

		btnNewButton = new JButton("Đổi mật khẩu");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setFocusable(false);
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnNewButton.setBounds(585, 400, 97, 29);
		btnNewButton.addActionListener(ac);
		tab5.add(btnNewButton);
	}

	public JPanel gettab5() {
		return tab5;
	}

	public void settab5(JPanel tab5) {
		this.tab5 = tab5;
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

}
