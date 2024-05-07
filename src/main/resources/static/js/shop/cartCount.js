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
      alert('장바구니에 담으시겠습니까?')
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