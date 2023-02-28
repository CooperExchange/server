## Run

```
$ docker run --rm --name lil-postgres -e POSTGRES_PASSWORD=password -d -v $HOME/srv/postgres:/var/lib/postgresql/data -p 5432:5432 postgres
$ mvn package && java -jar target/rps-0.0.1-SNAPSHOT-spring-boot.jar
```

To execute SQL commands in the docker container
```agsl
$ docker exec -it lil-postgres bash // go into the docker container using container name
$ docker exec -it 46c0b162a40b bash // or go into the docker container using container id
$ psql -h localhost -U postgres // run SQL comands
```

```agsl
$ CREATE DATABASE cooper_exchange;
$ GRANT ALL PRIVILEGES ON DATABASE cooper_exchange TO postgres;
$ \c cooper_exchange
```

```sql
CREATE TABLE accounts (
    user_id     SERIAL PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    username varchar(50) NOT NULL UNIQUE,
    pass_word varchar(50) NOT NULL,
    email varchar(50) NOT NULL UNIQUE,
    total_deposit NUMERIC(1000,2) DEFAULT 0.00,
    total_withdrawal NUMERIC(1000,2) DEFAULT 0.00,
    current_balance numeric GENERATED ALWAYS AS (total_deposit -  total_withdrawal) STORED
);


CREATE TABLE trades (
    trade_id     SERIAL PRIMARY KEY,
    trade_type   varchar(50)      NOT NULL,
    trade_date   DATE DEFAULT CURRENT_DATE,
    user_id      bigint           NOT NULL,
    asset_symbol varchar(50)      NOT NULL,
    asset_name   varchar(50)      NOT NULL,
    asset_price  NUMERIC(1000, 2) NOT NULL,
    asset_count  NUMERIC(1000, 2) NOT NULL,
    total_price  numeric GENERATED ALWAYS AS (asset_price * asset_count) STORED
);

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
	"assetCount" : "1.3"
}

{
	"tradeType": "sell",
	"assetSymbol" : "BTC",
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
- [x] `/accounts/{id}/deposit` - POST/increase cash balance
- [x] `/accounts/{id}/withdrawal` - POST/decrease cash balance
- [x] `/accounts/{id}/update` - POST/update user credentials
- [x] `/accounts/{id}/reset` - DELETE/reset the user trades and balance
- [x] `/accounts/{id}/trade/buy` - POST/purchase an asset
- [x] `/accounts/{id}/trade/sell` - POST/sell an asset
- [ ] `/accounts/{id}/portfolio` - GET/retrieve user portfolio and balance

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