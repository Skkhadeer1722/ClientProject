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
-- Table structure for table `telephone`
--

DROP TABLE IF EXISTS `telephone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telephone` (
  `id` varchar(255) NOT NULL,
  `active` int NOT NULL,
  `coutry_code` varchar(255) DEFAULT NULL,
  `created_timestamp` datetime(6) DEFAULT NULL,
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
  `isprimary` bit(1) NOT NULL,
  `isverified` bit(1) NOT NULL,
  `national_number` varchar(255) DEFAULT NULL,
  `source_id` varchar(255) DEFAULT NULL,
  `tenant_id` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `updated_timestamp` datetime(6) DEFAULT NULL,
  `version` int NOT NULL,
  `contact_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3aqryjb4pwb5g66n99qxuvo5o` (`contact_id`),
  CONSTRAINT `FK3aqryjb4pwb5g66n99qxuvo5o` FOREIGN KEY (`contact_id`) REFERENCES `contact_details` (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telephone`
--

LOCK TABLES `telephone` WRITE;
/*!40000 ALTER TABLE `telephone` DISABLE KEYS */;
INSERT INTO `telephone` VALUES ('aea41c7b-4983-4c9d-a879-1565d84d95ae',1,'IND','2022-03-31 14:39:54.377822',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '',_binary '\0','9440562301',NULL,NULL,'MOBILE','2022-04-01 11:40:56.865118',1,'5fb2e088-c7bf-4451-beec-87b306012482'),('bae9f9e9-6c28-472d-bae4-c4ccdd935592',1,'IND','2022-03-31 14:39:54.377822',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '\0',_binary '\0','987654320',NULL,NULL,'HOME','2022-03-31 14:39:54.377822',0,'5fb2e088-c7bf-4451-beec-87b306012482');
/*!40000 ALTER TABLE `telephone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:31:39
