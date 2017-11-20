# Micro-rest

A coding assessment.  A REST service built on [Spring Boot](https://projects.spring.io/spring-boot/) that provides the
following:

  1.  A "Hello World" endpoint.

  2.  An endpoint to accept a JSON object containing a paragraph of text and returns a JSON array of objects. The returned objects
      represent the unique words from the supplied paragraph along with a count of the number of occurrences. The array of objects
      is sorted alphabetically.

  3.  An endpoint to accept a number, N, and return a JSON array with the first N Fibonacci numbers.

  4.  An endpoint which creates two threads that become deadlocked with each other, monitors the two threads and detects the deadlock.

  5.  Three endpoints for database table manipulation:
      * One to add records
      * One to query records
      * One to delete records

  6.  An endpoint that queries an external REST service using Spring RestTemplate.

## Building

[Gradle](https://gradle.org) is used for dependency management and building.  The build file, `build.gradle`, is located in
the project's root directory.  To build using the command line make the project root the working directory and use gradle.

#### Build:

        gradle build

#### Clean:

        gradle clean

### Generating javaDocs documentation:

#### Public only

    The output is in build/docs/javadoc

        gradle javadoc

#### All

    The output is in docs/javadoc

        gradle javadocAll

### Running tests

To run the tests:

        gradle test

After running the tests a JaCoCo report can be generated with:

        gradle jacocoTestReport

and the results can be viewed by opening the file `build/jacocoHtml/index.html`.

## Running

To run from the command line use java with the -jar option and provide the path to the micro-rest JAR file.  The following
examples assume the current working directory is the root directory of the project and uses a relative path to the JAR file.

To run using the default service port of 8080:

        java -jar build/libs/micro-rest.jar

To have the application listen on a different port, use the `--service.port=PORTNUM` argument, where `PORTNUM` is the desired
port.  To use 9090, for example, run with:

        java -jar build/libs/micro-rest.jar --service.port=9090 

and the application will use port 9090 instead of 8080.

## The REST API

The API is accessed using HTTP.  Successful responses result in content specific to the endpoint and is described in the following
sections.  When errors occur during request processing each endpoint returns a common error object in the body.

Error response object structure:

```
        {
            status: "HTTP_STATUS_STRING",
            timestamp: "yyyy-MM-dd hh:mm:ss",
            message: "user-friendly-string",
            systemMessage: "system-error-message-string"
        }
```

Examples for transaction with the REST endpoints are given for curl.  The examples use the service on port 8080 the localhost.  For
remote access use the remote host and port numbers instead.

### 1) The "Hello World" endpoint

A simple message object is returned by this endpoint with the content "Hello World".

*   #### URL

        /hello-world

*   #### Method

        GET

*   #### Success Response

    + **Code:** 200 <br />
    + **Content:** `{ content: "Hello World" }`

*   #### Using curl

        curl http://localhost:8080/hello-world

### 2) The unique words in paragraph endpoint.

Accepts a paragraph of text and returns a list of objects each containing a word from the paragraph and the number of
times it was found in the paragraph.  The list is ordered alphabetically.

*   #### URL

        /words

*   #### Method

        POST

*   #### Data Params

    A JSON object containing a field named ```text``` in the request body.

        { "text": "the text of the paragraph" }

*   #### Success Response

    + **Code:** 200 <br/>
    + **Content:** `[ { word: "WORD", occurances: INT }, ... ]`

*   #### Error response

    + **Code:** 400 Bad Request <br/>
    + **Content:** An error object, described above

*   #### Using curl

        curl -X POST -H "Content-Type: application/json;charset=UTF8" -d '{ "text": "The text of the paragraph" }' http://localhost:8080/words

### 3) The Fibonacci number endpoint

A list of the first N Fibonacci numbers is returned as a JSON array of integers.

*   #### URL

        /fibonacci-numbers

*   #### Method

        GET

*   #### URL Params

    ##### Required:

        n=[integer]

*   #### Success Response

    + **Code:** 200 <br/>
    + **Content:** `[ 0, 1, 1, 2, ... ]`

*   #### Error response

    + **Code:** 400 Bad Request <br/>
    + **Content:** An error object, described above

*   #### Using curl

    Example: get the first 8 Fibonacci numbers

        curl http://localhost:8080/fibonacci-numbers?n=8

### 4) The threads deadlock endpoint

Creates two threads that become deadlocked with each other.  The condition will be detected and a message, "Deadlocked" returned.

*   #### URL

        /threads

*   #### Method

        GET

*   #### Success Response

    + **Code:** 200 <br/>
    + **Content:** `{ content: "Deadlocked" }`

*   #### Using curl

        curl http://localhost:8080/threads

### 5) The database manipulation endpoint

Add, query and delete records in a database.  To implement this endpoint the application has a database of sayings, or quotes made
by people.  Each record contains an ID, name and a quotation.  The schema for the table is:

    create table sayings(
        id integer not null identity,
        name varchar(64) not null,
        quote varchar(256) not null
    );

Sayings can be Read, Created and Deleted.

#### Read

> Reads all sayings or one saying by its ID.  ID values are integers.

*   #### URL

        /sayings
        /sayings/{id}

*   #### Method

        GET

*   #### Success Response

    ##### Read all

    + **Code:** 200 <br/>
    + **Content:** `[ { id: INT, name: "", quote: "" }, ... ]`

    ##### Read by ID

    + **Code:** 200 <br/>
    + **Content:** `{ id: INT, name: "", quote: "" }`

*   #### Error response

    + **Code:** 404 Not Found <br/>
    + **Content:** An error object, described above

*   #### Using curl

        curl http://localhost:8080/sayings
        curl http://localhost:8080/sayings/1

#### Create

> Creates a saying, storing it in the database.

*   #### URL

        /sayings

*   #### Method

        POST

*   #### Data Params
    A JSON object with the ```name``` and ```quote``` fields specified in the request body.

        { name: "...", quote: "..." }
    
*   #### Success Response

    + **Code:** 201 Created <br/>

*   #### Error response

    + **Code:** 400 Bad Request <br/>
    + **Content:** An error object, described above

*   #### Using curl

        curl -X POST -H "Content-Type: application/json;charset=UTF8" -d '{ "name": "", "quote": "" }' http://localhost:8080/sayings

#### Delete

> Deletes a saying from the database.  The ID of the saying is used to identify which saying is deleted.

*   #### URL

        /sayings/{id}

*   #### Method

        DELETE
    
*   #### Success Response

    + **Code:** 204 No Content <br/>

*   #### Using curl

        curl -X DELETE http://localhost:8080/sayings/1

### 6) External REST service query endpoint

Microrest will fetch entities from [JSONPlaceholder](https://jsonplaceholder.typicode.com/) when request are make via its
`/external` URL.  Internally it uses Spring's `RestTemplate` to make requests and process the responses to and from JSONPlaceholder.

These are the entities available from JSONPlaceholder

> * posts
* comments
* albums
* photos
* todos
* users

A collection of entities is requested by its name and a single entity is requested by adding its ID.  In the URL forms listed
below, the expression `{entity}` is a place holder for one of the names listed above.  The expression `{id}` is a place holder
for an entity's ID.  Entity ID values are integers.

*   #### URL

> URLs take the following form

            /external/{entity}
            /external/{entity}/{id}

> Nested URLs are possible in some cases.  The form is

            /external/{entity}/{id}/{nested-entity}

> For example, getting all comments for post with ID 1:

            /external/posts/1/comments

> Query parameters can be used as another way to get the same results.  This query will result is the same as the example above:

            /eternal/comments?postId=1

> Please see [JSONPlaceholder](https://jsonplaceholder.typicode.com/) for details.

*   #### Method

        GET

*   #### URL Params

    ##### Optional

    ```postId=[integer]``` - Used to identify a specific post

    ```userId=[integer]``` - Used to identify a specific user

   
*   #### Success Response

    ##### Read all

    + **Code:** 200 <br/>
    + **Content:** JSON array of objects for the requested entity.

    ##### Read by ID

    + **Code:** 200 <br/>
    + **Content:** JSON object for the requested entity.

*   #### Error response

    + **Code:** 400 Bad Request <br/>
    + **Content:** An error object, described above

*   #### Using curl

> Reading entities

            curl http://localhost:8080/external/posts
            curl http://localhost:8080/external/posts/1
            curl http://localhost:8080/external/comments
            curl http://localhost:8080/external/comments/1
            curl http://localhost:8080/external/users

            curl http://localhost:8080/external/posts?userId=1
            curl http://localhost:8080/external/comments?postId=2
