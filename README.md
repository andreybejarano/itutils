# IT Utils

Library to easy set-up an environment for integration tests using:

  - Embedded MongoDB
  - Embedded Cassandra
  - Embedded HSQL DB
  - Embedded Redis
  - Wiremock Server

You can also:
  - Create an ```application.yml``` for spring boot

### Usage

*Step 1*: Add maven dependency under _pom.xml_.

```mvn
<dependency>
    <groupId>com.almundo.commons</groupId>
    <artifactId>itutils</artifactId>
    <version>0.1.4</version>
    <scope>test</scope>
</dependency>
```

*Step 2*: Init test context.

```java
    @BeforeClass
    public static void init() throws Exception {
        ITUtils itUtils = ITUtilsFactory.getITUtils();
        itUtils.startMongo();
        itUtils.startHsql(Optional.of("db_scripts/init-hsql.sql"));
        itUtils.startCassandra(Optional.of("db_scripts/init-cassandra.cql"));
        itUtils.startRedis();
        itUtils.startWireMock();
        itUtils.generateIntegrationYml();
    }
```

> The scripts to initialize db's should be located in the classpath


### Adding mock web services
In this library we use [wiremock](http://wiremock.org/docs/) to mock web services. You can add a service stub as following:

```java
    ITUtils itUtils = ITUtilsFactory.getITUtils();
    WireMockServer wireMockServer = itUtils.getWireMockServer();

    wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/api/foo"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBodyFile("foo-response.json")));
```

### Generating application.yml for spring boot
You need to create an ```application-template.yml``` file in yout classpath using the following:

* ```127.0.0.1``` for the db's and mocked webservices IP's.
* ```mongoPort``` as port of MongoDB.
*  ```cassandraPort```as port of Cassandra.
*  ```hsqlPort```as port of HSQL DB.
*  ```redisPort```as port of Redis.
*  ```mockServerPort``` as port of mocked webservices.

For example:
```yaml
    # application-template.yml
    spring:
        data:
            mongodb:
                uri: "mongodb://127.0.0.1:mongoPort/fooDB"
```

And then before your tests:

```java
    @BeforeClass
    public static void init() throws Exception {
        ITUtils itUtils = ITUtilsFactory.getITUtils();
        // start needed db's and wiremock
        itUtils.generateIntegrationYml();
    }
```
