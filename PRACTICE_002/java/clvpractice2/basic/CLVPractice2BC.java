/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLVPractice2BC.java
*@FileTitle : CLV Practice 2
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.04
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.04 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.clvpractice2.basic;

import java.util.List;

import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice2.vo.CodeMgmtCondVO;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice2.vo.CodeMgmtDTLVO;

/**
 * ALPS-Clvpractice2 Business Logic Command Interface<br>
 * - ALPS-Clvpractice2에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Nhat Nguyen
 * @since J2EE 1.6
 */

public interface CLVPractice2BC {

	/**
	 * Search Code Master
	 * 
	 * @param CodeMgmtCondVO	codeMgmtCondVO
	 * @return List<CodeMgmtCondVO>
	 * @exception EventException
	 */
	public List<CodeMgmtCondVO> searchCodeMgmt(CodeMgmtCondVO codeMgmtCondVO) throws EventException;
	
	/**
	 * Insert Delete Update Code Master
	 * 
	 * @param CodeMgmtCondVO[] codeMgmtCondVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void multiCodeMgmt(CodeMgmtCondVO[] codeMgmtCondVO,SignOnUserAccount account) throws EventException;
	
	/**
	 * Search Sub System Code in 
	 * @return
	 * @exception EventException
	 */
	public String[] searchSubSystemCodeList() throws EventException;
	/**
	 * Search Code Detail
	 * 
	 * @param CodeMgmtDtlVO[] codeMgmtDtlVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public List<CodeMgmtDTLVO> searchCodeMgmtDtl(CodeMgmtDTLVO codeMgmtDtlVO) throws EventException;
	
	/**
	 * Insert Delete Update Code Detail
	 * 
	 * @param CodeMgmtDTLVO[] codeMgmtDtlVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void multiCodeMgmtDtl(CodeMgmtDTLVO[] codeMgmtDtlVO,SignOnUserAccount account) throws EventException;
	
	/**
	 * Check Duplicate Code Master
	 * 
	 * @param CodeMgmtCondVO	codeMgmtCondVO
	 * @return List<CodeMgmtCondVO>
	 * @exception EventException
	 */
	public String chkDuplCodeMgmt(CodeMgmtCondVO codeMgmtCondVO) throws EventException;
	
}