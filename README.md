# Redirection
## Description
This app can shorten links. Thereby personal data of the client like access time, header, link ID and IP address are stored in a Postgres database.
Note: Adding more URLs can be done by anyone. Moreover, the collection of this personal data is not allowed without consent.

## Usage
### Shorten a link
`http://localhost:8080/add/<URL>/<ID>`
`<URL>` is the URL to be shortened (e.g. `www.google.com`)
`<ID>` is the ID of the shortened URL (e.g. `google`)
This request returns the shortened URL.

### Redirect to a link
`http://localhost:8080/s/<ID>`
`<ID>` is the ID of the shortened URL

### Show all links
`http://localhost:8080`

## Database
### Redirect
- id: ID of the shortened URL
- url: URL to be shortened
- redirect_string: The configured string to be used in the shortened URL

### Connection
- id: ID of the connection
- ip: IP address of the client
- header: Header of the client
- redirect_id: ID of the shortened URL
- date_time: Time of the connection

## Configuration
### Database
You can configure the database connection in the `src/main/resources/application.properties` file.

### Postgres Docker
The Docker-Compose file is located in the `docker` folder.
You can use the following command to start a Postgres Docker container:
```bash
cd docker
docker compose up -d
```