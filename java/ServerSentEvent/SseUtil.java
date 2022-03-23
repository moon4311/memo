package kr.co.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * SSE (Server Sent Event)
 * @author user
 *
 */
public class SseUtil {

//생성된 EventStream 목록을 저장하기 위한 로컬 캐시
  private static Map<String, SseEmitter> sseEmitterCache = new HashMap<String,SseEmitter>();
  private static List<String> removeList = new ArrayList<String>();
  
  public static int getSize() {
    return sseEmitterCache.size();
  }
  
  /**
   * 기본값(5분)으로 생성
   * @param sessionId
   */
  public static SseEmitter addClient(String sessionId) {
    // EventStream 생성 후 1분 경과시 제거
    // 클라이언트는 연결 종료 인지 후 EventStream 자동 재생성 요청
     return addClient(sessionId, 60 * 1000L);
  }
  
  /**
   * 
   * @param sessionId
   * @param timeout  : 0보다 작으면 제한 없음
   */
  public static SseEmitter addClient(String sessionId, long timeout) {
    SseEmitter sseEmitter;
    if(timeout<0) {
      sseEmitter = new SseEmitter();
    }else {
      sseEmitter = new SseEmitter(timeout);
    }
    sseEmitterCache.put(sessionId, sseEmitter);
    System.err.println(" add(+1) : " +getSize());
    return sseEmitter;
  }
  
  public static boolean send(String eventNm, String data) {
    System.err.println("before : "+getSize());
    for(Entry<String, SseEmitter> entry: sseEmitterCache.entrySet()) {
      try {
          entry.getValue().send( SseEmitter
              .event()
              .name(eventNm)
              .data(data)
           );
      } catch (IOException ex) {
        removeList.add(entry.getKey());
        ex.printStackTrace();
      } catch (java.lang.IllegalStateException ex) {
        removeList.add(entry.getKey());
        ex.printStackTrace();
      }
    }
    
    for(String key : removeList) {
      sseEmitterCache.remove(key);
    }
    System.err.println("after : "+getSize());
    return true;
  }
}
