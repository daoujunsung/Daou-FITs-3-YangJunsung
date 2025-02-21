package org.example.boardproject.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.boardproject.dao.BoardDAO;
import org.example.boardproject.dao.ReplyDAO;
import org.example.boardproject.mybatis.MyBatisSessionFactory;
import org.example.boardproject.vo.BoardVO;
import org.example.boardproject.vo.ReplyVO;

import java.util.List;

public class ReplyService {
    public ReplyService() {
    }

    public int countByBoardId(int boardId) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        ReplyDAO dao = new ReplyDAO(sqlSession);
        int result = dao.countByBoardId(boardId);

        sqlSession.close();

        return result;
    }

    public List<ReplyVO> selectByBoardId(int boardId) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        ReplyDAO dao = new ReplyDAO(sqlSession);
        List<ReplyVO> replyList = dao.selectByBoardId(boardId);

        sqlSession.close();

        return replyList;
    }

    public int getLastReplyId() {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        ReplyDAO dao = new ReplyDAO(sqlSession);
        int lastReplyId = dao.getLastReplyId();

        sqlSession.close();

        return lastReplyId;
    }

    public ReplyVO selectByReplyId(int replyId) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        ReplyDAO dao = new ReplyDAO(sqlSession);
        ReplyVO reply = dao.selectByReplyId(replyId);

        sqlSession.close();

        return reply;
    }

    public int insertReply(ReplyVO reply) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        ReplyDAO dao = new ReplyDAO(sqlSession);
        int result = dao.insertReply(reply);

        sqlSession.commit();
        sqlSession.close();

        return result;
    }

    public int deleteReply(int replyId) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        ReplyDAO dao = new ReplyDAO(sqlSession);
        int result = dao.deleteReply(replyId);

        sqlSession.commit();
        sqlSession.close();

        return result;
    }

    public int deleteBoardReply(int boardId) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        ReplyDAO dao = new ReplyDAO(sqlSession);
        int result = dao.deleteBoardReply(boardId);

        sqlSession.commit();
        sqlSession.close();

        return result;
    }
}
