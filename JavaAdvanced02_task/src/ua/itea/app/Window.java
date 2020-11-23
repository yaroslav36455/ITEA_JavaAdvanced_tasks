package ua.itea.app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame {
	private Config config;
	private JLabel[] phrases;
	private JButton loadButton;
	private JButton saveButton;
	private JComboBox<LocaleFlag> comboBox;

	public Window() {
		
		phrases = createLabels();
		comboBox = createComboBox();
		loadButton = createLoadButton();
		saveButton = createSaveButton();
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(loadButton, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(saveButton, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.fill = GridBagConstraints.NONE;
		add(comboBox, gbc);
		
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		add(new JLabel(new ImageIcon("images/Pudge_icon.png")), gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		for (int i = 0; i < phrases.length; i++) {
			gbc.gridx = 0;
			gbc.gridy = i + 2;			
			add(phrases[i], gbc);
		}
		
		setSize(300, 250);
		setLocationByPlatform(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		config = new Config();
		config.load();
		updateView(config.getLocale());
		comboBox.setSelectedIndex(LocaleFlag.getFlag(config.getLocale()).ordinal());
	}
	
	private JButton createLoadButton() {
		JButton button = new JButton();
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				config.load();
				updateView(config.getLocale());
				comboBox.setSelectedIndex(LocaleFlag.getFlag(config.getLocale()).ordinal());
			}
		});
		
		return button;
	}
	
	private JButton createSaveButton() {
		JButton button = new JButton();
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				config.save();
			}
		});
		
		return button;
	}

	private JLabel[] createLabels() {
		JLabel[] labels = new JLabel[3];
		
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel();
			
		}
		
		return labels;
	}
	
	private JComboBox<LocaleFlag> createComboBox() {
		JComboBox<LocaleFlag> comboBox = new JComboBox<>(LocaleFlag.values());
		
		comboBox.setRenderer(new OwnListCellRenderer());
		comboBox.setMaximumRowCount(10);
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LocaleFlag localeFlag = LocaleFlag.values()[comboBox.getSelectedIndex()];
				
				config.setLocale(localeFlag.convertToLocale());
				updateView(config.getLocale());
			}
		});
		
		return comboBox;
	}
	
	public void updateView(Locale locale) {
		setPhrases(locale);
		setInterface(locale);
	}
	
	public void setPhrases(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("PudgePhrasesBundle", locale,
																 new OwnControl("utf-8"));
		for (int i = 0; i < phrases.length; i++) {
			phrases[i].setText(resourceBundle.getString("phrase" + (i + 1)));
		}
	}
	
	public void setInterface(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("InterfaceBundle", locale,
				 										         new OwnControl("utf-8"));
		
		saveButton.setText(resourceBundle.getString("saveButton"));
		loadButton.setText(resourceBundle.getString("loadButton"));
	}
}