package view;

import java.awt.EventQueue;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.Cursor;
import javax.swing.border.LineBorder;

import controller.logoutController;
import controller.logoutNhanVienController;
import model.taiKhoan;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.ComponentOrientation;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mainViewNhanVien extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ActionListener ac;
	private JTable table;
	private JTabbedPane tabbedPane;
	public taiKhoan taiKhoanHienTai;

	public mainViewNhanVien(taiKhoan taiKhoan) {
		taiKhoanHienTai = taiKhoan;
		ImageIcon favicon = new ImageIcon(mainViewNhanVien.class.getResource("/img/cc.png"));
		setIconImage(favicon.getImage());
		setTitle("HRM_TENPM");
		ac = new logoutNhanVienController(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1196, 771);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 235, 220, 499);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(null);
		panel_7.setForeground(new Color(25, 25, 112));
		panel_7.setBackground(new Color(255, 255, 255));
		panel_7.setBounds(0, 0, 220, 225);
		contentPane.add(panel_7);
		panel_7.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.BLACK);
		tabbedPane.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		tabbedPane.setBounds(217, -26, 965, 760);
		contentPane.add(tabbedPane);

		trangChuView trangChuViewNhanVien = new trangChuView();
		tabbedPane.addTab("New tab", null, trangChuViewNhanVien.tab1, null);

		guiYeuCauViewNhanVien nhanVienViewNhanVien = new guiYeuCauViewNhanVien(this);
		tabbedPane.addTab("New tab", null, nhanVienViewNhanVien.tab3, null);

		chamCongViewNhanVien chamCongViewNhanVien = new chamCongViewNhanVien(this);
		tabbedPane.addTab("New tab", null, chamCongViewNhanVien.tab4, null);

		taiKhoanViewNhanVien taiKhoanViewNhanVien = new taiKhoanViewNhanVien(this);
		tabbedPane.addTab("New tab", null, taiKhoanViewNhanVien.tab7, null);

		JButton btnNewButton = new JButton("TRANG CHỦ");
		btnNewButton.setOpaque(false);
		btnNewButton.setFocusTraversalKeysEnabled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnNewButton.setIconTextGap(10);
		btnNewButton.setIcon(new ImageIcon(mainViewNhanVien.class.getResource("/img/home-icon-silhouette.png")));
		btnNewButton.setFocusable(false);
		btnNewButton.setBounds(0, 49, 218, 50);
		panel.add(btnNewButton);
		btnNewButton.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("JetBrains Mono", Font.BOLD, 14));

		JButton btnNhnVin = new JButton("GỬI YÊU CẦU");
		btnNhnVin.setOpaque(false);
		btnNhnVin.setFocusTraversalKeysEnabled(false);
		btnNhnVin.setFocusPainted(false);
		btnNhnVin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNhnVin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnNhnVin.setIconTextGap(10);
		btnNhnVin.setIcon(new ImageIcon(mainViewNhanVien.class.getResource("/img/teamwork.png")));
		btnNhnVin.setFocusable(false);
		btnNhnVin.setForeground(new Color(0, 0, 0));
		btnNhnVin.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
		btnNhnVin.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnNhnVin.setBackground(new Color(255, 255, 255));
		btnNhnVin.setBounds(0, 99, 218, 50);
		panel.add(btnNhnVin);

		JButton btnChmCng = new JButton("CHẤM CÔNG");
		btnChmCng.setOpaque(false);
		btnChmCng.setFocusTraversalKeysEnabled(false);
		btnChmCng.setFocusPainted(false);
		btnChmCng.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChmCng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnChmCng.setIconTextGap(8);
		btnChmCng.setIcon(new ImageIcon(mainViewNhanVien.class.getResource("/img/notes.png")));
		btnChmCng.setFocusable(false);
		btnChmCng.setForeground(new Color(0, 0, 0));
		btnChmCng.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
		btnChmCng.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnChmCng.setBackground(new Color(255, 255, 255));
		btnChmCng.setBounds(0, 149, 218, 50);
		panel.add(btnChmCng);

		JButton btnTiKhon = new JButton("TÀI KHOẢN");
		btnTiKhon.setOpaque(false);
		btnTiKhon.setFocusTraversalKeysEnabled(false);
		btnTiKhon.setFocusPainted(false);
		btnTiKhon.setIconTextGap(10);
		btnTiKhon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTiKhon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnTiKhon.setIcon(new ImageIcon(mainViewNhanVien.class.getResource("/img/account.png")));
		btnTiKhon.setForeground(new Color(0, 0, 0));
		btnTiKhon.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
		btnTiKhon.setFocusable(false);
		btnTiKhon.setBorder(null);
		btnTiKhon.setBackground(new Color(255, 255, 255));
		btnTiKhon.setBounds(0, 209, 218, 50);
		panel.add(btnTiKhon);

		JButton btnngXut = new JButton("ĐĂNG XUẤT");
		btnngXut.setFocusTraversalKeysEnabled(false);
		btnngXut.setFocusPainted(false);
		btnngXut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnngXut.addActionListener(ac);
		btnngXut.setHorizontalTextPosition(SwingConstants.LEFT);
		btnngXut.setIconTextGap(20);
		btnngXut.setIcon(new ImageIcon(mainViewNhanVien.class.getResource("/img/logout.png")));
		btnngXut.setForeground(new Color(0, 0, 0));
		btnngXut.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
		btnngXut.setFocusable(false);
		btnngXut.setBorder(null);
		btnngXut.setBackground(new Color(102, 205, 170));
		btnngXut.setBounds(0, 400, 218, 47);
		panel.add(btnngXut);

		JLabel lblNewLabel = new JLabel("HRM SOFTWARE");
		lblNewLabel.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBorder(null);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 18));
		lblNewLabel.setIcon(new ImageIcon(mainViewNhanVien.class.getResource("/img/hired (1).png")));
		lblNewLabel.setBounds(0, 0, 220, 173);
		panel_7.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("xxx");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(10, 183, 210, 42);
		lblNewLabel_1.setText(taiKhoanHienTai.getNhanVien().getHoTen());
		panel_7.add(lblNewLabel_1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.setVisible(true);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabPhongBan() {
		tabbedPane.setSelectedIndex(1);
	}

	public void setTabNhanVien() {
		tabbedPane.setSelectedIndex(2);
	}

	public void setTabChamCong() {
		tabbedPane.setSelectedIndex(3);
	}

	public void setTabTrinhDo() {
		tabbedPane.setSelectedIndex(4);
	}

	public void setTabHopDong() {
		tabbedPane.setSelectedIndex(5);
	}

	public void setTabTaiKhoan() {
		tabbedPane.setSelectedIndex(6);
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public taiKhoan getTaiKhoanHienTai() {
		return taiKhoanHienTai;
	}

	public void setTaiKhoanHienTai(taiKhoan taiKhoanHienTai) {
		this.taiKhoanHienTai = taiKhoanHienTai;
	}
}