#!/bin/bash

#先打包
echo "=======================打包===================="
make clean package

#清理配置文件和包
echo "=======================清理原有配置文件和包===================="
echo "rm -rf ./deploy/bbdm/conf/* && rm -rf ./deploy/bbdm/lib/*"
rm -rf ./deploy/bbdm/conf/*
rm -rf ./deploy/bbdm/lib/*

#准备好配置文件和包
echo "=======================更新配置文件和包===================="
echo "
cp -a ./src/main/resources/application.properties ./deploy/bbdm/conf/
cp -a ./src/main/resources/application.yaml ./deploy/bbdm/conf/
cp -a ./src/main/resources/application-dev.yaml ./deploy/bbdm/conf/
cp -a ./src/main/resources/logback-spring.xml ./deploy/bbdm/conf/
cp -a target/hsbc-ocr-real-predictor.jar  ./deploy/bbdm/lib/
"
cp -a ./src/main/resources/application.properties ./deploy/bbdm/conf/
cp -a ./src/main/resources/application.yaml ./deploy/bbdm/conf/
cp -a ./src/main/resources/application-dev.yaml ./deploy/bbdm/conf/
cp -a ./src/main/resources/logback-spring.xml ./deploy/bbdm/conf/
cp -a target/hsbc-ocr-real-predictor.jar  ./deploy/bbdm/lib/