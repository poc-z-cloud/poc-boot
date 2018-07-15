function btnAdd_onclick() {
	window.location.href="new";
}


function btnQuery_onclick() {
	var aRet=new Array()
	aRet=window.showModalDialog("query.jsp")
	if (aRet[0]=='OK')
	{
		document.frmAction.sWhere.value=aRet[1];
		document.frmAction.sCurrentPage.value=1;
		document.frmAction.sAction.value='';
		document.frmAction.submit();


	}
}

function goSearch(){
	frmList.action='/console/role/searchByNameAndDecription';
	return;
}
function btnDel_onclick()
{
var n,i,sSQL
	if (confirm('Are you sure you want to delete the records?')==false) return;
	
	var elements = document.getElementsByClassName("check");
	for(var i=0; i<elements.length; i++) {
	    if (elements[i].checked){
//			frmAction.sAction.value="DEL";
			frmList.submit();
			return;
	    }
	}
	
	alert("Please select at least one！");
	return;
}

function btnOK_onclick() {
	if (!CheckInput()) return false;
	frmAction.sAction.value='UPDATE';
	frmAction.submit()

}

function btnCancel_onclick() {
	window.history.back();

}


function btnPageDown_onclick() {
	frmAction.adminCurrentPage.value=frmAction.sDirectPage.value
	frmAction.sAction.value=""
	frmAction.submit();

}

function selectAll() {
	var elements = document.getElementsByClassName("check");
	for(var i=0; i<elements.length; i++) {
		elements[i].checked=true;
	}

}

function selectNone() {
	var elements = document.getElementsByClassName("check");
	for(var i=0; i<elements.length; i++) {
		elements[i].checked=false;
	}
}



function btnSelectAll_onclick() {
	//只有一条记录时
var oSource=event.srcElement
	if 	(oSource.value=='All')
	{
		oSource.value='None';
		var elements = document.getElementsByClassName("check");
		for(var i=0; i<elements.length; i++) {
			elements[i].checked=true;
		}
		return;
	}
	else
	{
		oSource.value='All';
		var elements = document.getElementsByClassName("check");
		for(var i=0; i<elements.length; i++) {
			elements[i].checked=false;
		}
		return;

	}

}

var showString = "inline";
function showHideFilter() {
    var filterTableImg = document.getElementById ("filterTableImg");
    
    if (filterTableImg.src.indexOf("wtable_filter_row_show") > 0) {
        disFilter = showString;
        filterTableImg.src = filterTableImg.src.replace("wtable_filter_row_show","wtable_filter_row_hide");
        filterTableImg.title = "Hide filter function";
    } else {
        filterTableImg.src = filterTableImg.src.replace("wtable_filter_row_hide","wtable_filter_row_show");
        filterTableImg.title = "Show filter function";
        disFilter = "none";
    }

    document.getElementById("filterControls").style.display = disFilter;
}


function clearFilter(formName) {
    var theForm = document.getElementById(formName);
    for(i=0;i<theForm.length;i++){
    	if(theForm[i].type=="text"){
    		theForm[i].value="";
    	}
    }
//	document.frmAction.name.value="";
//	document.frmAction.description.value="";
    document.getElementById("searchAction").click();
}


function moveAll(src, target) {
	var obj=src;
	
	for (var i=0; i<obj.options.length; i++) {
	    obj.options[i].selected = true;
	}	
	move(src,target);
}

function move(SourceList,TargetList){
	var itemSelected=false;
	for (var i=SourceList.options.length-1; i>=0; i--) {
		if (SourceList.options[i].selected){
		    var newOption = SourceList.options[i].cloneNode(true);

		    SourceList.removeChild(SourceList[i]);
		    TargetList.appendChild(newOption);
			
		    itemSelected=true;
		}
	}	
	
	if (!itemSelected){
		alert("Please select at least one item to move.");
		return;
		
	}
}
