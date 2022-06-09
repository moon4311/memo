/* 마지막 한개만 업데이트 할 경우 */
UPDATE table_name
set  update_dt = now()
where  pk = 1
order by pk desc
limit 1
;
