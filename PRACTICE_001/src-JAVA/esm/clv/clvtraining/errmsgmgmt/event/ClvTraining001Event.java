/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : ClvTraining001Event.java
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

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.syscommon.common.table.ComErrMsgVO;


/**
 * CLV_TRAINING_001 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  CLV_TRAINING_001HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Nhat Nguyen
 * @see CLV_TRAINING_001HTMLAction 참조
 * @since J2EE 1.6
 */

public class ClvTraining001Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	ComErrMsgVO comErrMsgVO = null;
	
	/** Table Value Object Multi Data 처리 */
	ComErrMsgVO[] comErrMsgVOs = null;

	public ClvTraining001Event(){}
	
	public void setComErrMsgVO(ComErrMsgVO comErrMsgVO){
		this. comErrMsgVO = comErrMsgVO;
	}

	public void setComErrMsgVOS(ComErrMsgVO[] comErrMsgVOs){
		this. comErrMsgVOs = comErrMsgVOs;
	}

	public ComErrMsgVO getComErrMsgVO(){
		return comErrMsgVO;
	}

	public ComErrMsgVO[] getComErrMsgVOS(){
		return comErrMsgVOs;
	}

}