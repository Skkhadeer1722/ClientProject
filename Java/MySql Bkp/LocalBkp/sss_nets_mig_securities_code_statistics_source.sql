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
-- Table structure for table `securities_code_statistics_source`
--

DROP TABLE IF EXISTS `securities_code_statistics_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `securities_code_statistics_source` (
  `id` varchar(36) DEFAULT NULL,
  `securities_code` varchar(50) DEFAULT NULL COMMENT 'Securities code from securitities_code table',
  `total_redeemed_amount` varchar(50) DEFAULT NULL COMMENT 'total redeemed amount',
  `total_nominal_amount_issued_for_erf` varchar(50) DEFAULT NULL COMMENT 'To display the total nominal amount issued for ERF.',
  `total_redeemed_amount_for_erf` varchar(50) DEFAULT NULL COMMENT 'To display the total redeemed amount for ERF.',
  `outstanding_nominal_amount` varchar(50) DEFAULT NULL COMMENT 'Outstanding nominal amount',
  `next_coupon_date` varchar(50) DEFAULT NULL COMMENT 'Next coupon date',
  `last_coupon_date` varchar(50) DEFAULT NULL COMMENT 'Last Coupon Date',
  `next_index_end_date` varchar(50) DEFAULT NULL COMMENT 'To use in FRN Coupon Rate Calculation',
  `amount_allotted_to_central_bank` varchar(50) DEFAULT NULL COMMENT 'To display the nominal amount of the securities allotted to MAS',
  `amount_allotted_to_others` varchar(50) DEFAULT NULL COMMENT 'To display the nominal amount of the securities allotted to other participants',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(1000) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='txn-flow_na_securities-code-statistics_Securities code statistics details will be saved';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securities_code_statistics_source`
--

LOCK TABLES `securities_code_statistics_source` WRITE;
/*!40000 ALTER TABLE `securities_code_statistics_source` DISABLE KEYS */;
INSERT INTO `securities_code_statistics_source` VALUES ('3f95f838c98549529aba59997bdd619d','AUTOSFNEWP99','000000000002000','000902799998000','000902799998000','000902799998000','20230601','20221201','20230601','000001500000000','000901300000000',1,NULL,'2022-12-23 17:26:08'),('8d5f9b351a664055b2d039338c4e90b7','BCSISMBIL001','000000000000000','000000100000000','000000100000000','000000100000000','','','','000000000900000','000000099100000',2,NULL,'2022-12-23 17:26:08'),('3d60e54e76a04556bc7f6e91aa8dfd7b','BCSISTEST012','000000000000000','000000630000000','000000630000000','000000630000000','','','','000000000010000','000000629990000',3,NULL,'2022-12-23 17:26:09'),('a650e71cca474e9b847c1c9cb182be0b','MEPSUAT00016','000000000045000','000000000242500','000000000242500','000000000242500','20230422','20221022','20230422','000000000000000','000000000287500',4,NULL,'2022-12-23 17:26:10'),('a1544fea36584da3a4a6e4a6d8a0ac5d','MEPSUAT00024','000000000130000','000000000170000','000000000170000','000000000170000','20230425','20221025','20230425','000000000000000','000000000300000',5,NULL,'2022-12-23 17:26:11'),('e39ce3c535724e608b253f4b617e3e2f','MEPSUAT00032','000000000120000','000000000017500','000000000017500','000000000017500','20230503','20221103','20230503','000000000000000','000000000137500',6,NULL,'2022-12-23 17:26:12'),('23a557c73b0747929245d4529841f480','NPTESTSCREEN','000000000000000','000000000000000','000000000000000','000000000000000','','','','000000000000000','000000000000000',7,NULL,'2022-12-23 17:26:12'),('8bfc575088ec439486dc5fe35fed8af7','NSBONDMAY002','000000000000000','000000000100000','000000000100000','000000000100000','20230530','20221130','20230530','000000000000000','000000000100000',8,NULL,'2022-12-23 17:26:13');
/*!40000 ALTER TABLE `securities_code_statistics_source` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-09  9:19:55
