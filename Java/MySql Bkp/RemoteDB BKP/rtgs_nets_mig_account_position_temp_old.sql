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
-- Table structure for table `account_position_temp_old`
--

DROP TABLE IF EXISTS `account_position_temp_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_position_temp_old` (
  `id` varchar(36) NOT NULL,
  `account_type` varchar(100) DEFAULT NULL,
  `account_id` varchar(100) DEFAULT NULL,
  `member_code` varchar(100) DEFAULT NULL,
  `member_name` varchar(100) DEFAULT NULL,
  `currency_code` varchar(100) DEFAULT NULL,
  `value_date` date DEFAULT NULL,
  `opening_balance` varchar(100) DEFAULT NULL,
  `debit` varchar(100) DEFAULT NULL,
  `credit` varchar(100) DEFAULT NULL,
  `total` varchar(100) DEFAULT NULL,
  `current_balance` varchar(100) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `accountId` int DEFAULT NULL,
  `accountType` varchar(255) DEFAULT NULL,
  `currencyCode` varchar(255) DEFAULT NULL,
  `currentBalance` varchar(255) DEFAULT NULL,
  `memberCode` varchar(255) DEFAULT NULL,
  `memberName` varchar(255) DEFAULT NULL,
  `openingBalance` varchar(255) DEFAULT NULL,
  `valueDate` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_position_temp_old`
--

LOCK TABLES `account_position_temp_old` WRITE;
/*!40000 ALTER TABLE `account_position_temp_old` DISABLE KEYS */;
INSERT INTO `account_position_temp_old` VALUES ('187','Settlement Account','74630101','058d08cc-814c-4c2a-bcde-0190375b4108','membername1','SGD','2022-03-03','4400000','500000','1500000','1000000','3400000','2022-06-15 09:48:51',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('189','Settlement Account','74630102','058d08cc-814c-4c2a-bcde-0190375b4108','membername1','SGD','2022-03-03','4400000','500000','1500000','1000000','3400000','2022-07-13 12:27:43',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `account_position_temp_old` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:32:07
