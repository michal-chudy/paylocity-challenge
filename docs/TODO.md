# Data model
* DB performance optimisation - indexes (payroll -> payroll status + employeeId)
* Versioning system (cloning current versions of entities and archiving the old ones)
* Rules - cut out into its own entity
* Multi-currency for payroll/benefits etc.
* Settings as DB tables

# API
* HATEOAS links
* authentication
* authorization - only admin and the employee can enter benefits related to a particular Employee
* adding benefits - validation that only one benefit of type EMPLOYEE can exist per employee
* API performance monitoring

# Other
* auditing
* unit tests
* Javadoc
* logging
* application level caching - Settings objects