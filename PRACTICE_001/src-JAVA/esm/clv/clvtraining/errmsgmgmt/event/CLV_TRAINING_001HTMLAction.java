/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLV_TRAINING_001HTMLAction.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2023.04.19
*@LastModifier : 
*@LastVersion : 1.0
* 2023.04.19 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.vo.ComErrMsgVO;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.esm.clv.clvtraining 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 CLVTrainingSC로 실행요청<br>
 * - CLVTrainingSC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * @author Nhat Nguyen
 * @see CLVTrainingEvent 참조
 * @since J2EE 1.6
 */

public class CLV_TRAINING_001HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * CLV_TRAINING_001HTMLAction 객체를 생성
	 */
	public CLV_TRAINING_001HTMLAction() {}

	/**
	 * Parsing Value of HTML DOM object into Java variable<br>
	 * Parse HttpRequest information with CLVPractice2Event and set it in request<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		ClvTraining001Event event = new ClvTraining001Event();
		
		if(command.isCommand(FormCommand.MULTI)) {
			event.setComErrMsgVOS((ComErrMsgVO[])getVOs(request, ComErrMsgVO .class,""));
		}
		else if(command.isCommand(FormCommand.SEARCH)) {
			event.setComErrMsgVO((ComErrMsgVO)getVO(request, ComErrMsgVO.class));
		}

		return  event;
	}

	/**
	 * Save the business scenario execution result value in the attribute of HttpRequest<br>
	 * Set ResultSet in request, which transmits execution results from ServiceCommand to View (JSP)<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}


}