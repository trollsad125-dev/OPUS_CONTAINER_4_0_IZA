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
msgs["PRC00001"] = "No Column to download";
	var sheetObjects=new Array();
	var sheetCnt=0;
    document.onclick=processButtonClick;
    /**
     * Set Sheet Object into SheetObjects
     * @param sheet_obj
     * @returns
     */
    function setSheetObject(sheet_obj){
        sheetObjects[sheetCnt++]=sheet_obj;
     }
    /**
     * resize Sheet
     * @returns
     */
    function resizeSheet(){
      	         ComResizeSheet(sheetObjects[0]);
      }
     /**
      * Load Page and Call IBSearch
      * @returns
      */
     function loadPage() {
 		for(i=0;i<sheetObjects.length;i++){
			ComConfigSheet (sheetObjects[i]);
			initSheet(sheetObjects[i],i+1);
			ComEndConfigSheet(sheetObjects[i]);
			doActionIBSheet(sheetObjects[i],document.form,IBSEARCH)
		}
 	}
/**
 * Process when Click any button
 * @returns
 */
    function processButtonClick(){
        var sheetObject1=sheetObjects[0];
        /*******************************************************/
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
/**
 * init Sheet
 * @param sheetObj
 * @param sheetNo
 * @returns
 */
    function initSheet(sheetObj,sheetNo) {
        var cnt=0;
		var sheetID=sheetObj.id;
        switch(sheetID) {
            case "sheet1":
            	with(sheetObj){
                var HeadTitle="|Del|Msg Cd|Msg Type|Msg level|Message|Description" ;
                var headCount=ComCountHeadTitle(HeadTitle);
                /** SearchMode :
                 *  2 : LazyMode Get All Data and load it on screen based on Page property and scroll
                 *  0 : Get All Data and load it on screen
                 *  1 : Get All Data and load it on screen based on Page property
                 */
                //MergeSheet : 5 Merge header only,1 Merge All, 2 Merge Data, 7 Merge Data and Header
                //FrozenCol: Froze the Column in Sheet, it can't affect by horizontal scroll
                //Page: The Rows defined in 1 Page (Default:20)
                //DataRowMerge: Use with MergeSheet if the data of the row 1 and row 2 is duplicate data -> Merged
                SetConfig( { SearchMode:2, MergeSheet:5 } );
                //HeaderCheck: Use for tick all in header
                //Sort: Allow Sort in Header
                //ColMove: Allow Move the Column in sheet
                //ColResize: Allow Resize Column in Sheet
                var info    = { Sort:1, ColMove:1, HeaderCheck:0,ColResize:1 };
                var headers = [ { Text:HeadTitle, Align:"Center"} ];
                InitHeaders(headers, info);

                var cols = 
                	[ 	 {Type:"Status",    Hidden:1, Width:30,   Align:"Center",  ColMerge:0,   SaveName:"ibflag" },
	                     {Type:"DelCheck",  Hidden:0, Width:45,   Align:"Center",  ColMerge:1,   SaveName:"DEL",         KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	                     {Type:"Text",      Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_msg_cd",  KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1,EditLen:8 },
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

/**
 * Do Action IBSHEET 
 * @param sheetObj
 * @param formObj
 * @param sAction
 * @returns
 */
    function doActionIBSheet(sheetObj,formObj,sAction) {
        switch(sAction) {
		case IBSEARCH:      //Search
			ComOpenWait(true);
			formObj.f_cmd.value=SEARCH;
			//ObjId.DoSearch(PageUrl, [Param], [Opt])
			/**
			 * Param: FormQueryString(formObj) handle form tag from jsp
			 * Opt: 
			 *   Sync  : Sync search or not [Default:0]
			 *   Append: Append search result or not, [Default=0]
			 */
			sheetObj.DoSearch("CLV_TRAINING_001GS.do", FormQueryString(formObj) );
			break;
		case IBSAVE:        //Save
        	formObj.f_cmd.value=MULTI;
    		//ObjId.DoSave(PageUrl, [Param], [Col] , [Quest], [UrlEncode], [Mode], [Delim])
    		/**
    		 *  [Param]:	 Parameter for saving, [Default=""]
    		 *  [Col] :		 Column to save, or SaveName [Default=Status column (-1)]
    		 *  [Quest]:  	 display confirmation message before saving, [Default=1]
    		 *  [UrlEncode]: encode data on IBSheet, [Default=1]
    		 *  [Mode] : 	 How to combine strings for Query String, [Mode=1, Mode=2 (Default=1)]
    		 *  [Delim]: 	 Set the seperator for Mode 2, [Default "|"]
    		 */
            sheetObj.DoSave("CLV_TRAINING_001GS.do", FormQueryString(formObj));
			break;
		case IBINSERT:      // Insert
			row=sheetObj.DataInsert(-1);
			break;
		case IBDOWNEXCEL:	//DOWNLOAD excel
			if(sheetObj.RowCount() < 1){
				ComShowCodeMessage('PRC00001');
			}else{
				//makeHiddenSkipCol : Use to pass the DelCheck,Checkbox,...
				//SheetDesign:1 Allow to apply IBSheet design
				//Merge: 1 use to download true format merge column exactly like Sheet on UI. It will affected performance
				sheetObj.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj), SheetDesign:1, Merge:1});
			}
			break;
    }
    }
    /**
     * Sheet 1 when after search
     * 
     * @param sheetObj
     * @param Code
     * @param Msg
     * @param StCode
     * @param StMsg
     * @returns
     */
    function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
     	ComOpenWait(false);
     }

    /**
	 * Check Validate in Client Side Sheet 1
	 * 
	 * @param sheetObj
	 * @param Row
	 * @param Col
	 */
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
    /**
     * Search when Save end
     * 
     * @param sheetObj
     * @returns
     */
    function sheet1_OnSaveEnd(sheetObj){
    	doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
    }