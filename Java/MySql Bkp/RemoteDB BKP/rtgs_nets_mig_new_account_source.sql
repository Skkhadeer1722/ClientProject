-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 103.177.224.100    Database: rtgs_nets_mig_new
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
-- Table structure for table `account_source`
--

DROP TABLE IF EXISTS `account_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_source` (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'account Id',
  `account_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'account number',
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'account description',
  `member_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'member id',
  `account_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'account type',
  `main_account_indicator` varchar(50) DEFAULT NULL COMMENT 'main account indicator',
  `default_main_account` varchar(50) DEFAULT NULL COMMENT 'default main account',
  `statement_delivery_bic` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'statement delivery BIC',
  `account_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'account status',
  `currency_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'currency code',
  `end_of_day_statement` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'statement to be sent by end of the day',
  `payer_reference` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Payer Reference No for the Target Transaction',
  `related_reference` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Related Reference No for the Target Transaction',
  `counterparty_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'counterparty code',
  `value_date` varchar(50) DEFAULT NULL COMMENT 'value date of this record',
  `activation_date` varchar(50) DEFAULT NULL COMMENT 'effective date for the whole any changes made in Sub Account Management except Account Status field',
  `gl_category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'GL category, possible value: GLBNK, GLAGD, GLFCB, GLFIN, GLIFI, GLOTH, BLANK',
  `cost_centre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'cost centre',
  `gl_account_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'GL account number',
  `statement_interval` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'interval at which statements are generated, possible value: Daily, Monthly, No',
  `action` varchar(50) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(50) DEFAULT NULL COMMENT 'Record status',
  `modified_by` varchar(50) DEFAULT NULL COMMENT 'Last modified User ID',
  `modified_date` varchar(50) DEFAULT NULL COMMENT 'Last modified timestamp',
  `approved_by` varchar(50) DEFAULT NULL COMMENT 'Last approved user ID',
  `approved_date` varchar(50) DEFAULT NULL COMMENT 'Last approved timestamp',
  `created_date` varchar(50) DEFAULT NULL COMMENT 'Created date of the record',
  `effective_date` varchar(50) DEFAULT NULL COMMENT 'Activation date of the record',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` varchar(50) DEFAULT NULL COMMENT 'Approval flow ID',
  `compliance_calculation` varchar(50) DEFAULT NULL COMMENT 'Included for Compliance Calculation',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(1000) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_source`
--

LOCK TABLES `account_source` WRITE;
/*!40000 ALTER TABLE `account_source` DISABLE KEYS */;
INSERT INTO `account_source` VALUES ('6bd1b6dd65524b08a51c44418480cb5f','21110223','Settlement Account','MASGSGSG','SETT','Y','Y','DBSSSGSG000','Active','SGD','NO_STATEMENT',NULL,NULL,NULL,NULL,NULL,'ctgy1','cost1','123','No',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,'2023-01-03 11:23:59'),('203c9e002151446eafd5eff2d3fdf86e','21110223','Settlement Account','MASGSGSG','SETT','Y','Y','DBSSSGSG000','Active','SGD','NO_STATEMENT',NULL,NULL,NULL,NULL,NULL,'ctgy1','cost1','123','No',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,'2023-01-03 11:23:59'),('b3f34b1573b141759b79dac9421f1036','11111110','Settlement Account@','MASGSGSG','SETT','Y','Y','DBSSSGSG000','Active','SGD','NO_STATEMENT',NULL,NULL,NULL,NULL,NULL,'ctgy1','cost1','123','No',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,'2023-01-03 11:23:59'),('bb797888975445b7aa404ba92cc1a984','11111111','Settlement Account','MASGSGSG','SETT','Y','Y','DBSSSGSG000','Active','SG-','NO_STATEMENT',NULL,NULL,NULL,NULL,NULL,'ctgy1','cost1','123','No',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,' Currency Code : Field contains not allowed special characters','2023-01-03 11:23:59'),('3f92f8c5f39e4632b60c577e5b77dec7','11111112','Settlement Account','DBSSSGSG','SETT','N','Y','DBSSSGSG000','Active','SGD','NO_STATEMENT',NULL,NULL,NULL,NULL,NULL,'ctgy1','cost1','123','No',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,NULL,'2023-01-03 11:23:59'),('61a04d3a9b0f47929e2ee5301d4b33e4','11111113','Settlement Account','MASGSGSG','SETT','Y','N','DBSSSGSG000','Active','SGD','NO_STATEMENT',NULL,NULL,NULL,NULL,NULL,'ctgy1','cost1','123','No',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6,NULL,'2023-01-03 11:24:00'),('c6814b4f2d764ac5917deb29fdd33218','11111114','Settlement Account','MASGSGSG','SETT','Y','Y','DBSSSGSG000','Active','SGD','NO_STATEMENT',NULL,NULL,NULL,NULL,NULL,'ctgy1','cost1','123','TEST',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,7,NULL,'2023-01-03 11:24:00'),('9d7309034a064c36977ef5675a6fc825','11111115','Settlement Account','MASGSGSG','SETT','Y','Y','DBSSSGSG000','TEST','SGD','NO_STATEMENT',NULL,NULL,NULL,NULL,NULL,'ctgy1','cost1','123','No',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8,NULL,'2023-01-03 11:24:00'),('eea5dc03498a4936a9cfc280c782b029','11111116','Settlement Account','','SETT','Y','Y','DBSSSGSG000','Active','SGD','NO_STATEMENT',NULL,NULL,NULL,NULL,NULL,'ctgy1','cost1','123','No',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,9,' Member Id : Null or Empty, Member Id : Null or Empty','2023-01-03 11:24:00'),('60fc6726a56240108c466c40dbd291d4','','Settlement Account','DBSSSGSG','SETT','Y','Y','DBSSSGSG000','Active','SGD','NO_STATEMENT',NULL,NULL,NULL,NULL,NULL,'ctgy1','cost1','123','No',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,' Account Number : Null or Empty, Account Number : Null or Empty','2023-01-03 11:24:00'),('2aec3d73956845c5a6065e66a54f5e2f','11111117','Settlement Account@','MASGSGSG','SETT','Y','Y','DBSSSGSG000','Active','SG-','NO_STATEMENT@',NULL,NULL,NULL,NULL,NULL,'ctgy1','cost1','123','No',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,11,' Currency Code : Field contains not allowed special characters, End Of Day Statement : Field contains not allowed special characters except underscore','2023-01-03 11:24:00'),('a445e45cd1fd4711a536299b472c75fa','11111118','Settlement Account','MASGSGSG','SETT','Y','Y','DBSSSGSG000@','Active','SGD','NO_STATEMENT',NULL,NULL,NULL,NULL,NULL,'ctgy1','cost1','123','No',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,12,' Statement Delivery Bic : Field contains not allowed special characters, Statement Delivery Bic : Field length more then 11','2023-01-03 11:24:00'),('9589bd4152a74a749bc97e1997d8cdb4','11111118','Settlement Account','MASGSGSG@','SETT','Y','Y','DBSSSGSG000','Active','SGD','NO_STATEMENT',NULL,NULL,NULL,NULL,NULL,'ctgy1','cost1','123','No',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,13,' Member Id : Field contains not allowed special characters','2023-01-03 11:24:00');
/*!40000 ALTER TABLE `account_source` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:34:51
