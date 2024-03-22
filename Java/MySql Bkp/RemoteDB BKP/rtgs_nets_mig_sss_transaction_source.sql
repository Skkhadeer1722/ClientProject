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
-- Table structure for table `sss_transaction_source`
--

DROP TABLE IF EXISTS `sss_transaction_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sss_transaction_source` (
  `sss_reference` varchar(36) NOT NULL,
  `message_log_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'message ID of the message.',
  `securities_code` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'unique Securities Code of the securities in the transaction.',
  `transaction_type` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'type of transaction.',
  `status` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'current transaction status.',
  `settlement_date` int NOT NULL COMMENT 'value date of the transaction.',
  `trade_date` int NOT NULL COMMENT 'trade date of the transaction.',
  `currency_code` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'currency code of the transaction.',
  `settlement_amount` decimal(23,5) DEFAULT NULL COMMENT 'settlement amount of the securities in the transaction.',
  `nominal_amount` decimal(23,5) NOT NULL COMMENT 'nominal amount of the securities in the transaction.',
  `deal_price` decimal(23,5) DEFAULT '0.00000' COMMENT 'Deal Price of the transaction.',
  `deliverer_member_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'deliverer member code',
  `receiver_member_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'receiver member code',
  `repo_closing_date` int DEFAULT '0' COMMENT 'closing date for RPO transactions',
  `repo_closing_settlement_amount` decimal(23,5) DEFAULT NULL COMMENT 'settlement amount during repo closing',
  `reason_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'return code of the status (FS - ''return code'')',
  `transaction_reference` varchar(36) DEFAULT NULL,
  `processed_date` int DEFAULT '0' COMMENT 'date that the transaction is being processed',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sender_member_swift_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'sender member swift BIC code',
  `payer_member_swift_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'payer member swift BIC code',
  `payee_member_swift_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'payee member swift BIC code',
  `receiver_member_swift_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'receiver member swift BIC code',
  `sender_member_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `sender_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'sender type',
  `message_type` varchar(26) DEFAULT NULL,
  `receiver_rtgs_account` varchar(34) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `deliverer_rtgs_account` varchar(34) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `receiver_rtgs_member_code` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Member code of the RTGS receiver.',
  `deliverer_rtgs_member_code` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Member code of the RTGS deliverer.',
  `hold_indicator` tinyint DEFAULT NULL COMMENT 'Hold indicator to hold the settlement in case of ERF closing leg transactions',
  `payment_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Payment type of the transaction',
  `grid_lock_indicator` tinyint DEFAULT NULL,
  `rollover_count` int DEFAULT '0' COMMENT 'Number of times a transaction has been rolled over',
  `pending_cancellation_indicator` tinyint DEFAULT NULL COMMENT 'To indicate that there is a cancallation request',
  `deliverer_member_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Member ID of the Deliverer of the securities in the transactions.',
  `receiver_member_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Member ID of the Receiver of the securities in the transactions.',
  `receiver_account_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Account ID of the Receiver’s securities holding  in the transactions.',
  `deliverer_account_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Account ID of the Deliverer’s securities holding  in the transactions',
  `sender_member_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Member ID of the Deliverer/Receiver message sender',
  `pdm_indicator` tinyint DEFAULT NULL,
  `erf_reference` varchar(36) DEFAULT NULL,
  `deliverer_account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'deliverer account no',
  `receiver_account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'receiver account no',
  `msg_received_timestamp` timestamp NULL DEFAULT NULL COMMENT '"date and time of the message created.\r\nThis is defaulted to”-“ if Channel is “System Generated”."',
  `receiver_sender_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Receiver sender member id',
  `deliverer_sender_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'deliverer sender member id',
  `deliverer_onbehalf` tinyint DEFAULT '0' COMMENT 'indicator to know whether it is deliverer onbehalf of or not',
  `receiver_onbehalf` tinyint DEFAULT '0' COMMENT 'indicator to know whether it is receiver onbehalf of or not',
  `process_flag` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Indicator to know the current process status of transaction',
  `deliverer_beneficiary_account` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Deliverer beneficiary account',
  `underlying_id` varchar(42) DEFAULT NULL,
  `balance_type` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `accured_interest_amount` decimal(23,5) DEFAULT '0.00000',
  `deliverer_channel` varchar(36) DEFAULT NULL COMMENT 'Channel of the deliverer',
  `receiver_channel` varchar(36) DEFAULT NULL COMMENT 'Receiver Channel',
  `repo_rate` decimal(23,10) DEFAULT '0.0000000000' COMMENT 'Repo Rate of the transaction.',
  `haircut_rate` decimal(23,5) DEFAULT '0.00000' COMMENT 'Haircut Rate of the transaction.',
  `original_account_no` varchar(35) DEFAULT NULL COMMENT 'Original Account Number',
  PRIMARY KEY (`sss_reference`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='txn-flow_na_transaction_This table is used to save the transactions which is processed in sss';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss_transaction_source`
--

LOCK TABLES `sss_transaction_source` WRITE;
/*!40000 ALTER TABLE `sss_transaction_source` DISABLE KEYS */;
INSERT INTO `sss_transaction_source` VALUES ('ST2022060210472597906325',NULL,'SGSITSE04051','RPC',NULL,20220601,20220601,'SGD',100.00000,4000.00000,10.00000,'ZYSASGS0','MASGSGSC',NULL,NULL,NULL,'transaction001',NULL,'2022-06-01 21:17:26','2022-06-01 21:17:29',NULL,NULL,NULL,NULL,NULL,NULL,'sese.023.001.09',NULL,NULL,'receiver123','deliverer123',NULL,'APMT',NULL,NULL,NULL,'54b0e908-d207-4d57-b719-0cd6a9099741','293e05dd-7914-43f7-8d0a-95c805c22a46','293e05dd-7914-43f7-8d0a-95c805c22a46','54b0e908-d207-4d57-b719-0cd6a9099741',NULL,NULL,NULL,'CTR00002','ERF',NULL,'0','0',NULL,NULL,NULL,NULL,NULL,NULL,1.00000,NULL,NULL,100.0000000000,55.45000,NULL),('ST2022060910450115819557',NULL,'SGCANCELSHA1-','RPC',NULL,20220909,20220909,'SG/',200.00000,70000.00000,20.00000,'MASGSGSC','ZYSFSGS0',NULL,NULL,NULL,'transaction002',NULL,'2022-06-08 21:15:01','2022-06-08 21:15:12',NULL,NULL,NULL,NULL,NULL,NULL,'sese.023.001.09',NULL,NULL,'receiver456','deliverer456',NULL,'FREE',NULL,NULL,NULL,'293e05dd-7914-43f7-8d0a-95c805c22a46','f341e16b-0afb-4607-b72f-0e5adf88a911','54b0e908-d207-4d57-b719-0cd6a9099741','293e05dd-7914-43f7-8d0a-95c805c22a46',NULL,NULL,NULL,'ALO00001','MRF',NULL,'0','0',NULL,NULL,NULL,NULL,NULL,NULL,22.90000,NULL,NULL,200.0000000000,75.00000,NULL),('ST2022061005005110298446',NULL,'SGCANCELSHA7','RPC',NULL,20220310,20220310,'-GD',0.00000,80000.00000,3000.00000,'MASGSGSG','ZYSASGS0',NULL,NULL,NULL,'transaction003',NULL,'2022-06-09 15:30:51','2022-06-10 05:00:02',NULL,NULL,NULL,NULL,NULL,NULL,'sese.023.001.09',NULL,NULL,'receiver789','deliverer789',NULL,'APMT',NULL,NULL,NULL,'cc4812ad-5c87-4729-a093-858687bd1fa4','096b44f4-1b0f-4a69-8a2f-727a80bd4de8','f341e16b-0afb-4607-b72f-0e5adf88a911','54b0e908-d207-4d57-b719-0cd6a9099741',NULL,NULL,NULL,'MAS00001','ERF',NULL,'0','0',NULL,NULL,NULL,NULL,NULL,NULL,40.00000,NULL,NULL,15.2200000000,20.35000,NULL);
/*!40000 ALTER TABLE `sss_transaction_source` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:31:53
