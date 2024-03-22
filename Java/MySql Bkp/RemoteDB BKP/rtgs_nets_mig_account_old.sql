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
-- Table structure for table `account_old`
--

DROP TABLE IF EXISTS `account_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_old` (
  `id` varchar(36) NOT NULL,
  `account_number` varchar(100) NOT NULL,
  `description` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `member_id` varchar(36) NOT NULL,
  `account_type` varchar(100) DEFAULT NULL,
  `main_account_indicator` varchar(100) DEFAULT NULL,
  `default_main_account` varchar(100) DEFAULT NULL,
  `statement_delivery_bic` varchar(100) DEFAULT NULL,
  `account_status` varchar(100) DEFAULT NULL,
  `currency_code` varchar(100) DEFAULT NULL,
  `minimum_balance` bigint DEFAULT NULL,
  `end_of_day_statement` varchar(36) DEFAULT NULL,
  `value_date` date DEFAULT NULL,
  `activation_date` date DEFAULT NULL,
  `gl_category` varchar(100) DEFAULT NULL,
  `cost_centre` varchar(100) DEFAULT NULL,
  `gl_account_number` varchar(100) DEFAULT NULL,
  `statement_interval` varchar(100) DEFAULT NULL,
  `action` varchar(30) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `modified_by` varchar(36) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  `approved_by` varchar(36) DEFAULT NULL,
  `approved_date` timestamp NULL DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `effective_date` timestamp NULL DEFAULT NULL,
  `approval_remark` varchar(140) DEFAULT NULL,
  `payer_reference` varchar(100) DEFAULT NULL,
  `related_reference` varchar(100) DEFAULT NULL,
  `counterparty_id` varchar(100) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `accountNumber` varchar(255) NOT NULL,
  `accountStatus` varchar(255) DEFAULT NULL,
  `accountType` varchar(255) DEFAULT NULL,
  `activationDate` datetime(6) DEFAULT NULL,
  `costCentre` varchar(255) DEFAULT NULL,
  `currencyCode` varchar(255) DEFAULT NULL,
  `defaultMainAccount` varchar(255) DEFAULT NULL,
  `endOfDayStatement` varchar(255) DEFAULT NULL,
  `glAccountNumber` varchar(255) DEFAULT NULL,
  `glCategory` varchar(255) DEFAULT NULL,
  `mainAccountIndicator` varchar(255) DEFAULT NULL,
  `memberId` varchar(255) DEFAULT NULL,
  `minimumBalance` bigint DEFAULT NULL,
  `statementDeliveryBic` varchar(255) DEFAULT NULL,
  `statementInterval` varchar(255) DEFAULT NULL,
  `valueDate` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_old`
--

LOCK TABLES `account_old` WRITE;
/*!40000 ALTER TABLE `account_old` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_old` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:33:20
