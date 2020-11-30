package ua.itea.model;

public class ProjectAttributes {
	private String xmlns;
	private String xmlnsXsi;
	private String xsiSchemaLocation;
	
	@Override
	public String toString() {
		return "ProjectAttributes [xmlns=" + xmlns + ", xmlns:xsi=" + xmlnsXsi + ", xsi:schemaLocation="
				+ xsiSchemaLocation + "]";
	}
}
