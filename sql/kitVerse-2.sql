-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 21, 2026 at 08:16 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kitVerse`
--

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `total_amt` decimal(10,2) NOT NULL,
  `status` enum('APPROVED','CANCELLED','PENDING','') NOT NULL DEFAULT 'PENDING',
  `address` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `customer_id`, `total_amt`, `status`, `address`, `created_at`, `updated_at`) VALUES
(58, 10, 4500.00, 'PENDING', 'Kathmandu, Nepal', '2026-05-20 06:10:04', '2026-05-21 06:10:04'),
(59, 10, 8200.00, 'APPROVED', 'Lalitpur, Nepal', '2026-05-18 06:10:04', '2026-05-21 06:10:04'),
(60, 10, 5600.00, 'PENDING', 'Bhaktapur, Nepal', '2026-05-16 06:10:04', '2026-05-21 06:10:04'),
(61, 10, 10400.00, 'CANCELLED', 'Pokhara, Nepal', '2026-05-14 06:10:04', '2026-05-21 06:10:04'),
(62, 10, 7300.00, 'APPROVED', 'Chitwan, Nepal', '2026-05-11 06:10:04', '2026-05-21 06:10:04'),
(63, 10, 9200.00, 'PENDING', 'Butwal, Nepal', '2026-05-06 06:10:04', '2026-05-21 06:10:04'),
(64, 10, 6100.00, 'APPROVED', 'Dharan, Nepal', '2026-05-01 06:10:04', '2026-05-21 06:10:04'),
(65, 10, 4800.00, 'PENDING', 'Biratnagar, Nepal', '2026-04-26 06:10:04', '2026-05-21 06:10:04'),
(66, 10, 15000.00, 'APPROVED', 'Janakpur, Nepal', '2026-04-21 06:10:04', '2026-05-21 06:10:04'),
(67, 10, 3900.00, 'CANCELLED', 'Hetauda, Nepal', '2026-04-16 06:10:04', '2026-05-21 06:10:04'),
(68, 12, 5200.00, 'APPROVED', 'Kathmandu, Nepal', '2026-05-19 06:10:04', '2026-05-21 06:10:04'),
(69, 12, 8800.00, 'PENDING', 'Lalitpur, Nepal', '2026-05-17 06:10:04', '2026-05-21 06:10:04'),
(70, 12, 6400.00, 'APPROVED', 'Pokhara, Nepal', '2026-05-15 06:10:04', '2026-05-21 06:10:04'),
(71, 12, 9900.00, 'CANCELLED', 'Bhaktapur, Nepal', '2026-05-13 06:10:04', '2026-05-21 06:10:04'),
(72, 12, 7100.00, 'PENDING', 'Chitwan, Nepal', '2026-05-09 06:10:04', '2026-05-21 06:10:04'),
(73, 12, 4300.00, 'APPROVED', 'Butwal, Nepal', '2026-05-03 06:10:04', '2026-05-21 06:10:04'),
(74, 12, 11500.00, 'PENDING', 'Dharan, Nepal', '2026-04-29 06:10:04', '2026-05-21 06:10:04'),
(75, 12, 7600.00, 'APPROVED', 'Biratnagar, Nepal', '2026-04-23 06:10:04', '2026-05-21 06:10:04'),
(76, 12, 13400.00, 'CANCELLED', 'Janakpur, Nepal', '2026-04-11 06:10:04', '2026-05-21 06:10:04'),
(77, 12, 5000.00, 'PENDING', 'Hetauda, Nepal', '2026-04-01 06:10:04', '2026-05-21 06:10:04');

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_variant_id` int(11) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `player_name` varchar(50) DEFAULT NULL,
  `player_no` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`item_id`, `order_id`, `product_variant_id`, `quantity`, `player_name`, `player_no`, `created_at`, `updated_at`) VALUES
