package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.datechooser.*;

import javax.swing.JTextField;

import DAO.phongBanDAO;
import controller.themYeuCauNhanVienController;
//import controller.themNhanVienController;
import model.phongBan;
import model.taiKhoan;

import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class themYeuCauViewNhanVien extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private ActionListener ac;
	private DateChooser ngSinhNV = new DateChooser();
	private KeyListener ke;
	private taiKhoan tkht;
	private JTextArea textArea;

	public themYeuCauViewNhanVien(taiKhoan tkht) {
		ImageIcon favicon = new ImageIcon(mainView.class.getResource("/img/cc.png"));
		setIconImage(favicon.getImage());
		setTitle("HRM_TENPM");
		this.tkht = tkht;
		ac = new themYeuCauNhanVienController(this);

		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 803, 635);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBounds(0, 0, 789, 519);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JPanel formPanel = new JPanel();
		formPanel.setBackground(new Color(255, 255, 255));
		formPanel.setBounds(0, 0, 519, 519);
		contentPanel.add(formPanel);
		formPanel.setLayout(null);

		JPanel header = new JPanel();
		header.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		header.setBackground(new Color(102, 205, 170));
		header.setBounds(38, 0, 238, 49);
		formPanel.add(header);
		header.setLayout(null);

		JLabel headerLabel = new JLabel("GỬI YÊU CẦU");
		headerLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 19));
		headerLabel.setBounds(10, 10, 259, 30);
		header.add(headerLabel);
		ngSinhNV.setForeground(new Color(255, 69, 0));

		String gioiTinh[] = { "Nam", "Nữ" };

		JLabel lblCccd = new JLabel("Nội dung:");
		lblCccd.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		lblCccd.setBounds(38, 99, 127, 38);
		formPanel.add(lblCccd);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(187, 102, 321, 299);
		formPanel.add(textArea);

		JButton btnNewButton = new JButton("Gửi");
		btnNewButton.addActionListener(ac);
		btnNewButton.setFocusable(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnNewButton.setBounds(423, 410, 85, 28);
		btnNewButton.setBackground(new Color(102, 205, 170));
		btnNewButton.setFont(new Font("JetBrains Mono", Font.PLAIN, 14));
		formPanel.add(btnNewButton);

		String capBacNV[] = { "Fresher", "Junior", "Senior", "Leader" };

		ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectAll();
		String[] maPB = new String[arr_pb.size() + 1];
		for (int i = 0; i < arr_pb.size(); i++) {
			maPB[i] = String.valueOf(arr_pb.get(i).getMaPB());
		}
		maPB[arr_pb.size()] = "---";
		{
			JPanel imgPanel = new JPanel();
			imgPanel.setBackground(new Color(255, 255, 255));
			imgPanel.setBounds(525, 0, 270, 416);
			contentPanel.add(imgPanel);
			imgPanel.setLayout(null);

			JLabel imgLabel = new JLabel("");
			imgLabel.setIcon(new ImageIcon(themYeuCauViewNhanVien.class.getResource("/img/win.png")));
			imgLabel.setBounds(0, 90, 260, 259);
			imgPanel.add(imgLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(1, 529, 788, 69);
			getContentPane().add(buttonPane);
			buttonPane.setOpaque(false);
			buttonPane.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
			{
				okButton = new JButton("Gửi");

				okButton.setFocusable(false);
				okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				okButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
				okButton.setBounds(600, 20, 68, 29);
				okButton.setBackground(new Color(102, 205, 170));
				okButton.setFont(new Font("JetBrains Mono", Font.PLAIN, 14));
				buttonPane.setLayout(null);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.setVisible(false);
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
				mainViewNhanVien mainViewNhanVien = new mainViewNhanVien(tkht);
				mainViewNhanVien.setTabNhanVien();

			}
		});

		this.setVisible(true);
		this.setLocationRelativeTo(null);

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

	public DateChooser getNgSinhNV() {
		return ngSinhNV;
	}

	public void setNgSinhNV(DateChooser ngSinhNV) {
		this.ngSinhNV = ngSinhNV;
	}

	public KeyListener getKe() {
		return ke;
	}

	public void setKe(KeyListener ke) {
		this.ke = ke;
	}

	public taiKhoan getTkht() {
		return tkht;
	}

	public void setTkht(taiKhoan tkht) {
		this.tkht = tkht;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
}
