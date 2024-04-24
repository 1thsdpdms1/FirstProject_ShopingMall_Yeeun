let memberPhoneNumber = document.querySelector("#memberPhoneNumber");
let sellerPhoneNumber = document.querySelector("#sellerPhoneNumber");
const memberShowPw = $('#memberShowPw');
const sellerShowPw = $('#sellerShowPw');

memberShowPw.on('click', memberShowPwFn);
sellerShowPw.on('click', sellerShowPwFn);

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

