# eliftech-assignment

Requirements: java8, maven 3+

Build & Launch

1. Go to project root and run 'mvn package'
2. After successful build in 'application/target' folder
You can find 'eliftech-assignment.application-0.0.1-SNAPSHOT.jar' file. It could
have not exactly the same name, depending on the current project version.
3. To launch an application run command: 'java -jar eliftech-assignment.application-0.0.1-SNAPSHOT.jar h2'
4. Now an application should be running with embedded database and You can use it on 'localhost:8080'.

NOTE: there is 'h2' argument passed to application. This argument indicates that application should
use an embedded database. Although there are still no more arguments (and databases) currently supported.