(90, 58, 257, 1, 'RONALDO', 7, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(91, 58, 258, 1, 'MBAPPE', 10, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(92, 59, 259, 1, 'MESSI', 9, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(93, 60, 260, 2, 'SALAH', 11, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(94, 61, 261, 1, 'SAKA', 8, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(95, 62, 262, 1, 'STERLING', 1, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(96, 62, 263, 1, 'KANE', 4, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(97, 63, 264, 1, 'LEWANDOWSKI', 5, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(98, 64, 265, 1, 'DYBALA', 6, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(99, 64, 266, 1, 'MBAPPE', 10, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(100, 65, 267, 1, 'NEYMAR', 7, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(101, 66, 268, 1, 'MESSI', 10, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(102, 67, 269, 1, 'GRIEZMANN', 9, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(103, 68, 270, 1, 'KROOS', 4, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(104, 69, 271, 2, 'RONALDO', 8, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(105, 70, 272, 1, 'FODEN', 11, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(106, 70, 273, 1, 'YAMAL', 7, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(107, 71, 274, 1, 'MODRIC', 10, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(108, 72, 275, 1, 'HAALAND', 6, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(109, 73, 276, 1, 'DEPAY', 9, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(110, 74, 277, 1, 'VINICIUS', 7, '2026-05-21 06:12:12', '2026-05-21 06:12:12'),
(111, 75, 278, 1, 'PEDRI', 10, '2026-05-21 06:12:12', '2026-05-21 06:12:12');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `team_name` varchar(255) NOT NULL,
  `category` enum('club','country') NOT NULL,
  `description` text DEFAULT NULL,
  `image_path` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `product_name`, `team_name`, `category`, `description`, `image_path`, `created_at`, `updated_at`) VALUES
(15, 'Home Jersey 2025', 'Real Madrid', 'club', 'Official Real Madrid home jersey for the 2025 season with premium breathable fabric.', 'images/products/real_madrid_home_2025.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(16, 'Away Jersey 2025', 'Barcelona', 'club', 'Barcelona away kit featuring modern design and lightweight material.', 'images/products/barcelona_away_2025.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(17, 'Third Kit 2025', 'Manchester United', 'club', 'Stylish Manchester United third kit with moisture-wicking technology.', 'images/products/man_utd_third_2025.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(18, 'Home Jersey 2025', 'Liverpool', 'club', 'Official Liverpool FC home jersey with embroidered club crest.', 'images/products/liverpool_home_2025.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(19, 'Away Jersey 2025', 'Arsenal', 'club', 'Comfortable Arsenal away jersey suitable for sports and casual wear.', 'images/products/arsenal_away_2025.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(20, 'Training Kit', 'Chelsea', 'club', 'Chelsea FC training kit designed for maximum flexibility and comfort.', 'images/products/chelsea_training.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(21, 'Goalkeeper Jersey', 'Bayern Munich', 'club', 'Official Bayern Munich goalkeeper jersey with long sleeves.', 'images/products/bayern_goalkeeper.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(22, 'Retro Jersey', 'AC Milan', 'club', 'Classic AC Milan retro jersey inspired by legendary seasons.', 'images/products/ac_milan_retro.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(23, 'Home Jersey 2025', 'Juventus', 'club', 'Juventus home jersey with modern striped design.', 'images/products/juventus_home_2025.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(24, 'Fourth Kit', 'Paris Saint-Germain', 'club', 'Limited edition PSG fourth kit with premium finishing.', 'images/products/psg_fourth_kit.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(25, 'Home Jersey 2025', 'Brazil', 'country', 'Official Brazil national team home jersey with iconic yellow design.', 'images/products/brazil_home_2025.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(26, 'Away Jersey 2025', 'Argentina', 'country', 'Argentina away kit featuring lightweight athletic fabric.', 'images/products/argentina_away_2025.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(27, 'Home Jersey 2025', 'France', 'country', 'France national team jersey with breathable mesh technology.', 'images/products/france_home_2025.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(28, 'Training Kit', 'Germany', 'country', 'Germany training kit suitable for daily practice sessions.', 'images/products/germany_training.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(29, 'Home Jersey 2025', 'Portugal', 'country', 'Portugal official home jersey with premium stitched logo.', 'images/products/portugal_home_2025.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(30, 'Away Jersey 2025', 'England', 'country', 'England away jersey designed with slim athletic fit.', 'images/products/england_away_2025.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(31, 'Special Edition Jersey', 'Spain', 'country', 'Spain special edition jersey with elegant modern graphics.', 'images/products/spain_special.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(32, 'Goalkeeper Jersey', 'Italy', 'country', 'Italy goalkeeper jersey with padded sleeves for extra comfort.', 'images/products/italy_goalkeeper.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(33, 'Home Jersey 2025', 'Netherlands', 'country', 'Netherlands home jersey with vibrant orange finish.', 'images/products/netherlands_home_2025.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44'),
(34, 'Fan Edition Jersey', 'Nepal', 'country', 'Nepal national football team fan edition jersey for supporters.', 'images/products/nepal_fan_edition.jpg', '2026-05-21 06:02:44', '2026-05-21 06:02:44');

-- --------------------------------------------------------

--
-- Table structure for table `product_variants`
--

CREATE TABLE `product_variants` (
  `variant_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `size` enum('S','M','L','XL','2XL') NOT NULL,
  `selling_price` decimal(10,2) NOT NULL,
  `stock` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product_variants`
--

INSERT INTO `product_variants` (`variant_id`, `product_id`, `size`, `selling_price`, `stock`, `created_at`, `updated_at`) VALUES
(257, 15, 'S', 4500.00, 15, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(258, 15, 'M', 4500.00, 20, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(259, 15, 'L', 4500.00, 18, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(260, 15, 'XL', 4700.00, 10, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(261, 16, 'S', 4400.00, 12, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(262, 16, 'M', 4400.00, 22, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(263, 16, 'L', 4400.00, 17, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(264, 16, 'XL', 4600.00, 9, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(265, 17, 'S', 4300.00, 14, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(266, 17, 'M', 4300.00, 19, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(267, 17, 'L', 4300.00, 16, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(268, 17, 'XL', 4500.00, 8, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(269, 18, 'S', 4250.00, 13, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(270, 18, 'M', 4250.00, 20, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(271, 18, 'L', 4250.00, 15, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(272, 18, 'XL', 4450.00, 7, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(273, 19, 'S', 4200.00, 11, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(274, 19, 'M', 4200.00, 18, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(275, 19, 'L', 4200.00, 14, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(276, 19, 'XL', 4400.00, 6, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(277, 20, 'S', 4100.00, 10, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(278, 20, 'M', 4100.00, 16, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(279, 20, 'L', 4100.00, 13, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(280, 20, 'XL', 4300.00, 5, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(281, 21, 'S', 4600.00, 9, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(282, 21, 'M', 4600.00, 15, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(283, 21, 'L', 4600.00, 12, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(284, 21, 'XL', 4800.00, 5, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(285, 22, 'S', 4000.00, 8, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(286, 22, 'M', 4000.00, 14, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(287, 22, 'L', 4000.00, 11, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(288, 22, 'XL', 4200.00, 4, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(289, 23, 'S', 4350.00, 10, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(290, 23, 'M', 4350.00, 17, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(291, 23, 'L', 4350.00, 13, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(292, 23, 'XL', 4550.00, 6, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(293, 24, 'S', 4800.00, 7, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(294, 24, 'M', 4800.00, 12, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(295, 24, 'L', 4800.00, 10, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(296, 24, 'XL', 5000.00, 4, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(297, 25, 'S', 4500.00, 16, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(298, 25, 'M', 4500.00, 24, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(299, 25, 'L', 4500.00, 20, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(300, 25, 'XL', 4700.00, 10, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(301, 26, 'S', 4550.00, 15, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(302, 26, 'M', 4550.00, 23, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(303, 26, 'L', 4550.00, 19, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(304, 26, 'XL', 4750.00, 9, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(305, 27, 'S', 4450.00, 14, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(306, 27, 'M', 4450.00, 21, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(307, 27, 'L', 4450.00, 18, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(308, 27, 'XL', 4650.00, 8, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(309, 28, 'S', 4300.00, 12, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(310, 28, 'M', 4300.00, 19, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(311, 28, 'L', 4300.00, 15, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(312, 28, 'XL', 4500.00, 7, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(313, 29, 'S', 4400.00, 13, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(314, 29, 'M', 4400.00, 20, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(315, 29, 'L', 4400.00, 16, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(316, 29, 'XL', 4600.00, 7, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(317, 30, 'S', 4350.00, 11, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(318, 30, 'M', 4350.00, 18, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(319, 30, 'L', 4350.00, 15, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(320, 30, 'XL', 4550.00, 6, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(321, 31, 'S', 4250.00, 10, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(322, 31, 'M', 4250.00, 17, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(323, 31, 'L', 4250.00, 14, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(324, 31, 'XL', 4450.00, 5, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(325, 32, 'S', 4200.00, 9, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(326, 32, 'M', 4200.00, 15, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(327, 32, 'L', 4200.00, 12, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(328, 32, 'XL', 4400.00, 5, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(329, 33, 'S', 4300.00, 10, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(330, 33, 'M', 4300.00, 16, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(331, 33, 'L', 4300.00, 13, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(332, 33, 'XL', 4500.00, 5, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(333, 34, 'S', 3500.00, 20, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(334, 34, 'M', 3500.00, 28, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(335, 34, 'L', 3500.00, 22, '2026-05-21 06:06:50', '2026-05-21 06:06:50'),
(336, 34, 'XL', 3700.00, 12, '2026-05-21 06:06:50', '2026-05-21 06:06:50');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phn_no` varchar(15) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` enum('admin','normal') NOT NULL DEFAULT 'normal',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `full_name`, `email`, `phn_no`, `password`, `user_type`, `created_at`, `updated_at`) VALUES
(1, 'Aayush Bastola', 'aayush1@mail.com', '9800000001', '$2a$10$7QJ8h1hK1yQ7GQ8p0J9Z1eT7sC8zF6Zx0nQpQz0vWk2Gx5zZxY7yG', 'admin', '2026-05-03 18:13:55', '2026-05-03 18:13:55'),
(2, 'John Doe', 'john@mail.com', '9800000002', '$2a$10$7QJ8h1hK1yQ7GQ8p0J9Z1eT7sC8zF6Zx0nQpQz0vWk2Gx5zZxY7yG', 'normal', '2026-05-03 18:13:55', '2026-05-03 18:13:55'),
(3, 'Jane Smith', 'jane@mail.com', '9800000003', '$2a$10$7QJ8h1hK1yQ7GQ8p0J9Z1eT7sC8zF6Zx0nQpQz0vWk2Gx5zZxY7yG', 'normal', '2026-05-03 18:13:55', '2026-05-03 18:13:55'),
(4, 'Ram Sharma', 'ram@mail.com', '9800000004', '$2a$10$7QJ8h1hK1yQ7GQ8p0J9Z1eT7sC8zF6Zx0nQpQz0vWk2Gx5zZxY7yG', 'normal', '2026-05-03 18:13:55', '2026-05-03 18:13:55'),
(5, 'Sita Rai', 'sita@mail.com', '9800000005', '$2a$10$7QJ8h1hK1yQ7GQ8p0J9Z1eT7sC8zF6Zx0nQpQz0vWk2Gx5zZxY7yG', 'normal', '2026-05-03 18:13:55', '2026-05-03 18:13:55'),
(6, 'Alex Lee', 'alex@mail.com', '9800000006', '$2a$10$7QJ8h1hK1yQ7GQ8p0J9Z1eT7sC8zF6Zx0nQpQz0vWk2Gx5zZxY7yG', 'normal', '2026-05-03 18:13:55', '2026-05-03 18:13:55'),
(7, 'Chris Paul', 'chris@mail.com', '9800000007', '$2a$10$7QJ8h1hK1yQ7GQ8p0J9Z1eT7sC8zF6Zx0nQpQz0vWk2Gx5zZxY7yG', 'normal', '2026-05-03 18:13:55', '2026-05-03 18:13:55'),
(8, 'David Miller', 'david@mail.com', '9800000008', '$2a$10$7QJ8h1hK1yQ7GQ8p0J9Z1eT7sC8zF6Zx0nQpQz0vWk2Gx5zZxY7yG', 'normal', '2026-05-03 18:13:55', '2026-05-03 18:13:55'),
(9, 'Emma Watson', 'emma@mail.com', '9800000009', '$2a$10$7QJ8h1hK1yQ7GQ8p0J9Z1eT7sC8zF6Zx0nQpQz0vWk2Gx5zZxY7yG', 'normal', '2026-05-03 18:13:55', '2026-05-03 18:13:55'),
(10, 'Lionel Messi', 'messi@mail.com', '9800000010', '$2a$10$7QJ8h1hK1yQ7GQ8p0J9Z1eT7sC8zF6Zx0nQpQz0vWk2Gx5zZxY7yG', 'normal', '2026-05-03 18:13:55', '2026-05-03 18:13:55'),
(12, 'Aayush Don', 'aayush2@gmail.com', '9890000022', '$2a$10$RUZ7So3tul2ZH0YVSYhtiunAm92/QDhD9VyrMe7WwmAhwrGyahQtO', 'normal', '2026-05-03 19:14:41', '2026-05-03 19:14:41');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_variant_id` (`product_variant_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `product_variants`
--
ALTER TABLE `product_variants`
  ADD PRIMARY KEY (`variant_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phn_no` (`phn_no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=112;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `product_variants`
--
ALTER TABLE `product_variants`
  MODIFY `variant_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=337;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `product_variant_id` FOREIGN KEY (`product_variant_id`) REFERENCES `product_variants` (`variant_id`);

--
-- Constraints for table `product_variants`
--
ALTER TABLE `product_variants`
  ADD CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
