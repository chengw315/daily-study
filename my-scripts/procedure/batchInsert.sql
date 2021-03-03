--
-- 批量添加1千万条行车轨迹数据（1千辆设备*1w条/辆）
-- 参数 beginDeviceNo：起始设备编号
--
DROP PROCEDURE IF EXISTS batchAddHistories;
CREATE PROCEDURE batchAddHistories(IN beginDeviceNo BIGINT)
BEGIN
DECLARE i INT DEFAULT 0;
DECLARE j INT DEFAULT 0;
DECLARE beginNo BIGINT DEFAULT 0;
DECLARE v_sql LONGTEXT;

DECLARE ridingMileagem BIGINT;
DECLARE ridingTime BIGINT;
DECLARE maxSpeed DOUBLE;
DECLARE beginTime DATETIME;
WHILE i<20 DO
		SET v_sql='INSERT INTO bump_dvc_history(device_no,riding_mileage,riding_time,highest_speed,address_start,address_end,gmt_start,gmt_end,is_permit) VALUES';
		SET j=0;
		SET beginNo=beginDeviceNo;

  -- 骑行时间 2分钟~10小时零2分钟
		SET ridingTime=RAND()*36000000+120000;
		SET maxSpeed=RAND()*35+15;
		SET ridingMileagem=maxSpeed/2*ridingTime/3600;
		SET beginTime=DATE_ADD(CURRENT_TIMESTAMP,INTERVAL i SECOND);
		SET v_sql=CONCAT(v_sql,'(',beginNo,',',ridingMileagem,',',ridingTime,',',maxSpeed,',','''浙江省杭州市余杭区仓前街道罗家路利尔达物联网科技园''',',','''浙江省杭州市余杭区仓前街道罗家路利尔达物联网科技园''',',''',beginTime,''',''',DATE_ADD(beginTime,INTERVAL ridingTime/1000 SECOND),''',',1,')');
		SET j=j+1;
		SET beginNo=beginNo+1;

  WHILE j<1000 DO
	  -- 骑行时间 2分钟~10小时零2分钟
		SET ridingTime=RAND()*36000000+120000;
		SET maxSpeed=RAND()*35+15;
		SET ridingMileagem=maxSpeed/2*ridingTime/3600;
		SET beginTime=DATE_ADD(CURRENT_TIMESTAMP,INTERVAL i SECOND);
		SET v_sql=CONCAT(v_sql,',(',beginNo,',',ridingMileagem,',',ridingTime,',',maxSpeed,',','''浙江省杭州市余杭区仓前街道罗家路利尔达物联网科技园''',',','''浙江省杭州市余杭区仓前街道罗家路利尔达物联网科技园''',',''',beginTime,''',''',DATE_ADD(beginTime,INTERVAL ridingTime/1000 SECOND),''',',1,')');
		SET j=j+1;
		SET beginNo=beginNo+1;
	END WHILE;
	-- 执行sql
	SET @v_sql=v_sql;
	PREPARE stmt1 FROM @v_sql;
  EXECUTE stmt1;
	SET i=i+1;
END WHILE;
END;
CALL batchAddHistories(70000001001);