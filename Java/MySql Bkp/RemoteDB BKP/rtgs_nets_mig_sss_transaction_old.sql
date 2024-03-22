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
-- Table structure for table `sss_transaction_old`
--

DROP TABLE IF EXISTS `sss_transaction_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sss_transaction_old` (
  `id` varchar(36) NOT NULL,
  `sss_reference` varchar(100) DEFAULT NULL,
  `message_log_id` varchar(100) DEFAULT NULL,
  `securities_type` varchar(100) DEFAULT NULL,
  `transaction_type` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `settlement_date` varchar(100) DEFAULT NULL,
  `trade_date` varchar(100) DEFAULT NULL,
  `currency_code` varchar(100) DEFAULT NULL,
  `settlement_amount` varchar(100) DEFAULT NULL,
  `nominal_amount` varchar(100) DEFAULT NULL,
  `deal_price` varchar(100) DEFAULT NULL,
  `deliverer_member_id` varchar(100) DEFAULT NULL,
  `deliverer_account_id` varchar(100) DEFAULT NULL,
  `receiver_member_id` varchar(100) DEFAULT NULL,
  `receiver_account_id` varchar(100) DEFAULT NULL,
  `repo_closing_date` varchar(100) DEFAULT NULL,
  `repo_closing_settlement_amount` varchar(100) DEFAULT NULL,
  `reason_code` varchar(100) DEFAULT NULL,
  `link_transaction_reference` varchar(100) DEFAULT NULL,
  `processed_date` varchar(100) DEFAULT NULL,
  `created_date` varchar(100) DEFAULT NULL,
  `modified_date` varchar(100) DEFAULT NULL,
  `transaction_received_date` varchar(100) DEFAULT NULL,
  `sender_swift_member_code` varchar(100) DEFAULT NULL,
  `payer_swift_member_code` varchar(100) DEFAULT NULL,
  `payee_swift_member_code` varchar(100) DEFAULT NULL,
  `receiver_swift_member_code` varchar(100) DEFAULT NULL,
  `sender_member_code` varchar(100) DEFAULT NULL,
  `sender_type` varchar(100) DEFAULT NULL,
  `accrued_interest_amount` varchar(100) DEFAULT NULL,
  `match_timestamp` varchar(100) DEFAULT NULL,
  `deliverer_rtgs_account` varchar(100) DEFAULT NULL,
  `receiver_rtgs_account` varchar(100) DEFAULT NULL,
  `grid_lock_indicator` varchar(100) DEFAULT NULL,
  `rollover_count` varchar(100) DEFAULT NULL,
  `pending_cancellation_indicator` varchar(100) DEFAULT NULL,
  `receiver_rtgs_member_code` varchar(100) DEFAULT NULL,
  `deliverer_rtgs_member_code` varchar(100) DEFAULT NULL,
  `payment_type` varchar(100) DEFAULT NULL,
  `hold_indicator` varchar(100) DEFAULT NULL,
  `channel` varchar(100) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `accruedInterestAmount` varchar(255) DEFAULT NULL,
  `createdDate` varchar(255) DEFAULT NULL,
  `currencyCode` varchar(255) DEFAULT NULL,
  `dealPrice` varchar(255) DEFAULT NULL,
  `delivererAccountId` varchar(255) DEFAULT NULL,
  `delivererMemberId` varchar(255) DEFAULT NULL,
  `delivererRtgsAccount` varchar(255) DEFAULT NULL,
  `delivererRtgsMemberCode` varchar(255) DEFAULT NULL,
  `gridLockIndicator` varchar(255) DEFAULT NULL,
  `holdIndicator` varchar(255) DEFAULT NULL,
  `linkTransactionReference` varchar(255) DEFAULT NULL,
  `matchTimestamp` varchar(255) DEFAULT NULL,
  `messageLogId` varchar(255) DEFAULT NULL,
  `modifiedDate` varchar(255) DEFAULT NULL,
  `nominalAmount` varchar(255) DEFAULT NULL,
  `payeeSwiftMemberCode` varchar(255) DEFAULT NULL,
  `payerSwiftMemberCode` varchar(255) DEFAULT NULL,
  `paymentType` varchar(255) DEFAULT NULL,
  `pendingCancellationIndicator` varchar(255) DEFAULT NULL,
  `processedDate` varchar(255) DEFAULT NULL,
  `reasonCode` varchar(255) DEFAULT NULL,
  `receiverAccountId` varchar(255) DEFAULT NULL,
  `receiverMemberId` varchar(255) DEFAULT NULL,
  `receiverRtgsAccount` varchar(255) DEFAULT NULL,
  `receiverRtgsMemberCode` varchar(255) DEFAULT NULL,
  `receiverSwiftMemberCode` varchar(255) DEFAULT NULL,
  `repoClosingDate` varchar(255) DEFAULT NULL,
  `repoClosingSettlementAmount` varchar(255) DEFAULT NULL,
  `rolloverCount` varchar(255) DEFAULT NULL,
  `securitiesType` varchar(255) DEFAULT NULL,
  `senderMemberCode` varchar(255) DEFAULT NULL,
  `senderSwiftMemberCode` varchar(255) DEFAULT NULL,
  `senderType` varchar(255) DEFAULT NULL,
  `settlementAmount` varchar(255) DEFAULT NULL,
  `settlementDate` varchar(255) DEFAULT NULL,
  `sssReference` varchar(255) DEFAULT NULL,
  `tradeDate` varchar(255) DEFAULT NULL,
  `transactionReceivedDate` varchar(255) DEFAULT NULL,
  `transactionType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss_transaction_old`
--

LOCK TABLES `sss_transaction_old` WRITE;
/*!40000 ALTER TABLE `sss_transaction_old` DISABLE KEYS */;
INSERT INTO `sss_transaction_old` VALUES ('02400259-c05d-4911-9b84-d957040dd34a','S201021182250843','3356783','T-Bill','FOP','TFRC','21-Mar-2022','15-Apr-2022','SGD','4000000','5000000','2000000','MASSSGS0','00200100','OCBCSGS0','33330101','18-Mar-2022','300000','NULL','S201026151103932','21-Mar-2022','22-Mar-2022','23-Mar-2022','24-Mar-2022','11110101','77770707','44440404','66660606','002003100','Deliverer','100','28-Mar-2022','25ec94f7-7c66-458c-acde-6d8748729412','87eecb75-b264-44ed-b2a4-29ebfb55c7ee','0','0','0','25ec94f7-7c66-458c-acde-6d8748729412','43ff7ccf-9d83-406b-9074-8cdf9307d12c','Repuchasing Closing','0','SWIFT','2022-06-20 21:34:33',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('0ac29e80-217b-43e7-a1a8-a38dc02b7028','S201021182250841','3356781','T-Bill','FOP','TFRC','15-Mar-2022','10-Mar-2022','SGD','2000000','3000000','5000000','MASSSGS0','00200100','OCBCSGS0','11110101','30-Mar-2022','100000','NULL','S201026151103932','21-Mar-2022','22-Mar-2022','23-Mar-2022','24-Mar-2022','22220202','33330303','44440404','99990909','002003100','Deliverer','100','21-Mar-2022','25ec94f7-7c66-458c-acde-6d8748729412','87eecb75-b264-44ed-b2a4-29ebfb55c7ee','0','0','0','25ec94f7-7c66-458c-acde-6d8748729412','87eecb75-b264-44ed-b2a4-29ebfb55c7ee','Repuchasing Closing','0','SWIFT','2022-06-20 21:34:33',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('4d06897f-761f-44ea-9af3-ccc9e88b1448','S201021182250842','3356782','T-Bill','FOP','TFRC','20-Mar-2022','18-Mar-2022','SGD','3000000','4000000','1000000','MASSSGS0','00200100','OCBCSGS0','22220101','28-Mar-2022','200000','NULL','S201026151103932','21-Mar-2022','22-Mar-2022','23-Mar-2022','24-Mar-2022','99990909','66660606','44440404','11110101','002003100','Deliverer','100','20-Mar-2022','87eecb75-b264-44ed-b2a4-29ebfb55c7ee','25ec94f7-7c66-458c-acde-6d8748729412','0','0','0','87eecb75-b264-44ed-b2a4-29ebfb55c7ee','43ff7ccf-9d83-406b-9074-8cdf9307d12c','Repuchasing Closing','0','SWIFT','2022-06-20 21:34:33',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('727cc6aa-737f-4944-bcfe-d2b4ce321eec','S201021182250848','3356782','T-Bill','FOP','TFRC','22-Mar-2022','29-Apr-2022','SGD','5000000','6000000','9000000','MASSSGS0','00200100','OCBCSGS0','44440101','03-Mar-2022','400000','NULL','S201026151103932','21-Mar-2022','22-Mar-2022','23-Mar-2022','24-Mar-2022','55550505','33330303','66660606','77770707','002003100','Deliverer','100','23-Mar-2022','87eecb75-b264-44ed-b2a4-29ebfb55c7ee','87eecb75-b264-44ed-b2a4-29ebfb55c7ee','0','0','0','25ec94f7-7c66-458c-acde-6d8748729412','25ec94f7-7c66-458c-acde-6d8748729412','Repuchasing Closing','0','SWIFT','2022-06-20 21:34:33',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('7bb63c3e-7195-42f4-9e4c-e22231efcac0','S201021182250840','3356782','T-Bill','FOP','TFRC','24-Mar-2022','08-Apr-2022','SGD','100000','1000000','6500000','MASSSGS0','00200100','OCBCSGS0','55550101','03-Mar-2022','150000','NULL','S201026151103932','15-Mar-2022','18-Mar-2022','02-Mar-2022','05-Mar-2022','44440404','33330303','11110101','99990909','002003100','Deliverer','100','19-Mar-2022','25ec94f7-7c66-458c-acde-6d8748729412','87eecb75-b264-44ed-b2a4-29ebfb55c7ee','0','0','0','25ec94f7-7c66-458c-acde-6d8748729412','43ff7ccf-9d83-406b-9074-8cdf9307d12c','Repuchasing Closing','0','SWIFT','2022-06-20 21:34:33',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sss_transaction_old` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:32:28
