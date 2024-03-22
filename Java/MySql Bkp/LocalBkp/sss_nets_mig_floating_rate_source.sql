-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: sss_nets_mig
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
INSERT INTO `floating_rate_source` VALUES ('434e4a5796f94da9b97a66b1b2bc1b4c','SORA INDEX','20220506','20220515','1.2000000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,'2022-12-21 15:08:16'),('e95942c1a854441a85bc8401514bc2b8','SORA INDEX','20220506','20220515','1.3000000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,'2022-12-21 15:08:16'),('d1321da02ed04fa5bbe5e4ca4f595188','SORA RATE','20220508','20220508','0.0200000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,'2022-12-21 15:08:17'),('eda9f3cde7a14cd7a32a59e0eda70148','SORA RATE','20220506','20220510','0.0400000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,NULL,'2022-12-21 15:08:17'),('a15db38d6cb84254a8c337e5b9cc9acc','SORA RATE','20220508','20220508','-0.0200000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,' Rate : Field not decimal','2022-12-21 15:08:17'),('84b39932c412419593842a5a0af5fb7e','SORA RATE','20220506','-0220510','0.0400000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6,' Value Date : Field not numeric','2022-12-21 15:08:17'),('fbd0d467772044c087bdd99d4a7ba2e9','SORA INDEX','20220506','20220515','TEST',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,7,' Rate : Field not decimal','2022-12-21 15:08:17'),('32676d70e293418a86f335403efc0f79','SORA INDEX','20220506','TEST','1.3000000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8,' Value Date : Field not numeric','2022-12-21 15:08:17'),('9ae1cd98bdd14bbc997531e663acaa2e','SORA INDEX','TEST','20220519','1.3000000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,9,' Publication Date : Field not numeric','2022-12-21 15:08:18');
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

-- Dump completed on 2023-01-09  9:19:55
