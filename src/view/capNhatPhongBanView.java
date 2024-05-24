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
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.datechooser.*;

import javax.swing.JTextField;

import controller.capNhatPhongBanController;
import model.phongBan;

import java.awt.Cursor;

public class capNhatPhongBanView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tenPhongBanTextField;
	private JTextField ngThanhLapTF;
	private JTextField maTruongPhongTF;
	private JTextField ngNhanChucTF;
	private DateChooser ngThanhLap = new DateChooser();
	private DateChooser ngNhanChuc = new DateChooser();
	private JButton okButton;
	private JButton cancelButton;
	private ActionListener ac;
	private JTextField maPBPhongBanTextField;

	public capNhatPhongBanView(phongBan phongBan) {
		ImageIcon favicon = new ImageIcon(mainView.class.getResource("/img/cc.png"));
		setIconImage(favicon.getImage());
		setTitle("HRM_TENPM");
		ac = new capNhatPhongBanController(this);

		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 803, 424);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBounds(0, 0, 789, 387);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setOpaque(false);
			buttonPane.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
			buttonPane.setBounds(7, 318, 788, 69);
			contentPanel.add(buttonPane);
			{
				okButton = new JButton("Cập nhật");
				okButton.addActionListener(ac);
				okButton.setFocusable(false);
				okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				okButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
				okButton.setBounds(586, 20, 82, 29);
				okButton.setBackground(new Color(102, 205, 170));
				okButton.setFont(new Font("JetBrains Mono", Font.PLAIN, 14));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
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
				cancelButton.setBounds(680, 20, 82, 29);
				cancelButton.setBackground(new Color(102, 205, 170));
				cancelButton.setFont(new Font("JetBrains Mono", Font.PLAIN, 14));
				buttonPane.add(cancelButton);
			}
		}

		JPanel formPanel = new JPanel();
		formPanel.setBackground(new Color(255, 255, 255));
		formPanel.setBounds(0, 0, 519, 279);
		contentPanel.add(formPanel);
		formPanel.setLayout(null);

		JPanel header = new JPanel();
		header.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		header.setBackground(new Color(102, 205, 170));
		header.setBounds(38, 0, 243, 49);
		formPanel.add(header);
		header.setLayout(null);

		JLabel headerLabel = new JLabel("THÔNG TIN PHÒNG BAN");
		headerLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 19));
		headerLabel.setBounds(10, 10, 259, 30);
		header.add(headerLabel);

		JLabel tenPhongLabel = new JLabel("Tên Phòng:");
		tenPhongLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		tenPhongLabel.setBounds(38, 115, 127, 26);
		formPanel.add(tenPhongLabel);

		JLabel ngayThanhLapLabel = new JLabel("Ngày thành lập:");
		ngayThanhLapLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		ngayThanhLapLabel.setBounds(38, 161, 141, 26);
		formPanel.add(ngayThanhLapLabel);

		JLabel maTruongPhongLabel = new JLabel("Mã trưởng phòng:");
		maTruongPhongLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		maTruongPhongLabel.setBounds(38, 207, 141, 26);
		formPanel.add(maTruongPhongLabel);

		JLabel ngayNhanChucLabel = new JLabel("Ngày nhận chức:");
		ngayNhanChucLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		ngayNhanChucLabel.setBounds(38, 253, 141, 26);
		formPanel.add(ngayNhanChucLabel);

		maPBPhongBanTextField = new JTextField();
		maPBPhongBanTextField.setEditable(false);
		if (String.valueOf(phongBan.getMaPB()) != null) {
			maPBPhongBanTextField.setText(String.valueOf(phongBan.getMaPB()));
		}
		maPBPhongBanTextField.setFont(new Font("Dialog", Font.PLAIN, 15));
		maPBPhongBanTextField.setColumns(10);
		maPBPhongBanTextField.setBorder(new LineBorder(new Color(0, 0, 0)));
		maPBPhongBanTextField.setBounds(187, 72, 261, 26);
		formPanel.add(maPBPhongBanTextField);

		tenPhongBanTextField = new JTextField();
		tenPhongBanTextField.setText(phongBan.getTenPB());
		tenPhongBanTextField.setBorder(new LineBorder(new Color(0, 0, 0)));
		tenPhongBanTextField.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		tenPhongBanTextField.setBounds(187, 115, 261, 26);
		formPanel.add(tenPhongBanTextField);
		tenPhongBanTextField.setColumns(10);

		ngThanhLapTF = new JTextField();
		ngThanhLapTF.setText(String.valueOf(phongBan.getNgThanhLap()));
		ngThanhLapTF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ngThanhLapTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		ngThanhLapTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		ngThanhLapTF.setColumns(10);
		ngThanhLapTF.setBounds(187, 161, 261, 26);
		formPanel.add(ngThanhLapTF);
		ngThanhLap.setTextRefernce(ngThanhLapTF);
		ngThanhLap.setForeground(new Color(255, 69, 0));

		maTruongPhongTF = new JTextField();
		if (String.valueOf(phongBan.getMaTruongPhong()).equals("0")) {
			maTruongPhongTF.setText("null");
		} else {
			maTruongPhongTF.setText(String.valueOf(phongBan.getMaTruongPhong()));
		}
		maTruongPhongTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		maTruongPhongTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		maTruongPhongTF.setColumns(10);
		maTruongPhongTF.setBounds(187, 207, 261, 26);
		formPanel.add(maTruongPhongTF);

		ngNhanChucTF = new JTextField();
		if (phongBan.getNgNhanChuc() == null) {
			ngNhanChucTF.setText("null");
		} else {
			ngNhanChucTF.setText(String.valueOf(phongBan.getNgNhanChuc()));

		}
		ngNhanChucTF.setText("");
		ngNhanChucTF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ngNhanChucTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		ngNhanChucTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		ngNhanChucTF.setColumns(10);
		ngNhanChucTF.setBounds(187, 253, 261, 26);
		ngNhanChuc.setTextRefernce(ngNhanChucTF);
		ngNhanChuc.setForeground(new Color(255, 69, 0));
		ngNhanChuc.setSelectedDate(Date.valueOf(ngNhanChucTF.getText()));
		formPanel.add(ngNhanChucTF);

		JLabel maPhongLabel = new JLabel("Mã Phòng Ban");
		maPhongLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		maPhongLabel.setBounds(38, 72, 127, 26);
		formPanel.add(maPhongLabel);

		{
			JPanel imgPanel = new JPanel();
			imgPanel.setBackground(new Color(255, 255, 255));
			imgPanel.setBounds(525, 0, 270, 279);
			contentPanel.add(imgPanel);
			imgPanel.setLayout(null);

			JLabel imgLabel = new JLabel("");
			imgLabel.setIcon(new ImageIcon(themPhongBanView.class.getResource("/img/partners.png")));
			imgLabel.setBounds(0, 10, 260, 259);
			imgPanel.add(imgLabel);
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
				mainView.setTabPhongBan();

			}
		});
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	public JTextField getTenPhongBanTextField() {
		return tenPhongBanTextField;
	}

	public void setTenPhongBanTextField(JTextField tenPhongBanTextField) {
		this.tenPhongBanTextField = tenPhongBanTextField;
	}

	public JTextField getNgThanhLapTF() {
		return ngThanhLapTF;
	}

	public void setNgThanhLapTF(JTextField ngThanhLapTF) {
		this.ngThanhLapTF = ngThanhLapTF;
	}

	public JTextField getMaTruongPhongTF() {
		return maTruongPhongTF;
	}

	public void setMaTruongPhongTF(JTextField maTruongPhongTF) {
		this.maTruongPhongTF = maTruongPhongTF;
	}

	public JTextField getNgNhanChucTF() {
		return ngNhanChucTF;
	}

	public void setNgNhanChucTF(JTextField ngNhanChucTF) {
		this.ngNhanChucTF = ngNhanChucTF;
	}

	public DateChooser getNgThanhLap() {
		return ngThanhLap;
	}

	public void setNgThanhLap(DateChooser ngThanhLap) {
		this.ngThanhLap = ngThanhLap;
	}

	public DateChooser getNgNhanChuc() {
		return ngNhanChuc;
	}

	public void setNgNhanChuc(DateChooser ngNhanChuc) {
		this.ngNhanChuc = ngNhanChuc;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public ActionListener getAc() {
		return ac;
	}

	public void setAc(ActionListener ac) {
		this.ac = ac;
	}

	public JTextField getMaPBPhongBanTextField() {
		return maPBPhongBanTextField;
	}

	public void setMaPBPhongBanTextField(JTextField maPBPhongBanTextField) {
		this.maPBPhongBanTextField = maPBPhongBanTextField;
	}

}
