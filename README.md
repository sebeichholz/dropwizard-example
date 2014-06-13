# Introduction

The drop wizard example application was developed to, as its name implies, provide examples of some of the features
present in drop wizard.

# Overview

# Running The Application

To test the example application run the following commands.

* To setup the h2 database run.

        gradle migrate

* To run the server run.

        gradle run

* To post data into the application.


    $ curl -H "Content-Type: application/json" -X POST -d '{"name":"Buy milk","description":"Actually, I meant to buy scotch"}' http://localhost:8080/tasks
	
	$ open http://localhost:8080/tasks

	
# Endpoints

    GET /tasks
    
    POST /tasks
     
    GET /tasks/complete
    
    GET /tasks/incomplete
    
    GET /task/:id

    PUT /task/:id
    
    DELETE /task/:id
    
    