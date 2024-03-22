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
-- Table structure for table `file_upload_header`
--

DROP TABLE IF EXISTS `file_upload_header`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file_upload_header` (
  `FILE_UPLOAD_ID` int NOT NULL,
  `FILE_UPLOAD_CODE` varchar(50) NOT NULL,
  `IP_ADDRESS` varchar(50) NOT NULL,
  `FILE_PATH` varchar(100) NOT NULL,
  `FILE_NAME` varchar(100) NOT NULL,
  `DRAFT_TABLE_NAME` varchar(100) NOT NULL,
  `LIVE_TABLE_NAME` varchar(100) NOT NULL,
  `FILE_DELIMITER` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `FILE_HEADER` int DEFAULT NULL,
  `IS_ACTIVE` int DEFAULT '1',
  `PROCEDURE_NAME` varchar(50) DEFAULT NULL,
  `fileUploadId` int NOT NULL,
  `draftTableName` varchar(255) DEFAULT NULL,
  `fileDelimiter` varchar(255) DEFAULT NULL,
  `fileHeader` int DEFAULT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  `fileUploadCode` varchar(255) DEFAULT NULL,
  `ipAddress` varchar(255) DEFAULT NULL,
  `isActive` int DEFAULT NULL,
  `liveTableName` varchar(255) DEFAULT NULL,
  `procedureName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`FILE_UPLOAD_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_upload_header`
--

LOCK TABLES `file_upload_header` WRITE;
/*!40000 ALTER TABLE `file_upload_header` DISABLE KEYS */;
INSERT INTO `file_upload_header` VALUES (2,'ACCOUNT_FILE_UPLOAD','103.177.224.100','C:UsersPublicscrips data','Account.txt','STR_ACCOUNT','ACCOUNT','|',1,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'ACCOUNT_POSITION_FILE_UPLOAD','103.177.224.100','C:UsersPublicscrips data','AccountPosition.txt','STR_AccountPosition','AccountPosition','|',1,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'ACCOUNT_SETTLEMENT_PURPOSE_FILE_UPLOAD','103.177.224.100','C:UsersPublicscrips data','AccountStlmentP.txt','STR_AccountStlmentP','account_settlement_purpose','|',1,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1,'MEMBER_FILE_UPLOAD','103.177.224.100','C:UsersPublicscrips data','Member.txt','INT_MEMBER','MEMBER','|',1,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'SSS_ACCOUNT_FILE_UPLOAD','103.177.224.100','C:UsersPublicscrips data','SSS_Account.txt','STR_sss_account','sss_account','|',1,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'SSS_ACCOUNT_SECURITY_POSITION_STATS_FILE_UPLOAD','103.177.224.100','C:UsersPublicscrips data','SSS_AccSPStats.txt','STR_sss_account_securites_position_stats','sss_account_securites_position_stats','|',1,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,'SSS_ALLOTMENT_FILE_UPLOAD','103.177.224.100','C:UsersPublicscrips data','SSS_Allotment.txt','STR_sss_allotment','sss_allotment','|',1,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'SSS_COUPON_SCHEDULES_FILE_UPLOAD','103.177.224.100','C:UsersPublicscrips data','SSS_CoupCodes.txt','STR_sss_coupon_schedules','sss_coupon_schedules','|',1,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'SSS_FLOATING-RATES_FILE_UPLOAD','103.177.224.100','C:UsersPublicscrips data','SSS_FloatRates.txt','STR_sss_floating_rates','sss_floating_rates','|',1,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'SSS_MEMBER_FILE_UPLOAD','103.177.224.100','C:UsersPublicscrips data','Member.txt','STR_sss_member','sss_member','|',1,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'SSS_SECURITIES_CODE_FILE_UPLOAD','103.177.224.100','C:UsersPublicscrips data','SSS_SecCodes.txt','STR_sss_securities_code','sss_securities_code','|',1,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'SSS_SECURITIES_CODE_STATISTICS_FILE_UPLOAD','103.177.224.100','C:UsersPublicscrips data','SSS_SecCodesStats.txt','STR_sss_securities_code_statistics','sss_securities_code_statistics','|',1,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'SSS_SECURITIES_PRICE_FILE_UPLOAD','103.177.224.100','C:UsersPublicscrips data','SSS_SecPrices.txt','STR_sss_securities_price','sss_securities_price','|',1,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,'SSS_TRANSACTION_FILE_UPLOAD','103.177.224.100','C:UsersPublicscrips data','SSS_Trancation.txt','STR_sss_transaction','sss_transaction','|',1,1,'',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `file_upload_header` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:33:17
