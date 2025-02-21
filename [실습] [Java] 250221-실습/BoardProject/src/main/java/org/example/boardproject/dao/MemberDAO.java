package org.example.boardproject.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.boardproject.vo.MemberVO;

import java.util.HashMap;
import java.util.Map;

public class MemberDAO {
    private SqlSession sqlSession;

    public MemberDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public MemberVO login(String id, String password) {
        MemberVO member = null;

        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("password", password);

        try {
            member = sqlSession.selectOne("board.Member.selectByIDAndPassword", params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return member;
    }
}
