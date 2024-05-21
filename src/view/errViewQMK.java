package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Cursor;
import javax.swing.JTextField;

public class errViewQMK extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private quenMatKhauView qmkv;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public errViewQMK(String str, String mk, quenMatKhauView qmkv) {
		ImageIcon favicon = new ImageIcon(mainView.class.getResource("/img/cc.png"));
		setTitle("HRM_TENPM");
		this.setIconImage(favicon.getImage());
		this.qmkv = qmkv;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(55, 119, 315, 33);
		contentPanel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton.setBackground(new Color(102, 205, 170));
		btnNewButton.setFocusable(false);
		btnNewButton.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		btnNewButton.setBounds(169, 181, 85, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals(str)) {
					qmkv.dispose();
					dispose();
					new loginView();
					errView errView = new errView();
					errView.getLblNewLabel().setText("Thông tin: Tài khoản: " + "\"" + qmkv.getTextField().getText()
							+ "\"" + " Mật khẩu: " + "\"" + mk + "\"");
					errView.setVisible(true);

				} else {
					errView errView = new errView();
					errView.getLblNewLabel().setText("OTP không hợp lệ!");
					errView.setVisible(true);
				}
			}
		});
		contentPanel.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Nhập mã OTP");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(0, 56, 436, 27);
		contentPanel.add(lblNewLabel_1);
		this.setLocationRelativeTo(null);

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public quenMatKhauView getQmkv() {
		return qmkv;
	}

	public void setQmkv(quenMatKhauView qmkv) {
		this.qmkv = qmkv;
	}

}
