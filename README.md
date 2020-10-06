
**atm_restful_service**

'atm_restful_service' is back-end system for ATM machines. It implements Rest API’s for ATM Backend service which can be used by all Indian Banks. ATM (Automated  Teller Machine) is a banking application developed to perform different banking transactions.

atm_restful_service is developed using spring boot framaework.It uses in-memory H2 Database as a db source.We are using JPA for all db transactions.

atm_restful_service have the following functionalities:

1. Create accounts: Accept sufficient attributes required to create the user account.
2. Balance enquiry.
3. Cash deposits: Based on user account details users can deposit amounts from their bank account.
4. cash withdrawals:Based on user account details users can withdraw amounts from their bank account.

**Running the Application**

please first do maven update and then run the applicationa as spring boot app.

**API's Signatures**

**1.Create Account**: 

Method: POST

url   : http://localhost:8080/atm/create-account?accountHolderName={accountHolderName}t&pin={pin}&balance={balance}

|**paramters** | accountHolderName| pin | balance| 
|:--: | :---: | :---: | :---: |
|**type** | Required | Required |  optional (by default set 0.0 if not provided at the time of creation)| 


Example  :   http://localhost:8080/atm/create-account?accountHolderName=puneet&pin=1234

**2.Check balance:** 
 
 Method: GET
 
 url   : http://localhost:8080/atm/check-balance?accountNumber={accountNumber}

 |**paramters** | accountNumber| 
 |:--: | :---: |
 |**type** | Required |
 
 Example  :   http://localhost:8080/atm/check-balance?accountNumber=1000

**3.Deposit Money:**
 
 Method: GET
 
 url   : http://localhost:8080/atm/deposit?accountNumber={accountNumber}&amount={depositAmount}

  |**paramters** | accountNumber| depositAmount|
 |:--: | :---: |:---:|
 |**type** | Required |Required|
  
  
  Example  :   http://localhost:8080/atm/deposit?accountNumber=1000&amount=2
  
 **4.Withdraw Money:**
  
  Method: GET
  
  url   : http://localhost:8080/atm/withdraw?accountNumber={accountNumber}&amount={withdrawAmount}

  
|**paramters** | accountNumber| depositAmount|
 |:--: | :---: |:---:|
 |**type** | Required |Required|

  
  Example  :   http://localhost:8080/atm/withdraw?accountNumber=1000&amount=1
  
  
  **UML Diagram Link**
  
  https://drive.google.com/file/d/10rnDusLly0Do5YYpwtnwMSZq93ZgLVa5/view?usp=sharing

