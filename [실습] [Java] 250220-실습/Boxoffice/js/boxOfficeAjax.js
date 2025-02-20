function search() {
    $.ajax({
        async: true,
        url: "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json",
        type: "GET",
        data: {
            key: "fb2b247ef1ad0b7de1430292064f96f7",
            targetDt: $('#searchDate').val().replace(/-/g, '')
        },
        dataType: "json",
        success: function(result) {
            $('tbody').empty();
            let movieList = result.boxOfficeResult.dailyBoxOfficeList;
            movieList.slice(0, 10).forEach((item) => {
                let tr = $("<tr></tr>");
                let rank = $("<td></td>").text(item.rank);
                let moviePoster = $("<td></td>");
                let posterImg = $("<img height='100'/>");
                moviePoster.append(posterImg);
                let movieName = $("<td></td>").text(item.movieNm);
                let audiAcc = $("<td></td>").text(item.audiAcc);
                let openDt = $("<td></td>").text(item.openDt);
                let deleteCol = $("<td></td>");
                let deleteBtn = $("<button></button>").text("삭제");
                deleteCol.append(deleteBtn);
                $.ajax({
                    async: true,
                    url: "https://dapi.kakao.com/v2/search/image",
                    type: "GET",
                    headers: {
                        Authorization: "KakaoAK d2035424bcd1c2ba9b9b56f61e0cba7c",
                    },
                    data: {
                        query: item.movieNm + ' 포스터',
                        size: 1
                    },
                    success: function(data) {
                        posterImg.attr('src', data.documents[0].thumbnail_url);
                    },
                    error: function() {
                        alert("이미지 못불러옴");
                    }

                });

                deleteBtn.click(function() {
                    $(this).parent().parent().remove();
                })

                tr.append(rank);
                tr.append(moviePoster);
                tr.append(movieName);
                tr.append(audiAcc);
                tr.append(openDt);
                tr.append(deleteCol);
                $("tbody").append(tr);
            })

        },
        error: function() {
            alert("영화 정보 못불러옴");
        }
    });

}
