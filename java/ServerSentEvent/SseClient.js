// eventSource 생성
// addEventListener 은  SseUtil.send.eventNm 과 일치시킬 것
const eventSource = new EventSource('/sse/connect')
          eventSource.addEventListener("sse", function(event) {
               console.log(event.data);
          });

//테스트
$.ajax({ method:"GET"
        , url:"/sse/createEvent"
        , success:function(data){console.log(data);} 
       });
