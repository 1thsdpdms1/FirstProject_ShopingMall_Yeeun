

$(document).ready(function() {
  // 페이지가 로드될 때 좋아요 상태를 확인하는 함수
  function checkLikeStatus() {
    const shopId = $('#id').val();
    const memberId = $('#memberId2').val();

    // 좋아요 상태를 확인하는 Ajax 요청
    $.ajax({
      type: "GET",
      url: "/shop/checkLikeStatus",
      data: {
        shopId: shopId,
        memberId: memberId
      },
      success: function(response) {
        // 받은 응답에 따라 좋아요 버튼의 클래스를 설정
        const likeButton = $('#likeButton');
        if (response === 'liked') {
          likeButton.addClass('liked');
        }
      },
      error: function(xhr, status, error) {
        console.error(xhr.responseText);
        alert("An error occurred: " + xhr.responseText);
      }
    });
  }

  // 페이지가 로드될 때 좋아요 상태를 확인
  checkLikeStatus();

  // 좋아요 버튼 클릭 시 동작하는 함수
  $('#likeButton').click(function() {
    const shopId = $('#id').val();
    const memberId = $('#memberId2').val();
    const likeButton = $(this);

    const dataVal = {
      'shopId': shopId,
      'memberId': memberId
    };

    // 좋아요 토글 요청
    $.ajax({
      type: "POST",
      url: "/shop/like",
      data: dataVal,
      success: function(response) {
        alert(response);
        // 좋아요 토글 성공 시 버튼 색상 변경
        likeButton.toggleClass('liked');
      },
      error: function(xhr, status, error) {
        console.error(xhr.responseText);
        alert("An error occurred: " + xhr.responseText);
      }
    });
  });
});

function count(type) {

  const price = parseInt($('#shopPrice').val());

  // 현재 화면에 표시된 값
  let number = $('#result').text();
  let priceSumVal = $('#priceSum').text();

  // 더하기/빼기
  if (type === 'plus') {
    number = parseInt(number) + 1;
  } else if (type === 'minus') {
    number = parseInt(number) - 1;
  }

  // 결과 출력
   $('#result').text(number);
  $('#priceSum').text( (number * price));

console.log($('#result').text())
console.log($('#priceSum').text())
}

$('#cartBtn').on('click', cartBtnFn);
function cartBtnFn() {
  const shopId = $('#id').val(); // input 일때 val
  const priceCount = $('#result').text();

  $.ajax({
    type: 'GET',
    url: `/shop/cart/${shopId}/${priceCount}`,
    success: function (res) {
      alert('장바구니 담')
      test();
      console.log(res);
    },
    error: function () {
      alert('못담아');
    }
  });
}

function test() {
        if (!confirm("장바구니로 이동하시겠습니까?")) {
            alert("네");
        } else {
            location.href=`/shop/cartList`;
        }
    }