-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 103.177.224.100    Database: cas
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
-- Table structure for table `acc_definition`
--

DROP TABLE IF EXISTS `acc_definition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acc_definition` (
  `AD_ACC_NO` varchar(34) NOT NULL,
  `AD_DESC` varchar(140) DEFAULT NULL,
  `AD_ACC_ADDR` varchar(120) DEFAULT NULL,
  `AD_MBR_CODE` varchar(16) DEFAULT NULL,
  `AD_CCY_CODE` varchar(3) NOT NULL,
  `AD_ACC_TYPE` varchar(3) NOT NULL,
  `AD_ACC_STAT` varchar(1) NOT NULL,
  `AD_GL_CAT` varchar(7) NOT NULL,
  `AD_ZERO_IND` varchar(1) DEFAULT NULL,
  `AD_OD_ALLOW` varchar(1) DEFAULT NULL,
  `AD_EOD_STAT_SENT` varchar(3) DEFAULT NULL,
  `AD_STAT_SENT_MED` varchar(2) DEFAULT NULL,
  `AD_MASNET_ID` varchar(70) DEFAULT NULL,
  `AD_NEXT_QUE_NO` int DEFAULT NULL,
  `AD_GL_ACC_CC` varchar(5) DEFAULT NULL,
  `AD_GL_ACC_NO` varchar(34) DEFAULT NULL,
  `AD_DEFAULT_ACC` varchar(1) DEFAULT NULL,
  `AD_STAT` varchar(1) DEFAULT NULL,
  `AD_ACTION` varchar(1) DEFAULT NULL,
  `AD_ACTION_STAT` varchar(1) DEFAULT NULL,
  `AD_USER_ID_1` varchar(16) DEFAULT NULL,
  `AD_USER_ID_2` varchar(16) DEFAULT NULL,
  `AD_UPD_DT_STAMP` date DEFAULT NULL,
  `AD_SWIFT_BIC_ADDR` varchar(8) DEFAULT NULL,
  `AD_STATEMENT_IND` varchar(1) DEFAULT NULL,
  `AD_COLLATERAL_IND` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`AD_ACC_NO`,`AD_CCY_CODE`,`AD_ACC_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_definition`
--

LOCK TABLES `acc_definition` WRITE;
/*!40000 ALTER TABLE `acc_definition` DISABLE KEYS */;
INSERT INTO `acc_definition` VALUES ('70080100','ZYABSGD0',NULL,'MASGSGSG','SGD','LIB','A','GLIFI','N','N','NA','NA',NULL,0,'0000','22060101LIABGRF','Y','A','M','A','CASSADM1','BCSUSER8','2020-11-20','RTRTRTTT','N','N'),('70080300','ZYABSGD0',NULL,'CMBCSGSG','SGD','LIB','A','GLBNK','N','N','NA','NA',NULL,0,'0000','111111117777777777777','Y','A','M','A','BCSUSER12','BCSUSER25','2020-10-21','ZYSESGD0','N','N');
/*!40000 ALTER TABLE `acc_definition` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:31:44