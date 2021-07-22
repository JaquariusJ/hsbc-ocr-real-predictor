#!/bin/bash
dockerRegistry=115.29.238.138:5000
mirror="${dockerRegistry}/bbdm-svc:v_"$(date +"%Y%m%d%H%M%S")
deployName=bbdm
port=8092
namespace=4pd
replicas=1
cpuRequest=0.5m
cpuLimit=8m
memoryRequest=1Gi
memoryLimit=16Gi

#构建yaml
K8S_DEPLOY_TEMPLATE_PATH=bbdm-template.yaml
K8S_DEPLOY_PAHT=bbdm.yaml

echo "cp -a $K8S_DEPLOY_TEMPLATE_PATH $K8S_DEPLOY_PAHT"
cp -a $K8S_DEPLOY_TEMPLATE_PATH $K8S_DEPLOY_PAHT


sed -i "" "s#{{ deployName }}#${deployName}#g" ${K8S_DEPLOY_PAHT}
sed -i "" "s#{{ port }}#${port}#g" ${K8S_DEPLOY_PAHT}
sed -i "" "s#{{ namespace }}#${namespace}#g" ${K8S_DEPLOY_PAHT}
sed -i "" "s#{{ mirror }}#${mirror}#g" ${K8S_DEPLOY_PAHT}
sed -i "" "s#{{ replicas }}#${replicas}#g" ${K8S_DEPLOY_PAHT}
sed -i "" "s#{{ cpuRequest }}#${cpuRequest}#g" ${K8S_DEPLOY_PAHT}
sed -i "" "s#{{ cpuLimit }}#${cpuLimit}#g" ${K8S_DEPLOY_PAHT}
sed -i "" "s#{{ memoryRequest }}#${memoryRequest}#g" ${K8S_DEPLOY_PAHT}
sed -i "" "s#{{ memoryLimit }}#${memoryLimit}#g" ${K8S_DEPLOY_PAHT}

echo "docker build -t ${mirror} ."
docker build -t ${mirror} .
echo "docker push ${mirror}"
#docker push ${mirror}
echo "k apply -f bbdm.yaml"
kubectl apply -f bbdm.yaml


