/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLV_PRACTICE_003.js
*@FileTitle : CLV Practice 3
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.11
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.11 
* 1.0 Creation
=========================================================*/
    
// common global variable
var sheetObjects = new Array();
var sheetCnt = 0;
var comboObjects = new Array();
var comboCnt = 0;
var tabObjects=new Array();
var tabCnt=0 ;
var beforetab=1;
var summaryFlag = 0;
var detailFlag=0
var ROWMARK = "|";
var FIELDMARK=",";
msgs['PRC00002']="'To date' must be later than 'From date'.";
msgs['PRC00001']="There is any error in script.  Please check it again.";


//Event handler processing by button click event */
document.onclick = processButtonClick;
// Event handler processing by button name */
function processButtonClick() {
	/** *** setting sheet object **** */
	var sheetObject1 = getCurrentSheet();
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
		case "btn_DownExcel":
			if (sheetObject1.RowCount() < 1) {// no data
				ComShowCodeMessage("COM132501");
			} else {
				sheetObject1.Down2Excel({
					HiddenColumn : 1,
					Merge : 1
				});
			}
			break;
		case "btn_date_fr_down":
			UF_addMonth(formObj.date_fr, -1);
			sheetObject1.RemoveAll();
			break;
		case "btn_date_fr_up":
			if (!GetCheckConditionPeriod()) {
				ComShowCodeMessage("PRC00002");
				return;
			}
			UF_addMonth(formObj.date_fr, +1);
			sheetObject1.RemoveAll();
			break;
		case "btn_date_to_down":
			if (!GetCheckConditionPeriod()) {
				ComShowCodeMessage("PRC00002");
				return;
			}
			UF_addMonth(formObj.date_to, -1);
			sheetObject1.RemoveAll();
			break;
		case "btn_date_to_up":
			UF_addMonth(formObj.date_to, +1);
			sheetObject1.RemoveAll();
			break;
		case "btn_DownExcel2":
			doActionIBSheet(sheetObject1, formObj, IBDOWNEXCEL);
			sheetObject1.RemoveAll();
			break;
		default:
			break;
		} // end switch
	} catch (e) {
		if (e == "[object Error]") {
			ComShowCodeMessage('PRC00001');
		} else {
			ComShowMessage(e.message);
		}
	}
}
/**
 * rest form when click new button
 * @param formObj
 */
function resetForm(formObj){
	formObj.reset();
	getCurrentSheet().RemoveAll();
	jo_crr_cds.SetSelectIndex(0);
}
/**
 * registering IBSheet Object as list adding process for list in case of needing
 * batch processing with other items defining list on the top of source
 * @param sheet_obj
 */
function setSheetObject(sheet_obj) {
	console.log(sheet_obj.id);
	sheetObjects[sheetCnt++] = sheet_obj;
}

/**
 * Load after everything done using onLoad
 */
function getCurrentSheet(){
    var sheetObj=null;
    if(beforetab == 0){
        sheetObj=sheetObjects[0];
    }else{
        sheetObj=sheetObjects[1];
    }
    
    return sheetObj;
}

/**
 * registering IBCombo Object as list param : combo_obj adding process for list
 * in case of needing batch processing with other items defining list on the top
 * of source
 * @param combo_obj
 */
function setComboObject(combo_obj) {
    comboObjects[comboCnt++] = combo_obj;
}
/**
 * registering IBTab Object as list
 * adding process for list in case of needing batch processing with other items
 * defining list on the top of source
 * @param tab_obj
 */
function setTabObject(tab_obj){
    tabObjects[tabCnt++]=tab_obj;
}
/**
 * initializing Tab
 * setting Tab items
 * @param tabObj
 * @param tabNo
 */
function initTab(tabObj , tabNo) {
     switch(tabNo) {
         case 1:
            with (tabObj) {
                InsertItem( "Summary" , "");
                InsertItem( "Detail" , "");
            }
         break;
     }
}
/**
 * Event when clicking Tab
 * activating selected tab items
 * @param tabObj
 * @param nItem
 */
