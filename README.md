## Run

```
$ docker run --rm --name lil-postgres -e POSTGRES_PASSWORD=password -d -v $HOME/srv/postgres:/var/lib/postgresql/data -p 5432:5432 postgres
$ mvn package && java -jar target/cex-0.0.1-SNAPSHOT-spring-boot.jar
```


## API Endpoint Examples for Demo

### Registration
`POST` `/registration`
> Username and email must be unique. 

```
{
	"email": "demo@gmail.com",
	"username" : "demouser",
	"firstName": "Demo",
	"lastName" : "Lee",
	"password" : "demo123"
}
```

`POST` `/registration-random`


### Login Auth
`POST` `/login`
```
{
	"email": "demo@gmail.com",
	"password" : "demo123"
}

> Should return a user_id to the front-end app for executing queires
```

### Account Trade/Balance
`POST` `accounts/{userId}/deposit`
```
453,344
```

`POST` `accounts/{userId}/withdrawal`
```
4500
```

`POST` `accounts/{userId}/trade`
```
{
	"tradeType": "buy",
	"assetSymbol" : "BTC",
	"assetName" : "Bitcoin",
	"assetCategory" : "crypto",
	"assetCount" : "1.3"
}

or

{
	"tradeType": "sell",
	"assetSymbol" : "BTC",
	"assetName" : "Bitcoin",
	"assetCategory" : "crypto",
	"assetCount" : "0.5"
}

```

### Account Settings
`POST` `/accounts/{userId}/update`
```
{
	"email": "sangjoon.lee@cooper.edu",
	"username" : "sangjoon.lee",
	"firstName": "Sangoon (Bob)",
	"lastName" : "Lee",
	"password" : "sangjoon123"
}
```

`POST` `/accounts/{userId}/reset`
> Reset all trade history

## Demo End Points
- [x] `/registeration` - POST/register an account
- [x] `/registeration-random` - POST/register a random account
- [x] `/login` - POST/handle authentication
- [x] `/accounts/{id}/update` - POST/update user credentials
- [x] `/accounts/{id}/deposit` - POST/increase cash balance
- [x] `/accounts/{id}/withdrawal` - POST/decrease cash balance
- [x] `/accounts/{id}/trade` - POST/purchase an
- [ ] `/accounts/{id}/reset` - DELETE/reset the user trades and balance
- [ ] `/accounts/{id}/portfolio` - GET/retrieve user portfolio list
- [ ] `/accounts/{id}/portfolio-balance` - GET/retrieve user portfolio value
- [ ] `/accounts/{id}/portfolio-history` - GET/retrieve history of portfolio values 
## SQL Database

### Docker Compose
```agsl
$ docker compose build
$ docker compose up
```

## API Tools
- https://reqbin.com/ for REST API testing

## API Endpoint General Practices
- URIs as resources as nouns Example: /users/{id} instead of /getUser
- Lowercase letters and dashes: /users/{id}/pending-orders
- Use plural nouns ex) http://mysite.com/posts

Sources:
- https://nordicapis.com/10-best-practices-for-naming-api-endpoints/
- https://www.freecodecamp.org/news/rest-api-best-practices-rest-endpoint-design-examples/
