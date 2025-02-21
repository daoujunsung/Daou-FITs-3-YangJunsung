package org.example.boardproject.dao;

import org.apache.ibatis.session.SqlSession;
import org.example.boardproject.vo.ReplyVO;

import java.util.List;

public class ReplyDAO {

    private SqlSession sqlSession;

    public ReplyDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int countByBoardId(int boardId){
        int result = 0;

        try {
            result = sqlSession.selectOne("board.Reply.countByBoardId", boardId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ReplyVO selectByReplyId(int replyId) {
        ReplyVO reply = null;

        try {
            reply = sqlSession.selectOne("board.Reply.selectByReplyId", replyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reply;
    }

    public List<ReplyVO> selectByBoardId(int boardId) {
        List<ReplyVO> replyList = null;

        try {
            replyList = sqlSession.selectList("board.Reply.selectById", boardId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return replyList;
    }

    public int getLastReplyId() {
        int lastReplyId = 0;

        try {
            lastReplyId = sqlSession.selectOne("board.Reply.getLastReplyId");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastReplyId;
    }

    public int insertReply(ReplyVO reply) {
        int result = 0;

        try {
            result = sqlSession.insert("board.Reply.insertReply", reply);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int deleteReply(int replyId) {
        int result = 0;

        try {
            result = sqlSession.insert("board.Reply.deleteReply", replyId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int deleteBoardReply(int boardId) {
        int result = 0;

        try {
            result = sqlSession.insert("board.Reply.deleteBoardReply", boardId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
