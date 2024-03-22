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
-- Table structure for table `securities_code_source`
--

DROP TABLE IF EXISTS `securities_code_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `securities_code_source` (
  `id` varchar(50) DEFAULT NULL,
  `securities_code` varchar(50) DEFAULT NULL COMMENT 'PRIMARY KEY - securities code',
  `issue_code` varchar(50) DEFAULT NULL COMMENT 'Issue Code of the Securities',
  `description` varchar(50) DEFAULT NULL COMMENT 'description of the securities code',
  `securities_type_id` varchar(50) DEFAULT NULL COMMENT 'securities type from the securities_type table',
  `securities_status` varchar(50) DEFAULT NULL COMMENT 'Status of the securities',
  `securities_tenor_period_unit` varchar(50) DEFAULT NULL COMMENT 'tenor period - DAYS, MONTHS, YEARS',
  `currency_code` varchar(50) DEFAULT NULL COMMENT 'Currency code of the securities',
  `denomination` varchar(50) DEFAULT NULL COMMENT 'denomination value',
  `issuer_id` varchar(50) DEFAULT NULL COMMENT 'Issuer of the securities',
  `ipa` varchar(50) DEFAULT NULL COMMENT 'Member code with IPA=Yes',
  `facility_eligibility_id` varchar(50) DEFAULT NULL COMMENT 'Facility Eligibility ID tied to security code.',
  `haircut_id` varchar(50) DEFAULT NULL COMMENT 'Hair cut id from Hair cut table',
  `tradable` varchar(50) DEFAULT NULL COMMENT 'Indicator to know whether security itradable or not',
  `first_auction_date` varchar(50) DEFAULT NULL COMMENT 'First Auctiondate',
  `first_issuance_date` varchar(50) DEFAULT NULL COMMENT 'First issuance date of the security',
  `first_issuance_amount` varchar(50) DEFAULT NULL COMMENT 'First issuance amount of the security',
  `redemption_date` varchar(50) DEFAULT NULL COMMENT 'Redemption date of the security',
  `optional_redemption_date` varchar(50) DEFAULT NULL COMMENT 'Optional Redemption date of the securities',
  `redemption_price` varchar(50) DEFAULT NULL COMMENT 'Redemption price of the securities',
  `optional_redemption_price` varchar(50) DEFAULT NULL COMMENT 'Optional redemption price',
  `central_bank_applied_amount` varchar(50) DEFAULT NULL COMMENT 'MAS applied amount',
  `coupon_structure` varchar(50) DEFAULT NULL COMMENT 'Coupon Structure',
  `coupon_payment_frequency` varchar(50) DEFAULT NULL COMMENT 'Coupon payment frequency',
  `coupon_rate` varchar(50) DEFAULT NULL COMMENT 'Coupon rate',
  `average_yield` varchar(50) DEFAULT NULL COMMENT 'average yield of the securities',
  `day_count_convention` varchar(50) DEFAULT NULL COMMENT 'convention to be used fo the day count in calcualtions',
  `rounding_option` varchar(50) DEFAULT NULL COMMENT 'Rounding Option to be used for that security code related calculations',
  `record_date_period` varchar(50) DEFAULT NULL COMMENT 'input the duration (in business days) prior to coupon payment, to be specified by the user in order to derive the Record Date',
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
  `tenor_period` varchar(50) DEFAULT NULL COMMENT 'tenor period',
  `issue_price` varchar(50) DEFAULT NULL COMMENT 'The cut-off price is interfaced from external system (eApps).',
  `issue_yield` varchar(50) DEFAULT NULL COMMENT 'To display cut-off yield. The cut-off yield is interfaced from external system (eApps).',
  `redemption_reimburse` varchar(50) DEFAULT NULL COMMENT 'redemption or reimbursement type of the securities. Possible values are fixed maturity and perpertual',
  `tax_scheme_id` varchar(50) DEFAULT NULL COMMENT 'Tax scheme id - primary key of tax scheme table',
  `benchmark_indicator` varchar(50) DEFAULT NULL COMMENT 'To allow the user to select the benchmark indicator.  It indicates whether the securities code is eligible to be benchmark',
  `first_coupon_date` varchar(50) DEFAULT NULL COMMENT 'First coupon date',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(1000) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='biz-param_screen_securities-code_This is the configuration table of securities';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securities_code_source`
--

