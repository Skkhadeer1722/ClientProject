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
-- Table structure for table `sss_member`
--

DROP TABLE IF EXISTS `sss_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sss_member` (
  `id` varchar(36) NOT NULL COMMENT 'Internal UUID, Primary key, Member''s unique identification',
  `member_code` varchar(11) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT 'member name',
  `short_name` varchar(30) NOT NULL COMMENT 'member’s short name',
  `member_type` varchar(3) NOT NULL COMMENT 'play the member type, shows the type belonging to the member. FOREIGN KEY - Id from member_type',
  `member_status` varchar(3) NOT NULL COMMENT 'member’s status. FOREIGN KEY - Id from member_status',
  `activation_date` date NOT NULL COMMENT 'To allow the user to set the activation date of the new member creation',
  `swift_bic` varchar(8) NOT NULL COMMENT '"member’s SWIFT\nBIC. SWIFT BIC used by a Participant at its alternate site."',
  `location` varchar(3) NOT NULL COMMENT '3-digit location code. Any transaction generated by the system will send to the location set in this field',
  `fi_code` varchar(4) NOT NULL COMMENT 'unique bank code',
  `trader_status` varchar(20) NOT NULL,
  `sector_id` varchar(30) NOT NULL COMMENT 'sector Id',
  `auto_debit_indicator` tinyint NOT NULL COMMENT 'To allow the user to select the auto debit indicator',
  `facility_eligibility_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'To allow the user to select the Faciliy Eligibility ID. Id from facility_eligibility table as FOREIGN KEY',
  `uen` varchar(10) DEFAULT NULL COMMENT 'UEN number',
  `lei` varchar(20) DEFAULT NULL COMMENT 'LEI number',
  `statement_delivery_bic` varchar(11) DEFAULT NULL COMMENT 'statement delivery BIC',
  `end_of_day_statement` varchar(36) NOT NULL COMMENT '"• SWIFT securities statement – Statement (semt.002, semt.017, semt.018) will be sent out to the Member\n• OSP securities statement – Statement will not be sent out to the member. Member will retrieve the statement in OSP\n• No statement – Statement will not be sent out. (default value for auto creation by system)\n"',
  `exempted_from_billing` tinyint NOT NULL COMMENT 'indication whether the member is exempted from the billing',
  `exempted_from_system_limit` tinyint NOT NULL COMMENT 'indication whether the member is exempted from system limit',
  `zero_holdings_statement` varchar(20) NOT NULL COMMENT '"option when the SSS statements will be generated\n• Daily: Statement will be generated for all working days even when there are no holdings in the custody accounts.\n• Monthly: Statement will be generated on the last working day of every month even when there are no holdings in the custody accounts. For the rest of the working days of each month, the statements of accounts would only be generated when there are holdings in the custody accounts. \n• No: Daily statement will not be generated when there are no holdings in the custody accounts.\nNote: MT535 will be generated for each custody account type."',
  `rtgs_account` varchar(34) NOT NULL COMMENT 'To allow the user to input RTGS Account For Settlement.',
  `tax_profile_id` varchar(36) DEFAULT NULL COMMENT 'Its tax profile for billing',
  `billing_profile_id` varchar(36) DEFAULT NULL COMMENT 'Its fee profile for billing',
  `action` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(30) NOT NULL COMMENT 'Record status',
  `modified_by` varchar(36) NOT NULL COMMENT 'Last modified User ID',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last modified timestamp',
  `approved_by` varchar(36) NOT NULL COMMENT 'Last approved user ID',
  `approved_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last approved timestamp',
  `created_date` timestamp NOT NULL COMMENT 'Created date of the record',
  `effective_date` timestamp NULL DEFAULT NULL COMMENT 'Activation date of the record',
  `workflow_status_id` int DEFAULT NULL COMMENT 'Approval flow ID',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `fall_back_account` varchar(36) DEFAULT NULL COMMENT 'To allow the user to select a fall back account of the member.',
  `issuing_paying_agent` varchar(36) DEFAULT NULL COMMENT 'indicate whether member is a primary dealer.',
  `primary_dealer` varchar(36) DEFAULT NULL,
  `fi_group` char(1) NOT NULL COMMENT 'To allow the user to input the one character financial institution group',
  `account_operator` varchar(36) DEFAULT NULL,
  `verified_by` varchar(36) DEFAULT NULL,
  `verified_date` timestamp NULL DEFAULT NULL,
  `rtgs_member_code` varchar(11) NOT NULL COMMENT 'To allow the user to input RTGS Member Code.',
  `activationDate` varchar(255) DEFAULT NULL,
  `autoDebitIndicator` varchar(255) DEFAULT NULL,
  `bankCode` varchar(255) DEFAULT NULL,
  `endOfDayStatement` varchar(255) DEFAULT NULL,
  `exemptedFromBilling` varchar(255) DEFAULT NULL,
  `exemptedFromSystemLimit` varchar(255) DEFAULT NULL,
  `facilityEligibilityId` varchar(255) DEFAULT NULL,
  `feeProfileId` varchar(255) DEFAULT NULL,
  `memberCode` varchar(255) DEFAULT NULL,
  `memberName` varchar(255) DEFAULT NULL,
  `memberShortName` varchar(255) DEFAULT NULL,
  `memberStatus` varchar(255) DEFAULT NULL,
  `memberType` int NOT NULL,
  `sectorId` varchar(255) DEFAULT NULL,
  `statementDeliveryBic` varchar(255) DEFAULT NULL,
  `taxProfileId` varchar(255) DEFAULT NULL,
  `zeroHoldingsStatement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_code` (`member_code`),
  UNIQUE KEY `member_UN` (`fi_code`),
  KEY `fk_member_modified_by_idx` (`modified_by`),
  KEY `fk_member_approved_by_idx` (`approved_by`),
  KEY `fk_member_tax_profile_idx` (`tax_profile_id`),
  KEY `fk_member_member_type_idx` (`member_type`),
  KEY `fk_member_member_status_idx` (`member_status`),
  KEY `fk_member_facility_eligibility_idx` (`facility_eligibility_id`),
  KEY `fk_member_billing_profile_idx` (`billing_profile_id`),
  CONSTRAINT `fk_member_billing_profile` FOREIGN KEY (`billing_profile_id`) REFERENCES `billing_profile` (`id`),
  CONSTRAINT `fk_member_facility_eligibility` FOREIGN KEY (`facility_eligibility_id`) REFERENCES `facility_eligibility` (`id`),
  CONSTRAINT `fk_member_member_status` FOREIGN KEY (`member_status`) REFERENCES `member_status` (`id`),
  CONSTRAINT `fk_member_member_type` FOREIGN KEY (`member_type`) REFERENCES `member_type` (`id`),
  CONSTRAINT `fk_member_tax_profile` FOREIGN KEY (`tax_profile_id`) REFERENCES `tax_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='member_screen_member_This table is used to save the members of sss';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss_member`
--

LOCK TABLES `sss_member` WRITE;
/*!40000 ALTER TABLE `sss_member` DISABLE KEYS */;
INSERT INTO `sss_member` VALUES ('096b44f4-1b0f-4a69-8a2f-727a80bd4de8','ZYSASGS0','ZYSA Participant','ZYSASGS0','DPP','ACT','2022-06-02','ZYSASGS0','001','1112','PRIMARY_DEALER','IPA',1,'ALLFacMem','202145679R',NULL,NULL,'SWIFT_SECURITIES_STATEMENT',1,0,'DAILY','11120201','f0377e3c-a749-4838-a994-4b61878196b4',NULL,NULL,'ACTIVE','46cd963c-5d14-49c2-9da1-27e353206b10','2022-06-16 20:16:27','40d6436f-75b4-40aa-a82f-2789ea0cb014','2022-06-16 20:16:27','2022-06-01 02:20:23','2022-06-16 10:30:00',655,'','fc6aed09-2252-4e9e-a35b-712cffebbb09','Yes','Yes','A','','40d6436f-75b4-40aa-a82f-2789ea0cb014','2022-06-16 20:16:21','ZYSASGS0001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('31c17da1-2380-4264-8b83-515899ca1015','ZYSHSGS0','ZYSHParticipant08','ZYSHBank','VOS','ACT','2022-06-08','ZYSHSGS0','001','4125','PRIMARY_DEALER','LWB',1,'ALLFacMem','ZYSM21001V',NULL,'ZYSHSGS0','SWIFT_SECURITIES_STATEMENT',1,1,'NO','41250201','f0377e3c-a749-4838-a994-4b61878196b4',NULL,NULL,'ACTIVE','88384283-0c0e-45e8-a32d-14221d4017bc','2022-06-14 01:59:05','81c5065b-5098-4234-9fab-352edabf0e39','2022-06-14 01:59:05','2022-06-07 03:06:11','2022-06-13 10:30:00',602,'','','No','No','B','','81c5065b-5098-4234-9fab-352edabf0e39','2022-06-14 01:57:50','ZYSHSGS0001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('40d300df-364f-48de-8ad1-737fdf4d3024','ZYSFSGS0','ZYSF Participant','ZYSFSGS0','DPP','ACT','2022-06-02','ZYSFSGS0','006','1113','PRIMARY_DEALER','IPA',1,'ALLFacMem','S10FC2022D',NULL,NULL,'SWIFT_SECURITIES_STATEMENT',1,0,'DAILY','21230201','f0377e3c-a749-4838-a994-4b61878196b4',NULL,NULL,'ACTIVE','40d6436f-75b4-40aa-a82f-2789ea0cb014','2022-06-16 01:14:50','9bbcf05f-1fa7-49cf-9a40-1484f49a5f97','2022-06-16 01:14:50','2022-06-01 05:11:08','2022-06-15 10:30:00',629,'','','Yes','Yes','A','cc4812ad-5c87-4729-a093-858687bd1fa4','9bbcf05f-1fa7-49cf-9a40-1484f49a5f97','2022-06-16 01:14:37','ZYSFSGS0001',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('94524a8c-a3d8-4c10-8643-c0cd7175a5c1','ZYSBSGS0','ZYSB Participant','ZYSBSGS0XXX','DPP','ACT','2022-06-08','ZYSBSGS0','002','3114','PRIMARY_DEALER','LFB',1,'ALLFacMem','202109876R',NULL,NULL,'SWIFT_SECURITIES_STATEMENT',1,0,'DAILY','31140201','f0377e3c-a749-4838-a994-4b61878196b4',NULL,NULL,'ACTIVE','40d6436f-75b4-40aa-a82f-2789ea0cb014','2022-06-13 20:04:17','88384283-0c0e-45e8-a32d-14221d4017bc','2022-06-13 20:04:17','2022-06-06 22:30:29','2022-06-13 10:30:00',586,'','','No','Yes','B','','88384283-0c0e-45e8-a32d-14221d4017bc','2022-06-13 20:03:58','ZYSBSGS0000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('bc9eb452-851b-4367-84ae-775ce2aea818','MASGSGSC','Operator','MASGSGSC','VOS','ACT','2022-03-08','MASGSGSC','SSS','1414','PRIMARY_DEALER','IPA',1,'ALLFacMem','M020P1011S',NULL,NULL,'SWIFT_SECURITIES_STATEMENT',0,0,'MONTHLY','',NULL,NULL,NULL,'ACTIVE','system','2022-04-01 01:18:57','system','2022-04-01 01:19:27','2022-03-07 02:28:33','2022-03-31 10:30:00',NULL,'','','Yes','Yes','1',NULL,NULL,NULL,'rtgs002',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('cc4812ad-5c87-4729-a093-858687bd1fa4','MASGSGSG','MAS','MAS','CBK','ACT','2020-10-13','MASGSGSG','XXX','1111','CBK','IPA',1,'ALLFacMem','123456789A','111','1','NO_STATEMENT',1,1,'DAILY','33330301','f0377e3c-a749-4838-a994-4b61878196b4',NULL,NULL,'ACTIVE','88384283-0c0e-45e8-a32d-14221d4017bc','2022-06-15 23:12:04','40d6436f-75b4-40aa-a82f-2789ea0cb014','2022-06-15 23:12:04','2020-09-02 18:30:00','2022-06-15 10:30:00',633,'','0c80ce81-0e07-44c2-bbc4-212e6250543d','Yes','Yes','A','','40d6436f-75b4-40aa-a82f-2789ea0cb014','2022-06-15 23:11:58','MASGSGSGXXX',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sss_member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:33:15