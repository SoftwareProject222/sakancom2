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
-- Table structure for table `furniture`
--

DROP TABLE IF EXISTS `furniture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `furniture` (
  `idFurniture` int NOT NULL AUTO_INCREMENT,
  `tenant_id` int DEFAULT NULL,
  `tenant_name` varchar(50) DEFAULT NULL,
  `furniture_name` varchar(255) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`idFurniture`),
  KEY `id_tenant_furniture` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `furniture`
--

LOCK TABLES `furniture` WRITE;
/*!40000 ALTER TABLE `furniture` DISABLE KEYS */;
INSERT INTO `furniture` VALUES (1,1789,'Safaa AbuSaa','Sofa','Comfortable sofa for sale',250),(2,1789,'Safaa AbuSaa','table','medium',100),(3,9988,'Zain AbuSaa','table','small',60),(4,9988,'Zain AbuSaa','chair','Comfortable for sale',65),(5,0,'null','Sofa','Comfortable sofa for sale',250),(6,0,'null','Sofa','Comfortable sofa for sale',250),(7,0,'null','Sofa','Comfortable sofa for sale',250),(8,0,'null','Sofa','Comfortable sofa for sale',250),(9,0,'null','Sofa','Comfortable sofa for sale',250),(10,0,'null','Sofa','Comfortable sofa for sale',250),(11,0,'null','Sofa','Comfortable sofa for sale',250),(12,0,'null','Sofa','Comfortable sofa for sale',250),(13,0,'null','Sofa','Comfortable sofa for sale',250),(14,0,'null','Sofa','Comfortable sofa for sale',250),(15,0,'null','Sofa','Comfortable sofa for sale',250),(16,0,'null','Sofa','Comfortable sofa for sale',250),(17,0,'null','Sofa','Comfortable sofa for sale',250),(18,0,'null','Sofa','Comfortable sofa for sale',250),(19,0,'null','Sofa','Comfortable sofa for sale',250),(20,0,'null','Sofa','Comfortable sofa for sale',250),(21,0,'null','Sofa','Comfortable sofa for sale',250),(22,0,'null','Sofa','Comfortable sofa for sale',250),(23,3,'Farah AlHasan','chair','small chair for table',50),(24,0,'null','Sofa','Comfortable sofa for sale',250),(25,0,'null','Sofa','Comfortable sofa for sale',250),(26,0,'null','Sofa','Comfortable sofa for sale',250),(27,0,'null','Sofa','Comfortable sofa for sale',250),(28,0,'null','Sofa','Comfortable sofa for sale',250),(29,0,'null','Sofa','Comfortable sofa for sale',250);
/*!40000 ALTER TABLE `furniture` ENABLE KEYS */;
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
