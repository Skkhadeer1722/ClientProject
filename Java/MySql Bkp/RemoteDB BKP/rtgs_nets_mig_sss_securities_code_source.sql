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
-- Table structure for table `sss_securities_code_source`
--

DROP TABLE IF EXISTS `sss_securities_code_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sss_securities_code_source` (
  `id` varchar(36) DEFAULT NULL,
  `securities_code` varchar(12) DEFAULT NULL COMMENT 'PRIMARY KEY - securities code',
  `issue_code` varchar(8) DEFAULT NULL COMMENT 'Issue Code of the Securities',
  `description` varchar(45) DEFAULT NULL COMMENT 'description of the securities code',
  `securities_type_id` varchar(10) DEFAULT NULL COMMENT 'securities type from the securities_type table',
  `securities_status` varchar(9) DEFAULT NULL COMMENT 'Status of the securities',
  `securities_tenor_period_unit` varchar(10) DEFAULT NULL COMMENT 'tenor period - DAYS, MONTHS, YEARS',
  `credit_rating` varchar(5) DEFAULT NULL,
  `currency_code` varchar(3) DEFAULT NULL COMMENT 'Currency code of the securities',
  `denomination` bigint DEFAULT NULL COMMENT 'denomination value',
  `issuer_id` varchar(36) DEFAULT NULL COMMENT 'Issuer of the securities',
  `ipa` varchar(36) DEFAULT NULL COMMENT 'Member code with IPA=Yes',
  `facility_eligibility_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Facility Eligibility ID tied to security code.',
  `haircut_id` varchar(35) DEFAULT NULL COMMENT 'Hair cut id from Hair cut table',
  `tradable` tinyint DEFAULT NULL COMMENT 'Indicator to know whether security itradable or not',
  `first_auction_date` date DEFAULT NULL COMMENT 'First Auctiondate',
  `first_issuance_date` int DEFAULT NULL COMMENT 'First issuance date of the security',
  `first_issuance_amount` bigint DEFAULT NULL COMMENT 'First issuance amount of the security',
  `redemption_date` int DEFAULT NULL COMMENT 'Redemption date of the security',
  `optional_redemption_date` int DEFAULT NULL COMMENT 'Optional Redemption date of the securities',
  `redemption_price` decimal(9,5) DEFAULT NULL COMMENT 'Redemption price of the securities',
  `optional_redemption_price` decimal(9,5) DEFAULT NULL COMMENT 'Optional redemption price',
  `central_bank_applied_amount` bigint DEFAULT NULL COMMENT 'MAS applied amount',
  `coupon_structure` varchar(14) DEFAULT NULL COMMENT 'Coupon Structure',
  `coupon_payment_frequency` varchar(18) DEFAULT NULL COMMENT 'Coupon payment frequency',
  `coupon_rate` decimal(8,5) DEFAULT NULL COMMENT 'Coupon rate',
  `average_yield` decimal(9,5) DEFAULT NULL COMMENT 'average yield of the securities',
  `day_count_convention` varchar(20) DEFAULT NULL COMMENT 'convention to be used fo the day count in calcualtions',
  `rounding_option` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Rounding Option to be used for that security code related calculations',
  `record_date_period` int DEFAULT NULL COMMENT 'input the duration (in business days) prior to coupon payment, to be specified by the user in order to derive the Record Date',
  `action` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(30) DEFAULT NULL COMMENT 'Record status',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Last modified User ID',
  `modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last modified timestamp',
  `approved_by` varchar(36) DEFAULT NULL COMMENT 'Last approved user ID',
  `approved_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last approved timestamp',
  `created_date` timestamp NULL DEFAULT NULL COMMENT 'Created date of the record',
  `effective_date` timestamp NULL DEFAULT NULL COMMENT 'Activation date of the record',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` int DEFAULT NULL COMMENT 'Approval flow ID',
  `tenor_period` int DEFAULT NULL COMMENT 'tenor period',
  `issue_price` decimal(8,5) DEFAULT NULL COMMENT 'The cut-off price is interfaced from external system (eApps).',
  `issue_yield` decimal(5,2) DEFAULT NULL COMMENT 'To display cut-off yield. The cut-off yield is interfaced from external system (eApps).',
  `redemption_reimburse` varchar(20) DEFAULT NULL COMMENT 'redemption or reimbursement type of the securities. Possible values are fixed maturity and perpertual',
  `tax_scheme_id` varchar(36) DEFAULT NULL COMMENT 'Tax scheme id - primary key of tax scheme table',
  `first_coupon_date` int DEFAULT NULL COMMENT 'First coupon date',
  `benchmark_indicator` varchar(36) DEFAULT NULL COMMENT 'To allow the user to select the benchmark indicator.  It indicates whether the securities code is eligible to be benchmark'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='biz-param_screen_securities-code_This is the configuration table of securities';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss_securities_code_source`
--

LOCK TABLES `sss_securities_code_source` WRITE;
/*!40000 ALTER TABLE `sss_securities_code_source` DISABLE KEYS */;
INSERT INTO `sss_securities_code_source` VALUES ('c1fe8252-5d65-47cb-a5ad-117f9f49a28e','SGCAPACSTST1','M1KUO','PACS TESTING RECORD 1',NULL,'ACTIVE',NULL,NULL,'SGD',1000,NULL,NULL,NULL,NULL,NULL,'2022-06-16',20210316,100000000000,20210630,1,100.00000,1.50000,0,NULL,'3',NULL,9.32000,NULL,'NEAREST',4,'NULL','ACTIVE',NULL,'2022-08-22 08:35:48',NULL,'2022-08-22 08:35:48','2022-08-22 08:35:48','2022-08-22 08:35:48','NULL',635,0,100.00000,0.00,'Fixed maturity','1234',20200520,NULL),('375fbeda-bf21-4214-95b5-8781295ececb','SGCAPACSTST2','M1DCG','PACS TESTING RECORD 2',NULL,'ACTIVE',NULL,NULL,'SGD',1000,NULL,NULL,NULL,NULL,NULL,'2022-06-16',20210618,100000000000,20220330,1,100.00000,1.50000,0,NULL,'3',NULL,9.32000,NULL,'NEAREST',0,'NULL','ACTIVE',NULL,'2022-08-22 08:35:49',NULL,'2022-08-22 08:35:49','2022-08-22 08:35:49','2022-08-22 08:35:49','NULL',635,0,100.00000,0.00,'Fixed maturity','1234',20210316,NULL),('422542ec-026d-4d4a-a545-94bc577a4f2e','SGCAPACSTST3','NH-B2','PACS TESTING RECORD 3',NULL,'ACTIVE',NULL,NULL,'SGD',1000,NULL,NULL,NULL,NULL,NULL,'2022-06-16',20220116,100000000000,20220730,1,100.00000,1.50000,0,NULL,'3',NULL,9.32000,NULL,'NEAREST',6,'NULL','ACTIVE',NULL,'2022-08-22 08:35:50',NULL,'2022-08-22 08:35:50','2022-08-22 08:35:50','2022-08-22 08:35:50','NULL',635,0,100.00000,0.00,'Fixed maturity','1234',20211222,NULL),('932979b1-6c29-4f2b-bf56-ab2524a9e122','SGCAPACSTST4','GXANF','PACS TESTING RECORD 3',NULL,'ACTIVE',NULL,NULL,'-GD',1000,NULL,NULL,NULL,NULL,NULL,'2022-06-16',20220916,100000000000,20240101,1,100.00000,1.50000,0,NULL,'3',NULL,9.32000,NULL,'NEAREST',3,'NULL','ACTIVE',NULL,'2022-08-22 08:35:51',NULL,'2022-08-22 08:35:51','2022-08-22 08:35:51','2022-08-22 08:35:51','NULL',635,0,100.00000,0.00,'Fixed maturity','1234',20230509,NULL),('35f29f3b-16f4-4d92-98bb-cfdb97ca47e6','SGCAP-ACSTS5','GX@NN','PACS TESTING RECORD 3',NULL,'ACTIVE',NULL,NULL,'SGD',1000,NULL,NULL,NULL,NULL,NULL,'2022-06-16',20220916,100000000000,20240101,1,100.00000,1.50000,0,NULL,'3',NULL,9.32000,NULL,'NEAREST',3,'NULL','ACTIVE',NULL,'2022-08-22 08:35:51',NULL,'2022-08-22 08:35:51','2022-08-22 08:35:51','2022-08-22 08:35:51','NULL',635,0,100.00000,0.00,'Fixed maturity','1234',20230509,NULL),('1a766891-02b6-48b0-ad84-b60883331fd0','SGCAPACSTST1','M1KUO','PACS TESTING RECORD 1',NULL,'ACTIVE',NULL,NULL,'SGD',1000,NULL,NULL,NULL,NULL,NULL,'2022-06-16',20210316,100000000000,20210630,1,100.00000,1.50000,0,NULL,'3',NULL,9.32000,NULL,'NEAREST',4,'NULL','ACTIVE',NULL,'2022-08-26 04:54:36',NULL,'2022-08-26 04:54:36','2022-08-26 04:54:36','2022-08-26 04:54:36','NULL',635,0,100.00000,0.00,'Fixed maturity','1234',20200520,NULL),('6ca9976c-bc4b-4d86-ba2a-97b7c65b47b4','SGCAPACSTST2','M1DCG','PACS TESTING RECORD 2',NULL,'ACTIVE',NULL,NULL,'SGD',1000,NULL,NULL,NULL,NULL,NULL,'2022-06-16',20210618,100000000000,20220330,1,100.00000,1.50000,0,NULL,'3',NULL,9.32000,NULL,'NEAREST',0,'NULL','ACTIVE',NULL,'2022-08-26 04:54:39',NULL,'2022-08-26 04:54:39','2022-08-26 04:54:39','2022-08-26 04:54:39','NULL',635,0,100.00000,0.00,'Fixed maturity','1234',20210316,NULL),('06936542-c91b-4c02-8010-eb9eee8722fb','SGCAPACSTST3','NH-B2','PACS TESTING RECORD 3',NULL,'ACTIVE',NULL,NULL,'SGD',1000,NULL,NULL,NULL,NULL,NULL,'2022-06-16',20220116,100000000000,20220730,1,100.00000,1.50000,0,NULL,'3',NULL,9.32000,NULL,'NEAREST',6,'NULL','ACTIVE',NULL,'2022-08-26 04:54:40',NULL,'2022-08-26 04:54:40','2022-08-26 04:54:40','2022-08-26 04:54:40','NULL',635,0,100.00000,0.00,'Fixed maturity','1234',20211222,NULL),('0392709d-2451-41f9-a35c-83675f01aa12','SGCAPACSTST4','GX@NF','PACS TESTING RECORD 3',NULL,'ACTIVE',NULL,NULL,'-GD',1000,NULL,NULL,NULL,NULL,NULL,'2022-06-16',20220916,100000000000,20240101,1,100.00000,1.50000,0,NULL,'3',NULL,9.32000,NULL,'NEAREST',3,'NULL','ACTIVE',NULL,'2022-08-26 04:54:41',NULL,'2022-08-26 04:54:41','2022-08-26 04:54:41','2022-08-26 04:54:41','NULL',635,0,100.00000,0.00,'Fixed maturity','1234',20230509,NULL),('4ec520ee-ab3c-4e7c-87e4-0379f7cecc8e','SGCAP-CSTS5','GXANN','PACS TESTING RECORD 3',NULL,'ACTIVE',NULL,NULL,'SGD',1000,NULL,NULL,NULL,NULL,NULL,'2022-06-16',20220916,100000000000,20240101,1,100.00000,1.50000,0,NULL,'3',NULL,9.32000,NULL,'NEAREST',3,'NULL','ACTIVE',NULL,'2022-08-26 04:54:42',NULL,'2022-08-26 04:54:42','2022-08-26 04:54:42','2022-08-26 04:54:42','NULL',635,0,100.00000,0.00,'Fixed maturity','1234',20230509,NULL);
/*!40000 ALTER TABLE `sss_securities_code_source` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:32:48
