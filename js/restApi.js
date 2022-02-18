
/**
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
	imgList = [];
	childList = [];
	
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
		get(this.path+"/info.json/"+data,"", (rslt)=>{
			this.info= rslt.info; 
			if(rslt.imgList){	this.imgList 	= rslt.imgList; }
			if(rslt.childList){ this.childList 	= rslt.childList; }
		},true);
		return this.info;
	}
	
	//이미지리스트 조회
	getImgList(data){
		return this.imgList;
	}
	
	getInfo2(data){
		get(this.path+"/info.json",data, (rslt)=>{ this.info= rslt.info; },true);
		return this.info;
	}
	
	post(data, callback){
		if(!callback) callback = this.after;
		 post(this.path+'/info.json', data, callback, true  ); 
	}
	
	put(data, callback){
		if(!callback) callback = this.after;
		 put(this.path+'/info.json', data, callback, true  );
	}
	
	del(data, callback){
		if(!callback) callback = this.after;
		 del(this.path+'/info.json', data, callback, true  ); 
	}

	after(data){
		try{
			afterCustom(data);
		}catch(e){
			console.log("Need to create function\n - afterCustom(); : 펑션");
		}
	}	
}
