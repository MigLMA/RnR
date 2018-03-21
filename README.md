# Requirements
#### ==========================================================================================
## Software
* Java 1.8+
* Maven 3.5.2+

## Required ENV vars
* JAVA_HOME - location of a JDK home dir

## Optional ENV vars
* M2_HOME - location of maven2's installed home dir

## Path var
* Add 'M2_HOME/bin' folder to Path variable, in order to be able to use mvn command from everywhere.

#### ==========================================================================================

# Cognizant Rewards and Recognition service project

* Set Server Port on application.yml
* Set Active profile in application.yml: spring.profiles.active
* To run the server: clone repository & go inside project folder & execute: **mvn spring-boot:run**
* API help: access <http://localhost:4444/swagger-ui.html> (using "dev" active-profile) 

#### ==========================================================================================  

# Unit-testing & Coverage

* Load project on eclipse IDE.
* Find file (Ctrl+Shift+R): AllTestSuites.java
* Right-click on the file and execute **Run as JUnit test**
* If Coverage plugin is installed, Right-click on the file and execute **Coverage as JUnit test** 


#### ==========================================================================================

# Before deploying to PRODUCTION

- [ ] Set active profile to 'prod' on application.yml  
  


#### ==========================================================================================


# SonnarQube & coverage

* Start SonarQube server: **docker run -d --name sonarqube -p 9000:9000 -p 9092:9092 sonarqube**
* Make the report: **mvn sonar:sonar**
* Access the report at <http://localhost:9000/projects?sort=-analysis_date>


#### ==========================================================================================


# To build the Docker image, run the following command:


$ docker build -t rnr/midtier:latest .


# Running the ```docker history rnr/midtier:latest``` command on created Docker image will let you see all layers that make up this image:

$ docker history rnr/midtier:latest
IMAGE               CREATED             CREATED BY                                      SIZE                COMMENT
a9338ae3f3ed        2 minutes ago       /bin/sh -c #(nop)  CMD ["/usr/bin/java" "-...   0B                  
8a78e40d58ad        2 minutes ago       /bin/sh -c #(nop) COPY file:030955a33ab51f...   22.2MB              
b36ec9de53a8        2 weeks ago         /bin/sh -c set -x  && apk add --no-cache  ...   77.5MB              
<missing>           2 weeks ago         /bin/sh -c #(nop)  ENV JAVA_ALPINE_VERSION...   0B                  
<missing>           2 weeks ago         /bin/sh -c #(nop)  ENV JAVA_VERSION=8u131       0B                  
<missing>           2 weeks ago         /bin/sh -c #(nop)  ENV PATH=/usr/local/sbi...   0B                  
<missing>           2 weeks ago         /bin/sh -c #(nop)  ENV JAVA_HOME=/usr/lib/...   0B                  
<missing>           2 weeks ago         /bin/sh -c {   echo '#!/bin/sh';   echo 's...   87B                 
<missing>           2 weeks ago         /bin/sh -c #(nop)  ENV LANG=C.UTF-8             0B                  
<missing>           2 weeks ago         /bin/sh -c #(nop)  CMD ["/bin/sh"]              0B                  
<missing>           2 weeks ago         /bin/sh -c #(nop) ADD file:1e87ff33d1b6765...   3.97MB   


3.97MB Alpine Linux Layer
77.5MB OpenJDK JRE Layer
22.2MB Application JAR file


#### ========================================================================================


# Running the Java Application Docker container

In order to run the demo application, run following command:

```docker run -d --name demo-rnr -p 4444:8090 -p 8091:8091 rnr/midtier:latest```


#### ========================================================================================


Then, to test the application is deployed: just use curl or http (from httpie) to get to /ip endpoint:

```$ curl http://localhost:8090/api/ip
Running from IP address: 172.17.0.3
```
```$ http http://localhost:8090/api/ip
HTTP/1.1 200 
Content-Length: 36
Content-Type: text/plain;charset=UTF-8
Date: Tue, 21 Nov 2017 22:29:25 GMT
X-Application-Context: metadatatool-service:test:4444

Running from IP address: 172.17.0.3

```


#### ========================================================================================


# Resident Set Size (RSS): RSS = Heap size + MetaSpace + OffHeap size


#### ========================================================================================
















#### ========================================================================================

# Version history:


### 0.0.0: 
+ initial version, with sample endpoint (/ip).
+ swagger api documentation.
+ added CORS (Cross-Origin Resource Sharing) support.

