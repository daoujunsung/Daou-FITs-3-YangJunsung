$(document).ready(function() {
    $(".likeBtn").click(function() {
        let isLiked = $(".likeBtn").text() === "♥";
        $.ajax({
            async: true,
            url: "http://localhost:8080/BoardProject_war_exploded/like",
            method: "GET",
            data: {
                boardId: $("#hiddenBoardId").val()
            },
            success:function() {
                if (isLiked) {
                    $(".likeBtn").text("♡");
                } else {
                    $(".likeBtn").text("♥");
                }
            },
            error:function() {
                alert('좋아요 변경에 실패했습니다.');
            }
        });
    });

    $("#reply-register").click(function() {
        var newReplyContent = $("#newReply").val();
        var boardId = $("#hiddenBoardId").val();

        if (newReplyContent.trim() === "") {
            alert("댓글을 입력해주세요.");
            return;
        }

        $.ajax({
            async: true,
            url: "http://localhost:8080/BoardProject_war_exploded/replyRegister",
            method: "POST",
            data: {
                boardId: boardId,
                replyContent: newReplyContent
            },
            success:function(response) {
                // 서버에서 응답받은 댓글 정보 확인
                if (response && response.replyId) {
                    // 새로 등록된 댓글을 리스트에 동적으로 추가
                    var newReply = `
                        <li class="reply-item">
                            <div class="reply-header">
                                <div class="reply-writer">${response.replyWriter}</div>
                                <div class="reply-date">${response.createDate}</div>
                            </div>
                            <div class="reply-content">${response.replyContent}</div>
                            <button class="reply-delete" data-reply-id="${response.replyId}">삭제</button>
                        </li>`;
                    $(".reply-list").append(newReply); // 가장 위에 댓글 추가
                    $("#newReply").val(""); // 입력란 초기화
                } else {
                    alert('댓글 등록에 실패했습니다');
                }
            },
            error:function() {
                alert('댓글 등록에 실패했습니다');
            }
        });
    });

    $(".reply-delete").click(function() {
        var replyId = $(this).data("reply-id");
        var boardId = $("#hiddenBoardId").val();
        console.log(replyId);

        $.ajax({
            async: true,
            url: "http://localhost:8080/BoardProject_war_exploded/replyDelete",
            method: "GET",
            data: {
                replyId: replyId
            },
            success:function() {
                // 삭제된 댓글을 DOM에서 제거
                $(`.reply-delete[data-reply-id="${replyId}"]`).closest(".reply-item").remove();
            },
            error:function() {
                alert('댓글 삭제에 실패했습니다');
            }
        });
    });

    // $("#reply-register").click(function() {
    //     var newReplyContent = $("#newReply").val();
    //     var boardId = $("#hiddenBoardId").val();
    //     console.log(boardId);
    //     if (newReplyContent.trim() === "") {
    //         alert("댓글을 입력해주세요.");
    //         return;
    //     }
    //     $.ajax({
    //         async: true,
    //         url: "http://localhost:8080/BoardProject_war_exploded/replyRegister",
    //         method: "POST",
    //         data: {
    //             boardId: boardId,
    //             replyContent: newReplyContent
    //         },
    //         success:function() {
    //             window.location.href="http://localhost:8080/BoardProject_war_exploded/boardDetail?boardId=" + boardId;
    //         },
    //         error:function() {
    //             alert('댓글 등록에 실패했습니다');
    //         }
    //     });
    // });
    //
});