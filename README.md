# OAuth2Discord
A little OAuth2 Wrapper for [Discord](https://discord.com).

Updated by ASL STUDIO

[![GitHub license](https://img.shields.io/github/license/asl-std/OAuth2Discord?style=plastic)](https://github.com/asl-std/OAuth2Discord/blob/release/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/asl-std/OAuth2Discord?style=plastic)](https://github.com/asl-std/OAuth2Discord/issues)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/asl-std/OAuth2Discord/Build?style=plastic)
![Maven Central](https://img.shields.io/maven-central/v/ru.aslcraft.bell.oauth.discord/OAuth2Discord?style=plastic)

## Features
* Generation of Authorization URI
* Code-Exchange and getting the access and refresh token
* Revocation of Access-Token
* Refreshing of Access-Token with Refresh-Token
* Get guilds / user / connections information of user

## How to add it


## How does it work
1. Instantiate the `OAuthBuilder` like this:
```
import ru.aslcraft.bell.oauth.discord.main.OAuthBuilder;

OAuthBuilder builder = new OAuthBuilder("clientID", "clientSecret")
        .setScopes(new String[]{"connections", "guilds", "email"})
        .setRedirectURI("RedirectURL");
```
2. Get the Authorization URL:
```
import ru.aslcraft.bell.oauth.discord.main.OAuthBuilder;

String authURL = builder.getAuthorizationUrl(null);
```
3. Exchange the Code you get from the redirect (as a GET parameter):
```
import ru.aslcraft.bell.oauth.discord.main.OAuthBuilder;
import ru.aslcraft.bell.oauth.discord.main.Response;

Response response = builder.exchange(code);

        if (response == Response.ERROR) {
        // AN ERROR HAPPENED WHILE EXCHANGING THE CODE
        } else {
        // EVERYTHING WORKED AS EXPECTED
        }
```
* Get User-Information (only available with scopes `email` or `identify`):
```
import ru.aslcraft.bell.oauth.discord.main.OAuthBuilder;
import ru.aslcraft.bell.oauth.discord.domain.User;

User user =  builder.getUser();
```
* Get Guilds-Information (only available with scope `guilds`):
```
import ru.aslcraft.bell.oauth.discord.main.OAuthBuilder;
import ru.aslcraft.bell.oauth.discord.domain.Guild;
import java.util.List;

List<Guild> guilds = builder.getGuilds();
```
* Get Connections-Information (only available with scope `connections`):
```
import ru.aslcraft.bell.oauth.discord.main.OAuthBuilder;
import ru.aslcraft.bell.oauth.discord.domain.Connection;
import java.util.List;

List<Connection> connections = builder.getConnections();
```
* Refresh the access-token:
```
import ru.aslcraft.bell.oauth.discord.main.OAuthBuilder;
import ru.aslcraft.bell.oauth.discord.main.Response;

Response response = builder.refresh();

        if (response == Response.ERROR) {
        // AN ERROR HAPPENED WHILE EXCHANGING THE CODE
        } else {
        // EVERYTHING WORKED AS EXPECTED
        }
```
* Revoke the access-token:
```
import ru.aslcraft.bell.oauth.discord.main.OAuthBuilder;
import ru.aslcraft.bell.oauth.discord.main.Response;

Response response = builder.revoke();

        if (response == Response.ERROR) {
        // AN ERROR HAPPENED WHILE EXCHANGING THE CODE
        } else {
        // EVERYTHING WORKED AS EXPECTED
        }
```
