function save() {
	
	var f = document.writeForm;
	var fullfile = f.file.value;
	var arr = fullfile.split("\\");
	var filename = arr[arr.length-1];
	if(f.title.value=="") {
		alert("제목을 입력하시오.");
		f.title.focus();
		exit;
	}
	else if(f.title.value.length>25) {
		alert("제목 길이가 초과되었습니다.");
		f.title.focus();
		exit;
	}
	if(f.content.value=="") {
		alert("내용을 입력하시오");
		f.content.focus();
		exit;
	}
	else if(f.content.value.length>250) {
		alert("내용 길이가 초과되었습니다.");
		f.content.focus();
		exit;
	}
	else if(filename.length>20) {
		alert("파일 이름이 너무 깁니다.");
		exit;
	}
	else {
		f.submit();
		return;
	}
}