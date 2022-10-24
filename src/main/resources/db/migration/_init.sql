-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 24, 2022 at 06:26 AM
-- Server version: 8.0.17
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `garbage_classifier`
--

-- --------------------------------------------------------

--
-- Table structure for table `bank_acct`
--

CREATE TABLE `bank_acct` (
  `id` bigint(20) NOT NULL,
  `account_code` varchar(255) DEFAULT NULL,
  `bank_type_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bank_type`
--

CREATE TABLE `bank_type` (
  `id` bigint(20) NOT NULL,
  `bank_code` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `garbage_record`
--

CREATE TABLE `garbage_record` (
  `id` bigint(20) NOT NULL,
  `weight` double DEFAULT NULL,
  `garbage_type` bigint(20) DEFAULT NULL,
  `machine_id` bigint(20) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `garbage_type`
--

CREATE TABLE `garbage_type` (
  `id` bigint(20) NOT NULL,
  `type_name` varchar(255) DEFAULT NULL,
  `unit_price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `machine`
--

CREATE TABLE `machine` (
  `id` bigint(20) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `machine_lock` tinyint(1) DEFAULT '0',
  `user_lock` tinyint(1) DEFAULT '0',
  `user_id` bigint(20) DEFAULT NULL,
  `machine_picture` longblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `machine_storage`
--

CREATE TABLE `machine_storage` (
  `id` bigint(20) NOT NULL,
  `storage` double DEFAULT NULL,
  `time_stamp` datetime DEFAULT NULL,
  `garbage_type` bigint(20) DEFAULT NULL,
  `machine` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transfer_money_record`
--

CREATE TABLE `transfer_money_record` (
  `id` int(11) NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `time_stamp` datetime DEFAULT NULL,
  `receiver` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `firebase_token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `wallet`
--

CREATE TABLE `wallet` (
  `id` int(11) NOT NULL,
  `change_value` decimal(19,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `time_stamp` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bank_acct`
--
ALTER TABLE `bank_acct`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhbrwrhfgqsq26wlrv7vxf9wf9` (`bank_type_id`),
  ADD KEY `FKeje21844gsyb462sok3nmfsfj` (`user_id`);

--
-- Indexes for table `bank_type`
--
ALTER TABLE `bank_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `garbage_record`
--
ALTER TABLE `garbage_record`
  ADD KEY `FKb829i9sw75du2g8njd40xcahm` (`machine_id`),
  ADD KEY `FKs3m8u7abqt3lw2gb2v27dj397` (`user`),
  ADD KEY `FKmndjaju0yqj8c4kjbx5bfx2ab` (`garbage_type`);

--
-- Indexes for table `garbage_type`
--
ALTER TABLE `garbage_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `machine`
--
ALTER TABLE `machine`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4qe89atf3mesjqlktcf7tt9hs` (`user_id`);

--
-- Indexes for table `machine_storage`
--
ALTER TABLE `machine_storage`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK52o5grvp5pap34nkanwkqp0mr` (`garbage_type`),
  ADD KEY `FKmoqqlm9xcdfj713oa214arkhp` (`machine`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `transfer_money_record`
--
ALTER TABLE `transfer_money_record`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1sxar2r5lmwxnx10gml3dvxvs` (`receiver`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK_k8d0f2n7n88w1a16yhua64onx` (`user_name`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`);

--
-- Indexes for table `wallet`
--
ALTER TABLE `wallet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgbusavqq0bdaodex4ee6v0811` (`user_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bank_acct`
--
ALTER TABLE `bank_acct`
  ADD CONSTRAINT `FKeje21844gsyb462sok3nmfsfj` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKhbrwrhfgqsq26wlrv7vxf9wf9` FOREIGN KEY (`bank_type_id`) REFERENCES `bank_type` (`id`);

--
-- Constraints for table `garbage_record`
--
ALTER TABLE `garbage_record`
  ADD CONSTRAINT `FKb829i9sw75du2g8njd40xcahm` FOREIGN KEY (`machine_id`) REFERENCES `machine` (`id`),
  ADD CONSTRAINT `FKmndjaju0yqj8c4kjbx5bfx2ab` FOREIGN KEY (`garbage_type`) REFERENCES `garbage_type` (`id`),
  ADD CONSTRAINT `FKs3m8u7abqt3lw2gb2v27dj397` FOREIGN KEY (`user`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `machine`
--
ALTER TABLE `machine`
  ADD CONSTRAINT `FK4qe89atf3mesjqlktcf7tt9hs` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `machine_storage`
--
ALTER TABLE `machine_storage`
  ADD CONSTRAINT `FK52o5grvp5pap34nkanwkqp0mr` FOREIGN KEY (`garbage_type`) REFERENCES `garbage_type` (`id`),
  ADD CONSTRAINT `FKmoqqlm9xcdfj713oa214arkhp` FOREIGN KEY (`machine`) REFERENCES `machine` (`id`);

--
-- Constraints for table `transfer_money_record`
--
ALTER TABLE `transfer_money_record`
  ADD CONSTRAINT `FK1sxar2r5lmwxnx10gml3dvxvs` FOREIGN KEY (`receiver`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);

--
-- Constraints for table `wallet`
--
ALTER TABLE `wallet`
  ADD CONSTRAINT `FKgbusavqq0bdaodex4ee6v0811` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
