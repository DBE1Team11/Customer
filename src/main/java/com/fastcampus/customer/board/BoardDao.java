package com.fastcampus.customer.board;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    BoardDto select(Integer bno) throws Exception; //번호로 select
    int delete(Integer bno, String writer) throws Exception;//번호와 작성자의 정보로 삭제
    int insert (BoardDto board) throws Exception;//글 작성
    int update (BoardDto board) throws Exception;//글 수정
    int increaseViewCnt(Integer bno) throws Exception; //조회수 증가

    List<BoardDto> selectPage(Map map) throws Exception; //page수 확인
    List<BoardDto> selectAll() throws Exception;//모든페이지확인
    int deleteAll()throws Exception;//모든글 삭제
    int count() throws Exception;//모든글수확인
    String getServerTime()throws Exception; //서버연결테스트
}
