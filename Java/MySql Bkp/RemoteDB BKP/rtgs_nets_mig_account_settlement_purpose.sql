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
-- Table structure for table `account_settlement_purpose`
--

DROP TABLE IF EXISTS `account_settlement_purpose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_settlement_purpose` (
  `account_id` varchar(255) NOT NULL,
  `currency_code` varchar(255) DEFAULT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  CONSTRAINT `FK59fsg8h251oi3s8dibng84r4i` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_settlement_purpose`
--

LOCK TABLES `account_settlement_purpose` WRITE;
/*!40000 ALTER TABLE `account_settlement_purpose` DISABLE KEYS */;
INSERT INTO `account_settlement_purpose` VALUES ('1c8fc6b8-2227-4f9a-a794-4c63caa5f1cc',NULL,'SETT'),('2980f3d0-5f0d-4c43-ac08-9fabe9b54897',NULL,'SETT'),('30e0932a-964b-4771-b1ef-20b3cf273f9a',NULL,'SETT'),('4d4e1646-fb3f-4a6c-87df-398ed5cf08d8',NULL,'SETT'),('4da82b2e-8c37-4c2d-b84f-d91481dbc5ba',NULL,'SETT'),('4fc4b545-652b-46c1-a79c-2e3dfc01cf87',NULL,'SETT'),('6b0a561f-3601-48fe-bf46-0ab4a830d537',NULL,'SETT'),('a361b114-e5fd-4da0-a33f-b6d0d1c2ab8d',NULL,'SETT'),('c11442b3-667e-45e4-8dae-4dc06517057b',NULL,'SETT'),('c93c6b81-0ef2-47e8-baef-714d14cd4b3c',NULL,'SETT');
/*!40000 ALTER TABLE `account_settlement_purpose` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:32:00
