-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Sep 06, 2020 at 08:09 AM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_acc`
--

-- --------------------------------------------------------

--
-- Table structure for table `acc_balance`
--

DROP TABLE IF EXISTS `acc_balance`;
CREATE TABLE IF NOT EXISTS `acc_balance` (
  `acc_no` decimal(10,0) DEFAULT NULL,
  `acc_bal` decimal(10,0) DEFAULT NULL,
  KEY `acc_no` (`acc_no`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `acc_balance`
--

INSERT INTO `acc_balance` (`acc_no`, `acc_bal`) VALUES
('123451', '3500'),
('123452', '1000'),
('123453', '1000'),
('123454', '50'),
('123455', '10000100'),
('123456', '4500'),
('1', '99');

-- --------------------------------------------------------

--
-- Table structure for table `acc_holder`
--

DROP TABLE IF EXISTS `acc_holder`;
CREATE TABLE IF NOT EXISTS `acc_holder` (
  `acc_no` decimal(10,0) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `pin` decimal(6,0) NOT NULL,
  PRIMARY KEY (`acc_no`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `acc_holder`
--

INSERT INTO `acc_holder` (`acc_no`, `name`, `pin`) VALUES
('123456', 'Hemant', '123456'),
('123451', 'Harish', '123456'),
('123452', 'Kamla', '123456'),
('123453', 'Sanjay', '123456'),
('123454', 'Rohit', '123456'),
('123455', 'Vinay', '123456'),
('1', 'try', '2');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
