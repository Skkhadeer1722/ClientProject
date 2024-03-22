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
-- Table structure for table `almt_det`
--

DROP TABLE IF EXISTS `almt_det`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `almt_det` (
  `ALD_ALMT_DATE` varchar(8) NOT NULL,
  `ALD_ISIN` varchar(12) NOT NULL,
  `ALD_ISIN_TYPE` varchar(2) NOT NULL,
  `ALD_MBR_CODE` varchar(16) NOT NULL,
  `ALD_CUSTODY` varchar(3) NOT NULL,
  `ALD_ALMT_TYPE` varchar(1) DEFAULT NULL,
  `ALD_ALMT_PRICE` double(8,5) DEFAULT NULL,
  `ALD_NOM_AMT` bigint DEFAULT NULL,
  `ALD_SETT_AMT` bigint DEFAULT NULL,
  `ALD_ACTION` varchar(1) DEFAULT NULL,
  `ALD_ACTION_STAT` varchar(1) DEFAULT NULL,
  `ALD_USER_ID_1` varchar(16) DEFAULT NULL,
  `ALD_USER_ID_2` varchar(16) DEFAULT NULL,
  `ALD_UPD_DT_STAMP` date DEFAULT NULL,
  `ALD_SEQ_NO` int DEFAULT NULL,
  PRIMARY KEY (`ALD_ISIN`,`ALD_ALMT_DATE`,`ALD_ISIN_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `almt_det`
--

LOCK TABLES `almt_det` WRITE;
/*!40000 ALTER TABLE `almt_det` DISABLE KEYS */;
INSERT INTO `almt_det` VALUES ('20221010','SGCAPACSTST1','1','MASGSGSG','11','1',100.00000,100,100,'1','1','3432sd','1234esd','2022-10-10',1);
/*!40000 ALTER TABLE `almt_det` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:33:33