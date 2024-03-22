-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: sss_nets_mig
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
-- Table structure for table `allotment_source`
--

DROP TABLE IF EXISTS `allotment_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allotment_source` (
  `id` varchar(36) DEFAULT NULL COMMENT 'Primary key (UUID)',
  `securities_code` varchar(50) DEFAULT NULL COMMENT 'Securities Code related to allotment record',
  `auction_date` varchar(50) DEFAULT NULL COMMENT 'Auction happended date of the Securities',
  `issuance_date` varchar(50) DEFAULT NULL COMMENT 'To display the date on which the securities are to be issued to participants',
  `allotment_price` varchar(50) DEFAULT NULL COMMENT 'Allotment price of the securies',
  `total_nominal_amount_alloted` varchar(50) DEFAULT NULL COMMENT 'Total nominal amount alloted already for that securities',
  `total_nominal_amount_to_be_alloted` varchar(50) DEFAULT NULL COMMENT 'Total nominal amount to be allotmented',
  `first_allotment` varchar(50) DEFAULT NULL COMMENT 'To display whether the current allotment of securities is the first allotment, i.e. allotment of a new issue and not a re-opening action',
  `total_settlement_amount` varchar(50) DEFAULT NULL COMMENT 'To display the total settlement amount of the securities to be issued',
  `processed` varchar(50) DEFAULT NULL COMMENT 'This indicator is used to check whether the allotment transaction is created or not',
  `action` varchar(50) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(50) DEFAULT NULL COMMENT 'Record status',
  `modified_by` varchar(50) DEFAULT NULL COMMENT 'Last modified User ID',
  `modified_date` varchar(50) DEFAULT NULL COMMENT 'Last modified timestamp',
  `approved_by` varchar(50) DEFAULT NULL COMMENT 'Last approved user ID',
  `approved_date` varchar(50) DEFAULT NULL COMMENT 'Last approved timestamp',
  `effective_date` varchar(50) DEFAULT NULL COMMENT 'Activation date of the record',
  `created_date` varchar(50) DEFAULT NULL COMMENT 'Created date of the record',
  `approval_remark` varchar(50) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` varchar(50) DEFAULT NULL COMMENT 'Approval flow ID',
  `planned_total_nominal_amount_allotted` varchar(50) DEFAULT NULL COMMENT 'Running total of allotment member nominal amount',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(1000) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='allotment_na_allotment_This table is used to save the allotment';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allotment_source`
--

LOCK TABLES `allotment_source` WRITE;
/*!40000 ALTER TABLE `allotment_source` DISABLE KEYS */;
INSERT INTO `allotment_source` VALUES ('49272c0d963d4c0aafa70484ad0eed88','MEPSUAT00016','20221010','2022-10-10','.44',NULL,'1000000','1','12.',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,'2023-01-06 18:15:33'),('3ff78c5dfe7643d7bf2c9377b9058c13','MEPSUAT00016','20221010','2022-10-10','.',NULL,'1000000','1','.',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,' Allotment Price : Field not decimal, Total Settlement Amount : Field not decimal','2023-01-06 18:15:34'),('d6c4404a029045cf9519ed2fe5ed1c51','MEPSUAT00016','20221010','2022-10-10','123.12345',NULL,'1000000','1','100.01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,'2023-01-06 18:15:34'),('e8bf9b229b2a4390866866068bbc94e3','MEPSUAT00016','20221010','2022-10-10','124.1',NULL,'1000000','1','1234567891234567.12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,NULL,'2023-01-06 18:15:34');
/*!40000 ALTER TABLE `allotment_source` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-09  9:20:02
