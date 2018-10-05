-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- ------------------------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `publisher`;
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema publisher
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `publisher` DEFAULT CHARACTER SET utf8 ;

USE `publisher` ;

-- -----------------------------------------------------
-- Table `publisher`.`magazines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `publisher`.`magazines` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  `quantityInMounth` TINYINT(1) UNSIGNED NOT NULL,
  `theme` VARCHAR(15) NOT NULL,
  `image` MEDIUMBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `publisher`.`commentaries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `publisher`.`commentaries` (
  `id` INT(11) NOT NULL,
  `message` VARCHAR(200) NULL DEFAULT NULL,
  `users_id` INT(11) NOT NULL,
  `magazines_id` INT(11) NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_commentaries_users1_idx` (`users_id` ASC),
  INDEX `fk_commentaries_magazines1_idx` (`magazines_id` ASC),
  CONSTRAINT `fk_commentaries_magazines1`
  FOREIGN KEY (`magazines_id`)
  REFERENCES `publisher`.`magazines` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_commentaries_users1`
  FOREIGN KEY (`users_id`)
  REFERENCES `publisher`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `publisher`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `publisher`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(12) NOT NULL,
  `surname` VARCHAR(15) NOT NULL,
  `login` VARCHAR(12) NOT NULL,
  `password` VARCHAR(70) NOT NULL,
  `email` VARCHAR(25) NOT NULL,
  `role` ENUM('ADMIN', 'REGISTR_CLIENT', 'NON_REGISTR_CLIENT') NOT NULL DEFAULT 'NON_REGISTR_CLIENT',
  `isBan` TINYINT(1) NULL DEFAULT '0',
  `walletBalance` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARACTER SET = utf8;

INSERT INTO `users`(id,name,surname,login,password,email,role) VALUES(DEFAULT ,"Anna","Zimina","admin","123","anna.zimina@nure.ua","ADMIN");
-- -----------------------------------------------------
-- Table `publisher`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `publisher`.`orders` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `order_status` ENUM('IN_PROGRESS', 'EXECUTED', 'DENIED') NOT NULL,
  `date` DATETIME NOT NULL,
  `users_id` INT(11) NOT NULL,
  `price` DECIMAL(6,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_users_idx` (`users_id` ASC),
  CONSTRAINT `fk_orders_users`
  FOREIGN KEY (`users_id`)
  REFERENCES `publisher`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `publisher`.`order_magazine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `publisher`.`order_magazine` (
  `magazines_id` INT(11) NOT NULL,
  `orders_id` INT(11) NOT NULL,
  `firstMounth` VARCHAR(10) NULL DEFAULT NULL,
  `secondMounrh` VARCHAR(10) NULL DEFAULT NULL,
  `firstYear` INT(11) NULL DEFAULT NULL,
  `secondYear` INT(11) NULL DEFAULT NULL,
  INDEX `fk_order_magazine_magazines1_idx` (`magazines_id` ASC),
  INDEX `fk_order_magazine_orders1_idx` (`orders_id` ASC),
  CONSTRAINT `fk_order_magazine_magazines1`
  FOREIGN KEY (`magazines_id`)
  REFERENCES `publisher`.`magazines` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_magazine_orders1`
  FOREIGN KEY (`orders_id`)
  REFERENCES `publisher`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `publisher`.`votes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `publisher`.`votes` (
  `value` INT(11) NULL DEFAULT NULL,
  `users_id` INT(11) NOT NULL,
  `magazines_id` INT(11) NOT NULL,
  INDEX `fk_votes_users1_idx` (`users_id` ASC),
  INDEX `fk_votes_magazines1_idx` (`magazines_id` ASC),
  CONSTRAINT `fk_votes_magazines1`
  FOREIGN KEY (`magazines_id`)
  REFERENCES `publisher`.`magazines` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_votes_users1`
  FOREIGN KEY (`users_id`)
  REFERENCES `publisher`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

USE `publisher`;

DELIMITER $$
USE `publisher`$$
CREATE
  DEFINER=`root`@`localhost`
TRIGGER `publisher`.`users_BEFORE_INSERT`
BEFORE INSERT ON `publisher`.`users`
FOR EACH ROW
  BEGIN
    SET new.password = MD5(new.password);
  END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
