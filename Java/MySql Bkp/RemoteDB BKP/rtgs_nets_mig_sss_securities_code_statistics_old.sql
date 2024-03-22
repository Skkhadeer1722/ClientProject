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
-- Table structure for table `sss_securities_code_statistics_old`
--

DROP TABLE IF EXISTS `sss_securities_code_statistics_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sss_securities_code_statistics_old` (
  `securities_code` varchar(36) NOT NULL,
  `total_nominal_amount_issued` int DEFAULT NULL,
  `total_redeemed_amount` int DEFAULT NULL,
  `total_nominal_amount_issued_for_erf` int DEFAULT NULL,
  `outstanding_nominal_amount` int DEFAULT NULL,
  `next_coupon_date` varchar(100) DEFAULT NULL,
  `last_coupon_date` varchar(100) DEFAULT NULL,
  `next_index_end_date` varchar(100) DEFAULT NULL,
  `amount_alloted_to_central_bank` varchar(100) DEFAULT NULL,
  `amount_alloted_to_others` varchar(100) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `securitiesCode` varchar(255) NOT NULL,
  `amountAllotedToCentralBank` varchar(255) DEFAULT NULL,
  `amountAllotedToOthers` varchar(255) DEFAULT NULL,
  `lastCouponDate` varchar(255) DEFAULT NULL,
  `nextCouponDate` varchar(255) DEFAULT NULL,
  `nextIndexEndDate` varchar(255) DEFAULT NULL,
  `outstandingNominalAmount` int DEFAULT NULL,
  `totalNominalAmountIssued` int DEFAULT NULL,
  `totalNominalAmountIssuedForErf` int DEFAULT NULL,
  `totalRedeemedAmount` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`securities_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss_securities_code_statistics_old`
--

LOCK TABLES `sss_securities_code_statistics_old` WRITE;
/*!40000 ALTER TABLE `sss_securities_code_statistics_old` DISABLE KEYS */;
INSERT INTO `sss_securities_code_statistics_old` VALUES ('0f3ce294-f58a-4e35-a1e7-062ca2db0e08',10,10000,0,5000,'02-05-2022','02-05-2023','03-05-2023','2000','8000','2022-06-10 16:17:13','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sss_securities_code_statistics_old` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:32:03
