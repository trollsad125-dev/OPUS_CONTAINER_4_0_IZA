/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLV_TRAINING_001.js
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2023.04.19
*@LastModifier : 
*@LastVersion : 1.0
* 2023.04.19 
* 1.0 Creation
=========================================================*/
    
	var tabObjects=new Array();
	var tabCnt=0 ;
	var beforetab=1;
	var sheetObjects=new Array();
	var sheetCnt=0;
	var rowcount=0;
    document.onclick=processButtonClick;


    function setSheetObject(sheet_obj){
        sheetObjects[sheetCnt++]=sheet_obj;
     }
    function resizeSheet(){
      ComResizeSheet(sheetObjects[0]);
     }

     function loadPage() {
 		for(i=0;i<sheetObjects.length;i++){
			ComConfigSheet (sheetObjects[i]);
			initSheet(sheetObjects[i],i+1);
			ComEndConfigSheet(sheetObjects[i]);
			doActionIBSheet(sheetObjects[i],document.form,IBSEARCH)
		}
 	}

    function processButtonClick(){
        var sheetObject1=sheetObjects[0];
        var formObject=document.form;
   	try {
   		var srcName=ComGetEvent("name");
           switch(srcName) {
           	case "btn_Add":
           		doActionIBSheet(sheetObject1,formObject,IBINSERT);
           		break;
           	case "btn_Retrieve":
           		doActionIBSheet(sheetObject1,formObject,IBSEARCH);
           		break;
           	case "btn_Save":
           		doActionIBSheet(sheetObject1,formObject,IBSAVE);
           		break;
           	case "btn_DownExcel":
           		doActionIBSheet(sheetObject1,formObject,IBDOWNEXCEL);
           		break;
           } // end switch
   	}catch(e) {
   		if( e == "[object Error]") {
   			ComShowMessage(OBJECT_ERROR);
   		} else {
   			ComShowMessage(e);
   		}
   	}
    }


    function initSheet(sheetObj,sheetNo) {
        var cnt=0;
		var sheetID=sheetObj.id;
        switch(sheetID) {
            case "sheet1":
            	with(sheetObj){
                var HeadTitle="|Del|Msg Cd|Msg Type|Msg level|Message|Description" ;
                var headCount=ComCountHeadTitle(HeadTitle);

                SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:1 } );

                var info    = { Sort:1, ColMove:1, HeaderCheck:0, ColResize:1 };
                var headers = [ { Text:HeadTitle, Align:"Center"} ];
                InitHeaders(headers, info);

                var cols = [ {Type:"Status",    Hidden:1, Width:30,   Align:"Center",  ColMerge:0,   SaveName:"ibflag" },
	                     {Type:"DelCheck",  Hidden:0, Width:45,   Align:"Center",  ColMerge:1,   SaveName:"DEL",         KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	                     {Type:"Text",      Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_msg_cd",  KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
	                     {Type:"Combo",     Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_tp_cd",   KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, ComboText:"Server|UI|Both", ComboCode:"S|U|B" },
	                     {Type:"Combo",     Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_lvl_cd",  KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, ComboText:"ERR|WARNING|INFO", ComboCode:"E|W|I" },
	                     {Type:"Text",      Hidden:0, Width:400,  Align:"Left",    ColMerge:0,   SaveName:"err_msg",     KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, MultiLineText:1 },
	                     {Type:"Text",      Hidden:0, Width:250,  Align:"Left",    ColMerge:0,   SaveName:"err_desc",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 } ];
                  
                InitColumns(cols);
                SetWaitImageVisible(0);
                resizeSheet();
                }
            break;
        }
    }

    function doActionIBSheet(sheetObj,formObj,sAction) {
        switch(sAction) {
		case IBSEARCH:      //Search
			ComOpenWait(true);
			formObj.f_cmd.value=SEARCH;
			sheetObj.DoSearch("CLV_TRAINING_001GS.do", FormQueryString(formObj) );
			break;
		case IBSAVE:        //Save
        	formObj.f_cmd.value=MULTI;
            sheetObj.DoSave("CLV_TRAINING_001GS.do", FormQueryString(formObj));
			break;
		case IBINSERT:      // Insert
			rowcount=sheetObj.RowCount();
			row=sheetObj.DataInsert(-1);
			break;
		case IBDOWNEXCEL:	//Download Excel
			if(sheetObj.RowCount() < 1){
				ComShowMessage("No Row");
			}else{
				sheetObj.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj), SheetDesign:1, Merge:1});
			}
			break;
        }
    }
    function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
     	ComOpenWait(false);
     }
    
     function sheet1_OnChange(sheetObj,Row,Col){
    	 if(Col == 2){
			var code=sheetObj.GetCellValue(Row, Col);
    	    for(var int=1; int < sheetObj.RowCount(); int++) {
			var orlcode=sheetObj.GetCellValue(int, Col);
				if(code != '' && int != Row && code == orlcode){
    				 ComShowCodeMessage('COM131302',code);
    				 sheetObj.SetCellValue(Row, Col,"");
    				 return;
    			 }
    		 }
    	 }
     }
     
