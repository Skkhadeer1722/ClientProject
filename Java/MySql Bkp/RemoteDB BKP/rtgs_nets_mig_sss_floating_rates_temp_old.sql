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
-- Table structure for table `sss_floating_rates_temp_old`
--

DROP TABLE IF EXISTS `sss_floating_rates_temp_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sss_floating_rates_temp_old` (
  `id` varchar(36) NOT NULL,
  `rate_index` varchar(100) DEFAULT NULL,
  `publication_date` varchar(100) DEFAULT NULL,
  `value_date` varchar(100) DEFAULT NULL,
  `rate` varchar(100) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `publicationDate` varchar(255) DEFAULT NULL,
  `rateIndex` varchar(255) DEFAULT NULL,
  `valueDate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss_floating_rates_temp_old`
--

LOCK TABLES `sss_floating_rates_temp_old` WRITE;
/*!40000 ALTER TABLE `sss_floating_rates_temp_old` DISABLE KEYS */;
INSERT INTO `sss_floating_rates_temp_old` VALUES ('e90600bc-07ee-4326-a7d7-92120ab47123','SORA_INDEX','14-Feb-2022','14-Feb-2022','0.9876545678','2022-06-21 09:55:22',NULL,NULL,NULL),('e90600bc-07ee-4326-a7d7-92120ab47300','SORA_INDEX','22-Mar-2022','22-Mar-2022','0.1234563454','2022-06-21 09:55:22',NULL,NULL,NULL),('e90600bc-07ee-4326-a7d7-92120ab47422','SORA_INDEX','27-Mar-2022','27-Mar-2022','0.5234252334','2022-06-21 09:55:22',NULL,NULL,NULL),('e90600bc-07ee-4326-a7d7-92120ab47777','SORA_INDEX','12-Aug-2022','12-Aug-2022','0.3454323456','2022-06-21 09:55:22',NULL,NULL,NULL),('e90600bc-07ee-4326-a7d7-92120ab47896','SORA_INDEX','14-Sep-2022','14-Sep-2022','0.6544567887','2022-06-21 09:55:22',NULL,NULL,NULL);
/*!40000 ALTER TABLE `sss_floating_rates_temp_old` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:33:14
