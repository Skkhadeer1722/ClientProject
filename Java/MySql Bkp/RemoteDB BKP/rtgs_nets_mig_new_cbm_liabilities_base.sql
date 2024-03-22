-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 103.177.224.100    Database: rtgs_nets_mig_new
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
-- Table structure for table `cbm_liabilities_base`
--

DROP TABLE IF EXISTS `cbm_liabilities_base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cbm_liabilities_base` (
  `member_id` varchar(36) NOT NULL COMMENT 'Member id',
  `sector_id` varchar(4) DEFAULT NULL COMMENT 'Sector id',
  `currency_code` varchar(3) NOT NULL COMMENT 'Currency code',
  `mcb_id` varchar(10) DEFAULT NULL COMMENT 'MCB id',
  `ql_lb_type` varchar(3) NOT NULL COMMENT 'QL / LB items',
  `action` varchar(30) DEFAULT NULL,
  `status` varchar(30) NOT NULL,
  `modified_by` varchar(36) DEFAULT NULL,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `approved_by` varchar(36) DEFAULT NULL,
  `approved_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_date` timestamp NOT NULL,
  `effective_date` timestamp NOT NULL,
  `approval_remark` varchar(140) DEFAULT NULL,
  `workflow_status_id` int DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cbm_liabilities_base`
--

LOCK TABLES `cbm_liabilities_base` WRITE;
/*!40000 ALTER TABLE `cbm_liabilities_base` DISABLE KEYS */;
/*!40000 ALTER TABLE `cbm_liabilities_base` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:34:35
