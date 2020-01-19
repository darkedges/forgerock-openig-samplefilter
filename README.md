# 

## Install Maven Dependencies

```bash
cd IG-eval-6.5.2/WEB-INF/lib
mvn install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=openig-core-6.5.2.jar -DartifactId=openig-core -Dversion=6.5.2 -Dpackaging=jar
mvn install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=chf-http-core-24.0.20.jar -DartifactId=chf-http-core -Dversion=24.0.20 -Dpackaging=jar
mvn install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=slf4j-api-1.7.21.jar -DartifactId=slf4j-api -Dversion=1.7.21 -Dpackaging=jar
mvn install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=forgerock-util-24.0.20.jar -DartifactId=forgerock-util -Dversion=24.0.20 -Dpackaging=jar
mvn install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=config-24.0.20.jar -DartifactId=config -Dversion=24.0.20 -Dpackaging=jar
mvn install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=jackson-core-2.10.0.jar -DartifactId=jackson-core -Dversion=2.10.0 -Dpackaging=jar
mvn install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=jackson-databind-2.10.0.jar -DartifactId=jackson-databind -Dversion=2.10.0 -Dpackaging=jar
mvn install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=jackson-annotations-2.10.0.jar -DartifactId=jackson-annotations -Dversion=2.10.0 -Dpackaging=jar
mvn install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=contrib-http-framework-6.5.2.jar -DartifactId=contrib-http-framework -Dversion=6.5.2 -Dpackaging=jar
mvn install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=juel-api-2.2.7.jar -DartifactId=juel-api -Dversion=2.2.7 -Dpackaging=jar
mvn install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=juel-impl-2.2.7.jar -DartifactId=juel-impl -Dversion=2.2.7 -Dpackaging=jar
mvn install:install-file -DgroupId=org.forgerock.openig -DgeneratePom=true -Dfile=json-resource-24.0.20.jar -DartifactId=json-resource -Dversion=24.0.20 -Dpackaging=jar
```

## Update Maven Dependency

on Windows
edit `$HOME/.m2/registry/org/forgerock/openig/openig-core/6.5.2/openig-core-6.5.2.pom` and the following lines

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