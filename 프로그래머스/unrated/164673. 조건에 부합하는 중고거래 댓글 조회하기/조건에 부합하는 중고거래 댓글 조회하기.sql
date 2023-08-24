select A.title,A.board_id,B.reply_id,B.writer_id,B.contents,
    DATE_FORMAT(B.created_date,"%Y-%m-%d") as CREATED_DATE
from used_goods_board A join used_goods_reply B using(board_id)
where DATE_FORMAT(A.created_date,"%Y-%m")='2022-10'
order by 6 , 1 ;
