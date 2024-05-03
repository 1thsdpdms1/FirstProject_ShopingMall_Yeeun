let memberPhoneNumber = document.querySelector("#memberPhoneNumber");
let sellerPhoneNumber = document.querySelector("#sellerPhoneNumber");

//const memberShowPw = $('#memberShowPw');
//const sellerShowPw = $('#sellerShowPw');
//const memberCartList = $('#memberCartListBtn');

$('#memberShowPw').on('click', memberShowPwFn);
$('#sellerShowPw').on('click', sellerShowPwFn);
$('#memberCartListBtn').on('click', memberCartListFn);
$('#closeCartListBtn').on('click', cartListCloseFn);

function cartListCloseFn() {
    $(".cartListTable").slideUp(600);
    $(".cartListBtn").show();
    $(".cartList").css({borderLeft: '0'});
}

function memberCartListFn(event) {

    event.preventDefault();

    const memberId = $("input#id").val();
    $.ajax({
        type: 'GET',
        url: "/member/memberCartList/" + memberId,

        success: function (res) {

            let data = ``;
            let totalPrice = ``;
            this.totalPrice = 0;

            console.log(res)
            console.log(res.cartList)
            console.log(typeof res.cartList)
            //
            // if (res.length === 1) {
            //     alert("장바구니가 없습니다.");
            // } else {
            //     alert("ㅇㅇ");
            // }
        if(res.cartList && Array.isArray(res.cartList)){
            res.cartList.forEach((el) => {
                data += `
                     <tr>
                         <td>${el.shopEntity.shopTitle}</td>
                         <td>${el.shopEntity.shopPrice}</td>
                         <td>${el.count}</td>
                     </tr>
                     `;
                this.totalPrice += el.shopEntity.shopPrice * el.count;
                // data += `<span th:text="${#aggregates.sum(res.shopEntity.shopPrice * res.count) }"> </span>`;
            });
            totalPrice += "총 합계 : " + this.totalPrice + " 원";
            // console.log(this.totalPrice);
            $(".totalPrice").html(totalPrice).slideDown(600);
            $(".tData").html(data).slideDown(600);
            $(".cartListTable").slideDown(600);
            $(".cartListBtn").hide();
            $(".cartList").css({borderLeft: '1px solid #333333'});
        }
        else{
            alert("장바구니가 없습니다.");
        }

        },
        error: function () {
            alert("장바구니가 없습니다.");
        }
    });
}

function memberShowPwFn() {
    if ($("#memberUserPw").attr("type") == "password") {
        $("#memberUserPw").attr("type", "text");
        $("#memberUserPwCheck").attr("type", "text");
    } else {
        $("#memberUserPw").attr("type", "password");
        $("#memberUserPwCheck").attr("type", "password");
    }
}

function sellerShowPwFn() {
    if ($("#sellerUserPw").attr("type") == "password") {
        $("#sellerUserPw").attr("type", "text");
        $("#sellerUserPwCheck").attr("type", "text");
    } else {
        $("#sellerUserPw").attr("type", "password");
        $("#sellerUserPwCheck").attr("type", "password");
    }
}

const memberPhoneNumberInput = () => {

    let val = memberPhoneNumber.value.replace(/\D/g, "");
    let len = val.length;
    let result = '';
    if (len < 4) {
        result = val;
    } else if (len < 8) {
        result += val.substring(0, 3);
        result += "-";
        result += val.substring(3);
    } else if (len <= 13) {
        result += val.substring(0, 3);
        result += "-";
        result += val.substring(3, 7);
        result += "-";
        result += val.substring(7, 13);
    } else {
        result = val;
    }
    memberPhoneNumber.value = result;
}
const sellerPhoneNumberInput = () => {

    let val = sellerPhoneNumber.value.replace(/\D/g, "");
    let len = val.length;
    let result = '';

    if (len < 4) {
        result = val;
    } else if (len < 8) {
        result += val.substring(0, 3);
        result += "-";
        result += val.substring(3);
    } else if (len <= 13) {
        result += val.substring(0, 3);
        result += "-";
        result += val.substring(3, 7);
        result += "-";
        result += val.substring(7, 13);
    } else {
        result = val;
    }
    sellerPhoneNumber.value = result;
}

function onlyNumber() {
    const reg = /\D/g;
    event.target.value = event.target.value.replace(reg, "");
}

function openMemberJoin() {
    memberSellerJoin(true);
}

function openSellerJoin() {
    memberSellerJoin(false);
}


function memberSellerJoin(isTrue) {
    if (isTrue) {
        $('#memberJoin-con').show();
        document.getElementById("memberJoinBtn").style.border = '1px solid blue';
        document.getElementById("memberJoinBtn").style.color = 'blue';
        $('#sellerJoin-con').hide();
        document.getElementById("sellerJoinBtn").style.border = '1px solid #ccc';
        document.getElementById("sellerJoinBtn").style.color = '#ccc';

    } else {
        $('#memberJoin-con').hide();
        document.getElementById("memberJoinBtn").style.border = '1px solid #ccc';
        document.getElementById("memberJoinBtn").style.color = '#ccc';
        $('#sellerJoin-con').show();
        document.getElementById("sellerJoinBtn").style.border = '1px solid blue';
        document.getElementById("sellerJoinBtn").style.color = 'blue';
    }
}

// (() => memberCartListFn())();