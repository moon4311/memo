package kr.co.common.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import kr.co.common.util.SseUtil;

@RestController
@CrossOrigin
class SseController{
     
     
    // 클라이언트의 요청으로 sessionId에 대한 EventStream 생성
    @GetMapping("/sse/connect")
    public SseEmitter createEventStream( 
        HttpServletRequest request, HttpServletResponse response
    ) throws InstantiationException, IllegalAccessException {

        String sessionId = request.getSession().getId();
      
        // 리버스 프록시에서의 오동작을 방지
        response.addHeader("X-Accel-Buffering", "no");
        
        return SseUtil.addClient(sessionId);
    }

    
    // 생성된 모든 EventStream에 Event 전송
    @GetMapping("/sse/createEvent")
    public String createEvent(@RequestParam Map<String,Object> param){
      SseUtil.send("sse", "this is test Data "+ System.currentTimeMillis());
      return "OK";
    }
    
    
}
