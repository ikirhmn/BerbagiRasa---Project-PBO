-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 13, 2024 at 06:47 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_berbagi_rasa`
--

-- --------------------------------------------------------

--
-- Table structure for table `donatur`
--

CREATE TABLE `donatur` (
  `id_donatur` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `profile_path` varchar(255) DEFAULT NULL,
  `alamat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `donatur`
--

INSERT INTO `donatur` (`id_donatur`, `nama`, `username`, `password`, `profile_path`, `alamat`) VALUES
(1, 'Amanah', 'resto1', 'resto1', 'assets/profile/profile1.jpg', 'Jl. Merdeka No. 1');

-- --------------------------------------------------------

--
-- Table structure for table `makanan`
--

CREATE TABLE `makanan` (
  `id_makanan` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `porsi` int(11) NOT NULL,
  `photo_path` varchar(255) DEFAULT NULL,
  `waktu_ketersediaan` datetime NOT NULL,
  `status` enum('Belum ACC','ACC') DEFAULT 'Belum ACC',
  `id_donatur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `makanan`
--

INSERT INTO `makanan` (`id_makanan`, `nama`, `porsi`, `photo_path`, `waktu_ketersediaan`, `status`, `id_donatur`) VALUES
(1, 'Nasi Goreng', 200, 'D:\\Project PBO\\BerbagiRasa---Project-PBO\\assets\\makanan\\nasi goreng.jpg', '2025-01-14 01:23:50', 'Belum ACC', 1);

-- --------------------------------------------------------

--
-- Table structure for table `panti`
--

CREATE TABLE `panti` (
  `id_panti` int(11) NOT NULL,
  `nama_panti` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `profile_path` varchar(255) DEFAULT NULL,
  `alamat_panti` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `panti`
--

INSERT INTO `panti` (`id_panti`, `nama_panti`, `username`, `password`, `profile_path`, `alamat_panti`) VALUES
(1, 'Panti Asuhan Kasih', 'panti1', 'panti1', 'assets\\profile\\profile2.jpg', 'Jl. Mawar No. 10, Jakarta');

-- --------------------------------------------------------

--
-- Table structure for table `permintaan`
--

CREATE TABLE `permintaan` (
  `id_permintaan` int(11) NOT NULL,
  `id_panti` int(11) NOT NULL,
  `id_makanan` int(11) NOT NULL,
  `tanggal_permintaan` datetime NOT NULL DEFAULT current_timestamp(),
  `tanggal_acc` datetime DEFAULT NULL,
  `status` enum('Belum ACC','ACC') DEFAULT 'Belum ACC'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `donatur`
--
ALTER TABLE `donatur`
  ADD PRIMARY KEY (`id_donatur`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `makanan`
--
ALTER TABLE `makanan`
  ADD PRIMARY KEY (`id_makanan`),
  ADD KEY `id_donatur` (`id_donatur`);

--
-- Indexes for table `panti`
--
ALTER TABLE `panti`
  ADD PRIMARY KEY (`id_panti`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `permintaan`
--
ALTER TABLE `permintaan`
  ADD PRIMARY KEY (`id_permintaan`),
  ADD KEY `id_panti` (`id_panti`),
  ADD KEY `id_makanan` (`id_makanan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `donatur`
--
ALTER TABLE `donatur`
  MODIFY `id_donatur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `makanan`
--
ALTER TABLE `makanan`
  MODIFY `id_makanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `panti`
--
ALTER TABLE `panti`
  MODIFY `id_panti` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `permintaan`
--
ALTER TABLE `permintaan`
  MODIFY `id_permintaan` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `makanan`
--
ALTER TABLE `makanan`
  ADD CONSTRAINT `makanan_ibfk_1` FOREIGN KEY (`id_donatur`) REFERENCES `donatur` (`id_donatur`) ON DELETE CASCADE;

--
-- Constraints for table `permintaan`
--
ALTER TABLE `permintaan`
  ADD CONSTRAINT `permintaan_ibfk_1` FOREIGN KEY (`id_panti`) REFERENCES `panti` (`id_panti`) ON DELETE CASCADE,
  ADD CONSTRAINT `permintaan_ibfk_2` FOREIGN KEY (`id_makanan`) REFERENCES `makanan` (`id_makanan`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
