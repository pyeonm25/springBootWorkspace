function deleteRecord(id){
    fetch('/study/'+id ,{ method: 'DELETE'} )
        .then((response) => response.text())
        .then((data) => refresh(data));
}

function refresh(data){
    if(data=="ok"){
        alert("기록삭제 성공");
        location.href="/study/records";
    }
}
