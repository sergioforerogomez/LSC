#!/bin/bash
touch log.log

for file in $(find ./LSC/LSC/ -name *SNAPSHOT.jar); do java -Dspring.profiles.active=prod -jar $file >> ~/log.log 2>&1 & done