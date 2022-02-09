
/**
  base : jquery, $.ajax

	사용방법
	1. 초기화
	var memberApi = new restAPI('/members');
	2. 목록조회
	var memberList = memberApi.getList(data);
	3. 한개조회
	var member = memberApi.getInfo(data);
	
  
 */

class restAPI {
	path = "";
	info = null;
	list = [];
	
	constructor(path){
		this.path = path;
	}
	//목록 조회
	getList(data){
		get(this.path+"/list.json",data, (rslt)=>{ 	this.list = rslt.list; } ,true);
		return this.list;
	}
	
	//한개 조회
	getInfo(data){
		get(this.path+"/info.json/"+data,null, (rslt)=>{ this.info= rslt.info; },true);
		return this.info;
	}
	post(data){
		 post(this.path+'/info.json', data, this.after, true  ); 
	}
	
	put(data){
		 put(this.path+'/info.json', data, this.after, true  );
	}
	
	del(data){
		 del(this.path+'/info.json', data, this.after, true  ); 
	}

	after(){
		try{
			afterCustom();
		}catch(e){
			console.log("Need to create function\n - afterCustom(); : 펑션");
		}
		
		
	}	
}
