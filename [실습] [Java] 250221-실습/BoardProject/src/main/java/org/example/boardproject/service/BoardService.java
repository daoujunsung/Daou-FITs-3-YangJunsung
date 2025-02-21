package org.example.boardproject.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.boardproject.dao.BoardDAO;
import org.example.boardproject.dao.MemberDAO;
import org.example.boardproject.mybatis.MyBatisSessionFactory;
import org.example.boardproject.vo.BoardVO;
import org.example.boardproject.vo.MemberVO;

import java.util.List;

public class BoardService {

    public BoardService() {
    }

    public List<BoardVO> selectAllBoards() {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        BoardDAO dao = new BoardDAO(sqlSession);
        List<BoardVO> boardList = dao.selectAll();

        sqlSession.close();
        return boardList;
    }

    public BoardVO selectByBoardId(int boardId) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        BoardDAO dao = new BoardDAO(sqlSession);
        BoardVO board = dao.selectById(boardId);

        sqlSession.close();
        return board;
    }

    public List<BoardVO> selectByTitleOrContent(String keyword) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        BoardDAO dao = new BoardDAO(sqlSession);
        List<BoardVO> boardList = dao.selectByTitleOrContent(keyword);

        sqlSession.close();
        return boardList;
    }

    public BoardVO selectByBoardIdForEdit(int boardId) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        BoardDAO dao = new BoardDAO(sqlSession);
        BoardVO board = dao.selectByIdForEdit(boardId);

        sqlSession.close();
        return board;
    }

    public int insertBoard(BoardVO board) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        BoardDAO dao = new BoardDAO(sqlSession);
        int result = dao.insertBoard(board);

        sqlSession.commit();
        sqlSession.close();

        return result;
    }

    public int updateBoard(BoardVO board) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        BoardDAO dao = new BoardDAO(sqlSession);
        int result = dao.updateBoard(board);

        sqlSession.commit();
        sqlSession.close();

        return result;
    }

    public int updateBoardView(BoardVO board) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        BoardDAO dao = new BoardDAO(sqlSession);
        int result = dao.updateBoardViewCount(board);

        sqlSession.commit();
        sqlSession.close();

        return result;
    }

    public int deleteBoard(int boardId) {
        SqlSessionFactory factory =
                MyBatisSessionFactory.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();

        BoardDAO dao = new BoardDAO(sqlSession);
        int result = dao.deleteBoard(boardId);

        sqlSession.commit();
        sqlSession.close();

        return result;
    }
}
