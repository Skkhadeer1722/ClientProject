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
-- Table structure for table `securities_code_statistics`
--

DROP TABLE IF EXISTS `securities_code_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `securities_code_statistics` (
  `securities_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Securities code from securitities_code table',
  `total_redeemed_amount` bigint NOT NULL COMMENT 'total redeemed amount',
  `total_nominal_amount_issued_for_erf` bigint DEFAULT NULL COMMENT 'To display the total nominal amount issued for ERF.',
  `total_redeemed_amount_for_erf` bigint DEFAULT NULL COMMENT 'To display the total redeemed amount for ERF.',
  `outstanding_nominal_amount` bigint NOT NULL COMMENT 'Outstanding nominal amount',
  `next_coupon_date` int DEFAULT '0' COMMENT 'Next coupon date',
  `last_coupon_date` int DEFAULT '0' COMMENT 'Last Coupon Date',
  `next_index_end_date` int DEFAULT NULL COMMENT 'To use in FRN Coupon Rate Calculation',
  `amount_allotted_to_central_bank` bigint DEFAULT NULL COMMENT 'To display the nominal amount of the securities allotted to MAS',
  `amount_allotted_to_others` bigint DEFAULT NULL COMMENT 'To display the nominal amount of the securities allotted to other participants',
  PRIMARY KEY (`securities_code`),
  KEY `securities_code_statistics_FK` (`securities_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='txn-flow_na_securities-code-statistics_Securities code statistics details will be saved';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securities_code_statistics`
--

LOCK TABLES `securities_code_statistics` WRITE;
/*!40000 ALTER TABLE `securities_code_statistics` DISABLE KEYS */;
INSERT INTO `securities_code_statistics` VALUES ('AUTOSFNEWP99',2000,902799998000,902799998000,902799998000,20230601,20221201,20230601,1500000000,901300000000),('BCSISMBIL001',0,100000000,100000000,100000000,NULL,NULL,NULL,900000,99100000),('BCSISTEST012',0,630000000,630000000,630000000,NULL,NULL,NULL,10000,629990000),('MEPSUAT00016',45000,242500,242500,242500,20230422,20221022,20230422,0,287500),('MEPSUAT00024',130000,170000,170000,170000,20230425,20221025,20230425,0,300000),('MEPSUAT00032',120000,17500,17500,17500,20230503,20221103,20230503,0,137500),('NPTESTSCREEN',0,0,0,0,NULL,NULL,NULL,0,0),('NSBONDMAY002',0,100000,100000,100000,20230530,20221130,20230530,0,100000);
/*!40000 ALTER TABLE `securities_code_statistics` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-09  9:20:05
