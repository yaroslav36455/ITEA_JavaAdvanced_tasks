package ua.itea.app;

import java.awt.MediaTracker;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public enum LocaleFlag {
	DE("germany_icon.png", "de", "DE"),
	RU("russia_icon.png", "ru", "UA"),
	UA("ukraine_icon.png", "ua", "UA"),
	US("united_states_icon.png", "en", "US");
	
	private JLabel label;
	private ImageIcon icon;
	private String flagFile;
	
	private Locale locale;
	private String language;
	private String country;
	
	private LocaleFlag(String flagFile, String language, String country) {
		this.flagFile = flagFile;
		this.language = language;
		this.country = country;
		
		this.label = new JLabel();
		this.label.setOpaque(true);
	}
	
	public JLabel getFlagLabel() {
		if (icon == null) {
			icon = new ImageIcon("images/" + flagFile);
			
			if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
				System.err.println("File " + flagFile + " not loaded");
			} else {
				label.setIcon(icon);
			}
		}
		
		return label;
	}
	
	public Locale convertToLocale() {
		if (locale == null) {
			locale = new Locale(language, country);
		}
		
		return locale;
	}
	
	public static LocaleFlag getFlag(Locale locale) {
		switch (locale.getLanguage()) {
		case "de":
			return DE;
			
		case "ru":
			return RU;
			
		case "ua":
			return UA;
			
		case "en":
			return US;
			
		default:
			throw new RuntimeException("LocaleFlag not found");
		}
	}
	
	public static Locale getSuitableLocale(Locale locale) {
		switch (locale.getLanguage()) {
		case "de":
			return DE.convertToLocale();
			
		case "ru":
			return RU.convertToLocale();
			
		case "ua":
			return UA.convertToLocale();
			
		case "en":
		default:
			return US.convertToLocale();
		}
	}
}
