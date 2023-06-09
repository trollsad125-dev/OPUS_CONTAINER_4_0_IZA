/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLV_PRACTICE_004.js
*@FileTitle : FW_Practice_004
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.08
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.08 
* 1.0 Creation
=========================================================*/

msgs['PRC00001']="'To date' must be later than 'From date'.";
msgs['PRC00002']="{?msg1} code is invalid !";
msgs['PRC00003']="There is any error in script.  Please check it again.";

    
// common global variable
var sheetObjects = new Array();
var sheetCnt = 0;
var comboObjects = new Array();
var comboCnt = 0;

//Event handler processing by button click event */
document.onclick = processButtonClick;
// Event handler processing by button name */
function processButtonClick() {
	/** *** setting sheet object **** */
	var sheetObject1 = sheetObjects[0];
	/** **************************************************** */
	var formObj = document.form;
	try {
		var srcName = ComGetEvent("name");
		if (srcName == null) {
			return;
		}

		switch (srcName) {
			case "btn_Retrieve":
				doActionIBSheet(sheetObject1, formObj, IBSEARCH);
				break;
			case "btn_New":
				resetForm(formObj);
				break;
			case "btn_Save":
				doActionIBSheet(sheetObject1, formObj, IBSAVE);
				break;
			case "btn_DownExcel":
				if (sheetObject1.RowCount() < 1) {// no data
					ComShowCodeMessage("COM132501");
				} else {
					sheetObject1.Down2Excel({ HiddenColumn:1,Merge:1});
				}
				break;
			case "btns_calendar1":
			case "btns_calendar2":
                var cal=new ComCalendar();
                if(srcName == "btns_calendar1"){
                    cal.select(formObj.s_cre_dt_fm, 'yyyy-MM-dd');
                }else{
                    cal.select(formObj.s_cre_dt_to, 'yyyy-MM-dd');
                }
                break;
			case "btn_RowDelete":
				doActionIBSheet(sheetObject1, formObj, IBDELETE);
				break;
			case "btn_RowAdd":
				doActionIBSheet(sheetObject1, formObj, IBINSERT);
				break;
			default :
				break;
		} // end switch
	} catch (e) {
		if (e == "[object Error]") {
			ComShowCodeMessage('PRC00003');
		} else {
			ComShowMessage(e.message);
		}
	}
}
/**
 * reset form when click new button
 * 
 * @param formObj
 */
function resetForm(formObj){
	formObj.reset();
	sheetObjects[0].RemoveAll();
	s_jo_crr_cd.SetSelectIndex(0);
}
/**
 * registering IBSheet Object as list adding process for list in case of needing
 * batch processing with other items defining list on the top of source
 * 
 * @param sheet_obj
 */
function setSheetObject(sheet_obj) {
	sheetObjects[sheetCnt++] = sheet_obj;
}
/**
 * registering IBCombo Object as list param : combo_obj adding process for list
 * in case of needing batch processing with other items defining list on the top
 * of source
 * 
 * @param combo_obj
 */
function setComboObject(combo_obj) {
	comboObjects[comboCnt++] = combo_obj;
}
/**
 * Load after everything done using onLoad
 * 
 */
function loadPage() {
	//generate Grid Layout
	for (i = 0; i < sheetObjects.length; i++) {
		ComConfigSheet(sheetObjects[i]);
		initSheet(sheetObjects[i], i + 1);
		ComEndConfigSheet(sheetObjects[i]);
	}
	//generate dopdownlist data
	for ( var k = 0; k < comboObjects.length; k++) {
		initCombo(comboObjects[k], k + 1);
	}
	
	//auto search data after loading finish.
	doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
}

/**
 * setting Combo basic info param : comboObj, comboNo initializing sheet
 * 
 * @param comboObj
 * @param comboNo
 */
function initCombo(comboObj, comboNo) {
	var formObj = document.form
	switch (comboNo) {
	case 1:
		with (comboObj) {
			SetMultiSelect(1);
	        SetDropHeight(200);
	        ValidChar(2,1);
		}
		var comboItems = crrCombo.split("|");
		addComboItem(comboObj, comboItems);
		comboObj.SetSelectIndex(0);
		break;
	}
}

/**
 * ComboBox s_jo_crr_cd When Check Click
 * 
 * @param comboObj
 * @param index
 * @param code
 */
