CREATE DATABASE  IF NOT EXISTS `qlks` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `qlks`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: qlks
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `app_role`
--

DROP TABLE IF EXISTS `app_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_uk` (`role_name`),
  UNIQUE KEY `APP_ROLE_UK` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_role`
--

LOCK TABLES `app_role` WRITE;
/*!40000 ALTER TABLE `app_role` DISABLE KEYS */;
INSERT INTO `app_role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `app_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `encryted_password` varchar(125) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `guest_guest_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_uk` (`user_name`),
  UNIQUE KEY `APP_USER_UK` (`user_name`),
  KEY `FKpu975pv9qbl87h1m26p7heafp` (`guest_guest_id`),
  CONSTRAINT `FKpu975pv9qbl87h1m26p7heafp` FOREIGN KEY (`guest_guest_id`) REFERENCES `guest` (`guest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (1,'admin','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',_binary '',NULL),(2,'user','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',_binary '',NULL),(3,'leduongk54a2','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',_binary '\0',6),(4,'manh1412','$2a$12$ct89.eUJTD0k0BoYVHCX3u8f7y8WGJxT.YrKCrAR2ls9WRpoM9qTi',_binary '\0',7);
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `employee_name` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Hà Nội','23/05/2001','leduongk54a2@gmail.com','Lê Mạnh Dương','Nam','0984851556',30000),(2,'qwewq','23/05/1999','lglggty@gmail.com','Nguyễn Văn A','Nữ','0984851557',50000);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest` (
  `guest_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `guest_name` varchar(255) DEFAULT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`guest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` VALUES (6,'son la','23/05/2001','ewqewq@erere.com','le manh duong','26456465456','097856645878'),(7,'Thai binh','19/08/1999','manh@kewqe.com','nguyen duc manh','654484897897','05598789789');
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (3);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental`
--

DROP TABLE IF EXISTS `rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rental` (
  `rental_id` bigint NOT NULL AUTO_INCREMENT,
  `check_in_date` datetime(6) DEFAULT NULL,
  `check_out_date` datetime(6) DEFAULT NULL,
  `guest_guest_id` bigint DEFAULT NULL,
  `room_room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`rental_id`),
  KEY `FKhlitkf949o9vc5x8lxf37jf5n` (`guest_guest_id`),
  KEY `FKfhgt5cbnijp2xh0bec97vfot6` (`room_room_id`),
  CONSTRAINT `FKfhgt5cbnijp2xh0bec97vfot6` FOREIGN KEY (`room_room_id`) REFERENCES `room` (`room_id`),
  CONSTRAINT `FKhlitkf949o9vc5x8lxf37jf5n` FOREIGN KEY (`guest_guest_id`) REFERENCES `guest` (`guest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental`
--

LOCK TABLES `rental` WRITE;
/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
INSERT INTO `rental` VALUES (32,'2022-05-31 16:22:06.720000','2022-05-31 16:34:43.402000',6,2),(33,'2022-05-31 16:22:09.838000','2022-05-31 16:24:46.245000',6,4),(34,'2022-05-31 16:22:14.685000',NULL,6,6),(35,'2022-05-31 16:22:27.838000',NULL,6,7),(37,'2022-05-31 16:32:40.125000','2022-05-31 16:33:57.990000',6,4),(38,'2022-05-31 16:34:12.679000','2022-05-31 16:37:40.615000',6,4),(39,'2022-05-31 16:34:56.563000','2022-05-31 16:35:09.359000',6,2),(40,'2022-05-31 16:35:40.266000','2022-05-31 16:43:05.761000',7,4),(41,'2022-05-31 16:35:50.322000','2022-05-31 16:36:11.608000',7,1),(42,'2022-05-31 16:36:00.445000',NULL,7,5),(43,'2022-05-31 16:36:25.499000','2022-05-31 16:36:30.992000',7,1),(44,'2022-05-31 16:38:52.176000',NULL,6,2),(45,'2022-05-31 16:44:49.205000','2022-05-31 16:45:02.531000',6,4),(46,'2022-05-31 16:45:18.373000','2022-05-31 16:45:22.101000',6,4),(47,'2022-05-31 16:45:45.260000','2022-05-31 16:45:51.087000',6,4),(48,'2022-05-31 16:50:06.654000','2022-05-31 16:50:23.308000',6,1),(49,'2022-05-31 16:50:36.468000','2022-05-31 16:50:41.558000',6,1);
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `room_id` bigint NOT NULL AUTO_INCREMENT,
  `is_empty` varchar(255) DEFAULT NULL,
  `price_day` double DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'Trống',700000,'Single'),(2,'Đã SD',500000,'Couple'),(4,'Trống',1000000,'Family'),(5,'Đã SD',1200000,'Family'),(6,'Đã SD',1000000,'Couple'),(7,'Đã SD',9000000,'Presidential');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `USER_ROLE_UK` (`user_id`,`role_id`),
  KEY `user_role_fk2` (`role_id`),
  CONSTRAINT `user_role_fk1` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`user_id`),
  CONSTRAINT `user_role_fk2` FOREIGN KEY (`role_id`) REFERENCES `app_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1),(3,2,2),(4,3,2),(5,4,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-31 18:21:16
