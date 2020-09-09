-- CREATE TABLE Device (
--    device_id INT PRIMARY KEY,
--    mac VARCHAR(80),
--    name VARCHAR(80) NOT NULL
-- );
-- select * from pg_tables where schemaname = 'public';

CREATE TABLE LandingPage (
   landing_page_id CHAR(16) PRIMARY KEY,
   name VARCHAR(80) NOT NULL,
   url VARCHAR(80) NOT NULL,
   description VARCHAR --关于
);

-- 插入
INSERT INTO LandingPage VALUES ('G6YF3UVQLD4S7ATV', 'Adaprox Official', 'www.adaprox.io');
INSERT INTO LandingPage VALUES ('G6382GFCHAFZ3U2G', 'Baidu', 'www.baidu.com');
INSERT INTO LandingPage VALUES ('G5CGP46K4UGJFJSH', 'Tencent', 'www.qq.com');

CREATE TABLE LandingRecord (
   landing_record_id SERIAL PRIMARY KEY,
   ad_id VARCHAR(80) NOT NULL, -- demo id
   landing_page_id VARCHAR(80) NOT NULL REFERENCES LandingPage(landing_page_id),
   device_id VARCHAR(80) NOT NULL,
   landing_time TIME NOT NULL,
   description VARCHAR
);

--  墨水屏广告
-- CREATE TABLE Ad (
--    ad_id CHAR(16) PRIMARY KEY,
--    name VARCHAR(80) NOT NULL, --广告名字
--    description VARCHAR --不同屏幕对应的参数
-- );

-- 扫码的记录函数
CREATE OR REPLACE FUNCTION public.recordlanding(
  new_ad_id character,
  new_landing_page_id character,
  new_device_id character
)
RETURNS boolean AS
$BODY$
BEGIN
  INSERT INTO LandingRecord(ad_id, landing_page_id, device_id, landing_time)
  VALUES (new_ad_id, new_landing_page_id, new_device_id, CURRENT_TIMESTAMP);
  RETURN true;
END;
$BODY$
LANGUAGE plpgsql SECURITY DEFINER;

SELECT * FROM recordlanding('AD111', 'G6YF3UVQLD4S7ATV', 'DEVICE_MAC');
SELECT * FROM recordlanding('AD111', 'G6382GFCHAFZ3U2G', 'DEVICE_MAC');
SELECT * FROM recordlanding('AD111', 'G5CGP46K4UGJFJSH', 'DEVICE_MAC');

-- 获得扫码
-- SELECT COUNT(*) FROM LandingRecord
-- WHERE landing_page_id = "" and device_id = "";
