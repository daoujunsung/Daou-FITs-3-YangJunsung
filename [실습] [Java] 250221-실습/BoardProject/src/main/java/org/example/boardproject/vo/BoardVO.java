package org.example.boardproject.vo;

import java.util.Date;

public class BoardVO {
    private int boardId;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private Date createDate;
    private Date modifyDate;
    private int viewCount;
    private int replyCount;
    private int likeCount;

    public BoardVO() {
    }

    public BoardVO(String boardTitle, String boardContent, String boardWriter) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
    }

    public BoardVO(int boardId, String boardTitle, String boardContent, String boardWriter, Date createDate) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
        this.createDate = createDate;
    }

    public BoardVO(int boardId, String boardTitle, String boardContent, String boardWriter, Date createDate, Date modifyDate, int viewCount, int replyCount, int likeCount) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.viewCount = viewCount;
        this.replyCount = replyCount;
        this.likeCount = likeCount;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public String getBoardWriter() {
        return boardWriter;
    }

    public void setBoardWriter(String boardWriter) {
        this.boardWriter = boardWriter;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
