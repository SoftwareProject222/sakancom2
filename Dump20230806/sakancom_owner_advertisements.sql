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
-- Table structure for table `owner_advertisements`
--

DROP TABLE IF EXISTS `owner_advertisements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `owner_advertisements` (
  `idhouse_adv` int NOT NULL,
  `photos` varchar(45) DEFAULT NULL,
  `owner_name` varchar(45) DEFAULT NULL,
  `owner_contactInfo` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `services` varchar(45) DEFAULT NULL,
  `monthly_rent` double DEFAULT NULL,
  `note_rent` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `accept` varchar(10) DEFAULT NULL,
  `link` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idhouse_adv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner_advertisements`
--

LOCK TABLES `owner_advertisements` WRITE;
/*!40000 ALTER TABLE `owner_advertisements` DISABLE KEYS */;
INSERT INTO `owner_advertisements` VALUES (1124,'h1.jpg,h2.jpg,homed2.jpg','Lemara Ali','lemaraali@gmail.com, 0599632333','Nablus,PalestineStreet','internet',1000,'inclusive of electricity',5550,'yes','http://localhost/sakancom/p1124.php'),(1125,'h125.jpg,h125a.jpg,h125b.jpg','Noor Foqha','noorMF@gmail.com,0599617745','Nablus,AlQudsStreet','Cleaning Service,Security',500,'inclusive water',4655,'no','http://localhost/sakancom/p1125.php'),(1127,'h27.pnj','Lemara Ali','lemaraali@gmail.com,0599632333','Tulkarem','internet',550,'inclusive of electricity and water',7000,'no','http://localhost/sakancom/p1127.php');
/*!40000 ALTER TABLE `owner_advertisements` ENABLE KEYS */;
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
