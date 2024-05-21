package view;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class MyCustomRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Kiểm tra cột và set color tương ứng
        if (column == 7) { // Cột thứ 3 (index từ 0)
            c.setBackground(new Color(135, 206, 250));
        } else {
            c.setBackground(Color.WHITE);
        }
        
        return c;
    }
}