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

import controller.capNhatHopDongController;
import controller.themPhongBanController;

import model.hopDong;

import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class capNhatHopDongView extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField maNVTF;
	private JTextField ngKTTF;
	private JButton okButton;
	private JButton cancelButton;
	private ActionListener ac;
	private JTextField ngBDTF;
	private DateChooser ngBatDau = new DateChooser();
	private DateChooser ngKetThuc = new DateChooser();
	private hopDong hopDong;
	private JTextField maHDTF;

	public capNhatHopDongView(hopDong hopDong) {
		this.hopDong = hopDong;

		ac = new capNhatHopDongController(this);
		maHDTF = new JTextField();
		maHDTF.setText(String.valueOf(hopDong.getMaHD()));
		maHDTF.setVisible(false);
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

		JLabel headerLabel = new JLabel("CẬP NHẬT HỢP ĐỒNG");
		headerLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 19));
		headerLabel.setBounds(10, 10, 259, 30);
		header.add(headerLabel);

		JLabel maNV = new JLabel("Mã nhân viên:");
		maNV.setHorizontalAlignment(SwingConstants.LEFT);
		maNV.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		maNV.setBounds(38, 93, 127, 26);
		formPanel.add(maNV);

		JLabel ngBatDau = new JLabel("Ngày bắt đầu hợp đồng:");
		ngBatDau.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		ngBatDau.setBounds(38, 139, 200, 26);
		formPanel.add(ngBatDau);

		JLabel level = new JLabel("Ngày kết thúc hợp đồng:");
		level.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		level.setBounds(38, 185, 214, 26);
		formPanel.add(level);

		maNVTF = new JTextField();
		maNVTF.setEditable(false);
		maNVTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		maNVTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		maNVTF.setBounds(262, 93, 188, 26);
		formPanel.add(maNVTF);
		maNVTF.setText(String.valueOf(hopDong.getNhanVien().getMaNV()));
		maNVTF.setColumns(10);

		ngKTTF = new JTextField();
		ngKTTF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ngKTTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		ngKTTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		ngKTTF.setColumns(10);
		ngKTTF.setBounds(262, 185, 188, 26);
		formPanel.add(ngKTTF);
		this.ngKetThuc.setTextRefernce(ngKTTF);
		this.ngKetThuc.setForeground(new Color(255, 69, 0));

		ngBDTF = new JTextField();
		ngBDTF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ngBDTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		ngBDTF.setColumns(10);
		ngBDTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		ngBDTF.setBounds(262, 139, 188, 26);
		formPanel.add(ngBDTF);
		this.ngBatDau.setTextRefernce(ngBDTF);
		this.ngBatDau.setForeground(new Color(255, 69, 0));

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
			imgLabel.setIcon(new ImageIcon(capNhatHopDongView.class.getResource("/img/law-firm.png")));
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
				mainView.setTabHopDong();

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
		return ngKTTF;
	}

	public void setSoNgayLamViecTF(JTextField soNgayLamViecTF) {
		this.ngKTTF = soNgayLamViecTF;
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

	public JTextField getNgKTTF() {
		return ngKTTF;
	}

	public void setNgKTTF(JTextField ngKTTF) {
		this.ngKTTF = ngKTTF;
	}

	public JTextField getNgBDTF() {
		return ngBDTF;
	}

	public void setNgBDTF(JTextField ngBDTF) {
		this.ngBDTF = ngBDTF;
	}

	public DateChooser getNgBatDau() {
		return ngBatDau;
	}

	public void setNgBatDau(DateChooser ngBatDau) {
		this.ngBatDau = ngBatDau;
	}

	public DateChooser getNgKetThuc() {
		return ngKetThuc;
	}

	public void setNgKetThuc(DateChooser ngKetThuc) {
		this.ngKetThuc = ngKetThuc;
	}

	public hopDong getHopDong() {
		return hopDong;
	}

	public void setHopDong(hopDong hopDong) {
		this.hopDong = hopDong;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JTextField getMaHDTF() {
		return maHDTF;
	}

	public void setMaHDTF(JTextField maHDTF) {
		this.maHDTF = maHDTF;
	}

}
