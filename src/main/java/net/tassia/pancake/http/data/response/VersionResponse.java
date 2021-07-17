package net.tassia.pancake.http.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.tassia.pancake.Version;

public final class VersionResponse {

	@JsonProperty("major")
	public final int major;

	@JsonProperty("minor")
	public final int minor;

	@JsonProperty("patch")
	public final int patch;

	@JsonProperty("build")
	public final int build;



	@JsonProperty("timestamp")
	public final long timestamp;

	@JsonProperty("extension")
	public final String extension;

	@JsonProperty("snapshot")
	public final boolean isSnapshot;



	@JsonProperty("git_head")
	public final String gitHead;

	@JsonProperty("git_branch")
	public final String gitBranch;



	public VersionResponse(Version version) {
		this.major = version.major;
		this.minor = version.minor;
		this.patch = version.patch;
		this.build = version.build;

		this.timestamp = version.timestamp;
		this.extension = version.extension;
		this.isSnapshot = version.isSnapshot;

		this.gitHead = version.gitFullHead;
		this.gitBranch = version.gitBranch;
	}

}
