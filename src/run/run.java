package run;

import javax.swing.SwingUtilities;

import view.mainView;
import view.loadingView;

public class run {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new loadingView();
				;
				;
				;

			}
		});
	}
}
