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
-- Table structure for table `house_floor`
--

DROP TABLE IF EXISTS `house_floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `house_floor` (
  `id_house` int NOT NULL,
  `id_floor` int NOT NULL,
  `id_apart` int NOT NULL,
  `no_bathrooms` int DEFAULT NULL,
  `no_bedrooms` int DEFAULT NULL,
  `balcony` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_house`,`id_apart`),
  KEY `idx_id_apart` (`id_apart`),
  CONSTRAINT `fk_idhousefloor` FOREIGN KEY (`id_house`) REFERENCES `house` (`idhouse`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house_floor`
--

LOCK TABLES `house_floor` WRITE;
/*!40000 ALTER TABLE `house_floor` DISABLE KEYS */;
INSERT INTO `house_floor` VALUES (1124,1,1202,2,3,'yse'),(1124,1,1204,1,2,'yse'),(1125,1,1301,2,3,'no'),(1126,1,1402,2,3,'yes'),(1127,1,1701,2,2,'yes'),(1128,1,2801,2,2,'yes'),(1129,1,2901,2,2,'no'),(1129,1,2902,1,1,'yes'),(1130,1,3001,2,2,'no');
/*!40000 ALTER TABLE `house_floor` ENABLE KEYS */;
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
