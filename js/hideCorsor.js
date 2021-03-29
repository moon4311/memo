var seconds = 0;
var hideVal = 5; //5초 후
$(document).ready(function(){
  	$(document).on("mouseover",function(){
    		cursorCnt = 0;
    	});
    setInterval(mouseHide,1000); // 커서 숨김처리 

});
  
function mouseHide(){
    	seconds++;
    	if(cnt<hideVal){
    		$("*").css("cursor","");
    	}else if(cursorCnt>=hideVal){
    		$("*").css("cursor","none");
    	}
    }
