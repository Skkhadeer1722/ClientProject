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
-- Table structure for table `incoming_transaction_temp_old`
--

DROP TABLE IF EXISTS `incoming_transaction_temp_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incoming_transaction_temp_old` (
  `sss_reference` varchar(36) NOT NULL,
  `message_log_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'message ID of the message.',
  `securities_code` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'unique Securities Code of the securities in the transaction.',
  `transaction_type` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'type of transaction.',
  `status` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'current transaction status.',
  `settlement_date` int NOT NULL COMMENT 'value date of the transaction.',
  `trade_date` int NOT NULL COMMENT 'trade date of the transaction.',
  `currency_code` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'currency code of the transaction.',
  `settlement_amount` decimal(23,5) NOT NULL COMMENT 'settlement amount of the securities in the transaction.',
  `nominal_amount` decimal(23,5) NOT NULL COMMENT 'nominal amount of the securities in the transaction.',
  `deal_price` decimal(23,5) DEFAULT '0.00000' COMMENT 'Deal Price of the transaction.',
  `deliverer_member_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'deliverer member code',
  `receiver_member_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Receiver member code',
  `repo_closing_date` int DEFAULT '0' COMMENT 'repo closing date',
  `repo_closing_settlement_amount` decimal(23,5) DEFAULT NULL COMMENT 'settlement amount during repo closing',
  `matched_sss_reference` varchar(36) DEFAULT NULL,
  `sender_member_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Sender Member Code',
  `sender_reference` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '"Name of the Deliverer/Receiver message sender\r\nThis is defaulted to”-“ if Channel is “System Generated”."',
  `sender_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'To indicate `deliverer` or `receiver`',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `message_type` varchar(26) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'To allow the user to select the message type.',
  `receiver_rtgs_account` varchar(34) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Account number of the RTGS receivers account.',
  `deliverer_rtgs_account` varchar(34) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Account number of the RTGS deliverer''s account.',
  `receiver_rtgs_member_code` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Member code of the RTGS receiver.',
  `deliverer_rtgs_member_code` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Member code of the RTGS deliverer.',
  `hold_indicator` tinyint NOT NULL,
  `payment_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Payment type of the transaction',
  `deliverer_member_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Member ID of the Deliverer of the securities in the transactions.',
  `receiver_member_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Member ID of the Receiver of the securities in the transactions.',
  `receiver_account_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Account ID of the Receiver’s securities holding  in the transactions.',
  `deliverer_account_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Account ID of the Deliverer’s securities holding  in the transactions',
  `sender_member_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Member ID of the Deliverer/Receiver message sender',
  `pdm_indicator` tinyint DEFAULT NULL COMMENT 'This column is used to identify the duplicate message',
  `pending_cancellation_indicator` tinyint(1) DEFAULT NULL COMMENT 'This indicator is used in matching of cancellation request',
  `deliverer_account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SSS Deliverer account number',
  `receiver_account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SSS Receiver account number',
  `msg_received_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `submitted_clo_settle_amount` decimal(23,5) DEFAULT NULL COMMENT 'Closing settlement amount submitted value',
  `deliverer_beneficiary_account` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Deliverer beneficiary account',
  `accured_interest_amount` decimal(23,5) DEFAULT '0.00000',
  `sssReference` varchar(255) NOT NULL,
  `accuredInterestAmount` double NOT NULL,
  `createdDate` datetime(6) DEFAULT NULL,
  `currencyCode` varchar(255) DEFAULT NULL,
  `dealPrice` double NOT NULL,
  `delivererAccountId` varchar(255) DEFAULT NULL,
  `delivererAccountNo` varchar(255) DEFAULT NULL,
  `delivererBeneficiaryAccount` varchar(255) DEFAULT NULL,
  `delivererMemberCode` varchar(255) DEFAULT NULL,
  `delivererMemberId` varchar(255) DEFAULT NULL,
  `delivererRtgsAccount` varchar(255) DEFAULT NULL,
  `delivererRtgsMemberCode` varchar(255) DEFAULT NULL,
  `holdIndicator` tinyint NOT NULL,
  `matchedSssReference` varchar(255) DEFAULT NULL,
  `messageLogId` varchar(255) DEFAULT NULL,
  `messageType` varchar(255) DEFAULT NULL,
  `modifiedDate` datetime(6) DEFAULT NULL,
  `msgReceivedTimestamp` datetime(6) DEFAULT NULL,
  `nominalAmount` double NOT NULL,
  `paymentType` varchar(255) DEFAULT NULL,
  `pdmIndicator` tinyint NOT NULL,
  `pendingCancellationIndicator` tinyint NOT NULL,
  `receiverAccountId` varchar(255) DEFAULT NULL,
  `receiverAccountNo` varchar(255) DEFAULT NULL,
  `receiverMemberCode` varchar(255) DEFAULT NULL,
  `receiverMemberId` varchar(255) DEFAULT NULL,
  `receiverRtgsAccount` varchar(255) DEFAULT NULL,
  `receiverRtgsMemberCode` varchar(255) DEFAULT NULL,
  `repoClosingDate` int NOT NULL,
  `repoClosingSettlementAmount` double NOT NULL,
  `securitiesCode` varchar(255) DEFAULT NULL,
  `senderMemberCode` varchar(255) DEFAULT NULL,
  `senderMemberId` varchar(255) DEFAULT NULL,
  `senderReference` varchar(255) DEFAULT NULL,
  `senderType` varchar(255) DEFAULT NULL,
  `settlementAmount` double NOT NULL,
  `settlementDate` int NOT NULL,
  `submittedCloSettleAmount` double NOT NULL,
  `tradeDate` int NOT NULL,
  `transactionType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sss_reference`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='txn-flow_na_incoming-transaction_This table is used to save all the sss incoming transactions for settlement';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incoming_transaction_temp_old`
--

LOCK TABLES `incoming_transaction_temp_old` WRITE;
/*!40000 ALTER TABLE `incoming_transaction_temp_old` DISABLE KEYS */;
INSERT INTO `incoming_transaction_temp_old` VALUES ('9f9fdf8b-2009-48e0-b65a-300310e608bd','5','SG2020446611','TRAD','REJT',20220309,20220309,'SGD',1111111.12345,1000.00000,10.00000,'ANZSGLTD','SCBLSG22',0,3333.00000,'NULL','ANZSGLTD','TRAD-Send1649315053','DELIVERER','2022-04-28 11:02:08','2022-04-28 11:02:09','sese.023.001.09','NULL','NULL',NULL,'NULL',0,'FREE','NULL','NULL','NULL','NULL','NULL',0,0,'12245789','SSS47222','2022-04-28 11:02:07',3000.00000,'NULL',4000.00000,'',0,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,0,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,0,0,0,0,NULL),('a402fee2-1951-428c-8d54-6e7aec2ba6d7','5','SG2020446611','TRAD','REJT',20220309,20220309,'SGD',1111111.12345,1000.00000,10.00000,'ANZSGLTD','SCBLSG22',0,3333.00000,'NULL','ANZSGLTD','TRAD-Send1649315053','DELIVERER','2022-04-28 11:02:08','2022-04-28 11:02:09','sese.023.001.09','NULL','NULL',NULL,'NULL',0,'FREE','NULL','NULL','NULL','NULL','NULL',0,0,'12245789','SSS47222','2022-04-28 11:02:07',3000.00000,'NULL',4000.00000,'',0,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,0,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,0,0,0,0,NULL),('ec3e2da2-f1b1-48cf-8284-12273b8c2a1f','5','SG2020446611','TRAD','REJT',20220309,20220309,'SGD',1111111.12345,1000.00000,10.00000,'ANZSGLTD','SCBLSG22',0,3333.00000,'NULL','ANZSGLTD','TRAD-Send1649315053','DELIVERER','2022-04-28 11:02:08','2022-04-28 11:02:09','sese.023.001.09','NULL','NULL','NULL','NULL',0,'FREE','NULL','NULL','NULL','NULL','NULL',0,0,'12245789','SSS47222','2022-04-28 11:02:07',3000.00000,'NULL',4000.00000,'',0,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,0,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,0,0,0,0,NULL);
/*!40000 ALTER TABLE `incoming_transaction_temp_old` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:32:15