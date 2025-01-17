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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='txn-flow_na_securities-code-statistics_Securities code statistics details will be saved';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securities_code_statistics_source`
--

LOCK TABLES `securities_code_statistics_source` WRITE;
/*!40000 ALTER TABLE `securities_code_statistics_source` DISABLE KEYS */;
INSERT INTO `securities_code_statistics_source` VALUES ('ad40dae12b854a7d9d4eb6fb163fccb8','BCSISTEST012','000000000002000','000902799998000','000902799998000','000902799998000','20230601','20221201','20230601','000001500000000','000901300000000',1,NULL,'2023-01-02 12:59:24'),('9d19fcf4e6b14af8b9b16b6843ce23c4','BCSISMBIL001','000000000000000','000000100000000','000000100000000','000000100000000','','','','000000000900000','000000099100000',2,NULL,'2023-01-02 12:59:24'),('239ad70912544001bd4528a513376b66','MEPSUAT00016','000000000045000','000000000242500','000000000242500','000000000242500','20230422','20221022','20230422','000000000000000','000000000287500',3,NULL,'2023-01-02 12:59:24'),('8034b9e13b4743e1b0013c9e86201aec','MEPSUAT00024','000000000130000','000000000170000','000000000170000','000000000170000','20230425','20221025','20230425','000000000000000','000000000300000',4,NULL,'2023-01-02 12:59:24'),('42036df2965249deb979c7b178946151','BCSISTEST012','000000000002000','000902799998000','000902799998000','000902799998000','20230601','20221201','20230601','000001500000000','000901300000000',5,NULL,'2023-01-02 15:45:41'),('31feb1f080364eb7a291cb7a8e4db908','BCSISMBIL001','000000000000000','000000100000000','000000100000000','000000100000000','','','','000000000900000','000000099100000',6,NULL,'2023-01-02 15:45:41'),('17409e71c2dc4d67abc5748219e4171f','MEPSUAT00016','000000000045000','000000000242500','000000000242500','000000000242500','20230422','20221022','20230422','000000000000000','000000000287500',7,NULL,'2023-01-02 15:45:42'),('407a4b85d23442b0a3d1f2e55a0d8bb0','MEPSUAT00024','000000000130000','000000000170000','000000000170000','000000000170000','20230425','20221025','20230425','000000000000000','000000000300000',8,NULL,'2023-01-02 15:45:42'),('8da699eaa57149c496536878388748f8','BCSISTEST012','000000000002000','000902799998000','000902799998000','000902799998000','20230601','20221201','20230601','000001500000000','000901300000000',9,NULL,'2023-01-02 17:51:42'),('cad884652d4e4d2da4cdaaf93521f999','BCSISMBIL001','000000000000000','000000100000000','000000100000000','000000100000000','','','','000000000900000','000000099100000',10,NULL,'2023-01-02 17:51:42'),('7995fdc728994ab0934907bd07ca451a','MEPSUAT00016','000000000045000','000000000242500','000000000242500','000000000242500','20230422','20221022','20230422','000000000000000','000000000287500',11,NULL,'2023-01-02 17:51:42'),('d10709a68e274c889959a455c5da591f','MEPSUAT00024','000000000130000','000000000170000','000000000170000','000000000170000','20230425','20221025','20230425','000000000000000','000000000300000',12,NULL,'2023-01-02 17:51:42');
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

-- Dump completed on 2023-01-03 11:34:23