function s_jo_crr_cd_OnCheckClick(comboObj, index, code) {
    if(index==0) {
        var bChk=comboObj.GetItemCheck(index);
       // If we checked in ALL, Set everything to 0 (uncheck)
        if(bChk){
            for(var i=1 ; i < comboObj.GetItemCount() ; i++) {
                comboObj.SetItemCheck(i,0);
            }
        }
    } else {
    	// If we checked in anything, Set ALL to 0 (uncheck)
        var bChk=comboObj.GetItemCheck(index);
        if (bChk) {
            comboObj.SetItemCheck(0,0);
        }
    }
    var checkCnt=0;
    //Count every check
    var count = parseInt(comboObj.GetItemCount());
    for(var i = 1 ; i <  count; i++) {
        if(comboObj.GetItemCheck(i)) {
            checkCnt++;
        }
    }
    //If no check -> Set to check ALL
    if(checkCnt == 0) {
        comboObj.SetItemCheck(0,true, null, null, false);
    }
}

/**
 * setting sheet initial values and header param : sheetObj, sheetNo adding case
 * as numbers of counting sheets
 * 
 * @param sheetObj
 * @param sheetNo
 */
function initSheet(sheetObj, sheetNo) {
	var cnt = 0;
	switch (sheetNo) {
	case 1: // sheet1 init
		with (sheetObj) {

			var HeadTitle = "STS|Chk|Carrier|Rev. Lane|Vendor Code|Customer Code|Customer Code|Trade|Delete Flag|Create Date|Create User ID|Update Date|Update User ID";
			var headCount = ComCountHeadTitle(HeadTitle);
	        /** SearchMode :
	         *  2 : LazyMode Get All Data and load it on screen based on Page property and scroll
	         *  0 : Get All Data and load it on screen
	         *  1 : Get All Data and load it on screen based on Page property
	         */
	        //MergeSheet : 5 Merge header only,1 Merge All, 2 Merge Data, 7 Merge Data and Header
	        //FrozenCol: Froze the Column in Sheet, it can't affect by horizontal scroll
	        //Page: The Rows defined in 1 Page (Default:20)
	        //DataRowMerge: Use with MergeSheet if the data of the row 1 and row 2 is duplicate data -> Merged
			SetConfig({SearchMode : 2, MergeSheet : 5});
            //HeaderCheck: Use for tick all in header
            //Sort: Allow Sort in Header
            //ColMove: Allow Move the Column in sheet
            //ColResize: Allow Resize Column in Sheet
			var info = {Sort : 1, ColMove : 1, HeaderCheck:0, ColResize : 1};
			var headers = [ { Text : HeadTitle, Align : "Center" }];
			InitHeaders(headers, info);
            /**CalcLogic: Use for calculate based on other column value
             * Ex: If you want to reference to other column 5 minus 2 and * column 3, calcLogic be like
             * |5| - 2 * |3|
             * Beside we can use SaveName
             */
			var cols = [ 
	             { Type : "Status",   Hidden : 1, Width : 50,  Align : "Center", ColMerge : 0, SaveName : "ibflag" }, 
	             { Type : "CheckBox", Hidden : 0, Width : 50,  Align : "Center", ColMerge : 0, SaveName : "del_chk" }, 
	             { Type : "Combo", 	  Hidden : 0, Width : 70,  Align : "Center", ColMerge : 0, SaveName : "jo_crr_cd",   KeyField : 1, Format : "", UpdateEdit : 0, InsertEdit : 1, EditLen: 3 }, 
	             { Type : "Combo",    Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "rlane_cd",    KeyField : 1, Format : "", UpdateEdit : 0, InsertEdit : 1, EditLen: 5  }, 
	             { Type : "Text",     Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "vndr_seq",    KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1 ,EditLen: 6 }, 
	             { Type : "Text",     Hidden : 0, Width : 50,  Align : "Center", ColMerge : 0, SaveName : "cust_cnt_cd", KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1 ,EditLen: 2 }, 
	             { Type : "Text", 	  Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "cust_seq",    KeyField : 1, Format : "", UpdateEdit : 1, InsertEdit : 1, EditLen: 6 }, 
	             { Type : "Text",     Hidden : 0, Width : 70,  Align : "Center", ColMerge : 0, SaveName : "trd_cd",      KeyField : 0, Format : "", UpdateEdit : 1, InsertEdit : 1, EditLen: 3 }, 
	             { Type : "Combo",    Hidden : 0, Width : 70,  Align : "Center", ColMerge : 0, SaveName : "delt_flg",    KeyField : 0, Format : "", UpdateEdit : 1, InsertEdit : 1}, 
	             { Type : "Text",     Hidden : 0, Width : 150, Align : "Center", ColMerge : 0, SaveName : "cre_dt",      KeyField : 0, Format : "", UpdateEdit : 0, InsertEdit : 0 }, 
	             { Type : "Text",     Hidden : 0, Width : 180, Align : "Left", ColMerge : 0, SaveName : "cre_usr_id",  KeyField : 0, Format : "", UpdateEdit : 0, InsertEdit : 0 }, 
	             { Type : "Text",     Hidden : 0, Width : 150, Align : "Center", ColMerge : 0, SaveName : "upd_dt",      KeyField : 0, Format : "", UpdateEdit : 0,InsertEdit : 0 }, 
	             { Type : "Text",     Hidden : 0, Width : 180, Align : "Left", ColMerge : 0, SaveName : "upd_usr_id",  KeyField : 0, Format : "", UpdateEdit : 0, InsertEdit : 0}
	             ];

			InitColumns(cols);
			SetEditable(1);
			SetColProperty("jo_crr_cd", { ComboText : crrComboIns, ComboCode : crrComboIns });
			SetColProperty("vndr_seq", { AcceptKeys : "N"});
			SetColProperty("cust_cnt_cd", { AcceptKeys : "E|N", InputCaseSensitive : 1});
			SetColProperty("cust_seq", { AcceptKeys : "N"});
			SetColProperty("trd_cd", { AcceptKeys : "E|N", InputCaseSensitive : 1 });
			SetColProperty("rlane_cd", { ComboText : rlanCombo, ComboCode : rlanCombo });
			SetColProperty("delt_flg", { ComboText : "N|Y", ComboCode : "N|Y" });
			SetWaitImageVisible(0);
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
    ComResizeSheet(sheetObjects[0]);
}

/**
 * To Do Action in Sheet
 * 
 * @param sheetObj
 * @param formObj
 * @param sAction
 * 
 */
function doActionIBSheet(sheetObj, formObj, sAction) {
	sheetObj.ShowDebugMsg(false);
	if (!validateForm(sheetObj, formObj, sAction)) {
		return false;
	}
	switch (sAction) {
	case IBSEARCH: // retrieve
		formObj.f_cmd.value = SEARCH;
		ComOpenWait(true);
		//ObjId.DoSearch(PageUrl, [Param], [Opt])
		/**
		 * Param: FormQueryString(formObj) handle form tag from jsp
		 * Opt: 
		 *   Sync  : Sync search or not [Default:0]
		 *   Append: Append search result or not, [Default=0]
		 */
		sheetObj.DoSearch("CLV_PRACTICE_004GS.do", FormQueryString(formObj),{Sync:1} );
		break;
	case IBSAVE: // retrieve
		formObj.f_cmd.value = MULTI;
		//ObjId.DoSave(PageUrl, [Param], [Col] , [Quest], [UrlEncode], [Mode], [Delim])
		/**
		 *  [Param]:	 Parameter for saving, [Default=""]
		 *  [Col] :		 Column to save, or SaveName [Default=Status column (-1)]
		 *  [Quest]:  	 display confirmation message before saving, [Default=1]
		 *  [UrlEncode]: encode data on IBSheet, [Default=1]
		 *  [Mode] : 	 How to combine strings for Query String, [Mode=1, Mode=2 (Default=1)]
		 *  [Delim]: 	 Set the seperator for Mode 2, [Default "|"]
		 */
		sheetObj.DoSave("CLV_PRACTICE_004GS.do", FormQueryString(formObj));
		break;
	case IBINSERT: //Row Add button event
		sheetObj.DataInsert(-1);
		break;
	case IBDELETE: //Row Delete button event
		for( var i = sheetObj.LastRow(); i >= sheetObj.HeaderRows(); i-- ) {
			if(sheetObj.GetCellValue(i, "del_chk") == 1){
				sheetObj.SetRowHidden(i, 1);
				sheetObj.SetRowStatus(i,"D");
			}
		}
		break;
	}
}
/**
 * Sheet 1 When Search End will go here
 * 
 */
function sheet1_OnSearchEnd() { 
 	ComOpenWait(false);
}
/**
 * Sheet 1 When Before Save 
 * 
 */
function sheet1_OnBeforeSave() {
	ComOpenWait(true);
}

/**
 * Sheet 1 When After Save 
 * 
 */
function sheet1_OnSaveEnd() { 
	doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
}

/**
 * handling process for input validation
 * @param sheetObj
 * @param formObj
 * @param sAction
 * @returns {Boolean}
 */
function validateForm(sheetObj, formObj, sAction) {
	sheetObj.ShowDebugMsg(false);
	switch (sAction) {
	case IBSEARCH: // retrieve
		var creDtFm = form.s_cre_dt_fm;
        var creDtTo = form.s_cre_dt_to;
        if(creDtFm.value != "" && creDtTo.value != "" && creDtFm.value > creDtTo.value) {
            ComShowCodeMessage("PRC00001");
            ComSetFocus(creDtFm);
            return false;
        }
		break;
	}
	return true;
}

/**
 * handling when sheet1 on change
 * 
 * @param sheetObj
 * @param Row
 * @param Col
 * @param Value
 * @param OldValue
 * @param RaiseFlag
 */
function sheet1_OnChange(sheetObj, Row, Col, Value, OldValue, RaiseFlag){
	var formObj=document.form ;
	var colName=sheetObj.ColSaveName(Col);
	
	if(Value == ""){
		return;
	}
	
	if(colName == "jo_crr_cd"){//check invalid with mdm carrier
		formObj.f_cmd.value		= COMMAND02;
		var sParam				= FormQueryString(formObj) + "&jo_crr_cd=" + Value;
		var sXml 				= sheetObj.GetSearchData("CLV_PRACTICE_004GS.do", sParam, {sync:1});	
		var flag				= ComGetEtcData(sXml, "ISEXIST");
		if(flag == 'N'){
			ComShowCodeMessage("PRC00002",["Carrier"]);
			sheetObj.SetCellValue(Row, Col,OldValue,0);
			sheetObj.SelectCell(Row, Col);
		}
	}else if(colName == "vndr_seq"){//check invalid vendor code
		formObj.f_cmd.value		= COMMAND03;
		var sParam				= FormQueryString(formObj) + "&vndr_seq=" + Value;
		var sXml 				= sheetObj.GetSearchData("CLV_PRACTICE_004GS.do", sParam, {sync:1});	
		var flag				= ComGetEtcData(sXml, "ISEXIST");
		if(flag == 'N'){
			ComShowCodeMessage("PRC00002",["Vendor"]);
			sheetObj.SetCellValue(Row, Col,OldValue,0);
			sheetObj.SelectCell(Row, Col);
		}
	}else if(colName == "cust_seq" || colName == "cust_cnt_cd"){//check invalid customer information
		if(sheetObj.GetCellValue(Row,"cust_seq") != "" && sheetObj.GetCellValue(Row,"cust_cnt_cd") != ""){
			formObj.f_cmd.value		= COMMAND04;
			var sParam				= FormQueryString(formObj)+ "&cust_cnt_cd=" + sheetObj.GetCellValue(Row,"cust_cnt_cd") + "&cust_seq=" + sheetObj.GetCellValue(Row,"cust_seq") ;
			var sXml 				= sheetObj.GetSearchData("CLV_PRACTICE_004GS.do", sParam, {sync:1});	
			var flag				= ComGetEtcData(sXml, "ISEXIST");
			if(flag == 'N'){
				ComShowCodeMessage("PRC00002",["Customer"]);
				sheetObj.SetCellValue(Row, Col,OldValue,0);
				sheetObj.SelectCell(Row, Col);
			}
		}
	}else if(colName == "trd_cd"){//check invalid trade code
		formObj.f_cmd.value		= COMMAND05;
		var sParam				= FormQueryString(formObj) + "&trd_cd=" + Value;
		var sXml 				= sheetObj.GetSearchData("CLV_PRACTICE_004GS.do", sParam, {sync:1});	
		var flag				= ComGetEtcData(sXml, "ISEXIST");
		if(flag == 'N'){
			ComShowCodeMessage("PRC00002",["Trade"]);
			sheetObj.SetCellValue(Row, Col,OldValue,0);
			sheetObj.SelectCell(Row, Col);
		}
	}
	
}
/**
* adding data to combo field
* 
* @param comboObj
* @param comboItems
* */
function addComboItem(comboObj, comboItems) {
	for (var i = 0; i < comboItems.length; i++) {
		var comboItem = comboItems[i].split(",");
			// Insert Combo Item into ComboOject  s_jo_crr_cd
			comboObj.InsertItem(i, comboItem[0], comboItem[0]);
	}
}