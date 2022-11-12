import com.fasterxml.jackson.databind.ObjectMapper;

public class TypeUtil {
  

  public final static ObjectMapper om = new ObjectMapper();

  //편하지만 직접 map을 만들어 변환하는것 보다 느리다. 대용량처리일 경우 비추천
  public static Map<String,Object> toMap(Object ob) {
		return om.convertValue(ob, Map.class);
	}
	
	public static <T> T toObj(Map<String,Object> map, Class s) {
		return (T) om.convertValue(map, s);
	}
}
