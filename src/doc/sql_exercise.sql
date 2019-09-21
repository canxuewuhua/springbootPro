-- SQL总结

-- 在 SQL 中增加 HAVING 子句原因是，WHERE 关键字无法与合计函数一起使用
SELECT sum(price) FROM `order` GROUP BY name HAVING sum(price) > 60;
