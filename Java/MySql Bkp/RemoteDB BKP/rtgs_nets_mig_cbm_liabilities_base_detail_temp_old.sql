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
-- Table structure for table `cbm_liabilities_base_detail_temp_old`
--

DROP TABLE IF EXISTS `cbm_liabilities_base_detail_temp_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cbm_liabilities_base_detail_temp_old` (
  `id` varchar(36) NOT NULL COMMENT 'Liabilities base detail id',
  `member_id` varchar(36) NOT NULL COMMENT 'Member id',
  `start_date` date NOT NULL COMMENT 'Start date of maintenance period',
  `end_date` date NOT NULL COMMENT 'End date of maintenance period',
  `ql_lb` decimal(23,5) DEFAULT NULL COMMENT 'QL/LB submission amount',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='CentralBankFunctions1_Screen_CBM-Liabilities-Management_Liabilities management';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cbm_liabilities_base_detail_temp_old`
--

LOCK TABLES `cbm_liabilities_base_detail_temp_old` WRITE;
/*!40000 ALTER TABLE `cbm_liabilities_base_detail_temp_old` DISABLE KEYS */;
INSERT INTO `cbm_liabilities_base_detail_temp_old` VALUES ('a05d36f2-6450-46bb-8125-2af1279ede39','2','2021-06-10','2021-10-10',1234567.00000);
/*!40000 ALTER TABLE `cbm_liabilities_base_detail_temp_old` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:31:54
