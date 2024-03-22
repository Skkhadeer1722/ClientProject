-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 103.177.224.100    Database: nets
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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `member_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `member_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Bank/FI name',
  `member_short_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Bank/FI short name',
  `member_type` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `member_status` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `activation_date` date NOT NULL,
  `swift_member` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `swift_bic` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `location_code` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '3 digit location code',
  `fi_group` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fi_code` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sector_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `currency_code_subscription` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `tax_rate` varchar(100) DEFAULT NULL,
  `fee_profile_id` varchar(100) DEFAULT NULL,
  `mcb_id` varchar(100) DEFAULT NULL,
  `intrday_mcb_requirement` varchar(100) DEFAULT NULL,
  `minimum_mcb_requirement` varchar(100) DEFAULT NULL,
  `maximum_mcb_requirement` varchar(100) DEFAULT NULL,
  `average_mcb_requirement` varchar(100) DEFAULT NULL,
  `uen` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Company registartion number',
  `lei` varchar(20) DEFAULT NULL,
  `exempted_from_billing` int DEFAULT NULL,
  `exempted_from_system_limit` int DEFAULT NULL,
  `currency_code` varchar(3) DEFAULT NULL,
  `currency_member_code` varchar(36) DEFAULT NULL,
  `currency_settlement_agent` varchar(100) DEFAULT NULL,
  `member_liaison` varchar(100) DEFAULT NULL,
  `memeber_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('1bcd-44d3-a0fe-ccc6ac8d1340','MASGSG','Central Bank XXX0MASHOST','MASGSXXX','CBK','ACT','2021-12-26','1','MASGSXXX','120','B','NA','LBK','0','TM000','fee001','BANKS','10','1','89','40','S72FC2238G','7LTWFZYICNSX8D621K86',0,0,'SGD','e0f021c3-1bcd-44d3-a0fe-ccc6ac8d1340','0','9',NULL),('44d3-a0fe-ccc6ac8d1340','MASGSG','Central Bank XXX0MASHOST','MASGSXXX','CBK','ACT','2021-12-26','1','MASGSXXX','120','B','NA','LBK','0','TM000','fee001','BANKS','10','1','89','40','S72FC2238G','7LTWFZYICNSX8D621K86',0,0,'SGD','e0f021c3-1bcd-44d3-a0fe-ccc6ac8d1340','0','9',NULL),('a0fe-ccc6ac8d1340','MASGSG','Central Bank XXX0MASHOST','MASGSXXX','CBK','ACT','2021-12-26','1','MASGSXXX','120','B','NA','LBK','0','TM000','fee001','BANKS','10','1','89','40','S72FC2238G','7LTWFZYICNSX8D621K86',0,0,'SGD','e0f021c3-1bcd-44d3-a0fe-ccc6ac8d1340','0','9',NULL),('e0f021c3-1bcd-44d3-a0fe-ccc6ac8d1340','MASGSG','Central Bank XXX0MASHOST','MASGSXXX','CBK','ACT','2021-12-26','1','MASGSXXX','120','B','NA','LBK','0','TM000','fee001','BANKS','10','1','89','40','S72FC2238G','7LTWFZYICNSX8D621K86',0,0,'SGD','e0f021c3-1bcd-44d3-a0fe-ccc6ac8d1340','0','9',NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:31:23