LOCK TABLES `securities_code_source` WRITE;
/*!40000 ALTER TABLE `securities_code_source` DISABLE KEYS */;
INSERT INTO `securities_code_source` VALUES ('9d85a13f77e3401e9827214cb00f212e','AUTOSFNEWP99','AUTOSFNP','99YR BOND 2017 DUE 010199 AUTOSFNP',NULL,'A',NULL,'SGD','000000000001000',NULL,NULL,NULL,NULL,NULL,'20171228','20171229','000001000000000','20990101','','10000000','00000000','000902800000000',NULL,'S','00120000','00000000',NULL,NULL,'3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'050','00000000','00000000',NULL,NULL,NULL,'20221201',1,'Invalid Issue code, Invalid couponStructure','2022-12-23 11:16:41'),('4a0091053dc34ae9bce06d59418aaae6','BCSISMBIL001','BN2005M','MAS Bill',NULL,'A',NULL,'SGD','000000000001000',NULL,NULL,NULL,NULL,NULL,'20210518','20210519','000000100000000','20251231','','10000000','00000000','000000100000000',NULL,'N','00000000','00000000',NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'241','00000000','00000000',NULL,NULL,NULL,'',2,NULL,'2022-12-23 11:16:42'),('25abc32c8f8741bc91e36b4ef3125a7e','BCSISTEST012','N999012M','15 YEARS BONDS',NULL,'A',NULL,'SGD','000000000001000',NULL,NULL,NULL,NULL,NULL,'20170122','20170201','000000630000000','20290801','','10000000','00000000','000000630000000',NULL,'S','00125000','00000000',NULL,NULL,'5',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'015','00000000','00000000',NULL,NULL,NULL,'',3,NULL,'2022-12-23 11:16:42'),('d4d32c2ac26043fea3717f6b52bd9877','MEPSUAT00016','GX16501W','10YR SBOND 2016 DUE220426 GX16501W',NULL,'A',NULL,'SGD','000000000000500',NULL,NULL,NULL,NULL,NULL,'20160421','20160422','000000000287500','20260422','','10000000','00000000','000000000287500',NULL,'S','00700000','00000000',NULL,NULL,'3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'010','00000000','00000000',NULL,NULL,NULL,'20221022',4,NULL,'2022-12-23 11:16:43'),('aebcd395558b4c6bbfa9713b8541b255','MEPSUAT00024','GX16502N','10YR SBOND 2016 DUE250426 GX16502N',NULL,'A',NULL,'SGD','000000000000500',NULL,NULL,NULL,NULL,NULL,'20160422','20160425','000000000300000','20260425','','10000000','00000000','000000000300000',NULL,'S','00293000','00000000',NULL,NULL,'3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'010','00000000','00000000',NULL,NULL,NULL,'20221025',5,NULL,'2022-12-23 11:16:43'),('5989a0f40674455ca84f1e3622b33d7b','MEPSUAT00032','GX16503V','10YR SBOND 2016 DUE 030526 GX16503V',NULL,'A',NULL,'SGD','000000000000500',NULL,NULL,NULL,NULL,NULL,'20160429','20160503','000000000137500','20260503','','10000000','00000000','000000000137500',NULL,'S','00354000','00000000',NULL,NULL,'3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'010','00000000','00000000',NULL,NULL,NULL,'20221103',6,NULL,'2022-12-23 11:16:44'),('50c119175d8b4cc1923df74b4863d19f','NPTESTSCREEN','NY15046C','10YR BOND 2016 DUE 260531 NY15046C',NULL,'N',NULL,'SGD','000000000001000',NULL,NULL,NULL,NULL,NULL,'20160528','20160531','000000000001000','20260531','','10000000','00000000','000000000000000',NULL,'S','00000000','00000000',NULL,NULL,'3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'010','00000000','00000000',NULL,NULL,NULL,'',7,NULL,'2022-12-23 11:16:44'),('6f75a90c384d4d9fa7dac5d2087d233f','NSBONDMAY002','NS21002A','NS BOND 3YR 2021 - 2024 NS21002A',NULL,'A',NULL,'SGD','000000000001000',NULL,NULL,NULL,NULL,NULL,'20210505','20210506','000000000100000','20240531','','10000000','00000000','000000000100000',NULL,'S','00215000','00000000',NULL,NULL,'3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'005','00000000','00000000',NULL,NULL,NULL,'20221130',8,NULL,'2022-12-23 11:16:45');
/*!40000 ALTER TABLE `securities_code_source` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-09  9:19:58
