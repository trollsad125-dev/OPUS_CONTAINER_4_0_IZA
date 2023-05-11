/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLV_PRACTICE_004HTMLAction.java
*@FileTitle : FW_Practice_004
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.08
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.08 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.vo.JooCarrierVO;

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.esm.clv.clvpractice4 Parsing the value of HTML DOM object sent to the server through the screen as a Java variable<br>
 * - Convert the parsed information into an event, put it in a request and request execution to CLVPractice4SC<br>
 * - Set EventResponse in request that transmits the execution result from CLVPractice4SC to View (JSP)<br>
 * @author Nhat Nguyen
 * @see CLVPractice4Event 참조
 * @since J2EE 1.6
 */

public class CLV_PRACTICE_004HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * CLV_PRACTICE_004HTMLAction 객체를 생성
	 */
	public CLV_PRACTICE_004HTMLAction() {}

	/**
	 * 
	 * Parsing Value of HTML DOM object into Java variable<br>
	 * Parse HttpRequest information into CLVPractice4Event and set it in request
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		ClvPractice004Event event = new ClvPractice004Event();
		
		if(command.isCommand(FormCommand.MULTI)) {
			event.setJooCarrierVOS((JooCarrierVO[])getVOs(request, JooCarrierVO .class,""));
		}
		else if(command.isCommand(FormCommand.SEARCH)) {
			JooCarrierVO carrierVO = new JooCarrierVO();
			carrierVO.setJoCrrCd(JSPUtil.getParameter(request, "s_jo_crr_cd", ""));
			carrierVO.setVndrSeq(JSPUtil.getParameter(request, "s_vndr_seq", ""));
			carrierVO.setCreDtFr(JSPUtil.getParameter(request, "s_cre_dt_fm", ""));
			carrierVO.setCreDtTo(JSPUtil.getParameter(request, "s_cre_dt_to", ""));
			event.setJooCarrierVO(carrierVO);
		}else if(command.isCommand(FormCommand.COMMAND01)) {//check duplicate data
			event.setJooCarrierVO((JooCarrierVO)getVO(request, JooCarrierVO .class,""));
		}else if(command.isCommand(FormCommand.COMMAND02) || command.isCommand(FormCommand.COMMAND03) 
				|| command.isCommand(FormCommand.COMMAND04) || command.isCommand(FormCommand.COMMAND05)) {//check invalid carrrier code
			event.setJooCarrierVO((JooCarrierVO)getVO(request, JooCarrierVO .class,""));
		}
		return  event;
	}

	/**
	 * Save the business scenario execution result value in the attribute of HttpRequest<br>
	 * Set ResultSet in request, which transmits execution results from ServiceCommand to View (JSP)<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * Save the result of parsing HttpRequest in the attribute of HttpRequest<br>
	 * HttpRequest parsing result value set in request<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event 
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}