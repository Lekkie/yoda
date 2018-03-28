Requirements
===========
-- Jdk 8
-- Gradle


How to run
==========
```shell
./gradlew build && java -jar build/libs/yoda-1.0-SNAPSHOT.jar
```
   
To Debug   
```
./gradlew build && java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar build/libs/yoda-1.0-SNAPSHOT.jar
```

Browse to `http://localhost:9080`


Hwo to use
=====================
Password Grant Type
-------------------
http://localhost:9080/oauth/token
Basic: base64(devglan-client:devglan-secret)
Content-Type: application/x-www-form-urlencoded

form data
username: Alex123
password: password
grant_type: password

Login with basic authentication `devglan-client`,  and `devglan-secret` as the password.  


Client Credentials Grant Type
------------------------------
http://localhost:9080/oauth/token
Basic: base64(devglan-client:devglan-secret)
Content-Type: application/x-www-form-urlencoded

form data
grant_type: client_credentials

Login with basic authentication `devglan-client`,  and `devglan-secret` as the password.  

 



 
