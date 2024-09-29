git pull;
mvn clean package -DskipTests;
docker build -t calendly:latest .;
nohup docker-compose up --build > output.log 2>&1 & disown;
tail -f output.log;