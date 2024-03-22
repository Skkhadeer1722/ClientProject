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
-- Table structure for table `sss_coupon_schedules`
--

DROP TABLE IF EXISTS `sss_coupon_schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sss_coupon_schedules` (
  `id` varchar(255) NOT NULL,
  `coupon_date` varchar(255) DEFAULT NULL,
  `coupon_rate` varchar(255) DEFAULT NULL,
  `securities_code` varchar(255) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `CouponDate` varchar(255) DEFAULT NULL,
  `CouponRate` varchar(255) DEFAULT NULL,
  `couponPaymentFrequency` varchar(255) DEFAULT NULL,
  `couponStructure` varchar(255) DEFAULT NULL,
  `issuer` varchar(255) DEFAULT NULL,
  `securiiesType` varchar(255) DEFAULT NULL,
  `securitiesCode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sss_coupon_schedules`
--

LOCK TABLES `sss_coupon_schedules` WRITE;
/*!40000 ALTER TABLE `sss_coupon_schedules` DISABLE KEYS */;
INSERT INTO `sss_coupon_schedules` VALUES ('01cb2f1a-2cf7-4fee-95a5-4c68c6e311fe','20201120',NULL,'SGCAPACSTST1','2022-08-26 10:24:42',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('44ccb441-2118-4e93-a817-c709b2a82898','20220316',NULL,'SGCAPACSTST2','2022-08-26 10:24:43',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('6b9f0e48-a11d-4e9c-bed5-2d7dd1856718','20210916',NULL,'SGCAPACSTST2','2022-08-26 10:24:43',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('73a6f59e-71db-4b98-abe4-81c6e640e586','20210520',NULL,'SGCAPACSTST1','2022-08-26 10:24:42',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('942a97b1-5475-41f6-ab3d-0c51ac4f21c2','20200520',NULL,'SGCAPACSTST1','2022-08-26 10:24:42',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('d9f90e36-3344-4271-8619-55680b7722ac','20210316',NULL,'SGCAPACSTST2','2022-08-26 10:24:42',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sss_coupon_schedules` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:32:51