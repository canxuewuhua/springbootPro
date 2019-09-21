-- SQL总结

-- 在 SQL 中增加 HAVING 子句原因是，WHERE 关键字无法与合计函数一起使用
SELECT sum(price) FROM `order` GROUP BY name HAVING sum(price) > 60;

-- 添加银行卡名，在name字段之后
ALTER TABLE `order` ADD COLUMN `bank`  varchar(50) NULL COMMENT '银行' AFTER `name`;
