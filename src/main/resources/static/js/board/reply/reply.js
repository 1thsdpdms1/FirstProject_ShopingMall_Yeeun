// 자바스크립트언어
const replyOkBtn=documentSelector('#replyOkBtn');
replyOkBtn.addEventListener('click',replyOkBtn);
//function replyOkBtn(){
//    fecth..
//
//}


//댓글 등록
$('#replyOkBtn').on('click',replyOkBtnFn);
function replyOkBtnFn(){

const boardId=$('#boardId').val();
const boardReplyContentVal=$('#boardReplyContent').val();
const boardReplyWriter=$('#boardReplyWriter').val();
    if(boardReplyContent.length<=0){
    alert("입력에러");
    $('#boardReplyContent').focus();
    return false;
    }
const dataVal={
    'boardId':boardId
    'boardReplyContent':boardReplyContent
    'boardReplyWriter' : boardReplyWriter
};
    $.ajax({
            type:'POST',
            url:'/reply/boardReplyWrite',
            data:dataVal,
            success:function(data){
            console.log(data);
            if(data == null){
                alert("댓글 작성 성공");
                ajaxList();
            }else{alert("댓글 작성 실패")
            }
           },
            error:function(){
           }
    });

}

////////////////////이름 체크
//$('#nameCheckedBtn').on('click',nameCheckedBtnFn);
//function nameCheckedBtnFn(){
//
//const userNameVal=$('#userName').val();
//    if(userNameVal.length<=0){
//    alert("이름을 입력하세요");
//    $('#userName').focus();
//    return false;
//    }
//const dataVal={
//    'userName': userNameVal
//};
//    $.ajax({
//            type:'GET',
//            url:'/ajax/nameChecked',
//            data:dataVal,
//            success:function(res){
//            console.log(res);
//            if(res==1){alert("사용할 수 있는 아이디입니다")
//            }else{ alert("이미 사용중인 이름입니다.") }
//           },
//            error:function(){
//           }
//    });
//
//}

///////////////////////////////////////////////////////
// 목록 조회(출력)
function ajaxList(){
    $.ajax({
        type:'GET',
        url:'/reply/boardReplyList',
        success:function(res){
            console.log(res);

        let data=``;
         res.forEach((el,idx)=>{
                data+=`
                    <tr>
                        <td>${el.boardId}</td>
                        <td>${el.boardReplyWriter}</td>
                        <td>${el.boardReplyContent}</td>
                    </tr>
                `;
         });
           data+=``;    console.log(data);
          $('.list-data').html(data);

        },
        error:function(){

        }
    });
}

// 즉시 실행 함수 -> 한번만 실행 -> 페이지가 로드 될 때
(()=>{
    ajaxList();
})();


////검색
//
//$('#searchBtn').on('click',searchBtnFn);
//function searchBtnFn(){
//const searchVal=$('#search').val();
//const subjectVal=$('#subject').val();
// if(searchVal.length<=0){
//    alert("입력에러");
//    $('#searchVal').focus();
//    return false;
//    }
//    const dataVal={
//        'search':searchVal,
//        'subject':subjectVal
//    }
//    $.ajax({
//        type:'GET',
//        url:'/ajax/search',
//        data:dataVal,
//        success:function(res){
//            console.log(res);
//
//        let data=``;
//         res.forEach((el,idx)=>{
//                data+=`
//                    <tr>
//                        <td>${el.id}</td>
//                        <td>${el.userName}</td>
//                        <td>${el.userPw}</td>
//                    </tr>
//                `;
//         });
//           data+=``;    console.log(data);
//          $('.list-data').html(data);
//
//        },
//        error:function(){
//
//        }
//    });
}