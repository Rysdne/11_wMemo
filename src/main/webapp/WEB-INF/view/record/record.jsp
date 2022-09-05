<%@ page import="javax.naming.spi.DirStateFactory.Result"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/wMemo/css/content/style.css" type="text/css" rel="stylesheet" />
<script src="//cdnjs.cloudflare.com/ajax/libs/annyang/2.6.1/annyang.min.js"></script>
<script type="text/javascript">
	var recordState = document.querySelector("#recordState");
	var button = document.querySelector("#speech");
	var recordSave = document.querySelector("#recordSave");
	var slicedText = document.querySelector("#slicedText");
	var stackText = document.querySelector("#stackText");
	var isRecognizing = false;
	var lastText = "";
	var stopThatShit="";

	if ('SpeechRecognition' in window) {
		// Speech recognition support. Talk to your apps!
		console.log("음성인식을 지원하는 브라우저입니다.")
	}

	try {
		var recognition = new (window.SpeechRecognition
				|| window.webkitSpeechRecognition
				|| window.mozSpeechRecognition || window.msSpeechRecognition)();
	} catch (e) {
		console.error(e);
	}

	recognition.lang = 'ko-KR'; //선택하게 해줘야 할듯 .
	recognition.interimResults = false;
	recognition.maxAlternatives = 5;
	//recognition.continuous = true;

	function speech_to_text() {

		recognition.start();
		isRecognizing = true;

		recognition.onstart = function() {
			console.log("Listening..")
			recordState.innerHTML = "Listening.. ";
			button.disabled = true;
			recordSave.disabled = true;
		}

		recognition.onresult = function(event) {

			var resText = event.results[0][0].transcript;
			lastText = lastText + resText + "<br>";
			slicedText.innerHTML = resText;
			stackText.innerHTML = lastText;
		};

		recognition.onend = function() {
			if(recordState.innerHTML=="Paused.. "){
				return;
			}else{
			recordState.innerHTML = "Listening..!";
			//button.disabled = false;
			//recordSave.disabled = false;
			 
			//isRecognizing = false;
			//*음성 입력이 꺼졌다 켜지는 것을 방지함
			
			recognition.start();
			}
		}
	}

	function stop() {
		recognition.stop();
		recordState.innerHTML = "Paused.. ";
		button.disabled = false;
		recordSave.disabled = false;
		isRecognizing = false;
		
	}
	
	$('#recordSave').click(function(){
		$('#recordText').val($('#stackText').html());
		var uId=$('#recordId').val();
    	var recordTitle=$('#recordTitle').val();
    	var recordText=$('#recordText').val();
    	if(recordTitle=="") {
    		alert('타이틀을 입력해주세요');
    		return;
    	} else {
		   	alert('저장 완료');
			$.ajax({
				url : "/wMemo/record/recordSave",
				type : "post",
				dataType : "text",
				data : {"id":uId, "rtitle":recordTitle, "rtext":recordText},
				success : function(result) {
					$('#aside_box').html(result);
				}
			});
    	}
	});
</script>
<div id="recordBox">
	<div>
		<span id="recordState">Initialized..</span>
		 | 
		<button id="speech" onclick="speech_to_text()">Rec</button>
		<button id="stop" onclick="stop()">Pause</button> | 
		<input type="text" id="recordTitle" name="recordTitle" style="width: 15vh;" placeholder="Title"> 
		<button id="recordSave" onclick="recordSave()">Save</button>
	</div>
	<div id="textWrapper">
		<input type="hidden" id="recordId" name="recordId" value="${sessionScope.id}">
		<div id="slicedText" class="textbox" style="height: 4vh; display: none;"></div>
		<div id="stackText" class="textbox"></div>
		<input type="hidden" id="recordText" name="recordText">
	</div>
</div>