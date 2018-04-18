#!/bin/bash
touch log.log

#Common mongo
mongod --dbpath ./mongoData/common --port 12357 >> ~/log.log 2>&1 &

#Dictionary mongo
mongod --dbpath ./mongoData/dictionary --port 12358 >> ~/log.log 2>&1 &

for file in $(find ./LSC/LSC -name *SNAPSHOT.jar); do java -Dspring.profiles.active=prod -jar $file >> ~/log.log 2>&1 & done