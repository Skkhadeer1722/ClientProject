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
-- Table structure for table `incoming_transaction_temp`
--

DROP TABLE IF EXISTS `incoming_transaction_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incoming_transaction_temp` (
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
  `status` varchar(255) DEFAULT NULL,
  `submittedCloSettleAmount` double NOT NULL,
  `tradeDate` int NOT NULL,
  `transactionType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sssReference`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incoming_transaction_temp`
--

LOCK TABLES `incoming_transaction_temp` WRITE;
/*!40000 ALTER TABLE `incoming_transaction_temp` DISABLE KEYS */;
/*!40000 ALTER TABLE `incoming_transaction_temp` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:32:57
