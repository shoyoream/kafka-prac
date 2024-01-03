CREATE DATABASE kafka_prac;
CREATE USER 'kafka-user'@'localhost' IDENTIFIED BY 'kafka-user';
GRANT ALL PRIVILEGES ON kafka_prack.* TO 'kafka-user'@'localhost';
