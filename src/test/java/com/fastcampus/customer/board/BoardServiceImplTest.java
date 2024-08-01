package com.fastcampus.customer.board;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void writeTest100() throws Exception{
        boardService.removeAll();
        for (int i = 1; i <= 210; i++) {
            BoardDto dto = new BoardDto(""+i,"test"+i,"123");
            dto.setWriter("1");
            boardService.write(dto);
        }
    }
    @Test
    public void getCount() throws Exception {
        boardService.removeAll();
        assertTrue(boardService.getCount()==0);
        BoardDto dto = new BoardDto("test","test","test");

        assertTrue(boardService.write(dto)==1);
        assertTrue(boardService.getCount()==1);

        assertTrue(boardService.write(dto)==1);
        assertTrue(boardService.getCount()==2);
    }

    @Test
    public void remove() throws Exception {
        boardService.removeAll();
        assertTrue(boardService.getCount()==0);

        BoardDto dto = new BoardDto("test","test","test");
        assertTrue(boardService.write(dto)==1);
        Integer bno = boardService.getList().get(0).getBno();

        assertTrue(boardService.remove(bno,dto.getWriter())==1);
        assertTrue(boardService.getCount()==0);

        assertTrue(boardService.write(dto)==1);
        bno = boardService.getList().get(0).getBno();
        assertTrue(boardService.remove(bno,(dto.getWriter())+"111")==0);
        assertTrue(boardService.getCount()==1);
        assertTrue(boardService.remove(bno,dto.getWriter())==1);
        assertTrue(boardService.getCount()==0);
    }

    @Test
    public void write() throws Exception {
        boardService.removeAll();
        BoardDto dto = new BoardDto("test","test","test");
        assertTrue(boardService.write(dto)==1);

        dto = new BoardDto("test2","test2","test2");
        assertTrue(boardService.write(dto)==1);
        assertTrue(boardService.getCount()==2);

        boardService.removeAll();
        dto = new BoardDto("test","test","test");
        assertTrue(boardService.write(dto)==1);
        assertTrue(boardService.getCount()==1);

    }

    @Test
    public void getList() throws Exception {
        boardService.removeAll();
        assertTrue(boardService.getCount()==0);

        List<BoardDto> list = boardService.getList();
        assertTrue(list.size()==0);

        BoardDto dto = new BoardDto("test","test","test");
        assertTrue(boardService.write(dto)==1);

        list = boardService.getList();
        assertTrue(list.size()==1);

        assertTrue(boardService.write(dto)==1);
        list = boardService.getList();
        assertTrue(list.size()==2);
    }

    @Test
    public void read() throws Exception {
        boardService.removeAll();
        assertTrue(boardService.getCount()==0);

        BoardDto dto = new BoardDto("test","test","test");
        assertTrue(boardService.write(dto)==1);//삽입

        Integer bno = boardService.getList().get(0).getBno();//첫번째 번호 넣기
        dto.setBno(bno);//첫번째 번호 입력 dto에
        BoardDto dto2 = boardService.read(bno); //dto2에 첫번째 번호의 dto넣기
        assertTrue(dto.getBno() == dto2.getBno()); // 비교
    }

    @Test
    public void getPage() throws Exception{
        boardService.removeAll();
        assertTrue(boardService.getCount()==0);

        for (int i = 1; i <= 10; i++) {
            BoardDto dto = new BoardDto(""+i,"test","test");
            boardService.write(dto);
        }

        Map map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 3);

        List<BoardDto> list = boardService.getPage(map);
        assertTrue(list.get(0).getTitle().equals("10"));
        assertTrue(list.get(1).getTitle().equals("9"));
        assertTrue(list.get(2).getTitle().equals("8"));

        map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 1);

        list = boardService.getPage(map);
        assertTrue(list.get(0).getTitle().equals("10"));

        map = new HashMap();
        map.put("offset", 7);
        map.put("pageSize", 3);

        list = boardService.getPage(map);
        assertTrue(list.get(0).getTitle().equals("3"));
        assertTrue(list.get(1).getTitle().equals("2"));
        assertTrue(list.get(2).getTitle().equals("1"));
    }

    @Test
    public void modify() throws Exception {
        boardService.removeAll();
        BoardDto dto = new BoardDto("test","test","test");
        assertTrue(boardService.write(dto)==1);

        Integer bno = boardService.getList().get(0).getBno();
        dto.setBno(bno);
        dto.setTitle("yes title");
        assertTrue(boardService.modify(dto)==1);

        BoardDto dto2 = boardService.read(bno);
        assertTrue(dto2.getTitle().equals(dto.getTitle()));
    }
}