#!/bin/bash
version="bbdm-svc:v_"$(date +"%Y%m%d%H%M%S")
deployName=bbdm
port=8081
namespace=4pd
replicas=1
cpuRequest=1m
cpuLimit=1Gi
memoryRequest=1m
memoryLimit=6Gi

#构建yaml
K8S_DEPLOY_TEMPLATE_PATH=bbdm-template.yaml
K8S_DEPLOY_PAHT=bbdm.yaml

echo "cp -a $K8S_DEPLOY_TEMPLATE_PATH $K8S_DEPLOY_PAHT"
cp -a $K8S_DEPLOY_TEMPLATE_PATH $K8S_DEPLOY_PAHT


sed -i "" "s/{{ deployName }}/${deployName}/g" ${K8S_DEPLOY_PAHT}
sed -i "" "s/{{ port }}/${port}/g" ${K8S_DEPLOY_PAHT}
sed -i "" "s/{{ namespace }}/${namespace}/g" ${K8S_DEPLOY_PAHT}
sed -i "" "s/{{ imageRegistry }}/${version}/g" ${K8S_DEPLOY_PAHT}
sed -i "" "s/{{ replicas }}/${replicas}/g" ${K8S_DEPLOY_PAHT}
sed -i "" "s/{{ cpuRequest }}/${cpuRequest}/g" ${K8S_DEPLOY_PAHT}
sed -i "" "s/{{ cpuLimit }}/${cpuLimit}/g" ${K8S_DEPLOY_PAHT}
sed -i "" "s/{{ memoryRequest }}/${memoryRequest}/g" ${K8S_DEPLOY_PAHT}
sed -i "" "s/{{ memoryLimit }}/${memoryLimit}/g" ${K8S_DEPLOY_PAHT}

echo "docker build -t ${version} ."
docker build -t ${version} .
echo "k apply -f bbdm.yaml"
kubectl apply -f bbdm.yaml


