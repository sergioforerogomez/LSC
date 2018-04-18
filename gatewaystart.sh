#!/bin/bash
touch log.log

#Users mongo
mongod --dbpath ./mongoData/users --port 12356 >> ~/log.log 2>&1 &

for file in $(find ./LSC/LSC/ -name *SNAPSHOT.jar); do java -Dspring.profiles.active=prod -jar $file >> ~/log.log 2>&1 & done