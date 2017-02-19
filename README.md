Retail Manager Boot REST Service
================================



Requirements
------------
* [Java Platform (JDK) 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Apache Maven 3.x](http://maven.apache.org/)

Quick start
-----------
1. `mvn package`
2. `java -jar target/retail-manager-1.0-SNAPSHOT.jar`
3. Get the short-lived authorization token from response header
from localhost:8080/login post with username and password
{"username":"","password":""}

4. `curl -X POST --header "Content-Type: application/json" --header "Accept: */*" --header "Authorization: eyJ....." -d "{
  \"shopAddress\": {
    \"postCode\": \"string\",
    \"shopNumber\": \"string\"
  },
  \"shopName\": \"string\"
}" "http://localhost:8080/shop"`
5. Get all the list of Shop
curl -X GET --header "Accept: */*" --header "Authorization: eyJhbGciOi" "http://localhost:8080/shop"

6.Get the nearest Shop
curl -X GET --header "Accept: */*" --header "Authorization: eyJhbGci" "http://localhost:8080/shop/nearest/19.0000/72.09823"

7.Browse localhost:8080/swagger-ui.html for API documentation
