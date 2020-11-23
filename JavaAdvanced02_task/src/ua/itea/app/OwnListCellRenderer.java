package ua.itea.app;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class OwnListCellRenderer implements ListCellRenderer<LocaleFlag> {

	@Override
	public Component getListCellRendererComponent(JList<? extends LocaleFlag> list,
								LocaleFlag value, int index,
								boolean isSelected, boolean cellHasFocus) {
		JLabel label = value.getFlagLabel();
		
		if (isSelected) {
			label.setBackground(list.getSelectionBackground());
		} else {
			label.setBackground(list.getBackground());
		}
		
		return label;
	}

}
