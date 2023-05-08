/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLV_PRACTICE_002HTMLAction.java
*@FileTitle : CLV Practice 2
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.04
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.04 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvpractice2.clvpractice2.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.apps.opus.esm.clv.clvpractice2.clvpractice2.vo.CodeMgmtCondVO;
import com.clt.apps.opus.esm.clv.clvpractice2.clvpractice2.vo.CodeMgmtDTLVO;

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.esm.clv.clvpractice2 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 CLVPractice2SC로 실행요청<br>
 * - CLVPractice2SC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * @author Nhat Nguyen
 * @see CLVPractice2Event 참조
 * @since J2EE 1.6
 */

public class CLV_PRACTICE_002HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * CLV_PRACTICE_002HTMLAction 객체를 생성
	 */
	public CLV_PRACTICE_002HTMLAction() {}

	/**
	 * HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
	 * HttpRequst의 정보를 CLVPractice2Event로 파싱하여 request에 셋팅<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		ClvPractice002Event event = new ClvPractice002Event();
		
		if(command.isCommand(FormCommand.MULTI)) {
			event.setCodeMgmtCondVOS((CodeMgmtCondVO[])getVOs(request, CodeMgmtCondVO.class,"sheet1_"));
			event.setCodeMgmtDTLVOs((CodeMgmtDTLVO[])getVOs(request, CodeMgmtDTLVO.class,"sheet2_"));
		}
		else if(command.isCommand(FormCommand.SEARCH01)) {
			event.setCodeMgmtCondVO((CodeMgmtCondVO)getVO(request, CodeMgmtCondVO.class));
		}else if(command.isCommand(FormCommand.SEARCH02)){
			event.setCodeMgmtDTLVO((CodeMgmtDTLVO)getVO(request, CodeMgmtDTLVO.class));
		}

		return  event;
	}

	/**
	 * HttpRequest의 attribute에 업무시나리오 수행결과 값 저장<br>
	 * ServiceCommand에서 View(JSP)로 실행결과를 전송하는 ResultSet을 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * HttpRequest의 attribute에 HttpRequest 파싱 수행결과 값 저장<br>
	 * HttpRequest 파싱 수행결과 값 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}