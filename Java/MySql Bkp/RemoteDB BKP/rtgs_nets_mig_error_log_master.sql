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
-- Table structure for table `error_log_master`
--

DROP TABLE IF EXISTS `error_log_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `error_log_master` (
  `ERROR_CODE` int NOT NULL,
  `ERROR_DESCRIPTION` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `REMARKS` varchar(50) DEFAULT NULL,
  `error_descrption` varchar(255) DEFAULT NULL,
  `errorCode` bigint NOT NULL,
  `errorDescription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ERROR_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `error_log_master`
--

LOCK TABLES `error_log_master` WRITE;
/*!40000 ALTER TABLE `error_log_master` DISABLE KEYS */;
INSERT INTO `error_log_master` VALUES (0,'Run time errors','Un Documented errors',NULL,0,NULL),(100,'IO exceptions','Check file path and other FTP access',NULL,0,NULL),(300,'SQL DB errors','errors caused at DB operations',NULL,0,NULL),(400,'URL not found','Please retry with valid URL',NULL,0,NULL),(404,'Authorization error','Check user profile sent in request',NULL,0,NULL),(405,'BAD REQUEST','Check file upload code sent',NULL,0,NULL),(500,'Internal server error','Please check in logs for failure',NULL,0,NULL),(600,'Data Validation Failure','Values sent in data file is not in expected patter',NULL,0,NULL),(601,'Field is empty','Check values sent',NULL,0,NULL),(602,'Date format is not correct','',NULL,0,NULL),(603,'Length exceeding','inserted value is more than max length',NULL,0,NULL);
/*!40000 ALTER TABLE `error_log_master` ENABLE KEYS */;
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
