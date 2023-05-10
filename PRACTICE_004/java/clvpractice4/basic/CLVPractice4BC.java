/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLVPractice4BC.java
*@FileTitle : FW_Practice_004
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.08
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.08 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.basic;

import java.util.List;

import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.vo.JooCarrierVO;

/**
 * ALPS-Clvpractice4 Business Logic Command Interface<br>
 * - ALPS-Clvpractice4에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Nhat Nguyen
 * @since J2EE 1.6
 */

public interface CLVPractice4BC {

	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param JooCarrierVO	jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception EventException
	 */
	public List<JooCarrierVO> searchJooCarrier(JooCarrierVO jooCarrierVO) throws EventException;
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param JooCarrierVO[] jooCarrierVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void multiJooCarrier(JooCarrierVO[] jooCarrierVO,SignOnUserAccount account) throws EventException;
	/**
	 * = searching RLane Code list, it's used dropdown list
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception EventException
	 */
	public List<JooCarrierVO> searchRLaneCd(JooCarrierVO jooCarrierVO) throws EventException;
	/**
	 * searching Carrier Code list, it's used dropdownlist
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception EventException
	 */
	
	public List<JooCarrierVO> searchCrrCd(JooCarrierVO jooCarrierVO) throws EventException;
	/**
	 * searching customer Code list, it's used checking invalid data
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception EventException
	 */

	public List<JooCarrierVO> searchCusCd(JooCarrierVO jooCarrierVO) throws EventException;
	
	/**
	 *  searching trade Code list, it's used checking invalid data
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception EventException
	 */
	
	public List<JooCarrierVO> searchTradeCd(JooCarrierVO jooCarrierVO) throws EventException;
	/**
	 * searching vendor Code list, it's used checking invalid data
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception EventException
	 */
	
	public List<JooCarrierVO> searchVndrCd(JooCarrierVO jooCarrierVO) throws EventException;
}