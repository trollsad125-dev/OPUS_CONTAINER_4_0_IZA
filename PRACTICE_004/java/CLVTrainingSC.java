/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLVTrainingSC.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2023.04.19
*@LastModifier : 
*@LastVersion : 1.0
* 2023.04.19 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.clt.apps.opus.esm.clv.clvtraining.clvpractice2.basic.CLVPractice2BC;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice2.basic.CLVPractice2BCImpl;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice2.event.ClvPractice002Event;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice2.vo.CodeMgmtCondVO;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice2.vo.CodeMgmtDTLVO;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.basic.CLVPractice4BC;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.basic.CLVPractice4BCImpl;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.event.ClvPractice004Event;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.vo.JooCarrierVO;
import com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.basic.ErrMsgMgmtBC;
import com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.basic.ErrMsgMgmtBCImpl;
import com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.event.ClvTraining001Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.vo.ComErrMsgVO;
import com.clt.apps.opus.fns.joo.training.joocarriermgmt.basic.JooCarrierMgmtBC;
import com.clt.apps.opus.fns.joo.training.joocarriermgmt.basic.JooCarrierMgmtBCImpl;
import com.clt.apps.opus.fns.joo.training.joocarriermgmt.event.FnsJoo0901Event;




/**
 * ALPS-CLVTraining Business Logic ServiceCommand 
 * 
 * @author Nhat Nguyen
 * @see ErrMsgMgmtDBDAO
 * @since J2EE 1.6
 */

