package ua.itea.model;

public class Plugin {
	private ArtifactId artifactId;
	private Version version;
	private Configuration configuration;
	private Executions executions;
	
	@Override
	public String toString() {
		return "Plugin [artifactId=" + artifactId + ", version=" + version + ", configuration=" + configuration
				+ ", executions=" + executions + "]";
	}
}
