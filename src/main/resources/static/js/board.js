let index = {
	init: function() {
		$("#btn-save").on("click", () =>  {
			this.save();
		});
	},

	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		//console.log(data);

		//ajax호출시 default가 비동기 호출
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), //java object를 JSON으로 변경, http body데이터
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때  기본적으로 모든 것이 String (근데 생긴게 JSON이라면 ) => json이라고 알려주면 javascript object로 바꿔준다
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
}

index.init();