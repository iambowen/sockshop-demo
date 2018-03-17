#!/bin/bash

mvn clean package

docker-compose up -d --build

mvn pact:verify

EXIT_CODE=$?

docker-compose down -d

exit $EXIT_CODE
