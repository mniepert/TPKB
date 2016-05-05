-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 13, 2015 at 10:32 AM
-- Server version: 5.5.43-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tpkb`
--

-- --------------------------------------------------------

--
-- Table structure for table `TIs`
--

CREATE TABLE IF NOT EXISTS `TIs` (
  `object` varchar(20) NOT NULL COMMENT 'path of the object',
  `objectDepth` tinyint(4) NOT NULL COMMENT 'depth of the object in the part decomposition',
  `class` varchar(20) NOT NULL COMMENT 'class name',
  `classDepth` tinyint(4) NOT NULL COMMENT 'depth of the class in the class hierarchy',
  `declAsClass` varchar(20) DEFAULT NULL COMMENT 'the class the object was declared to be an instance of',
  `declInClass` varchar(20) DEFAULT NULL COMMENT 'the class in which the object was declared',
  `declObject` varchar(20) DEFAULT NULL COMMENT 'the parent of the object',
  `weight` double NOT NULL DEFAULT '1' COMMENT 'the weight of the sub-partition function Z(O,C)',
  PRIMARY KEY (`object`,`class`),
  KEY `classDepth` (`classDepth`),
  KEY `class` (`class`),
  KEY `class_2` (`class`,`declAsClass`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `TSub`
--

CREATE TABLE IF NOT EXISTS `TSub` (
  `class` varchar(20) NOT NULL COMMENT 'name of the class',
  `classDepth` tinyint(4) NOT NULL COMMENT 'depth of the class',
  `superclass` varchar(20) NOT NULL COMMENT 'parent of the class',
  `weight` double NOT NULL COMMENT 'weight of the subclass relation',
  PRIMARY KEY (`class`,`superclass`),
  KEY `classDepth` (`classDepth`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
