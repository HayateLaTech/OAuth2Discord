package bell.oauth.discord.domain;

public class Connection {
	
	private String id, name, type;
	private boolean verified, friend_sync;
	private Integer visibility;
	
	@Override
	public String toString() {
		return this.name
				+ "\nID: " + this.id
				+ "\nType: " + this.type
				+ "\nVerified: " + this.verified
				+ "\nVisibility: " + this.visibility;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public boolean isFriend_sync() {
		return friend_sync;
	}
	public void setFriend_sync(boolean friend_sync) {
		this.friend_sync = friend_sync;
	}
	public Integer getVisibility() {
		return visibility;
	}
	public void setVisibility(Integer visibility) {
		this.visibility = visibility;
	}

}
