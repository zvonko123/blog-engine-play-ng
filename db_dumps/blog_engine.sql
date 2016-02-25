SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

CREATE DATABASE IF NOT EXISTS `blog_engine` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `blog_engine`;

CREATE TABLE IF NOT EXISTS `Comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `message` varchar(255) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

INSERT INTO `Comment` (`id`, `name`, `message`, `timestamp`) VALUES
(1, 'bob', 'this is a comment on first post', '2016-02-23 11:54:13'),
(2, 'zunzibag', 'opinions are like assholes...', '2016-02-23 20:20:45'),
(3, 'bla', 'blobo', '2016-02-23 20:21:34'),
(7, 'hilda', 'hiya bla', '2016-02-23 20:40:39'),
(8, 'mraica', 'bok!', '2016-02-24 14:36:02'),
(9, 'zdenko', 'opinions..', '2016-02-24 14:40:23'),
(11, 'jerry', 'i like cigars too', '2016-02-24 18:18:42'),
(12, 'bob', 'i like em too !', '2016-02-24 18:57:55'),
(13, 'bob', 'second comment !', '2016-02-25 09:55:22'),
(14, 'first', 'first', '2016-02-25 18:55:51'),
(15, 'second', 'second', '2016-02-25 18:55:59'),
(16, 'third', 'third', '2016-02-25 18:56:06'),
(17, 'fourth', 'fourth', '2016-02-25 18:56:14'),
(18, 'janice', 'me too !', '2016-02-25 18:56:30');

CREATE TABLE IF NOT EXISTS `Post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `body` varchar(512) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

INSERT INTO `Post` (`id`, `title`, `body`, `timestamp`) VALUES
(1, 'cigars', 'i liek em a <span style="text-decoration: underline;">lot</span>', '2016-02-23 10:02:22'),
(2, 'topic test', 'topic test body', '2016-02-23 10:17:57'),
(3, 'a topic', 'lets discuss the topic', '2016-02-23 10:20:45'),
(4, 'turtles', 'i like em', '2016-02-24 18:34:49'),
(5, 'newest post', 'hello <span style="font-weight: bold;">bold</span> <span style="font-style: italic;">italic</span> <span style="text-decoration: underline;">underline</span>&nbsp;<span style="vertical-align: super;">superscript</span>', '2016-02-25 08:31:16');

CREATE TABLE IF NOT EXISTS `PostComment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `comment_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

INSERT INTO `PostComment` (`id`, `post_id`, `comment_id`) VALUES
(1, 1, 1),
(2, 1, 7),
(3, 1, 8),
(4, 2, 9),
(5, 1, 11),
(6, 4, 12),
(7, 1, 13),
(8, 3, 14),
(9, 3, 15),
(10, 3, 16),
(11, 3, 17),
(12, 4, 18);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
