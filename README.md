Retail Manager Boot REST Service
================================



Requirements
------------
* [Java Platform (JDK) 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Apache Maven 3.x](http://maven.apache.org/)

Quick start
-----------
1. `mvn package`
2. `java -jar target/example-spring-boot-rest-1.0-SNAPSHOT.jar`
3. Point your browser to [http://localhost:8080](http://localhost:8080)
4. `curl -X POST -d '{"id":1,"shopName":"name","shopAddress":{"id":1,"shopNumber":"123","postCode":"700047"},"longitude":1234.34,"latitute":67.76}' http://localhost:8080/shop`
5. Refresh the page