package org.example.boardproject.vo;

import java.util.Date;

public class ReplyVO {
    private int replyId;
    private String replyWriter;
    private String replyContent;
    private Date createDate;
    private int boardId;

    public ReplyVO() {
    }

    public ReplyVO(int replyId, String replyWriter, String replyContent, Date createDate, int boardId) {
        this.replyId = replyId;
        this.replyWriter = replyWriter;
        this.replyContent = replyContent;
        this.createDate = createDate;
        this.boardId = boardId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getReplyWriter() {
        return replyWriter;
    }

    public void setReplyWriter(String replyWriter) {
        this.replyWriter = replyWriter;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
}
