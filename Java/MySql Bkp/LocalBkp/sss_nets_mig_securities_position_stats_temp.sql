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
-- Table structure for table `securities_position_stats_temp`
--

DROP TABLE IF EXISTS `securities_position_stats_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `securities_position_stats_temp` (
  `id` varchar(36) NOT NULL,
  `securities_code` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'COMPOSITE PRIMARY KEY - Securities code',
  `value_date` int NOT NULL COMMENT 'COMPOSITE primary key',
  `opening_nominal_amount` decimal(23,5) NOT NULL COMMENT 'The opening nominal amount will be the previous working day''s closing nominal amount.',
  `closing_nominal_amount` decimal(23,5) DEFAULT NULL COMMENT 'The current nominal amount is the net of the opening nominal amount and the current day net changes',
  `settled_count` smallint NOT NULL DEFAULT '0' COMMENT 'number of transactions settled today',
  `settled_amount` decimal(23,5) DEFAULT NULL COMMENT 'total figures for settled transactions',
  `receipt_count` smallint NOT NULL DEFAULT '0' COMMENT 'number of received securities today',
  `receipt_amount` decimal(23,5) DEFAULT NULL COMMENT 'total figures of received securities today',
  `queued_count` smallint NOT NULL DEFAULT '0' COMMENT 'queued transactions count',
  `queued_amount` decimal(23,5) DEFAULT NULL COMMENT 'total figures for queued transactions',
  `rejected_count` smallint NOT NULL DEFAULT '0' COMMENT 'rejected transactions count',
  `rejected_amount` decimal(23,5) DEFAULT NULL COMMENT 'total figures for rejected transactions',
  `cancelled_count` smallint NOT NULL DEFAULT '0' COMMENT 'cancelled transactions count',
  `cancelled_amount` decimal(23,5) DEFAULT NULL COMMENT 'total figures for cancelled transactions',
  `account_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'COMPOSITE PRIMARY KEY - account id of the sss account',
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `remarks` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='txn-flow_na_securities-position-stats_This table is used to maintain the statistics based on the securities transaction';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securities_position_stats_temp`
--

LOCK TABLES `securities_position_stats_temp` WRITE;
/*!40000 ALTER TABLE `securities_position_stats_temp` DISABLE KEYS */;
INSERT INTO `securities_position_stats_temp` VALUES ('06f2a364983d431c93b327d214c81fcd','MEPSUAT00024',20221223,170000.00000,170000.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,'f1094486-b936-42fa-94b8-4393fa476bc7','2022-12-23 16:36:54',NULL),('3a72e94135704838bcb2583c024783dd','BCSISTEST012',20221223,630000000.00000,630000000.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,'f1094486-b936-42fa-94b8-4393fa476bc7','2022-12-23 16:36:54',' Opening Nominal Account : Field not decimal, Closing Nominal Account : Field not decimal'),('4051287bca4346afb1e689f443da8cb4','NPTESTSCREEN',20221223,0.00000,0.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,'f1094486-b936-42fa-94b8-4393fa476bc7','2022-12-23 16:36:55',NULL),('48e2325fbf0044ba8c5c0720591859ac','NSBONDMAY002',20221223,100000.00000,100000.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,'f1094486-b936-42fa-94b8-4393fa476bc7','2022-12-23 16:36:55',NULL),('4d644f86dff945069a3e11016057d96b','BCSISMBIL001',20221223,100000000.00000,100000000.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,'f1094486-b936-42fa-94b8-4393fa476bc7','2022-12-23 16:36:53',' Opening Nominal Account : Field not decimal, Closing Nominal Account : Field not decimal'),('604e7b6108bd488ea02c825c0d7d72ca','MEPSUAT00016',20221223,242500.00000,242500.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,'f1094486-b936-42fa-94b8-4393fa476bc7','2022-12-23 16:36:54',NULL),('abc10ec9c62e4efa847caa2974b49ca8','MEPSUAT00032',20221223,17500.00000,17500.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,'f1094486-b936-42fa-94b8-4393fa476bc7','2022-12-23 16:36:54',NULL),('d8cbc45f05e04c69a5ce16beed360764','AUTOSFNEWP99',20221223,902799998000.00000,902799998000.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,0,0.00000,'f1094486-b936-42fa-94b8-4393fa476bc7','2022-12-23 16:36:53','securities_code is not found in Securities Code Table');
/*!40000 ALTER TABLE `securities_position_stats_temp` ENABLE KEYS */;
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
