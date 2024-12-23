# System Analysis

## DB tables

### 1. users
| Variable | Data Type |Note|
|:------|:-------:|:-----|
| id (PK)| int |
|username| varchar|
|role|varchar|user, admin
|password (hashed)|varchar|
|email|varchar|

### 2. musics
|Variable | Data Type | Note|
|:------|:-------:|:-----|
| link_code (PK)| varchar|
| title | varchar|null
| duration | int|seconds|null
| low_thumbnail_url | varchar|
| high_thumbnail_url| varchar|


### 3. download_requests
|Variable | Data Type | Note|
|:------|:-------:|:-----|
|link_code|varchar||
| user_id (id) |int||
| status| varchar|successful, ongoing, unsuccessful|

### 4. playlists
|Variable | Data Type | Note|
|:------|:-------:|:-----|
|playlist_id|int|PK|
|playlist_name|varchar
|user_id|int|FK|
|created_at| timestamp

- upon playlist deletion playlist_song will also be removed (can use cronjob or deletion right after request)
- new user creation will create a "Saved" playlist as a default playlist (not removable)


### 5. playlist_song
|Variable | Data Type | Note|
|:------|:-------:|:-----|
|playlist_id|int|FK
|song_id|int|FK


