$(document).ready(function () {
  // 글 쓰기 폼 제출 이벤트
  $('#boardForm').submit(function (e) {
    e.preventDefault(); // 기본 제출 이벤트 방지
    var formData = $(this).serialize(); // 폼 데이터를 직렬화
    $.ajax({
      type: 'POST',
<<<<<<< HEAD
      url: '/board/inquiry/write', // 글 쓰기 처리를 담당하는 컨트롤러의 URL
=======
      url: '/Inquiry', // 글 쓰기 처리를 담당하는 컨트롤러의 URL
>>>>>>> dev
      data: formData,
      success: function (data) {
        // 글 쓰기가 성공하면 새로운 글 목록을 불러옴
        loadBoardList();
      }
    });
  });

  // 초기에 페이지가 로드될 때 글 목록을 불러옴
<<<<<<< HEAD
  loadBoardList();
=======
  //    loadBoardList();
>>>>>>> dev
});

function loadBoardList() {
  $.ajax({
    type: 'GET',
<<<<<<< HEAD
    url: '/boardList',
=======
   url: '/boardList',
    /*url: '/boardInquiry/category/{category}', // 글 목록을 가져오는 컨트롤러의 URL*/
>>>>>>> dev
    success: function (data) {
      $('#boardList').html(data); // 가져온 데이터를 목록 영역에 삽입
    }
  });
}

<<<<<<< HEAD
function ajaxInquiry(event) {
=======


function ajaxInquiry(event) {

>>>>>>> dev
  event.preventDefault();

  const boardTitle = $('#boardTitle').val();
  const boardContent = $('#boardContent').val();
  const category = $('#category').val();
  const boardWriter = $('#boardWriter').val();

  const inquiryData = {
    'boardTitle': boardTitle,
    'boardContent': boardContent,
    'category': category,
    'boardWriter': boardWriter
  };

  $.ajax({
    type: 'POST',
<<<<<<< HEAD
    url: '/board/inquiry/write',
    data: inquiryData,
    success: function (data) {
      let html = `
        <tr>
          <td>${data.board.boardTitle}</td>
          <td>${data.board.boardContent}</td>
          <td>${data.board.boardWriter}</td>
          <td>
            <a href="/board/boardAnswer?id=${data.board.id}">문의 답변</a>
          </td>
        </tr>
      `;

      $('tbody').prepend(html);
    }
  });
}
=======
    url: '/board/inquiry/write', // 글 쓰기 처리를 담당하는 컨트롤러의 URL
    data: inquiryData,
    success: function (data) {
      console.log(data);
      console.log(data.board);
      console.log(typeof data.board);
      console.log(data.board.boardTitle);
      console.log(data.board.boardContent);
      console.log(data.board.boardWriter);

      let html = `
                <tr>
                  <td>${data.board.boardTitle}></td>
                  <td>${data.board.boardContent}></td>
                  <td>${data.board.boardWriter}></td>
                   <td>
                      <a href="/board/boardAnswer/${data.board.category}">문의답변</a>
                    </td>
                </tr>
              `;

      $('tbody').append(html);
//      ajaxDataListFn();
    }
  });
}


//let ajaxDataList=new Object();
/*
function ajaxDataListFn() {
  $.ajax({
        type: 'GET',
        url: '/board/inquiry/inquiryList', // 글 목록을 가져오는 컨트롤러의 URL
        success: function (data) {
          let htmlTag = ``;
          data.inquiryList.content.forEach((el) => {
              htmlTag += `
                                    <tr>
                                      <td>${el.boardTitle}></td>
                                      <td>${el.boardContent}></td>
                                      <td>${el.boardWriter}></td>
                                       <td>
                                          <a href="/board/boardAnswer/${el.category}">문의답변</a>
                                        </td>
                                    </tr>
                                  `;
              $('tbody').html(htmlTag);
            });
           }
          });
       }*/

//      (()=>{
//        ajaxDataListFn();
//      })();
>>>>>>> dev
