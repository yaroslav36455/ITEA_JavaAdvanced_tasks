package ua.itea.model;

public class Project {
	private ProjectAttributes attributes;
	
	private ModelVersion modelVersion;
	private GroupId groupId;
	private ArtifactId artifactId;
	private Version version;
	private Name name;
	
	private Properties properties;
	private Dependencies dependencies;
	private Build build;
	
	@Override
	public String toString() {
		return "Project [attributes=" + attributes + ", modelVersion=" + modelVersion + ", groupId=" + groupId
				+ ", artifactId=" + artifactId + ", version=" + version + ", name=" + name + ", properties="
				+ properties + ", dependencies=" + dependencies + ", build=" + build + "]";
	}
}
