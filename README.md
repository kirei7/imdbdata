# imdbdata

REQUIREMENTS
1. OS Linux
2. MySQL server 5.7.17 (optional)
3. Maven
4. Java 8

INSTALLATION
1. Clone repository to your local machine
2. Run 'install.sh' script
3. At some point, You will be asked to enter the password of MySQL user,
that is needed to create database and user for current project.

Database created: imdb

User created: 'dummy'@'localhost'

User password: password'


LAUNCHING
1. If You successfully passed INSTALLATION step, then run 'run.sh' script.
!!!Warning - be sure port 8080 isn't listened by any other app

HOW TO USE
1. Go to localhost:8080/
2. Fill in input fields
3. Hit 'Submit' button
4. Wait until batch job is completed
5. On successful completion of a batch job You will be notified
6. On unsuccessful completion You also will be notified
7. To properly end application go to localhost:8080/system/shutdown. 

NOTE
If You want to launch an application with in-memory database, You should only run following
commands in project root folder (the one with pom.xml file):

$ mvn clean

$ mvn package -P test

These commands will package ready-to-run application which can be launched WITHOUT MySQL.
