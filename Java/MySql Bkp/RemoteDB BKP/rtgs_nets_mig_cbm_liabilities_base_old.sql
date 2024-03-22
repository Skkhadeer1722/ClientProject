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
-- Table structure for table `cbm_liabilities_base_old`
--

DROP TABLE IF EXISTS `cbm_liabilities_base_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cbm_liabilities_base_old` (
  `id` varchar(36) NOT NULL COMMENT 'Liabilities id',
  `member_id` varchar(36) NOT NULL COMMENT 'Member id',
  `sector_id` varchar(4) DEFAULT NULL COMMENT 'Sector id',
  `currency_code` varchar(3) NOT NULL COMMENT 'Currency code',
  `mcb_id` varchar(10) DEFAULT NULL COMMENT 'MCB id',
  `ql_lb_type` varchar(3) NOT NULL COMMENT 'QL / LB items',
  `start_date` date NOT NULL COMMENT 'Start date for maintenance period',
  `end_date` date NOT NULL COMMENT 'End date for maintenance period',
  `ql_lb` decimal(23,5) DEFAULT NULL COMMENT 'QL / LB amount',
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='CentralBankFunctions1_Screen_CBM-Liabilities-Management_Liabilities management';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cbm_liabilities_base_old`
--

LOCK TABLES `cbm_liabilities_base_old` WRITE;
/*!40000 ALTER TABLE `cbm_liabilities_base_old` DISABLE KEYS */;
INSERT INTO `cbm_liabilities_base_old` VALUES ('0a991483-f61d-4c19-9378-b8f5d1fd963d','2','LBK','SGD','5','6','2021-06-10','2021-10-10',9.00000,'NULL','DELETED','0bdea729-4878-40b3-be91-7316aff49c86','2022-06-10 05:50:19','4d3921c4-9a6e-448d-b312-d282863d73d5','2022-06-10 05:50:19','2022-06-01 09:38:05','2022-05-29 18:30:00','approved',15);
/*!40000 ALTER TABLE `cbm_liabilities_base_old` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:31:46
