package com.fastcampus.customer.board;

import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("BoardDao")
public class BoardDaoImpl implements BoardDao{
    @Autowired
    private SqlSession session;
    private static String namespace =
            "com.fastcampus.customer.mapper.BoardMapper.";

    public String getServerTime() throws Exception{
        return session.selectOne(namespace+"now");
    }

    @Override
    public BoardDto select(Integer bno) throws Exception {
        return session.selectOne(namespace + "select", bno);
    }

    @Override
    public int delete(Integer bno, String writer) throws Exception {
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("writer", writer);
        return session.delete(namespace+"delete", map);
    }

    @Override
    public int insert(BoardDto board) throws Exception {
        return session.insert(namespace+"insert", board);
    }

    @Override
    public int update(BoardDto board) throws Exception {
        return session.update(namespace+"update", board);
    }

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return session.update(namespace+"increaseViewCnt", bno);
    }

    @Override
    public List<BoardDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage", map);
    }

    @Override
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace+"deleteAll");
    }

    @Override
    public int count() throws Exception{
        return session.selectOne(namespace+"count");
    }


}
