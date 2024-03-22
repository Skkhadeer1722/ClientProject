-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: project
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
-- Table structure for table `securities_type`
--

DROP TABLE IF EXISTS `securities_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `securities_type` (
  `id` varchar(10) NOT NULL COMMENT 'PRIMARY KEY - securities Type id',
  `description` varchar(35) NOT NULL COMMENT 'Securities Type Description',
  `securities_category` varchar(21) NOT NULL COMMENT 'Securities Category',
  `issuer` varchar(36) DEFAULT NULL COMMENT 'issuer id, issuer of the securities',
  `ipa` varchar(36) DEFAULT NULL COMMENT 'List of Members with IPA=Yes.',
  `securities_tenor_period_unit` varchar(6) DEFAULT NULL COMMENT 'Tenor unit',
  `tradable` tinyint DEFAULT NULL COMMENT 'select whether the securities is  tradable',
  `denomination` bigint DEFAULT NULL COMMENT 'the denomination of the securities',
  `currency_code` varchar(3) DEFAULT NULL COMMENT 'the denominating currency code for the securitiestype',
  `coupon_structure` varchar(14) DEFAULT NULL COMMENT 'Type of coupon for the securities type',
  `coupon_payment_frequency` varchar(18) DEFAULT NULL COMMENT 'select the frequency at which coupon payment will be performed.',
  `day_count_convention` varchar(16) DEFAULT NULL COMMENT 'number of day per year. It is an indication for the computation of the coupon/interest payments.',
  `rounding_option` varchar(10) DEFAULT NULL COMMENT 'for setting the calculated values to either Round-up (if equal to and greater than 5), Round-down (if smaller than 5) or to the Nearest. It is use for the computation of the coupon/interest payments.',
  `record_date_period` int DEFAULT NULL COMMENT 'input the duration (in business days) prior to coupon payment, to be specified by the user in order to derive the Record Date',
  `facility_eligibility_id` varchar(10) DEFAULT NULL COMMENT 'Facility Eligibility ID - List of Facility Eligibility IDs set up in SSS',
  `haircut_id` varchar(35) DEFAULT NULL COMMENT 'Haircut ID - List of Haircut IDs set up in SSS',
  `tax_scheme_id` varchar(36) DEFAULT NULL COMMENT 'Tax scheme id',
  `voluntary_redemption_frequency` varchar(7) DEFAULT NULL COMMENT 'voluntary redemption frequency for Singapore Savings Bond redemption',
  `redemption_end_date_offset` int DEFAULT NULL COMMENT 'The redemption end date offset for corporate action early redemption event',
  `automatic_event_generation` tinyint NOT NULL COMMENT 'To allow the user to indicate the event will be generated automatically by system on issuance date or coupon payment date.',
  `reference_rate` varchar(10) DEFAULT NULL COMMENT 'Reference Rate',
  `frn_calculation_method` varchar(23) DEFAULT NULL COMMENT 'FRN Calculation method',
  `allow_negative_rate` tinyint NOT NULL COMMENT 'To allow the user to indicate whether negative rate is allowed',
  `coupon_rounding_precision` int DEFAULT NULL COMMENT 'Rounding precision for the user input',
  `backward_shifted_observation_period` int DEFAULT NULL COMMENT 'It’s the  backward shifted observation period in days',
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
  `erf_eligible` tinyint NOT NULL COMMENT 'To allow user to indicate if the security type is eligible for Enhanced Repo Facility.',
  `redemption_reimbursement` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'redemption or reimbursement type of the securities. Possible values are fixed maturity and perpertual',
  `benchmark_indicator` varchar(14) NOT NULL COMMENT 'To allow the user to select the benchmark indicator.  It indicates whether the securities code is eligible to be benchmark',
  `primary_registry_id` varchar(36) NOT NULL,
  `settlement_cycle` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_securities_type_currency_param` (`currency_code`),
  KEY `fk_securities_type_issuer` (`issuer`),
  KEY `fk_securities_type_member` (`ipa`),
  KEY `fk_securities_type_facility_eligibility` (`facility_eligibility_id`),
  KEY `fk_securities_type_haircut` (`haircut_id`),
  KEY `fk_securities_type_scheme` (`tax_scheme_id`),
  KEY `fk_securities_type_modified_by_idx` (`modified_by`),
  KEY `fk_securities_type_approved_by_idx` (`approved_by`),
  CONSTRAINT `fk_securities_type_currency_param` FOREIGN KEY (`currency_code`) REFERENCES `currency_param` (`currency_code`),
  CONSTRAINT `fk_securities_type_facility_eligibility` FOREIGN KEY (`facility_eligibility_id`) REFERENCES `facility_eligibility` (`id`),
  CONSTRAINT `fk_securities_type_haircut` FOREIGN KEY (`haircut_id`) REFERENCES `haircut` (`id`),
  CONSTRAINT `fk_securities_type_issuer` FOREIGN KEY (`issuer`) REFERENCES `issuer` (`id`),
  CONSTRAINT `fk_securities_type_member` FOREIGN KEY (`ipa`) REFERENCES `member` (`id`),
  CONSTRAINT `fk_securities_type_scheme` FOREIGN KEY (`tax_scheme_id`) REFERENCES `tax_scheme` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='biz-param_screen_securities-type_This table is used to configure the securities type needed for securities code';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securities_type`
--

LOCK TABLES `securities_type` WRITE;
/*!40000 ALTER TABLE `securities_type` DISABLE KEYS */;
INSERT INTO `securities_type` VALUES ('MASBill','MAS Bill','MAS_BILL','537fe131-1c32-4628-90a8-d0968cd5527c','25ec94f7-7c66-458c-acde-6d8748729412','DAYS',1,1000,'SGD','ZERO_COUPON',NULL,'ACTUAL','NEAREST',0,'ALLFacSec',NULL,'f9f56571-5e96-4295-88ec-0a5e57a971c1',NULL,0,1,NULL,NULL,0,0,0,NULL,'ACTIVE','957c24b4-f5fe-4bcf-9342-72f1fdff4b63','2022-05-05 05:41:05','9c4424d2-422b-4a01-ba41-a6ffd9e083e9','2022-05-05 05:41:05','2022-05-04 18:30:00','2022-04-26 09:44:23','',2147,1,'FIXED_MATURITY','NOT_APPLICABLE','32bae33a-bcff-47eb-89ee-cf50e50ce8c5',0),('ST01','Sec Type 01','SGS_BOND','8165bb25-7b3f-48dd-81b9-0c56ddc2e395','25ec94f7-7c66-458c-acde-6d8748729412','DAYS',1,1000,'SGD','ZERO_COUPON',NULL,'ACTUAL','NEAREST',0,'FE1','HC1','d4b051a5-1000-438a-8f39-c51c5857d084','Monthly',20,1,NULL,NULL,0,0,0,NULL,'ACTIVE','47122ef4-0b2d-4306-82cc-d4296c01a8c3','2022-04-18 11:37:41','operatoruser1','2022-04-18 11:37:41','2022-04-13 18:30:00','2022-04-18 11:25:54','',1913,0,'PERPETUAL','YES','32bae33a-bcff-47eb-89ee-cf50e50ce8c5',0),('TBill','Treasury Bill','SGS_T_BILL','537fe131-1c32-4628-90a8-d0968cd5527c','25ec94f7-7c66-458c-acde-6d8748729412','DAYS',1,1000,'SGD','ZERO_COUPON',NULL,'ACTUAL','NEAREST',0,NULL,NULL,'f9f56571-5e96-4295-88ec-0a5e57a971c1',NULL,0,1,NULL,NULL,0,10,0,NULL,'ACTIVE','operatoruser2','2022-04-26 09:28:51','operatoruser1','2022-04-26 09:28:51','2022-04-26 18:30:00','2022-04-26 06:17:53','',2001,0,'FIXED_MATURITY','YES','32bae33a-bcff-47eb-89ee-cf50e50ce8c5',0);
/*!40000 ALTER TABLE `securities_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-09  9:19:27
