package bell.oauth.discord.domain;

public class User {
	
	private String id, username, discriminator, avatar, email;
	private boolean bot, mfa_enabled, verified;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDiscriminator() {
		return discriminator;
	}
	public void setDiscriminator(String discriminator) {
		this.discriminator = discriminator;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isBot() {
		return bot;
	}
	public void setBot(boolean bot) {
		this.bot = bot;
	}
	public boolean isMfa_enabled() {
		return mfa_enabled;
	}
	public void setMfa_enabled(boolean mfa_enabled) {
		this.mfa_enabled = mfa_enabled;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	@Override
	public String toString() {
		return this.username + " # " + this.discriminator
				+ "\nID: " + this.id
				+ "\nAvatar: " + this.avatar
				+ "\nIsBot: " + this.bot
				+ "\nMFA: " + this.mfa_enabled
				+ "\nEmail: " + this.email
				+ "\nVerified: " + this.verified;
	}
	
	
	

}
