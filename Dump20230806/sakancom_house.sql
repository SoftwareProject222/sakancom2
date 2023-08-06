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
-- Table structure for table `house`
--

DROP TABLE IF EXISTS `house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `house` (
  `idhouse` int NOT NULL AUTO_INCREMENT,
  `images` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `services` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `id_owner` int DEFAULT NULL,
  `no_floors` int DEFAULT NULL,
  `no_tenant` int DEFAULT NULL,
  PRIMARY KEY (`idhouse`),
  KEY `idowner_constraint` (`id_owner`),
  CONSTRAINT `idowner_constraint` FOREIGN KEY (`id_owner`) REFERENCES `owner` (`idowner`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1132 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house`
--

LOCK TABLES `house` WRITE;
/*!40000 ALTER TABLE `house` DISABLE KEYS */;
INSERT INTO `house` VALUES (1124,'http://localhost/sakancom/p1124.html','Nablus,PalestineStreet','Cleaning Service,Security',6500,12,3,25),(1125,'http://localhost/sakancom/p1125.html','Nablus,AlQudsStreet','internet',4655,22,2,12),(1126,'house2.png','Old City','Cleaning Service,Security',6300,12,2,0),(1127,'h27.pnj','Tulkarem','internet',6800,12,1,0),(1128,'h28.png','Hajja','internet,Security',6000,22,1,0),(1129,'h29.png','Attil','Cleaning Services,internet',7020,12,2,0),(1130,'house30.jpg','Zeta','internet',8500,12,2,0);
/*!40000 ALTER TABLE `house` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-06 20:03:35
