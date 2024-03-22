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
-- Table structure for table `sss_coupon_schedules_temp_old`
--

DROP TABLE IF EXISTS `sss_coupon_schedules_temp_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sss_coupon_schedules_temp_old` (
  `id` varchar(36) NOT NULL,
  `securities_code` varchar(36) NOT NULL,
  `securiies_type` varchar(100) DEFAULT NULL,
  `issuer` varchar(100) DEFAULT NULL,
  `coupon_structure` varchar(100) DEFAULT NULL,
  `coupon_payment_frequency` varchar(100) DEFAULT NULL,
  `coupon_date` varchar(100) DEFAULT NULL,
  `coupon_rate` varchar(100) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss_coupon_schedules_temp_old`
--

LOCK TABLES `sss_coupon_schedules_temp_old` WRITE;
/*!40000 ALTER TABLE `sss_coupon_schedules_temp_old` DISABLE KEYS */;
INSERT INTO `sss_coupon_schedules_temp_old` VALUES ('03d142df-ed7f-4808-b0f1-1b641d19e844','NETS00000102','1YTB','MAS','SSB','Semi-Yearly','22-Sep-2022','1.5','2022-06-20 12:48:07'),('0620ea61-6984-4894-a48f-bee836e9e2a5','NETS00000101','1YTB','MAS','SSB','Semi-Yearly','22-Sep-2022','1.5','2022-06-20 12:48:07'),('4fc3cdd7-439d-4816-bad5-0ce71878780e','NETS00000101','1YTB','MAS','SSB','Semi-Yearly','22-Sep-2022','1.5','2022-06-20 12:48:07'),('58361c30-d5b1-403c-a605-992de1f06564','NETS00000102','1YTB','MAS','SSB','Semi-Yearly','22-Sep-2022','1.5','2022-06-20 12:48:07'),('87a96e48-ec5e-4d21-a358-d89d04eb1435','NETS00000102','1YTB','MAS','SSB','Semi-Yearly','22-Sep-2022','1.5','2022-06-20 12:48:07'),('a12bbd3b-d3f3-4c08-99d6-19579d3eb1f2','NETS00000101','1YTB','MAS','SSB','Semi-Yearly','22-Sep-2022','1.5','2022-06-20 12:48:07'),('b8a25cb4-f117-4deb-9933-a3d86d6cc4ab','NETS00000101','1YTB','MAS','SSB','Semi-Yearly','22-Sep-2022','1.5','2022-06-20 12:48:07'),('c86595a7-11b1-4472-b837-09718c551330','NETS00000102','1YTB','MAS','SSB','Semi-Yearly','22-Sep-2022','1.5','2022-06-20 12:48:07');
/*!40000 ALTER TABLE `sss_coupon_schedules_temp_old` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:32:09
