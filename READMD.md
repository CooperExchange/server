# Server

Feb 13, 2023
- [x] Use Java to udpate user password, username, names, email
- [ ] Use Java to create a new user
- [ ] Use Java to add and remove account balance

Build and run Java
```
mvn package && mvn exec:java -Dexec.mainClass=cooperexchange.App
```

Count number of users
```java
ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM accounts");

while(resultSet.next()){
    System.out.println(resultSet.getInt(1));
}
```

