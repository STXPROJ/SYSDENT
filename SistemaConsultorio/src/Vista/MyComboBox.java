package Vista;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MyComboBox extends JLabel implements ListCellRenderer {
    private String title;

    public MyComboBox(String newTitle) {
        title = newTitle;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
        if (index == -1 && value == null) setText(title );
        else setText(value.toString());
        return this;
    }
}