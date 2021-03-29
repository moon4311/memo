/** 파라미터값 form에 주입 2019.12.09
* params : json Type
* checkbox[id] == checkbox[name]+checkbox[value];
*/
function fnInjectionParams(params){
	for(key in params){
		var value = params[key];
		if(value=='') continue;
		var target = $("[name="+key+"]");
		var type = target.attr("type");
		
		if(target.is("select") || target.is("textarea")){
	//SELECT, TEXTAREA
			target.val(value);
		}else if(target.is("input")){
	//RADIO
			if(type=="radio"){
				target.filter("[value="+value+"]").prop("checked","checked");
				
	// TEXT, HIDDEN
			}else if(type=="text" || type=="hidden"){
				target.val(value);
				
	// CHECKBOX
			}else if(type=="checkbox"){
				value.split(",").forEach(
					function(valOne){
						$("#"+key+valOne).prop("checked","checked");
				});
			}
		}
	}
}

//조건에 따른 히든처리 2020.01.16
function hiddenFlag(target,bool){
	bool ? $(target).show() : $(target).hide();
}
