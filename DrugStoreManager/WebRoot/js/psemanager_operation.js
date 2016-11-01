(function userManagerOperation(){
	function deletePer(){
		let checks=document.getElementsByName("id_check");
		let param="";
		for(let i=0,len=checks.length;i<len;i++){
			if(checks[i].checked==true){
				if(param==""){
					param+=checks[i].id;
				}else{
					param+=",";
					param+=checks[i].id;
				}

			}
		}
		if(param==""){
			alert("请选择要删除的数据");
		}else{
			alert(param);
			window.location.href="pse_delPurchase.action?&ids="+param;
		}
	}
	function update(){
		let checks=document.getElementsByName("id_check");
		let param="";
		let count=0;
		for(let i=0,len=checks.length;i<len;i++){
			if(checks[i].checked==true){
				param+=checks[i].id;
				count++;
			}
		}
		if(count>1){
			alert("一次只能修改一行数据");
		}else if(count==0){
			alert("请选择要修改的数据");
		}else if(count==1){
			window.location.href="pse_editPurchase.action?&id="+param;
		}
	}
	function init(){
		//查询
		document.querySelector("#btn_selectUser").addEventListener("click",()=>{
			var keyword=document.querySelector("#keyword").value;
			window.location.href="pse_showPurchase.action?currPage=1&keyword="+keyword;
		},false);
		//新增
		document.querySelector("#btn_addUser").addEventListener("click",()=>{
			window.location.href="pse_doaddPurchase.action";
		},false);
		//修改
		document.querySelector("#btn_updateUser").addEventListener("click",update,false);
		//删除
		document.querySelector("#btn_deleteUser").addEventListener("click",deletePer,false);
		document.querySelector("#all").addEventListener("change",()=>{
			let checks=document.getElementsByName("id_check");
			let all_check=document.querySelector("#all");
			for(let i=0,len=checks.length;i<len;i++){
				if(all_check.checked==true){
					checks[i].checked=true;
				}else{
					checks[i].checked=false;
				}
			}
		},false);
		//有效期预警
		document.querySelector("#btn_warn").addEventListener("click",()=>{
			var keyword=document.querySelector("#keyword").value;
			window.location.href="pse_showDateWarn.action?currPage=1&keyword="+keyword;
		},false);
		
		//打印报表
		document.querySelector("#btn_print").addEventListener("click",()=>{
			window.location.href="pse_doprint.action";
		},false);
	}
	window.addEventListener("load",init,false);
})();
