INSERT INTO `customer` (`id`, `created_at`, `updated_at`, `iva_code`, `name`, `surname`, `email`, `address`, `phone`)
SELECT * FROM
(SELECT '1' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'prova_1' `iva_code`, 'prova' `name`,'prova1' `surname`, 'prova1@mail.it' `email`, 'Milano, Via A. De Gasperi 32' `address`, '3293456156' `phone` UNION ALL
SELECT '2' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'prova_2' `iva_code`, 'provaa' `name`, 'prova2' `surname`, 'prova2@mail.it' `email`, 'Milano, Via A. De Gasperi 32' `address`, '3293456156' `phone`  UNION ALL
SELECT '3' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'prova_3' `iva_code`, 'provaaa' `name`,'prova3' `surname`,  'prova3@mail.it' `email`, 'Milano, Via A. De Gasperi 32' `address`,  '3293456156' `phone`) A
WHERE NOT EXISTS (SELECT NULL FROM customer B WHERE A.id=B.id);

INSERT INTO `user` (`id`, `created_at`, `updated_at`, `address`, `iva_code`, `password`, `phone`, `username`)
SELECT * FROM
(SELECT '1' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'Milano, Via A. De Gasperi 32' `address`, 'iva_code_example_1' `iva_code`, '123' `password`, '3293456156' `phone`, 'luca' `username` UNION ALL
SELECT '2' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'Milano, Via A. De Gasperi 32' `address`, 'iva_code_example_2' `iva_code`, '123' `password`, '3293456156' `phone`, 'alessandro' `username` UNION ALL
SELECT '3' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'Milano, Via A. De Gasperi 32' `address`, 'iva_code_example_3' `iva_code`, '123' `password`, '3293456156' `phone`, 'andrea' `username` UNION ALL
SELECT '4' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'Milano, Via A. De Gasperi 32' `address`, 'iva_code_example_4' `iva_code`, '123' `password`, '3293456156' `phone`, 'amanpreet' `username` UNION ALL
SELECT '5' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'Milano, Via A. De Gasperi 32' `address`, 'iva_code_example_5' `iva_code`, '123' `password`, '3293456156' `phone`, 'matteo' `username` UNION ALL
SELECT '6' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'Milano, Via A. De Gasperi 32' `address`, 'iva_code_example_6' `iva_code`, '123' `password`, '3293456156' `phone`, 'prova' `username`) A
WHERE NOT EXISTS (SELECT NULL FROM user B WHERE A.id=B.id);

INSERT INTO `unit_of_measure`(`id`, `unit`, `name`)
SELECT * FROM
(SELECT '1' `id`, 'g' `unit`, 'grammi' `name` UNION ALL
 SELECT '2' `id`, 'kg' `unit`, 'chilogrammi' `name` UNION ALL
 SELECT '3' `id`, 'ml' `unit`, 'millilitri' `name` UNION ALL
 SELECT '4' `id`, 'l' `unit`, 'litri' `name` UNION ALL
 SELECT '5' `id`, 'm' `unit`, 'metri' `name` UNION ALL
 SELECT '6' `id`, 'km' `unit`, 'chilometri' `name` ) A
WHERE NOT EXISTS (SELECT NULL FROM unit_of_measure B WHERE A.id=B.id);

INSERT INTO `item` (`id`, `created_at`, `updated_at`, `code`, `description`, `price`, `unit_of_measure_id`, `service`)
SELECT * FROM
(SELECT '1' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'AAA' `code`, 'legno' `description`, 10 `price`, 2 `unit_of_measure_id`, FALSE `service` UNION ALL
SELECT '2' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'BBB' `code`, 'roccia' `description`, 20 `price`, 2 `unit_of_measure_id`, FALSE `service` UNION ALL
SELECT '3' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'CCC' `code`, 'acqua' `description`, 30 `price`, 4 `unit_of_measure_id`, FALSE `service` UNION ALL
SELECT '4' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'DDD' `code`, 'pietra' `description`, 40 `price`, 2 `unit_of_measure_id`, FALSE `service` UNION ALL
SELECT '5' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'EEE' `code`, 'fango' `description`, 50 `price`, 2 `unit_of_measure_id`, FALSE `service` UNION ALL
SELECT '6' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'FFF' `code`, 'sabbia' `description`, 60 `price`, 2 `unit_of_measure_id`, FALSE `service` UNION ALL
SELECT '7' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'GGG' `code`, 'ghiaia' `description`, 70 `price`, 3 `unit_of_measure_id`, FALSE `service` UNION ALL
SELECT '8' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'HHH' `code`, 'rete' `description`, 80 `price`, 5 `unit_of_measure_id`, FALSE `service` UNION ALL
SELECT '9' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'III' `code`, 'bronzo' `description`, 90 `price`, 1 `unit_of_measure_id`, FALSE `service` UNION ALL
SELECT '10' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'JJJ' `code`, 'oro' `description`, 100 `price`, 1 `unit_of_measure_id`, FALSE `service` UNION ALL
SELECT '11' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'KKK' `code`, 'spago' `description`, 110 `price`, 5 `unit_of_measure_id`, FALSE `service` UNION ALL
SELECT '12' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'LLL' `code`, 'argento' `description`, 120 `price`, 6 `unit_of_measure_id`, FALSE `service` UNION ALL
SELECT '13' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'MMM' `code`, 'granito' `description`, 130 `price`, 5 `unit_of_measure_id`, FALSE `service` UNION ALL
SELECT '14' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'NNN' `code`, 'marmo' `description`, 140 `price`, 1 `unit_of_measure_id`, FALSE `service` UNION ALL
SELECT '15' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'OOO' `code`, 'sciroppo' `description`, 150 `price`, 1 `unit_of_measure_id`, FALSE `service` UNION ALL
SELECT '16' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'PPP' `code`, 'consulenza' `description`, 160 `price`, 1 `unit_of_measure_id`, TRUE `service` UNION ALL
SELECT '17' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'QQQ' `code`, 'consegna' `description`, 170 `price`, 1 `unit_of_measure_id`, TRUE `service` UNION ALL
SELECT '18' `id`, CURRENT_TIMESTAMP `created_at`, CURRENT_TIMESTAMP `updated_at`, 'RRR' `code`, 'montatura' `description`, 180 `price`, 4 `unit_of_measure_id`, TRUE `service`) A
WHERE NOT EXISTS (SELECT NULL FROM item B WHERE A.id=B.id);
