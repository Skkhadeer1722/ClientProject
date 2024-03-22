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
-- Table structure for table `sss_account_old`
--

DROP TABLE IF EXISTS `sss_account_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sss_account_old` (
  `id` varchar(36) NOT NULL,
  `account_id` varchar(100) DEFAULT NULL,
  `member_code` varchar(100) DEFAULT NULL,
  `custody_account_type_id` varchar(100) DEFAULT NULL,
  `account_category` varchar(100) DEFAULT NULL,
  `investor_individual_id` varchar(100) DEFAULT NULL,
  `investor_name` varchar(100) DEFAULT NULL,
  `account_status` varchar(100) DEFAULT NULL,
  `tax_profile_id` varchar(100) DEFAULT NULL,
  `statement_delivery_bic` varchar(100) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `accountCategory` varchar(255) DEFAULT NULL,
  `accountId` varchar(255) DEFAULT NULL,
  `accountStatus` varchar(255) DEFAULT NULL,
  `custodyAccountTypeId` varchar(255) DEFAULT NULL,
  `investorIndividualId` varchar(255) DEFAULT NULL,
  `investorName` varchar(255) DEFAULT NULL,
  `memberCode` varchar(255) DEFAULT NULL,
  `statementDeliveryBic` varchar(255) DEFAULT NULL,
  `taxProfileId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss_account_old`
--

LOCK TABLES `sss_account_old` WRITE;
/*!40000 ALTER TABLE `sss_account_old` DISABLE KEYS */;
INSERT INTO `sss_account_old` VALUES ('2cdabf92-9571-45ab-9ac0-9e0b734417c3','01011111','BANKASGS','25ec94f7-7c66-458c-acde-6d8748729412','AccountCat','NULL','NULL','Active','Tax101','BANKASGS','2022-06-10 16:56:42',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('364c2878-449d-4971-9c69-16fe2d5d7055','01011111','BANKASGS','25ec94f7-7c66-458c-acde-6d8748729412','AccountCat','NULL','NULL','Active','Tax101','BANKASGS','2022-06-10 16:56:42',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('7cc4fb84-e1e8-44ff-9258-fa76160be115','01011111','BANKASGS','25ec94f7-7c66-458c-acde-6d8748729412','AccountCat','NULL','NULL','Active','Tax101','BANKASGS','2022-06-10 16:56:42',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('9a260be5-7717-4dbc-a396-7f647a1174ed','01011111','BANKASGS','25ec94f7-7c66-458c-acde-6d8748729412','AccountCat','NULL','NULL','Active','Tax101','BANKASGS','2022-06-10 16:56:42',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('d6338e80-972e-48c2-8d9b-811faa29fcb6','01011111','BANKASGS','25ec94f7-7c66-458c-acde-6d8748729412','AccountCat','NULL','NULL','Active','Tax101','BANKASGS','2022-06-10 16:56:42',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('d7753d26-4958-4a52-946c-ff068494c7c6','01011111','BANKASGS','25ec94f7-7c66-458c-acde-6d8748729412','AccountCat','NULL','NULL','Active','Tax101','BANKASGS','2022-06-10 16:56:42',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('f9029c83-8e18-45f7-91b8-620dfeada032','01011111','BANKASGS','25ec94f7-7c66-458c-acde-6d8748729412','AccountCat','NULL','NULL','Active','Tax101','BANKASGS','2022-06-10 16:56:42',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sss_account_old` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:33:03
