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
-- Table structure for table `transaction_source`
--

DROP TABLE IF EXISTS `transaction_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_source` (
  `id` varchar(36) DEFAULT NULL,
  `sss_reference` varchar(50) DEFAULT NULL,
  `message_log_id` varchar(50) DEFAULT NULL COMMENT 'message ID of the message.',
  `securities_code` varchar(50) DEFAULT NULL COMMENT 'unique Securities Code of the securities in the transaction.',
  `transaction_type` varchar(50) DEFAULT NULL COMMENT 'type of transaction.',
  `status` varchar(50) DEFAULT NULL COMMENT 'current transaction status.',
  `settlement_date` varchar(50) DEFAULT NULL COMMENT 'value date of the transaction.',
  `trade_date` varchar(50) DEFAULT NULL COMMENT 'trade date of the transaction.',
  `currency_code` varchar(50) DEFAULT NULL COMMENT 'currency code of the transaction.',
  `settlement_amount` varchar(50) DEFAULT NULL COMMENT 'settlement amount of the securities in the transaction.',
  `nominal_amount` varchar(50) DEFAULT NULL COMMENT 'nominal amount of the securities in the transaction.',
  `deal_price` varchar(50) DEFAULT NULL COMMENT 'Deal Price of the transaction.',
  `deliverer_member_code` varchar(50) DEFAULT NULL COMMENT 'deliverer member code',
  `receiver_member_code` varchar(50) DEFAULT NULL COMMENT 'receiver member code',
  `repo_closing_date` varchar(50) DEFAULT NULL COMMENT 'closing date for RPO transactions',
  `repo_closing_settlement_amount` varchar(50) DEFAULT NULL COMMENT 'settlement amount during repo closing',
  `reason_code` varchar(50) DEFAULT NULL COMMENT 'return code of the status (FS - ''return code'')',
  `transaction_reference` varchar(50) DEFAULT NULL,
  `processed_date` varchar(50) DEFAULT NULL COMMENT 'date that the transaction is being processed',
  `created_date` varchar(50) DEFAULT NULL,
  `modified_date` varchar(50) DEFAULT NULL,
  `sender_member_swift_code` varchar(50) DEFAULT NULL COMMENT 'sender member swift BIC code',
  `payer_member_swift_code` varchar(50) DEFAULT NULL COMMENT 'payer member swift BIC code',
  `payee_member_swift_code` varchar(50) DEFAULT NULL COMMENT 'payee member swift BIC code',
  `receiver_member_swift_code` varchar(50) DEFAULT NULL COMMENT 'receiver member swift BIC code',
  `sender_member_code` varchar(50) DEFAULT NULL,
  `sender_type` varchar(50) DEFAULT NULL COMMENT 'sender type',
  `message_type` varchar(50) DEFAULT NULL,
  `receiver_rtgs_account` varchar(50) DEFAULT NULL,
  `deliverer_rtgs_account` varchar(50) DEFAULT NULL,
  `receiver_rtgs_member_code` varchar(50) DEFAULT NULL COMMENT 'Member code of the RTGS receiver.',
  `deliverer_rtgs_member_code` varchar(50) DEFAULT NULL COMMENT 'Member code of the RTGS deliverer.',
  `hold_indicator` varchar(50) DEFAULT NULL COMMENT 'Hold indicator to hold the settlement in case of ERF closing leg transactions',
  `payment_type` varchar(50) DEFAULT NULL COMMENT 'Payment type of the transaction',
  `grid_lock_indicator` varchar(50) DEFAULT NULL,
  `rollover_count` varchar(50) DEFAULT NULL COMMENT 'Number of times a transaction has been rolled over',
  `pending_cancellation_indicator` varchar(50) DEFAULT NULL COMMENT 'To indicate that there is a cancallation request',
  `deliverer_member_id` varchar(50) DEFAULT NULL COMMENT 'Member ID of the Deliverer of the securities in the transactions.',
  `receiver_member_id` varchar(50) DEFAULT NULL COMMENT 'Member ID of the Receiver of the securities in the transactions.',
  `receiver_account_id` varchar(50) DEFAULT NULL COMMENT 'Account ID of the Receiver’s securities holding  in the transactions.',
  `deliverer_account_id` varchar(50) DEFAULT NULL COMMENT 'Account ID of the Deliverer’s securities holding  in the transactions',
  `sender_member_id` varchar(50) DEFAULT NULL COMMENT 'Member ID of the Deliverer/Receiver message sender',
  `pdm_indicator` varchar(50) DEFAULT NULL,
  `erf_reference` varchar(50) DEFAULT NULL,
  `deliverer_account_no` varchar(50) DEFAULT NULL COMMENT 'deliverer account no',
  `receiver_account_no` varchar(50) DEFAULT NULL COMMENT 'receiver account no',
  `msg_received_timestamp` varchar(50) DEFAULT NULL COMMENT '"date and time of the message created.\r\nThis is defaulted to”-“ if Channel is “System Generated”."',
  `receiver_sender_id` varchar(50) DEFAULT NULL COMMENT 'Receiver sender member id',
  `deliverer_sender_id` varchar(50) DEFAULT NULL COMMENT 'deliverer sender member id',
  `deliverer_onbehalf` varchar(50) DEFAULT NULL COMMENT 'indicator to know whether it is deliverer onbehalf of or not',
  `receiver_onbehalf` varchar(50) DEFAULT NULL COMMENT 'indicator to know whether it is receiver onbehalf of or not',
  `process_flag` varchar(50) DEFAULT NULL COMMENT 'Indicator to know the current process status of transaction',
  `deliverer_beneficiary_account` varchar(50) DEFAULT NULL COMMENT 'Deliverer beneficiary account',
  `underlying_id` varchar(50) DEFAULT NULL,
  `balance_type` varchar(50) DEFAULT NULL,
  `accured_interest_amount` varchar(50) DEFAULT NULL,
  `deliverer_channel` varchar(50) DEFAULT NULL,
  `receiver_channel` varchar(50) DEFAULT NULL COMMENT 'Receiver Channel',
  `repo_rate` varchar(50) DEFAULT NULL COMMENT 'Repo Rate of the transaction.',
  `haircut_rate` varchar(50) DEFAULT NULL COMMENT 'Haircut Rate of the transaction.',
  `original_account_no` varchar(50) DEFAULT NULL COMMENT 'Original Account Number',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(1000) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='txn-flow_na_transaction_This table is used to save the transactions which is processed in sss';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_source`
--

LOCK TABLES `transaction_source` WRITE;
/*!40000 ALTER TABLE `transaction_source` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_source` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-09  9:20:09
