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
-- Table structure for table `sss_issuer_account`
--

DROP TABLE IF EXISTS `sss_issuer_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sss_issuer_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PRIMARY_KEY',
  `issuer_id` varchar(36) NOT NULL COMMENT 'It’s issuer''s id',
  `ipa` varchar(36) NOT NULL COMMENT 'List of member with IPA flag=Yes',
  `sss_account` varchar(36) NOT NULL COMMENT 'Its type of account',
  `rtgs_account` varchar(34) NOT NULL COMMENT 'Its type of account',
  `bank_account` varchar(34) NOT NULL COMMENT 'To allow the user to input bank account with IPA. It is an account maintained in bank’s internal system.',
  PRIMARY KEY (`id`),
  KEY `fk_issuer_account_issuer_idx` (`issuer_id`),
  KEY `fk_issuer_account_member_idx` (`ipa`),
  KEY `fk_issuer_account_account_idx` (`sss_account`),
  CONSTRAINT `fk_issuer_account_account` FOREIGN KEY (`sss_account`) REFERENCES `account` (`id`),
  CONSTRAINT `fk_issuer_account_issuer` FOREIGN KEY (`issuer_id`) REFERENCES `issuer` (`id`),
  CONSTRAINT `fk_issuer_account_member` FOREIGN KEY (`ipa`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='member_screen_issuer_Account information of issuer';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss_issuer_account`
--

LOCK TABLES `sss_issuer_account` WRITE;
/*!40000 ALTER TABLE `sss_issuer_account` DISABLE KEYS */;
INSERT INTO `sss_issuer_account` VALUES (7,'8165bb25-7b3f-48dd-81b9-0c56ddc2e395','25ec94f7-7c66-458c-acde-6d8748729412','6c6ab966-8a4f-4d91-af0d-c8d15e61be63','SETT0200','111111111'),(8,'537fe131-1c32-4628-90a8-d0968cd5527c','25ec94f7-7c66-458c-acde-6d8748729412','1033be5a-eb96-4809-9ca8-1227e169af2e','26440200','22211121'),(9,'4ad71162-e15d-43be-b16f-064ac88fe7b3','25ec94f7-7c66-458c-acde-6d8748729412','1033be5a-eb96-4809-9ca8-1227e169af2e','26440200','11122211');
/*!40000 ALTER TABLE `sss_issuer_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:32:55
