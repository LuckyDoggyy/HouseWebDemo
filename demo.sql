-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: demo
-- ------------------------------------------------------
-- Server version	5.7.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `broker`
--

DROP TABLE IF EXISTS `broker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `broker` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `phone` varchar(20) NOT NULL DEFAULT '',
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `broker`
--

LOCK TABLES `broker` WRITE;
/*!40000 ALTER TABLE `broker` DISABLE KEYS */;
INSERT INTO `broker` VALUES (1,'Lorry','111111','xxy','xxy'),(2,'Asri','111111','xx','xx'),(3,'cc','222222','x','x'),(4,'','','',''),(5,'','','',''),(6,'','','',''),(7,'','','',''),(8,'','','',''),(9,'','','','');
/*!40000 ALTER TABLE `broker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house`
--

DROP TABLE IF EXISTS `house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `house` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bedroom` int(11) NOT NULL DEFAULT '0',
  `livroom` int(11) NOT NULL DEFAULT '0',
  `community` varchar(50) NOT NULL DEFAULT '',
  `address` varchar(50) NOT NULL DEFAULT '',
  `build_year` varchar(5) NOT NULL DEFAULT '2017',
  PRIMARY KEY (`id`),
  KEY `idx_bedroom` (`bedroom`),
  KEY `idx_livroom` (`livroom`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house`
--

LOCK TABLES `house` WRITE;
/*!40000 ALTER TABLE `house` DISABLE KEYS */;
INSERT INTO `house` VALUES (1,1,1,'','','2017'),(2,2,2,'','','2017'),(3,3,3,'','','2017'),(4,4,4,'','','2017'),(5,5,5,'','','2017'),(6,6,6,'','','2017'),(7,1,1,'fd','dfg','1980'),(8,1,1,'gh','fg','1980'),(9,1,1,'xx','xx','1980'),(10,1,1,'pudong','莘庄','1980'),(11,1,1,'人光','人广','1980'),(12,1,1,'普陀','金沙江','1980'),(13,1,1,'长宁','渡口','1980'),(14,1,1,'金城御苑','浦东','1980'),(15,1,1,'金城御苑','沙发','1989');
/*!40000 ALTER TABLE `house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house_desc`
--

DROP TABLE IF EXISTS `house_desc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `house_desc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(500) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house_desc`
--

LOCK TABLES `house_desc` WRITE;
/*!40000 ALTER TABLE `house_desc` DISABLE KEYS */;
INSERT INTO `house_desc` VALUES (1,''),(2,''),(3,''),(4,''),(5,''),(6,''),(7,'ss'),(8,'df'),(9,'432'),(10,'sdf');
/*!40000 ALTER TABLE `house_desc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house_info`
--

DROP TABLE IF EXISTS `house_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `house_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL DEFAULT '',
  `area` int(11) NOT NULL DEFAULT '0',
  `price` int(11) NOT NULL DEFAULT '0',
  `floor` int(11) NOT NULL DEFAULT '0',
  `tfloor` int(11) NOT NULL DEFAULT '0',
  `desc_id` int(11) NOT NULL DEFAULT '0',
  `broker_id` int(11) NOT NULL DEFAULT '0',
  `house_id` int(11) NOT NULL DEFAULT '0',
  `pub_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_area` (`area`),
  KEY `idx_price` (`price`),
  KEY `idx_pub_time` (`pub_time`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house_info`
--

LOCK TABLES `house_info` WRITE;
/*!40000 ALTER TABLE `house_info` DISABLE KEYS */;
INSERT INTO `house_info` VALUES (1,'',0,0,0,0,1,1,4,'2017-12-18 12:13:07'),(2,'',0,0,0,0,1,3,2,'2017-12-18 12:13:07'),(3,'',0,0,0,0,2,2,3,'2017-12-18 12:13:07'),(4,'',0,0,0,0,3,3,1,'2017-12-18 12:13:07'),(5,'',0,0,0,0,4,2,6,'2017-12-18 12:13:07'),(6,'',0,0,0,0,1,4,5,'2017-12-18 12:13:07'),(7,'',0,0,0,0,5,9,5,'2017-12-18 12:13:07'),(8,'',0,0,0,0,4,7,4,'2017-12-18 12:13:07'),(9,'',0,0,0,0,6,4,3,'2017-12-18 12:13:07'),(10,'',101,0,0,0,4,8,2,'2017-12-18 12:13:07'),(11,'',200,0,0,0,4,5,5,'2017-12-18 12:13:07'),(12,'',30,0,0,0,3,8,6,'2017-12-18 12:13:07'),(13,'',100,0,0,0,1,5,1,'2017-12-18 12:13:07'),(14,'xx',100,100,15,15,7,1,2,'2017-12-29 03:00:11'),(15,'xx',111,1333,11,33,8,3,2,'2017-12-29 03:15:37'),(16,'xs',123,441,12,13,9,2,1,'2017-12-30 02:16:22'),(17,'sdf',134,400,11,23,10,2,1,'2017-12-30 10:16:21');
/*!40000 ALTER TABLE `house_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-12 16:17:41
