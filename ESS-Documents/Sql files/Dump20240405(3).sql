-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: EssDb
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.20.04.1

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
-- Table structure for table `attendance_correction`
--

DROP TABLE IF EXISTS attendance_correction;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE attendance_correction (
  id bigint NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  remark varchar(255) DEFAULT NULL,
  employee_id bigint DEFAULT NULL,
  leave_status varchar(20) DEFAULT 'PENDING',
  PRIMARY KEY (id),
  KEY FKcjep8rvju820bbq37hiok2cqf (employee_id),
  CONSTRAINT FKcjep8rvju820bbq37hiok2cqf FOREIGN KEY (employee_id) REFERENCES employees (employee_id)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance_correction`
--

LOCK TABLES attendance_correction WRITE;
/*!40000 ALTER TABLE attendance_correction DISABLE KEYS */;
INSERT INTO attendance_correction VALUES (1,'2024-04-03','asdfasdfsadfasdfasdfasdfasdfasdfs',1,'APPROVED'),(2,'2024-04-04',NULL,1,'APPROVED'),(8,'2024-04-02',NULL,1,'APPROVED'),(9,'2024-04-01',NULL,1,'APPROVED');
/*!40000 ALTER TABLE attendance_correction ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_personal_details`
--

DROP TABLE IF EXISTS employee_personal_details;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE employee_personal_details (
  id bigint NOT NULL AUTO_INCREMENT,
  current_address varchar(255) DEFAULT NULL,
  current_city varchar(255) DEFAULT NULL,
  current_country varchar(255) DEFAULT NULL,
  current_pin_code bigint DEFAULT NULL,
  current_state varchar(255) DEFAULT NULL,
  date_of_birth date DEFAULT NULL,
  emergency_contact_name_1 varchar(255) DEFAULT NULL,
  emergency_contact_name_2 varchar(255) DEFAULT NULL,
  emergency_contact_number_1 bigint DEFAULT NULL,
  emergency_contact_number_2 bigint DEFAULT NULL,
  permanent_address varchar(255) DEFAULT NULL,
  permanent_city varchar(255) DEFAULT NULL,
  permanent_country varchar(255) DEFAULT NULL,
  permanent_pin_code bigint DEFAULT NULL,
  permanent_state varchar(255) DEFAULT NULL,
  personal_email varchar(255) DEFAULT NULL,
  personal_mobile varchar(255) DEFAULT NULL,
  employee_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_to3dgp7umsi2croo7s1bibv70 (employee_id),
  CONSTRAINT FK2lwwg5nop0g2ap39t3ut0bey6 FOREIGN KEY (employee_id) REFERENCES employees (employee_id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_personal_details`
--

LOCK TABLES employee_personal_details WRITE;
/*!40000 ALTER TABLE employee_personal_details DISABLE KEYS */;
INSERT INTO employee_personal_details VALUES (2,NULL,NULL,NULL,NULL,NULL,'2002-11-17','Jaysukh kaneriya',NULL,9904656528,NULL,NULL,NULL,NULL,NULL,NULL,'20ceuos069@ddu.ac.in','9707556528',1);
/*!40000 ALTER TABLE employee_personal_details ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS employees;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE employees (
  employee_id bigint NOT NULL AUTO_INCREMENT,
  employee_email varchar(255) DEFAULT NULL,
  employee_firstname varchar(255) DEFAULT NULL,
  employee_lastname varchar(255) DEFAULT NULL,
  employee_password varchar(255) DEFAULT NULL,
  role_id bigint DEFAULT NULL,
  team_id bigint DEFAULT NULL,
  PRIMARY KEY (employee_id),
  UNIQUE KEY UK_qc2np93kx42ylhmdg3cdr7nha (employee_email),
  KEY FKah490190ww1q2a4piuv41hk6e (role_id),
  KEY FKa5cxjw6yuqlbp0np1g51o03gf (team_id),
  CONSTRAINT FKa5cxjw6yuqlbp0np1g51o03gf FOREIGN KEY (team_id) REFERENCES teams (team_id),
  CONSTRAINT FKah490190ww1q2a4piuv41hk6e FOREIGN KEY (role_id) REFERENCES roles (role_id)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES employees WRITE;
/*!40000 ALTER TABLE employees DISABLE KEYS */;
INSERT INTO employees VALUES (1,'adityakaneriya1711@gmail.com','Aditya','Kaneriya','$2a$10$PZJm4mOrZR/lJ48qYTmALO/MTEJ2VJLS9QGPLk/3u4BPcizjFAAj.',12,1),(4,'admin@gmail.com','Admin','Admin','$2a$10$PZJm4mOrZR/lJ48qYTmALO/MTEJ2VJLS9QGPLk/3u4BPcizjFAAj.',9,2),(6,'poojankaneriya3112@gmail.com','Poojan','Kaneriya','$2a$10$x56zUo/JUMFIKuxUEHvaSe.SOl2PKylff.pz3gLZbxcQf/q2bbcmm',10,1),(7,'aditya.upadhyay@gmail.com','Aditya','Updadhyay','$2a$10$DDh4yHTAytLLl5Yp4L30oePVR8W2svI3eALfsvuQUHpTHgDrN89.q',10,1),(8,'rohan.pansara@drcsystems.com','Rohan','Pansara','$2a$10$Chvt0F67BAtQ6M0wh5JLgOi4Gnth9tjU2zDyVwTnQzJtj4n0DNCOq',10,1),(9,'fenil.panseriya@gmail.com','Fenil','Panseriya','$2a$10$UmIJXL84Jb3G1DoOy7/4dOTBtXC441guB7993bF1o1cEtEfw0cdmK',10,4),(10,'aditya.upadhyay@drcsystems.com','Aditya','Upadhyay','$2a$10$G3TxOsxpiWwr1jZrk9k0LeBYsV7Zrh/UZHGBIEC1y4yjPyzG/AgjW',10,3),(11,'nilay.patel@drcsystems.com','Nilay','Patel','$2a$10$XZInPJfvD7UpC22ijUAeuej6JdOx32LuPNBeIxCtjNB5GTgClSv2e',10,3),(12,'fenil.panseriya@drcsystems.com','Fenil','Panseriya','$2a$10$5xuv.eQOzO6NOFTFwcmgJOOOj3xFXJjzlcxCSruN.6UKYE1tZvfii',12,3);
/*!40000 ALTER TABLE employees ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `holidays`
--

DROP TABLE IF EXISTS holidays;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE holidays (
  holiday_id bigint NOT NULL AUTO_INCREMENT,
  holiday_date date DEFAULT NULL,
  holiday_day tinyint DEFAULT NULL,
  holiday_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (holiday_id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `holidays`
--

LOCK TABLES holidays WRITE;
/*!40000 ALTER TABLE holidays DISABLE KEYS */;
INSERT INTO holidays VALUES (1,'2024-03-25',0,'Holiiii'),(2,'2024-01-15',0,'Uttrayan'),(3,'2023-03-08',2,'Holi'),(5,'2023-01-26',3,'Republic day');
/*!40000 ALTER TABLE holidays ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leaves`
--

DROP TABLE IF EXISTS leaves;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leaves` (
  id bigint NOT NULL AUTO_INCREMENT,
  leave_days bigint DEFAULT '1',
  leave_from date DEFAULT NULL,
  leave_reason varchar(255) DEFAULT NULL,
  leave_status varchar(20) DEFAULT 'PENDING',
  leave_to date DEFAULT NULL,
  leave_type enum('PAID','UNPAID') NOT NULL,
  employee_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK6kwhuq11saeyki0nc54elfap2 (employee_id),
  CONSTRAINT FK6kwhuq11saeyki0nc54elfap2 FOREIGN KEY (employee_id) REFERENCES employees (employee_id)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leaves`
--

LOCK TABLES leaves WRITE;
/*!40000 ALTER TABLE leaves DISABLE KEYS */;
INSERT INTO leaves VALUES (2,1,'2024-03-15','Function','REJECTED','2024-03-16','PAID',1),(3,1,'2024-03-13','Sick','APPROVED','2024-03-14','UNPAID',1),(4,3,'2024-03-27','Family function','APPROVED','2024-03-30','UNPAID',1),(5,1,'2024-03-20','Trip','REJECTED','2024-03-21','UNPAID',6),(6,2,'2024-03-27','Sick','REJECTED','2024-03-29','PAID',1),(7,2,'2024-04-01','Sick','PENDING','2024-04-03','UNPAID',1);
/*!40000 ALTER TABLE leaves ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_logs`
--

DROP TABLE IF EXISTS project_logs;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE project_logs (
  log_id bigint NOT NULL AUTO_INCREMENT,
  log_message varchar(255) DEFAULT NULL,
  log_timestamp datetime(6) DEFAULT NULL,
  project_id bigint DEFAULT NULL,
  PRIMARY KEY (log_id),
  KEY FKvbe1i8bmg2wo76370qquws89 (project_id),
  CONSTRAINT FKvbe1i8bmg2wo76370qquws89 FOREIGN KEY (project_id) REFERENCES projects (project_id)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_logs`
--

LOCK TABLES project_logs WRITE;
/*!40000 ALTER TABLE project_logs DISABLE KEYS */;
INSERT INTO project_logs VALUES (2,'Demo projectProject created','2024-03-13 16:43:58.912145',2),(3,'adityakaneriya1711@gmail.com Added to project: Demo project','2024-03-13 17:14:34.796875',2),(4,'Demo project-2Project created','2024-03-13 17:32:44.058503',3),(5,'adityakaneriya1711@gmail.com Added to project: Demo project','2024-03-13 17:49:19.349702',2),(6,'Role of adityakaneriya1711@gmail.com changed from MEMBER to MANAGER','2024-03-14 10:45:17.854468',2),(7,'poojankaneriya3112@gmail.com Added to project: Demo project-2 with role MANAGER','2024-03-14 10:47:00.956356',3),(8,'Demo-3Project created','2024-03-14 12:45:55.602776',4),(9,'Demo-4 Project created','2024-03-14 12:47:36.575933',5),(10,'Demo-5 Project created','2024-03-14 12:47:46.802484',6),(11,'Demo-6 Project created','2024-03-14 12:47:52.161917',7),(12,'Demo-7 Project created','2024-03-14 12:47:57.735979',8),(13,'Demo-8 Project created','2024-03-14 12:48:02.487020',9),(14,'Demo-9 Project created','2024-03-14 12:48:09.335978',10),(15,'Demo-10 Project created','2024-03-14 12:48:13.354886',11),(16,'Solve bug task assign to adityakaneriya1711@gmail.com by admin@gmail.com','2024-03-19 16:33:40.488259',2),(17,'Solve bug-2 task assign to adityakaneriya1711@gmail.com by admin@gmail.com','2024-03-20 16:46:40.048249',2),(18,'Solve bug-3 task assign to adityakaneriya1711@gmail.com by admin@gmail.com','2024-03-20 16:47:37.131004',2),(19,'asdf task assign to adityakaneriya1711@gmail.com by adityakaneriya1711@gmail.com','2024-03-22 11:46:26.653282',2),(20,'asdf-1 task assign to adityakaneriya1711@gmail.com by adityakaneriya1711@gmail.com','2024-03-22 11:49:26.263779',2),(21,'asdf-3 task assign to adityakaneriya1711@gmail.com by adityakaneriya1711@gmail.com','2024-03-22 11:50:18.092870',2),(22,'Aditya kaneriya task assign to adityakaneriya1711@gmail.com by adityakaneriya1711@gmail.com','2024-03-22 15:19:12.116850',2),(23,' updated task status of asdf-1 from TODO to TODO','2024-03-22 15:25:19.080027',NULL),(24,' updated task status of asdf from TODO to TODO','2024-03-22 15:25:27.938466',NULL),(25,' updated task status of Aditya kaneriya from TODO to TODO','2024-03-22 15:25:52.895557',NULL),(26,' updated task status of asdf from TODO to TODO','2024-03-22 15:27:19.956225',NULL),(27,' updated task status of asdf from DONE to DONE','2024-03-22 15:29:50.999003',NULL),(28,' updated task status of Solve bug-2 from IN_PROGRESS to IN_PROGRESS','2024-03-22 15:29:59.958777',NULL),(29,' updated task status of Aditya kaneriya from TODO to IN_REVIEW','2024-03-22 15:31:33.639177',NULL),(30,' updated task status of asdf-3 from TODO to IN_REVIEW','2024-03-22 15:31:38.402579',NULL),(31,' updated task status of asdf-1 from TODO to IN_REVIEW','2024-03-22 15:31:40.873610',NULL),(32,' updated task status of Aditya kaneriya from IN_REVIEW to IN_REVIEW','2024-03-22 15:31:43.766788',NULL),(33,' updated task status of asdf-1 from IN_REVIEW to IN_REVIEW','2024-03-22 15:31:45.848258',NULL),(34,' updated task status of asdf from DONE to IN_PROGRESS','2024-03-22 15:31:50.947269',NULL),(35,' updated task status of Aditya kaneriya from IN_REVIEW to DONE','2024-03-22 15:32:02.095246',NULL),(36,' updated task status of Aditya kaneriya from DONE to IN_REVIEW','2024-03-22 15:33:25.101471',NULL),(37,' updated task status of Aditya kaneriya from IN_REVIEW to IN_PROGRESS','2024-03-22 15:33:26.484807',NULL),(38,' updated task status of Aditya kaneriya from IN_PROGRESS to IN_REVIEW','2024-03-22 15:33:30.215952',NULL),(39,' updated task status of Aditya kaneriya from IN_REVIEW to DONE','2024-03-22 15:33:34.036240',NULL),(40,' updated task status of Aditya kaneriya from DONE to TODO','2024-03-22 15:33:39.593748',NULL),(41,' updated task status of asdf from IN_PROGRESS to TODO','2024-03-22 15:33:40.766567',NULL),(42,' updated task status of asdf-3 from IN_REVIEW to TODO','2024-03-22 15:33:42.288105',NULL),(43,' updated task status of asdf-1 from IN_REVIEW to TODO','2024-03-22 15:33:43.889604',NULL),(44,' updated task status of Aditya kaneriya from TODO to IN_REVIEW','2024-03-22 15:38:46.673321',NULL),(45,' updated task status of Aditya kaneriya from IN_REVIEW to IN_PROGRESS','2024-03-22 15:38:48.488092',NULL),(46,' updated task status of Aditya kaneriya from IN_PROGRESS to IN_REVIEW','2024-03-22 15:38:50.007172',NULL),(47,' updated task status of asdf-3 from TODO to IN_REVIEW','2024-03-22 17:21:49.926950',NULL),(48,' updated task status of asdf from TODO to IN_REVIEW','2024-03-22 17:21:51.169166',NULL),(49,' updated task status of Solve bug-3 from DONE to IN_REVIEW','2024-03-22 17:21:52.542591',NULL),(50,' updated task status of Aditya kaneriya from IN_REVIEW to DONE','2024-03-22 17:22:06.642412',NULL),(51,' updated task status of asdf from IN_REVIEW to DONE','2024-03-22 17:22:08.345126',NULL),(52,' updated task status of asdf-3 from IN_REVIEW to IN_PROGRESS','2024-03-22 17:22:10.213080',NULL),(53,' updated task status of asdf from DONE to DONE','2024-03-22 17:22:11.903571',NULL),(54,' updated task status of asdf from DONE to IN_PROGRESS','2024-03-22 17:22:14.858823',NULL),(55,' updated task status of Aditya kaneriya from DONE to IN_PROGRESS','2024-03-22 17:22:16.724289',NULL),(56,' updated task status of Aditya kaneriya from IN_PROGRESS to IN_PROGRESS','2024-03-22 17:22:18.654887',NULL),(57,' updated task status of Solve bug-2 from IN_PROGRESS to IN_PROGRESS','2024-03-22 17:22:20.114529',NULL),(58,' updated task status of asdf-1 from TODO to TODO','2024-03-22 17:22:22.749355',NULL),(59,' updated task status of asdf-3 from IN_PROGRESS to DONE','2024-03-22 17:22:26.590897',NULL),(60,' updated task status of asdf from IN_PROGRESS to IN_PROGRESS','2024-03-26 10:12:30.736578',NULL),(61,' updated task status of asdf from IN_PROGRESS to DONE','2024-03-26 11:41:10.573041',NULL),(62,' updated task status of asdf-3 from DONE to IN_REVIEW','2024-03-26 12:54:47.692748',NULL),(63,'poojankaneriya3112@gmail.com Added to project: Demo project with role MEMBER','2024-03-26 13:02:27.726191',2),(64,'Task for poojan task assign to poojankaneriya3112@gmail.com by adityakaneriya1711@gmail.com','2024-03-26 13:02:57.140951',2),(65,' updated task status of Task for poojan from IN_PROGRESS to IN_PROGRESS','2024-03-26 13:03:48.160725',NULL),(66,' updated task status of Task for poojan from IN_PROGRESS to DONE','2024-03-26 13:03:52.573411',NULL),(67,' updated task status of Task for poojan from DONE to IN_REVIEW','2024-03-26 13:03:54.406889',NULL),(68,' updated task status of Task for poojan from IN_REVIEW to DONE','2024-03-27 10:31:59.310148',NULL),(69,' updated task status of asdf-3 from IN_REVIEW to DONE','2024-03-27 10:34:13.974430',NULL),(70,' updated task status of Task for poojan from DONE to IN_REVIEW','2024-03-27 13:25:37.441634',NULL),(71,' updated task status of Aditya kaneriya from IN_PROGRESS to DONE','2024-03-27 13:25:38.884137',NULL),(72,' updated task status of Task for poojan from IN_REVIEW to IN_PROGRESS','2024-03-27 13:25:43.042138',NULL),(73,' updated task status of Aditya kaneriya from DONE to IN_REVIEW','2024-03-27 13:25:45.861871',NULL),(74,'adityakaneriya1711@gmail.com Added to project: Demo-3 with role MANAGER','2024-03-28 14:21:34.238145',4),(75,'adityakaneriya1711@gmail.com Added to project: Demo-4 with role MANAGER','2024-03-28 14:23:31.503192',5),(76,'Role of adityakaneriya1711@gmail.com changed from MANAGER to MEMBER','2024-03-28 14:27:04.369040',5),(77,'Role of adityakaneriya1711@gmail.com changed from MEMBER to MANAGER','2024-03-28 14:35:09.474391',5),(78,' updated task status of Task for poojan from IN_PROGRESS to IN_PROGRESS','2024-03-29 16:22:59.126275',NULL),(79,'Check priority task assign to poojankaneriya3112@gmail.com by adityakaneriya1711@gmail.com','2024-04-01 12:42:59.818074',2),(80,' updated task status of Check priority from IN_PROGRESS to IN_REVIEW','2024-04-03 13:21:27.784348',NULL),(81,' updated task status of Check priority from IN_REVIEW to IN_PROGRESS','2024-04-03 13:26:43.055956',2),(82,' updated assign to adityakaneriya1711@gmail.com from adityakaneriya1711@gmail.com by adityakaneriya1711@gmail.com','2024-04-03 14:03:50.674204',2),(83,' updated assign By adityakaneriya1711@gmail.com from adityakaneriya1711@gmail.com by adityakaneriya1711@gmail.com','2024-04-03 14:03:50.812047',2),(84,' updated description from asdfgadsgasdgasdf to asdfgadsgasdgasdf by adityakaneriya1711@gmail.com','2024-04-03 14:03:50.833150',2),(85,' Updated task Aditya kaneriya by adityakaneriya1711@gmail.com','2024-04-03 14:03:50.851953',2),(86,' updated description from This is just for poojan to This is just for poojan (Edited) by adityakaneriya1711@gmail.com','2024-04-03 14:16:43.034337',2),(87,' Updated task Task for poojan by adityakaneriya1711@gmail.com','2024-04-03 14:16:43.097140',2),(88,' Updated task Task for poojan by adityakaneriya1711@gmail.com','2024-04-03 14:17:03.975899',2),(89,' updated status from IN_PROGRESS to TODO by adityakaneriya1711@gmail.com','2024-04-03 14:17:34.106346',2),(90,' Updated task Task for poojan by adityakaneriya1711@gmail.com','2024-04-03 14:17:34.119362',2),(91,' Updated task Task for poojan by adityakaneriya1711@gmail.com','2024-04-03 14:17:56.988360',2),(92,' Updated task Task for poojan by adityakaneriya1711@gmail.com','2024-04-03 14:19:08.635481',2),(93,' updated priority from NONE to LOW by adityakaneriya1711@gmail.com','2024-04-03 14:25:52.637676',2),(94,' Updated task Solve bug-2 by adityakaneriya1711@gmail.com','2024-04-03 14:25:52.703370',2),(95,' updated description from null to asdfasdfasdf by adityakaneriya1711@gmail.com','2024-04-03 14:29:56.552412',2),(96,' Updated task Solve bug-2 by adityakaneriya1711@gmail.com','2024-04-03 14:29:56.634177',2),(97,' updated description from asdfasdfasdf to asdfasdfasdf {E} by adityakaneriya1711@gmail.com','2024-04-03 14:30:58.035520',2),(98,' updated priority from LOW to HIGH by adityakaneriya1711@gmail.com','2024-04-03 14:30:58.103590',2),(99,' Updated task Solve bug-2 by adityakaneriya1711@gmail.com','2024-04-03 14:30:58.119121',2),(100,' updated assign to adityakaneriya1711@gmail.com from adityakaneriya1711@gmail.com by adityakaneriya1711@gmail.com','2024-04-03 14:34:41.876958',2),(101,' Updated task Solve bug-2 by adityakaneriya1711@gmail.com','2024-04-03 14:34:41.937375',2),(102,' updated assign to adityakaneriya1711@gmail.com from adityakaneriya1711@gmail.com by adityakaneriya1711@gmail.com','2024-04-03 14:36:08.636122',2),(103,' Updated task Solve bug-2 by adityakaneriya1711@gmail.com','2024-04-03 14:36:08.689472',2),(104,' updated assign to adityakaneriya1711@gmail.com from adityakaneriya1711@gmail.com by adityakaneriya1711@gmail.com','2024-04-03 14:37:40.351445',2),(105,' Updated task Solve bug-2 by adityakaneriya1711@gmail.com','2024-04-03 14:37:40.364804',2),(106,' updated assign to adityakaneriya1711@gmail.com from poojankaneriya3112@gmail.com by adityakaneriya1711@gmail.com','2024-04-03 14:52:11.385529',2),(107,' Updated task Solve bug-2 by adityakaneriya1711@gmail.com','2024-04-03 14:52:11.433065',2),(108,' updated task status of Task for poojan from TODO to IN_REVIEW by adityakaneriya1711@gmail.com','2024-04-03 15:30:09.608846',2),(109,' updated assign to adityakaneriya1711@gmail.com from poojankaneriya3112@gmail.com by adityakaneriya1711@gmail.com','2024-04-03 15:30:34.563106',2),(110,' Updated task Solve bug by adityakaneriya1711@gmail.com','2024-04-03 15:30:34.608899',2),(111,' updated priority from NONE to MEDIUM by adityakaneriya1711@gmail.com','2024-04-03 15:30:46.277009',2),(112,' Updated task Solve bug by adityakaneriya1711@gmail.com','2024-04-03 15:30:46.288601',2),(113,' updated priority from NONE to HIGH by adityakaneriya1711@gmail.com','2024-04-03 15:32:23.905771',2),(114,' Updated task asdf-1 by adityakaneriya1711@gmail.com','2024-04-03 15:32:23.916987',2),(115,' updated priority from NONE to MEDIUM by adityakaneriya1711@gmail.com','2024-04-03 15:32:58.888115',2),(116,' Updated task Aditya kaneriya by adityakaneriya1711@gmail.com','2024-04-03 15:32:58.898658',2),(117,' updated priority from NONE to LOW by adityakaneriya1711@gmail.com','2024-04-03 15:37:58.564143',2),(118,' Updated task Solve bug-3 by adityakaneriya1711@gmail.com','2024-04-03 15:37:58.578077',2),(119,' updated task priority of Solve bug-2 from HIGH to HIGH by adityakaneriya1711@gmail.com','2024-04-03 16:03:44.355167',2),(120,' updated task priority of Solve bug-2 from HIGH to HIGH by adityakaneriya1711@gmail.com','2024-04-03 16:04:06.934137',2),(121,' updated task priority of Solve bug-3 from LOW to LOW by adityakaneriya1711@gmail.com','2024-04-03 16:05:07.158057',2),(122,' updated task priority of Task for poojan from NONE to NONE by adityakaneriya1711@gmail.com','2024-04-03 16:05:13.986352',2),(123,' updated task priority of Task for poojan from NONE to NONE by adityakaneriya1711@gmail.com','2024-04-03 16:05:25.679892',2),(124,' updated task priority of Solve bug-3 from LOW to LOW by adityakaneriya1711@gmail.com','2024-04-03 16:07:30.887886',2),(125,' updated task priority of Solve bug-3 from LOW to HIGH by adityakaneriya1711@gmail.com','2024-04-03 16:08:43.098681',2),(126,' updated task status of asdf from DONE to DONE by adityakaneriya1711@gmail.com','2024-04-04 13:48:02.749533',2),(127,' updated description from null to dkjhf ashdflkajshdfkanshdflk kn lsandf knkn lksandflkna sdf by adityakaneriya1711@gmail.com','2024-04-04 15:32:38.362638',2),(128,' Updated task Solve bug-3 by adityakaneriya1711@gmail.com','2024-04-04 15:32:38.420657',2),(129,' updated task status of Check priority from IN_PROGRESS to TODO by adityakaneriya1711@gmail.com','2024-04-04 15:32:57.377132',2),(130,' updated task status of Aditya kaneriya from IN_REVIEW to IN_PROGRESS by adityakaneriya1711@gmail.com','2024-04-04 15:34:13.502441',2),(131,' updated task status of asdf-1 from TODO to TODO by adityakaneriya1711@gmail.com','2024-04-05 17:12:46.835681',2),(132,' updated description from dkjhf ashdflkajshdfkanshdflk kn lsandf knkn lksandflkna sdf to This is very small bug it should be solved in short time only. by adityakaneriya1711@gmail.com','2024-04-05 17:14:28.033707',2),(133,' Updated task Solve bug-3 by adityakaneriya1711@gmail.com','2024-04-05 17:14:28.087259',2);
/*!40000 ALTER TABLE project_logs ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_members`
--

DROP TABLE IF EXISTS project_members;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE project_members (
  id bigint NOT NULL AUTO_INCREMENT,
  `role` enum('OWNER','MANAGER','MEMBER','EXTERNAL') DEFAULT NULL,
  employee_id bigint DEFAULT NULL,
  project_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK6hb0s9g0u21ta4s0bkrghncce (employee_id),
  KEY FKdki1sp2homqsdcvqm9yrix31g (project_id),
  CONSTRAINT FK6hb0s9g0u21ta4s0bkrghncce FOREIGN KEY (employee_id) REFERENCES employees (employee_id),
  CONSTRAINT FKdki1sp2homqsdcvqm9yrix31g FOREIGN KEY (project_id) REFERENCES projects (project_id)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_members`
--

LOCK TABLES project_members WRITE;
/*!40000 ALTER TABLE project_members DISABLE KEYS */;
INSERT INTO project_members VALUES (1,'MANAGER',1,2),(3,'MANAGER',6,3),(4,'MEMBER',6,2),(5,'MANAGER',1,4),(6,'MANAGER',1,5);
/*!40000 ALTER TABLE project_members ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_tasks`
--

DROP TABLE IF EXISTS project_tasks;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE project_tasks (
  task_id bigint NOT NULL AUTO_INCREMENT,
  task_name varchar(255) DEFAULT NULL,
  task_priority enum('NONE','LOW','MEDIUM','HIGH') DEFAULT NULL,
  task_status enum('TODO','IN_PROGRESS','IN_REVIEW','DONE') DEFAULT NULL,
  assign_by bigint DEFAULT NULL,
  assign_to bigint DEFAULT NULL,
  project_id bigint DEFAULT NULL,
  task_description varchar(255) DEFAULT NULL,
  PRIMARY KEY (task_id),
  KEY FK2wsi9hvxvp8erpmkv7lqcg0ma (assign_by),
  KEY FKmw9aqi3d6q4072yqrvos70n1j (assign_to),
  KEY FKhsx8wvsrs7t8x9hq10jncbisx (project_id),
  CONSTRAINT FK2wsi9hvxvp8erpmkv7lqcg0ma FOREIGN KEY (assign_by) REFERENCES employees (employee_id),
  CONSTRAINT FKhsx8wvsrs7t8x9hq10jncbisx FOREIGN KEY (project_id) REFERENCES projects (project_id),
  CONSTRAINT FKmw9aqi3d6q4072yqrvos70n1j FOREIGN KEY (assign_to) REFERENCES employees (employee_id)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_tasks`
--

LOCK TABLES project_tasks WRITE;
/*!40000 ALTER TABLE project_tasks DISABLE KEYS */;
INSERT INTO project_tasks VALUES (1,'Solve bug','MEDIUM','TODO',4,6,2,NULL),(2,'Solve bug-2','HIGH','IN_PROGRESS',4,6,2,'asdfasdfasdf {E}'),(3,'Solve bug-3','HIGH','IN_REVIEW',4,1,2,'This is very small bug it should be solved in short time only.'),(4,'asdf','NONE','DONE',1,1,2,'asdefadfssadf'),(5,'asdf-1','HIGH','TODO',1,1,2,'asdefadfssadf'),(6,'asdf-3','NONE','DONE',1,1,2,'adsfasdfadsf'),(7,'Aditya kaneriya','MEDIUM','IN_PROGRESS',1,1,2,'asdfgadsgasdgasdf'),(8,'Task for poojan','NONE','IN_REVIEW',1,6,2,'This is just for poojan (Edited)'),(9,'Check priority','HIGH','TODO',1,6,2,'This task is just for see if priority drop-down is showing proper priority or not.');
/*!40000 ALTER TABLE project_tasks ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS projects;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE projects (
  project_id bigint NOT NULL AUTO_INCREMENT,
  project_created_on datetime(6) DEFAULT NULL,
  project_last_modified datetime(6) DEFAULT NULL,
  project_name varchar(255) DEFAULT NULL,
  project_status enum('NEW','IN_PROGRESS','ON_HOLD','COMPLETED','CANCELED') DEFAULT NULL,
  PRIMARY KEY (project_id)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES projects WRITE;
/*!40000 ALTER TABLE projects DISABLE KEYS */;
INSERT INTO projects VALUES (2,'2024-03-13 16:43:58.818270','2024-03-13 16:43:58.818289','Demo project','NEW'),(3,'2024-03-13 17:32:43.967788',NULL,'Demo project-2','NEW'),(4,'2024-03-14 12:45:55.500866',NULL,'Demo-3','IN_PROGRESS'),(5,'2024-03-14 12:47:36.501778',NULL,'Demo-4','ON_HOLD'),(6,'2024-03-14 12:47:46.792620',NULL,'Demo-5','COMPLETED'),(7,'2024-03-14 12:47:52.151273',NULL,'Demo-6','CANCELED'),(8,'2024-03-14 12:47:57.725040',NULL,'Demo-7','IN_PROGRESS'),(9,'2024-03-14 12:48:02.476995',NULL,'Demo-8','IN_PROGRESS'),(10,'2024-03-14 12:48:09.325405',NULL,'Demo-9','COMPLETED'),(11,'2024-03-14 12:48:13.345088',NULL,'Demo-10','COMPLETED');
/*!40000 ALTER TABLE projects ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `punchin`
--

DROP TABLE IF EXISTS punchin;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE punchin (
  punchin_id bigint NOT NULL AUTO_INCREMENT,
  punchin_date date DEFAULT NULL,
  punchin_time time(6) DEFAULT NULL,
  employee_id bigint DEFAULT NULL,
  PRIMARY KEY (punchin_id),
  KEY FKem2s3kpijc08w90uyh6erlcib (employee_id),
  CONSTRAINT FKem2s3kpijc08w90uyh6erlcib FOREIGN KEY (employee_id) REFERENCES employees (employee_id)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `punchin`
--

LOCK TABLES punchin WRITE;
/*!40000 ALTER TABLE punchin DISABLE KEYS */;
INSERT INTO punchin VALUES (2,'2024-03-06','11:47:00.000000',1),(3,'2024-03-06','12:15:00.000000',1),(4,'2024-03-06','14:13:00.000000',1),(5,'2024-03-06','14:50:00.000000',1),(6,'2024-03-06','17:50:00.000000',1),(7,'2024-03-07','11:40:00.000000',1),(8,'2024-03-07','11:43:00.000000',1),(9,'2024-03-07','11:43:00.000000',1),(10,'2024-03-07','12:34:00.000000',1),(11,'2024-03-07','12:34:00.000000',1),(12,'2024-03-07','12:58:00.000000',1),(13,'2024-03-07','14:06:00.000000',1),(14,'2024-03-07','14:28:00.000000',1),(15,'2024-03-07','15:11:00.000000',1),(16,'2024-03-11','09:44:00.000000',1),(17,'2024-03-11','13:06:00.000000',1),(18,'2024-03-11','13:07:00.000000',1),(19,'2024-03-12','10:25:00.000000',1),(20,'2024-03-12','10:25:00.000000',1),(21,'2024-03-14','14:30:00.000000',4),(27,'2024-04-04','16:08:59.386000',4),(28,'2024-04-03','10:00:00.000000',1),(30,'2024-04-04','10:00:00.000000',1),(31,'2024-04-02','10:00:00.000000',1),(32,'2024-04-01','10:00:00.000000',1),(33,'2024-04-05','17:06:14.477000',1),(34,'2024-04-05','17:07:27.369000',1);
/*!40000 ALTER TABLE punchin ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `punchout`
--

DROP TABLE IF EXISTS punchout;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE punchout (
  punchout_id bigint NOT NULL AUTO_INCREMENT,
  punchout_date date DEFAULT NULL,
  punchout_time time(6) DEFAULT NULL,
  employee_id bigint DEFAULT NULL,
  PRIMARY KEY (punchout_id),
  KEY FK39vv9yodr2sd4vjrfwdny0233 (employee_id),
  CONSTRAINT FK39vv9yodr2sd4vjrfwdny0233 FOREIGN KEY (employee_id) REFERENCES employees (employee_id)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `punchout`
--

LOCK TABLES punchout WRITE;
/*!40000 ALTER TABLE punchout DISABLE KEYS */;
INSERT INTO punchout VALUES (1,'2024-03-06','12:13:00.000000',1),(2,'2024-03-06','14:13:00.000000',1),(3,'2024-03-06','17:03:00.000000',1),(4,'2024-03-06','17:03:00.000000',1),(5,'2024-03-06','17:03:00.000000',1),(6,'2024-03-07','11:42:00.000000',1),(7,'2024-03-07','11:42:00.000000',1),(8,'2024-03-07','12:34:00.000000',1),(9,'2024-03-07','12:35:00.000000',1),(10,'2024-03-07','12:35:00.000000',1),(11,'2024-03-07','12:35:00.000000',1),(12,'2024-03-07','14:15:00.000000',1),(13,'2024-03-06','18:00:00.000000',1),(14,'2024-03-07','15:00:00.000000',1),(15,'2024-03-07','18:00:00.000000',1),(16,'2024-03-11','13:06:00.000000',1),(17,'2024-03-14','14:30:00.000000',4),(22,'2024-04-04','16:10:05.438000',4),(23,'2024-04-03','17:30:00.000000',1),(25,'2024-04-04','17:30:00.000000',1),(26,'2024-04-02','17:30:00.000000',1),(27,'2024-04-01','17:30:00.000000',1),(28,'2024-04-05','17:07:07.590000',1);
/*!40000 ALTER TABLE punchout ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS roles;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE roles (
  role_id bigint NOT NULL AUTO_INCREMENT,
  role_clearance varchar(20) DEFAULT 'LEVEL_1',
  role_description varchar(255) DEFAULT NULL,
  role_name varchar(255) NOT NULL,
  PRIMARY KEY (role_id),
  UNIQUE KEY UK_716hgxp60ym1lifrdgp67xt5k (role_name)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES roles WRITE;
/*!40000 ALTER TABLE roles DISABLE KEYS */;
INSERT INTO roles VALUES (9,'LEVEL_1','ADMIN_DESCRIPTION','Admin'),(10,'LEVEL_2','EMPLOYEE_DESCRIPTION','Employee'),(12,'LEVEL_1','Manager can manage employee details in their corresponding teams..','Manager');
/*!40000 ALTER TABLE roles ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS teams;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE teams (
  team_id bigint NOT NULL AUTO_INCREMENT,
  team_description varchar(255) DEFAULT NULL,
  team_name varchar(255) NOT NULL,
  PRIMARY KEY (team_id),
  UNIQUE KEY UK_dsqu2wx93en6lbl2bnrjy7kol (team_name)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES teams WRITE;
/*!40000 ALTER TABLE teams DISABLE KEYS */;
INSERT INTO teams VALUES (1,'JAVA TEAM_DESCRIPTION','Java Team'),(2,'HR team manages everything here in the company.','HR'),(3,'This is python team','Python team'),(4,'Mongo + Express + React + Node','MERN - 1');
/*!40000 ALTER TABLE teams ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS users;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE users (
  id int NOT NULL,
  about varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES users WRITE;
/*!40000 ALTER TABLE users DISABLE KEYS */;
/*!40000 ALTER TABLE users ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_seq`
--

DROP TABLE IF EXISTS users_seq;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE users_seq (
  next_val bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_seq`
--

LOCK TABLES users_seq WRITE;
/*!40000 ALTER TABLE users_seq DISABLE KEYS */;
INSERT INTO users_seq VALUES (1);
/*!40000 ALTER TABLE users_seq ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-05 17:43:23
