# OAuth2Discord
A little OAuth2 Wrapper for [Discord](https://discordapp.com).

## Features
* Generation of Authorization URI
* Code-Exchange and getting the access and refresh token
* Revocation of Access-Token
* Refreshing of Access-Token with Refresh-Token
* Get guilds / user / connections information of user

## How to add it
Maven
```
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.HayateLaTech</groupId>
  <artifactId>OAuth2Discord</artifactId>
  <version>1</version>
</dependency>
```

Gradle
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
  compile 'com.github.HayateLaTech:OAuth2Discord:1'
}
```
[Jitpack](https://jitpack.io/#HayateLaTech/OAuth2Discord)

Or build it locally..

## How does it work
1. Instantiate the `OAuthBuilder` like this:
```java
import bell.oauth.discord.main.OAuthBuilder;

OAuthBuilder builder = new OAuthBuilder("clientID", "clientSecret")
                          .setScopes(new String[]{"connections", "guilds", "email"})
                          .setRedirectURI("RedirectURL");
```
2. Get the Authorization URL:
```java
import bell.oauth.discord.main.OAuthBuilder;

String authURL = builder.getAuthorizationUrl(null);
```
3. Exchange the Code you get from the redirect (as a GET parameter):
```java
import bell.oauth.discord.main.OAuthBuilder;
import bell.oauth.discord.main.Response;

Response response = builder.exchange(code);

if (response == Response.ERROR) {
  // AN ERROR HAPPENED WHILE EXCHANGING THE CODE
} else {
  // EVERYTHING WORKED AS EXPECTED
}
```
* Get User-Information (only available with scopes `email` or `identify`):
```java
import bell.oauth.discord.main.OAuthBuilder;
import bell.oauth.discord.domain.User;

User user =  builder.getUser();
```
* Get Guilds-Information (only available with scope `guilds`):
```java
import bell.oauth.discord.main.OAuthBuilder;
import bell.oauth.discord.domain.Guild;
import java.util.List;

List<Guild> guilds = builder.getGuilds();
```
* Get Connections-Information (only available with scope `connections`):
```java
import bell.oauth.discord.main.OAuthBuilder;
import bell.oauth.discord.domain.Connection;
import java.util.List;

List<Connection> connections = builder.getConnections();
```
* Refresh the access-token:
```java
import bell.oauth.discord.main.OAuthBuilder;
import bell.oauth.discord.main.Response;

Response response = builder.refresh();

if (response == Response.ERROR) {
  // AN ERROR HAPPENED WHILE EXCHANGING THE CODE
} else {
  // EVERYTHING WORKED AS EXPECTED
}
```
* Revoke the access-token:
```java
import bell.oauth.discord.main.OAuthBuilder;
import bell.oauth.discord.main.Response;

Response response = builder.revoke();

if (response == Response.ERROR) {
  // AN ERROR HAPPENED WHILE EXCHANGING THE CODE
} else {
  // EVERYTHING WORKED AS EXPECTED
}
```

## TODO
* Invite workflow
