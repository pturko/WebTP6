-- phpMyAdmin SQL Dump
-- version 2.6.1
-- http://www.phpmyadmin.net
-- 
-- Host: localhost
-- Generation Time: Jul 29, 2013 at 12:07 AM
-- Server version: 5.0.45
-- PHP Version: 5.2.4
-- 
-- Database: `t_Turko_project`
-- 

CREATE DATABASE `t_Turko_project` DEFAULT CHARACTER SET cp1251 COLLATE cp1251_general_ci;
USE t_Turko_project;

-- --------------------------------------------------------

-- 
-- Table structure for table `author`
-- 

CREATE TABLE `author` (
  `AUTH_ID` int(50) NOT NULL auto_increment,
  `NAME` varchar(50) NOT NULL,
  `SURNAME` varchar(50) NOT NULL,
  PRIMARY KEY  (`AUTH_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

-- 
-- Dumping data for table `author`
-- 

INSERT INTO `author` VALUES (1, 'Виктор', 'Сорокин');
INSERT INTO `author` VALUES (2, 'Андрей', 'Крузин');
INSERT INTO `author` VALUES (3, 'Антон', 'Чехов');
INSERT INTO `author` VALUES (7, 'Style', 'Company');

-- --------------------------------------------------------

-- 
-- Table structure for table `genre`
-- 

CREATE TABLE `genre` (
  `GENRE_ID` int(50) NOT NULL auto_increment,
  `TITLE` varchar(50) NOT NULL,
  PRIMARY KEY  (`GENRE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

-- 
-- Dumping data for table `genre`
-- 

INSERT INTO `genre` VALUES (1, 'Научное');
INSERT INTO `genre` VALUES (2, 'Бизнес');
INSERT INTO `genre` VALUES (3, 'Туризм');
INSERT INTO `genre` VALUES (6, 'Женское');

-- --------------------------------------------------------

-- 
-- Table structure for table `publication`
-- 

CREATE TABLE `publication` (
  `PUBL_ID` int(50) NOT NULL auto_increment,
  `NAME` text NOT NULL,
  `AUTH_ID` text NOT NULL,
  `GENRE_ID` varchar(25) NOT NULL,
  `PRICE` int(10) NOT NULL,
  `DESCRIPTION` text NOT NULL,
  `IMAGE_PATH` varchar(50) default NULL,
  PRIMARY KEY  (`PUBL_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

-- 
-- Dumping data for table `publication`
-- 

INSERT INTO `publication` VALUES (1, 'Современные технологии автоматизации', '1', '1', 45, 'Современные технологии автоматизации (СТА) — профессиональный научно-технический журнал для специалистов и управленцев, работающих в сфере АСУ ТП, встраиваемых систем и в других смежных областях. Он предназначен как для разработчиков и системных интеграторов, так и для конечных пользователей систем автоматизации.\r\n\r\nВыходит ежеквартально с 1996 года тиражом до 20 тыс. экземпляров, имеет объём до 128 страниц и содержит компакт-диски с каталогами продукции или программным обеспечением.', 'automation.jpg');
INSERT INTO `publication` VALUES (2, 'Бухгалтерия', '2', '2', 25, 'Основной управленческой задачей бухгалтерского учёта (иначе говоря, задачей, возлагаемой на бухгалтерию) является сбор и обработка полной и достоверной информации о деятельности хозяйствующего субъекта. Итоговая бухгалтерская информация в виде, пригодном для экономического анализа и принятия на его основе управленческих решений, формируется в виде финансовой отчётности.', 'buhgalter.jpg');
INSERT INTO `publication` VALUES (3, 'Налоговый кодекс', '3', '2', 70, 'Ведение учета доходов арбитражным управляющим, одновременно осуществляющим адвокатскую деятельность\r\n\r\nКак вести учет и представлять отчетность физическому лицу, осуществляющему независимую профессиональную деятельность в качестве арбитражного управляющего и одновременно адвокатскую деятельность.', 'nalogi.jpg');
INSERT INTO `publication` VALUES (4, 'Бюджетная бухгалтерия', '3', '2', 90, 'Полный объем информации:\r\n\r\n    нормативные документы с комментариями специалистов редакции, министерств и ведомств\r\n    публикации материалов, предоставленные работниками Государственного казначейства, контрольно-ревизионной службы, ГНАУ, Минздрава, Минобразования и других официальных органов\r\n    консультации по вопросам бухгалтерского и налогового учета в бюджетной сфере\r\n    сметы, учет обязательств бюджетных учреждений и организаций\r\n    собенности трудовых отношений в бюджетной сфере\r\n    нюансы составления финансовой, налоговой, статистической отчетности\r\n    акупка товаров, работ и услуг за государственные средства', 'nalog_budz.jpg');
INSERT INTO `publication` VALUES (5, 'Украинский туризм', '3', '3', 50, 'В каждом номере публикуются материалы, в которых представлена исчерпывающая информация по рынку авиаперевозок, гостиничному обслуживанию, страхованию, морским круизам, туризму, новым технологиям, а также последние изменения в законодательной базе с комментариями экспертов и специалистов туристической отрасли.', 'turizm.jpg');
INSERT INTO `publication` VALUES (6, 'Наука и жизнь', '2', '1', 45, '«Наука и жизнь» − старейший и самый известный научно-популярный журнал России. Первый номер вышел в 1890 году.\r\nДевиз нашего издания: «О науке – доступно, о жизни – серьёзно». Целевая аудитория журнала не ограничена возрастными или профессиональными рамками. «Наука и жизнь» предназначена для читателей с научным складом ума и неукротимой любознательностью. Один-единственный номер журнала «Наука и жизнь» вполне может заменить сотни умных книжек: журнал расширяет кругозор, дает пищу для ума и побуждает к активной творческой деятельности.', 'nij.jpg');
INSERT INTO `publication` VALUES (7, 'Женское мнение', '4', '4', 40, 'Мы привыкли к тому, что креативные стрижки и окрашивание – это стиль не для всех, а, в основном, для узкого круга профи, тинэйджеров и неформалов. Однако, парикмахерская мода последних двух лет активно опровергает этот принцип. Все больше молодых  (да и не только) женщин стремятся к яркому самовыражению через прическу. В начале, речь шла о цвете волос: в той или иной степени (на кончиках, попрядно, или по всей длине волос) радужно яркие тона и их сочетания украсили головы многих юных модниц. Теперь настала очередь непосредственно стрижек.', 'woman.jpg');

-- --------------------------------------------------------

-- 
-- Table structure for table `subscription`
-- 

CREATE TABLE `subscription` (
  `SUBS_ID` int(50) NOT NULL auto_increment,
  `PUBL_ID` int(50) default NULL,
  `USER_ID` int(50) default NULL,
  `PAY` int(50) default NULL,
  `PRICE` int(50) default NULL,
  `DATE` varchar(25) default NULL,
  PRIMARY KEY  (`SUBS_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

-- 
-- Dumping data for table `subscription`
-- 

INSERT INTO `subscription` VALUES (1, 1, 2, 1, 40, '27-07-2013 20:21:45');
INSERT INTO `subscription` VALUES (2, 5, 2, 1, 50, '28-07-2013 23:52:58');

-- --------------------------------------------------------

-- 
-- Table structure for table `users`
-- 

CREATE TABLE `users` (
  `USER_ID` int(50) NOT NULL auto_increment,
  `LOGIN` varchar(35) NOT NULL,
  `PASSWORD` varchar(35) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `SURNAME` varchar(100) NOT NULL,
  `BALANCE` int(50) NOT NULL,
  `USER_TYPE` int(5) default NULL,
  `LAST_LOGIN` date NOT NULL,
  PRIMARY KEY  (`USER_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

-- 
-- Dumping data for table `users`
-- 

INSERT INTO `users` VALUES (1, 'admin', 'admin', 'Алексей', 'Ясинский', 0, 0, '2013-07-18');
INSERT INTO `users` VALUES (2, 'root', 'root', 'Василий', 'Пупкин', 150, 1, '2013-07-27');
        