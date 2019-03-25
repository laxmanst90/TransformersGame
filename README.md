# Transformers Game

#Build the Application

mvn clean install
This should run all the test cases, and returns a Build success.

#Start the Application

mvn jetty:run
This should start they jetty server, and our services become active after jetty successfully starts. 

Note:- There is a jar package warning thrown while starting up jetty, please ignore them, I think its due to the jetty version and that jetty doesn't support JARS, so I have done a work around for jetty to start they jetty by including the below in pom.xml

<supportedPackagings>
    <supportedPackaging>jar</supportedPackaging>
</supportedPackagings>


#How to access the application
In the web.xml, I have made the URLS to contain the pattern "/webapi" and the TransformersResource URL is set as "/myresource".

#CRUD Operations of the GAME
So to access the application, Please use:-
#To see all the Transformers :- localhost:8080/webapi/myresource/all
#To see only autobots transformers :- localhost:8080/webapi/myresource/autobots
#To see only decepticons transformers :- localhost:8080/webapi/myresource/decepticons
#To see a particular autobot or a decepticon transformers :- http://localhost:8080/webapi/myresource/{transformerId}
#To add a transformer:- localhost:8080/webapi/myresource/ 
POST Request with Transformer Object in raw body:-
{
        "courage": 10,
        "endurance": 10,
        "fightResult": false,
        "firepower": 10,
        "intelligence": 10,
        "name": "Optimus Prime",
        "rank": 1,
        "skill": 10,
        "speed": 10,
        "strength": 10,
        "type": "Autobots"
    }
    
#To Update a transformer:- localhost:8080/webapi/myresource/{transformerId}
Put Request with transformerId to Update and the transformerObject in raw body.

{
        "courage": 10,
        "endurance": 10,
        "fightResult": false,
        "firepower": 10,
        "intelligence": 10,
        "name": "Predaking",
        "rank": 1,
        "skill": 10,
        "speed": 10,
        "strength": 10,
        "type": "Decepticons"
    }
    
#To Delete a transformer:- localhost:8080/webapi/myresource/{transformerId}
DELETE Request with transformerId to DELETE


#To Start the game
Please use this url to start the game:- localhost:8080/webapi/myresource/fight
If accessing the above url from a tool like postman, please make sure the Content-Type is set as 'text/plain'


#Things to consider while playing the game
I have included 3 transformers in the servletContext, so on server startup there will be three transformers(2 autobots and 1 decepticon) added.

If you have to access the above resources in post man, please make sure Content-Type is 'application/json'.
If you have to access localhost:8080/webapi/myresource/fight, please make sure you access it by setting the content-type as 'text/plain'.






