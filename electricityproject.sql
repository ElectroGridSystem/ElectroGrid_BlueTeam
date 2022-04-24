-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 24, 2022 at 05:05 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `electricityproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `branch1`
--

CREATE TABLE IF NOT EXISTS `branch1` (
  `BranchID` int(6) NOT NULL AUTO_INCREMENT,
  `BranchName` varchar(40) NOT NULL,
  `Category` varchar(50) NOT NULL,
  `Location` varchar(60) NOT NULL,
  `PowerCapacity` varchar(50) NOT NULL,
  `HeadEngineer` varchar(80) NOT NULL,
  PRIMARY KEY (`BranchID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `user1`
--

CREATE TABLE IF NOT EXISTS `user1` (
  `UID` int(6) NOT NULL AUTO_INCREMENT,
  `Name` varchar(60) NOT NULL,
  `ACC_Num` varchar(70) NOT NULL,
  `Email` varchar(80) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Phone` varchar(10) NOT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
