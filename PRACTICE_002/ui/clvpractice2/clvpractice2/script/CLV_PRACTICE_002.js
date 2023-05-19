/*=========================================================
*Copyright(c) 2023 CyberLogitec. All Rights Reserved.
*@FileName   : CLV_PRACTICE_002.js
*@FileTitle  : CLV Practice 2
*@author     : Nhat Nguyen
*@version    : 1.0
*@since      : 2023/04/05
=========================================================*/
var sheetObjects=new Array();
var sheetCnt=0;
document.onclick=processButtonClick;

    function processButtonClick(){

         var formObject=document.form1;
    	try {
    		var srcName=ComGetEvent("name");
            switch(srcName) {
        	    case "btn_Retrieve":
    	            doActionIBSheet(sheetObjects[0],formObject,IBSEARCH);
        	        break;
                case "btn_Save":
                  if(confirm("Do you save selected codes?")){
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
     * init Sheet
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
                var prefix="sheet1_";

                SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:0 } );

                var info    = { Sort:1, ColMove:1, HeaderCheck:0, ColResize:1 };
                var headers = [ { Text:HeadTitle, Align:"Center"} ];
                InitHeaders(headers, info);

                var cols = [ {Type:"Status",    Hidden:0, Width:10,   Align:"Center",  ColMerge:0,   SaveName:prefix+"ibflag",          KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Combo",     Hidden:0, Width:70,   Align:"Center",  ColMerge:0,   SaveName:prefix+"ownr_sub_sys_cd", KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Text",      Hidden:0,  Width:60,   Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_id",      KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
	             {Type:"Text",      Hidden:0,  Width:200,  Align:"Left",    ColMerge:0,   SaveName:prefix+"intg_cd_nm",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Text",      Hidden:0,  Width:50,   Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_len",     KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Combo",     Hidden:0, Width:100,   Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_tp_cd",   KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Text",      Hidden:0,  Width:150,  Align:"Left",    ColMerge:0,   SaveName:prefix+"mng_tbl_nm",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	             {Type:"Text",      Hidden:0,  Width:350,  Align:"Left",    ColMerge:0,   SaveName:prefix+"intg_cd_desc",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Combo",     Hidden:0, Width:40,   Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_use_flg", KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	             {Type:"Text",      Hidden:0,  Width:80,   Align:"Center",  ColMerge:0,   SaveName:prefix+"cre_usr_id",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	             {Type:"Date",      Hidden:0,  Width:80,   Align:"Center",  ColMerge:0,   SaveName:prefix+"cre_dt",          KeyField:0,   CalcLogic:"",   Format:"Ymd",         PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	             {Type:"Text",      Hidden:0,  Width:80,   Align:"Center",  ColMerge:0,   SaveName:prefix+"upd_usr_id",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	             {Type:"Date",      Hidden:0,  Width:80,   Align:"Center",  ColMerge:0,   SaveName:prefix+"upd_dt",          KeyField:0,   CalcLogic:"",   Format:"Ymd",         PointCount:0,   UpdateEdit:0,   InsertEdit:0 } ];
                         
                InitColumns(cols);
            	var tmp=subSysCd.substring(1,subSysCd.length-1).split(", ");
            	
            	SetColProperty(prefix+"ownr_sub_sys_cd", {ComboText:tmp.join("|"), ComboCode:tmp.join("|")} );
            	SetColProperty(prefix+"intg_cd_tp_cd", {ComboText:"General Code|Table Code", ComboCode:"G|T"} );
            	SetColProperty(prefix+"intg_cd_use_flg", {ComboText:"Y|N", ComboCode:"Y|N"} );
            	SetEditable(1);
	            SetSheetHeight(240);
            	
        	}
        	break;
        	
        case 2:      //IBSheet2 init
        	with(sheetObj){

                var HeadTitle="|Cd ID|Cd Val|Display Name|Description Remark|Order" ;
                var prefix="sheet2_";

                SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:0 } );

                var info    = { Sort:1, ColMove:1, HeaderCheck:0, ColResize:1 };
                var headers = [ { Text:HeadTitle, Align:"Center"} ];
                InitHeaders(headers, info);

                var cols = [ {Type:"Status",    Hidden:1, Width:10,   Align:"Center",  ColMerge:0,   SaveName:prefix+"ibflag",              KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
			     {Type:"Text",      Hidden:0, Width:10,   Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_id",          KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
			     {Type:"Text",      Hidden:0,  Width:60,   Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_val_ctnt",    KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
			     {Type:"Text",      Hidden:0,  Width:200,  Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_val_dp_desc", KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
			     {Type:"Text",      Hidden:0,  Width:600,  Align:"Left",    ColMerge:0,   SaveName:prefix+"intg_cd_val_desc",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
			     {Type:"Text",      Hidden:0,  Width:50,   Align:"Center",  ColMerge:0,   SaveName:prefix+"intg_cd_val_dp_seq",  KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 } ];
                 
                InitColumns(cols);
                SetEditable(1);
	            resizeSheet();
    		}
            break;
        }
    }
    
    /**
     * Resize Sheet
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
        sheetObj.ShowDebugMsg(false);
        switch(sAction) {
           case IBSEARCH:      //Search
                    if ( sheetObj.id == "sheet1" ) {
    					formObj.f_cmd.value=SEARCH01;
    					var arr1=new Array("sheet1_", "");
    		        	var sParam1=FormQueryString(formObj)+ "&" + ComGetPrefixParam(arr1);
     					var sXml1=sheetObj.GetSearchData("CLV_PRACTICE_002GS.do", sParam1);
    					if(sXml1.length>0){
    						sheetObj.LoadSearchData(sXml1,{Sync:1} );
    					}
    					sheetObjects[1].RemoveAll();
    					formObj.codeid.value='';
                    } else if ( sheetObj.id == "sheet2" ) {
    					formObj.f_cmd.value=SEARCH02;
    					var arr2=new Array("sheet2_", "");
    		        	var sParam2=FormQueryString(formObj)+ "&" + ComGetPrefixParam(arr2);
     					var sXml2=sheetObj.GetSearchData("CLV_PRACTICE_002GS.do", sParam2);
    					if(sXml2.length>0){
    						sheetObj.LoadSearchData(sXml2,{Sync:1} );
    					}
                    }
                break;
            case IBSAVE:       //SAVE
                formObj.f_cmd.value=MULTI;
                sheetObj.DoSave("CLV_PRACTICE_002GS.do", FormQueryString(formObj), -1, false);
                break;
	 		case IBINSERT: // Row Add	
	 			with (sheetObj) {
		 			sheetObj.DataInsert(-1);
		 			if ( sheetObj.id == "sheet1" ) {
		 				sheetObjects[1].RemoveAll();
		 				
		 			}
		 			if ( sheetObj.id == "sheet2" ) {
		 				if( sheetObj.SearchRows()== 0 ){
		 					SetCellValue(LastRow(), "sheet2_intg_cd_id",sheetObjects[0].GetCellValue(sheetObjects[0].GetSelectRow(),"sheet1_intg_cd_id"));
		 				} else {
		 					SetCellValue(LastRow(), "sheet2_intg_cd_id",sheetObj.GetCellValue(1, "sheet2_intg_cd_id"));
		 				}
		 			}
		 			return true;
	 			}
 		 		break;	
            case MODIFY01: // Row Delete
            	var sheetprefix=sheetObj.id+"_";
            	var j=sheetObj.GetSelectRow();
            	sheetObj.SetCellValue(j, sheetprefix+"ibflag","D");
            	sheetObj.SetRowHidden(j,1);
            	if( sheetObj.id == "sheet1" ){
            		var codeid=sheetObj.GetCellValue(j, "sheet1_intg_cd_id");
            		if( sheetObjects[1].RowCount()> 0 && codeid==document.form1.codeid.value){
            		      for(i=sheetObjects[1].LastRow();i>0;i--){
            		    	  sheetObjects[1].SetCellValue(i, "sheet2_ibflag","D");
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
    	ComSetObjValue(document.form1.codeid, sheetObj.GetCellValue(Row, "sheet1_intg_cd_id"));
    	doActionIBSheet(sheetObjects[1],document.form1,IBSEARCH);
    }
