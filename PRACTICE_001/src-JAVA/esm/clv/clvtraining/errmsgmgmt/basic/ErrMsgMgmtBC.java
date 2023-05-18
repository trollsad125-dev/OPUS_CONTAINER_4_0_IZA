/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : ErrMsgMgmtBC.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2023.04.19
*@LastModifier : 
*@LastVersion : 1.0
* 2023.04.19 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.basic;

import java.util.List;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.vo.ComErrMsgVO;

/**
 * ALPS-Clvtraining Business Logic Command Interface<br>
 * - ALPS-Clvtraining에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Nhat Nguyen
 * @since J2EE 1.6
 */

public interface ErrMsgMgmtBC {

	/**
	 * Search Error Message
	 * 
	 * @param ComErrMsgVO	comErrMsgVO
	 * @return List<ComErrMsgVO>
	 * @exception EventException
	 */
	public List<ComErrMsgVO> searchErrMsg(ComErrMsgVO comErrMsgVO) throws EventException;
	
	/**
	 * Insert Delete Update Error Message
	 * 
	 * @param ComErrMsgVO[] comErrMsgVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void multiErrMsg(ComErrMsgVO[] comErrMsgVO,SignOnUserAccount account) throws EventException;
}