function tab1_OnChange(tabObj , nItem)
{
    var objs=document.all.item("tabLayer");
    objs[nItem].style.display="Inline";
    objs[beforetab].style.display="none";

    beforetab=nItem;
    
    if (beforetab == 0 && summaryFlag == 0) {
        ComFireEvent(ComGetObject("btn_Retrieve") ,"click");
    }else if(beforetab == 1 &&  detailFlag ==0 || getCurrentSheet().RowCount() <=0){
        ComFireEvent(ComGetObject("btn_Retrieve") ,"click");
    }
    
    resizeSheet();
}

/**
 * setting Combo basic info
 * 
 * @param comboObj
 * @param comboNo
 *            
 */
function initCombo(comboObj, comboNo) {
    var formObject = document.form;
    switch (comboObj.options.id) {
        case "jo_crr_cds":
            with (comboObj) {
                SetMultiSelect(true);
                SetMultiSeparator(",");  // add 
                SetUseAutoComplete(1);
                SetColAlign(0, "left");
                SetColWidth(0, "60");
                SetDropHeight(160);
                SetMaxLength(3);
            }
            if (ComTrim(gJoCrrCdComboItems) != ""){
                var comboItems=gJoCrrCdComboItems.split(ROWMARK);
                var comboItem="";
                jo_crr_cds.InsertItem(-1, "ALL|", "ALL");    //ALL
                for (var i=0 ; i < comboItems.length ; i++) {
                    comboItem=comboItems[i].split(FIELDMARK);
                    jo_crr_cds.InsertItem(-1, comboItem[0], comboItem[0]);
                }
            }else{
                jo_crr_cds.RemoveAll();
            }
            break;
        case "trd_cd":
            with (comboObj) {
                SetMultiSelect(0);
                SetUseAutoComplete(1);
                SetColAlign(0, "left");
                SetDropHeight(160);
                SetColWidth(0, "60");
                SetMaxLength(3);
                SetEnable(0);
            }
            comboObj.RemoveAll();
            break;

        case "rlane_cds": 
            with (comboObj) { 
                SetMultiSelect(0);
                SetUseAutoComplete(1);
                SetColAlign(0, "left");
                SetColWidth(0, "60");
                SetDropHeight(160);
                SetMaxLength(3);
                SetEnable(0);
            }
            comboObj.RemoveAll();

            break;
    }
}
/**
 * initializing sheet implementing onLoad event handler in body tag adding
 * first-served functions after loading screen.
 */
function loadPage() {
    for (i = 0; i < sheetObjects.length; i++) {
        ComConfigSheet(sheetObjects[i]);
        initSheet(sheetObjects[i], i + 1);
        ComEndConfigSheet(sheetObjects[i]);
    }
    for(k=0;k<tabObjects.length;k++){
        initTab(tabObjects[k],k+1);
        tabObjects[k].SetSelectedIndex(0);
    }
    
    // initializing IBMultiCombo
    for (var k = 0; k < comboObjects.length; k++) {
        initCombo(comboObjects[k], k + 1);
    }
    resizeSheet();
}

/**
 * setting sheet initial values and header param : sheetObj, sheetNo adding case
 * as numbers of counting sheets
 * @param sheetObj
 * @param sheetNo
 */
