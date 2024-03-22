-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 103.177.224.100    Database: test
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
-- Table structure for table `member_liaison`
--

DROP TABLE IF EXISTS `member_liaison`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_liaison` (
  `id` varchar(100) NOT NULL,
  `member_code` varchar(100) DEFAULT NULL,
  `liaison_officer_name` varchar(100) DEFAULT NULL,
  `liaison_officer_contact_number` varchar(100) DEFAULT NULL,
  `liaison_officer_email_address` varchar(100) DEFAULT NULL,
  `registered_address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_liaison`
--

LOCK TABLES `member_liaison` WRITE;
/*!40000 ALTER TABLE `member_liaison` DISABLE KEYS */;
INSERT INTO `member_liaison` VALUES ('1855487e-1c2d-4f88-a163-311d56d414e0','2','I','I','I','I'),('2e2b0bc6-c696-41c6-9bbf-69a5c019d26c','2','I','I','I','I'),('2efd1e4e-2e86-491b-85bc-fa069ada354e','2','I','I','I','I'),('5f847199-591f-4f75-82cb-0dfcec97fc9a','2','I','I','I','I'),('947acb3c-324a-45ad-8706-d1877c462400','2','I','I','I','I'),('d3f64a9e-0fe4-4004-89e6-39e85db997ee','2','I','I','I','I'),('ebaeb02b-92ef-4d8d-89f9-ee57862cbfab','2','I','I','I','I'),('fdf7f25e-3870-45cd-a578-d7e19515cc98','2','I','I','I','I');
/*!40000 ALTER TABLE `member_liaison` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:33:30
