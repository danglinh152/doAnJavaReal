package view;

import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;

public class rendererTableGuiYC extends DefaultTableCellRenderer {
	private static final int MAX_COLUMN_WIDTH = 600; // Chiều rộng tối đa của cột
	private static final int MARGIN_RIGHT = 50; // Khoảng cách margin bên phải
	private static final int MAX_LINES = 3; // Số dòng tối đa

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		label.setVerticalAlignment(JLabel.TOP);

		String text = (String) value;
		FontMetrics metrics = label.getFontMetrics(label.getFont());
		int wrapStringWidth = SwingUtilities.computeStringWidth(metrics, text);
		if (wrapStringWidth >= MAX_COLUMN_WIDTH) {
			int lines = Math.min((wrapStringWidth / MAX_COLUMN_WIDTH) + 1, MAX_LINES);
			label.setText("<html><div style='width:" + (MAX_COLUMN_WIDTH - MARGIN_RIGHT) + "px; margin: 0 "
					+ MARGIN_RIGHT + "px 0 0;'>" + text + "</div></html>");
			label.setToolTipText(text);
			label.setBorder(this.getBorder());
			label.setSize(MAX_COLUMN_WIDTH, (int) (lines * metrics.getHeight()));
		}
		return label;
	}
}
