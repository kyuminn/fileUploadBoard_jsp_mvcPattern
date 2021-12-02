/**
 * 
 */


function uploadCheck() {
	if(document.uploadForm.uploader.value=="") {
		alert("제목을 입력하세요!");
		document.uploadForm.uploader.focus();
		return false;
	}
		if(document.uploadForm.description.value=="") {
		alert("내용을 입력하세요!");
		document.uploadForm.description.focus();
		return false;
	}
	if(document.uploadForm.password.value=="") {
		alert("비밀번호를 입력하세요!");
		document.uploadForm.password.focus();
		return false;
	}
}

function UpdatePassCheck(){
	if (document.updateForm.password.value==""){
		alert("비밀번호를 입력하세요!");
		document.updateForm.password.focus();
		return false;
	}
}

function DeletePassCheck(){
	if (document.delForm.password.value==""){
		alert("비밀번호를 입력하세요!");
		document.delForm.password.focus();
		return false;
	}
}


