/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : ClvPractice003Event.java
*@FileTitle : CLV Practice 3
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.11
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.11 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.event;

import com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.vo.DetailVO;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.vo.SummaryVO;
import com.clt.framework.support.layer.event.EventSupport;


/**
 * CLV_PRACTICE_003 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  CLV_PRACTICE_003HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Nhat Nguyen
 * @see CLV_PRACTICE_003HTMLAction 참조
 * @since J2EE 1.6
 */

public class ClvPractice003Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	SummaryVO summaryVO = null;
	
	/** Table Value Object Multi Data 처리 */
	SummaryVO[] summaryVOs = null;

	/** Table Value Object 조회 조건 및 단건 처리  */
	DetailVO detailVO = null;
	
	/** Table Value Object Multi Data 처리 */
	DetailVO[] detailVOs = null;

	public ClvPractice003Event(){}
	
	public void setSummaryVO(SummaryVO summaryVO){
		this. summaryVO = summaryVO;
	}

	public void setSummaryVOS(SummaryVO[] summaryVOs){
		this. summaryVOs = summaryVOs;
	}

	public void setDetailVO(DetailVO detailVO){
		this. detailVO = detailVO;
	}

	public void setDetailVOS(DetailVO[] detailVOs){
		this. detailVOs = detailVOs;
	}

	public SummaryVO getSummaryVO(){
		return summaryVO;
	}

	public SummaryVO[] getSummaryVOS(){
		return summaryVOs;
	}

	public DetailVO getDetailVO(){
		return detailVO;
	}

	public DetailVO[] getDetailVOS(){
		return detailVOs;
	}

}