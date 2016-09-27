(function userManagerPage(){
	function page_button_state(){
		if(document.querySelector("#currentPage").textContent==1){
			document.querySelector("#btn_firstPage").disabled=true;
			document.querySelector("#btn_prePage").disabled=true;
		}
		if(document.querySelector("#currentPage").textContent==document.querySelector("#totalPage").textContent){
			document.querySelector("#btn_nextPage").disabled=true;
			document.querySelector("#btn_lastPage").disabled=true;
		}
	}
	function forward(url){
		window.location.href=url;
	}
	function init(){
		page_button_state();
		document.querySelector("#btn_firstPage").addEventListener("click",()=>{
			let keyword=document.querySelector("#keyword").value;
			forward('user.servlet?param=init&currentPage=1&keyword='+keyword);
			page_button_state();
		},false);
		document.querySelector("#btn_nextPage").addEventListener("click",()=>{
			let currentPage=document.querySelector("#currentPage").textContent;
			let keyword=document.querySelector("#keyword").value;
			window.location.href='user.servlet?param=init&currentPage='+(1+parseInt(currentPage)+'&keyword='+keyword);
			page_button_state();
		},false);		
		document.querySelector("#btn_prePage").addEventListener("click",()=>{
			let currentPage=document.querySelector("#currentPage").textContent;
			let keyword=document.querySelector("#keyword").value;
			window.location.href='user.servlet?param=init&currentPage='+(parseInt(currentPage)-1)+'&keyword='+keyword;
			page_button_state();
		},false);	
		document.querySelector("#btn_lastPage").addEventListener("click",()=>{
			let totalPage=document.querySelector("#totalPage").textContent;
			let keyword=document.querySelector("#keyword").value;
			window.location.href='user.servlet?param=init&currentPage='+totalPage+'&keyword='+keyword;
			page_button_state();
		},false);
		document.querySelector("#select_jumpPage").addEventListener("change",()=>{
			let keyword=document.querySelector("#keyword").value;
			window.location.href='user.servlet?param=init&currentPage='+document.querySelector("#select_jumpPage").value+'&keyword='+keyword;
		},false);
		document.querySelector("#btn_go").addEventListener("click",()=>{
			let keyword=document.querySelector("#keyword").value;
			window.location.href='user.servlet?param=init&currentPage='+document.querySelector("#input_page").value+'&keyword='+keyword;
		},false);
		//查询
		document.querySelector("#btn_query").addEventListener("click",()=>{
			let keyword=document.querySelector("#keyword").value;
			window.location.href='user.servlet?param=init&keyword='+document.querySelector("#keyword").value;
		},false);
		
	}
	window.addEventListener("load",init,false);
})();