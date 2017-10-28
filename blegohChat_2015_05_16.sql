-- 
-- Structure for table `chat`
-- 

DROP TABLE IF EXISTS `chat`;
CREATE TABLE IF NOT EXISTS `chat` (
  `id_chat` int(11) NOT NULL AUTO_INCREMENT,
  `user_id_sender` int(10) NOT NULL,
  `user_id_receiver` int(10) NOT NULL,
  `isi_chat` text NOT NULL,
  `chat_time` datetime NOT NULL,
  PRIMARY KEY (`id_chat`),
  KEY `index` (`user_id_sender`),
  KEY `indexx` (`user_id_receiver`),
  CONSTRAINT `chat_ibfk_1` FOREIGN KEY (`user_id_sender`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chat_ibfk_2` FOREIGN KEY (`user_id_receiver`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- 
-- Data for table `chat`
-- 

INSERT INTO `chat` (`id_chat`, `user_id_sender`, `user_id_receiver`, `isi_chat`, `chat_time`) VALUES
  ('1', '1', '2', '<html><p>hay</p><p> wsedv</p></html>', '2014-10-03 00:00:00'),
  ('2', '3', '1', '<html>cuks</html>', '2015-03-27 00:00:00'),
  ('3', '1', '3', '<html>siapa ya?</html>', '2015-04-03 00:00:00'),
  ('4', '2', '1', '<html>iya :)</html>', '2015-04-03 00:00:00'),
  ('5', '4', '1', '<html>gan</html>', '2015-04-03 00:00:00'),
  ('6', '1', '2', '<html>:o</html>', '2015-04-04 00:00:00'),
  ('7', '2', '1', '<html>:v</html>', '2015-04-05 00:00:00'),
  ('8', '1', '2', '<html>lagi apa</html>', '2015-04-05 00:00:00'),
  ('9', '1', '4', '<html>oyi</html>', '2015-04-05 00:00:00'),
  ('10', '4', '1', '<html>dimana?</html>', '2015-04-05 00:00:00'),
  ('11', '1', '4', '<html>kontrakan</html>', '2015-04-05 00:00:00'),
  ('12', '2', '1', '<html>lagi anu :p</html>', '2015-04-05 00:00:00'),
  ('13', '1', '2', '<html>gitu :(</html>', '2015-04-05 00:00:00'),
  ('14', '1', '4', '<html>taik lu -_-</html>', '2015-04-05 00:00:00'),
  ('15', '5', '1', '<html>neg ndi cok?</html>', '2015-04-06 00:00:00'),
  ('16', '6', '1', '<html>neg kost?</html>', '2015-04-06 10:15:05'),
  ('17', '1', '5', '<html>kontrakan</html>', '2015-04-06 10:49:39'),
  ('18', '2', '1', '<html>:P</html>', '2015-04-07 13:19:33'),
  ('19', '1', '5', '<html>lapo cuks?</html>', '2015-04-09 13:42:14'),
  ('20', '5', '1', '<html>aku kate mrono</html>', '2015-04-09 13:43:28'),
  ('21', '1', '2', '<html>:@</html>', '2015-04-10 10:11:45'),
  ('22', '1', '5', '<html>y</html>', '2015-04-10 10:16:22'),
  ('23', '5', '1', '<html>otw</html>', '2015-04-10 10:17:16'),
  ('24', '5', '1', '<html>1 jam</html>', '2015-04-10 10:17:38'),
  ('25', '1', '5', '<html>yo ndangan</html>', '2015-04-10 10:20:22'),
  ('26', '1', '2', '<html>:o</html>', '2015-04-16 10:37:21'),
  ('27', '1', '2', '<html><p>test 2 baris</p><p>\ntest</p><p>asasca</p></html>', '2015-05-10 10:10:51'),
  ('28', '1', '4', '<html>test</html>', '2015-05-16 18:16:38'),
  ('29', '1', '4', '<html>test view</html>', '2015-05-16 18:17:04'),
  ('30', '1', '4', '<html>test\nts</html>', '2015-05-16 18:17:22'),
  ('31', '1', '4', '<html>asdes</html>', '2015-05-16 18:19:44'),
  ('32', '1', '4', '<html>as</html>', '2015-05-16 18:29:17'),
  ('33', '1', '4', '<html>as</html>', '2015-05-16 18:31:41'),
  ('35', '1', '2', '<html>test</html>', '2015-05-16 19:03:18'),
  ('36', '1', '2', '<html>as</html>', '2015-05-16 19:05:17');

-- 
-- Structure for table `kontak`
-- 

DROP TABLE IF EXISTS `kontak`;
CREATE TABLE IF NOT EXISTS `kontak` (
  `user_id` int(10) NOT NULL,
  `kontak_id` int(10) NOT NULL,
  PRIMARY KEY (`user_id`,`kontak_id`),
  KEY `kontak_id` (`kontak_id`),
  CONSTRAINT `kontak_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kontak_ibfk_2` FOREIGN KEY (`kontak_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 
-- Data for table `kontak`
-- 

INSERT INTO `kontak` (`user_id`, `kontak_id`) VALUES
  ('1', '2'),
  ('1', '5'),
  ('1', '6');

-- 
-- Structure for table `user`
-- 

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `foto` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  `last_seen` datetime NOT NULL,
  `email` varchar(50) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 
-- Data for table `user`
-- 

INSERT INTO `user` (`user_id`, `username`, `password`, `nama`, `foto`, `status`, `last_seen`, `email`, `is_active`) VALUES
  ('1', 'blegoh', 'itsme', 'Yusuf Eka Sayogan', 'blegoh.jpg', 'lol', '2014-11-08 21:15:00', 'yusufblegoh@gmail.com', '1'),
  ('2', 'wenny', 'wenny', 'Wenny Hardiyanti Pratiwi', 'wenny.jpg', ':v', '2014-11-08 21:15:00', '', '1'),
  ('3', 'fery', 'fery', 'Fery Andika', 'fery.jpg', ':)', '2014-11-08 21:15:00', '', '1'),
  ('4', 'vananda', 'vananda', 'Vananda Rahadika', 'vananda.jpg', 'aku suka kakak ingkat', '2015-02-25 13:16:46', '', '1'),
  ('5', 'tommy', 'tommy', 'Tommy', 'tommy.jpg', ':(', '2015-04-06 09:22:13', 'cas@WQ.as.as', '1'),
  ('6', 'dias', 'dias', 'Dias', 'dias.jpg', 'aku manies', '2015-04-06 10:14:51', 'dias@gmail.com', '1');

