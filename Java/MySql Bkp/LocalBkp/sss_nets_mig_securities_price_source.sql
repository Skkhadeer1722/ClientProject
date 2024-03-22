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
-- Table structure for table `securities_price_source`
--

DROP TABLE IF EXISTS `securities_price_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `securities_price_source` (
  `id` varchar(36) DEFAULT NULL,
  `securities_code` varchar(50) DEFAULT NULL COMMENT 'COMPOSITE PRIMARY KEY - To allow the user to select from a list of available Securities code for enquiry.',
  `pricing_date` varchar(50) DEFAULT NULL COMMENT 'COPOSITE KEY - Pricing Date of Securities Price',
  `description` varchar(50) DEFAULT NULL COMMENT 'Securities description',
  `price` varchar(50) DEFAULT NULL COMMENT 'Price of the Securities.',
  `issue_code` varchar(50) DEFAULT NULL COMMENT 'Issue Code of the Securities',
  `pricing_type` varchar(50) DEFAULT NULL COMMENT 'COMPOSITE KEY - Pricing Type of Securities Price',
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
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(1000) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='application-param_screen_securities-price_The daily securities price will be saved in this table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securities_price_source`
--

LOCK TABLES `securities_price_source` WRITE;
/*!40000 ALTER TABLE `securities_price_source` DISABLE KEYS */;
INSERT INTO `securities_price_source` VALUES ('39ba1a5c23e64fcc83234a9e6b694457','AUTOSFNEWP99','20221201',NULL,'10000000','AUTOSFNP',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,'2022-12-23 14:52:00'),('d56fba0ba85e4cadb93ce639a67873ce','BCSISMBIL001','',NULL,'10000000','BN2005M',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,' Pricing Date : Null or Empty','2022-12-23 14:52:00'),('a287131a6b664ad4bd28e798066a6be0','BCSISTEST012','',NULL,'10000000','N999012M',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,' Pricing Date : Null or Empty','2022-12-23 14:52:00'),('b372b87660db498f8fef2afa424f87a3','MEPSUAT00016','20221022',NULL,'10000000','GX16501W',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,NULL,'2022-12-23 14:52:00'),('4e8fa2bf92ad445fa21aeae22b9570d9','MEPSUAT00024','20221025',NULL,'10000000','GX16502N',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,NULL,'2022-12-23 14:52:00'),('23e6517d4d984d6facc67abacc6a2846','MEPSUAT00032','20221103',NULL,'10000000','GX16503V',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6,NULL,'2022-12-23 14:52:01'),('fd9a496b2f2e4c44ba31023bcc97cbb1','NPTESTSCREEN','',NULL,'10000000','NY15046C',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,7,' Pricing Date : Null or Empty','2022-12-23 14:52:01'),('e49ea1dc12b54cc583f2a896213ca869','NSBONDMAY002','20221130',NULL,'10000000','NS21002A',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8,NULL,'2022-12-23 14:52:01');
/*!40000 ALTER TABLE `securities_price_source` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-09  9:20:04
