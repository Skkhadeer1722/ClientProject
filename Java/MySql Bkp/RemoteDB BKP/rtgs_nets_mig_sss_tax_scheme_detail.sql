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
-- Table structure for table `sss_tax_scheme_detail`
--

DROP TABLE IF EXISTS `sss_tax_scheme_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sss_tax_scheme_detail` (
  `id` varchar(36) NOT NULL COMMENT 'PRIMARY KEY',
  `tax_scheme_id` varchar(36) NOT NULL COMMENT 'Tax scheme id',
  `tax_bracket` varchar(35) NOT NULL COMMENT 'Tax bracket for tax profile',
  `tax_rate` decimal(13,10) NOT NULL COMMENT 'Tax rate',
  PRIMARY KEY (`id`),
  KEY `fk_tax_scheme_detail_tax_scheme` (`tax_scheme_id`),
  CONSTRAINT `fk_tax_scheme_detail_tax_scheme` FOREIGN KEY (`tax_scheme_id`) REFERENCES `tax_scheme` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='biz-param_screen_tax-scheme_The detail table of tax scheme';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss_tax_scheme_detail`
--

LOCK TABLES `sss_tax_scheme_detail` WRITE;
/*!40000 ALTER TABLE `sss_tax_scheme_detail` DISABLE KEYS */;
INSERT INTO `sss_tax_scheme_detail` VALUES ('c0c47ee2-2c4e-4634-b0df-73be40b2a6bb','22d5ad2a-df08-44c7-a9a9-31eb17d4866c','24Local',0.0000000000),('d96b4712-ec84-41b6-a9ad-2a718a239431','289bdfe2-843b-4291-a943-316178ebb82f','Standard-rated',8.0000000000),('e71758e6-eb00-4a66-9fff-91c401718ac5','3b1d02c3-2bd7-4047-ba67-b9f4233a58ea','Exempt',0.0000000000);
/*!40000 ALTER TABLE `sss_tax_scheme_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:31:48
