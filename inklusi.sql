-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 03 Jan 2024 pada 02.29
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inklusi`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `data`
--

CREATE TABLE `data` (
  `id` bigint(20) NOT NULL,
  `nik` varchar(100) NOT NULL,
  `no_kk` varchar(50) NOT NULL,
  `tempat_lahir` varchar(50) NOT NULL,
  `tanggal_lahir` varchar(100) NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `jenis_kelamin` varchar(100) NOT NULL,
  `jenis_disabilitas` varchar(100) NOT NULL,
  `desa` varchar(100) NOT NULL,
  `kecamatan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data`
--

INSERT INTO `data` (`id`, `nik`, `no_kk`, `tempat_lahir`, `tanggal_lahir`, `nama_lengkap`, `jenis_kelamin`, `jenis_disabilitas`, `desa`, `kecamatan`) VALUES
(3, '332424', '3324334', 'Tegal', '2023-12-18', 'asep', 'Perempuan', 'Tunawicara', 'Kademangaran', 'Dukuhturi'),
(4, '3342', '33234', 'Brebes', '2023-12-12', 'Anto', 'Laki-Laki', 'Tunarungu', 'Balapulang Wetan', 'Balapulang'),
(5, '312', '312', 'Tegal', '2023-12-03', 'afib', 'Laki-Laki', 'Tunanetra', 'Pedagangan', 'Dukuhwaru'),
(6, '332', '332', 'Tegal', '2023-12-11', 'siti', 'Perempuan', 'Tunarungu', 'Kalisoka', 'Dukuhwaru'),
(7, '332', '332', 'Ad', '2023-12-07', 'Ada', 'Laki-Laki', 'Tunawicara', 'Jatinegara', 'Kedungwungu');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `data`
--
ALTER TABLE `data`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=202312310002;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
