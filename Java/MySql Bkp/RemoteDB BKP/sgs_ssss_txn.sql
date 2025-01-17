-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 103.177.224.100    Database: sgs
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
-- Table structure for table `ssss_txn`
--

DROP TABLE IF EXISTS `ssss_txn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ssss_txn` (
  `SST_SSSS_REF_NO` varchar(16) NOT NULL,
  `SST_TXN_DATE` varchar(8) NOT NULL,
  `SST_SETT_DATE` varchar(8) NOT NULL,
  `SST_TRAD_DATE` varchar(8) NOT NULL,
  `SST_TXN_TYPE` varchar(3) NOT NULL,
  `SST_TXN_STAT` varchar(4) NOT NULL,
  `SST_RTN_CODE` varchar(10) DEFAULT NULL,
  `SST_ISIN` varchar(12) NOT NULL,
  `SST_NOM_AMT` int NOT NULL,
  `SST_SETT_AMT` int DEFAULT NULL,
  `SST_DELV_AGENT` varchar(16) DEFAULT NULL,
  `SST_DELV_CUSTODY` varchar(3) DEFAULT NULL,
  `SST_RECV_AGENT` varchar(16) DEFAULT NULL,
  `SST_RECV_CUSTODY` varchar(3) DEFAULT NULL,
  `SST_QUE_SEQ_NO` int DEFAULT NULL,
  `SST_DELV_CHNL` varchar(1) DEFAULT NULL,
  `SST_RECV_CHNL` varchar(1) DEFAULT NULL,
  `SST_ISIN_CNT` varchar(2) DEFAULT NULL,
  `SST_UPD_DT_STAMP` date NOT NULL,
  `SST_SUB_TXN_TYPE` varchar(3) DEFAULT NULL,
  `SST_ORIG_SETT_AMT` int DEFAULT NULL,
  `SST_ORIG_REPO_AMT` int DEFAULT NULL,
  PRIMARY KEY (`SST_SSSS_REF_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ssss_txn`
--

LOCK TABLES `ssss_txn` WRITE;
/*!40000 ALTER TABLE `ssss_txn` DISABLE KEYS */;
INSERT INTO `ssss_txn` VALUES ('S202209115746993','20220911','20220911','20220911','RPC','TFRC',NULL,'SG2020446634',4000,319900,'ZYSBSGS0','TRD','ZYSHSGS0','ERF',1,'S',NULL,'1','2022-10-28','SFT',400000,219900);
/*!40000 ALTER TABLE `ssss_txn` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:33:36
