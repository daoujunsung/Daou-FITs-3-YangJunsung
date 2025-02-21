package org.example.boardproject.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.boardproject.dao.MemberDAO;
import org.example.boardproject.mybatis.MyBatisSessionFactory;
import org.example.boardproject.vo.MemberVO;

public class MemberService {

    public MemberService() {
    }

    public MemberVO login(String id, String password) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        MemberDAO dao = new MemberDAO(sqlSession);
        MemberVO member = dao.login(id, password);

        sqlSession.close();
        return member;
    }
}