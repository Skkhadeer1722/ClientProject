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
-- Table structure for table `securities_coupon`
--

DROP TABLE IF EXISTS `securities_coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `securities_coupon` (
  `securities_code` varchar(12) NOT NULL,
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
  PRIMARY KEY (`securities_code`),
  KEY `fk_securities_coupon_modified_by_idx` (`modified_by`),
  KEY `fk_securities_coupon_approved_by_idx` (`approved_by`),
  CONSTRAINT `fk_securities_coupon_approved_by` FOREIGN KEY (`approved_by`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_securities_coupon_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_securities_coupon_securities_code` FOREIGN KEY (`securities_code`) REFERENCES `securities_code` (`securities_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ca_screen_securities-coupon_This table is store maintenance info of securities coupons';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securities_coupon`
--

LOCK TABLES `securities_coupon` WRITE;
/*!40000 ALTER TABLE `securities_coupon` DISABLE KEYS */;
INSERT INTO `securities_coupon` VALUES ('BCSISMBIL001',' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2023-01-03 04:26:27','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2023-01-03 04:26:27','2023-01-03 04:26:27','2023-01-03 04:26:27','Data Migration',NULL),('BCSISTEST012',' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2023-01-03 04:26:27','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2023-01-03 04:26:27','2023-01-03 04:26:27','2023-01-03 04:26:27','Data Migration',NULL),('MEPSUAT00016',' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2023-01-03 04:26:28','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2023-01-03 04:26:28','2023-01-03 04:26:28','2023-01-03 04:26:28','Data Migration',NULL),('MEPSUAT00024',' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2023-01-03 04:26:28','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2023-01-03 04:26:28','2023-01-03 04:26:28','2023-01-03 04:26:28','Data Migration',NULL);
/*!40000 ALTER TABLE `securities_coupon` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:34:25
