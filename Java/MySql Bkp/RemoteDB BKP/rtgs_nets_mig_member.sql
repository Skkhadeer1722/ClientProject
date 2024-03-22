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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'PRIMARY KEY - Id',
  `member_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SENSITIVE,PRIMARY KEY - member code',
  `swift_bic` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SENSITIVE, member’s SWIFT BIC',
  `swift_member` tinyint(1) NOT NULL COMMENT 'swift member',
  `bank_code` smallint NOT NULL COMMENT 'SENSITIVE, unique bank code',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'member name',
  `short_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'member’s short name',
  `member_status` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'member’s status',
  `member_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'member type',
  `sector_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'sector ID',
  `mcb_id` varchar(10) NOT NULL,
  `uen` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'SENSITIVE, UEN number',
  `lei` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'LEI number',
  `member_classification` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'member’s classification',
  `exempted_from_billing` tinyint(1) DEFAULT NULL COMMENT 'the indication whether the member is exempted from the billing',
  `exempted_from_system_limit` tinyint(1) DEFAULT NULL COMMENT 'the indication whether the member is exempted from system limit',
  `location` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'location',
  `activation_date` timestamp NULL DEFAULT NULL COMMENT 'activation date of the member',
  `action` varchar(6) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(30) DEFAULT NULL COMMENT 'Record status',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Last modified User ID',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last modified timestamp',
  `approved_by` varchar(36) DEFAULT NULL COMMENT 'Last approved user ID',
  `approved_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last approved timestamp',
  `created_date` timestamp NOT NULL COMMENT 'Created date of the record',
  `effective_date` timestamp NULL DEFAULT NULL COMMENT 'Activation date of the record',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` int DEFAULT NULL COMMENT 'Approval flow ID',
  `fi_group` varchar(1) NOT NULL,
  `billing_profile_id` varchar(36) DEFAULT NULL,
  `mcb_intraday` decimal(8,5) NOT NULL,
  `mcb_eod_minimum` decimal(8,5) NOT NULL,
  `mcb_eod_maximum` decimal(8,5) NOT NULL,
  `mcb_average` decimal(8,5) NOT NULL,
  `tax_profile_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Its tax profile for billing',
  `verified_by` varchar(36) DEFAULT NULL,
  `verified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `average_mcb_requirement` varchar(255) DEFAULT NULL,
  `currency_code` varchar(255) DEFAULT NULL,
  `currency_code_subscription` varchar(255) DEFAULT NULL,
  `currency_member_code` varchar(255) DEFAULT NULL,
  `currency_settlement_agent` varchar(255) DEFAULT NULL,
  `fee_profile_id` varchar(255) DEFAULT NULL,
  `fi_code` varchar(255) DEFAULT NULL,
  `intrday_mcb_requirement` varchar(255) DEFAULT NULL,
  `location_code` varchar(255) DEFAULT NULL,
  `maximum_mcb_requirement` varchar(255) DEFAULT NULL,
  `member_short_name` varchar(255) DEFAULT NULL,
  `member_name` varchar(255) DEFAULT NULL,
  `minimum_mcb_requirement` varchar(255) DEFAULT NULL,
  `tax_rate` varchar(255) DEFAULT NULL,
  `account_operator` varchar(255) DEFAULT NULL,
  `auto_debit_indicator` smallint DEFAULT NULL,
  `end_of_day_statement` varchar(255) DEFAULT NULL,
  `facility_eligibility_id` varchar(255) DEFAULT NULL,
  `fall_back_account` varchar(255) DEFAULT NULL,
  `issuing_paying_agent` varchar(255) DEFAULT NULL,
  `primary_dealer` varchar(255) DEFAULT NULL,
  `rtgs_account` varchar(255) DEFAULT NULL,
  `rtgs_member_code` varchar(255) DEFAULT NULL,
  `statement_delivery_bic` varchar(255) DEFAULT NULL,
  `trader_status` varchar(255) DEFAULT NULL,
  `zero_holdings_statement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`member_code`),
  KEY `member_member_status_FK` (`member_status`),
  KEY `member_member_type_FK` (`member_type`),
  KEY `member_central_bank_management_FK` (`mcb_id`),
  KEY `fk_member_modified_by_idx` (`modified_by`),
  KEY `fk_member_approved_by_idx` (`approved_by`),
  KEY `fk_member_tax_profile` (`tax_profile_id`),
  KEY `fk_member_verified_by` (`verified_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('09be369c-3e2b-4f88-98bf-0fb13f22f365','MASGSGSC','MASGSGSC',1,2,'MASGSGSCRTG','MASGSGSCRTG','ACT','OPR','LFB','BANKS','UENMAS0002','LEI0002','NORMAL_MEMBER',0,0,'RTG','2022-05-30 10:03:52',NULL,'ACTIVE','System','2022-08-19 05:45:39','System','2022-08-19 05:45:39','2022-08-19 05:45:39','2022-08-19 05:45:39',NULL,0,'2','',0.00000,0.00000,0.00000,0.00000,'100','System','2022-08-19 05:45:39',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c49e98f4-880d-46fe-9d8b-f3dfbc2837bf','MASGSGSG','MASGSGSG',1,1,'MASGSGSGXXX','MASGSGSGXXX','ACT','CBK','LFB','BANKS','UENMAS0001','LEI0001','NORMAL_MEMBER',0,0,'XXX','2022-05-30 10:03:52',NULL,'ACTIVE','System','2022-08-19 05:45:39','System','2022-08-19 05:45:39','2022-08-19 05:45:39','2022-08-19 05:45:39',NULL,0,'1','',0.00000,0.00000,0.00000,0.00000,'100','System','2022-08-19 05:45:39',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('efa56224-3a5f-460e-a496-bd5bed5fa481','DBSSSGSG','DBSSSGSG',1,2111,'DBS BANK LTD.','DBSBank','ACT','DPP','LFB','BANKS','196800306E','ATUEL7OJR5057F2PV266','NORMAL_MEMBER',0,0,'000','2022-06-01 18:30:00',NULL,'ACTIVE','System','2022-08-19 05:45:39','System','2022-08-19 05:45:39','2022-08-19 05:45:39','2022-08-19 05:45:39',NULL,4,'A','',0.00000,0.00000,0.00000,0.00000,'100','System','2022-08-19 05:45:39',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:33:18
