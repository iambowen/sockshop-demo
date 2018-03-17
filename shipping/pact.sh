#!/bin/bash

mvn clean package

docker-compose up -d

mvn pact:verify