-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 103.177.224.100    Database: rtgs_nets_mig_new
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
-- Table structure for table `account_position_source`
--

DROP TABLE IF EXISTS `account_position_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_position_source` (
  `id` varchar(36) DEFAULT NULL,
  `account_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'account id',
  `currency_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'currency code',
  `member_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'member id',
  `current_account_balance` varchar(50) DEFAULT NULL COMMENT 'current or closing balance of the account.',
  `opening_account_balance` varchar(50) DEFAULT NULL COMMENT 'opening balance of the account for the selected value date',
  `available_balance` varchar(50) DEFAULT '0.00000' COMMENT 'available balance',
  `queue_count` varchar(50) DEFAULT NULL COMMENT 'counts of transactions those were in the queue',
  `queue_amount` varchar(50) DEFAULT NULL COMMENT 'amounts of transactions those were in the queue',
  `settled_payments_count` varchar(50) DEFAULT NULL COMMENT 'counts of payments due to transactions those were settled today.',
  `settled_payments_amount` varchar(50) DEFAULT NULL COMMENT 'amounts of payments due to transactions those were settled today.',
  `settled_receipts_count` varchar(50) DEFAULT NULL COMMENT 'counts of receipts due to transactions those were settled today',
  `settled_receipts_amount` varchar(50) DEFAULT NULL COMMENT 'amounts of receipts due to transactions those were settled today',
  `created_date` varchar(50) DEFAULT NULL,
  `modified_date` varchar(50) DEFAULT NULL,
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(1000) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_position_source`
--

LOCK TABLES `account_position_source` WRITE;
/*!40000 ALTER TABLE `account_position_source` DISABLE KEYS */;
INSERT INTO `account_position_source` VALUES ('1949bd4f7b2b453fb8e30836fd81f924','12347','SGD','MASGSGSG','500.00','500.00','500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'2022-06-27 19:00:05',NULL,1,NULL,'2023-01-03 11:24:39'),('508cd60d46234b59ac120290abc68cc6','12348','SGD','MASGSGSG','1000.00','1500.00000','500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'2021-06-15 09:34:23',NULL,2,NULL,'2023-01-03 11:24:39'),('2d165061b5fd4ea09b1f0713ed00ad0d','12348','SGD','MASGSGSG','500.00','500.0000','500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'2022-06-27 11:34:54',NULL,3,NULL,'2023-01-03 11:24:39'),('3527e32ac37f498791578b2c227bc869','12349','SGD','MASGSGSG','-500.00','500.0000','500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'2022-06-27 11:34:54',NULL,4,' Current Account Balance : Field not decimal','2023-01-03 11:24:39'),('9a099f7e69494cb6bab2a463bb27b75e','12350','SGD','MASGSGSG','500.00','-500.0000','500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'2022-06-27 11:34:54',NULL,5,' Opening Balance  : Field not decimal','2023-01-03 11:24:39'),('c5832f2788064e458e6a4bad5aa861d4','12350','SGD','MASGSGSG','500.00','500.0000','-500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'2022-06-27 11:34:54',NULL,6,' Available Balance  : Field not decimal','2023-01-03 11:24:40'),('4cd574c538eb43108650703d3d519ffe','12351','SGD','MASGSGSG','500.00','500.0000','-500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'2022-06-27',NULL,7,' Available Balance  : Field not decimal,Invalid Created Date','2023-01-03 11:24:40'),('c1084d1d562547de8a8073cf1d2d8a2c','12352','SGD','MASGSGSG','500.00','500.0000','500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'27/06/2022 11:34:54',NULL,8,'Invalid Created Date','2023-01-03 11:24:40'),('a89d021e0d06469ea75f6df62dde7dc4','12353','SG_','MASGSGSG','500.00','500.0000','500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'2022-06-27 11:34:54',NULL,9,' Currency Code : Field contains not allowed currency code, Currency Code : Field contains not allowed currency code','2023-01-03 11:24:40'),('6b93b11a04b2436c859396bb34d96b0b','12354','SGD','MASGSGS@','500.00','500.0000','500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'2022-06-27 11:34:54',NULL,10,' Member Code : Field contains not allowed special characters','2023-01-03 11:24:40'),('db28a76a12c149379026cf8a9fbe6132','12355','SGD','MASGSGSG','500.00','500.0000','500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'DATE',NULL,11,'Invalid Created Date','2023-01-03 11:24:40'),('d1b098274b2c40ed99956ae99dccccb4','12356','SGD','MASGSGSG','TEST','500.00','500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'2022-06-27 19:00:05',NULL,12,' Current Account Balance : Field not decimal','2023-01-03 11:24:40'),('2313c92680ff4e9c8ed1e20193a693fa','12357','SGD','MASGSGSG','500.00','TEST','500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'2022-06-27 19:00:05',NULL,13,' Opening Balance  : Field not decimal','2023-01-03 11:24:40'),('701785a117b24c9596b59aff6a73f2b3','12358','SGD','MASGSGSG','500.00','500.00','TEST',NULL,NULL,NULL,NULL,NULL,NULL,'2022-06-27 19:00:05',NULL,14,' Available Balance  : Field not decimal','2023-01-03 11:24:40');
/*!40000 ALTER TABLE `account_position_source` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:34:36
