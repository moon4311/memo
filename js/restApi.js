
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



function get(url,params, callback, async) {
	commonAjax(url,"GET",params,callback,"json",async);
}

function post(url,params,callback,async){
	if(params)
		commonAjax(url,"POST", params,callback, "json",async);
	else{
		alert("저장할 데이터가 없습니다.");
	}
}
function put(url,params,callback,async){
	if(params)
		commonAjax(url,"PUT", params,callback, "json",async);
}
function del(url,params,callback,async){
	if(params)
		commonAjax(url,"DELETE", params, callback, "json",async);
}


function commonAjax(url, method, params, callback, dataType,  async) {
	if (!(dataType == "text" || dataType == "json")) {
		dataType = "text";
	}
	if(typeof params != 'string'){
		params = JSON.stringify(params);
	}
	
	$.ajax({
		type: method
		, url: url
		, dataType: dataType // text, html, script, json, jsonp, xml. (서버가 던져주는 데이터형.)
		, async: !async
		, headers: {'jwt' : jwt }
		, contentType: "application/json;charset=UTF-8"
		, data: params
		, success: function(data, status) {
			
			if(data.cd == 100){
				alert("로그인 후 이용해주시기 바랍니다.");
				location.href="/members/login.do";
			}else if(data.cd!=0 ) {
				alert(data.msg);
			} else {
				if(data.msg != "OK") alert( data.msg );
				if (!(callback === "" || callback === undefined || callback === null)) {
					callback(data);
				}
			}
		}
		, error: function(request, status, statusText) {
			if (request.status == "401") {
				fnAlert(0, { message: "jwt 세션이 만료되었습니다." });
			} else {
				if (request.status == '200') {
					callback();
				} else if (status == 'error') {
					fnAlert(0, { message: "세션이 만료되었거나 사용권한이 없습니다." });
				} else {
					fnAlert(0, { message: "작업중 오류가 발생하였습니다." });
				}
			}
		}
		, beforeSend: function() {
			$('.spinner-box').show();
		}
		, complete: function() {
			$('.spinner-box').hide();
		}
		, timeout: 100000
	});
}
