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
-- Table structure for table `member_temp_old`
--

DROP TABLE IF EXISTS `member_temp_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_temp_old` (
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
  `exempted_from_billing` tinyint(1) DEFAULT NULL,
  `exempted_from_system_limit` tinyint(1) DEFAULT NULL,
  `currency_code` varchar(3) DEFAULT NULL,
  `currency_member_code` varchar(36) DEFAULT NULL,
  `currency_settlement_agent` varchar(100) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `member_liaison` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_temp_old`
--

LOCK TABLES `member_temp_old` WRITE;
/*!40000 ALTER TABLE `member_temp_old` DISABLE KEYS */;
INSERT INTO `member_temp_old` VALUES ('47e3f5cc-993f-458a-ac39-14e8e35ec972','MASGX-7','Central Bank XXX0MASHOST','MASGSXXX','CBK','ACT','2020-12-27','MASGSXXX','120','N','S','LBK','0','TM000','fee001','BANKS','10','1','89','40','S72FC2238G','7LTWFZYIC','0',0,2,'SGD','0','2022-05-11 10:13:25','2022-06-13 18:55:51',NULL),('5dc7c686-5684-4c03-8b9d-24af7a4029ce','MASGX-7','Central Bank XXX0MASHOST','MASGSXXX','CBK','ACT','2020-12-27','MASGSXXX','120','N','S','LBK','0','TM000','fee001','BANKS','10','1','89','40','S72FC2238G','7LTWFZYIC','0',0,2,'SGD','0','2022-05-11 10:13:25','2022-06-13 18:55:46',NULL),('5df5fda3-f421-4c8b-b2a5-a84e83fe40e0','MASGX-7','Central Bank XXX0MASHOST','MASGSXXX','SRI','ACT','2020-12-27','MASGSXXX','120','N','S','LBK','0','TM000','fee001','BANKS','10','1','89','40','S72FC2238G','7LTWFZYIC','0',0,2,'SGD','0','2022-05-11 10:13:25','2022-06-13 18:55:52',NULL),('d80bf673-cade-439a-9ffe-7e4b93d72fb4','MASGX-7','Central Bank XXX0MASHOST','MASGSXXX','NBK','ACT','2020-12-27','MASGSXXX','120','N','S','LBK','0','TM000','fee001','BANKS','10','1','89','40','S72FC2238G','7LTWFZYIC','0',0,2,'SGD','0','2022-05-11 10:13:25','2022-06-13 18:55:54',NULL),('f3e464ab-6fef-4065-8a39-27126e533caa','MASGX-7','Central Bank XXX0MASHOST','MASGSXXX','CBK','ACT','2020-12-27','MASGSXXX','120','N','S','LBK','0','TM000','fee001','BANKS','10','1','89','40','S72FC2238G','7LTWFZYIC','0',0,2,'SGD','0','2022-05-11 10:13:25','2022-06-10 17:28:16',NULL),('fce2a3ea-64fd-434d-8cf8-ce166f3b46b0','MASGX-7','Central Bank XXX0MASHOST','MASGSXXX','CBK','ACT','2020-12-27','MASGSXXX','120','N','S','LBK','0','TM000','fee001','BANKS','10','1','89','40','S72FC2238G','7LTWFZYIC','0',0,2,'SGD','0','2022-05-11 10:13:25','2022-06-13 18:55:53',NULL);
/*!40000 ALTER TABLE `member_temp_old` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:32:24
