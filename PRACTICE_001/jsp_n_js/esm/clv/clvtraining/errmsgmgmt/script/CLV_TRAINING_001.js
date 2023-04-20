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
/****************************************************************************************
  이벤트 구분 코드: [초기화]INIT=0; [입력]ADD=1; [조회]SEARCH=2; [리스트조회]SEARCHLIST=3;
					[수정]MODIFY=4; [삭제]REMOVE=5; [리스트삭제]REMOVELIST=6 [다중처리]MULTI=7
					기타 여분의 문자상수  COMMAND01=11; ~ COMMAND20=30;
 ***************************************************************************************/

/*------------------다음 코드는 JSDoc을 잘 만들기 위해서 추가된 코드임 ------------------*/
   /**
     * @fileoverview 업무에서 공통으로 사용하는 자바스크립트파일로 달력 관련 함수가 정의되어 있다.
     * @author 한진해운
     */

    /**
     * @extends 
     * @class CLV_TRAINING_001 : CLV_TRAINING_001 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
     */
    function CLV_TRAINING_001() {
    	this.processButtonClick		= tprocessButtonClick;
    	this.setSheetObject 		= setSheetObject;
    	this.loadPage 				= loadPage;
    	this.initSheet 				= initSheet;
    	this.initControl            = initControl;
    	this.doActionIBSheet 		= doActionIBSheet;
    	this.setTabObject 			= setTabObject;
    	this.validateForm 			= validateForm;
    }
    
   	/* 개발자 작업	*/
    var sheetObjects=new Array();
    var sheetCnt=0;
    document.onclick=processButtonClick;



    function loadPage(){
    	
    }

    function processButtonClick(){

    }

    function setSheetObject(sheet_obj){

    }

    function initSheet(sheetObj,sheetNo) {

    }

    function doActionIBSheet(sheetObj,formObj,sAction) {

    }


	/* 개발자 작업  끝 */