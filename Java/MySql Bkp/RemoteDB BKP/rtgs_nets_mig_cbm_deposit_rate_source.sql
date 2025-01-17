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
-- Table structure for table `cbm_deposit_rate_source`
--

DROP TABLE IF EXISTS `cbm_deposit_rate_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cbm_deposit_rate_source` (
  `id` varchar(36) NOT NULL,
  `tenor_period` varchar(30) DEFAULT NULL COMMENT 'Standing facility tenor period in calendar days',
  `deposit_rate` varchar(30) DEFAULT NULL COMMENT 'Deposit rate% for the standing facility tenor period',
  `facility_id` varchar(36) DEFAULT NULL COMMENT 'Facility id',
  `action` varchar(30) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `modified_by` varchar(36) DEFAULT NULL,
  `modified_date` varchar(30) DEFAULT NULL,
  `approved_by` varchar(36) DEFAULT NULL,
  `approved_date` varchar(30) DEFAULT NULL,
  `created_date` varchar(30) DEFAULT NULL,
  `effective_date` varchar(30) DEFAULT NULL,
  `approval_remark` varchar(140) DEFAULT NULL,
  `workflow_status_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cbm_deposit_rate_source`
--

LOCK TABLES `cbm_deposit_rate_source` WRITE;
/*!40000 ALTER TABLE `cbm_deposit_rate_source` DISABLE KEYS */;
/*!40000 ALTER TABLE `cbm_deposit_rate_source` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:31:47
