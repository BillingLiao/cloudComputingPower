/*
	插入所有云算力产品收益
*/
INSERT INTO t_income(user_id,income_type,electricity_fees,theoretical_income,settlement_income,return_cycle,create_time)
SELECT o.user_id AS user_id,o.order_type AS income_type,
	c.electricity_fees*c.power_waste*24/c.rated/(SELECT btc_cny FROM t_currency_price ORDER BY price_id DESC LIMIT 0,1) AS electricity_fees,
	(SELECT tstimate FROM t_tstimate ORDER BY tstimate_id DESC LIMIT 0,1) AS theoretical_income,
	((SELECT tstimate FROM t_tstimate ORDER BY tstimate_id DESC LIMIT 0,1)-(c.electricity_fees*c.power_waste*24/c.rated/(SELECT btc_cny FROM t_currency_price ORDER BY price_id DESC LIMIT 0,1)))*0.95*o.amount AS settlement_income,
	o.actual_receipts/(SELECT btc_cny FROM t_currency_price ORDER BY price_id DESC LIMIT 0,1)/(((SELECT tstimate FROM t_tstimate ORDER BY tstimate_id DESC LIMIT 0,1)-(c.electricity_fees*c.power_waste*24/c.rated/(SELECT btc_cny FROM t_currency_price ORDER BY price_id DESC LIMIT 0,1)))*0.95*o.amount) AS return_cycle,
	CURRENT_DATE AS create_time
FROM t_order o
LEFT JOIN t_cloud_product c ON c.product_id = o.product_id
WHERE o.order_type = 2 AND o.order_status = 2 AND TO_DAYS(CURRENT_DATE)-TO_DAYS(o.payment_time)>=2;

/*
	插入所有理财产品收益
*/
INSERT INTO t_income(user_id,income_type,money,create_time)
SELECT o.user_id AS user_id,o.order_type AS income_type,
	o.actual_receipts*f.reward_rate/360/100 AS money,
	CURRENT_DATE AS create_time
FROM t_order o
LEFT JOIN t_financial_product f ON f.product_id = o.product_id
WHERE o.order_type = 3 AND TO_DAYS(CURRENT_DATE)-TO_DAYS(o.payment_time)>=2 AND TO_DAYS(CURRENT_DATE)-TO_DAYS(o.payment_time)<f.cycle+2;


/*
	理财产品到期后更改状态为已完成
*/
UPDATE t_order o LEFT JOIN t_financial_product f ON f.product_id = o.product_id
SET order_status = 5 
WHERE o.order_type = 3 AND TO_DAYS(CURRENT_DATE)-TO_DAYS(o.payment_time) &lt; f.cycle;


LEFT JOIN t_cloud_product c ON c.product_id = o.product_id
WHERE o.order_status = 2;

SELECT c.electricity_fees*c.power_waste*24/c.rated/cp.btc_cny AS e
FROM t_cloud_product c,t_currency_price cp
ORDER BY cp.price_id DESC LIMIT 0,1;

SELECT btc_cny, FROM t_currency_price ORDER BY price_id DESC LIMIT 0,1;
SELECT tstimate FROM t_tstimate ORDER BY tstimate_id DESC LIMIT 0,1;

SELECT TO_DAYS('2018-09-04')-TO_DAYS(CURRENT_DATE)>=1
SELECT TO_DAYS(CURRENT_DATE)-TO_DAYS(SELECT DATE_FORMAT(create_time,'%y-%m-%d') FROM t_order WHERE order_id =2) >=2
SELECT TO_DAYS(SELECT DATE_FORMAT(create_time,'%y-%m-%d') FROM t_order WHERE order_id =2)

WHERE datediff（day，getdate（），生产日期）<30

SELECT * form t_order WHERE TO_DAYS('2018-09-04')-TO_DAYS(CURRENT_DATE)>=1

