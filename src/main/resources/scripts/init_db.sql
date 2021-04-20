INSERT INTO `user` (`id`,`created_at`,`updated_at`,`username`,`email`,`password`,`full_name`,`address`, `phone`, `role`)
SELECT * FROM
(SELECT '1' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'user' `username`, 'user' `email`,'123' `password`, 'name surname 1' `full_name`, 'Milano, Via A. De Gasperi 32' `address`, '32934561561' `phone`, 'USER' `role` UNION ALL
SELECT '2' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'writer' `username`, 'writer' `email`, '123' `password`, 'name surname 2' `full_name`, 'Milano, Via A. De Gasperi 32' `address`, '32934561562' `phone`, 'WRITER' `role`  UNION ALL
SELECT '3' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'reviewer' `username`, 'reviewer' `email`, '123' `password`, 'name surname 3' `full_name`, 'Milano, Via A. De Gasperi 32' `address`, '32934561563' `phone`, 'REVIEWER' `role`  UNION ALL
SELECT '4' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'admin' `username`, 'admin' `email`,'123' `password`,  'name surname 4' `full_name`, 'Milano, Via A. De Gasperi 32' `address`,  '32934561564' `phone`, 'ADMIN' `role`) A
WHERE NOT EXISTS (SELECT NULL FROM user B WHERE A.id=B.id);
