-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 103.177.224.100    Database: sss_nets_mig
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
-- Table structure for table `floating_rate_source`
--

DROP TABLE IF EXISTS `floating_rate_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `floating_rate_source` (
  `id` varchar(36) DEFAULT NULL,
  `reference_rate` varchar(50) DEFAULT NULL COMMENT 'Reference Rate',
  `publication_date` varchar(50) DEFAULT NULL COMMENT 'Publication Date',
  `value_date` varchar(50) DEFAULT NULL COMMENT 'Value Date',
  `rate` varchar(50) DEFAULT NULL COMMENT 'Rate',
  `action` varchar(50) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(50) DEFAULT NULL COMMENT 'Record status',
  `modified_by` varchar(50) DEFAULT NULL COMMENT 'Last modified User ID',
  `modified_date` varchar(50) DEFAULT NULL COMMENT 'Last modified timestamp',
  `approved_by` varchar(50) DEFAULT NULL COMMENT 'Last approved user ID',
  `approved_date` varchar(50) DEFAULT NULL COMMENT 'Last approved timestamp',
  `created_date` varchar(50) DEFAULT NULL COMMENT 'Created date of the record',
  `effective_date` varchar(50) DEFAULT NULL COMMENT 'Activation date of the record',
  `approval_remark` varchar(50) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` varchar(50) DEFAULT NULL COMMENT 'Approval flow ID',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(1000) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ca_screen_floating-rate_This table is used to save the calculated cash rate.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floating_rate_source`
--

LOCK TABLES `floating_rate_source` WRITE;
/*!40000 ALTER TABLE `floating_rate_source` DISABLE KEYS */;
INSERT INTO `floating_rate_source` VALUES ('aaaf50301dec46ed8c9352e1f4bef38d','SORA INDEX','20220506','20220515','1.2000000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,'2022-12-28 09:48:25'),('d24e536dd05a41c3a86f92c82bff2e8b','SORA INDEX','20220506','20220515','1.3000000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,'2022-12-28 09:48:25'),('36939f07b592453cb1caa41c3cf9bac8','SORA RATE','20220508','20220508','0.0200000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,'2022-12-28 09:48:25'),('c0318507d420410b9b72d9841ae0c41e','SORA RATE','20220506','20220510','0.0400000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,NULL,'2022-12-28 09:48:25'),('b4a8dac3e89b444bb5b267f59f297d4c','SORA RATE','20220508','20220508','-0.0200000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,' Rate : Field not decimal','2022-12-28 09:48:25'),('bc9e17b00a5a44efa9243cbcdaf8cb28','SORA RATE','20220506','-0220510','0.0400000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6,' Value Date : Field not numeric','2022-12-28 09:48:26'),('6083628037d34023bc21a8eb8a5e27bf','SORA INDEX','20220506','20220515','TEST',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,7,' Rate : Field not decimal','2022-12-28 09:48:26'),('868365c2a745465981abebfb84a8a0c9','SORA INDEX','20220506','TEST','1.3000000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8,' Value Date : Field not numeric','2022-12-28 09:48:26'),('fcd3c8409c024037b7e95afbfa92cfcc','SORA INDEX','TEST','20220519','1.3000000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,9,' Publication Date : Field not numeric','2022-12-28 09:48:26');
/*!40000 ALTER TABLE `floating_rate_source` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:34:31
