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

public class mainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ActionListener ac;
	private JTable table;
	private JTabbedPane tabbedPane;

	public mainView() {
		ImageIcon favicon = new ImageIcon(mainView.class.getResource("/img/cc.png"));
		setIconImage(favicon.getImage());
		setTitle("HRM_TENPM");
		ac = new logoutController(this);
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
		panel_7.setBounds(0, 0, 220, 237);
		contentPane.add(panel_7);
		panel_7.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.BLACK);
		tabbedPane.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		tabbedPane.setBounds(217, -26, 965, 760);
		contentPane.add(tabbedPane);

		trangChuView trangChuView = new trangChuView();
		tabbedPane.addTab("New tab", null, trangChuView.tab1, null);

		phongBanView phongBanView = new phongBanView(this);
		tabbedPane.addTab("New tab", null, phongBanView.tab2, null);

		nhanVienView nhanVienView = new nhanVienView(this);
		tabbedPane.addTab("New tab", null, nhanVienView.tab3, null);

		chamCongView chamCongView = new chamCongView(this);
		tabbedPane.addTab("New tab", null, chamCongView.tab4, null);

		trinhDoView trinhDoView = new trinhDoView(this);
		tabbedPane.addTab("New tab", null, trinhDoView.tab5, null);

		hopDongView hopDongView = new hopDongView(this);
		tabbedPane.addTab("New tab", null, hopDongView.tab6, null);

		taiKhoanView taiKhoanView = new taiKhoanView(this);
		tabbedPane.addTab("New tab", null, taiKhoanView.tab7, null);

		yeuCauView yeuCauView = new yeuCauView(this);
		tabbedPane.addTab("New tab", null, yeuCauView.tab8, null);

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
		btnNewButton.setIcon(new ImageIcon(mainView.class.getResource("/img/home-icon-silhouette.png")));
		btnNewButton.setFocusable(false);
		btnNewButton.setBounds(0, 1, 218, 50);
		panel.add(btnNewButton);
		btnNewButton.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("JetBrains Mono", Font.BOLD, 14));

		JButton btnPhngBan = new JButton("PHÒNG BAN");
		btnPhngBan.setOpaque(false);
		btnPhngBan.setFocusTraversalKeysEnabled(false);
		btnPhngBan.setFocusPainted(false);
		btnPhngBan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnPhngBan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPhngBan.setIcon(new ImageIcon(mainView.class.getResource("/img/department.png")));
		btnPhngBan.setIconTextGap(10);
		btnPhngBan.setForeground(new Color(0, 0, 0));
		btnPhngBan.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
		btnPhngBan.setFocusable(false);
		btnPhngBan.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnPhngBan.setBackground(new Color(255, 255, 255));
		btnPhngBan.setBounds(0, 49, 218, 50);
		panel.add(btnPhngBan);

		JButton btnNhnVin = new JButton("NHÂN VIÊN");
		btnNhnVin.setOpaque(false);
		btnNhnVin.setFocusTraversalKeysEnabled(false);
		btnNhnVin.setFocusPainted(false);
		btnNhnVin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNhnVin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnNhnVin.setIconTextGap(10);
		btnNhnVin.setIcon(new ImageIcon(mainView.class.getResource("/img/teamwork.png")));
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
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnChmCng.setIconTextGap(8);
		btnChmCng.setIcon(new ImageIcon(mainView.class.getResource("/img/notes.png")));
		btnChmCng.setFocusable(false);
		btnChmCng.setForeground(new Color(0, 0, 0));
		btnChmCng.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
		btnChmCng.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnChmCng.setBackground(new Color(255, 255, 255));
		btnChmCng.setBounds(0, 149, 218, 50);
		panel.add(btnChmCng);

		JButton btnKNng = new JButton("TRÌNH ĐỘ");
		btnKNng.setOpaque(false);
		btnKNng.setFocusTraversalKeysEnabled(false);
		btnKNng.setFocusPainted(false);
		btnKNng.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnKNng.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnKNng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnKNng.setMargin(new Insets(2, 11, 2, 14));
		btnKNng.setIconTextGap(18);
		btnKNng.setIcon(new ImageIcon(mainView.class.getResource("/img/skills.png")));
		btnKNng.setForeground(new Color(0, 0, 0));
		btnKNng.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
		btnKNng.setFocusable(false);
		btnKNng.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnKNng.setBackground(new Color(255, 255, 255));
		btnKNng.setBounds(0, 199, 218, 50);
		panel.add(btnKNng);

		JButton btnHpng = new JButton("HỢP ĐỒNG");
		btnHpng.setOpaque(false);
		btnHpng.setFocusTraversalKeysEnabled(false);
		btnHpng.setFocusPainted(false);
		btnHpng.setIconTextGap(18);
		btnHpng.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHpng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(5);
			}
		});
		btnHpng.setIcon(new ImageIcon(mainView.class.getResource("/img/business.png")));
		btnHpng.setForeground(new Color(0, 0, 0));
		btnHpng.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
		btnHpng.setFocusable(false);
		btnHpng.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnHpng.setBackground(new Color(255, 255, 255));
		btnHpng.setBounds(0, 249, 218, 50);
		panel.add(btnHpng);

		JButton btnTiKhon = new JButton("TÀI KHOẢN");
		btnTiKhon.setOpaque(false);
		btnTiKhon.setFocusTraversalKeysEnabled(false);
		btnTiKhon.setFocusPainted(false);
		btnTiKhon.setIconTextGap(10);
		btnTiKhon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTiKhon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(6);
			}
		});
		btnTiKhon.setIcon(new ImageIcon(mainView.class.getResource("/img/account.png")));
		btnTiKhon.setForeground(new Color(0, 0, 0));
		btnTiKhon.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
		btnTiKhon.setFocusable(false);
		btnTiKhon.setBorder(null);
		btnTiKhon.setBackground(new Color(255, 255, 255));
		btnTiKhon.setBounds(0, 349, 218, 50);
		panel.add(btnTiKhon);

		JButton btnngXut = new JButton("ĐĂNG XUẤT");
		btnngXut.setFocusTraversalKeysEnabled(false);
		btnngXut.setFocusPainted(false);
		btnngXut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnngXut.addActionListener(ac);
		btnngXut.setHorizontalTextPosition(SwingConstants.LEFT);
		btnngXut.setIconTextGap(20);
		btnngXut.setIcon(new ImageIcon(mainView.class.getResource("/img/logout.png")));
		btnngXut.setForeground(new Color(0, 0, 0));
		btnngXut.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
		btnngXut.setFocusable(false);
		btnngXut.setBorder(null);
		btnngXut.setBackground(new Color(102, 205, 170));
		btnngXut.setBounds(0, 400, 230, 47);
		panel.add(btnngXut);

		JButton btnHpng_1 = new JButton("YÊU CẦU");
		btnHpng_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHpng_1.setIcon(new ImageIcon(mainView.class.getResource("/img/notes.png")));
		btnHpng_1.setOpaque(false);
		btnHpng_1.setIconTextGap(18);
		btnHpng_1.setForeground(Color.BLACK);
		btnHpng_1.setFont(new Font("Dialog", Font.BOLD, 14));
		btnHpng_1.setFocusable(false);
		btnHpng_1.setFocusTraversalKeysEnabled(false);
		btnHpng_1.setFocusPainted(false);
		btnHpng_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		btnHpng_1.setBackground(Color.WHITE);
		btnHpng_1.setBounds(0, 300, 218, 50);
		btnHpng_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(7);
			}
		});
		panel.add(btnHpng_1);

		JLabel lblNewLabel = new JLabel("HRM SOFTWARE");
		lblNewLabel.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBorder(null);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 18));
		lblNewLabel.setIcon(new ImageIcon(mainView.class.getResource("/img/hired (1).png")));
		lblNewLabel.setBounds(0, 0, 247, 237);
		panel_7.add(lblNewLabel);
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
}