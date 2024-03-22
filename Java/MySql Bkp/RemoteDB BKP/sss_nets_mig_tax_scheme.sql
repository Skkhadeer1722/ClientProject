-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 103.177.224.100    Database: sss_nets_mig
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
-- Table structure for table `tax_scheme`
--

DROP TABLE IF EXISTS `tax_scheme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tax_scheme` (
  `id` varchar(36) NOT NULL COMMENT 'PRIMARY KEY',
  `tax_scheme_id` varchar(34) NOT NULL COMMENT 'Tax scheme id',
  `description` varchar(35) NOT NULL COMMENT 'Tax scheme description',
  `tax_currency` varchar(3) NOT NULL COMMENT 'Tax currency',
  `lock` tinyint DEFAULT '0' COMMENT 'indicator whether to lock transaction type record to prevent deletion from user end',
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `tax_scheme_id_UNIQUE` (`tax_scheme_id`),
  KEY `fk_tax_scheme_modified_by_idx` (`modified_by`),
  KEY `fk_tax_scheme_approved_by_idx` (`approved_by`),
  CONSTRAINT `fk_tax_scheme_approved_by` FOREIGN KEY (`approved_by`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_tax_scheme_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='biz-param_screen_tax-scheme_This table is used to save tax scheme related information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tax_scheme`
--

LOCK TABLES `tax_scheme` WRITE;
/*!40000 ALTER TABLE `tax_scheme` DISABLE KEYS */;
INSERT INTO `tax_scheme` VALUES ('0ccbd921-c62c-406b-9c7e-32546cfc84aa','GSTOS','GST Out-of-Scope Supply ','SGD',0,NULL,'ACTIVE','operatoruser2','2022-04-22 05:22:56','47122ef4-0b2d-4306-82cc-d4296c01a8c3','2022-04-22 05:22:56','2022-04-22 05:21:27','2022-04-21 18:30:00','',1951),('22d5ad2a-df08-44c7-a9a9-31eb17d4866c','SGWHT24','SGWHT fr securities issued frm 2024','SGD',0,NULL,'ACTIVE','operatoruser1','2022-04-22 05:37:22','47122ef4-0b2d-4306-82cc-d4296c01a8c3','2022-04-22 05:37:22','2022-04-22 05:36:39','2022-04-21 18:30:00','',1953),('289bdfe2-843b-4291-a943-316178ebb82f','GSTSR','GST Standard Rated Supply','SGD',0,NULL,'ACTIVE','47122ef4-0b2d-4306-82cc-d4296c01a8c3','2022-04-22 05:19:48','operatoruser1','2022-04-22 05:19:48','2022-04-21 08:57:58','2022-04-21 18:30:00','',1950),('3b1d02c3-2bd7-4047-ba67-b9f4233a58ea','GSTES','GST Exempt Supply','SGD',0,NULL,'ACTIVE','operatoruser2','2022-04-22 05:18:49','47122ef4-0b2d-4306-82cc-d4296c01a8c3','2022-04-22 05:18:49','2022-04-22 04:49:57','2022-04-21 18:30:00','',1949),('d4b051a5-1000-438a-8f39-c51c5857d084','TS01','Tax Scheme 01','SGD',0,NULL,'ACTIVE','operatoruser1','2022-04-18 11:20:23','operatoruser2','2022-04-18 11:20:23','2022-04-18 11:19:01','2022-04-13 18:30:00','',1908),('f9f56571-5e96-4295-88ec-0a5e57a971c1','SGWHT23','SGWHT for Securities issued by 2023','SGD',0,NULL,'ACTIVE','operatoruser1','2022-04-22 05:37:18','47122ef4-0b2d-4306-82cc-d4296c01a8c3','2022-04-22 05:37:18','2022-04-22 05:33:55','2022-04-21 18:30:00','',1952);
/*!40000 ALTER TABLE `tax_scheme` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:34:26
