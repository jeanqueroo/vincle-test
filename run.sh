#!/bin/bash -xe

if [ -z "${M2_HOME}" ]; then
  export MVN_EXEC="mvn"
else
  export MVN_EXEC="${M2_HOME}/bin/mvn"
fi


cd vincle-rest
#ls -a

$MVN_EXEC clean package

#docker build -t vincle-rest:1.0 . -f docker/Dockerfile

#cd ../vincle-web

#docker build -t vincle-web:1.0 . -f docker/Dockerfile

cd ../

#docker run -p 80:80 -t vincle-web:1.0
#docker run -p 8080:8080 -t vincle-rest:1.0
ls -a
docker-compose -f docker-vincle.yml up --build -d

