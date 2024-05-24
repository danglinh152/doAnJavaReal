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

import controller.capNhatTrinhDoController;
import controller.themPhongBanController;

import model.nhanVien_kyNang;

import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class capNhatTrinhDoView extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField maNVTF;
	private JTextField levelTF;
	private JButton okButton;
	private JButton cancelButton;
	private ActionListener ac;
	private nhanVien_kyNang nhanVien_kyNang;
	private JComboBox kyNangComboBox;
	private JComboBox capBacComboBox;

	public capNhatTrinhDoView(nhanVien_kyNang nhanVien_kyNang) {
		ImageIcon favicon = new ImageIcon(mainView.class.getResource("/img/cc.png"));
		setIconImage(favicon.getImage());
		setTitle("HRM_TENPM");
		this.nhanVien_kyNang = nhanVien_kyNang;

		ac = new capNhatTrinhDoController(this);

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
		header.setBounds(38, 0, 204, 49);
		formPanel.add(header);
		header.setLayout(null);

		JLabel headerLabel = new JLabel("CẬP NHẬT KỸ NĂNG");
		headerLabel.setFont(new Font("JetBrains Mono", Font.PLAIN, 19));
		headerLabel.setBounds(10, 10, 259, 30);
		header.add(headerLabel);

		JLabel maNV = new JLabel("Mã nhân viên:");
		maNV.setHorizontalAlignment(SwingConstants.LEFT);
		maNV.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		maNV.setBounds(38, 93, 127, 26);
		formPanel.add(maNV);

		JLabel tenKyNang = new JLabel("Tên kỹ năng:");
		tenKyNang.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		tenKyNang.setBounds(38, 139, 141, 26);
		formPanel.add(tenKyNang);

		JLabel level = new JLabel("Cấp bậc:");
		level.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		level.setBounds(38, 185, 155, 26);
		formPanel.add(level);

		maNVTF = new JTextField();
		maNVTF.setEditable(false);
		maNVTF.setBorder(new LineBorder(new Color(0, 0, 0)));
		maNVTF.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		maNVTF.setBounds(210, 93, 240, 26);
		maNVTF.setText(String.valueOf(nhanVien_kyNang.getMaNV()));
		formPanel.add(maNVTF);
		maNVTF.setColumns(10);

		String kn[] = { "Tin học", "Tiếng anh" };
		kyNangComboBox = new JComboBox(kn);
		kyNangComboBox.setBounds(210, 144, 98, 21);
		if (nhanVien_kyNang.getTenKyNang().equals("tin học")) {
			kyNangComboBox.setSelectedIndex(0);
		} else {
			kyNangComboBox.setSelectedIndex(1);
		}
		formPanel.add(kyNangComboBox);

		if (String.valueOf(kyNangComboBox.getSelectedItem()).equals("Tin học")) {
			String cb[] = { "A", "B", "C" };
			capBacComboBox = new JComboBox(cb);
			capBacComboBox.setBounds(210, 185, 98, 21);
			if (nhanVien_kyNang.getCapBacKyNang().equals("A") && nhanVien_kyNang.getTenKyNang().equals("Tin học")) {
				capBacComboBox.setSelectedIndex(0);
			} else if (nhanVien_kyNang.getCapBacKyNang().equals("B")
					&& nhanVien_kyNang.getTenKyNang().equals("Tin học")) {
				capBacComboBox.setSelectedIndex(1);
			} else if (nhanVien_kyNang.getCapBacKyNang().equals("C")
					&& nhanVien_kyNang.getTenKyNang().equals("Tin học")) {
				capBacComboBox.setSelectedIndex(2);
			}
			formPanel.add(capBacComboBox);
		} else {
			String cb[] = { "A1", "A2", "B1", "B2", "C1", "C2" };
			capBacComboBox = new JComboBox(cb);
			capBacComboBox.setBounds(210, 185, 98, 21);
			if (nhanVien_kyNang.getCapBacKyNang().equals("A1") && nhanVien_kyNang.getTenKyNang().equals("Tiếng anh")) {
				capBacComboBox.setSelectedIndex(0);
			} else if (nhanVien_kyNang.getCapBacKyNang().equals("A2")
					&& nhanVien_kyNang.getTenKyNang().equals("Tiếng anh")) {
				capBacComboBox.setSelectedIndex(1);
			} else if (nhanVien_kyNang.getCapBacKyNang().equals("B1")
					&& nhanVien_kyNang.getTenKyNang().equals("Tiếng anh")) {
				capBacComboBox.setSelectedIndex(2);
			} else if (nhanVien_kyNang.getCapBacKyNang().equals("B2")
					&& nhanVien_kyNang.getTenKyNang().equals("Tiếng anh")) {
				capBacComboBox.setSelectedIndex(3);
			} else if (nhanVien_kyNang.getCapBacKyNang().equals("C1")
					&& nhanVien_kyNang.getTenKyNang().equals("Tiếng anh")) {
				capBacComboBox.setSelectedIndex(4);
			} else if (nhanVien_kyNang.getCapBacKyNang().equals("C2")
					&& nhanVien_kyNang.getTenKyNang().equals("Tiếng anh")) {
				capBacComboBox.setSelectedIndex(5);
			}

			formPanel.add(capBacComboBox);
		}

		String maPB[] = { "P01", "P02", "..." };
		{
			JPanel imgPanel = new JPanel();
			imgPanel.setBackground(new Color(255, 255, 255));
			imgPanel.setBounds(508, 0, 287, 285);
			contentPanel.add(imgPanel);
			imgPanel.setLayout(null);

			JLabel imgLabel = new JLabel("");
			imgLabel.setIcon(new ImageIcon(capNhatTrinhDoView.class.getResource("/img/decision.png")));
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
				okButton.setBounds(590, 20, 83, 29);
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
				mainView.setTabTrinhDo();

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
		return levelTF;
	}

	public void setSoNgayLamViecTF(JTextField soNgayLamViecTF) {
		this.levelTF = soNgayLamViecTF;
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

	public JTextField getLevelTF() {
		return levelTF;
	}

	public void setLevelTF(JTextField levelTF) {
		this.levelTF = levelTF;
	}

	public nhanVien_kyNang getNhanVien_kyNang() {
		return nhanVien_kyNang;
	}

	public void setNhanVien_kyNang(nhanVien_kyNang nhanVien_kyNang) {
		this.nhanVien_kyNang = nhanVien_kyNang;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JComboBox getKyNangComboBox() {
		return kyNangComboBox;
	}

	public void setKyNangComboBox(JComboBox kyNangComboBox) {
		this.kyNangComboBox = kyNangComboBox;
	}

	public JComboBox getCapBacComboBox() {
		return capBacComboBox;
	}

	public void setCapBacComboBox(JComboBox capBacComboBox) {
		this.capBacComboBox = capBacComboBox;
	}

}
