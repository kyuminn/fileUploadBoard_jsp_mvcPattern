/**
 * 
 */


function uploadCheck() {
	if(document.uploadForm.uploader.value=="") {
		alert("이름을 입력하세요!");
		document.uploadForm.uploader.focus();
		return false;
	}
	var fileCheck = document.getElementById("uploadFile").value;
	if (!fileCheck){
		alert("파일을 첨부해 주세요!");
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

function UpdateCheck(){
	if (document.updateForm.password.value==""){
		alert("비밀번호를 입력하세요!");
		document.updateForm.password.focus();
		return false;
	}
	var updateFileCheck = document.getElementById("updateFile").value;
	if (!updateFileCheck){
		alert("새 파일을 첨부해 주세요!");
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


