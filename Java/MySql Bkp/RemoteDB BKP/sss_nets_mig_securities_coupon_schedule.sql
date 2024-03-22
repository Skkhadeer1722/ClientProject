-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 103.177.224.100    Database: sss_nets_mig
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
-- Table structure for table `securities_coupon_schedule`
--

DROP TABLE IF EXISTS `securities_coupon_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `securities_coupon_schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `securities_code` varchar(12) NOT NULL,
  `coupon_date` int NOT NULL,
  `coupon_rate` decimal(13,10) DEFAULT NULL,
  `diary_entry_id` varchar(40) DEFAULT NULL,
  `compounded_rate` decimal(13,10) DEFAULT NULL COMMENT 'To store compounded rate from FRN calculation',
  PRIMARY KEY (`id`),
  KEY `fk_coupon_schedule_coupon_idx` (`securities_code`),
  CONSTRAINT `fk_coupon_schedule_coupon` FOREIGN KEY (`securities_code`) REFERENCES `securities_coupon` (`securities_code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ca_screen_securities-coupon_This table is store maintenance info of securities coupon schedules';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securities_coupon_schedule`
--

LOCK TABLES `securities_coupon_schedule` WRITE;
/*!40000 ALTER TABLE `securities_coupon_schedule` DISABLE KEYS */;
INSERT INTO `securities_coupon_schedule` VALUES (1,'MEPSUAT00016',20230908,2.6000000000,NULL,NULL),(2,'MEPSUAT00016',20230908,2.6000000000,NULL,NULL),(3,'MEPSUAT00024',20230622,2.6000000000,NULL,NULL),(4,'MEPSUAT00024',20230622,2.6000000000,NULL,NULL),(5,'MEPSUAT00024',20230622,2.6000000000,NULL,NULL),(6,'MEPSUAT00024',20230622,2.6000000000,NULL,NULL);
/*!40000 ALTER TABLE `securities_coupon_schedule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:34:32
