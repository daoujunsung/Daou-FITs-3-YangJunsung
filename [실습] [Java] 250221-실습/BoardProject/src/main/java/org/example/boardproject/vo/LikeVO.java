package org.example.boardproject.vo;

public class LikeVO {
    private int boardId;
    private String memberId;

    public LikeVO() {
    }

    public LikeVO(int boardId, String memberId) {
        this.boardId = boardId;
        this.memberId = memberId;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
