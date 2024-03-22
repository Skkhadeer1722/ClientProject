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
-- Table structure for table `issuer`
--

DROP TABLE IF EXISTS `issuer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issuer` (
  `id` varchar(36) NOT NULL COMMENT 'PRIMARY KEY UUID',
  `issuer_code` varchar(8) NOT NULL COMMENT 'Issuer Code',
  `issuer_name` varchar(100) NOT NULL COMMENT 'issuer name',
  `issuer_type` varchar(30) NOT NULL COMMENT 'issuer type',
  `lei` varchar(30) DEFAULT NULL COMMENT 'issuer''s legal entity identifier',
  `uen` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'issuer''s uen',
  `issuing_country` varchar(56) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'issuer''s issuing country (2 char ISO country code)',
  `country_of_incorporation` varchar(2) NOT NULL COMMENT 'issuer''s country of incorporation (2 char ISO country code)',
  `action` varchar(6) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(30) NOT NULL COMMENT 'Record status',
  `modified_by` varchar(36) NOT NULL COMMENT 'Last modified User ID',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last modified timestamp',
  `approved_by` varchar(36) NOT NULL COMMENT 'Last approved user ID',
  `approved_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last approved timestamp',
  `effective_date` timestamp NULL DEFAULT NULL COMMENT 'Activation date of the record',
  `created_date` timestamp NOT NULL COMMENT 'Created date of the record',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` int DEFAULT NULL COMMENT 'Approval flow ID',
  `gl_indicator` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `issuer_code` (`issuer_code`),
  KEY `fk_issuer_modified_by_idx` (`modified_by`),
  KEY `fk_issuer_approved_by_idx` (`approved_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='member_screen_issuer_This table is used to configure the issuer related details which is required for securities';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issuer`
--

LOCK TABLES `issuer` WRITE;
/*!40000 ALTER TABLE `issuer` DISABLE KEYS */;
INSERT INTO `issuer` VALUES ('4ad71162-e15d-43be-b16f-064ac88fe7b3','MAS','Monetary Authority of Singapore','AGENCY',NULL,NULL,NULL,'SG',NULL,'ACTIVE','operatoruser2','2022-04-27 09:25:25','b1a38731-fb66-486c-9b0c-5c1045ffe4d6','2022-04-27 09:25:25','2022-04-26 18:30:00','2022-04-25 05:16:11','',2042,1),('537fe131-1c32-4628-90a8-d0968cd5527c','AGD','Singapore Government','GOVERNMENT',NULL,NULL,NULL,'SG',NULL,'ACTIVE','operatoruser2','2022-04-27 09:25:20','b1a38731-fb66-486c-9b0c-5c1045ffe4d6','2022-04-27 09:25:20','2022-04-26 18:30:00','2022-04-25 05:22:28','',2041,1),('8165bb25-7b3f-48dd-81b9-0c56ddc2e395','Issuer12','Issuer 123','CORPORATE',NULL,NULL,NULL,'SG',NULL,'ACTIVE','operatoruser2','2022-04-25 05:18:02','operatoruser1','2022-04-25 05:18:02','2022-04-24 18:30:00','2022-04-18 11:23:38','',1980,1);
/*!40000 ALTER TABLE `issuer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-09  9:19:29
