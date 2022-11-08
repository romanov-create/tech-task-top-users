# tech-task-top-users
simple Spring boot app
Tools
- Tomcat(spring-boot)
- Java11
- Maven

Run an application
method initDB() in UserServiceImpl for initialization DB in memory, after commit this method
Compile project use maven and run class TestTaskShopApplication (server.port: 8888)
Use Postman Address of application: http://localhost:8888/shop//product send Get http reqest with 2 parametrs:
nameFilter (Regular Expressions)
amount (Amount of products in response)
Example:
request (method Get)
http://localhost:8888/shop/product?nameFilter=%5E.*%5B1%5D.*%24&amount=10

response (Json)
