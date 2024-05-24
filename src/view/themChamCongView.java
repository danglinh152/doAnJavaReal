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
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.datechooser.*;

import javax.swing.JTextField;

import controller.themChamCongController;
import controller.themPhongBanController;

import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class themChamCongView extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField maNVTF;
	private JTextField soNgayLamViecTF;
	private JTextField soNgayNghiTF;
	private JButton okButton;
	private JButton cancelButton;
	private ActionListener ac;
	private JTextField soGioTangCaTF;
	private JTextField soNgayDiTreTF;
	private JTextField thangLamViecTF;

	public themChamCongView() {
		ImageIcon favicon = new ImageIcon(mainView.class.getResource("/img/cc.png"));
		setIconImage(favicon.getImage());
		setTitle("HRM_TENPM");
		ac = new themChamCongController(this);

		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 803, 490);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBounds(0, 0, 789, 379);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JPanel formPanel = new JPanel();
		formPanel.setBackground(new Color(255, 255, 255));
		formPanel.setBounds(0, 0, 498, 383);
		contentPanel.add(formPanel);
		formPanel.setLayout(null);

		JPanel header = new JPanel();
		header.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		header.setBackground(new Color(102, 205, 170));
		header.setBounds(38, 0, 317, 49);
		formPanel.add(header);
		header.setLayout(null);

		JLabel headerLabel = new JLabel("CHẤM CÔNG CHO NHÂN VIÊN");
		headerLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 19));
		headerLabel.setBounds(10, 10, 307, 30);
		header.add(headerLabel);

		JLabel maNV = new JLabel("Mã nhân viên:");
		maNV.setHorizontalAlignment(SwingConstants.LEFT);
		maNV.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		maNV.setBounds(38, 93, 127, 26);
		formPanel.add(maNV);

		JLabel thangLV = new JLabel("Tháng làm việc:");
		thangLV.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		thangLV.setBounds(38, 139, 141, 26);
		formPanel.add(thangLV);

		JLabel soNgayLV = new JLabel("Số ngày làm việc:");
		soNgayLV.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		soNgayLV.setBounds(38, 185, 155, 26);
		formPanel.add(soNgayLV);

		JLabel soNgayNghi = new JLabel("Số ngày nghỉ:");
		soNgayNghi.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		soNgayNghi.setBounds(38, 231, 141, 26);
		formPanel.add(soNgayNghi);

		maNVTF = new JTextField();
		maNVTF.setEditable(false);
		maNVTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		maNVTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		maNVTF.setBounds(210, 93, 240, 26);
		formPanel.add(maNVTF);
		maNVTF.setColumns(10);

		soNgayLamViecTF = new JTextField();
		soNgayLamViecTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		soNgayLamViecTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		soNgayLamViecTF.setColumns(10);
		soNgayLamViecTF.setBounds(210, 185, 240, 26);
		formPanel.add(soNgayLamViecTF);

		soNgayNghiTF = new JTextField();
		soNgayNghiTF.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		soNgayNghiTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		soNgayNghiTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		soNgayNghiTF.setColumns(10);
		soNgayNghiTF.setBounds(210, 231, 238, 26);
		formPanel.add(soNgayNghiTF);

		JLabel soGioTC = new JLabel("Số giờ tăng ca:");
		soGioTC.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		soGioTC.setBounds(38, 277, 141, 26);
		formPanel.add(soGioTC);

		soGioTangCaTF = new JTextField();
		soGioTangCaTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		soGioTangCaTF.setColumns(10);
		soGioTangCaTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		soGioTangCaTF.setBounds(208, 277, 240, 26);
		formPanel.add(soGioTangCaTF);

		JLabel soNgayDiTre = new JLabel("Số ngày đi trễ:");
		soNgayDiTre.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		soNgayDiTre.setBounds(38, 323, 141, 26);
		formPanel.add(soNgayDiTre);

		soNgayDiTreTF = new JTextField();
		soNgayDiTreTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		soNgayDiTreTF.setColumns(10);
		soNgayDiTreTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		soNgayDiTreTF.setBounds(208, 323, 240, 26);
		formPanel.add(soNgayDiTreTF);

		thangLamViecTF = new JTextField();
		thangLamViecTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		thangLamViecTF.setColumns(10);
		thangLamViecTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		thangLamViecTF.setBounds(210, 139, 240, 26);
		formPanel.add(thangLamViecTF);
		{
			JPanel imgPanel = new JPanel();
			imgPanel.setBackground(new Color(255, 255, 255));
			imgPanel.setBounds(508, 0, 287, 383);
			contentPanel.add(imgPanel);
			imgPanel.setLayout(null);

			JLabel imgLabel = new JLabel("");
			imgLabel.setIcon(new ImageIcon(themChamCongView.class.getResource("/img/planning.png")));
			imgLabel.setBounds(0, 90, 260, 259);
			imgPanel.add(imgLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 389, 788, 69);
			getContentPane().add(buttonPane);
			buttonPane.setOpaque(false);
			buttonPane.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
			{
				okButton = new JButton("Chấm công");
				okButton.addActionListener(ac); // add ac
				okButton.setFocusable(false);
				okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				okButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
				okButton.setBounds(585, 20, 83, 29);
				okButton.setBackground(new Color(102, 205, 170));
				okButton.setFont(new Font("JetBrains Mono", Font.PLAIN, 14));
				buttonPane.setLayout(null);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Hủy");
				cancelButton.addActionListener(ac); // add ac
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
				mainView.setTabChamCong();

			}
		});

		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}

	public JTextField getMaNVTF() {
		return maNVTF;
	}

	public void setMaNVTF(JTextField maNVTF) {
		this.maNVTF = maNVTF;
	}

	public JTextField getSoNgayLamViecTF() {
		return soNgayLamViecTF;
	}

	public void setSoNgayLamViecTF(JTextField soNgayLamViecTF) {
		this.soNgayLamViecTF = soNgayLamViecTF;
	}

	public JTextField getSoNgayNghiTF() {
		return soNgayNghiTF;
	}

	public void setSoNgayNghiTF(JTextField soNgayNghiTF) {
		this.soNgayNghiTF = soNgayNghiTF;
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

	public JTextField getSoGioTangCaTF() {
		return soGioTangCaTF;
	}

	public void setSoGioTangCaTF(JTextField soGioTangCaTF) {
		this.soGioTangCaTF = soGioTangCaTF;
	}

	public JTextField getSoNgayDiTreTF() {
		return soNgayDiTreTF;
	}

	public void setSoNgayDiTreTF(JTextField soNgayDiTreTF) {
		this.soNgayDiTreTF = soNgayDiTreTF;
	}

	public JTextField getThangLamViecTF() {
		return thangLamViecTF;
	}

	public void setThangLamViecTF(JTextField thangLamViecTF) {
		this.thangLamViecTF = thangLamViecTF;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

}
