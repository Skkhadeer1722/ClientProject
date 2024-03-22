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
-- Table structure for table `sss_account`
--

DROP TABLE IF EXISTS `sss_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sss_account` (
  `id` varchar(36) NOT NULL COMMENT 'Internal UUID',
  `member_id` varchar(36) NOT NULL COMMENT 'Internal Member ID (UUID)',
  `account_id` varchar(35) NOT NULL COMMENT 'Account ID - Unique to member',
  `description` varchar(35) NOT NULL COMMENT 'Account description',
  `custody_account_type_id` varchar(36) NOT NULL COMMENT 'Internal Custody Account Type ID (UUID)',
  `corporate_investor` varchar(20) DEFAULT NULL COMMENT 'LEI/UEN of the company',
  `account_status` varchar(3) NOT NULL COMMENT 'Account status - Pending Activation, Active, Suspended, Closed\nDefaults to ''Pending Activation'' for new account\n',
  `tax_profile_id` varchar(36) DEFAULT NULL COMMENT 'Tax Profile Id (UUID)',
  `statement_delivery_bic` varchar(11) DEFAULT NULL,
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
  `account_category` varchar(255) DEFAULT NULL,
  `investor_individual_id` varchar(255) DEFAULT NULL,
  `investor_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_id_UNIQUE` (`account_id`),
  KEY `fk_account_member_idx` (`member_id`),
  KEY `fk_account_custody_account_type_idx` (`custody_account_type_id`),
  KEY `fk_account_tax_profile_idx` (`tax_profile_id`),
  KEY `fk_account_modified_by_idx` (`modified_by`),
  KEY `fk_account_approved_by_idx` (`approved_by`),
  CONSTRAINT `fk_account_custody_account_type` FOREIGN KEY (`custody_account_type_id`) REFERENCES `custody_account_type` (`id`),
  CONSTRAINT `fk_account_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `fk_account_tax_profile` FOREIGN KEY (`tax_profile_id`) REFERENCES `tax_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='member_screen_account_This table used to save the SSS accounts';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss_account`
--

LOCK TABLES `sss_account` WRITE;
/*!40000 ALTER TABLE `sss_account` DISABLE KEYS */;
INSERT INTO `sss_account` VALUES ('06fbeb58-079d-4cc9-82bd-40b8d2b3f5c6','096b44f4-1b0f-4a69-8a2f-727a80bd4de8','ERF','Receiver Account CA1','1c33352a-dae9-45a0-8616-8498f2ec1183',NULL,'ACT','e54772f9-01d6-429d-927e-416c1f51db04',NULL,NULL,'ACTIVE','46cd963c-5d14-49c2-9da1-27e353206b10','2022-06-01 19:48:27','56b7a436-bf00-45d2-b3d1-f0098cb886ae','2022-06-01 19:48:27','2022-06-01 05:17:02','2022-06-01 10:30:00','',63,NULL,NULL,NULL),('293e05dd-7914-43f7-8d0a-95c805c22a46','cc4812ad-5c87-4729-a093-858687bd1fa4','CTR00002','Control Account for MAS','75079478-62e9-43c1-82b3-4d0091f2e8c0',NULL,'ACT','e54772f9-01d6-429d-927e-416c1f51db04',NULL,NULL,'ACTIVE','88384283-0c0e-45e8-a32d-14221d4017bc','2022-06-01 20:50:56','40d6436f-75b4-40aa-a82f-2789ea0cb014','2022-06-01 20:50:56','2022-06-01 20:50:33','2022-06-01 10:30:00','',77,NULL,NULL,NULL),('54b0e908-d207-4d57-b719-0cd6a9099741','cc4812ad-5c87-4729-a093-858687bd1fa4','ALO00001','Issuer\'s Account for CBK','7e73c7f5-3fd5-45f8-a54b-53d27df41fa5',NULL,'ACT','e54772f9-01d6-429d-927e-416c1f51db04',NULL,NULL,'ACTIVE','56b7a436-bf00-45d2-b3d1-f0098cb886ae','2022-06-01 19:51:23','46cd963c-5d14-49c2-9da1-27e353206b10','2022-06-01 19:51:23','2022-06-01 19:51:11','2022-06-01 10:30:00','',64,NULL,NULL,NULL),('9447d8e6-cf5c-4118-9684-f61c6eea1d35','cc4812ad-5c87-4729-a093-858687bd1fa4','MAS00001','Account for MAS - Incorrect account','1c33352a-dae9-45a0-8616-8498f2ec1183',NULL,'ACT','e54772f9-01d6-429d-927e-416c1f51db04',NULL,NULL,'ACTIVE','56b7a436-bf00-45d2-b3d1-f0098cb886ae','2022-06-01 19:47:09','46cd963c-5d14-49c2-9da1-27e353206b10','2022-06-01 19:47:09','2022-06-01 04:33:47','2022-06-01 10:30:00','',62,NULL,NULL,NULL),('d6469c73-e783-4400-8f53-d9e3a14b4a81','40d300df-364f-48de-8ad1-737fdf4d3024','MRF','Receiver Account CA1','1c33352a-dae9-45a0-8616-8498f2ec1183',NULL,'ACT','e54772f9-01d6-429d-927e-416c1f51db04',NULL,NULL,'ACTIVE','46cd963c-5d14-49c2-9da1-27e353206b10','2022-06-01 19:48:27','56b7a436-bf00-45d2-b3d1-f0098cb886ae','2022-06-01 19:48:27','2022-06-01 05:17:02','2022-06-01 10:30:00','',63,NULL,NULL,NULL),('f341e16b-0afb-4607-b72f-0e5adf88a911','096b44f4-1b0f-4a69-8a2f-727a80bd4de8','CANITEST1','For Testing Securities re-opening','f1094486-b936-42fa-94b8-4393fa476bc7',NULL,'ACT','b599202d-8642-44f8-805c-02000a47b884',NULL,NULL,'ACTIVE','0f70527b-5631-4432-b039-4278e2474f1b','2022-06-08 20:07:00','46cd963c-5d14-49c2-9da1-27e353206b10','2022-06-08 20:07:00','2022-06-08 19:50:34','2022-06-08 10:30:00','',380,NULL,NULL,NULL),('fc6aed09-2252-4e9e-a35b-712cffebbb09','096b44f4-1b0f-4a69-8a2f-727a80bd4de8','A0000001','Account for Participant A','f1094486-b936-42fa-94b8-4393fa476bc7',NULL,'ACT','e54772f9-01d6-429d-927e-416c1f51db04',NULL,NULL,'ACTIVE','56b7a436-bf00-45d2-b3d1-f0098cb886ae','2022-06-01 03:15:46','46cd963c-5d14-49c2-9da1-27e353206b10','2022-06-01 03:15:46','2022-06-01 03:15:18','2022-05-31 10:30:00','',25,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sss_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:32:09
