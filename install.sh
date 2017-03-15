mvn clean
mvn package -P prod
mysql -u root -p < bootstrap.sql

