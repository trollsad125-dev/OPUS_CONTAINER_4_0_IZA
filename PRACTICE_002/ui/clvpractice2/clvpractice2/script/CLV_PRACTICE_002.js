/*=========================================================
*Copyright(c) 2023 CyberLogitec. All Rights Reserved.
*@FileName   : CLV_PRACTICE_002.js
*@FileTitle  : CLV Practice 2
*@author     : Nhat Nguyen
*@version    : 1.0
*@since      : 2023/04/05
=========================================================*/
msgs['PRC00002']="{?msg1} code is invalid !";
msgs['PRC00005']="Do you want to save?";

//Common Global variable
var sheetObjects=new Array();
var sheetCnt=0;
document.onclick=processButtonClick;
var chkSuccessMst = false;

   /**
    * When user clicked on Client Side
    * 
    */
   function processButtonClick(){

         var formObject=document.form;
    	try {
    		var srcName=ComGetEvent("name");
            switch(srcName) {
        	    case "btn_Retrieve":
    	            doActionIBSheet(sheetObjects[0],formObject,IBSEARCH);
        	        break;
                case "btn_Save":
                	if(ComShowCodeConfirm("PRC00005")){
                      //Second, Loop all record in sheet and use sheetObject[0].GetRowStatus(i) == "I" || "D" => Perform SAVE
                  	  if((sheetObjects[0].RowCount("I")+sheetObjects[0].RowCount("U")+sheetObjects[0].RowCount("D")) >0 ){
                  		  doActionIBSheet(sheetObjects[0],formObject,IBSAVE);
                  	  } 
                  	  if((sheetObjects[1].RowCount("I")+sheetObjects[1].RowCount("U")+sheetObjects[1].RowCount("D")) >0 ) {
                  		  doActionIBSheet(sheetObjects[1],formObject,IBSAVE);
                  	  }
                	}
        	        break;
        			/*****************grid button ************************/				
				case "btn_rowadd_mst": //add row  
	                doActionIBSheet(sheetObjects[0],	formObject,	IBINSERT);
					break;
				case "btn_rowdelete_mst": //delete row
					doActionIBSheet(sheetObjects[0],	formObject,	MODIFY01);					
					break;        	        
				case "btn_rowadd_dtl": //add row  
	                doActionIBSheet(sheetObjects[1],	formObject,	IBINSERT);
					break;
				case "btn_rowdelete_dtl": //delete row
					doActionIBSheet(sheetObjects[1],	formObject,	MODIFY01);					
					break;  
            } // end switch
    	}catch(e) {
    		if( e == "[object Error]") {
    			ComShowMessage(ComGetMsg('COM12111'));
    		} else {
    			ComShowMessage(e.message);
    		}
    	}
    }
    /**
     * Set Sheet Object into Array
     * @param sheet_obj
     */
    function setSheetObject(sheet_obj){
    	 sheetObjects[sheetCnt++]=sheet_obj;
    }
    /**
     * JSP will call loadPage
     * 
     */
    function loadPage() {
        for(i=0;i<sheetObjects.length;i++){
            ComConfigSheet(sheetObjects[i]);
            initSheet(sheetObjects[i],i+1);
            ComEndConfigSheet(sheetObjects[i]);
        }
        //Insert into Sub System Object
		var tmp=subSysCd.substring(1,subSysCd.length-1).split(", ");
		with (subsystem) {
			SetMultiSelect(0);
			for ( var i=0; i<tmp.length; i++) {
				InsertItem(i, tmp[i], tmp[i]);
			}
		}

    }

    /**
     * Init Sheet
     * 
     * @param sheetObj
     * @param sheetNo
     */
    function initSheet(sheetObj,sheetNo) {
        var cnt=0;
        switch(sheetNo) {
        case 1:      //IBSheet1 init
        	with(sheetObj){
               
        		var HeadTitle="|SubSystem|Cd ID|Cd Name|Length|Cd Type|Table Name|Description Remark|Flag|Create User|Create Date|Update User|Update Date" ;
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
                var info    = { Sort:1, ColMove:1, ColResize:1 };
                var headers = [ { Text:HeadTitle, Align:"Center"} ];
                InitHeaders(headers, info);
                /**CalcLogic: Use for calculate based on other column value
                 * Ex: If you want to reference to other column 5 minus 2 and * column 3, calcLogic be like
                 * |5| - 2 * |3|
                 * Beside we can use SaveName
                 */
                var cols = 
               [ {Type:"Status",    Hidden:1,  Width:10,   Align:"Center",  ColMerge:0,   SaveName:"ibflag",          KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Combo",     Hidden:0,  Width:70,   Align:"Center",  ColMerge:0,   SaveName:"ownr_sub_sys_cd", KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Text",      Hidden:0,  Width:60,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_id",      KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1, EditLen:20 },
	             {Type:"Text",      Hidden:0,  Width:200,  Align:"Left",    ColMerge:0,   SaveName:"intg_cd_nm",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Text",      Hidden:0,  Width:50,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_len",     KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Combo",     Hidden:0,  Width:100,  Align:"Center",  ColMerge:0,   SaveName:"intg_cd_tp_cd",   KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Text",      Hidden:0,  Width:150,  Align:"Left",    ColMerge:0,   SaveName:"mng_tbl_nm",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	             {Type:"Text",      Hidden:0,  Width:350,  Align:"Left",    ColMerge:0,   SaveName:"intg_cd_desc",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Combo",     Hidden:0,  Width:40,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_use_flg", KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Text",      Hidden:0,  Width:80,   Align:"Center",  ColMerge:0,   SaveName:"cre_usr_id",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	             {Type:"Date",      Hidden:0,  Width:80,   Align:"Center",  ColMerge:0,   SaveName:"cre_dt",          KeyField:0,   CalcLogic:"",   Format:"Ymd",         PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	             {Type:"Text",      Hidden:0,  Width:80,   Align:"Center",  ColMerge:0,   SaveName:"upd_usr_id",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	             {Type:"Date",      Hidden:0,  Width:80,   Align:"Center",  ColMerge:0,   SaveName:"upd_dt",          KeyField:0,   CalcLogic:"",   Format:"Ymd",         PointCount:0,   UpdateEdit:0,   InsertEdit:0 } ];
                         
                InitColumns(cols);
            	var tmp=subSysCd.substring(1,subSysCd.length-1).split(", ");
            	
            	SetColProperty("ownr_sub_sys_cd", {ComboText:tmp.join("|"), ComboCode:tmp.join("|")} );
            	SetColProperty("intg_cd_tp_cd", {ComboText:"General Code|Table Code", ComboCode:"G|T"} );
            	SetColProperty("intg_cd_use_flg", {ComboText:"Y|N", ComboCode:"Y|N"} );
            	SetEditable(1);
	            SetSheetHeight(240);
            	
        	}
        	break;
        	
        case 2:      //IBSheet2 init
        	with(sheetObj){

                var HeadTitle="|Cd ID|Cd Val|Display Name|Description Remark|Order" ;
                /** SearchMode :
                 *  2 : LazyMode Get All Data and load it on screen based on Page property and scroll
                 *  0 : Get All Data and load it on screen
                 *  1 : Get All Data and load it on screen based on Page property
                 */
                //MergeSheet : 5 Merge header only,1 Merge All, 2 Merge Data, 7 Merge Data and Header
                //FrozenCol: Froze the Column in Sheet, it can't affect by horizontal scroll
                //Page: The Rows defined in 1 Page (Default:20)
                //DataRowMerge: Use with MergeSheet if the data of the row 1 and row 2 is duplicate data -> Merged
                SetConfig( { SearchMode:2, MergeSheet:5} );
                //HeaderCheck: Use for tick all in header
                //Sort: Allow Sort in Header
                //ColMove: Allow Move the Column in sheet
                //ColResize: Allow Resize Column in Sheet
                var info    = { Sort:1, ColMove:1, HeaderCheck:0,ColResize:1 };
                var headers = [ { Text:HeadTitle, Align:"Center"} ];
                InitHeaders(headers, info);

                var cols = 
               [ {Type:"Status",    Hidden:1,  Width:10,   Align:"Center",  ColMerge:0,   SaveName:"ibflag",              KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
			     {Type:"Text",      Hidden:1,  Width:50,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_id",          KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
			     {Type:"Text",      Hidden:0,  Width:80,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_val_ctnt",    KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1, EditLen:20 },
			     {Type:"Text",      Hidden:0,  Width:200,  Align:"Center",  ColMerge:0,   SaveName:"intg_cd_val_dp_desc", KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
			     {Type:"Text",      Hidden:0,  Width:600,  Align:"Left",    ColMerge:0,   SaveName:"intg_cd_val_desc",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
			     {Type:"Text",      Hidden:0,  Width:50,   Align:"Center",  ColMerge:0,   SaveName:"intg_cd_val_dp_seq",  KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 } ];
                 
                InitColumns(cols);
                SetEditable(1);
	            resizeSheet();
    		}
            break;
        }
    }
    
    /**
     * Resize Sheet 2
     * 
     */
    function resizeSheet(){
        ComResizeSheet(sheetObjects[1]);
    }

    /**
     * Do Action IB Sheet
     * 
     * @param sheetObj
     * @param formObj
     * @param sAction
     */
    function doActionIBSheet(sheetObj,formObj,sAction) {
        switch(sAction) {
           case IBSEARCH:      //Search
                    if ( sheetObj.id == "sheet1" ) {
                    	ComOpenWait(true);
    					formObj.f_cmd.value=SEARCH01;
    		        	var sParam1=FormQueryString(formObj);
    		        	/** GetSearchData
    		        	 * ObjId.GetSearchData(PageUrl, [Param])
    		        	 * Param:  Search parameter Query String, [Default=""]
    		        	 */
     					var sXml1=sheetObj.GetSearchData("CLV_PRACTICE_002GS.do", sParam1);
    					if(sXml1.length>0){
    						/**LoadSearchData
    						 * ObjId.LoadSearchData(Content, [Opt])
    						 * Content: Search XML or Search JSON string
    						 * Opt.Append: Append search result or not, Default=0
    						 * Opt.Sync  : Sync search or not, Default= 0
    						 */
    						sheetObj.LoadSearchData(sXml1,{Sync:1} );
    					}
    					sheetObjects[1].RemoveAll();
    					formObj.codeid.value='';
    					ComOpenWait(false);
    					chkSuccessMst = false;
                    } else if ( sheetObj.id == "sheet2" ) {
                    	ComOpenWait(true);

    					formObj.f_cmd.value=SEARCH02;
    		        	var sParam2=FormQueryString(formObj);
     					var sXml2=sheetObj.GetSearchData("CLV_PRACTICE_002GS.do", sParam2);
    					if(sXml2.length>0){
    						sheetObj.LoadSearchData(sXml2,{Sync:1} );
    					}
    					ComOpenWait(false);
                    }
                break;
            case IBSAVE:       //SAVE
            	if(sheetObj.id == "sheet1"){
            		formObj.f_cmd.value=MULTI;
            	}else{
            		formObj.f_cmd.value = MULTI01;
            	}
                
                var param = FormQueryString(formObj);
                
        		//ObjId.DoSave(PageUrl, [Param], [Col] , [Quest], [UrlEncode], [Mode], [Delim])
        		/**
        		 *  [Param]:	 Parameter for saving, [Default=""]
        		 *  [Col] :		 Column to save, or SaveName [Default=Status column (-1)]
        		 *  [Quest]:  	 display confirmation message before saving, [Default=1]
        		 *  [UrlEncode]: encode data on IBSheet, [Default=1]
        		 *  [Mode] : 	 How to combine strings for Query String, [Mode=1, Mode=2 (Default=1)]
        		 *  [Delim]: 	 Set the seperator for Mode 2, [Default "|"]
        		 */
                sheetObj.DoSave("CLV_PRACTICE_002GS.do", param,-1,false);
                break;
	 		case IBINSERT: // Row Add	
	 			with (sheetObj) {

		 			if ( sheetObj.id == "sheet1" ) {
		 				sheetObj.DataInsert(-1);
		 				sheetObjects[1].RemoveAll();
		 			}
		 			if ( sheetObj.id == "sheet2" ) {
		 				if(sheetObjects[0].GetCellValue(sheetObjects[0].GetSelectRow(),"intg_cd_id") == ""){
		 					ComShowMessage("The Master Table Cd Id is Null, Please fill it!");
		 					return;
		 				}
		 				sheetObj.DataInsert(-1);
		 				if( sheetObj.SearchRows()== 0 ){
		 					SetCellValue(LastRow(), "intg_cd_id",sheetObjects[0].GetCellValue(sheetObjects[0].GetSelectRow(),"intg_cd_id"));
		 				} else {
		 					SetCellValue(LastRow(), "intg_cd_id",sheetObj.GetCellValue(1, "intg_cd_id"));
		 				}
		 			}
		 			return true;
	 			}
 		 		break;	
            case MODIFY01: // Row Delete
            	var j=sheetObj.GetSelectRow();
            	sheetObj.SetCellValue(j, "ibflag","D");
            	sheetObj.SetRowHidden(j,1);
            	if( sheetObj.id == "sheet1" ){
            		var codeid=sheetObj.GetCellValue(j, "intg_cd_id");
            		if( sheetObjects[1].RowCount()> 0 && codeid==document.form.codeid.value){
            		      for(i=sheetObjects[1].LastRow();i>0;i--){
            		    	  sheetObjects[1].SetCellValue(i, "ibflag","D");
            		    	  sheetObjects[1].SetRowHidden(i,1);
            		        }
            		}
            	}
		 	    break; 
        }
    }

    /**
     * When Double Click on Sheet 1
     * 
     * @param sheetObj
     * @param Row
     * @param Col
     */
    function sheet1_OnDblClick(sheetObj, Row, Col) {
    	if(sheetObj.GetCellValue(Row,"ibflag") !== "I"){
        	ComSetObjValue(document.form.codeid, sheetObj.GetCellValue(Row, "intg_cd_id"));
        	doActionIBSheet(sheetObjects[1],document.form,IBSEARCH);
    	}
    }
    /**
     * Check Validate in Client Side Sheet 1
     * 
     * @param sheetObj
     * @param Row
     * @param Col
     */
    function sheet1_OnChange(sheetObj,Row,Col,Value,OldValue){
     var formObj = document.form;
     var colName=sheetObj.ColSaveName(Col);
   	 if(colName == "intg_cd_id"){
			var code=sheetObj.GetCellValue(Row, Col);
   	    for(var int=1; int < sheetObj.RowCount(); int++) {
			var orlcode=sheetObj.GetCellValue(int, Col);
				if(code != '' && int != Row && code == orlcode){
				 chkSuccessMst=true;
	   			 sheetObj.SetCellValue(Row, Col,"");
   				 ComShowCodeMessage('COM131302',code);
   				 return;
   			 }
   		}
		if (chkSuccessMst == false) {
			formObj.f_cmd.value = COMMAND01;
			var sParam = FormQueryString(formObj) + "&intg_cd_id=" + Value;
			var sXml = sheetObj.GetSearchData("CLV_PRACTICE_002GS.do", sParam,{sync : 1});
			var flag = ComGetEtcData(sXml, "ISEXIST");
			if (flag == 'Y') {
				ComShowCodeMessage('COM131302', code);
				sheetObj.SetCellValue(Row, Col, OldValue, 0);
				sheetObj.SelectCell(Row, Col);
				chkSuccessMst=false;
				return;
			}
		}
	}
    }
    /**
	 * Check Validate in Client Side Sheet 2
	 * 
	 * @param sheetObj
	 * @param Row
	 * @param Col
	 */
    function sheet2_OnChange(sheetObj, Row, Col,Value,OldValue) {
	var formObj = document.form;
	var colName = sheetObj.ColSaveName(Col);
	var intgCdIdValue = sheetObj.GetCellValue(Row,"intg_cd_id");
	if (colName == "intg_cd_val_ctnt") {
		var code = sheetObj.GetCellValue(Row, Col);
		for (var int = 1; int < sheetObj.RowCount(); int++) {
			var orlcode = sheetObj.GetCellValue(int, Col);
			if (code != '' && int != Row && code == orlcode) {
				ComShowCodeMessage('COM131302', code);
				sheetObj.SetCellValue(Row, Col, "");
				return;
			  }
	     }
		}
    }
	/**
	 * Sheet 1 When After Save 
	 * 
	 */
	function sheet1_OnSaveEnd(sheetObj) { 
		doActionIBSheet(sheetObj, document.form, IBSEARCH);
	}
