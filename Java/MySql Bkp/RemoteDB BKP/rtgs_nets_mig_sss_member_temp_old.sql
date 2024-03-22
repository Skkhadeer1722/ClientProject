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
-- Table structure for table `sss_member_temp_old`
--

DROP TABLE IF EXISTS `sss_member_temp_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sss_member_temp_old` (
  `id` varchar(36) NOT NULL,
  `member_code` varchar(100) DEFAULT NULL,
  `member_name` varchar(100) DEFAULT NULL,
  `activation_date` varchar(100) DEFAULT NULL,
  `member_short_name` varchar(100) DEFAULT NULL,
  `member_status` varchar(100) DEFAULT NULL,
  `member_type` int DEFAULT NULL,
  `sector_id` varchar(100) DEFAULT NULL,
  `bank_code` varchar(100) DEFAULT NULL,
  `auto_debit_indicator` varchar(100) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `facility_eligibility_id` varchar(100) DEFAULT NULL,
  `lei` varchar(100) DEFAULT NULL,
  `uen` varchar(100) DEFAULT NULL,
  `zero_holdings_statement` varchar(100) DEFAULT NULL,
  `exempted_from_billing` varchar(100) DEFAULT NULL,
  `exempted_from_system_limit` varchar(100) DEFAULT NULL,
  `statement_delivery_bic` varchar(100) DEFAULT NULL,
  `tax_profile_id` varchar(100) DEFAULT NULL,
  `fee_profile_id` varchar(100) DEFAULT NULL,
  `end_of_day_statement` varchar(100) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `activationDate` varchar(255) DEFAULT NULL,
  `autoDebitIndicator` varchar(255) DEFAULT NULL,
  `bankCode` varchar(255) DEFAULT NULL,
  `endOfDayStatement` varchar(255) DEFAULT NULL,
  `exemptedFromBilling` varchar(255) DEFAULT NULL,
  `exemptedFromSystemLimit` int DEFAULT NULL,
  `facilityEligibilityId` varchar(255) DEFAULT NULL,
  `memberCode` varchar(255) DEFAULT NULL,
  `memberName` varchar(255) DEFAULT NULL,
  `memberShortName` varchar(255) DEFAULT NULL,
  `memberStatus` varchar(255) DEFAULT NULL,
  `memberType` int NOT NULL,
  `sectorId` varchar(255) DEFAULT NULL,
  `statementDeliveryBic` varchar(255) DEFAULT NULL,
  `taxProfileId` varchar(255) DEFAULT NULL,
  `zeroHoldingsStatement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss_member_temp_old`
--

LOCK TABLES `sss_member_temp_old` WRITE;
/*!40000 ALTER TABLE `sss_member_temp_old` DISABLE KEYS */;
INSERT INTO `sss_member_temp_old` VALUES ('100104d9-07d1-41ed-b76e-3c1f238a44fa','MASGX1','Central Bank XXX0MASHOST','2022-02-07','MASGSXXX','ACT',12,'LBK','BANKS','Y','Customer Service Director Citibank, Robinson Road P.O. Box 036102','SGS1','9','0','0','N','N','No','TM000','fee001','100','2022-04-26 12:46:35',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('21461f40-3790-41b0-9175-d680a63800e4','MASGX1','Central Bank XXX0MASHOST','2022-02-07','MASGSXXX','ACT',444,'LBK','BANKS','Y','Customer Service Director Citibank, Robinson Road P.O. Box 036102','SGS1','9','0','0','N','4','No','TM000',NULL,'100','2022-06-10 15:55:54',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('21684538-8924-4134-a910-965211f7e33b','MASGX1','Central Bank XXX0MASHOST','2022-02-07','MASGSXXX','ACT',12,'LBK','BANKS','Y','Customer Service Director Citibank, Robinson Road P.O. Box 036102','SGS1','9','0','0','N','N','No','TM000','fee001','100','2022-04-26 12:46:35',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('3bc04c63-1e6a-4602-ad29-25dd8b9d06fe','MASGX1','Central Bank XXX0MASHOST','2022-02-07','MASGSXXX','ACT',444,'LBK','BANKS','Y','Customer Service Director Citibank, Robinson Road P.O. Box 036102','SGS1','9','0','0','N','4','No','TM000',NULL,'100','2022-06-10 16:14:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('6c1b74dd-cb8d-4f9b-bcf3-e3f6ae6aceb2','MASGX1','Central Bank XXX0MASHOST','2022-02-07','MASGSXXX','ACT',444,'LBK','BANKS','Y','Customer Service Director Citibank, Robinson Road P.O. Box 036102','SGS1','9','0','0','N','4','No','TM000',NULL,'100','2022-06-10 16:17:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('6d85f501-3bb7-49d6-8068-aa1164acfd4f','MASGX1','Central Bank XXX0MASHOST','2022-02-07','MASGSXXX','ACT',12,'LBK','BANKS','Y','Customer Service Director Citibank, Robinson Road P.O. Box 036102','SGS1','9','0','0','N','N','No','TM000','fee001','100','2022-04-26 12:46:35',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('8b556ef4-1161-4b19-af1c-1982236c14f5','MASGX1','Central Bank XXX0MASHOST','2022-02-07','MASGSXXX','ACT',12,'LBK','BANKS','Y','Customer Service Director Citibank, Robinson Road P.O. Box 036102','SGS1','9','0','0','N','N','No','TM000','fee001','100','2022-04-26 12:46:35',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('9f28d17c-8924-4816-b9ce-786378eb917d','MASGX1','Central Bank XXX0MASHOST','2022-02-07','MASGSXXX','ACT',444,'LBK','BANKS','Y','Customer Service Director Citibank, Robinson Road P.O. Box 036102','SGS1','9','0','0','N','4','No','TM000',NULL,'100','2022-06-07 15:29:50',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('a0668562-9c75-48fa-adfa-dab895c991bb','MASGX1','Central Bank XXX0MASHOST','2022-02-07','MASGSXXX','ACT',444,'LBK','BANKS','Y','Customer Service Director Citibank, Robinson Road P.O. Box 036102','SGS1','9','0','0','N','4','No','TM000',NULL,'100','2022-06-10 16:22:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('a9a375c8-0a20-4e62-a282-a6a4b51e4528','MASGX1','Central Bank XXX0MASHOST','0002-10-20 00:00:00.0','MASGSXXX','ACT',444,'LBK','BANKS','Y','Customer Service Director Citibank, Robinson Road P.O. Box 036102','SGS1','9','0','0','N','4','No','TM000',NULL,'100','2022-06-07 15:23:14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('b5f184db-dab1-40aa-96d3-fa9fcbbc04ea','MASGX1','Central Bank XXX0MASHOST','2022-02-07','MASGSXXX','ACT',12,'LBK','BANKS','Y','Customer Service Director Citibank, Robinson Road P.O. Box 036102','SGS1','9','0','0','N','N','No','TM000','fee001','100','2022-04-26 12:46:35',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('bdefefce-6438-418e-9f3d-5a574baefaf1','MASGX1','Central Bank XXX0MASHOST','2022-02-07','MASGSXXX','ACT',444,'LBK','BANKS','Y','Customer Service Director Citibank, Robinson Road P.O. Box 036102','SGS1','9','0','0','N','4','No','TM000',NULL,'100','2022-06-07 15:32:58',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('db4fe414-f15c-4b1e-895f-b3ea20608a2d','MASGX1','Central Bank XXX0MASHOST','2021-12-31 00:02:00.0','MASGSXXX','ACT',444,'LBK','BANKS','Y','Customer Service Director Citibank, Robinson Road P.O. Box 036102','SGS1','9','0','0','N','4','No','TM000',NULL,'100','2022-06-07 15:27:33',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('ed8d4a62-1ff8-447f-9234-56214e1b38c6','MASGX1','Central Bank XXX0MASHOST','2022-02-07','MASGSXXX','ACT',444,'LBK','BANKS','Y','Customer Service Director Citibank, Robinson Road P.O. Box 036102','SGS1','9','0','0','N','4','No','TM000',NULL,'100','2022-06-10 16:11:32',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),('f8d94ab3-2963-4c05-a15d-37950b965632','MASGX1','Central Bank XXX0MASHOST','0002-10-20 00:00:00.0','MASGSXXX','ACT',444,'LBK','BANKS','Y','Customer Service Director Citibank, Robinson Road P.O. Box 036102','SGS1','9','0','0','N','4','No','TM000',NULL,'100','2022-06-07 15:24:33',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sss_member_temp_old` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:32:17
