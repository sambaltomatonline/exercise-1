-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 14, 2019 at 02:43 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_example`
--
CREATE DATABASE IF NOT EXISTS `db_example` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db_example`;

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for table `author`
--
ALTER TABLE `author`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`id`, `email`, `gender`, `name`) VALUES
(1, 'email.author1@gmail.com', 'M', 'Author 1'),
(2, 'email.author2@gmail.com', 'F', 'Author 2');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);
  
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Fiksi'),
(2, 'Non Fiksi');

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `id` bigint(20) NOT NULL,
  `author_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5gbo4o7yxefxivwuqjichc67t` (`author_id`),
  ADD KEY `FKe4psgwi6953wvbqxruna75yax` (`category_id`);
  
--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
  
--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `FK5gbo4o7yxefxivwuqjichc67t` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  ADD CONSTRAINT `FKe4psgwi6953wvbqxruna75yax` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id`, `author_id`, `category_id`, `description`, `title`) VALUES
(1, 1, 1, 'Book 1 Description', 'Book 1'),
(2, 1, 2, 'Book 2 Description', 'Book 2'),
(3, 2, 2, 'Book 3 Description', 'Book 3');

-- --------------------------------------------------------

--
-- Table structure for table `t_order`
--

CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL,
  `createdDate` datetime NOT NULL,
  `total` decimal(19,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `t_order`
--
ALTER TABLE `t_order`
  ADD PRIMARY KEY (`id`);
  
--
-- AUTO_INCREMENT for table `t_order`
--
ALTER TABLE `t_order`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `t_order_detail`
--

CREATE TABLE `t_order_detail` (
  `id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `price` decimal(19,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `total` decimal(19,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `t_order_detail`
--
ALTER TABLE `t_order_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKm9jcw72hrsdbh7inwp6vkq49p` (`book_id`),
  ADD KEY `FKke2egp754lynigf01va6fhop6` (`order_id`);

--
-- AUTO_INCREMENT for table `t_order_detail`
--
ALTER TABLE `t_order_detail`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for table `t_order_detail`
--
ALTER TABLE `t_order_detail`
  ADD CONSTRAINT `FKke2egp754lynigf01va6fhop6` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`),
  ADD CONSTRAINT `FKm9jcw72hrsdbh7inwp6vkq49p` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
