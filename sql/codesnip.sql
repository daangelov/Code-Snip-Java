-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 10, 2020 at 02:47 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `codesnip`
--

-- --------------------------------------------------------

--
-- Table structure for table `snippet`
--

CREATE TABLE `snippet` (
  `id` int(11) NOT NULL,
  `creator_id` int(11) NOT NULL,
  `title` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `text` text COLLATE utf8_unicode_ci,
  `lang` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `public_id` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `snippet`
--

INSERT INTO `snippet` (`id`, `creator_id`, `title`, `text`, `lang`, `created_on`, `updated_on`, `public_id`) VALUES
(1, 1, 'Java code', '<pre spellcheck=\"false\">public class StringExample\r\n{	public static void main(String[] args)\r\n	{	String s1 = \"Computer Science\";\r\n		int x = 307;\r\n		String s2 = s1 + \" \" + x;\r\n		String s3 = s2.substring(10,17);\r\n		String s4 = \"is fun\";\r\n		String s5 = s2 + s4;\r\n		\r\n		System.out.println(\"s1: \" + s1);\r\n		System.out.println(\"s2: \" + s2);\r\n		System.out.println(\"s3: \" + s3);\r\n		System.out.println(\"s4: \" + s4);\r\n		System.out.println(\"s5: \" + s5);\r\n		\r\n		//showing effect of precedence\r\n		\r\n		x = 3;\r\n		int y = 5;\r\n		String s6 = x + y + \"total\";\r\n		String s7 = \"total \" + x + y;\r\n		String s8 = \" \" + x + y + \"total\";\r\n		System.out.println(\"s6: \" + s6);\r\n		System.out.println(\"s7: \" + s7);\r\n		System.out.println(\"s8: \" + s8);\r\n	}\r\n}', 'java', '2020-02-10 03:25:53', '2020-02-10 03:25:53', NULL),
(2, 1, 'това е ново', '<pre spellcheck=\"false\">// arrays example\r\n#include &lt;iostream&gt;\r\nusing namespace std;\r\n\r\nint foo [] = {16, 2, 77, 40, 12071};\r\nint n, result=0;\r\n\r\nint main ()\r\n{\r\n  for ( n=0 ; n&lt;5 ; ++n )\r\n  {\r\n    result += foo[n];\r\n  }\r\n  cout &lt;&lt; result;\r\n  return 0;\r\n}\r\n</pre><p><br></p>', 'c++', '2020-02-10 03:26:27', '2020-02-10 03:26:27', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `password_salt` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `firstname` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastname` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `password_salt`, `firstname`, `lastname`, `email`) VALUES
(1, 'drago', 'X7S9PX3OASIvjujzkx5hmi23ppM2ITzRwgHj8NJl6gc=', '5bcjV4yNuc2VZ1qhTuwxE5vr44a6Xt', 'Dragomir', 'Angelov', 'a.dragomir1996@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `snippet`
--
ALTER TABLE `snippet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_creator_id` (`creator_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `snippet`
--
ALTER TABLE `snippet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `snippet`
--
ALTER TABLE `snippet`
  ADD CONSTRAINT `fk_creator_id` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
