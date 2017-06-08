mvn clean install 
sudo docker stop app 
sudo docker rm app
sudo docker build -t rod/app .
sudo docker run -p 8081:8080 -d --name app --link banco:host-banco rod/app
