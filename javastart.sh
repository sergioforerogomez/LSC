#!/bin/bash
touch log.log
# Users mongo
mongod --dbpath ./mongoData/users --port 12356 >> ~/log.log 2>&1 &
# Common mongo
mongod --dbpath ./mongoData/common --port 12357 >> ~/log.log 2>&1 &
# Games mongo
mongod --dbpath ./mongoData/games --port 12358 >> ~/log.log 2>&1 &
# Dictionary mongo
mongod --dbpath ./mongoData/dictionary --port 12359 >> ~/log.log 2>&1 &
# Media mongo
mongod --dbpath ./mongoData/media --port 12360 >> ~/log.log 2>&1 &

for file in $(find ./LSC/LSC -name *SNAPSHOT.jar); do java -jar $file >> ~/log.log 2>&1 & done