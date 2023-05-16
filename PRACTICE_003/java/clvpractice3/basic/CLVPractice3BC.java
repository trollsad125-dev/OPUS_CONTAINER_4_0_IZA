/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLVPractice3BC.java
*@FileTitle : CLV Practice 3
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.11
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.11 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.basic;

import java.util.List;





import com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.vo.DetailVO;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.vo.SummaryVO;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.vo.JooCarrierVO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

/**
 * ALPS-Clvpractice3 Business Logic Command Interface<br>
 * - ALPS-Clvpractice3에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Nhat Nguyen
 * @since J2EE 1.6
 */

public interface CLVPractice3BC {

	/**
	 * 
	 * 
	 * Search Joo Invoice Detail
	 * 
	 * @param DetailVO	detailVO
	 * @return List<DetailVO>
	 * @exception EventException
	 */
	public List<DetailVO> searchDetailVO(DetailVO detailVO) throws EventException;

	/**
	 * Search Joo Invoice 
	 * 
	 * @param SummaryVO	summaryVO
	 * @return List<SummaryVO>
	 * @exception EventException
	 */
	public List<SummaryVO> searchSummaryVO(SummaryVO summaryVO) throws EventException;
	
	/**
	 * Search Joo Carrier Code 
	 * 
	 * @param SummaryVO	summaryVO
	 * @return List<SummaryVO>
	 * @exception EventException
	 */
	public List<SummaryVO> searchJooCrrCds(SummaryVO summaryVO) throws EventException;
	
	/**
	 * Search Joo Rev Lane Code 
	 * 
	 * @param SummaryVO	summaryVO
	 * @return List<SummaryVO>
	 * @exception EventException
	 */
	public List<SummaryVO> searchRevLaneCds(SummaryVO summaryVO) throws EventException;

	/**
	 * Search Joo Trade Code 
	 * 
	 * @param SummaryVO	summaryVO
	 * @return List<SummaryVO>
	 * @exception EventException
	 */
	public List<SummaryVO> searchTrdCds(SummaryVO summaryVo) throws EventException;
}