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
		setSize(420, 391);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 317, 413, 45);
		panel.setLayout(null);
		getContentPane().add(panel);

		progressBar = new JProgressBar(0, 100);
		progressBar.setBackground(Color.WHITE);
		progressBar.setForeground(Color.CYAN);
		progressBar.setBounds(0, 10, 413, 30);
		panel.add(progressBar);
		progressBar.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
		progressBar.setStringPainted(true);
		progressBar.setString("Loading...");

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 413, 324);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(loadingView.class.getResource("/img/Logo.png")));
		lblNewLabel.setBounds(0, 0, 403, 324);
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
