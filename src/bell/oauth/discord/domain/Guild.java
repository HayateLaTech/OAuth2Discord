package bell.oauth.discord.domain;

public class Guild {
	private String id, name, icon;
	private Integer permissions;
	private boolean owner;
	
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getPermissions() {
		return permissions;
	}
	public void setPermissions(Integer permissions) {
		this.permissions = permissions;
	}
	public boolean isOwner() {
		return owner;
	}
	public void setOwner(boolean owner) {
		this.owner = owner;
	}
	@Override
	public String toString() {
		return this.name
				+ "\nID: " + this.id
				+ "\nIcon: " + this.icon
				+ "\nPermissions: " + this.permissions
				+ "\nIsOwner: " + this.owner;
	}
	
	
}
