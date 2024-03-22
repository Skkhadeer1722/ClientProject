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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `member_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `member_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Bank/FI name',
  `member_short_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Bank/FI short name',
  `member_type` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `member_status` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `activation_date` date NOT NULL,
  `swift_member` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `swift_bic` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `location_code` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '3 digit location code',
  `fi_group` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fi_code` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sector_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `currency_code_subscription` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `tax_rate` varchar(100) DEFAULT NULL,
  `fee_profile_id` varchar(100) DEFAULT NULL,
  `mcb_id` varchar(100) DEFAULT NULL,
  `intrday_mcb_requirement` varchar(100) DEFAULT NULL,
  `minimum_mcb_requirement` varchar(100) DEFAULT NULL,
  `maximum_mcb_requirement` varchar(100) DEFAULT NULL,
  `average_mcb_requirement` varchar(100) DEFAULT NULL,
  `uen` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Company registartion number',
  `lei` varchar(20) DEFAULT NULL,
  `exempted_from_billing` tinyint(1) DEFAULT NULL,
  `exempted_from_system_limit` tinyint(1) DEFAULT NULL,
  `currency_code` varchar(3) DEFAULT NULL,
  `currency_member_code` varchar(36) DEFAULT NULL,
  `currency_settlement_agent` varchar(100) DEFAULT NULL,
  `member_liaison` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdnsaf6awge7jw0hn4nsq0ey0v` (`member_liaison`),
  CONSTRAINT `FKdnsaf6awge7jw0hn4nsq0ey0v` FOREIGN KEY (`member_liaison`) REFERENCES `member_liaison` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('19250c21-97bf-4f33-b00e-27d56635dea0','1','TEST','TST','T','S','2020-12-27','T','T','LC','F','F','S','C','B','T','I','I','I','I','I','I','I',1,1,'I','I','I','2efd1e4e-2e86-491b-85bc-fa069ada354e'),('2aa0d940-893a-4d61-8770-5b20b2317a4c','1','TEST','TST','T','S','2020-12-27','T','T','LC','F','F','S','C','B','T','I','I','I','I','I','I','I',1,1,'I','I','I','2e2b0bc6-c696-41c6-9bbf-69a5c019d26c'),('741448be-c88b-4659-a991-eb1a6bd7ee1f','1','TEST','TST','T','S','2020-12-27','T','T','LC','F','F','S','C','B','T','I','I','I','I','I','I','I',1,1,'I','I','I','d3f64a9e-0fe4-4004-89e6-39e85db997ee'),('a5b67f08-e296-49c0-aee3-5f6375dbeb93','1','TEST','TST','T','S','2020-12-27','T','T','LC','F','F','S','C','B','T','I','I','I','I','I','I','I',1,1,'I','I','I','ebaeb02b-92ef-4d8d-89f9-ee57862cbfab'),('c53c7bc8-ebde-4b09-92d3-36a1978128c4','1','TEST','TST','T','S','2020-12-27','T','T','LC','F','F','S','C','B','T','I','I','I','I','I','I','I',1,1,'I','I','I','fdf7f25e-3870-45cd-a578-d7e19515cc98'),('c6e03a4e-7289-447f-b7e4-d184865453a4','1','TEST','TST','T','S','2020-12-27','T','T','LC','F','F','S','C','B','T','I','I','I','I','I','I','I',1,1,'I','I','I','5f847199-591f-4f75-82cb-0dfcec97fc9a'),('d024d717-5f8b-4f7f-a9dd-f665814591f9','1','TEST','TST','T','S','2020-12-27','T','T','LC','F','F','S','C','B','T','I','I','I','I','I','I','I',1,1,'I','I','I','947acb3c-324a-45ad-8706-d1877c462400'),('e9742bee-1508-4ff7-aac6-c45ca90de6a2','1','TEST','TST','T','S','2020-12-27','T','T','LC','F','F','S','C','B','T','I','I','I','I','I','I','I',1,1,'I','I','I','1855487e-1c2d-4f88-a163-311d56d414e0');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:33:28
