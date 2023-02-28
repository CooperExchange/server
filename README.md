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
\c cooper_exchange
```
```sql
CREATE SEQUENCE account_seq start with 1; 
CREATE TABLE accounts (
    user_id bigint NOT NULL DEFAULT nextval('account_seq'),
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    ssn int NOT NULL UNIQUE,
    username varchar(50) NOT NULL UNIQUE,
    pass_word varchar(50) NOT NULL,
    email varchar(50) NOT NULL UNIQUE,
    num_of_trades bigint DEFAULT 0,
    total_deposit NUMERIC(1000,2) DEFAULT 0.00,
    total_withdrawn NUMERIC(1000,2) DEFAULT 0.00,
    current_bal NUMERIC(1000,2) DEFAULT 0.00,
    net_profit NUMERIC(1000,2) DEFAULT 0.00,
    PRIMARY KEY (user_id),
    CHECK (net_profit = current_bal + total_withdrawn - total_deposit)
);


CREATE SEQUENCE trade_seq start with 1;
CREATE TABLE trade_history (
    trade_id bigint NOT NULL DEFAULT nextval('trade_seq'),
    user_id bigint NOT NULL,
    asset_id bigint NOT NULL,
    asset_name varchar(15) NOT NULL,
    unit_price bigint NOT NULL,
    shares bigint NOT NULL,
    buy boolean NOT NULL,
    date_purchase date NOT NULL,
    PRIMARY KEY (trade_id),
    FOREIGN KEY (user_id) REFERENCES accounts(user_id),
    FOREIGN KEY (asset_id) REFERENCES assets(asset_id),
    FOREIGN KEY (asset_name) REFERENCES assets(asset_name)
);

CREATE SEQUENCE portfolio_seq start with 1;
CREATE TABLE portfolio (
    portfolio_id bigint NOT NULL DEFAULT nextval('portfolio_seq'),
    asset_id bigint NOT NULL,
    user_id bigint NOT NULL,
    asset_name varchar(15) NOT NULL,
    shares bigint NOT NULL,
    PRIMARY KEY (portfolio_id),
    FOREIGN KEY (user_id) REFERENCES accounts(user_id),
    FOREIGN KEY (asset_id) REFERENCES assets(asset_id),
    FOREIGN KEY (asset_name) REFERENCES assets(asset_name)  
);

CREATE SEQUENCE portfolio_history_seq start with 1;
CREATE TABLE portfolio_history (
    portfolio_history_id bigint NOT NULL DEFAULT nextval('portfolio_history_seq'),
    user_id bigint NOT NULL,
    current_bal NUMERIC(1000,2) NOT NULL,
    date_balance date NOT NULL,
    PRIMARY KEY (portfolio_history_id),
    FOREIGN KEY (user_id) REFERENCES accounts(user_id)
);

CREATE SEQUENCE asset_seq start with 1;
CREATE TABLE assets(
    asset_id bigint NOT NULL DEFAULT nextval('asset_seq'),
    asset_name varchar(100) NOT NULL UNIQUE,
    ticker varchar(50) NOT NULL UNIQUE,
    market_cap bigint NOT NULL,
    pe_ratio NUMERIC(4,2) NOT NULL,
    dividend_yield NUMERIC(4,2) NOT NULL,
    PRIMARY KEY (asset_id)
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