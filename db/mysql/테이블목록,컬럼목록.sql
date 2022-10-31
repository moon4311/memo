
-- 1. 조회할 데이터베이스명 세팅
set @db := 'lotto'; 

-- 2. 데이터베이스별  테이블 조회
SELECT  'lotto' TABLE_SCHEMA,
		TABLE_NAME,
		ENGINE,
        TABLE_COLLATION,
        TABLE_COMMENT
FROM 	information_schema.tables x
WHERE 	table_schema = @db ;


-- 3. 데이터베이스별 컬럼정보 조회
SELECT	distinct 
	   x.TABLE_NAME '테이블 명',
	   x.ORDINAL_POSITION '순번',
	   x.COLUMN_NAME '필드 명',
	   x.COLUMN_TYPE '데이터 타입',
	   x.COLUMN_KEY 'KEY',
	   -- y.INDEX_NAME '인덱스 명',
	   x.IS_NULLABLE 'NULL 여부',
	   x.EXTRA '자동 여부',
	   x.COLUMN_DEFAULT '디폴트 값',
	   x.COLUMN_COMMENT '필드 설명'
FROM 	information_schema.COLUMNS x 
LEFT JOIN information_schema.STATISTICS y 
ON 		x.TABLE_NAME = y.TABLE_NAME AND x.COLUMN_NAME = y.COLUMN_NAME
WHERE 	x.TABLE_SCHEMA = @db
-- and table_name = 'members_tb'
ORDER BY x.TABLE_NAME, x.ORDINAL_POSITION;
