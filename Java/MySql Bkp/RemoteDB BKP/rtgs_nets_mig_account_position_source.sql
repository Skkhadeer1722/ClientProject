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
-- Table structure for table `account_position_source`
--

DROP TABLE IF EXISTS `account_position_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_position_source` (
  `id` varchar(36) DEFAULT NULL,
  `account_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'account id',
  `currency_code` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'currency code',
  `member_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'member id',
  `current_account_balance` varchar(36) DEFAULT NULL COMMENT 'current or closing balance of the account.',
  `opening_account_balance` varchar(36) DEFAULT NULL COMMENT 'opening balance of the account for the selected value date',
  `available_balance` varchar(36) DEFAULT '0.00000' COMMENT 'available balance',
  `queue_count` varchar(36) DEFAULT NULL COMMENT 'counts of transactions those were in the queue',
  `queue_amount` varchar(36) DEFAULT NULL COMMENT 'amounts of transactions those were in the queue',
  `settled_payments_count` varchar(36) DEFAULT NULL COMMENT 'counts of payments due to transactions those were settled today.',
  `settled_payments_amount` varchar(36) DEFAULT NULL COMMENT 'amounts of payments due to transactions those were settled today.',
  `settled_receipts_count` varchar(36) DEFAULT NULL COMMENT 'counts of receipts due to transactions those were settled today',
  `settled_receipts_amount` varchar(36) DEFAULT NULL COMMENT 'amounts of receipts due to transactions those were settled today',
  `created_date` varchar(36) DEFAULT NULL,
  `modified_date` varchar(36) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_position_source`
--

LOCK TABLES `account_position_source` WRITE;
/*!40000 ALTER TABLE `account_position_source` DISABLE KEYS */;
INSERT INTO `account_position_source` VALUES ('cd50b5a9-85eb-47bb-ac83-44003f87bcc6','12347','SGD','MASGSGSG','500.00','500.00000','500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'2022-06-27 19:00:05',NULL),('81e3a46b-1c0d-45b2-84b0-ca8baad055a2','12348','SGD','MASGSGSG','1000.00','1500.00000','500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'2021-06-15 09:00:05',NULL),('6587a6c2-b4b4-4f81-9733-817fcbf94d35','12345','SG-','MASGSGSG','500.00','500.00000','500.00000',NULL,NULL,NULL,NULL,NULL,NULL,'2022-06-27 11:40:41',NULL);
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

-- Dump completed on 2023-01-03 11:32:02
