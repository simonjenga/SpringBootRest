Restful Web Service Code Challenge
======================================
Overview:
----------
We would like to have a RESTful web service that stores some transactions (in memory is fine)
and returns information about those transactions.
The transactions to be stored have a type and an amount. The service should support returning all
transactions of a type. Also, transactions can be linked to each other (using a "parent_id") and we
need to know the total amount involved for all transactions linked to a particular transaction.

For example:

In detail the api spec looks like the following:

**PUT /transactionservice/transaction/$transaction_id**

Body: 

 `{ "amount":double,"type":string,"parent_id":long } `
 
where:

 - **transaction_id** is a long specifying a new transaction
 
 - **amount** is a double specifying the amount
 
 - **type** is a string specifying a type of the transaction.
 
 - **parent_id** is an optional long that may specify the parent transaction of this transaction. 

**GET /transactionservice/transaction/$transaction_id **
Returns: 

`{ "amount":double,"type":string,"parent_id":long } `

**GET /transactionservice/types/$type **

Returns: `[ long, long, .... ]`
 
A json list of all transaction ids that share the same type $type.

**GET /transactionservice/sum/$transaction_id **

Returns : `{ "sum", double }`

A sum of all transactions that are transitively linked by their parent_id to $transaction_id.

**Some simple examples would be:**

- `PUT /transactionservice/transaction/10 ` => `{ "amount": 5000, "type":"cars" }` => `{ "status": "ok" } `

- `PUT /transactionservice/transaction/11` =>`{ "amount": 10000, "type": "shopping", "parent_id": 10 }` => `{ "status": "ok" } `

- `GET /transactionservice/types/cars` => `[10]`

- `GET /transactionservice/sum/10` => `{"sum":15000} `

- `GET /transactionservice/sum/11` => `{"sum":10000} `

Building The Project
====================

The project compiles with ```JDK >= 1.5``` and ```Maven >= 3.1.1``` as the build tool and to also manage the project dependencies.

To run Maven build, execute the following from a console/command prompt with the project root directory as the top level directory:

```mvn clean package```

This will create a distributable and executable JAR file for the standalone application.

Then run the JAR file created by executing the following:

```java -jar target/SpringBootRest.jar```

Point your browser to ```http://localhost:8080```
```curl -X POST -H "Content-Type: application/json" -d '{ "id": 1, "amount": 5000, "type": "test_type", "parent_id": 1 }' http://localhost:8080/transactionservice/transaction```
Refresh the page

Alternatively, you can use the web browser plugin/addon called RESTClient(a debugger for RESTful web services) to enable you to interact with the web services.

To run Unit tests, execute the following:

```mvn clean test```

### Instructions

Eclipse users run `mvn eclipse:eclipse` and then import the project or just import the code as a Maven project into IntelliJ, NetBeans, or Eclipse.

To generate project documentation of Java source files (Javadoc), run `mvn javadoc:javadoc`