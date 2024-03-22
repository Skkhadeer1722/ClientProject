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
-- Table structure for table `securities_code_temp`
--

DROP TABLE IF EXISTS `securities_code_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `securities_code_temp` (
  `id` varchar(36) NOT NULL,
  `securities_code` varchar(12) NOT NULL COMMENT 'PRIMARY KEY - securities code',
  `issue_code` varchar(8) NOT NULL COMMENT 'Issue Code of the Securities',
  `description` varchar(45) NOT NULL COMMENT 'description of the securities code',
  `securities_type_id` varchar(10) NOT NULL COMMENT 'securities type from the securities_type table',
  `securities_status` varchar(9) NOT NULL COMMENT 'Status of the securities',
  `securities_tenor_period_unit` varchar(10) NOT NULL COMMENT 'tenor period - DAYS, MONTHS, YEARS',
  `currency_code` varchar(3) NOT NULL COMMENT 'Currency code of the securities',
  `denomination` bigint NOT NULL COMMENT 'denomination value',
  `issuer_id` varchar(36) NOT NULL COMMENT 'Issuer of the securities',
  `ipa` varchar(36) NOT NULL COMMENT 'Member code with IPA=Yes',
  `facility_eligibility_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Facility Eligibility ID tied to security code.',
  `haircut_id` varchar(35) DEFAULT NULL COMMENT 'Hair cut id from Hair cut table',
  `tradable` tinyint NOT NULL COMMENT 'Indicator to know whether security itradable or not',
  `first_auction_date` date DEFAULT NULL COMMENT 'First Auctiondate',
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
  `benchmark_indicator` varchar(36) NOT NULL COMMENT 'To allow the user to select the benchmark indicator.  It indicates whether the securities code is eligible to be benchmark',
  `first_coupon_date` int DEFAULT NULL COMMENT 'First coupon date',
  `remarks` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='biz-param_screen_securities-code_This is the configuration table of securities';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securities_code_temp`
--

LOCK TABLES `securities_code_temp` WRITE;
/*!40000 ALTER TABLE `securities_code_temp` DISABLE KEYS */;
INSERT INTO `securities_code_temp` VALUES ('25abc32c8f8741bc91e36b4ef3125a7e','BCSISTEST012','N999012M','15 YEARS BONDS','SGSISGBond','A','yearly','SGD',1000,'537fe131-1c32-4628-90a8-d0968cd552dr','cc4812ad-5c87-4729-a093-858687bd1fa4',NULL,NULL,1,'2017-01-22',20170201,630000000,20290801,NULL,1000.00000,0.00000,630000000,'Fixed rate','S',1.25000,0.00000,'30/360','Nearest',6,' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-23 05:46:43','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-23 05:46:43','2022-12-23 05:46:43','2022-12-23 05:46:43','Data Migration',NULL,15,0.00000,0.00,'Fixed maturity','f9f56571-5e96-4295-88ec-0a5e57a971c1','NOT_APPLICABLE',NULL,NULL),('4a0091053dc34ae9bce06d59418aaae6','BCSISMBIL001','BN2005M','MAS Bill','TBill','A','daily','SGD',1000,'537fe131-1c32-4628-90a8-d0968cd552dr','cc4812ad-5c87-4729-a093-858687bd1fa4',NULL,NULL,1,'2021-05-18',20210519,100000000,20251231,NULL,1000.00000,0.00000,100000000,'Zero coupon','N',0.00000,0.00000,' ','Nearest',1,' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-23 05:46:42','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-23 05:46:42','2022-12-23 05:46:42','2022-12-23 05:46:42','Data Migration',NULL,241,0.00000,0.00,'Fixed maturity','f9f56571-5e96-4295-88ec-0a5e57a971c1','NOT_APPLICABLE',NULL,NULL),('50c119175d8b4cc1923df74b4863d19f','NPTESTSCREEN','NY15046C','10YR BOND 2016 DUE 260531 NY15046C','SGSISGBond','N','yearly','SGD',1000,'537fe131-1c32-4628-90a8-d0968cd552dr','cc4812ad-5c87-4729-a093-858687bd1fa4',NULL,NULL,1,'2016-05-28',20160531,1000,20260531,NULL,1000.00000,0.00000,0,'Fixed rate','S',0.00000,0.00000,'30/360','Nearest',4,' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-23 05:46:45','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-23 05:46:45','2022-12-23 05:46:45','2022-12-23 05:46:45','Data Migration',NULL,10,0.00000,0.00,'Fixed maturity','f9f56571-5e96-4295-88ec-0a5e57a971c1','NOT_APPLICABLE',NULL,NULL),('5989a0f40674455ca84f1e3622b33d7b','MEPSUAT00032','GX16503V','10YR SBOND 2016 DUE 030526 GX16503V','SSB','A','yearly','SGD',500,'537fe131-1c32-4628-90a8-d0968cd552dr','cc4812ad-5c87-4729-a093-858687bd1fa4',NULL,NULL,1,'2016-04-29',20160503,137500,20260503,NULL,1000.00000,0.00000,137500,'Fixed Multiple','S',NULL,0.00000,'30/360','Nearest',4,' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-23 05:46:44','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-23 05:46:44','2022-12-23 05:46:44','2022-12-23 05:46:44','Data Migration',NULL,10,0.00000,0.00,'Fixed maturity','f9f56571-5e96-4295-88ec-0a5e57a971c1','NOT_APPLICABLE',20221103,NULL),('6f75a90c384d4d9fa7dac5d2087d233f','NSBONDMAY002','NS21002A','NS BOND 3YR 2021 - 2024 NS21002A','SGSISGBond','A','yearly','SGD',1000,'537fe131-1c32-4628-90a8-d0968cd552dr','cc4812ad-5c87-4729-a093-858687bd1fa4',NULL,NULL,1,'2021-05-05',20210506,100000,20240531,NULL,1000.00000,0.00000,100000,'Fixed rate','S',2.15000,0.00000,'30/360','Nearest',4,' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-23 05:46:45','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-23 05:46:45','2022-12-23 05:46:45','2022-12-23 05:46:45','Data Migration',NULL,5,0.00000,0.00,'Fixed maturity','f9f56571-5e96-4295-88ec-0a5e57a971c1','NOT_APPLICABLE',20221130,NULL),('aebcd395558b4c6bbfa9713b8541b255','MEPSUAT00024','GX16502N','10YR SBOND 2016 DUE250426 GX16502N','SSB','A','yearly','SGD',500,'537fe131-1c32-4628-90a8-d0968cd552dr','cc4812ad-5c87-4729-a093-858687bd1fa4',NULL,NULL,1,'2016-04-22',20160425,300000,20260425,NULL,1000.00000,0.00000,300000,'Fixed Multiple','S',NULL,0.00000,'30/360','Nearest',4,' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-23 05:46:44','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-23 05:46:44','2022-12-23 05:46:44','2022-12-23 05:46:44','Data Migration',NULL,10,0.00000,0.00,'Fixed maturity','f9f56571-5e96-4295-88ec-0a5e57a971c1','NOT_APPLICABLE',20221025,NULL),('d4d32c2ac26043fea3717f6b52bd9877','MEPSUAT00016','GX16501W','10YR SBOND 2016 DUE220426 GX16501W','SSB','A','yearly','SGD',500,'537fe131-1c32-4628-90a8-d0968cd552dr','cc4812ad-5c87-4729-a093-858687bd1fa4',NULL,NULL,1,'2016-04-21',20160422,287500,20260422,NULL,1000.00000,0.00000,287500,'Fixed Multiple','S',NULL,0.00000,'30/360','Nearest',4,' ','ACTIVE','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-23 05:46:43','c93c6b81-0ef2-47e8-baef-714d14cd4b3c','2022-12-23 05:46:43','2022-12-23 05:46:43','2022-12-23 05:46:43','Data Migration',NULL,10,0.00000,0.00,'Fixed maturity','f9f56571-5e96-4295-88ec-0a5e57a971c1','NOT_APPLICABLE',20221022,NULL);
/*!40000 ALTER TABLE `securities_code_temp` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-09  9:20:06
