# Authentication mechanisms implementations:

This repo contains implementation for following authentication mechanisms using Spring boot and Spring security:

 1. In-memory Authentication.
 2. JDBC Authentication using H2 database
 3. JPA Authentication using PostgreSQL
 4. LDAP Authentication (Not yet implemented)
 5. JWT Token Authentication (Not yet implemented)
 6. OAuth2.0 Authentication (Not yet implemented)

Directions for usage: To use any of the above authentication mechanisms, goto src/main/resources/application.properties and update the `authentication-type` property with any of the below values:

 1. in-memory (For In-memory authentication)
 2. h2-default-schema (For JDBC authentication with spring default schema)
 3. h2-custom-database-schema (For JDBC authentication with custom schema)
 4. db-authentication (For JPA Authentication)
 5. ldap (For LDAP authentication)
 6. jwt (For JWT authentication)
 7. oauth (For OAuth authentication)

