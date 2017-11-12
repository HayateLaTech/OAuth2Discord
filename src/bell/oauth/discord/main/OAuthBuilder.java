package bell.oauth.discord.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bell.oauth.discord.domain.Connection;
import bell.oauth.discord.domain.Guild;
import bell.oauth.discord.domain.User;
import bell.oauth.discord.req.Post;
import okhttp3.OkHttpClient;

public class OAuthBuilder {
	
	private static final String BASEURI = "https://discordapp.com/api/";
	private static final String TOKENURI = "oauth2/token";
	private static final String REVOCATIONURI = "oauth2/token/revoke";
	
	private static final String CONNECTIONSURI = "users/@me/connections";
	private static final String MEURI = "users/@me";
	private static final String GUILDURI = "users/@me/guilds";
	
	//private static final String INVITEURI = "invites/{invite.id}";
	
	private OkHttpClient client;
	
	private String id, secret, redirect, scopes, access_token, refresh_token;
	
	public OAuthBuilder(String clientID, String clientSecret) {
		this.id = clientID;
		this.secret = clientSecret;
		
		this.client = new OkHttpClient();
	}
	
	public OAuthBuilder setRedirectURI(String url) {
		this.redirect = url;	
		return this;
	}
	
	public OAuthBuilder setScopes(String[] scopes) {
		this.scopes = "";
		for(String scope: scopes) {
			this.scopes += scope+"%20";
		}
		this.scopes = this.scopes.substring(0, this.scopes.length()-3);		
		return this;
	}
	
	public String getAuthorizationUrl(String state) {
		StringBuilder builder = new StringBuilder();
		
		builder.append(BASEURI);
		builder.append("oauth2/authorize");
		builder.append("?response_type=code");
		builder.append("&client_id="+this.id);
		builder.append("&scope="+this.scopes);
		if (state != null && state.length() > 0) builder.append("&state="+state);
		builder.append("&redirect_uri="+this.redirect);
		
		return builder.toString();
	}
	
	public Response exchange(String code) {
		try {
			String json = Post.exchangePost(client, BASEURI + TOKENURI, this.id, this.secret, code, this.redirect);
			
			JSONObject js = new JSONObject(json);
			
			try {
				this.access_token = js.getString("access_token");
				this.refresh_token = js.getString("refresh_token");
				
				return Response.OK;
			} catch (JSONException e) {
				return Response.ERROR;
			}
			
		} catch (IOException e) {
			return Response.ERROR;
		}
	}
	
	public Response refresh() {
		try {
			String json = Post.refreshPost(client, BASEURI + TOKENURI, this.id, this.secret, this.refresh_token, this.redirect);
			
			JSONObject js = new JSONObject(json);
			
			try {
				this.access_token = js.getString("access_token");
				this.refresh_token = js.getString("refresh_token");
				
				return Response.OK;
			} catch (JSONException e) {
				return Response.ERROR;
			}
			
		} catch (IOException e) {
			return Response.ERROR;
		}
	}
	
	public Response revoke() {
		try {
			Post.revokePost(client, BASEURI + REVOCATIONURI, access_token);
			return Response.OK;
		} catch (IOException e) {
			return Response.ERROR;
		}
	}
	
	public User getUser() {
		User user = new User();
		
		try {
			String json = Post.get(client, BASEURI + MEURI, access_token);
			
			JSONObject js = new JSONObject(json);
			try {
				user.setId(js.getString("id"));
				user.setAvatar(js.getString("avatar"));
				user.setBot(js.getBoolean("bot"));
				user.setDiscriminator(js.getString("discriminator"));
				user.setEmail(js.getString("email"));
				user.setMfa_enabled(js.getBoolean("mfa_enabled"));
				user.setUsername(js.getString("username"));
				user.setVerified(js.getBoolean("verified"));
			} catch (JSONException e) {
				user.setId(js.getString("id"));
				user.setAvatar(js.getString("avatar"));
				user.setDiscriminator(js.getString("discriminator"));
				user.setEmail(js.getString("email"));
				user.setUsername(js.getString("username"));
			}			
			
		} catch (IOException e) {
			return null;
		}
		
		return user;
	}
	
	public List<Guild> getGuilds() {
		List<Guild> guilds = new ArrayList<>();
		
		try {
			String json = Post.get(client, BASEURI + GUILDURI, access_token);
			
			JSONArray arrJs = new JSONArray(json);
			
			for (Object guild: arrJs) {
				Guild g = new Guild();
				JSONObject obj = (JSONObject) guild;
				
				g.setIcon(obj.getString("icon"));
				g.setId(obj.getString("id"));
				g.setName(obj.getString("name"));
				g.setOwner(obj.getBoolean("owner"));
				g.setPermissions(obj.getInt("permissions"));
				
				guilds.add(g);
			}				
			
		} catch (IOException e) {
			return null;
		}
		
		return guilds;
	}
	
	public List<Connection> getConnections() {
		List<Connection> connections = new ArrayList<>();
		
		try {
			String json = Post.get(client, BASEURI + CONNECTIONSURI, access_token);
			
			JSONArray arrJs = new JSONArray(json);
			
			for (Object connection: arrJs) {
				Connection c = new Connection();
				JSONObject obj = (JSONObject) connection;
				
				c.setFriend_sync(obj.getBoolean("friend_sync"));
				c.setId(obj.getString("id"));
				c.setName(obj.getString("name"));
				c.setType(obj.getString("type"));
				c.setVerified(obj.getBoolean("verified"));
				c.setVisibility(obj.getInt("visibility"));
				
				connections.add(c);
			}				
			
		} catch (IOException e) {
			return null;
		}
		
		return connections;
	}
	
	public String getScopes() {
		return scopes;
	}

	public void setScopes(String scopes) {
		this.scopes = scopes;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

}
