# Notes

### Youtube Downloader
- https://github.com/yt-dlp/yt-dlp#installation

### How to access postgresql in docker container

1. `docker exec -it containerID bash`
2. `psql -U himagi -d HPlayerDB`

### OAuth2
- https://youtu.be/us0VjFiHogo?t=306


### Tip
- Use a queue to process download request &check;
- for all songs for user, just select all songs related to user name from playlist table

### Port forward from remote server

- ssh -L 8080:localhost:8080 username@server-ip