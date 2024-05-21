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
import controller.themNhanVienController;
import model.phongBan;

import java.awt.Cursor;
import javax.swing.JComboBox;

public class themNhanVienView extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tenNVTF;
	private JTextField ngSinhTF;
	private JTextField sdtTF;
	private JButton okButton;
	private JButton cancelButton;
	private ActionListener ac;
	private DateChooser ngSinhNV = new DateChooser();
	private JTextField emailTF;
	private JTextField diaChiTF;
	private JTextField cccdTF;
	private JComboBox gioiTinhComboBox;
	private JComboBox capBacComboBox;
	private JComboBox maPBComboBox;
	private KeyListener ke;

	public themNhanVienView() {

		ac = new themNhanVienController(this);
		ke = new themNhanVienController(this);

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

		JLabel headerLabel = new JLabel("THÔNG TIN NHÂN VIÊN");
		headerLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 19));
		headerLabel.setBounds(10, 10, 259, 30);
		header.add(headerLabel);

		JLabel hoTenNV = new JLabel("Họ tên:");
		hoTenNV.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		hoTenNV.setBounds(38, 93, 127, 26);
		formPanel.add(hoTenNV);

		JLabel gioiTinhNV = new JLabel("Giới tính:");
		gioiTinhNV.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		gioiTinhNV.setBounds(38, 139, 141, 26);
		formPanel.add(gioiTinhNV);

		JLabel ngSinh = new JLabel("Ngày sinh:");
		ngSinh.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		ngSinh.setBounds(38, 185, 141, 26);
		formPanel.add(ngSinh);

		JLabel sdtNV = new JLabel("Số điện thoại:");
		sdtNV.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		sdtNV.setBounds(38, 231, 141, 26);
		formPanel.add(sdtNV);

		tenNVTF = new JTextField();
		tenNVTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		tenNVTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		tenNVTF.setBounds(187, 93, 261, 26);
		formPanel.add(tenNVTF);
		tenNVTF.setColumns(10);

		ngSinhTF = new JTextField();
		ngSinhTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		ngSinhTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		ngSinhTF.setColumns(10);
		ngSinhTF.setBounds(187, 185, 261, 26);
		formPanel.add(ngSinhTF);
		ngSinhNV.setTextRefernce(ngSinhTF);
		ngSinhNV.setForeground(new Color(255, 69, 0));

		sdtTF = new JTextField();
		sdtTF.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		sdtTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		sdtTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		sdtTF.setColumns(10);
		sdtTF.setBounds(187, 231, 261, 26);
		formPanel.add(sdtTF);

		String gioiTinh[] = { "Nam", "Nữ" };
		gioiTinhComboBox = new JComboBox(gioiTinh);
		gioiTinhComboBox.setBorder(null);
		gioiTinhComboBox.setFocusable(false);
		gioiTinhComboBox.setBackground(new Color(255, 255, 255));
		gioiTinhComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		gioiTinhComboBox.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));

		gioiTinhComboBox.setBounds(189, 144, 70, 21);
		formPanel.add(gioiTinhComboBox);

		JLabel emailNv = new JLabel("Email: ");
		emailNv.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		emailNv.setBounds(38, 277, 141, 26);
		formPanel.add(emailNv);

		emailTF = new JTextField();
		emailTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		emailTF.setColumns(10);
		emailTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		emailTF.setBounds(187, 277, 261, 26);
		formPanel.add(emailTF);

		JLabel diaChiNv = new JLabel("Địa chỉ:");
		diaChiNv.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		diaChiNv.setBounds(38, 323, 141, 26);
		formPanel.add(diaChiNv);

		diaChiTF = new JTextField();
		diaChiTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		diaChiTF.setColumns(10);
		diaChiTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		diaChiTF.setBounds(187, 323, 261, 26);
		formPanel.add(diaChiTF);

		JLabel lblCccd = new JLabel("CCCD:");
		lblCccd.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		lblCccd.setBounds(38, 369, 141, 26);
		formPanel.add(lblCccd);

		cccdTF = new JTextField();
		cccdTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		cccdTF.setColumns(10);
		cccdTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		cccdTF.setBounds(187, 369, 261, 26);
		formPanel.add(cccdTF);

		JLabel capBac = new JLabel("Cấp bậc:");
		capBac.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		capBac.setBounds(38, 415, 141, 26);
		formPanel.add(capBac);

		String capBacNV[] = { "Fresher", "Junior", "Senior", "Leader" };
		capBacComboBox = new JComboBox(capBacNV);
		capBacComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		capBacComboBox.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		capBacComboBox.setFocusable(false);
		capBacComboBox.setBorder(null);
		capBacComboBox.setBackground(Color.WHITE);
		capBacComboBox.setBounds(187, 420, 89, 21);
		formPanel.add(capBacComboBox);

		JLabel maPhongBan = new JLabel("Mã phòng:");
		maPhongBan.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		maPhongBan.setBounds(38, 461, 141, 26);
		formPanel.add(maPhongBan);

		ArrayList<phongBan> arr_pb = phongBanDAO.getInstance().selectAll();
		String[] maPB = new String[arr_pb.size() + 1];
		for (int i = 0; i < arr_pb.size(); i++) {
			maPB[i] = String.valueOf(arr_pb.get(i).getMaPB());
		}
		maPB[arr_pb.size()] = "---";
		maPBComboBox = new JComboBox(maPB);
		maPBComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		maPBComboBox.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		maPBComboBox.setFocusable(false);
		maPBComboBox.setBorder(null);
		maPBComboBox.setBackground(Color.WHITE);
		maPBComboBox.setBounds(187, 466, 89, 21);
		maPBComboBox.setSelectedIndex(arr_pb.size());
		formPanel.add(maPBComboBox);
		{
			JPanel imgPanel = new JPanel();
			imgPanel.setBackground(new Color(255, 255, 255));
			imgPanel.setBounds(525, 0, 270, 416);
			contentPanel.add(imgPanel);
			imgPanel.setLayout(null);

			JLabel imgLabel = new JLabel("");
			imgLabel.setIcon(new ImageIcon(themNhanVienView.class.getResource("/img/win.png")));
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
				okButton = new JButton("Thêm");
				okButton.addActionListener(ac);
				okButton.setFocusable(false);
				okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				okButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
				okButton.setBounds(600, 20, 68, 29);
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
				mainView.setTabNhanVien();

			}
		});

		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}

	public JTextField getTenNVTF() {
		return tenNVTF;
	}

	public void setTenNVTF(JTextField tenNVTF) {
		this.tenNVTF = tenNVTF;
	}

	public JTextField getNgSinhTF() {
		return ngSinhTF;
	}

	public void setNgSinhTF(JTextField ngSinhTF) {
		this.ngSinhTF = ngSinhTF;
	}

	public JTextField getSdtTF() {
		return sdtTF;
	}

	public void setSdtTF(JTextField sdtTF) {
		this.sdtTF = sdtTF;
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

	public JTextField getEmailTF() {
		return emailTF;
	}

	public void setEmailTF(JTextField emailTF) {
		this.emailTF = emailTF;
	}

	public JTextField getDiaChiTF() {
		return diaChiTF;
	}

	public void setDiaChiTF(JTextField diaChiTF) {
		this.diaChiTF = diaChiTF;
	}

	public JTextField getCccdTF() {
		return cccdTF;
	}

	public void setCccdTF(JTextField cccdTF) {
		this.cccdTF = cccdTF;
	}

	public JComboBox getGioiTinhComboBox() {
		return gioiTinhComboBox;
	}

	public void setGioiTinhComboBox(JComboBox gioiTinhComboBox) {
		this.gioiTinhComboBox = gioiTinhComboBox;
	}

	public JComboBox getCapBacComboBox() {
		return capBacComboBox;
	}

	public void setCapBacComboBox(JComboBox capBacComboBox) {
		this.capBacComboBox = capBacComboBox;
	}

	public JComboBox getMaPBComboBox() {
		return maPBComboBox;
	}

	public void setMaPBComboBox(JComboBox maPBComboBox) {
		this.maPBComboBox = maPBComboBox;
	}

}
