// https://developer.android.com/reference/android/app/Activity
public class SampleActivity extends Activity{


  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
  }

  //활동이 시작됨 상태에 들어가면 시스템은 이 콜백을 호출
  // 앱은 활동을 포그라운드에 보내 상호작용할 수 있도록 준비합니다. 예를 들어 이 메서드에서 앱이 UI를 관리하는 코드를 초기화
  @Override
  protected void onStart() {
      super.onStart();
  }
  
  //활동이 재개됨 상태에 들어가면 포그라운드에 표시
  @Override
  protected void onResume(){
    super.onResume();
  }
  
  // 활동이 포그라운드에 있지 않게 되었다는 것을 나타냅니다
  // 애플리케이션 또는 사용자 데이터를 저장하거나, 네트워크 호출을 하거나, 데이터베이스 트랜잭션을 실행해서는 안 됩니다.
  //  부하가 큰 종료 작업은 onStop() 상태일 때 실행
  @Override
  protected void onPause(){
    super.onPause();
  }
  // 활동이 사용자에게 더 이상 표시되지 않으면 중단됨 상태에 들어가고, 시스템은 onStop() 콜백을 호출.
  // 예를 들어 새로 시작된 활동이 화면 전체를 차지할 경우에 적용됩니다
  @Override
  protected void onStop(){
    super.onStop();
  }
  
  // 활동이 소멸되기 전에 호출
  @Override
  protected void onDestory(){
    super.onDestory();
  }
  
  
  
  
}
