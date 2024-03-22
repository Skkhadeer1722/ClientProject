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
-- Table structure for table `national_identifier`
--

DROP TABLE IF EXISTS `national_identifier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `national_identifier` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(255) DEFAULT NULL,
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
  `identifier_type` varchar(255) DEFAULT NULL,
  `identifier_value` varchar(255) DEFAULT NULL,
  `source_id` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `tenant_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `national_identifier`
--

LOCK TABLES `national_identifier` WRITE;
/*!40000 ALTER TABLE `national_identifier` DISABLE KEYS */;
INSERT INTO `national_identifier` VALUES (1,'cf4b0154-49ca-4d0a-b5b1-94df58df933e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'DRIVING_LICENCE_NUMBER','3495934534052',NULL,_binary '\0',NULL),(2,'cf4b0154-49ca-4d0a-b5b1-94df58df933e',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'PASSPORT_NUMBER','98347465773',NULL,_binary '\0',NULL);
/*!40000 ALTER TABLE `national_identifier` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:31:35
