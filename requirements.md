# System Analysis

## DB tables

### 1. Users
| Variable | Data Type |
|:------|:-------:|
| id (PK)| int |
|username| varchar|
|password (hashed)|varchar|
|email|varchar|

### 2. Musics
|Variable | Data Type |
|:------|:-------:|
| link_code (PK)| varchar|
| title | varchar|
| duration | int|
|music_file| longblob|


### 3. Download_requests
|Variable | Data Type |
|:------|:-------:|
|link_code|varchar|
| userId (id) |int|
| isDownloaded| boolean|
