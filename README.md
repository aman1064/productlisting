# Wingify Problem Statement

### Problem Statement: Design a RESTful API for an online store which can be used to manage different products.

### Details
You need to send us a design document on how you would implement a RESTful API for an online store. It should support addition, deletion, editing and searching a product. You are free to assume everything else but make sure you document them. Make sure you have considered things like authentication (only authenticated users can add / view / edit / delete items).

### Solution
This project uses mongodb as its only database.
Choice of mongodb is questionable if transactions are very high.
For large scale systems I'll choose elasticsearch over it along with Redis for caching few things.
To run this project make sure you have JAVA 8 installed on system.
For building this project Maven is required.
I am adding the current architecture image. Please ignore Elasticsearch in it. To reduce setup i have stored product in mongodb for the time being. But in real world application i'll choose elastic as it provides complex search capabilitis.

### Architecture
![Image of Yaktocat](https://studyplatform.s3.amazonaws.com/61f55c69-cc64-4481-8a47-1e384a07c1a4.png) 

### Steps to build
* Clone the project by using following command "git clone https://github.com/aman1064/wingify.git"
* Go to project directory where pom.xml is present and run following command "mvn clean package"
* The final build will be present in target directory with name api-server-0.0.1-SNAPSHOT.jar

### Running the project
* To run simply do java -jar api-server-0.0.1-SNAPSHOT.jar --aws.access.key=accessKey --aws.secret.key=secretKey
* To check list of API present in the system go to following url http://localhost:8080/swagger-ui.html

### List of API
* User Registration: To register user. Please note this api saves raw password in database. In realword applications this needs to be hashed before getting saved in DB. I usually use BCrypt Encoder for it.
  * API Endpoint: /api/user/save
  * Request Type: POST
  * Request Payload: 
    ```javascript
      {
      "userName": "aman1064",
      "emailid": "aman1064@gmail.com",
      "name": "Amandeep Singh",
      "password":"12345"
      }
    ```
  * Response:
    Json Response with saved user details ignore the extra fields like accountNonExpired,authorities,credentialsNonExpired,accountNonLocked,enabled.
    These fields jave been kept in case security policy is introduced in system, or a ROLE Based authentication system comes into picture
    ```javascript
    {
    "response": {
        "userName": "aman1064",
        "name": "Amandeep Singh",
        "emailid": "aman1064@gmail.com",
        "password": null,
        "authorities": null,
        "accountNonExpired": true,
        "credentialsNonExpired": true,
        "accountNonLocked": true,
        "username": "aman1064",
        "enabled": true
      },
    "error": null
    }
    ```

* User Authentication: To issue an authorization token back to client on succesfull authentication.
  * API Endpoint: /api/login
  * Request Type: POST
  * Request: Should be a form POST with params "userName" and "password"
  * Response: Will return the JSON with user details and a authentication header with name 'T'. This header is a JWT Token for stateless authentication.
  
* Adding a product: To add a new product to the DB.
  * API Endpoint: /api/product
  * Request Type: PUT
  * Request Headers  
    * T: JWT Token
  * Request Payload
    ```javascript
          {
        "title": "Samsung J7",
        "description": "One of the android phones available in the market",
        "productDetails": {
          "brand": "Samsung",
          "modelNumber": "J7",
          "releaseDate": 1489944527000,
          "lotNumber":"March2017",
          "productType":"Mobile"
        },
        "groupDetails":{
          "groups":[
            {
              "groupName":"Phones",
              "subGroup":"Android"
            }
            ]
        },
        "dimensions": {
          "width": 10,
          "height": 10,
          "depth": 1
        },
        "weight": 150,
        "quantity": 99,
        "pricing": {
          "mrp": 10000.0,
          "sellingprice": 9838.5
        },
        "otherDetails":{
          "otherDetail1":"Detail Value",
          "otherDetail2":"2nd Value"
        }
      }
    ```
* Updating a product: To update existing product to the DB.
  * API Endpoint: /api/product
  * Request Type: POST
  * Request Headers: 
    * T: JWT Token
  * Request Payload
    ```javascript
    {
        "productid": "productid"
        "title": "Samsung J7",
        "description": "One of the android phones available in the market",
        "productDetails": {
          "brand": "Samsung",
          "modelNumber": "J7",
          "releaseDate": 1489944527000,
          "lotNumber":"March2017",
          "productType":"Mobile"
        },
        "groupDetails":{
          "groups":[
            {
              "groupName":"Phones",
              "subGroup":"Android"
            }
            ]
        },
        "dimensions": {
          "width": 10,
          "height": 10,
          "depth": 1
        },
        "weight": 150,
        "quantity": 99,
        "pricing": {
          "mrp": 10000.0,
          "sellingprice": 9838.5
        },
        "otherDetails":{
          "otherDetail1":"Detail Value",
          "otherDetail2":"2nd Value"
        }
      }
    ```
* Get Product By productid: To get product details by product id.
  * API Endpoint: /api/product
  * Request Type: GET
  * Request Header
    * T: JWT Token
  * Request: GET request with mandatory request param **productid**
  * Response: Will return the JSON with product details

* Delete Product By productid: To delete product details by product id.
  * API Endpoint: /api/product
  * Request Type: DELETE
  * Request Header
    * T: JWT Token
  * Request: DELETE request with mandatory request param **productid**
  * Response: Will return the JSON with true value in response node on sucessfull deletion
  
* Search Product: This will search only in title. Can be done similarly for other fields. 
  * API Endpoint: /api/product/search
  * Request Type: GET
  * Request Header
    * T: JWT Token
  * Request: GET request with mandatory request param **search**, **page**, **size**
  * Response: Will return the JSON with true value in response node on sucessfull deletion

* Get Product By Brand and Type: This will search only in title. Can be done similarly for other fields. 
  * API Endpoint: /api/products
  * Request Type: GET
  * Request Header
    * T: JWT Token
  * Request: GET request with mandatory request params **brand** , **type**, **page**, **size**
  * Response: Will return the JSON with true value in response node on sucessfull deletion
