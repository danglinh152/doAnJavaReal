package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controller.loginController;
import controller.quenMatKhauController;

import javax.swing.border.LineBorder;
import java.awt.Cursor;

public class quenMatKhauView {

	private JFrame frame;
	private JTextField textField;
	private JButton btnNewButton;
	private ActionListener ac;
	private KeyListener ke;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					quenMatKhauView window = new quenMatKhauView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public quenMatKhauView() {
		frame = new JFrame();
		ImageIcon favicon = new ImageIcon(mainView.class.getResource("/img/cc.png"));
		frame.setIconImage(favicon.getImage());
		frame.setTitle("HRM_TENPM");
		ac = new quenMatKhauController(this);
		ke = new quenMatKhauController(this);

		frame.getContentPane().setSize(new Dimension(957, 535));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		frame.setSize(975, 535);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		btnNewButton = new JButton("Xác nhận"); // add ac
		btnNewButton.addActionListener(ac);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFocusable(false);
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnNewButton.setBackground(new Color(102, 205, 170));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("JetBrains Mono", Font.PLAIN, 16));
		btnNewButton.setBounds(572, 299, 287, 42);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(loginView.class.getResource(
				"/img/Black And White Aesthetic Minimalist Modern Simple Typography Coconut Cosmetics Logo.jpg")));
		lblNewLabel.setBounds(0, 0, 500, 500);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("QUÊN MẬT KHẨU");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("DialogInput", Font.BOLD, 32));
		lblNewLabel_1.setBounds(572, 59, 287, 66);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tên tài khoản");
		lblNewLabel_2.setFont(new Font("JetBrains Mono", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(572, 194, 250, 34);
		frame.getContentPane().add(lblNewLabel_2);

		textField = new JTextField(); // add ke
		textField.addKeyListener(ke);
		textField.setFont(new Font("JetBrains Mono", Font.PLAIN, 16));
		textField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField.setBounds(572, 238, 287, 27);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("Quay lại trang đăng nhập");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addActionListener(ac);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(644, 351, 147, 21);
		frame.getContentPane().add(btnNewButton_1);
		frame.setVisible(true);
	}

	public void dispose() {
		this.frame.dispose();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
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
}
