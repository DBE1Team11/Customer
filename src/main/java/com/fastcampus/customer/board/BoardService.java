package com.fastcampus.customer.board;

import java.util.List;
import java.util.Map;

public interface BoardService {
    int getCount() throws Exception;//글개수
    int write(BoardDto boardDto) throws Exception;//글작성
    int remove(Integer bno, String writer) throws Exception;//글삭제
    int modify(BoardDto boardDto) throws Exception;//글수정
    List<BoardDto> getList() throws Exception;//글목록 불러오기
    BoardDto read(Integer bno) throws Exception;//한개의 글 불러오기
    List<BoardDto> getPage(Map map) throws Exception; //글 페이지 불러오기
    int removeAll() throws Exception; // 글다 삭제
}
