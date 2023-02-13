# Back-end for CooperExchange

Pending Tasks
- [ ] Run a Java server using SpringBoot
- [ ] Implement Docker workflow
- [ ] Handle account registration and login requesets
- [ ] Refactor code using `DataAccessObject` and `DataTransferObject`

Implemented
- [x] Use Java to update a property value using `user_id` - 02/13/2023
- [x] Use Java to create an account - 02/13/2023
- [x] Use Java to connect with the local database and retrieve information based on `user_id` 0 02/13/2023

## How to use

Build and run Java.

```
$ mvn package && mvn exec:java -Dexec.mainClass=cooperexchange.App
```

> Make sure Maven is installed. Otherwise, visit the `API` repository to learn how to install.

