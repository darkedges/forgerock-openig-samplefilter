# ForgeRock Identity Gateway - Sample Filter

This projects implements the sample filter at [https://backstage.forgerock.com/docs/ig/6.5/gateway-guide/#about-custom-extensions](https://backstage.forgerock.com/docs/ig/6.5/gateway-guide/#about-custom-extensions)

It further adds in testing of both the [Request](src/test/java/org/forgerock/openig/doc/SampleFilterTest.java#L125-L156) and [Response](src/test/java/org/forgerock/openig/doc/SampleFilterTest.java#L125-L156) aspects of the filter through [src/test/java/org/forgerock/openig/doc/SampleFilterTest.java](src/test/java/org/forgerock/openig/doc/SampleFilterTest.java#L125-L156)


## Build

The following instruction concern installing the necessary artifacts to the maven local registry for those who do not have ForgeRock Maven Access.

## Download and extract the Identity Gateway Archive

1. First register an account with ForgeRock at [https://backstage.forgerock.com/account/register?next=https:%2F%2Fbackstage.forgerock.com%2F](https://backstage.forgerock.com/account/register?next=https:%2F%2Fbackstage.forgerock.com%2F)
2. When completed go to [https://backstage.forgerock.com/downloads/browse/ig/latest](https://backstage.forgerock.com/downloads/browse/ig/latest) and download the latest version.
   *_Note:_* As of this document the version is `6.5.2`
3. Extract `IG-eval-6.5.2.war` to a `<known location>`

### Install Maven Dependencies

```bash
cd <known location>/IG-eval-6.5.2/WEB-INF/lib
./mvnw install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=openig-core-6.5.2.jar -DartifactId=openig-core -Dversion=6.5.2 -Dpackaging=jar
./mvnw install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=chf-http-core-24.0.20.jar -DartifactId=chf-http-core -Dversion=24.0.20 -Dpackaging=jar
./mvnw install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=slf4j-api-1.7.21.jar -DartifactId=slf4j-api -Dversion=1.7.21 -Dpackaging=jar
./mvnw install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=forgerock-util-24.0.20.jar -DartifactId=forgerock-util -Dversion=24.0.20 -Dpackaging=jar
./mvnw install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=config-24.0.20.jar -DartifactId=config -Dversion=24.0.20 -Dpackaging=jar
./mvnw install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=jackson-core-2.10.0.jar -DartifactId=jackson-core -Dversion=2.10.0 -Dpackaging=jar
./mvnw install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=jackson-databind-2.10.0.jar -DartifactId=jackson-databind -Dversion=2.10.0 -Dpackaging=jar
./mvnw install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=jackson-annotations-2.10.0.jar -DartifactId=jackson-annotations -Dversion=2.10.0 -Dpackaging=jar
./mvnw install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=contrib-http-framework-6.5.2.jar -DartifactId=contrib-http-framework -Dversion=6.5.2 -Dpackaging=jar
./mvnw install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=juel-api-2.2.7.jar -DartifactId=juel-api -Dversion=2.2.7 -Dpackaging=jar
./mvnw install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=juel-impl-2.2.7.jar -DartifactId=juel-impl -Dversion=2.2.7 -Dpackaging=jar
./mvnw install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=json-resource-24.0.20.jar -DartifactId=json-resource -Dversion=24.0.20 -Dpackaging=jar
```

### Update Maven Dependency

on Windows
edit `~/.m2/registry/org/forgerock/openig/openig-core/6.5.2/openig-core-6.5.2.pom` and the following lines

```bash
    <dependencies>
		<dependency>
			<groupId>org.forgerock.openig</groupId>
			<artifiactId>openig-core</artifactId>
			<version>6.5.2</version>
		</dependency>
		<dependency>
			<groupId>org.forgerock.openig</groupId>
			<artifiactId>chf-http-core</artifactId>
			<version>24.0.20</version>
		</dependency>
		<dependency>
			<groupId>org.forgerock.openig</groupId>
			<artifiactId>slf4j-api</artifactId>
			<version>1.7.21</version>
		</dependency>
		<dependency>
			<groupId>org.forgerock.openig</groupId>
			<artifiactId>forgerock-util</artifactId>
			<version>24.0.20</version>
		</dependency>
		<dependency>
			<groupId>org.forgerock.openig</groupId>
			<artifiactId>config</artifactId>
			<version>24.0.20</version>
		</dependency>
		<dependency>
			<groupId>org.forgerock.openig</groupId>
			<artifiactId>jackson-core</artifactId>
			<version>2.10.0</version>
		</dependency>
		<dependency>
			<groupId>org.forgerock.openig</groupId>
			<artifiactId>jackson-databind</artifactId>
			<version>2.10.0</version>
		</dependency>
		<dependency>
			<groupId>org.forgerock.openig</groupId>
			<artifiactId>jackson-annotations</artifactId>
			<version>2.10.0</version>
		</dependency>
		<dependency>
			<groupId>org.forgerock.openig</groupId>
			<artifiactId>contrib-http-framework</artifactId>
			<version>6.5.2</version>
		</dependency>
		<dependency>
			<groupId>org.forgerock.openig</groupId>
			<artifiactId>juel-api</artifactId>
			<version>2.2.7</version>
		</dependency>
		<dependency>
			<groupId>org.forgerock.openig</groupId>
			<artifiactId>juel-impl</artifactId>
			<version>2.2.7</version>
		</dependency>
		<dependency>
			<groupId>org.forgerock.openig</groupId>
			<artifiactId>json-resource</artifactId>
			<version>24.0.20</version>
		</dependency>
	</dependencies>
```

### Perform a build

```bash
./mwnw clean package
```