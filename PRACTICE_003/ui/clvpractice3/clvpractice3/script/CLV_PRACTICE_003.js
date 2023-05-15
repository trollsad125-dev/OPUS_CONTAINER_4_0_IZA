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

	msgs['PRC00001']="'To date' must be later than 'From date'.";
    msgs['PRC00002']="{?msg1} code is invalid !";
    
// common global variable
var sheetObjects = new Array();
var sheetCnt = 0;
var comboObjects = new Array();
var comboCnt = 0;
var tabObjects=new Array();
var tabCnt=0 ;
var beforetab=1;
var ROWMARK = "|";
var FIELDMARK=",";
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
                    cal.select(formObj.date_fr, 'yyyy-MM-dd');
                }else{
                    cal.select(formObj.date_to, 'yyyy-MM-dd');
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
			ComShowCodeMessage('JOO00001');
		} else {
			ComShowMessage(e.message);
		}
	}
}
/**
 * rest form when click new button
 */
function resetForm(formObj){
	formObj.reset();
	sheetObjects[0].RemoveAll();
	s_jo_crr_cd.SetSelectIndex(0);
}
/**
 * registering IBSheet Object as list adding process for list in case of needing
 * batch processing with other items defining list on the top of source
 */
function setSheetObject(sheet_obj) {
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
 */
function setComboObject(combo_obj) {
    comboObjects[comboCnt++] = combo_obj;
}
/**
 * registering IBTab Object as list
 * adding process for list in case of needing batch processing with other items
 * defining list on the top of source
 */
function setTabObject(tab_obj){
    tabObjects[tabCnt++]=tab_obj;
}
/**
 * initializing Tab
 * setting Tab items
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
 */
function tab1_OnChange(tabObj , nItem)
{
    var objs=document.all.item("tabLayer");
    objs[nItem].style.display="Inline";
    objs[beforetab].style.display="none";
    //--------------- important --------------------------//
    objs[beforetab].style.zIndex=objs[nItem].style.zIndex -1 ;
    //------------------------------------------------------//
    beforetab=nItem;
    
    if (beforetab == 0) {
        ComFireEvent(ComGetObject("btn_Retrieve") ,"click");
    }else{
        ComFireEvent(ComGetObject("btn_Retrieve") ,"click");
    }
    
    resizeSheet();
}

/**
 * setting Combo basic info
 * 
 * @param comboObj
 * @param comboIndex
 *            Number
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
                SetColWidth(0, "150");
                ValidChar(2, 1); // Uppercase
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
                // SetColWidth(0, "60");
                ValidChar(2, 1); // Uppercase
                SetDropHeight(160);
                SetMaxLength(3);
                SetEnable(0);
            }
            break;

        case "rlane_cds": 
            with (comboObj) { 
                SetMultiSelect(false);
                SetUseAutoComplete(1);
                SetColAlign(0, "left");
                SetColWidth(0, "90");
                SetDropHeight(160);
                ValidChar(2,1);//only upper case
                SetMaxLength(7);
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
    //initControl();
    resizeSheet();
    //doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
}
/*function initControl() {
    var formObject=document.form;   

    
    TO : 현재 월
    FR : 현재 월 - 2 : 3개월전.
     
    initPeriod();  
    
    doActionIBSheet(sheetObjects[0], document.form, IBSEARCH_ASYNC02);
}

function initPeriod(){
    var formObj=document.form;
    
    //From : -3년, To : +1 월
    var tmpToYm = ComGetNowInfo("ymd","-"); //ComGetDateAdd(formObj.exe_yrmon.value,"M", 1);
    formObj.to_acct_yrmon.value=JooGetDateFormat(tmpToYm,"ym");
    
    //년월 만 하면 -3년이 넘게 설정 되어 동일 월로 설정하기 위해서 2015-07-02 로 Day 를 마춘다.
    var tmpFrYm = ComGetDateAdd(formObj.to_acct_yrmon.value+"-01","M", -2);
    formObj.fr_acct_yrmon.value=JooGetDateFormat(tmpFrYm,"ym"); 
}
*/


function jo_crr_cd_OnCheckClick(comboObj, index, code) {
    if(index==0) {          
        var bChk=comboObj.GetItemCheck(index);
        if(bChk){
            for(var i=1 ; i < comboObj.GetItemCount() ; i++) {
                comboObj.SetItemCheck(i,0);
            }
        }
    } else {
        //ALL 
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
 * setting sheet initial values and header param : sheetObj, sheetNo adding case
 * as numbers of counting sheets
 */
function initSheet(sheetObj, sheetNo) {
	var cnt = 0;
	switch (sheetObj.id) {
	 case "t1sheet1": // t1sheet1 init     //t1sheet1 init
         with(sheetObj){
             var HeadTitle1="|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Customer/S.Provider|Customer/S.Provider|cust_vndr_cnt_cd|cust_vndr_seq";
             var HeadTitle2="|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Code|Name|cust_vndr_cnt_cd|cust_vndr_seq";
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
	                       {Type:"Text",      Hidden:0, Width:200,  Align:"Left",    ColMerge:0,   SaveName: prefix + "cust_vndr_eng_nm",KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },   
	                       {Type:"Text",      Hidden:1, Width:90,   Align:"Left",    ColMerge:0,   SaveName: prefix + "cust_vndr_cnt_cd",KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 },   
	                       {Type:"Text",      Hidden:1, Width:90,   Align:"Left",    ColMerge:0,   SaveName: prefix + "cust_vndr_seq",  KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:0 }
	                        ];
	                 
	                InitColumns(cols);
	                SetCountPosition();
	                SetEditable(1);
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
        //SetColProperty(0    , prefix +"re_divr_cd"      , {ComboText:"|Rev|Exp", ComboCode:"|R|E", DefaultValue:"R"} );
        SetCountPosition();
        SetEditable(1);
        
     }
    break;
}
}
function resizeSheet(){
    ComResizeSheet(sheetObjects[0]);
}

//handling sheet process
function doActionIBSheet(sheetObj, formObj, sAction, cRow) {
  sheetObj.ShowDebugMsg(false);
  var sheetID=sheetObj.id;
  switch (sAction) {
      case IBSEARCH: // retrieve
          if( !validateForm(sheetObj,formObj,sAction) ){return;}
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
      case IBINSERT: // insert
          break;
          
      case IBSEARCH_ASYNC08: // Carrier/Trade/RLane Combo Code By Auth Office Code
          var param = "";
              param += "f_cmd="   + SEARCH23;
          
          var sXml = sheetObj.GetSearchData("CLV_PRACTICE_003GS.do", param);
          
          var joCrrCds    = ComGetEtcData(sXml, "jo_crr_cds");
          
          var trdCds      = ComGetEtcData(sXml, "trd_cds");
          var rlaneCds    = ComGetEtcData(sXml, "rlane_cds");            
          
          var comboItems=joCrrCds.split(ROWMARK);
          var comboItem="";
          jo_crr_cds.InsertItem(-1, "ALL|", "ALL");    //ALL
          for (var i=0 ; i < comboItems.length ; i++) {
              jo_crr_cds.InsertItem(-1, comboItems[i], comboItems[i]);
          }
          jo_crr_cds.SetSelectIndex(0);
          
          break;
  }
}
function t1sheet1_OnSearchEnd(sheetObj, ErrMsg) {
  if (sheetObj.RowCount() > 0) {

	  var index = "";
      var strPartner = "";
      var nextStrPartner = "";
      var strLane = "";
      var nextStrLane = "";
      var iStRow = sheetObj.HeaderRows();
      var iEdRow = sheetObj.LastRow();
      var prefix = "t1sheet1_";
      var totalColumnValue=1;
      for(var i=iStRow;i<=iEdRow;i++){
    	  //First line in sheet
          strPartner = sheetObj.GetCellText(i, prefix + "jo_crr_cd"); 
          strLane = sheetObj.GetCellText(i, prefix + "rlane_cd"); 
          //Next First Line in Sheet
          nextStrPartner = sheetObj.GetCellText(i+1, prefix + "jo_crr_cd");
     	  nextStrLane = sheetObj.GetCellText(i+1, prefix + "rlane_cd");
         
         if(strPartner === nextStrPartner && strLane === nextStrLane){
        	 totalColumnValue++;
         
         }else{
        	 //Insert next row
        	 sheetObj.DataInsert(i+1);
        	
        	 //Get Current total data
        	 var currentCurr = sheetObj.GetCellText(i, prefix + "locl_curr_cd");
        	 var totalRevMoney = 0;
        	 var totalExpenseMoney = 0;
    	 
        	 for(var j=0;j<totalColumnValue;j++){
        		 var revMoney = sheetObj.GetCellValue(i-j, prefix + "inv_rev_act_amt");
        		 var expenseMoney = sheetObj.GetCellValue(i-j, prefix + "inv_exp_act_amt");
        		 totalRevMoney=totalRevMoney+ revMoney;
        		 totalExpenseMoney = totalExpenseMoney + expenseMoney;
        	 }
        	 //Handle total value
        	 i++;
        	 index += i+ROWMARK;
             sheetObj.SetRowBackColor(i,"#FCDCEE");
             //Set Last Row again
        	 iEdRow = sheetObj.LastRow();
             sheetObj.SetCellFontBold(i,  prefix + "jo_crr_cd", 1);
             sheetObj.SetCellFontBold(i,  prefix + "locl_curr_cd", 1);
             sheetObj.SetCellFontBold(i,  prefix + "inv_rev_act_amt", 1);
             sheetObj.SetCellFontBold(i,  prefix + "inv_exp_act_amt", 1);
             //Currency
             sheetObj.SetCellValue(i,6,currentCurr);
             //Rev 
             sheetObj.SetCellValue(i,7,totalRevMoney);
             //Expense
             sheetObj.SetCellValue(i,8,totalExpenseMoney);
             totalColumnValue=1;
         }
         
      }
      var allCurrencyG=allCurrency.split(ROWMARK);
      var allIndexAdded = index.split(ROWMARK);
 
      for(var i =0;i<allCurrencyG.length;i++){
    	  if(allCurrencyG[i] !==""){
        	  sheetObj.DataInsert(-1);
        	  this["totalRev"+allCurrencyG[i]]=0;
        	  this["totalExp"+allCurrencyG[i]]=0;
        	  for(var j=0;j<allIndexAdded.length;++j){
        		  if(allCurrencyG[i] == sheetObj.GetCellText(allIndexAdded[j], prefix + "locl_curr_cd")){
        			  this["totalRev"+allCurrencyG[i]] = this["totalRev"+allCurrencyG[i]] + sheetObj.GetCellValue(allIndexAdded[j], prefix + "inv_rev_act_amt");
        			  this["totalExp"+allCurrencyG[i]] = this["totalExp"+allCurrencyG[i]] + sheetObj.GetCellValue(allIndexAdded[j], prefix + "inv_exp_act_amt");
        		  }
        		 
        	  }
        	  sheetObj.SetCellValue(sheetObj.LastRow(),6,allCurrencyG[i]);
        	  sheetObj.SetCellValue(sheetObj.LastRow(),7,this["totalRev"+allCurrencyG[i]]);
        	  sheetObj.SetCellValue(sheetObj.LastRow(),8,this["totalExp"+allCurrencyG[i]]);
        	  sheetObj.SetCellFontBold(sheetObj.LastRow(),  prefix + "locl_curr_cd", 1);
              sheetObj.SetCellFontBold(sheetObj.LastRow(),  prefix + "inv_rev_act_amt", 1);
              sheetObj.SetCellFontBold(sheetObj.LastRow(),  prefix + "inv_exp_act_amt", 1);
              sheetObj.SetRowBackColor(sheetObj.LastRow(),"#FFD700");
    	  }
      }
      
  }
}
function t1sheet1_OnSaveEnd(sheetObj, Code, Msg, StCode, StMsg) {
  if (sheetObj.RowCount() > 0) {

  }
}

function t2sheet1_OnSearchEnd(sheetObj, ErrMsg) {
  if (sheetObj.RowCount() > 0) {


      var str = "";
      var iStRow = sheetObj.HeaderRows();
      var iEdRow = sheetObj.LastRow();
      var prefix = "t2sheet1_";
      for(var i=iStRow;i<=iEdRow;i++){
          str = sheetObj.GetCellText(i, prefix + "jo_crr_cd");   // ITM 항목 읽기
          
          if(str.indexOf("Total")>-1){
              sheetObj.SetMergeCell(i,1,1,11); // OWN 5칸 머지
              
              var tmpStr = ComReplaceStr(str+"","Total:","");
              sheetObj.SetCellValue(i, prefix + "jo_crr_cd", tmpStr, 0); // OWN 5칸 머지
              
              sheetObj.SetRowBackColor(i,"#FCDCEE");
              sheetObj.SetCellFontBold(i,  prefix + "jo_crr_cd", 1);
              sheetObj.SetCellFontBold(i,  prefix + "locl_curr_cd", 1);
              sheetObj.SetCellFontBold(i,  prefix + "inv_act_amt", 1);
              sheetObj.SetCellFontBold(i,  prefix + "slp_act_amt", 1);
          }else if(str.indexOf("Subtotal")>-1){
              sheetObj.SetMergeCell(i,1,1,11); // OWN 5칸 머지
              
              var tmpStr = ComReplaceStr(str+"","Subtotal:","");
              sheetObj.SetCellValue(i, prefix + "jo_crr_cd", tmpStr, 0); // OWN 5칸 머지
              sheetObj.SetRowBackColor(i,"#FEFA91");   
              sheetObj.SetCellFontBold(i,  prefix + "jo_crr_cd", 1);  
              sheetObj.SetCellFontBold(i,  prefix + "locl_curr_cd", 1);   
              sheetObj.SetCellFontBold(i,  prefix + "inv_act_amt", 1);    
              sheetObj.SetCellFontBold(i,  prefix + "slp_act_amt", 1);         
          }
      }
  }
}
/**
* handling process for input validation
*/
function validateForm(sheetObj, formObj, sAction) {
/*    with (formObj) {
      switch (sAction) {
          case IBSAVE:
              var sParam = ComGetSaveString(sheetObj);
              if (sParam == "") {
                  return false;
              }
              var Row = sheetObj.ColValueDup("jo_crr_cd|rlane_cd", false);
              if (Row > -1) {
                  ComShowCodeMessage("JOO00161");
                  sheetObj.SelectCell(Row, "rlane_cd");
                  return false;
              }
              if (!ComShowCodeConfirm("JOO00046")) {
                  return false;
              }
              break;
      } // end switch
  }*/
  return true;
}
function jo_crr_cds_OnCheckClick(comboObj, index, code) {
  if(index==0) {          
      var bChk=comboObj.GetItemCheck(index);
      if(bChk){
          for(var i=1 ; i < comboObj.GetItemCount() ; i++) {
              comboObj.SetItemCheck(i,0);
          }
      }
  } else {
      //ALL 이 아닌 다른 Item 체크.
      var bChk=comboObj.GetItemCheck(index);
      if (bChk) {
          comboObj.SetItemCheck(0,0);
      }
  }
  //Combo Item이 전부 Uncheck 일때 자동으로 All 선택이 되도록 한다.
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
* 
* @param comboObj
* @param value
* @param text
*/
function jo_crr_cds_OnChange(comboObj, oldIndex, oldText, oldCode, newIndex, newText, newCode) {
  var formObj = document.form;
 if(comboObj.GetSelectCode()!=null){
	 doActionIBSheet(getCurrentSheet(), formObj, IBSEARCH_ASYNC02);
	 rlane_cds.SetEnable(1);
	
 }
}
function rlane_cds_OnChange(comboObj, oldIndex, oldText, oldCode, newIndex, newText, newCode) {
	  var formObj = document.form;
	 if(comboObj.GetSelectCode()!=null){
		 doActionIBSheet(getCurrentSheet(), formObj, IBSEARCH_ASYNC03);
		 trd_cd.SetEnable(1);
		
	 }
	}


function GetCheckConditionPeriod(){
  var formObj=document.form;
  var frDt=formObj.fr_acct_yrmon.value.replaceStr("-","")+"01";
  var toDt=formObj.to_acct_yrmon.value.replaceStr("-","")+"01";
  if (ComGetDaysBetween(frDt, toDt) <= 0){
      return false;
  }   
  return true;
}
function resizeSheet() {
  if(beforetab == 0){
      ComResizeSheet(sheetObjects[0]);
  }else{
      ComResizeSheet(sheetObjects[1]);
  }
  
}