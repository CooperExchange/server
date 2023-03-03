import requests

def makeAccount():
    firstName = input("What is your First Name?\n")
    lastName = input("What is your Last Name?\n")
    email = input("Email: ")
    username = input("Username: ")
    password = input("Password: ")

    r = requests.post('http://localhost:8080/registration', json={
        "email": email,
        "username" : username,
        "firstName": firstName,
        "lastName" : lastName,
        "password" : password
    })

    if r.status_code != 200:
        print(f"Error! Status: {r.status_code}")

def updateAccount(user_id):
    firstName = input("What is your First Name?\n")
    lastName = input("What is your Last Name?\n")
    email = input("Email: ")
    username = input("Username: ")
    password = input("Password: ")

    r = requests.post(f'http://localhost:8080/accounts/{user_id}/update', json={
        "email": email,
        "username" : username,
        "firstName": firstName,
        "lastName" : lastName,
        "password" : password
    })

    if r.status_code != 200:
        print(f"Error! Status: {r.status_code}")

def login():
    email = input("Email: ")
    password = input("Password: ")

    r = requests.post('http://localhost:8080/login', json={
        "email": email,
        "password" : password
    })

    if r.status_code != 200:
        print(f"Error! Status: {r.status_code}")
    return r.content

def deposit(user_id, amount):
    header = {'content-type': 'application/json'}
    r = requests.post(f'http://localhost:8080/accounts/{user_id}/deposit', data=amount, headers=header)

    if r.status_code != 200:
        print(f"Error! Status: {r.status_code}")

def withdrawal(user_id, amount):
    header = {'content-type': 'application/json'}
    r = requests.post(f'http://localhost:8080/accounts/{user_id}/withdrawal', data=amount, headers=header)

    if r.status_code != 200:
        print(f"Error! Status: {r.status_code}")

def trade(user_id, tradeType, assetSymbol, assetCount):
    r = requests.post(f'http://localhost:8080/accounts/{user_id}/trade', json={
        "tradeType": tradeType,
        "assetSymbol" : assetSymbol,
        "assetCount" : assetCount
    })

    if r.status_code != 200:
        print(f"Error! Status: {r.status_code}")

action = input("Select Option: \n> Make Account\n> Login\n")
match action:
    case "Make Account":
        makeAccount()
    case "Login":
        user_id = login().decode("utf-8")
        while True:
            account_action = input("Select Option:\n> Update Account Settings\n> Deposit\n> Withdrawal\n> Trade\n> Logout\n")
            match account_action:
                case "Update Account Settings":
                    updateAccount(user_id)
                case "Deposit":
                    amount = input("How much would you like to deposit?\n")
                    deposit(user_id, amount)
                case "Withdrawal":
                    amount = input("How much would you like to withdraw?\n")
                    withdrawal(user_id, amount)
                case "Trade":
                    tradeType = input("Trade Type (Buy or Sell): ")
                    assetSymbol = input("Symbol: ")
                    assetCount = input("Number of Shares: ")
                    trade(user_id, tradeType, assetSymbol, assetCount)
                case "Logout":
                    exit()
                case _:
                    print("No option selected")
    case _:
        print("No option selected")

