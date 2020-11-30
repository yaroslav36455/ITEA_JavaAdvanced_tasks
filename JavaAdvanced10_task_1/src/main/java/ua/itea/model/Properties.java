package ua.itea.model;

public class Properties {
	private ProjectBuildSourceEncoding projectBuildSourceEncoding;
	private MavenCompilerSource mavenCompilerSource;
	private MavenCompilerTarget mavenCompilerTarget;
	
	@Override
	public String toString() {
		return "Properties [project.build.source.encoding=" + projectBuildSourceEncoding
				+ ", maven.compiler.source=" + mavenCompilerSource
				+ ", maven.compiler.target=" + mavenCompilerTarget + "]";
	}
}