public class CLVTrainingSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * Start SC
	 */
	public void doStart() {
		log.debug("CLVTrainingSC Start");
		try {
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * End SC
	 */
	public void doEnd() {
		log.debug("CLVTrainingSC End");
	}

	/**
	 * Perform
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;
		// Practice 1 event
		if (e.getEventName().equalsIgnoreCase("ClvTraining001Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchErrMsg(e);
			} else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = multiErrMsg(e);
			}
		} else if (e.getEventName().equalsIgnoreCase("ClvPractice004Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchJooCarrier(e);
			} else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = multiJooCarrier(e);
			} else if (e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = initRLaneCdnCrrCd(e);
			}else if (e.getFormCommand().isCommand(FormCommand.COMMAND01)) {
				eventResponse = chkDupDataJooCarrier(e);
			}else if (e.getFormCommand().isCommand(FormCommand.COMMAND02) 
					||e.getFormCommand().isCommand(FormCommand.COMMAND03)
					||e.getFormCommand().isCommand(FormCommand.COMMAND04)
					||e.getFormCommand().isCommand(FormCommand.COMMAND05)) {
				eventResponse = chkInvalid(e);
			}
		} else if (e.getEventName().equalsIgnoreCase("ClvPractice002Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH01)) {
				eventResponse = searchCodeMgmt(e);
			} else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = multiCodeMgmt(e);
			} else if (e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = getSystemCode(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH02)) {
				eventResponse = searchCodeDtl(e);
			}
		}
		return eventResponse;
	}
	/**
	 * Search Error Message
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchErrMsg(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvTraining001Event event = (ClvTraining001Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();

		try{
			List<ComErrMsgVO> list = command.searchErrMsg(event.getComErrMsgVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * Insert Update Delete Error Message
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse multiErrMsg(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvTraining001Event event = (ClvTraining001Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();
		try{
			begin();
			command.multiErrMsg(event.getComErrMsgVOS(),account);
			commit();
		} catch(EventException ex) {
			rollback();
			
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		return eventResponse;
	}
	/**
	 * CLV_Practice_004 :Search 
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchJooCarrier(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPractice004Event event = (ClvPractice004Event)e;
		CLVPractice4BC command = new CLVPractice4BCImpl();

		try{
			List<JooCarrierVO> list = command.searchJooCarrier(event.getJooCarrierVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * CLV_Practice_004 : Insert Update Delete
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse multiJooCarrier(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPractice004Event event = (ClvPractice004Event)e;
		CLVPractice4BC command = new CLVPractice4BCImpl();
		try{
			begin();
			command.multiJooCarrier(event.getJooCarrierVOS(),account);
			commit();
		} catch(EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		return eventResponse;
	}
	
	/**
	 * CLV_Practice_002 :Search Code Management
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchCodeMgmt(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPractice002Event event = (ClvPractice002Event)e;
		CLVPractice2BC command = new CLVPractice2BCImpl();

		try{
			List<CodeMgmtCondVO> list = command.searchCodeMgmt(event.getCodeMgmtCondVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	/**
	 * CLV_Practice_002 : Insert Update Delete Code Management
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	
	private EventResponse multiCodeMgmt(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPractice002Event event = (ClvPractice002Event)e;
		CLVPractice2BC command = new CLVPractice2BCImpl();
		
		try{
			begin();
			if(event.getCodeMgmtCondVOS()!=null){
				command.multiCodeMgmt(event.getCodeMgmtCondVOS(),account);
			}
			if(event.getCodeMgmtDTLVOs()!=null){
				command.multiCodeMgmtDtl(event.getCodeMgmtDTLVOs(), account);
			}
			
			eventResponse.setUserMessage(new ErrorHandler("XXXXXXXXX").getUserMessage());
			commit();
		} catch(EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		return eventResponse;
	}
	/**
	 * CLV_Practice_002 : Get Sub System Code
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse getSystemCode(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		CLVPractice2BC command = new CLVPractice2BCImpl();

		try{
			String[] list = command.searchSubSystemCodeList();
			Arrays.sort(list);
			eventResponse.setETCData("sub_sys_cd", Arrays.toString(list));
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * CLV_Practice_002 : Search Code Detail
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchCodeDtl(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPractice002Event event = (ClvPractice002Event)e;
		CLVPractice2BC command = new CLVPractice2BCImpl();

		try{
			List<CodeMgmtDTLVO> list = command.searchCodeMgmtDtl(event.getCodeMgmtDTLVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * This method for initial data
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse initRLaneCdnCrrCd(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPractice004Event event = (ClvPractice004Event)e;
		CLVPractice4BC command = new CLVPractice4BCImpl();

		try{
			List<JooCarrierVO> rlaneCds = command.searchRLaneCd(event.getJooCarrierVO());
			StringBuilder rlaneCdsBuilder = new StringBuilder();
			if(null != rlaneCds && rlaneCds.size() > 0){
				for(int i =0; i < rlaneCds.size(); i++){
					rlaneCdsBuilder.append(rlaneCds.get(i).getRlaneCd());
					if (i < rlaneCds.size() - 1){
						rlaneCdsBuilder.append("|");
					}	
				}
			}
			List<JooCarrierVO> crrCds = command.searchCrrCd(event.getJooCarrierVO());
			StringBuilder crrCdsBuilder = new StringBuilder();
			if(null != crrCds && crrCds.size() > 0){
				for(int i =0; i < crrCds.size(); i++){
					crrCdsBuilder.append(crrCds.get(i).getJoCrrCd());
					if (i < crrCds.size() - 1){
						crrCdsBuilder.append("|");
					}	
				}
			}
			eventResponse.setETCData("rlaneCds", rlaneCdsBuilder.toString());
			eventResponse.setETCData("crrCds", crrCdsBuilder.toString());
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * this method for checking invalid data
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse chkInvalid(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPractice004Event event = (ClvPractice004Event)e;
		CLVPractice4BC command = new CLVPractice4BCImpl();
		List<JooCarrierVO> list = null;
		try{
			if (e.getFormCommand().isCommand(FormCommand.COMMAND02)) {//check invalid carrier code
				list = command.searchCrrCd(event.getJooCarrierVO());
			}else if (e.getFormCommand().isCommand(FormCommand.COMMAND03)) {//check invalid vendor code
				list = command.searchVndrCd(event.getJooCarrierVO());
			}else if (e.getFormCommand().isCommand(FormCommand.COMMAND04)) {//check invalid customer code
				list = command.searchCusCd(event.getJooCarrierVO());
			}else if (e.getFormCommand().isCommand(FormCommand.COMMAND05)) {//check invalid trade code
				list = command.searchTradeCd(event.getJooCarrierVO());
			}
			if(null == list){
				list = new ArrayList<>();
			}
			eventResponse.setETCData("ISEXIST", list.size() > 0 ? "Y" : "N");
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}

	/**
	 * checking duplicate data Joo_CARRIER
	 * 
	 * @param Event
	 *            e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse chkDupDataJooCarrier(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPractice004Event event = (ClvPractice004Event) e;
		CLVPractice4BC command = new CLVPractice4BCImpl();

		try {
			List<JooCarrierVO> list = command.searchJooCarrier(event
					.getJooCarrierVO());
			if (null == list) {
				list = new ArrayList<>();
			}
			eventResponse.setETCData("ISEXIST", list.size() > 0 ? "Y" : "N");
		} catch (EventException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}
}