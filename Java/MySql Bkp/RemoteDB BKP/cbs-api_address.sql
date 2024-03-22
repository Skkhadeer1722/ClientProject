-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 103.177.224.100    Database: cbs-api
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` varchar(255) NOT NULL,
  `active` int NOT NULL,
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `coutry_code` varchar(255) DEFAULT NULL,
  `field_01` varchar(255) DEFAULT NULL,
  `field_02` varchar(255) DEFAULT NULL,
  `field_03` varchar(255) DEFAULT NULL,
  `field_04` varchar(255) DEFAULT NULL,
  `field_05` varchar(255) DEFAULT NULL,
  `field_06` varchar(255) DEFAULT NULL,
  `field_07` varchar(255) DEFAULT NULL,
  `field_08` varchar(255) DEFAULT NULL,
  `field_09` varchar(255) DEFAULT NULL,
  `field_10` varchar(255) DEFAULT NULL,
  `gone_away_status` bit(1) NOT NULL,
  `postalcode` varchar(255) DEFAULT NULL,
  `source_id` varchar(255) DEFAULT NULL,
  `tenant_id` varchar(255) DEFAULT NULL,
  `townorcity` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `contact_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi2b60s4eyx1srt8nm7eg4q71f` (`contact_id`),
  CONSTRAINT `FKi2b60s4eyx1srt8nm7eg4q71f` FOREIGN KEY (`contact_id`) REFERENCES `contact_details` (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES ('096a6dd7-8474-49c6-b7f5-e7ce5e00278b',0,'Street 1','line 2','USA','IND',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '','CF11 9AU',NULL,NULL,'HYD','REGISTERED','5fb2e088-c7bf-4451-beec-87b306012482'),('097f92a8-cf01-4e4c-b353-64455ec55304',0,'2nd line','yusuf guds','netherlands','mncb09',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '\0','123456',NULL,NULL,'hyderabad','PERSONAL','5fb2e088-c7bf-4451-beec-87b306012482');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:31:32
