ALTER TABLE `phone`
	ADD COLUMN `user_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' AFTER `comment`;

UPDATE `phone` SET `user_id`='1' WHERE  `id`=1;
UPDATE `phone` SET `user_id`='2' WHERE  `id`=2;

ALTER TABLE `user`
	DROP COLUMN `phone_id`;

ALTER TABLE `phone`
	ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);