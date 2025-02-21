package org.example.boardproject.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.boardproject.vo.LikeVO;

public class LikeDAO {

    private SqlSession sqlSession;

    public LikeDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int countByBoardId(int boardId) {
        int result = 0;

        try {
            result = sqlSession.selectOne("board.Like.countByBoardId", boardId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int countByBoardIdAndMemberId(LikeVO like) {
        int result = 0;

        try {
            result = sqlSession.selectOne("board.Like.countByMemberIdAndBoardId", like);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insertLike(LikeVO like) {
        int result = 0;

        try {
            result = sqlSession.insert("board.Like.insertLike", like);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int deleteLike(LikeVO like) {
        int result = 0;

        try {
            result = sqlSession.delete("board.Like.deleteLike", like);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
