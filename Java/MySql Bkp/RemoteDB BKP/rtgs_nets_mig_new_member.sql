-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 103.177.224.100    Database: rtgs_nets_mig_new
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
  `member_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SENSITIVE, member code',
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
  PRIMARY KEY (`id`),
  KEY `member_member_status_FK` (`member_status`),
  KEY `member_member_type_FK` (`member_type`),
  KEY `member_central_bank_management_FK` (`mcb_id`),
  KEY `fk_member_modified_by_idx` (`modified_by`),
  KEY `fk_member_approved_by_idx` (`approved_by`),
  KEY `fk_member_tax_profile` (`tax_profile_id`),
  KEY `fk_member_verified_by` (`verified_by`),
  CONSTRAINT `fk_member_approved_by` FOREIGN KEY (`approved_by`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_member_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_member_tax_profile` FOREIGN KEY (`tax_profile_id`) REFERENCES `tax_profile` (`id`),
  CONSTRAINT `fk_member_verified_by` FOREIGN KEY (`verified_by`) REFERENCES `user` (`id`),
  CONSTRAINT `member_cbm_mcb_management_FK` FOREIGN KEY (`mcb_id`) REFERENCES `cbm_mcb_management` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `member_member_status_FK` FOREIGN KEY (`member_status`) REFERENCES `member_status` (`id`),
  CONSTRAINT `member_member_type_FK` FOREIGN KEY (`member_type`) REFERENCES `member_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('0c458af4-0d8a-4023-a8c1-d010d08192aa','MASGSGSG','MASGSGSG',1,1,'MASGSGSGXXX','MASGSGSGXXX','ACT','CBK','LFB','BANKS','UENMAS0001','LEI0001','NORMAL_MEMBER',0,0,'XXX','2022-05-30 10:03:52',NULL,'ACTIVE','system','2022-05-30 10:03:52','system','2022-05-30 10:03:52','2022-05-30 10:03:52','2022-05-30 10:03:52',NULL,NULL,'1',NULL,0.00000,0.00000,0.00000,0.00000,'100','system','2022-05-30 10:03:52'),('0ea11c4d-cdfe-4dc6-a838-d8a9c530065a','MASGSGSC','MASGSGSC',1,2,'MASGSGSCRTG','MASGSGSCRTG','ACT','OPR','LFB','BANKS','UENMAS0002','LEI0002','NORMAL_MEMBER',0,0,'RTG','2022-05-30 10:03:52',NULL,'ACTIVE','system','2022-05-30 10:03:52','system','2022-05-30 10:03:52','2022-05-30 10:03:52','2022-05-30 10:03:52',NULL,NULL,'2',NULL,0.00000,0.00000,0.00000,0.00000,'100','system','2022-05-30 10:03:52'),('3bffcdd1-3bee-46e3-91d1-a15803e0bbea','CMBCSGSG','CMBCSGSG',1,7088,'CHINA MERCHANTS BANK SINGAPORE BRANCH','CMBCBank','ACT','DPP','LFB','BANKS','T21UF6703F','549300L51ZEIQOS58R74','NORMAL_MEMBER',0,0,'000','2022-06-02 18:30:00',NULL,'ACTIVE','1836952f-6245-47c3-ae8d-695bef6b90e7','2022-06-02 10:20:50','3b1aef27-127c-4fa5-a3da-19b66d8a437e','2022-06-02 10:21:20','2022-06-02 10:19:58','2022-06-02 02:30:00','',132,'A','',0.00000,0.00000,0.00000,0.00000,'100','3b1aef27-127c-4fa5-a3da-19b66d8a437e','2022-06-02 10:20:50'),('f31f32f4-1705-4413-9820-4a7b8d28da58','DBSSSGSG','DBSSSGSG',1,2111,'DBS BANK LTD.','DBSBank','ACT','DPP','LFB','BANKS','196800306E','ATUEL7OJR5057F2PV266','NORMAL_MEMBER',0,0,'000','2022-06-01 18:30:00',NULL,'ACTIVE','dac841f0-9c59-43ff-bd9d-6cde7cbc3ef6','2022-06-01 11:03:57','1e8b0bf2-a6b0-4c82-a1e2-bd2ae3b84a41','2022-06-01 11:05:09','2022-06-01 10:58:49','2022-06-01 02:30:00','',4,'A','',0.00000,0.00000,0.00000,0.00000,'100','614def60-2c5f-4005-87ed-785011feb1f0','2022-06-01 11:03:57'),('ff578b30-a524-49eb-9230-c9e1a03864b5','CSPBSGSG','CSPBSGSG',1,4404,'CREDIT SUISSE (SINGAPORE) LIMITED','CREDITSUISSE','ACT','DPP','MEB','DBANK','197702363D','549300WJEPV9WK5FYX61','NORMAL_MEMBER',0,0,'000','2022-06-01 18:30:00',NULL,'ACTIVE','614def60-2c5f-4005-87ed-785011feb1f0','2022-06-01 11:17:18','1e8b0bf2-a6b0-4c82-a1e2-bd2ae3b84a41','2022-06-01 11:18:16','2022-06-01 11:12:06','2022-06-01 02:30:00','',17,'D','',0.00000,0.00000,0.00000,0.00000,'200','dac841f0-9c59-43ff-bd9d-6cde7cbc3ef6','2022-06-01 11:17:18');
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

-- Dump completed on 2023-01-03 11:34:48
