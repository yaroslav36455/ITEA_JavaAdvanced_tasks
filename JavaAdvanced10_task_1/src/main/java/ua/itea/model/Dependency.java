package ua.itea.model;

public class Dependency {
	private GroupId groupId;
	private ArtifactId artifactId;
	private Version version;
	private Scope scope;
	
	@Override
	public String toString() {
		return "Dependency [groupId=" + groupId + ", artifactId=" + artifactId + ", version=" + version + ", scope="
				+ scope + "]";
	}
}
