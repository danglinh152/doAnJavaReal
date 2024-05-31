package view;

import javax.swing.*;

import java.awt.*;
import view.loginView;

public class loadingView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar;
	private Thread loadingThread;
	private Timer disposeTimer;

	public loadingView() {
		ImageIcon favicon = new ImageIcon(mainView.class.getResource("/img/cc.png"));
		this.setIconImage(favicon.getImage());
		setBackground(new Color(255, 255, 255));
		setTitle("HRM_TENPM");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(393, 435);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 378, 375, 20);
		panel.setLayout(null);
		getContentPane().add(panel);

		progressBar = new JProgressBar(0, 100);
		progressBar.setBorderPainted(false);
		progressBar.setBackground(new Color(255, 255, 255));
		progressBar.setForeground(new Color(102, 205, 170));
		progressBar.setBounds(0, 0, 375, 20);
		panel.add(progressBar);
		progressBar.setFont(new Font("Dialog", Font.ITALIC, 13));
		progressBar.setStringPainted(true);
		progressBar.setString("Loading...");

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 375, 379);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 380, 379);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(loadingView.class.getResource("/img/cc.png")));
		panel_1.add(lblNewLabel);

		startLoading();

		disposeTimer = new Timer(2100, e -> {
			dispose();
			new loginView();

		});
		disposeTimer.setRepeats(false);
		disposeTimer.start();
		this.setVisible(true);
	}

	private void startLoading() {
		loadingThread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 100;) {
					progressBar.setValue(i);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i += 3;
				}
			}
		});
		loadingThread.start();
	}
}
