package ru.aslcraft.bell.oauth.discord.req;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Post {
	
	public static String exchangePost(OkHttpClient client, String url, String client_id, String client_secret, String code, String redirect_uri) throws IOException {
		RequestBody body = new MultipartBody.Builder()
							.setType(MultipartBody.FORM)
							.addFormDataPart("client_id", client_id)
							.addFormDataPart("client_secret", client_secret)
							.addFormDataPart("grant_type", "authorization_code")
							.addFormDataPart("code", code)
							.addFormDataPart("redirect_uri", redirect_uri)
							.build();
		
		Request req = new Request.Builder()
						.url(url)
						.post(body)
						.build();
		
		Response response = client.newCall(req).execute();
		
		return response.body().string();
	}
	
	public static String refreshPost(OkHttpClient client, String url, String client_id, String client_secret, String refresh_token, String redirect_uri) throws IOException {
		RequestBody body = new MultipartBody.Builder()
							.setType(MultipartBody.FORM)
							.addFormDataPart("client_id", client_id)
							.addFormDataPart("client_secret", client_secret)
							.addFormDataPart("grant_type", "refresh_token")
							.addFormDataPart("refresh_token", refresh_token)
							.addFormDataPart("redirect_uri", redirect_uri)
							.build();
		
		Request req = new Request.Builder()
						.url(url)
						.post(body)
						.build();
		
		Response response = client.newCall(req).execute();
		
		return response.body().string();
	}
	
	public static void revokePost(OkHttpClient client, String url, String access_token) throws IOException {
		RequestBody body = new MultipartBody.Builder()
							.setType(MultipartBody.FORM)
							.addFormDataPart("token", access_token)
							.build();
		
		Request req = new Request.Builder()
						.url(url)
						.post(body)
						.build();
		
		client.newCall(req).execute();
	}
	
	public static String get(OkHttpClient client, String url, String access_token) throws IOException {
			Request req = new Request.Builder()
						.url(url)
						.get()
						.addHeader("Authorization", "Bearer "+access_token)
						.build();
				
			Response response = client.newCall(req).execute();
				
			return response.body().string();
	}

}
