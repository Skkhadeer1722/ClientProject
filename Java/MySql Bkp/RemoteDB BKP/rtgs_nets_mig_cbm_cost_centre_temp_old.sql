-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 103.177.224.100    Database: rtgs_nets_mig
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
-- Table structure for table `cbm_cost_centre_temp_old`
--

DROP TABLE IF EXISTS `cbm_cost_centre_temp_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cbm_cost_centre_temp_old` (
  `cost_centre` varchar(36) NOT NULL COMMENT 'Cost centre',
  `description` varchar(50) NOT NULL COMMENT 'Cost centre description',
  `created_date` timestamp NOT NULL COMMENT 'Created date',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Modified date',
  `costCentre` varchar(255) NOT NULL,
  `createdDate` datetime(6) DEFAULT NULL,
  `modifiedDate` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`cost_centre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='CentralBankFunctions1_NA_CBM-Cost-Centre_Cost centre';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cbm_cost_centre_temp_old`
--

LOCK TABLES `cbm_cost_centre_temp_old` WRITE;
/*!40000 ALTER TABLE `cbm_cost_centre_temp_old` DISABLE KEYS */;
INSERT INTO `cbm_cost_centre_temp_old` VALUES ('438e9a00-b3b8-482d-bfab-445877b78adf','Description For 2150','2022-06-07 04:40:10','2022-06-07 04:40:10','',NULL,NULL),('8682b6b4-5181-4151-90fb-3981d7dd874d','Description For 2150','2022-06-07 04:40:10','2022-06-07 04:40:10','',NULL,NULL),('9d628fe7-16d2-4812-8360-9d08bac6d3e0','Description For 2150','2022-06-07 04:40:10','2022-06-07 04:40:10','',NULL,NULL),('b368813b-ebc0-4831-9b9b-6f17ff92cb21','Description For 2150','2022-06-07 04:40:10','2022-06-07 04:40:10','',NULL,NULL);
/*!40000 ALTER TABLE `cbm_cost_centre_temp_old` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:32:07
