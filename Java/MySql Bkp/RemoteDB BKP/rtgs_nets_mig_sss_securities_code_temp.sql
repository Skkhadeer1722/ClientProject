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
-- Table structure for table `sss_securities_code_temp`
--

DROP TABLE IF EXISTS `sss_securities_code_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sss_securities_code_temp` (
  `id` varchar(36) NOT NULL,
  `securities_code` varchar(12) NOT NULL COMMENT 'PRIMARY KEY - securities code',
  `issue_code` varchar(8) NOT NULL COMMENT 'Issue Code of the Securities',
  `description` varchar(45) NOT NULL COMMENT 'description of the securities code',
  `securities_type_id` varchar(10) NOT NULL COMMENT 'securities type from the securities_type table',
  `securities_status` varchar(9) NOT NULL COMMENT 'Status of the securities',
  `securities_tenor_period_unit` varchar(10) NOT NULL COMMENT 'tenor period - DAYS, MONTHS, YEARS',
  `credit_rating` varchar(5) DEFAULT NULL,
  `currency_code` varchar(3) NOT NULL COMMENT 'Currency code of the securities',
  `denomination` bigint NOT NULL COMMENT 'denomination value',
  `issuer_id` varchar(36) NOT NULL COMMENT 'Issuer of the securities',
  `ipa` varchar(36) NOT NULL COMMENT 'Member code with IPA=Yes',
  `facility_eligibility_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Facility Eligibility ID tied to security code.',
  `haircut_id` varchar(35) DEFAULT NULL COMMENT 'Hair cut id from Hair cut table',
  `tradable` tinyint NOT NULL COMMENT 'Indicator to know whether security itradable or not',
  `first_auction_date` varchar(10) DEFAULT NULL,
  `first_issuance_date` int NOT NULL COMMENT 'First issuance date of the security',
  `first_issuance_amount` bigint NOT NULL COMMENT 'First issuance amount of the security',
  `redemption_date` int DEFAULT NULL COMMENT 'Redemption date of the security',
  `optional_redemption_date` int DEFAULT NULL COMMENT 'Optional Redemption date of the securities',
  `redemption_price` decimal(9,5) NOT NULL COMMENT 'Redemption price of the securities',
  `optional_redemption_price` decimal(9,5) DEFAULT NULL COMMENT 'Optional redemption price',
  `central_bank_applied_amount` bigint NOT NULL COMMENT 'MAS applied amount',
  `coupon_structure` varchar(14) NOT NULL COMMENT 'Coupon Structure',
  `coupon_payment_frequency` varchar(18) NOT NULL COMMENT 'Coupon payment frequency',
  `coupon_rate` decimal(8,5) DEFAULT NULL COMMENT 'Coupon rate',
  `average_yield` decimal(9,5) DEFAULT NULL COMMENT 'average yield of the securities',
  `day_count_convention` varchar(20) NOT NULL COMMENT 'convention to be used fo the day count in calcualtions',
  `rounding_option` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Rounding Option to be used for that security code related calculations',
  `record_date_period` int NOT NULL COMMENT 'input the duration (in business days) prior to coupon payment, to be specified by the user in order to derive the Record Date',
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
  `tenor_period` int DEFAULT NULL COMMENT 'tenor period',
  `issue_price` decimal(8,5) DEFAULT NULL COMMENT 'The cut-off price is interfaced from external system (eApps).',
  `issue_yield` decimal(5,2) DEFAULT NULL COMMENT 'To display cut-off yield. The cut-off yield is interfaced from external system (eApps).',
  `redemption_reimburse` varchar(20) DEFAULT NULL COMMENT 'redemption or reimbursement type of the securities. Possible values are fixed maturity and perpertual',
  `tax_scheme_id` varchar(36) DEFAULT NULL COMMENT 'Tax scheme id - primary key of tax scheme table',
  `first_coupon_date` int DEFAULT NULL COMMENT 'First coupon date',
  `benchmark_indicator` varchar(36) NOT NULL COMMENT 'To allow the user to select the benchmark indicator.  It indicates whether the securities code is eligible to be benchmark',
  `remarks` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`securities_code`),
  KEY `fk_securities_code_currency_param_idx` (`currency_code`),
  KEY `fk_securities_code_facility_eligibility_idx` (`facility_eligibility_id`),
  KEY `fk_securities_code_modified_by_idx` (`modified_by`),
  KEY `fk_securities_code_approved_by_idx` (`approved_by`),
  KEY `fk_securities_code_securities_type_idx` (`securities_type_id`),
  KEY `fk_securities_code_issuer_idx` (`issuer_id`),
  KEY `fk_securities_code_member_idx` (`ipa`),
  KEY `fk_securities_code_haircut_idx` (`haircut_id`),
  KEY `fk_securities_code_tax_scheme_idx` (`tax_scheme_id`),
  CONSTRAINT `fk_securities_code_temp_currency_param` FOREIGN KEY (`currency_code`) REFERENCES `currency_param` (`currency_code`),
  CONSTRAINT `fk_securities_code_temp_facility_eligibility` FOREIGN KEY (`facility_eligibility_id`) REFERENCES `facility_eligibility` (`id`),
  CONSTRAINT `fk_securities_code_temp_haircut` FOREIGN KEY (`haircut_id`) REFERENCES `haircut` (`id`),
  CONSTRAINT `fk_securities_code_temp_issuer` FOREIGN KEY (`issuer_id`) REFERENCES `issuer` (`id`),
  CONSTRAINT `fk_securities_code_temp_member` FOREIGN KEY (`ipa`) REFERENCES `member` (`id`),
  CONSTRAINT `fk_securities_code_temp_securities_type` FOREIGN KEY (`securities_type_id`) REFERENCES `securities_type` (`id`),
  CONSTRAINT `fk_securities_code_temp_tax_scheme` FOREIGN KEY (`tax_scheme_id`) REFERENCES `tax_scheme` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='biz-param_screen_securities-code_This is the configuration table of securities';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss_securities_code_temp`
--

LOCK TABLES `sss_securities_code_temp` WRITE;
/*!40000 ALTER TABLE `sss_securities_code_temp` DISABLE KEYS */;
INSERT INTO `sss_securities_code_temp` VALUES ('4ec520ee-ab3c-4e7c-87e4-0379f7cecc8e','SGCAP-CSTS5','GXANN','PACS TESTING RECORD 3','SSB','ACTIVE','yearly',NULL,'SGD',1000,'537fe131-1c32-4628-90a8-d0968cd5527c','cc4812ad-5c87-4729-a093-858687bd1fa4','ALLFacSec',NULL,1,'20220616',20220916,100000000000,20240101,1,100.00000,1.50000,0,'Fixed Multiple','3',NULL,9.32000,'30/360','NEAREST',4,'NULL','ACTIVE','System','2022-08-26 04:54:42','System','2022-08-26 04:54:42','2022-08-26 04:54:42','2022-08-26 04:54:42','NULL',635,0,100.00000,0.00,'Fixed maturity','GSTOS',20230509,'NOT_APPLICABLE','Securities Code'),('1a766891-02b6-48b0-ad84-b60883331fd0','SGCAPACSTST1','M1KUO','PACS TESTING RECORD 1','MASRIMFRN','ACTIVE','monthly',NULL,'SGD',1000,'4ad71162-e15d-43be-b16f-064ac88fe7b3','cc4812ad-5c87-4729-a093-858687bd1fa4','ALLFacSec',NULL,1,'20220616',20210316,100000000000,20210630,1,100.00000,1.50000,0,'Floating rate','3',NULL,9.32000,'Actual/365','NEAREST',5,'NULL','ACTIVE','System','2022-08-26 04:54:36','System','2022-08-26 04:54:36','2022-08-26 04:54:36','2022-08-26 04:54:36','NULL',635,0,100.00000,0.00,'Fixed maturity','GSTOS',20200520,'NOT_APPLICABLE',NULL),('6ca9976c-bc4b-4d86-ba2a-97b7c65b47b4','SGCAPACSTST2','M1DCG','PACS TESTING RECORD 2','MASRIMFRN','ACTIVE','monthly',NULL,'SGD',1000,'4ad71162-e15d-43be-b16f-064ac88fe7b3','cc4812ad-5c87-4729-a093-858687bd1fa4','ALLFacSec',NULL,1,'20220616',20210618,100000000000,20220330,1,100.00000,1.50000,0,'Floating rate','3',NULL,9.32000,'Actual/Actual','NEAREST',1,'NULL','ACTIVE','System','2022-08-26 04:54:39','System','2022-08-26 04:54:39','2022-08-26 04:54:39','2022-08-26 04:54:39','NULL',635,0,100.00000,0.00,'Fixed maturity','GSTOS',20210316,'NOT_APPLICABLE',NULL),('06936542-c91b-4c02-8010-eb9eee8722fb','SGCAPACSTST3','NH-B2','PACS TESTING RECORD 3','SGSISBond','ACTIVE','yearly',NULL,'SGD',1000,'537fe131-1c32-4628-90a8-d0968cd5527c','cc4812ad-5c87-4729-a093-858687bd1fa4','ALLFacSec',NULL,1,'20220616',20220116,100000000000,20220730,1,100.00000,1.50000,0,'Fixed rate','3',2.50000,9.32000,'30/360','NEAREST',7,'NULL','ACTIVE','System','2022-08-26 04:54:40','System','2022-08-26 04:54:40','2022-08-26 04:54:40','2022-08-26 04:54:40','NULL',635,0,100.00000,0.00,'Fixed maturity','GSTOS',20211222,'NOT_APPLICABLE','Issue Code'),('0392709d-2451-41f9-a35c-83675f01aa12','SGCAPACSTST4','GX@NF','PACS TESTING RECORD 3','SSB','ACTIVE','yearly',NULL,'-GD',1000,'537fe131-1c32-4628-90a8-d0968cd5527c','cc4812ad-5c87-4729-a093-858687bd1fa4','ALLFacSec',NULL,1,'20220616',20220916,100000000000,20240101,1,100.00000,1.50000,0,'Fixed Multiple','3',NULL,9.32000,'30/360','NEAREST',4,'NULL','ACTIVE','System','2022-08-26 04:54:41','System','2022-08-26 04:54:41','2022-08-26 04:54:41','2022-08-26 04:54:41','NULL',635,0,100.00000,0.00,'Fixed maturity','GSTOS',20230509,'NOT_APPLICABLE','Issue Code,Currency Code');
/*!40000 ALTER TABLE `sss_securities_code_temp` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:32:53
