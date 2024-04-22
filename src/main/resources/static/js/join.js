function openMemberJoin() {
    memberSellerJoin(true);
}
function openSellerJoin() {
    memberSellerJoin(false);
}

function memberSellerJoin(isTrue) {
    if (isTrue) {
        $('#memberJoin-con').show();
        document.getElementById("memberJoinBtn").style.borderBottom='1px solid black';
        $('#sellerJoin-con').hide();
        document.getElementById("sellerJoinBtn").style.borderBottom='1px solid #ccc';

    }
    else{
        $('#memberJoin-con').hide();
        document.getElementById("memberJoinBtn").style.borderBottom='1px solid #ccc';
        $('#sellerJoin-con').show();
        document.getElementById("sellerJoinBtn").style.borderBottom='1px solid black';
    }
}