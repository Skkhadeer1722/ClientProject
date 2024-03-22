-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 103.177.224.100    Database: cbs-api
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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL,
  `active` int NOT NULL,
  `created_timestamp` datetime(6) DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `doc_key` varchar(255) DEFAULT NULL,
  `field_01` varchar(255) DEFAULT NULL,
  `field_02` varchar(255) DEFAULT NULL,
  `field_03` varchar(255) DEFAULT NULL,
  `field_04` varchar(255) DEFAULT NULL,
  `field_05` varchar(255) DEFAULT NULL,
  `field_06` varchar(255) DEFAULT NULL,
  `field_07` varchar(255) DEFAULT NULL,
  `field_08` varchar(255) DEFAULT NULL,
  `field_09` varchar(255) DEFAULT NULL,
  `field_10` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `meta_data` text,
  `source_id` varchar(255) DEFAULT NULL,
  `sync_status` bit(1) DEFAULT NULL,
  `tenant_id` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `updated_timestamp` datetime(6) DEFAULT NULL,
  `version` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jt63q2suy91q2uch0ll9wcxx5` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,1,'2022-03-31 14:39:54.266400','cf4b0154-49ca-4d0a-b5b1-94df58df933e','Swamy Batchu|B|M|Tue Jan 01 05:30:00 IST 1991|SINGLE',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'M','{\"customerActive\":false,\"countryOfBirth\":\"IND\",\"countryOfResidence\":\"IND\",\"dateOfBirth\":\"1986-01-29\",\"maritalStatus\":\"SINGLE\",\"type\":\"INDIVIDUAL\",\"gender\":\"M\",\"name\":{\"id\":0,\"firstName\":\"SATYA\",\"lastName\":\"ndg\",\"pronouns\":\"HIM\"},\"contactDetails\":{\"emails\":[{\"id\":\"b07958a3-a71f-46ad-82e4-2dc36f45e24c\",\"email\":\"work2@abc.com\",\"type\":\"WORK\",\"primary\":false,\"verified\":false},{\"id\":\"efbd9e06-5cc3-4323-8734-85d0b609e767\",\"email\":\"teja234@gmail.com\",\"type\":\"PERSONAL\",\"primary\":true,\"verified\":false}],\"telephones\":[{\"id\":\"bae9f9e9-6c28-472d-bae4-c4ccdd935592\",\"country_code\":\"IND\",\"type\":\"HOME\",\"nationalNumber\":\"987654320\",\"primary\":false,\"verified\":false},{\"id\":\"aea41c7b-4983-4c9d-a879-1565d84d95ae\",\"country_code\":\"IND\",\"type\":\"MOBILE\",\"nationalNumber\":\"9440562301\",\"primary\":true,\"verified\":false}],\"otherAddresses\":[{\"id\":\"097f92a8-cf01-4e4c-b353-64455ec55304\",\"address_line1\":\"2nd line\",\"address_line2\":\"yusuf guds\",\"townorcity\":\"hyderabad\",\"country\":\"netherlands\",\"goneawaystatus\":false,\"country_code\":\"mncb09\"}],\"registeredAddress\":{\"id\":\"096a6dd7-8474-49c6-b7f5-e7ce5e00278b\",\"address_line1\":\"Street 1\",\"address_line2\":\"line 2\",\"townorcity\":\"HYD\",\"country\":\"USA\",\"goneawaystatus\":true,\"country_code\":\"IND\"}},\"otherNationalities\":[{\"countryCode\":\"IND\",\"nationalIdentifiers\":[{\"identifierType\":\"PASSPORT_NUMBER\",\"identifierValue\":\"98347465773\"}]}],\"placeOfBirth\":\"IND\",\"primaryNationality\":{\"id\":3,\"countryCode\":\"IND\",\"nationalIdentifiers\":[{\"id\":1,\"identifierType\":\"DRIVING_LICENCE_NUMBER\",\"identifierValue\":\"3495934534052\"}]},\"taxResidences\":[\"IND\"],\"communicationPreference\":\"MOBILE\",\"regulatoryDocs\":\"DIGITAL\",\"communicationNeeds\":\"BRAILLE\"}',NULL,NULL,NULL,NULL,'2022-04-06 16:30:51.247102',12);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-03 11:31:38
