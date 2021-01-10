# Nearby Point of Interest App

###Requirements 

In order to run this application you would need to have installed 
docker and docker compose. 

Entry script to run is: **docker compose up -d** from inside 
the folder which docker-compose.yaml exists

###Infrastructure

The application is a Java EE web app application that runs 
in Wildfly (*WildFly Full 21.0.2.Final*)

The database is a MySQL database (*MySQL Server 8.0.22*)

### Architecture 

The architecture of application is a 3-tier architecture with hexagonal design that consists of
1. nearby.poi.domain (Business Logic)
 
   It has all the domain objects and the services in order to find 
   the closest point of interest. It also provides the interfaces (adapters)
   in order the business domain communicate with the repository
   
2. nearby.poi.repository
    
   Is an outbound adapter responsible to communicate with the database
   and fetching the appropriate data. It consists of the jpa and 
   the repository. This module is responsible to communicate with 
   the MySQL
    
3. nearby.poi.soap 

   Is the web app application and it has the soap api methods 
   (http://localhost:8080/nearby.poi.api/PointOfInterestWebService?wsdl)
   
### Advantages of Architecture 

The advantages of this architecture are that we can 
replace the repository module or the presentation module without 
changing the business core logic. 

### Business Domain Service 

In order to find the closest point of interest the service uses 
haversine distance implementation by default. The client can 
override the default implementation by providing a custom 
implementation of *DistanceMetricInterface*. 

The service uses a map reduce with completable futures in order
to optimize the performance. The client must provide an implementation
of ExecutorService and the chunkNumber. Then the service chunks
the point of interests and passed to the theads to find the 
closest and then it reduces to find the one. 

### Caching implementation 

The cache of the application is a Singelton EJB that it is 
initialized on startup. 

### Table Of Point Of Interest In Database

    
POI Name | Latitude | Longitude
--- | --- | ---
Athens International Airport (ATH), Spata Srtemida, Greece | 37.937225 | 23.945238
Mount Olympus, Greece | 40.090549 | 22.361469
Santorini, Greece | 36.393154 | 25.461510
Athens,  Greece | 37.983810 | 23.727539
Thessaloniki Airport Makedonia (SKG), Thessaloníki, Greece | 37.983810 | 23.726110
Acropolis, Athens, Greece | 37.970833 | 23.726110
Thessaloniki, Greece | 40.629269 | 22.947412

### Libraries Used

1. Jupiter Junit - Mockito (testing purposes)
2. Hibernate - Hibernate Spatial (ORM implementation) 
3. logback-classic (Logging implementation)



