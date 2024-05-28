package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.datechooser.*;

import javax.swing.JTextField;

import controller.capNhatTaiKhoanController;
import controller.capNhatTaiKhoanNhanVienController;
import controller.themPhongBanController;
import model.taiKhoan;

import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class capNhatTaiKhoanNhanVienView extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField matKhauTF;
	private JButton okButton;
	private JButton cancelButton;
	private ActionListener ac;
	private JTextField tenTaiKhoanTF;
	private int maTK;

	public capNhatTaiKhoanNhanVienView(taiKhoan taiKhoan) {
		ImageIcon favicon = new ImageIcon(mainView.class.getResource("/img/cc.png"));
		setIconImage(favicon.getImage());
		setTitle("HRM_TENPM");
		ac = new capNhatTaiKhoanNhanVienController(this);

		maTK = taiKhoan.getMaTK();

		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 803, 402);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBounds(0, 0, 789, 291);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JPanel formPanel = new JPanel();
		formPanel.setBackground(new Color(255, 255, 255));
		formPanel.setBounds(0, 0, 498, 285);
		contentPanel.add(formPanel);
		formPanel.setLayout(null);

		JPanel header = new JPanel();
		header.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		header.setBackground(new Color(102, 205, 170));
		header.setBounds(38, 0, 224, 49);
		formPanel.add(header);
		header.setLayout(null);

		JLabel headerLabel = new JLabel("CẬP NHẬT TÀI KHOẢN");
		headerLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 19));
		headerLabel.setBounds(10, 10, 259, 30);
		header.add(headerLabel);

		JLabel tenTaiKhoan = new JLabel("Tên tài khoản:");
		tenTaiKhoan.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		tenTaiKhoan.setBounds(38, 139, 141, 26);
		formPanel.add(tenTaiKhoan);

		JLabel matKhau = new JLabel("Mật khẩu:");
		matKhau.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		matKhau.setBounds(38, 185, 155, 26);
		formPanel.add(matKhau);

		matKhauTF = new JPasswordField();
		matKhauTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		matKhauTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		matKhauTF.setColumns(10);
		matKhauTF.setText(String.valueOf(taiKhoan.getMatKhau()));
		matKhauTF.setBounds(210, 185, 240, 26);
		formPanel.add(matKhauTF);

		tenTaiKhoanTF = new JTextField();
		tenTaiKhoanTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		tenTaiKhoanTF.setColumns(10);
		tenTaiKhoanTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		tenTaiKhoanTF.setBounds(210, 139, 240, 26);
		tenTaiKhoanTF.setText(String.valueOf(taiKhoan.getTenTK()));
		formPanel.add(tenTaiKhoanTF);

		String kn[] = { "Tin học", "Tiếng anh" };

		String gioiTinh[] = { "Nam", "Nữ" };

		String capBacNV[] = { "Fresher", "Junior", "Senior", "Leader" };

		String maPB[] = { "P01", "P02", "..." };
		{
			JPanel imgPanel = new JPanel();
			imgPanel.setBackground(new Color(255, 255, 255));
			imgPanel.setBounds(508, 0, 287, 285);
			contentPanel.add(imgPanel);
			imgPanel.setLayout(null);

			JLabel imgLabel = new JLabel("");
			imgLabel.setIcon(new ImageIcon(capNhatTaiKhoanNhanVienView.class.getResource("/img/account sticker.png")));
			imgLabel.setBounds(0, 24, 260, 259);
			imgPanel.add(imgLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 301, 788, 69);
			getContentPane().add(buttonPane);
			buttonPane.setOpaque(false);
			buttonPane.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
			{
				okButton = new JButton("Cập nhật");
				okButton.addActionListener(ac);
				okButton.setFocusable(false);
				okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				okButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
				okButton.setBounds(594, 20, 80, 29);
				okButton.setBackground(new Color(102, 205, 170));
				okButton.setFont(new Font("JetBrains Mono", Font.PLAIN, 14));
				buttonPane.setLayout(null);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Hủy");
				cancelButton.addActionListener(ac);
				cancelButton.setFocusable(false);
				cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				cancelButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
				cancelButton.setBounds(680, 20, 68, 29);
				cancelButton.setBackground(new Color(102, 205, 170));
				cancelButton.setFont(new Font("JetBrains Mono", Font.PLAIN, 14));
				buttonPane.add(cancelButton);
			}
		}

		this.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				mainView mainView = new mainView();
				mainView.setTabTaiKhoan();

			}
		});

		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}

	public JTextField getSoNgayLamViecTF() {
		return matKhauTF;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public ActionListener getAc() {
		return ac;
	}

	public void setAc(ActionListener ac) {
		this.ac = ac;
	}

	public JPasswordField getMatKhauTF() {
		return matKhauTF;
	}

	public void setMatKhauTF(JPasswordField matKhauTF) {
		this.matKhauTF = matKhauTF;
	}

	public JTextField getTenTaiKhoanTF() {
		return tenTaiKhoanTF;
	}

	public void setTenTaiKhoanTF(JTextField tenTaiKhoanTF) {
		this.tenTaiKhoanTF = tenTaiKhoanTF;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public int getMaTK() {
		return maTK;
	}

	public void setMaTK(int maTK) {
		this.maTK = maTK;
	}

}