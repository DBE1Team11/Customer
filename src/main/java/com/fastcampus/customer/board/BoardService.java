package com.fastcampus.customer.board;

public interface BoardService {
    int getCount()throws Exception; //글수 확인
    int remove(Integer bno, String writer) throws Exception;
    int write(BoardDto boardDto) throws Exception;
    
}
