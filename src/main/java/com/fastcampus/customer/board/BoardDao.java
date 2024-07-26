package com.fastcampus.customer.board;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    BoardDto select(Integer bno) throws Exception;
    int delete(Integer bno, String writer) throws Exception;
    int insert (BoardDto board) throws Exception;
    int update (BoardDto board) throws Exception;
    int increaseViewCnt(Integer bno) throws Exception;
//
    List<BoardDto> selectPage(Map map) throws Exception;
    List<BoardDto> selectAll() throws Exception;
    int deleteAll()throws Exception;
    int count() throws Exception;
    String getServerTime()throws Exception;
}
