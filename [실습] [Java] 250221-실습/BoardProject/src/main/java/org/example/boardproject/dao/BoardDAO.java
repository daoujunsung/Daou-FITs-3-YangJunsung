package org.example.boardproject.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.boardproject.vo.BoardVO;

import java.util.List;

public class BoardDAO {
    private SqlSession sqlSession;

    public BoardDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<BoardVO> selectAll() {
        List<BoardVO> list = null;

        try {
            list = sqlSession.selectList("board.Board.selectAll");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public BoardVO selectById(int boardId) {
        BoardVO board = null;

        try {
            board = sqlSession.selectOne("board.Board.selectById", boardId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return board;
    }

    public List<BoardVO> selectByTitleOrContent(String keyword) {
        List<BoardVO> boardList = null;

        try {
            boardList = sqlSession.selectList("board.Board.selectByTitleOrContent", keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return boardList;
    }

    public BoardVO selectByIdForEdit(int boardId) {
        BoardVO board = null;

        try {
            board = sqlSession.selectOne("board.Board.selectByIdForEdit", boardId);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return board;
    }

    public int insertBoard(BoardVO board) {
        int result = 0;

        try {
            result = sqlSession.insert("board.Board.insertBoard", board);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int updateBoard(BoardVO board) {
        int result = 0;

        try {
            result = sqlSession.update("board.Board.updateBoard", board);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int updateBoardViewCount(BoardVO board) {
        int result = 0;

        try {
            result = sqlSession.update("board.Board.updateViewCount", board);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int deleteBoard(int boardId) {
        int result = 0;

        try {
            result = sqlSession.delete("board.Board.deleteBoard", boardId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
