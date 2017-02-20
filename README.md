Retail Manager Boot REST Service
================================



Requirements
------------
* [Java Platform (JDK) 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

* Gradle   https://gradle.org/gradle-download/
 OR
* [Apache Maven 3.x](http://maven.apache.org/)

* update with proper api key for Google map service in shop.properties under resource

Quick start(Gradle)
-----------
1.'gradle build'
2.`java -jar build/libs/retail-manager-1.0-SNAPSHOT.jar`
Quick start(Maven)
-----------
1. `mvn package`
2. `java -jar target/retail-manager-1.0-SNAPSHOT.jar`
3. Get the short-lived authorization token from response header
from localhost:8080/login post with username and password
{"username":"admin","password":"password"}

4. `curl -X POST --header "Content-Type: application/json" --header "Accept: */*" --header "Authorization: eyJ....." -d "{
  \"shopAddress\": {
    \"postCode\": \"string\",
    \"shopNumber\": \"string\"
  },
  \"shopName\": \"string\"
}" "http://localhost:8080/shop"`

Success Response : 200 OK

{
    "shopName": "string",
    "shopAddress": {
        "shopNumber": "string",
        "postCode": "700047"
    },
    "longitude": "latitude",
    "latitute": "longitude"
}

5. Get all the list of Shop
curl -X GET --header "Accept: */*" --header "Authorization: eyJhbGciOi" "http://localhost:8080/shop"

6.Get the nearest Shop
curl -X GET --header "Accept: */*" --header "Authorization: eyJhbGci" "http://localhost:8080/shop/nearest/{latitude}/{longitude}"

7.Browse localhost:8080/swagger-ui.html for API documentation
