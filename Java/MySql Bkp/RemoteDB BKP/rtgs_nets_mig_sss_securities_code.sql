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
-- Table structure for table `sss_securities_code`
--

DROP TABLE IF EXISTS `sss_securities_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sss_securities_code` (
  `id` varchar(255) NOT NULL,
  `action` varchar(255) DEFAULT NULL,
  `approval_remark` varchar(255) DEFAULT NULL,
  `approved_by` varchar(255) DEFAULT NULL,
  `approved_date` datetime(6) DEFAULT NULL,
  `average_yield` varchar(255) DEFAULT NULL,
  `benchmark_indicator` varchar(255) DEFAULT NULL,
  `central_bank_applied_amount` bigint DEFAULT NULL,
  `coupon_payment_frequency` varchar(255) DEFAULT NULL,
  `coupon_rate` double DEFAULT NULL,
  `coupon_structure` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `credit_rating` varchar(255) DEFAULT NULL,
  `currency_code` varchar(255) DEFAULT NULL,
  `day_count_convention` varchar(255) DEFAULT NULL,
  `denomination` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `effective_date` datetime(6) DEFAULT NULL,
  `facility_eligibility_id` varchar(255) DEFAULT NULL,
  `first_auction_date` varchar(255) DEFAULT NULL,
  `first_coupon_date` varchar(255) DEFAULT NULL,
  `first_issuance_amount` bigint DEFAULT NULL,
  `first_issuance_date` int DEFAULT NULL,
  `haircut_id` varchar(255) DEFAULT NULL,
  `ipa` varchar(255) DEFAULT NULL,
  `issue_code` varchar(255) DEFAULT NULL,
  `issue_price` double DEFAULT NULL,
  `issue_yield` double DEFAULT NULL,
  `issuer_id` varchar(255) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `optional_redemption_date` varchar(255) DEFAULT NULL,
  `optional_redemption_price` varchar(255) DEFAULT NULL,
  `record_date_period` int DEFAULT NULL,
  `redemption_date` varchar(255) DEFAULT NULL,
  `redemption_price` varchar(255) DEFAULT NULL,
  `redemption_reimburse` varchar(255) DEFAULT NULL,
  `rounding_option` varchar(255) DEFAULT NULL,
  `securities_code` varchar(255) DEFAULT NULL,
  `securities_status` varchar(255) DEFAULT NULL,
  `securities_tenor_period_unit` varchar(255) DEFAULT NULL,
  `securities_type_id` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `tax_scheme_id` varchar(255) DEFAULT NULL,
  `tenor_period` int DEFAULT NULL,
  `tradable` varchar(255) DEFAULT NULL,
  `workflow_status_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss_securities_code`
--

LOCK TABLES `sss_securities_code` WRITE;
/*!40000 ALTER TABLE `sss_securities_code` DISABLE KEYS */;
INSERT INTO `sss_securities_code` VALUES ('1a766891-02b6-48b0-ad84-b60883331fd0','NULL','NULL','System','2022-08-26 10:24:35.833000','9.32','NOT_APPLICABLE',0,'3',NULL,'Floating rate','2022-08-26 10:24:35.833000',NULL,'SGD','Actual/365','1000','PACS TESTING RECORD 1','2022-08-26 10:24:35.833000','ALLFacSec','20220616','20200520',100000000000,20210316,NULL,'cc4812ad-5c87-4729-a093-858687bd1fa4','M1KUO',100,0,'4ad71162-e15d-43be-b16f-064ac88fe7b3','System','2022-08-26 10:24:35.833000','1','1.5',5,'20210630','100.00000','Fixed maturity','NEAREST','SGCAPACSTST1','ACTIVE','monthly','MASRIMFRN','ACTIVE','GSTOS',0,'1',635),('6ca9976c-bc4b-4d86-ba2a-97b7c65b47b4','NULL','NULL','System','2022-08-26 10:24:38.973000','9.32','NOT_APPLICABLE',0,'3',NULL,'Floating rate','2022-08-26 10:24:38.973000',NULL,'SGD','Actual/Actual','1000','PACS TESTING RECORD 2','2022-08-26 10:24:38.973000','ALLFacSec','20220616','20210316',100000000000,20210618,NULL,'cc4812ad-5c87-4729-a093-858687bd1fa4','M1DCG',100,0,'4ad71162-e15d-43be-b16f-064ac88fe7b3','System','2022-08-26 10:24:38.973000','1','1.5',1,'20220330','100.00000','Fixed maturity','NEAREST','SGCAPACSTST2','ACTIVE','monthly','MASRIMFRN','ACTIVE','GSTOS',0,'1',635);
/*!40000 ALTER TABLE `sss_securities_code` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:31:57
