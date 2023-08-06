-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: sakancom
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `tenant`
--

DROP TABLE IF EXISTS `tenant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tenant` (
  `idtenant` int NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `phone` int DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `id_apart` int DEFAULT NULL,
  `time_to_pay` varchar(45) DEFAULT NULL,
  `id_house` int DEFAULT NULL,
  `isStudent` varchar(20) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `major` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idtenant`),
  KEY `idapart_constraint` (`id_apart`),
  KEY `idhoseTenant_constraint` (`id_house`),
  CONSTRAINT `idapart_constraint` FOREIGN KEY (`id_apart`) REFERENCES `house_floor` (`id_apart`) ON UPDATE CASCADE,
  CONSTRAINT `idhoseTenant_constraint` FOREIGN KEY (`id_house`) REFERENCES `house` (`idhouse`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant`
--

LOCK TABLES `tenant` WRITE;
/*!40000 ALTER TABLE `tenant` DISABLE KEYS */;
INSERT INTO `tenant` VALUES (3,'farah7','101','Farah AlHasan',599877414,'farah7@gmail.com',1301,'After 3 days from now',1125,'yes',21,'Computer Engineering'),(4,'alaa15','1234','Alaa AlHasan',599866414,'alaah4@gmail.com',1301,'After 3 days from now',1125,'yes',21,'Computer Engineering'),(5,'ruba12','4321','Ruba Qawareeq',599866418,'rubaqq@gmail.com',1301,'After 3 days from now',1125,'yes',21,'Computer Engineering'),(6,'jana8','1111','Jana Barakeh',599860018,'janaB@gmail.com',1301,'After 3 days from now',1125,'yes',21,'Computer Engineering'),(7,'mohammed20','707','Mohammed AbuSaa',599860012,'mohabuSa3@gmail.com',3001,'After 3 days from now',1130,'no',NULL,NULL),(9,'Karam6','7072006','Karam Nasir',599440012,'karamabuSa3@gmail.com',1701,'After 3 days from now',1127,'no',NULL,NULL),(1789,'saf15a','1506','Safaa AbuSaa',599382728,'safaa@gmail.com',1202,'After 3 days from now',1124,'yes',18,'Laboratory medicine'),(9988,'zain26','2607','Zain AbuSaa',599112728,'zainsaa@gmail.com',1202,'After 3 days from now',1124,'no',NULL,NULL);
/*!40000 ALTER TABLE `tenant` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-06 20:03:34
