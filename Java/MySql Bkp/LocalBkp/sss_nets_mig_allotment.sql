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
-- Table structure for table `allotment`
--

DROP TABLE IF EXISTS `allotment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allotment` (
  `id` varchar(36) NOT NULL COMMENT 'Primary key (UUID)',
  `securities_code` varchar(12) NOT NULL COMMENT 'Securities Code related to allotment record',
  `auction_date` date DEFAULT NULL COMMENT 'Auction happended date of the Securities',
  `issuance_date` int NOT NULL COMMENT 'To display the date on which the securities are to be issued to participants',
  `allotment_price` decimal(8,5) NOT NULL COMMENT 'Allotment price of the securies',
  `total_nominal_amount_alloted` bigint DEFAULT NULL COMMENT 'Total nominal amount alloted already for that securities',
  `total_nominal_amount_to_be_alloted` bigint DEFAULT NULL COMMENT 'Total nominal amount to be allotmented',
  `first_allotment` tinyint NOT NULL COMMENT 'To display whether the current allotment of securities is the first allotment, i.e. allotment of a new issue and not a re-opening action',
  `total_settlement_amount` decimal(18,2) NOT NULL COMMENT 'To display the total settlement amount of the securities to be issued',
  `processed` tinyint DEFAULT NULL COMMENT 'This indicator is used to check whether the allotment transaction is created or not',
  `action` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(30) NOT NULL COMMENT 'Record status',
  `modified_by` varchar(36) NOT NULL COMMENT 'Last modified User ID',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last modified timestamp',
  `approved_by` varchar(36) NOT NULL COMMENT 'Last approved user ID',
  `approved_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last approved timestamp',
  `effective_date` timestamp NULL DEFAULT NULL COMMENT 'Activation date of the record',
  `created_date` timestamp NOT NULL COMMENT 'Created date of the record',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` int DEFAULT NULL COMMENT 'Approval flow ID',
  `planned_total_nominal_amount_allotted` bigint DEFAULT '0' COMMENT 'Running total of allotment member nominal amount',
  PRIMARY KEY (`id`),
  KEY `fk_allotment_securities_code_idx` (`securities_code`),
  CONSTRAINT `fk_allotment_securities_code` FOREIGN KEY (`securities_code`) REFERENCES `securities_code` (`securities_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='allotment_na_allotment_This table is used to save the allotment';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allotment`
--

LOCK TABLES `allotment` WRITE;
/*!40000 ALTER TABLE `allotment` DISABLE KEYS */;
INSERT INTO `allotment` VALUES ('49272c0d963d4c0aafa70484ad0eed88','MEPSUAT00016','2022-10-10',20221010,0.44000,0,1000000,1,12.00,1,' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2023-01-06 12:45:34','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2023-01-06 12:45:34','2023-01-06 12:45:34','2023-01-06 12:45:34','Data Migration',NULL,NULL),('d6c4404a029045cf9519ed2fe5ed1c51','MEPSUAT00016','2022-10-10',20221010,123.12345,0,1000000,1,100.01,1,' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2023-01-06 12:45:34','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2023-01-06 12:45:34','2023-01-06 12:45:34','2023-01-06 12:45:34','Data Migration',NULL,NULL),('e8bf9b229b2a4390866866068bbc94e3','MEPSUAT00016','2022-10-10',20221010,124.10000,0,1000000,1,1234567891234567.12,1,' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2023-01-06 12:45:35','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2023-01-06 12:45:35','2023-01-06 12:45:35','2023-01-06 12:45:35','Data Migration',NULL,NULL);
/*!40000 ALTER TABLE `allotment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-09  9:20:05
