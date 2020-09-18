-- CREATE TABLE Device (
--    device_id INT PRIMARY KEY,
--    mac VARCHAR(80),
--    name VARCHAR(80) NOT NULL
-- );
-- select * from pg_tables where schemaname = 'public';

CREATE TABLE LandingPage (
   landing_page_id CHAR(8) PRIMARY KEY,
   name VARCHAR(80) NOT NULL,
   url VARCHAR(80) NOT NULL,
   description VARCHAR --关于
);

-- 插入
INSERT INTO LandingPage VALUES ('2U23Khvs', '拼多多', 'https://m.pinduoduo.com/'); -- ad001
INSERT INTO LandingPage VALUES ('HmBJqIlr', 'KFC', 'http://www.kfc.com.cn'); -- ad002
INSERT INTO LandingPage VALUES ('F9mOXr3Z', '中国移动浙江营业厅', 'http://www.10086.cn/zj/'); -- ad003

CREATE TABLE LandingRecord (
   landing_record_id SERIAL PRIMARY KEY,
   landing_page_id VARCHAR(80) NOT NULL REFERENCES LandingPage(landing_page_id),
   device_id VARCHAR(80) NOT NULL,
   landing_time TIMESTAMP NOT NULL,
   description VARCHAR
);

-- 扫码的记录函数
CREATE OR REPLACE FUNCTION public.recordlanding(
  landing_page_id character,
  device_id character
)
RETURNS boolean AS
$BODY$
BEGIN
  INSERT INTO LandingRecord(landing_page_id, device_id, landing_time)
  VALUES (landing_page_id, device_id, CURRENT_TIMESTAMP);
  RETURN true;
END;
$BODY$
LANGUAGE plpgsql SECURITY DEFINER;