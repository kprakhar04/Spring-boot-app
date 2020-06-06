const hardwares=[
	"x",
	"y",
	"z"
];

const softwares=[
	"a",
	"b",
	"c"
];

function prepopulate(){
	const ele= document.getElementsByName("requestType");
	const select=document.getElementById("requestValues");
	select.innerHTML='';
	if(ele[0].checked){
		for(let el of hardwares){
			const option = document.createElement("option");
			option.text = el;
			option.value = el;
			select.appendChild(option);
		}
	} else{
		for(let el of softwares){
			const option = document.createElement("option");
			option.text = el;
			option.value = el;
			select.appendChild(option);
		}
	}
}