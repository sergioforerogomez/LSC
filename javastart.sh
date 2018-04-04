#!/bin/bash
touch log.log
mongod --dbpath ./mongoData/test --port 27018 >> ~/log.log 2>&1 &
for file in $(find ./LSC/LSC -name *SNAPSHOT.jar); do java -jar $file >> ~/log.log 2>&1 & done
