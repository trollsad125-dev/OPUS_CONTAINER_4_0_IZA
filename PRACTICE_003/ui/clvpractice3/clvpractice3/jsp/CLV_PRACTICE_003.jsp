<%
/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLV_PRACTICE_003.jsp
*@FileTitle : CLV Practice 3
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.11
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.11 
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
<%@ page import="com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.event.ClvPractice003Event"%>
<%@ page import="org.apache.log4j.Logger" %>

<%
	ClvPractice003Event  event = null;					//PDTO(Data Transfer Object including Parameters)
	Exception serverException   = null;			//Server Exception
	String strErrMsg = "";						//Error Message

	Logger log = Logger.getLogger("com.clt.apps.CLVPractice3.CLVPractice3");
	String joCrrCdComboItems	= "";
	String currencyData 		= "";
	try {
	   	SignOnUserAccount account=(SignOnUserAccount)session.getAttribute(CommonWebKeys.SIGN_ON_USER_ACCOUNT);
		event = (ClvPractice003Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);

		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		}

		// 초기화면 로딩시 서버로부터 가져온 데이터 추출하는 로직추가 ..
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
		joCrrCdComboItems  	= eventResponse.getETCData("jo_crr_cds");
		currencyData = eventResponse.getETCData("currency_data");
	}catch(Exception e) {
		out.println(e.toString());
	}
%>

<script type="text/javascript">
	var gJoCrrCdComboItems 		= "<%=joCrrCdComboItems%>";
	var allCurrency				= "<%=currencyData%>";
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
	<!-- page_title_area(S) -->
	<div class="page_title_area clear">
	
		<!-- page_title(S) -->
		<h2 class="page_title"><button type="button"><span id="title"></span></button></h2>
		<!-- page_title(E) -->
		
		<!-- opus_design_btn (S) -->
		<div class="opus_design_btn">
			<button class="btn_accent" name="btn_Retrieve" id="btn_Retrieve" type="button">Retrieve</button><!-- 
		    --><button class="btn_normal" name="btn_New" id="btn_New" type="button">New</button><!--
			--><button class="btn_normal" name="btn_DownExcel" id=btn_DownExcel type="button">Down Excel</button><!-- 
			--><button class="btn_normal" name="btn_DownExcel2" id=btn_DownExce2l type="button">Down Excel2</button>
		</div>
		<!-- opus_design_btn (E) -->
	
		<!-- page_location(S) -->
		<div class="location">	
			<span id="navigation"></span>
		</div>
		<!-- page_location(E) -->
	</div>
	<!-- page_title_area(E) -->
	
	<!-- wrap_search(S) -->
	<div class="wrap_search">
		<!-- opus_design_inquiry(S) -->
		<div class="opus_design_inquiry wFit">
			<table>
				<colgroup>
					<col width="100" />				
					<col width="100" />						
					<col width="70" />	
					<col width="100" />				
					<col width="70" />					
					<col width="*" />				
			   </colgroup> 
			   <tbody>
			   		<tr>
			   		<th>Year Month</th>
						<td><input type="text" style="width: 100px;" class="input1" value="" name="date_fr" id="date_fr" readonly><!--
							--><button type="button" class="btn_left" name="btn_date_fr_down" id="btn_date_fr_down"></button><!--
							--><button type="button" class="btn_right" name="btn_date_fr_up" id="btn_date_fr_up"></button><!--
							--><input type="text" style="width: 100px;" class="input1" value="" name="date_to" id="date_to" readonly><!--
							--><button type="button" class="btn_left" name="btn_date_to_down" id="btn_date_to_down"></button><!--
							--><button type="button" class="btn_right" name="btn_date_to_up" id="btn_date_to_up"></button>
						</td>
						<th>Partner</th>
						<td><script type="text/javascript">ComComboObject('jo_crr_cds', 1, 100, 0, 0);</script></td>
						<th>Lane</th>
						<td><script type="text/javascript">ComComboObject('rlane_cds',1, 100, 0, 0);</script></td>
		               	<th>Trade</th>
						<td><script type="text/javascript">ComComboObject('trd_cd',1, 100, 0, 0);</script></td>
					</tr> 
			   </tbody>
			</table>
		</div>
		<!-- opus_design_inquiry(E) -->
	</div>
	<!-- wrap_search(E) -->
	<!-- wrap_result(S) -->
	<div class="wrap_result">
		<!-- opus_design_grid(S) -->
		<div class="opus_design_tab">
			<script type="text/javascript">ComTabObject('tab1')</script>
		</div>

		<div class="opus_design_grid clear" style="width:98%" name="tabLayer" id="tabLayer">
			<script type="text/javascript">ComSheetObject('t1sheet1');</script>
		</div>
		<div class="opus_design_grid clear" style="width:98%" name="tabLayer" id="tabLayer">
			<script type="text/javascript">ComSheetObject('t2sheet1');</script>
		</div>
		<!-- opus_design_grid(E) -->
	</div>
	<!-- wrap_result(E) -->
</form>

