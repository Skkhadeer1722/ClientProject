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
-- Table structure for table `account_temp_old`
--

DROP TABLE IF EXISTS `account_temp_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_temp_old` (
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
-- Dumping data for table `account_temp_old`
--

LOCK TABLES `account_temp_old` WRITE;
/*!40000 ALTER TABLE `account_temp_old` DISABLE KEYS */;
INSERT INTO `account_temp_old` VALUES ('14617002-7347-4c45-9e41-5a3d7056e378','74630101','Settlement Account','058d08cc-814c-4c2a-bcde-0190375b4108','SETT','1','2','DEUTSGSG','Active','SGD',100,'1','2022-03-03','2022-03-05','gl_1124','100','gl_1124','No','action','DELETED','0bdea729-4878-40b3-be91-7316aff49c86','2022-06-01 09:49:14','4d3921c4-9a6e-448d-b312-d282863d73d5','2022-06-01 09:49:55','2022-06-10 09:38:05','2022-04-27 18:30:00','approved','reference-1','reference-1','4d3921c4-9a6e-448d-b312-d282863d73d5','2022-07-01 14:34:03','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('4cc57921-ef0e-4be8-adaf-d307ea7ed339','74630102','Settlement Account','a4408532-70cc-4269-bba0-c65b8a35ff9c','SETT','2','3','DEUTSGSG','Closed','SGD',200,'2','2022-03-10','2022-03-12','gl_1124','100','gl_1124','No','action1','DELETED1','4d3921c4-9a6e-448d-b312-d282863d73d5','2022-06-01 09:49:14','4d3921c4-9a6e-448d-b312-d282863d73d5','2022-06-10 09:49:55','2022-06-12 09:38:05','2022-05-26 18:30:00','approved','reference-2','reference-2','0bdea729-4878-40b3-be91-7316aff49c86','2022-07-01 14:34:03','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('af5529a5-1edc-435d-a784-5f56a675af5f','74630103','Settlement Account','058d08cc-814c-4c2a-bcde-0190375b4108','SETT','3','4','DEUTSGSG','Suspended','SGD',300,'3','2022-04-15','2022-04-20','gl_1124','100','gl_1124','No','action2','DELETED2','0bdea729-4878-40b3-be91-7316aff49c86','2022-06-01 09:49:14','4d3921c4-9a6e-448d-b312-d282863d73d5','2022-07-01 09:49:55','2022-07-14 09:38:05','2022-05-29 18:30:00','approved','reference-3','reference-3','4d3921c4-9a6e-448d-b312-d282863d73d5','2022-07-01 14:34:03','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('decf37e9-4f32-4a57-9d09-f118aa9a012d','74630104','Settlement Account','058d08cc-814c-4c2a-bcde-0190375b4108','SETT','4','5','DEUTSGSG','Active','SGD',400,'4','2022-04-20','2022-04-25','gl_1124','100','gl_1124','No','action3','DELETED3','4d3921c4-9a6e-448d-b312-d282863d73d5','2022-06-01 09:49:14','4d3921c4-9a6e-448d-b312-d282863d73d5','2022-07-08 09:49:55','2022-07-18 09:38:05','2022-06-12 18:30:00','approved','reference-4','reference-4','0bdea729-4878-40b3-be91-7316aff49c86','2022-07-01 14:34:03','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `account_temp_old` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:33:23
