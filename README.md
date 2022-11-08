# tech-task-top-users
simple Spring boot app
Tools
- Tomcat(spring-boot)
- Java11
- Maven

Run an application
method initDB() in UserServiceImpl for initialization DB in memory, after commit this method
Compile project use maven and run class TestTaskShopApplication (server.port: 8888)
Use Postman Address of application: 
- http://localhost:8080/api/userinfo/{userId}
- http://localhost:8080/api/levelinfo/{levelIs}
- http://localhost:8080/api/setinfo with json body ex.:
{
    "userId": 2,
    "levelId": 2,
    "result": 1421
}

