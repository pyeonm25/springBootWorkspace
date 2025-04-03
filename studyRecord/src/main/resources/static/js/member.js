function deleteMember(id){
    fetch('/member/'+id ,{ method: 'DELETE'} )
        .then((response) => response.text())
        .then((data) => refresh(data));
}

function refresh(data){
    if(data=="ok"){
        alert("회원탈퇴 성공");
        location.href="/member/members";
    }
}
