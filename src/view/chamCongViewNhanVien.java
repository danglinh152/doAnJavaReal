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
import controller.chamCongNhanVienController;
import controller.nhanVienController;
import model.chamCongClass;
import model.nhanVien;
import javax.swing.border.MatteBorder;
import java.awt.Component;

public class chamCongViewNhanVien {
	public JPanel tab4;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPopupMenu popupMenu;
	private JPopupMenu popupMenu2;
	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private JLabel lblNewLabel_3;
	private DefaultTableModel tableModel_nv;
	private DefaultTableModel tableModel_cc;
	private JTable chamCongtable;

	private ArrayList<chamCongClass> chamCongData;
	private ActionListener ac;
	private KeyListener ke;
	private mainViewNhanVien mainViewNhanVien;
	private JButton btnInPhiuChm;
	private JButton btnInPhiuChm_1;

	public chamCongViewNhanVien(mainViewNhanVien mainViewNhanVien) {
		this.mainViewNhanVien = mainViewNhanVien;
		chamCongData = chamCongDAO.getInstance()
				.selectAllByID(this.mainViewNhanVien.getTaiKhoanHienTai().getNhanVien().getMaNV());

		ac = new chamCongNhanVienController(this);
		ke = new chamCongNhanVienController(this);

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

		// Tạo bảng và gắn dữ liệu vào
		String[] columnNames = { "Mã NV", "Họ Tên", "Email", "Địa chỉ", "CCCD", "Cấp bậc", "Mã PB" };
		tableModel_nv = new DefaultTableModel(columnNames, 0);

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
		bangChamCongcrollPane_1.setBounds(28, 62, 871, 233);
		panel_1.add(bangChamCongcrollPane_1);

		JLabel lblNewLabel_3_1 = new JLabel("BẢNG CHẤM CÔNG:");
		lblNewLabel_3_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1.setForeground(Color.BLACK);
		lblNewLabel_3_1.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
		lblNewLabel_3_1.setBorder(null);
		lblNewLabel_3_1.setBackground(Color.BLACK);
		lblNewLabel_3_1.setBounds(28, 10, 177, 45);
		panel_1.add(lblNewLabel_3_1);

		btnInPhiuChm = new JButton("In phiếu chấm công");
		btnInPhiuChm.setContentAreaFilled(false);
		btnInPhiuChm.setBorderPainted(false);
		btnInPhiuChm.setFocusTraversalKeysEnabled(false);
		btnInPhiuChm.setFocusPainted(false);
		btnInPhiuChm.setBackground(new Color(255, 255, 255));
		btnInPhiuChm.setBorder(null);
		btnInPhiuChm.setFocusable(false);
		btnInPhiuChm.addActionListener(ac);
		btnInPhiuChm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInPhiuChm.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnInPhiuChm.setBounds(213, 19, 177, 25);
		panel_1.add(btnInPhiuChm);

		btnInPhiuChm_1 = new JButton("Xuất PDF");
		btnInPhiuChm_1.setContentAreaFilled(false);
		btnInPhiuChm_1.setBorderPainted(false);
		btnInPhiuChm_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInPhiuChm_1.setFocusTraversalKeysEnabled(false);
		btnInPhiuChm_1.setFocusPainted(false);
		btnInPhiuChm_1.setBackground(new Color(255, 255, 255));
		btnInPhiuChm_1.setBorder(null);
		btnInPhiuChm_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnInPhiuChm_1.addActionListener(ac);
		btnInPhiuChm_1.setFocusable(false);
		btnInPhiuChm_1.setBounds(413, 19, 177, 25);
		panel_1.add(btnInPhiuChm_1);

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
	}

	public JPanel getTab4() {
		return tab4;
	}

	public void setTab4(JPanel tab4) {
		this.tab4 = tab4;
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

	public mainViewNhanVien getMainViewNhanVien() {
		return mainViewNhanVien;
	}

	public void setMainViewNhanVien(mainViewNhanVien mainViewNhanVien) {
		this.mainViewNhanVien = mainViewNhanVien;
	}

	public JButton getBtnInPhiuChm() {
		return btnInPhiuChm;
	}

	public void setBtnInPhiuChm(JButton btnInPhiuChm) {
		this.btnInPhiuChm = btnInPhiuChm;
	}

}
