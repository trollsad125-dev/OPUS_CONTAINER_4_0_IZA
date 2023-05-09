<%
/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLV_TRAINING_001.jsp
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2023.04.19
*@LastModifier : 
*@LastVersion : 1.0
* 2023.04.19 
* 1.0 Creation
=========================================================*/
%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.clt.framework.component.util.JSPUtil"%>
<%@ page import="com.clt.framework.component.util.DateTime"%>
<%@ page import="com.clt.framework.component.message.ErrorHandler"%>
<%@ page import="com.clt.framework.core.layer.event.GeneralEventResponse"%>
<%@ page import="com.clt.framework.support.controller.html.CommonWebKeys"%>
<%@ page import="com.clt.framework.support.view.signon.SignOnUserAccount"%>
<%@ page import="com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.event.ClvTraining001Event"%>
<%@ page import="org.apache.log4j.Logger" %>

<%
	ClvTraining001Event  event = null;					//PDTO(Data Transfer Object including Parameters)
	Exception serverException   = null;			//서버에서 발생한 에러
	String strErrMsg = "";						//에러메세지
	int rowCount	 = 0;						//DB ResultSet 리스트의 건수

	String successFlag = "";
	String codeList  = "";
	String pageRows  = "100";

	String strUsr_id		= "";
	String strUsr_nm		= "";
	String strUsr_auth_tp_cd = "";
	Logger log = Logger.getLogger("com.clt.apps.CLVTraining.ErrMsgMgmt");

	try {
	   	SignOnUserAccount account=(SignOnUserAccount)session.getAttribute(CommonWebKeys.SIGN_ON_USER_ACCOUNT);

		strUsr_id =	account.getUsr_id();
		strUsr_nm = account.getUsr_nm();
		strUsr_auth_tp_cd = account.getUsr_auth_tp_cd();

		event = (ClvTraining001Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);

		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		}

		// 초기화면 로딩시 서버로부터 가져온 데이터 추출하는 로직추가 ..
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
		
	}catch(Exception e) {
		out.println(e.toString());
	}
%>

<script language="javascript">
	function setupPage(){
		var errMessage = "<%=strErrMsg%>";
		if (errMessage.length >= 1) {
			ComShowMessage(errMessage);
		} // end if
		loadPage();
	}
</script>



<form name="form">
<input type="hidden" name="f_cmd">
<input type="hidden" name="pagerows">
<input type="hidden" name="lang_tp_cd" value="ENG">
<!-- 개발자 작업	-->
		<div class="page_title_area clear">
			<h2 class="page_title">
				<button type="button">
					<span id="title"></span>
				</button>
			</h2>
			<div class="opus_design_btn">
				<button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button><!-- 
				 --><button type="button" class="btn_normal" name="btn_Save" id="btn_Save">Save</button><!-- 
				 --><button type="button" class="btn_normal" name="btn_DownExcel" id="btn_DownExcel">Down Excel</button>
			</div>
			<div class="location">
				<span id="navigation"></span>
			</div>
		</div>
		<div class="wrap_search">
			<div class="opus_design_inquiry">
				<table>
					<tbody>
						<tr>
							<th width="40">Message Code</th>
							<td width="120"><input type="text" style="width: 100px;"
								class="input" value="" name="err_msg_cd" id="err_msg_cd"></td>
							<th width="40">Message</th>
							<td><input type="text" style="width: 100px;" class="input" value="" name="err_msg" id="err_msg"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="wrap_result">
			<div class="opus_design_grid">
				<div class="opus_design_btn">
					<button type="button" class="btn_accent" name="btn_Add" id="btn_Add">Row Add</button>
				</div>
				<script language="javascript">ComSheetObject('sheet1');</script>
			</div>
		</div>

		<!-- 개발자 작업  끝 -->
</form>
