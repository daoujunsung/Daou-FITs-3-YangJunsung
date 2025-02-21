package org.example.boardproject.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.boardproject.dao.LikeDAO;
import org.example.boardproject.mybatis.MyBatisSessionFactory;
import org.example.boardproject.vo.LikeVO;

public class LikeService {

    public LikeService() {
    }

    public int countAllLikes(int boardId) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        LikeDAO dao = new LikeDAO(sqlSession);
        int result = dao.countByBoardId(boardId);

        sqlSession.close();

        return result;
    }

    public boolean checkLike(LikeVO like) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        LikeDAO dao = new LikeDAO(sqlSession);
        int count = dao.countByBoardIdAndMemberId(like);

        sqlSession.close();

        return count == 1;
    }

    public int insertLike(LikeVO like) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        LikeDAO dao = new LikeDAO(sqlSession);
        int result = dao.insertLike(like);

        sqlSession.commit();
        sqlSession.close();

        return result;
    }

    public int deleteLike(LikeVO like) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        LikeDAO dao = new LikeDAO(sqlSession);
        int result = dao.deleteLike(like);

        sqlSession.commit();
        sqlSession.close();

        return result;
    }

}
