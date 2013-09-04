MutliTenantDemo
-----------------

Exemple for using Multy-tenancy with spring


What
=======

This is an exemple of how to achieve multi-tenacy 
(see http://en.wikipedia.org/wiki/Multitenancy) with spring inside a java container.
This project use maven as building tool.

Required environment
====================

tomcat + postgres

It's not essential to use the same. You can use another db, or servlet container.
You'll just need some adaptation.


Creation of Db
================

To run this exemple you need to run the sql script
    /src/resource/import.sql

This will create 1 user, 3 db, 1 table in each db

Testing
=============

Compile the project
    mvn clean package

Deploy it in a tomcat, go to one of thoses pages:
http://localhost:8080/mt-1.0-SNAPSHOT/GOLD
http://localhost:8080/mt-1.0-SNAPSHOT/SILVER
http://localhost:8080/mt-1.0-SNAPSHOT/BRONZE

Then go to 
http://localhost:8080/mt-1.0-SNAPSHOT/whatEverYouWhant


There is two version a static and a dynamic version of this demo
