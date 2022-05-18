-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 08:30 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wakalni_java`
--

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

CREATE TABLE `commande` (
  `commandeid` int(11) NOT NULL,
  `datecreation` datetime NOT NULL,
  `dateexpedition` datetime NOT NULL,
  `datearrivee` datetime NOT NULL,
  `clientid` int(11) NOT NULL,
  `livreurid` int(11) NOT NULL,
  `rcid` int(11) NOT NULL,
  `panierid` int(11) NOT NULL,
  `nomclient` varchar(11) NOT NULL,
  `nomlivreur` varchar(11) NOT NULL,
  `nomresto` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `leftovers`
--

CREATE TABLE `leftovers` (
  `leftoverid` int(11) NOT NULL,
  `sujet` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  `quantite` int(11) NOT NULL,
  `dateexpiration` datetime NOT NULL,
  `chefrestoid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `offre`
--

CREATE TABLE `offre` (
  `offreid` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  `prix` float NOT NULL,
  `crid` int(11) NOT NULL,
  `datefin` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `organisation`
--

CREATE TABLE `organisation` (
  `organisationid` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `adresse` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `numero` int(8) NOT NULL,
  `leftoverid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `panier`
--

CREATE TABLE `panier` (
  `panierid` int(11) NOT NULL,
  `produitid` int(11) NOT NULL,
  `clientid` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `prixprod` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `produitid` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  `crid` int(11) NOT NULL,
  `prix` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `produitpanier`
--

CREATE TABLE `produitpanier` (
  `ppanierid` int(11) NOT NULL,
  `pproduitid` int(11) NOT NULL,
  `quantite` int(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reclamation`
--

CREATE TABLE `reclamation` (
  `reclamationid` int(11) NOT NULL,
  `sujet` varchar(30) NOT NULL,
  `contenu` text NOT NULL,
  `clientid` int(11) NOT NULL,
  `commandeid` int(11) NOT NULL,
  `nomclient` varchar(11) NOT NULL,
  `reponse` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `reponse`
--

CREATE TABLE `reponse` (
  `reponseid` int(11) NOT NULL,
  `reclamationid` int(11) NOT NULL,
  `reponsenom` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `reviewid` int(11) NOT NULL,
  `note` int(5) NOT NULL,
  `commentaire` text NOT NULL,
  `utilisateurid` int(11) NOT NULL,
  `produitid` int(11) NOT NULL,
  `produitnom` varchar(11) NOT NULL,
  `clientnom` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `adresse` varchar(60) NOT NULL,
  `email` varchar(50) NOT NULL,
  `passwrd` varchar(64) NOT NULL,
  `numero` int(8) NOT NULL,
  `role` varchar(1) NOT NULL,
  `token` tinyint(1) NOT NULL DEFAULT 0,
  `Valide` int(1) DEFAULT 0,
  `role_name` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `adresse`, `email`, `passwrd`, `numero`, `role`, `token`, `Valide`, `role_name`) VALUES
(20, 'malek', 'maaa', 'ghazela', 'malek.guemri@esprit.tn', '2c7a5a6bfa4b5baee3b981b7803c3747', 23242526, '1', 0, 1, 'Client'),
(21, 'nouri', 'nouri', 'ghazl', 'nourhene.bakalti@esprit.tn', 'c26be8aaf53b15054896983b43eb6a65', 26281919, '1', 0, 1, 'Client'),
(28, 'aaaa', 'aaa', 'aaaa', 'norhbakalti@gmail.com', '74b87337454200d4d33f80c4663dc5e5', 23808586, '2', 0, 1, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`commandeid`),
  ADD KEY `fkclient` (`clientid`),
  ADD KEY `fklivreur` (`livreurid`),
  ADD KEY `fkrc` (`rcid`),
  ADD KEY `fkpanier` (`panierid`);

--
-- Indexes for table `leftovers`
--
ALTER TABLE `leftovers`
  ADD PRIMARY KEY (`leftoverid`),
  ADD KEY `fkleftover` (`chefrestoid`);

--
-- Indexes for table `offre`
--
ALTER TABLE `offre`
  ADD PRIMARY KEY (`offreid`),
  ADD KEY `fkcro` (`crid`);

--
-- Indexes for table `organisation`
--
ALTER TABLE `organisation`
  ADD PRIMARY KEY (`organisationid`),
  ADD KEY `fkorganisation` (`leftoverid`);

--
-- Indexes for table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`panierid`),
  ADD KEY `fkclientp` (`clientid`),
  ADD KEY `fkproduit` (`produitid`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`produitid`),
  ADD KEY `fkcrp` (`crid`);

--
-- Indexes for table `produitpanier`
--
ALTER TABLE `produitpanier`
  ADD PRIMARY KEY (`ppanierid`,`pproduitid`),
  ADD KEY `fkproduit` (`pproduitid`);

--
-- Indexes for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`reclamationid`),
  ADD KEY `fkclientrec` (`clientid`),
  ADD KEY `fkcommanderec` (`commandeid`);

--
-- Indexes for table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`reponseid`),
  ADD KEY `fkreponse` (`reclamationid`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`reviewid`),
  ADD KEY `fkclientreview` (`utilisateurid`),
  ADD KEY `fkproduitreview` (`produitid`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `commande`
--
ALTER TABLE `commande`
  MODIFY `commandeid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `leftovers`
--
ALTER TABLE `leftovers`
  MODIFY `leftoverid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `offre`
--
ALTER TABLE `offre`
  MODIFY `offreid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `organisation`
--
ALTER TABLE `organisation`
  MODIFY `organisationid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `panier`
--
ALTER TABLE `panier`
  MODIFY `panierid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `produitid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `reclamationid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `reponseid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `reviewid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `fkclient` FOREIGN KEY (`clientid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fklivreur` FOREIGN KEY (`livreurid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkpanier` FOREIGN KEY (`panierid`) REFERENCES `panier` (`panierid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkrc` FOREIGN KEY (`rcid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `leftovers`
--
ALTER TABLE `leftovers`
  ADD CONSTRAINT `fkleftover` FOREIGN KEY (`chefrestoid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `offre`
--
ALTER TABLE `offre`
  ADD CONSTRAINT `fkcro` FOREIGN KEY (`crid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `organisation`
--
ALTER TABLE `organisation`
  ADD CONSTRAINT `fkorganisation` FOREIGN KEY (`leftoverid`) REFERENCES `leftovers` (`leftoverid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `fkclientp` FOREIGN KEY (`clientid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkproduit` FOREIGN KEY (`produitid`) REFERENCES `produit` (`produitid`);

--
-- Constraints for table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `fkcrp` FOREIGN KEY (`crid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `fkclientrec` FOREIGN KEY (`clientid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkcommanderec` FOREIGN KEY (`commandeid`) REFERENCES `commande` (`commandeid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `fkreponse` FOREIGN KEY (`reclamationid`) REFERENCES `reclamation` (`reclamationid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `fkclientreview` FOREIGN KEY (`utilisateurid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkproduitreview` FOREIGN KEY (`produitid`) REFERENCES `produit` (`produitid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
