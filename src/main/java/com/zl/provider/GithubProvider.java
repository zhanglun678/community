package com.zl.provider;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zl.dto.AccessTokenDTO;
import com.zl.dto.GithubUser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class GithubProvider {
	/**
	 * 获取TOKEN
	 * @param accessTokenDTO
	 * @return
	 */
	public String getAccessToken(AccessTokenDTO accessTokenDTO) {
		MediaType mediaType = MediaType.get("application/json; charset=utf-8");
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
		Request request = new Request.Builder().url("https://github.com/login/oauth/access_token").post(body).build();
		try (Response response = client.newCall(request).execute()) {
			String str = response.body().string();
			//String str = "access_token=53a0df7a179f08ea59086b846da23b7ef6b96fd6&scope=user&token_type=bearer";
			return str.split("&")[0].split("=")[1];
		} catch (Exception e) {
		}
		return null;
	}
//获取github用户
	public GithubUser getUser(String access_token) {
		GithubUser githubUser = null;
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url("https://api.github.com/user?access_token=" + access_token).build();

		try (Response response = client.newCall(request).execute()) {
			String str = response.body().string();
			githubUser = JSON.parseObject(str, GithubUser.class);
			return githubUser;
		} catch (Exception e) {
		}
		return null;
	}

}
