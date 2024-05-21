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

}
