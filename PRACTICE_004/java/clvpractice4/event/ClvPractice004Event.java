/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : ClvPractice004Event.java
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

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.vo.JooCarrierVO;


/**
 * CLV_Practice_004 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  CLV_Practice_004HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Nhat Nguyen
 * @see CLV_Practice_004HTMLAction 참조
 * @since J2EE 1.6
 */

public class ClvPractice004Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	JooCarrierVO jooCarrierVO = null;
	
	/** Table Value Object Multi Data 처리 */
	JooCarrierVO[] jooCarrierVOs = null;

	public ClvPractice004Event(){}
	
	public void setJooCarrierVO(JooCarrierVO jooCarrierVO){
		this. jooCarrierVO = jooCarrierVO;
	}

	public void setJooCarrierVOS(JooCarrierVO[] jooCarrierVOs){
		this. jooCarrierVOs = jooCarrierVOs;
	}

	public JooCarrierVO getJooCarrierVO(){
		return jooCarrierVO;
	}

	public JooCarrierVO[] getJooCarrierVOS(){
		return jooCarrierVOs;
	}

}