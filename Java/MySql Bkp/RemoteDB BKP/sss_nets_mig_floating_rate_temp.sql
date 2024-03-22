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
-- Table structure for table `floating_rate_temp`
--

DROP TABLE IF EXISTS `floating_rate_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `floating_rate_temp` (
  `id` varchar(36) NOT NULL,
  `reference_rate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Reference Rate',
  `publication_date` int NOT NULL COMMENT 'Publication Date',
  `value_date` int NOT NULL COMMENT 'Value Date',
  `rate` decimal(13,10) DEFAULT NULL COMMENT 'Rate',
  `action` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(30) NOT NULL COMMENT 'Record status',
  `modified_by` varchar(36) NOT NULL COMMENT 'Last modified User ID',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last modified timestamp',
  `approved_by` varchar(36) NOT NULL COMMENT 'Last approved user ID',
  `approved_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last approved timestamp',
  `created_date` timestamp NOT NULL COMMENT 'Created date of the record',
  `effective_date` timestamp NULL DEFAULT NULL COMMENT 'Activation date of the record',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` int DEFAULT NULL COMMENT 'Approval flow ID',
  `remarks` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ca_screen_floating-rate_This table is used to save the calculated cash rate.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floating_rate_temp`
--

LOCK TABLES `floating_rate_temp` WRITE;
/*!40000 ALTER TABLE `floating_rate_temp` DISABLE KEYS */;
INSERT INTO `floating_rate_temp` VALUES ('36939f07b592453cb1caa41c3cf9bac8','SORA RATE',20220508,20220508,0.0200000000,' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-28 04:18:25','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-28 04:18:25','2022-12-28 04:18:25','2022-12-28 04:18:25','Data Migration',NULL,NULL),('aaaf50301dec46ed8c9352e1f4bef38d','SORA INDEX',20220506,20220515,1.2000000000,' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-28 04:18:25','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-28 04:18:25','2022-12-28 04:18:25','2022-12-28 04:18:25','Data Migration',NULL,NULL),('c0318507d420410b9b72d9841ae0c41e','SORA RATE',20220506,20220510,0.0400000000,' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-28 04:18:25','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-28 04:18:25','2022-12-28 04:18:25','2022-12-28 04:18:25','Data Migration',NULL,NULL),('d24e536dd05a41c3a86f92c82bff2e8b','SORA INDEX',20220506,20220515,1.3000000000,' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-28 04:18:25','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-28 04:18:25','2022-12-28 04:18:25','2022-12-28 04:18:25','Data Migration',NULL,'Duplicate ReferenceRate,Duplicate ValueDate');
/*!40000 ALTER TABLE `floating_rate_temp` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:34:24
