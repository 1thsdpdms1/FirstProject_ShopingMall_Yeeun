$(document).ready(function () {
  // 글 쓰기 폼 제출 이벤트
  $('#boardForm').submit(function (e) {
    e.preventDefault(); // 기본 제출 이벤트 방지
    var formData = $(this).serialize(); // 폼 데이터를 직렬화
    $.ajax({
      type: 'POST',
      url: '/Inquiry', // 글 쓰기 처리를 담당하는 컨트롤러의 URL
      data: formData,
      success: function (data) {
        // 글 쓰기가 성공하면 새로운 글 목록을 불러옴
        loadBoardList();
      }
    });
  });

  // 초기에 페이지가 로드될 때 글 목록을 불러옴
  //    loadBoardList();
});

function loadBoardList() {
  $.ajax({
    type: 'GET',
    url: '/boardList', // 글 목록을 가져오는 컨트롤러의 URL
    success: function (data) {
      $('#boardList').html(data); // 가져온 데이터를 목록 영역에 삽입
    }
  });
}



function ajaxInquiry(event) {

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
      ajaxDataListFn();
    }
  });
}


//let ajaxDataList=new Object();

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

//              let span1 = ``;
//              if (data.inquiryList.pageable.pageNumber + 1 == 1) {
//                span1 = `<span class="span0">START</span>`
//              } else {
//                span1 = ` <a th:href="@{/board/boardInquiry(page=0)}" class="span1">처음</a>`
//              }
//
//              let span2 = ``;
//              if (1 == data.inquiryList.pageable.pageNumber + 1) {
//                span2 = `<span class="span0">이전</span>`
//              } else {
//                span2 = `  <a href="/board/boardInquiry/${data.inquiryList.pageable.pageNumber-1}" class="span1">이전</a> `
//              }
//              let span3 = ``;
//              if (data.inquiryList.totalPages == data.inquiryList.pageable.pageNumber + 1) {
//                span3 = `<span class="span0">다음</span>`
//              } else {
//                span3 = `  <a href="/board/boardInquiry/${data.inquiryList.pageable.pageNumber+1}" class="span1">다음</a> `
//              }
//              let span4 = ``;
//              if (data.inquiryList.totalPages == data.inquiryList.pageable.pageNumber + 1) {
//                span4 = `<span class="span0">END</span>`
//              } else {
//                span4 = `  <a href="/board/boardInquiry/${data.inquiryList.totalPages-1}"class="span1">마지막</span> `
//              }
//              let paging = `
//               <ul>
//                                              <li>
//                                                  ${span1}
//                                              </li>
//
//                                              <li>
//                                                  ${span2}
//                                              </li>
//                                              <li>
//                                                ${span3}
//                                                </li>
//
//                                              <li>
//                                                  ${span4}
//                                              </li>
//                                          </ul>
//                 `;
//
//              $('.paging').html(paging); // 가져온 데이터를 목록 영역에 삽입
//          });
//        }
//      });
//    }

      (()=>{
        ajaxDataListFn();
      })();