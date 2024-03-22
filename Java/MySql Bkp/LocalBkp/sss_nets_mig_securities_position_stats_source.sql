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
-- Table structure for table `securities_position_stats_source`
--

DROP TABLE IF EXISTS `securities_position_stats_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `securities_position_stats_source` (
  `id` varchar(36) DEFAULT NULL,
  `securities_code` varchar(50) DEFAULT NULL COMMENT 'COMPOSITE PRIMARY KEY - Securities code',
  `value_date` varchar(50) DEFAULT NULL COMMENT 'COMPOSITE primary key',
  `opening_nominal_amount` varchar(50) DEFAULT NULL COMMENT 'The opening nominal amount will be the previous working day''s closing nominal amount.',
  `closing_nominal_amount` varchar(50) DEFAULT NULL COMMENT 'The current nominal amount is the net of the opening nominal amount and the current day net changes',
  `settled_count` varchar(50) DEFAULT NULL COMMENT 'number of transactions settled today',
  `settled_amount` varchar(50) DEFAULT NULL COMMENT 'total figures for settled transactions',
  `receipt_count` varchar(50) DEFAULT NULL COMMENT 'number of received securities today',
  `receipt_amount` varchar(50) DEFAULT NULL COMMENT 'total figures of received securities today',
  `queued_count` varchar(50) DEFAULT NULL COMMENT 'queued transactions count',
  `queued_amount` varchar(50) DEFAULT NULL COMMENT 'total figures for queued transactions',
  `rejected_count` varchar(50) DEFAULT NULL COMMENT 'rejected transactions count',
  `rejected_amount` varchar(50) DEFAULT NULL COMMENT 'total figures for rejected transactions',
  `cancelled_count` varchar(50) DEFAULT NULL COMMENT 'cancelled transactions count',
  `cancelled_amount` varchar(50) DEFAULT NULL COMMENT 'total figures for cancelled transactions',
  `account_id` varchar(50) DEFAULT NULL COMMENT 'COMPOSITE PRIMARY KEY - account id of the sss account',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(1000) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='txn-flow_na_securities-position-stats_This table is used to maintain the statistics based on the securities transaction';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securities_position_stats_source`
--

LOCK TABLES `securities_position_stats_source` WRITE;
/*!40000 ALTER TABLE `securities_position_stats_source` DISABLE KEYS */;
INSERT INTO `securities_position_stats_source` VALUES ('d8cbc45f05e04c69a5ce16beed360764','AUTOSFNEWP99',NULL,'000902799998000','000902799998000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ALLOTEST1',1,NULL,'2022-12-23 16:36:53'),('4d644f86dff945069a3e11016057d96b','BCSISMBIL001',NULL,'000000100000000','000000100000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ALLOTEST1',2,NULL,'2022-12-23 16:36:53'),('3a72e94135704838bcb2583c024783dd','BCSISTEST012',NULL,'000000630000000','000000630000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ALLOTEST1',3,NULL,'2022-12-23 16:36:53'),('604e7b6108bd488ea02c825c0d7d72ca','MEPSUAT00016',NULL,'000000000242500','000000000242500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ALLOTEST1',4,NULL,'2022-12-23 16:36:54'),('06f2a364983d431c93b327d214c81fcd','MEPSUAT00024',NULL,'000000000170000','000000000170000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ALLOTEST1',5,NULL,'2022-12-23 16:36:54'),('abc10ec9c62e4efa847caa2974b49ca8','MEPSUAT00032',NULL,'000000000017500','000000000017500',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ALLOTEST1',6,NULL,'2022-12-23 16:36:54'),('4051287bca4346afb1e689f443da8cb4','NPTESTSCREEN',NULL,'000000000000000','000000000000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ALLOTEST1',7,NULL,'2022-12-23 16:36:55'),('48e2325fbf0044ba8c5c0720591859ac','NSBONDMAY002',NULL,'000000000100000','000000000100000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ALLOTEST1',8,NULL,'2022-12-23 16:36:55');
/*!40000 ALTER TABLE `securities_position_stats_source` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-09  9:19:53
