# Paylocity Coding Challenge - Michal Chudy

## Prerequisites 
* You will need Java 8 (JDK) installed on your machine
* Create a system variable called `JAVA_HOME` and point to the home directory of the JDK. 
Generally, on Windows, this will be in `C:\Program Files\Java\jdk1.8.0_xxx`

## Running the application
 You can run the application from within your IDE (see "Dev setup") or from the command line (see "Running the application from the command line")

## Dev setup
* Checkout the sources from `https://github.com/michal-chudy/paylocity-challenge`
* Import the source to your favourite IDE such as IntelliJ IDEA (recommended) or Eclipse
    * Import the `pom.xml` as a new Maven project
    * Your IDE should setup the modules automatically and download all the dependencies
* Run the application (the following steps are specific to IntelliJ IDEA):
    * IntelliJ will automatically recognize that this is a Spring Boot application and will create a Runtime Configuration. 
    You should see a new `BenefitChallengeApplication` configuration in the list of runtime configurations.
    * Run the configuration
    * The application will start in 3-4 seconds
* The application is using an in-memory `H2 database`
    * The DB is pre-populated with sample data that can be found in [data.sql](src/main/resources/data.sql). This data includes:
        * 3x Employee
            * ID 1: `John Doe`
            * ID 2: `Jane Doe`
            * ID 3: `Anna Green` (for benefit discount testing)
        * An [EmployeeSalary](src/main/java/com/paylocity/benefitchallenge/domain/EmployeeSalary.java) for each employee
        * A `DRAFT` [Payroll](src/main/java/com/paylocity/benefitchallenge/domain/Payroll.java) for each employee
    * Once the application is running, you can access the H2 DB console on [http://localhost:8080/h2-console](http://localhost:8080/h2-console) in your browser. 
    A login form will appear.
        * The login credentials will be pre-populated in the login form
        * The `JDBC URL` has to be changed to: `jdbc:h2:mem:paylocity`

## Running the application from the command line
* The source code comes with Windows and Unix/Linux code compilation scripts. To compile the source code 
into an executable format, run the following command in the application root directory. Note, this may take a while 
as the command will download all the dependencies from the maven repo.
    * Windows: `.\mvnw.cmd clean install`
    * Unix/Linux: `./mwnw clean install`
* Navigate to the target directory `cd target`
* Execute `java -jar benefit-challenge-0.0.1-SNAPSHOT.war`

## Interaction with the application
* The application includes a generated SwaggerUI for interactions with the APIs. Alternatively, you can use Postman or any other 
API development tool.
* Once the application is running, visit [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) in a web browser. 
You can send requests to the APIs directly from the SwaggerUI:
    * Expand the API you'd like to test
    * Click `Try it out`
    * Update the request body
    * Click `Execute` 
* As the first thing, we have to create dependents. Dependents have to be associated with an Employee (see data model in [TECH_DESIGN.md](docs/TECH_DESIGN.md)). 
Use one of the pre-generated employees. Example request body:
```
{
  "firstName": "Aaron",
  "lastName": "Doe",
  "employeeId": 1,
  "dependentType": "CHILD"
}
```
* Next step is creating benefits. Benefit can be associated either with a dependent or with an employee.
    * An example request for creating a benefit for the dependent created in the previous step:
     ```
     {
       "employeeId": 1,
       "benefitType": "DEPENDENT",
       "dependentId": 1
     }
     ```
    * Similarly, creating a benefit for an employee can be done by using the following request body:
    ```
    {
        "employeeId": 1,
        "benefitType": "EMPLOYEE"    
    }
    ```
* You can then preview all the benefits recorded against a selected employee by executing the following GET request
    * `GET /benefits/employee/{employeeId}`. For the examples above, use `1` for `employeeId`
* Last step would be previewing the next payroll for the employee. This can be done by exploring the the `/payrolls` API
    * Execute `GET /payrolls/employee/1?payrollStatus=DRAFT`
    * The application will calculate the total benefit deductions for the next payroll and the net salary that the employee will receive.

# Tech stack used
* Spring Boot 2.7.1 with an embedded Tomcat 9
* H2 database
* OpenAPI docs with Swagger UI

# Copyright
@ 2023 Michal Chudy. All Rights Reserved.
