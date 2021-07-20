version = "hsbc-ocr-real-predictor-svc:v"$(date +"%Y%m%d%H%M%S")
echo "docker build -t $version ."
docker build -t $version .
echo "k apply -f deploy.yaml"
k apply -y deploy.yaml
