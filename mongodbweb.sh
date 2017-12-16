#!bin/sh

# Prerequisites:
#	. Java
#	. Gradle
#	. MongoDB
#	. Postman


# Compile with Gradle
./gradlew build


# Start MongoDB, using alias for  
#	mongod 
#		--config "/Users/matteobaiguini/tools/mongodb-data/conf/mongod.conf" 
#		--dbpath "/Users/matteobaiguini/tools/mongodb-data/db" 
#		--logpath "/Users/matteobaiguini/tools/mongodb-data/logs/mongodb.log" 
#		--pidfilepath "/Users/matteobaiguini/tools/mongodb-data/conf/mongod-pid.txt")
mongod-start


# Run the application
java -jar build/libs/rs-mongodbweb-0.1.jar


# Use Postman to test the application on port 4000
# 	1) open Postman
# 	2) import the collection provided with the project
# 	3) run some tests


# Stop MongoDB, using alias for  
#	mongo
#		127.0.0.1/admin 
#		--eval "db.shutdownServer()
mongod-stop