function initSheet(sheetObj, sheetNo) {
	var cnt = 0;
	initPeriod();
	switch (sheetObj.id) {
	 case "t1sheet1": // t1sheet1 init   
         with(sheetObj){
             var HeadTitle1="|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Customer/S.Provider|Customer/S.Provider";
             var HeadTitle2="|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Code|Name";
             var headCount=ComCountHeadTitle(HeadTitle1);
             SetConfig( { SearchMode:0, MergeSheet:1, Page:500, DataRowMerge:0 } );
             var info    = { Sort:0, ColMove:1, HeaderCheck:1, ColResize:1 };
             var headers = [ { Text:HeadTitle1, Align:"Center"} ,  { Text:HeadTitle2, Align:"Center"}];
			InitHeaders(headers, info);
			var prefix = "t1sheet1_";
			  var cols = [ 
	                       {Type:"Status",    Hidden:1, Width:0,    Align:"Center",  ColMerge:1,   SaveName: prefix + "ibflag" },
	                       {Type:"Text",      Hidden:0, Width:50,   Align:"Center",  ColMerge:0,   SaveName: prefix + "jo_crr_cd",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	                       {Type:"Text",      Hidden:0, Width:70,   Align:"Center",  ColMerge:0,   SaveName: prefix + "rlane_cd",       KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	                       {Type:"Text",      Hidden:0, Width:90,   Align:"Center",  ColMerge:1,   SaveName: prefix + "inv_no",         KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	                       {Type:"Text",      Hidden:0, Width:120,  Align:"Center",  ColMerge:1,   SaveName: prefix + "csr_no",         KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	                       {Type:"Text",      Hidden:0, Width:90,   Align:"Center",  ColMerge:1,   SaveName: prefix + "apro_flg",       KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	                       {Type:"Text",      Hidden:0, Width:50,   Align:"Center",  ColMerge:0,   SaveName: prefix + "locl_curr_cd",   KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
	                       {Type:"Float",     Hidden:0, Width:120,  Align:"Right",   ColMerge:0,   SaveName: prefix + "inv_rev_act_amt",KeyField:0,   CalcLogic:"",   Format:"NullFloat",   PointCount:2,   UpdateEdit:0,   InsertEdit:0 },
	                       {Type:"Float",     Hidden:0, Width:120,  Align:"Right",   ColMerge:0,   SaveName: prefix + "inv_exp_act_amt",KeyField:0,   CalcLogic:"",   Format:"NullFloat",   PointCount:2,   UpdateEdit:0,   InsertEdit:0 },
	                       {Type:"Text",      Hidden:0, Width:90,   Align:"Left",    ColMerge:0,   SaveName: prefix + "prnr_ref_no",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },   
	                       {Type:"Text",      Hidden:0, Width:200,  Align:"Left",    ColMerge:0,   SaveName: prefix + "cust_vndr_eng_nm",KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 }   
	                        ];
	                 
	                InitColumns(cols);
	                SetCountPosition();
	                SetEditable(1);
	                ShowSubSum([{ StdCol: prefix+"inv_no", SumCols: "7|8", CaptionText: " ", CaptionCol: 3 }]);
	             }
		break;
	 case "t2sheet1": // t2sheet1 init     
        with(sheetObj){
        var HeadTitle1="|Partner|Lane|Invoice No|Slip No|Approved|Rev\nExp|Item|Curr.|Revenue|Expense|Customer/S.Provider|Customer/S.Provider";
        var HeadTitle2="|Partner|Lane|Invoice No|Slip No|Approved|Rev\nExp|Item|Curr.|Revenue|Expense|Code|Name";

        var headCount=ComCountHeadTitle(HeadTitle1);
        SetConfig( { SearchMode:2, MergeSheet:7, Page:50, DataRowMerge:0 } );
        var info    = { Sort:0, ColMove:1, HeaderCheck:1, ColResize:1 };
        var headers = [ { Text:HeadTitle1, Align:"Center"} ,  { Text:HeadTitle2, Align:"Center"}];
        InitHeaders(headers, info);
        var prefix = "t2sheet1_";
        
        var cols = [ 
               {Type:"Status",    Hidden:1, Width:0,    Align:"Center",  ColMerge:1,   SaveName: prefix + "ibflag" },
               {Type:"Text",      Hidden:0, Width:55,   Align:"Center",  ColMerge:0,   SaveName: prefix + "jo_crr_cd",          KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
               {Type:"Text",      Hidden:0, Width:55,   Align:"Center",  ColMerge:0,   SaveName: prefix + "rlane_cd",           KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
               {Type:"Text",      Hidden:0, Width:80,   Align:"Center",  ColMerge:1,   SaveName: prefix + "inv_no",             KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
               {Type:"Text",      Hidden:0, Width:120,  Align:"Center",  ColMerge:1,   SaveName: prefix + "csr_no",             KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
               {Type:"Text",      Hidden:0, Width:65,   Align:"Center",  ColMerge:1,   SaveName: prefix + "apro_flg",           KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
               {Type:"Text",   	  Hidden:0, Width:45,   Align:"Center",  ColMerge:0,   SaveName: prefix + "rev_exp",         	KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
               {Type:"Text",      Hidden:0, Width:55,   Align:"Center",  ColMerge:0,   SaveName: prefix + "item",      			KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
               {Type:"Text",      Hidden:0, Width:45,   Align:"Center",  ColMerge:0,   SaveName: prefix + "locl_curr_cd",       KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
               {Type:"Float",     Hidden:0, Width:120,  Align:"Right",   ColMerge:0,   SaveName: prefix + "inv_rev_act_amt",    KeyField:0,   CalcLogic:"",   Format:"NullFloat",   PointCount:2,   UpdateEdit:0,   InsertEdit:0 },
               {Type:"Float",     Hidden:0, Width:120,  Align:"Right",   ColMerge:0,   SaveName: prefix + "inv_exp_act_amt",    KeyField:0,   CalcLogic:"",   Format:"NullFloat",   PointCount:2,   UpdateEdit:0,   InsertEdit:0 },
               {Type:"Text",      Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName: prefix + "prnr_ref_no",        KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },
               {Type:"Text",      Hidden:0, Width:150,  Align:"Left",    ColMerge:0,   SaveName: prefix + "cust_vndr_eng_nm",   KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 }
                ];
         
        InitColumns(cols);
        SetCountPosition();
        SetEditable(1);
        ShowSubSum([{ StdCol: prefix+"inv_no", SumCols: "9|10", ShowCumulate: 0, CaptionText: " ", CaptionCol: 3 }]);
	    }
        
	    break;
	}
}

/**
 * Do Action in IB Sheet 
 * @param sheetObj
 * @param formObj
 * @param sAction
 */
function doActionIBSheet(sheetObj, formObj, sAction) {
  sheetObj.ShowDebugMsg(false);
  var sheetID=sheetObj.id;
  switch (sAction) {
      case IBSEARCH: // retrieve
          if ( sheetID == "t1sheet1"){
              formObj.f_cmd.value=SEARCH01;
              var param = FormQueryString(formObj);
                  param += "&" + ComGetPrefixParam(sheetID+"_");
              ComOpenWait(true);
              var sXml=sheetObj.GetSearchData("CLV_PRACTICE_003GS.do", param);
              allCurrency	  = ComGetEtcData(sXml, "currency_data");
              sheetObj.LoadSearchData(sXml,{Sync:1} );
              
              ComOpenWait(false);
              summaryFlag = 1;
          }else if ( sheetID == "t2sheet1"){
              formObj.f_cmd.value=SEARCH02;
              var param = FormQueryString(formObj);
                  param += "&" + ComGetPrefixParam(sheetID+"_");
              ComOpenWait(true);
              var sXml=sheetObj.GetSearchData("CLV_PRACTICE_003GS.do", param);
              allCurrency	  = ComGetEtcData(sXml, "currency_data");
              sheetObj.LoadSearchData(sXml,{Sync:1} );
              ComOpenWait(false);
              detailFlag =1;
          }
          break; 

      case IBSEARCH_ASYNC02: // Get List Rev Lane Code By Carrier Code
          formObj.f_cmd.value = SEARCH24; //SEARCHLIST06;
          rlane_cds.RemoveAll();
          var joCrrCds = jo_crr_cds.GetSelectCode(); 
          var param = FormQueryString(formObj);
          var sXml = sheetObj.GetSearchData("CLV_PRACTICE_003GS.do", param);
          var comboItems="";
          var rlaneCds    = ComGetEtcData(sXml, "rlane_cds");      
          comboItems=rlaneCds.split(ROWMARK);
          rlane_cds.InsertItem(-1, "ALL|", "ALL");
          
          for (var i=0 ; i < comboItems.length ; i++) {
        	  if(comboItems[i]!==null){
        		  rlane_cds.InsertItem(-1, comboItems[i], comboItems[i]);
        	  }

          }
         
          break;
      case IBSEARCH_ASYNC03: // Get List Trade Code By Carrier Code and Rev Lane
          formObj.f_cmd.value = SEARCH25; //SEARCHLIST06;
          //Remove all old code
          trd_cd.RemoveAll();
          var joCrrCds = jo_crr_cds.GetSelectCode(); 
          var rLaneCds = rlane_cds.GetSelectCode();
          var param = FormQueryString(formObj);
          var sXml = sheetObj.GetSearchData("CLV_PRACTICE_003GS.do", param);
          var comboItems="";
          var trdCds    = ComGetEtcData(sXml, "trd_cds");      
          comboItems=trdCds.split(ROWMARK);
          for (var i=0 ; i < comboItems.length ; i++) {
        	  if(comboItems[i]!==null){
        		  trd_cd.InsertItem(-1, comboItems[i], comboItems[i]);
        	  }
        	  trd_cd.SetSelectIndex(0);
        	 
          }
          comboItems="";
          
          break;
      case IBDOWNEXCEL:
    	  if ( sheetID == "t1sheet1"){
              formObj.f_cmd.value=SEARCH01;
              var param = FormQueryString(formObj);
                  param += "&" + ComGetPrefixParam(sheetID+"_");
              ComOpenWait(true);
              var sXml=sheetObj.GetSearchData("CLV_PRACTICE_003GS.do", param);
              allCurrency	  = ComGetEtcData(sXml, "currency_data");
              sheetObj.LoadSearchData(sXml,{Sync:1} );
              ComOpenWait(false);
          }else if ( sheetID == "t2sheet1"){
              formObj.f_cmd.value=SEARCH02;
              var param = FormQueryString(formObj);
                  param += "&" + ComGetPrefixParam(sheetID+"_");
              ComOpenWait(true);
              var sXml=sheetObj.GetSearchData("CLV_PRACTICE_003GS.do", param);
              sheetObj.LoadSearchData(sXml,{Sync:1} );
              ComOpenWait(false);
          }
    	  sheetObj.Down2Excel({
				HiddenColumn : 1,
				Merge : 1
			});
    	  
          break;
	}	
}
 
/**
 * ComboBox Joo Carrier Code When Clicked 
 * 
 * @param comboObj
 * @param value
 * @param text
 */
function jo_crr_cds_OnCheckClick(comboObj, index, code) {
  if(index==0) {          
      var bChk=comboObj.GetItemCheck(index);
      if(bChk){
          for(var i=1 ; i < comboObj.GetItemCount() ; i++) {
              comboObj.SetItemCheck(i,0);
          }
      }
  } else {
      var bChk=comboObj.GetItemCheck(index);
      if (bChk) {
          comboObj.SetItemCheck(0,0);
      }
  }
  var checkCnt=0;
  var count = parseInt(comboObj.GetItemCount());
  for(var i = 1 ; i <  count; i++) {
      if(comboObj.GetItemCheck(i)) {
          checkCnt++;
      }
  }
  if(checkCnt == 0) {
      comboObj.SetItemCheck(0,true, null, null, false);
  }
}
/**
 * jo_crr_cds comboObject when have changing
 * 
 * @param comboObj
 * @param formObj
 */
function jo_crr_cds_OnChange(comboObj, oldIndex, oldText, oldCode, newIndex,
		newText, newCode) {
	var formObj = document.form;
	if (comboObj.GetSelectCode() != null) {
		doActionIBSheet(getCurrentSheet(), formObj, IBSEARCH_ASYNC02);
		rlane_cds.SetEnable(1);
		trd_cd.SetEnable(0);
		trd_cd.RemoveAll();

	}
}
/**
 * rlane_cds comboObject when have changing
 * 
 * @param comboObj
 * @param formObj
 */
function rlane_cds_OnChange(comboObj, oldIndex, oldText, oldCode, newIndex,
		newText, newCode) {
	var formObj = document.form;
	if (comboObj.GetSelectCode() != null) {
		doActionIBSheet(getCurrentSheet(), formObj, IBSEARCH_ASYNC03);
		trd_cd.SetEnable(1);
	}
}

/**
 * Resize Sheet Object
 * 
 */
function resizeSheet() {
	if (beforetab == 0) {
		ComResizeSheet(sheetObjects[0]);
	} else {
		ComResizeSheet(sheetObjects[1]);
	}

}
/**
 * Tab 1 Sheet 1 Double Click and do Search to Detail
 * 
 * @param currentRow
 * @param parentCodeS1
 * @param rLaneCodeS1
 * @param iStRow
 * @param iEdRow
 */
function t1sheet1_OnDblClick(sheetObj, Row,Col){
	var currentRow = sheetObj.GetSelectRow();
	var parentCodeS1 = sheetObj.GetCellValue(currentRow,1);
	var rLaneCodeS1  = sheetObj.GetCellValue(currentRow,2);
	var slipNoS1 	 = sheetObj.GetCellValue(currentRow,4);
	var unFindFlag =0;
	if(parentCodeS1!=="" && rLaneCodeS1 !==""){
		tabObjects[0].SetSelectedIndex(1);
		if(findJooRlaneCode(getCurrentSheet(),parentCodeS1,rLaneCodeS1,slipNoS1) === 1){
		tabObjects[0].SetSelectedIndex(0);
		}

	}else{
		ComShowMessage("No please");
		return;
	}

}

/**
 * find Joo Carrier and Rev Lane Code
 * 
 * @param sheetObj
 * @param parentCodeS1
 * @param rLaneCodeS1
 * @param slipNoS1
 */

function findJooRlaneCode(sheetObj,parentCodeS1,rLaneCodeS1,slipNoS1){

	var iStRow = sheetObj.HeaderRows();
    var iEdRow = sheetObj.LastRow();
    var findOut = 0;
    var index = -1;
	for(var i=iStRow;i<=iEdRow;i++){
		var s2JooCrr = sheetObj.GetCellValue(i, "t2sheet1_" + "jo_crr_cd");
		var s2RlaneCd = sheetObj.GetCellValue(i, "t2sheet1_" + "rlane_cd");
		var slipNoS2 = sheetObj.GetCellValue(i, "t2sheet1_" + "csr_no");
		if (parentCodeS1 === s2JooCrr && rLaneCodeS1 === s2RlaneCd && slipNoS1 === slipNoS2) {
			findOut = 1;
			index = i;
			unFindFlag = 0;
			break;
		} 
		
		iEdRow = sheetObj.LastRow();
	}
	if(findOut === 1){
		sheetObj.SetSelectRow(index);
		
	}else{
		findOut =0;
		return 1;
	}
	
	
}
/**
 * Check Period in Date From to Date To
 * 
 * @param comboObj
 * @param frDt
 * @param toDt
 */
function GetCheckConditionPeriod(){
    var formObj=document.form;
    var frDt=formObj.date_fr.value.replaceStr("-","")+"01";
    var toDt=formObj.date_to.value.replaceStr("-","")+"01";
    if (ComGetDaysBetween(frDt, toDt) <= 0){
        return false;
    }   
    return true;
}

/**
 * add month click
 * @param obj
 * @param iMonth
 * @return
 */

function UF_addMonth(obj, iMonth){
	 if (obj.value != "") {
		 obj.value=ComGetDateAdd(obj.value+"-01", "M", iMonth).substring(0, 7);
	 }
}

/**
 * Init Period
 * 
 * @param formObj
 * @param tmpToYm
 * @param toDt
 */
function initPeriod(){
    var formObj=document.form;
    //Get tmp Date To and set to value date_to
    var tmpToYm = ComGetNowInfo("ymd","-"); 
    formObj.date_to.value=GetDateFormat(tmpToYm,"ym");
    //Get Date Tmp From and set to Value date_fr
    var tmpFrYm = ComGetDateAdd(formObj.date_to.value+"-01","M", -2);
    formObj.date_fr.value=GetDateFormat(tmpFrYm,"ym"); 
}

/**
 * Get Date Format
 * 
 * @param sVal
 * @param sFormat
 * @param retValue
 */
function GetDateFormat(obj, sFormat){
	var sVal = String(getArgValue(obj));
	sVal = sVal.replace(/\/|\-|\.|\:|\ /g,"");
	
    if (ComIsEmpty(sVal)) return "";
    
	var retValue = "";
	switch(sFormat){
	    	
		case "ymd":		//yyyy-mm-dd 10	            
		case "mdy":		//mm-dd-yyyy 10
			retValue = sVal.substring(0,8);
			break;     	            
	    case "ym":		//yyyy-mm 7
	    case "yw":		//yyyy-mm 7
	    case "hms":     //hh:mm:ss 8
			retValue = sVal.substring(0,6);
			break;     	            
	    case "yyyy":     //yyyy 4   	            
	    case "hm":      //hh:mm 5
			retValue = sVal.substring(0,4);
			break;
	    case "ymdhms":     //yyyy-mm-dd hh:mm:ss 19
			retValue = sVal.substring(0,14);
			break;    	            
	    case "ymdhm":     //yyyy-mm-dd hh:mm 16
			retValue = sVal.substring(0,12);
			break;
		}

	retValue = ComGetMaskedValue(retValue,sFormat);
	return retValue;
}
/**
 * Calculate Sum based on SubSumRow 
 * 
 * @param sheetObj
 * @param prefix
 * @param currPos
 * @param revPos
 * @param expPos
 */
function showSumTotal(sheetObj,prefix,currPos,revPos,expPos){
	 if (sheetObj.RowCount() > 0) {

	      var findSubSum = sheetObj.FindSubSumRow();
	      var arrSubSum = findSubSum.split(ROWMARK);
	      var allCurrencyG=allCurrency.split(ROWMARK);
	      
	 
	      for(var i =0;i<allCurrencyG.length;i++){
	    	  if(allCurrencyG[i] !==""){
	        	  sheetObj.DataInsert(-1);
	        	  this["totalRev"+allCurrencyG[i]]=0;
	        	  this["totalExp"+allCurrencyG[i]]=0;
	        	  for(var j=0;j<arrSubSum.length;++j){
	        		  if(allCurrencyG[i] == sheetObj.GetCellText(arrSubSum[j]-1, prefix + "locl_curr_cd")){
	        			  this["totalRev"+allCurrencyG[i]] = this["totalRev"+allCurrencyG[i]] + sheetObj.GetCellValue(arrSubSum[j], prefix + "inv_rev_act_amt");
	        			  this["totalExp"+allCurrencyG[i]] = this["totalExp"+allCurrencyG[i]] + sheetObj.GetCellValue(arrSubSum[j], prefix + "inv_exp_act_amt");
	        		  }
	        		 
	        	  }
	        	  sheetObj.SetCellValue(sheetObj.LastRow(),currPos,allCurrencyG[i]);
	        	  sheetObj.SetCellValue(sheetObj.LastRow(),revPos,this["totalRev"+allCurrencyG[i]]);
	        	  sheetObj.SetCellValue(sheetObj.LastRow(),expPos,this["totalExp"+allCurrencyG[i]]);
	        	  sheetObj.SetCellFontBold(sheetObj.LastRow(),  prefix + "locl_curr_cd", 1);
	              sheetObj.SetCellFontBold(sheetObj.LastRow(),  prefix + "inv_rev_act_amt", 1);
	              sheetObj.SetCellFontBold(sheetObj.LastRow(),  prefix + "inv_exp_act_amt", 1);
	              sheetObj.SetRowBackColor(sheetObj.LastRow(),"#FFD700");
	    	  }
	      }
	      
	  }
}
/**
 * Calculate Total when t2sheet1 end
 * @param sheetObj
 */
function t2sheet1_OnSearchEnd(sheetObj){
	showSumTotal(sheetObj,"t2sheet1_",8,9,10);
}

/**
 * Calculate Total when t1sheet1 end
 * @param sheetObj
 */
function t1sheet1_OnSearchEnd(sheetObj){
	showSumTotal(sheetObj,"t1sheet1_",6,7,8);
}

