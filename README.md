# Micro-services-commands : API RESTful

> Application micro-services réalisée en JAVA 11 Spring Boot

# Fonctionnalités
#### Microservices-commands :
This service is responsible for handling project's commands to be executed inside the micro-ci-docker-runner service.
## Communication with other microservices
#### From micro-ci-projects
When a build is triggered, micro-ci-projects service must send its data to this HTTP.

Content-Type must be application/json

The request body must be a JSON payload, it will be merged with the project's commands, if they are available (otherwise they will be substituted an empty array).

#### To micro-ci-docker-runner
After receiving data from micro-ci-projects, the resulting message will be sent to RabbitMQ in the al1.docker.runner.queue queue.

The payload will be in JSON format and will look like this:

```
{
     "process_id": 1001,
     "commands": [
         {
             "id": 1003,
             "command": "pytest cc",
             "stdout": true,
             "create_time": "Jul 11, 2020, 4:12:43 AM"
         },
         {
             "id": 1004,
             "command": "ls -l cc",
             "stdout": true,
             "create_time": "Jul 11, 2020, 4:12:43 AM"
         },
         {
             "id": 1005,
             "command": "docker run cc",
             "stdout": true,
             "create_time": "Jul 11, 2020, 4:12:43 AM"
         }
     ],
     "project": {
         "project_id": 1001
     }
 }
```

### Swaggger html API documentation
Build and go to the [API DOC](micro-ci.westus2.cloudapp.azure.com:40501/al1.commands-ci/swagger-ui.html#/)
