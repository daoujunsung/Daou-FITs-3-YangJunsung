
function myFunc() {
    // 버튼 클릭하면 실행

    // jQuery 로 AJAX 호출 실행
    // 어떤 API 를 호출할 지, GET 방식인지, POST 방식인지, 파라미터는 무엇인지
    // 정보를 전달하기 위해 JavaScript 객체로 만들어서 사용

    $.ajax({
        async: true, // 비동기 방식인지 동기 방식인지 선택, 기본값은 비동기(true)
        url: "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json",
        type: "GET",
        data: {
            key: "fb2b247ef1ad0b7de1430292064f96f7",
            targetDt: "20250218"
        },
        dataType: "json", // 서버가 보내주는 결과 데이터가 JSON 이라는 것을 의미,
        success: function(result) {
            // result 라는 매개변수로 서버가 보내준
            // JSON 문자열을 객체로 변환시킨 객체가 매핑
            $("h1").text(result.boxOfficeResult.dailyBoxOfficeList[0].movieNm);
        },
        error: function() {
            alert("뭔가 이상해");
        }
    });
}