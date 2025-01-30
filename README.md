# Spring Data JPA и Kotlin
REST API for project management

queries are implemented in the project

Project Management: 

GET /projects - Get a list of projects.
POST /projects - Create a new project.
PUT /projects/:id - Edit a project.
DELETE /projects/:id - Deleting a project.
+
Vacancies management (Optional - Additional Job):
GET /projects/:id/vacancies - Retrieve a list of vacancies for a specific project.
POST /projects/:id/vacancies - Add a job to a project.
PUT /vacancies/:id - Edit a vacancy.
DELETE /vacancies/:id - Deleting a vacancy.

there is a file for Postman in the project root
Hiroku One To Many.postman_collection.json
which already contains requests to the external server
https://entity-one-to-many-db45078fbac9.herokuapp.com/projects
with typed test data for PUT and POST requests 


aplication.yml - is configured to run on a remote server, so I do 
not provide requirements for local launch.
The project is currently running and available at the external link 
- https://kotlin-rest-api-e5bf80c4b125.herokuapp.com/projects.