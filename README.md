# Game mods backend
### Running the application
To run the application you have to create `.json` config file which looks like this:
```
{
    "databaseConfig": {
        "user": "userName",
        "password": "password",
        "url": "jdbc:postgresql://localhost:5432/databaseName"
    },
    "smtpConfig": {
        "host": "host",
        "auth": true,
        "tls": true,
        "port": 123,
        "username": "username",
        "password": "password"
  },
  "securityConfig": {
    "jwt": {
      "secret": "secret",
      "expiration": 123
    },
    "passwordSecret": "password-secret"
  }
}
```
Expiration is in ms.

Then if you run the application from cmd the command will look like this:
`java -jar -Dconfig="path-to-configfile.json" jar-name.jar com.gmw.api.ApiApplication`.

If you want to run application using Intellij you will need to specify vm option in `Edit configurations...`.
This option should look like this:
`-Dconfig="path-to-file.json"`
