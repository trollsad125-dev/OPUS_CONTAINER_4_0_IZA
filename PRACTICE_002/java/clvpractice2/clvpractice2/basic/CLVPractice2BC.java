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
package com.clt.apps.opus.esm.clv.clvpractice2.clvpractice2.basic;

import java.util.List;

import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.clvpractice2.clvpractice2.vo.CodeMgmtCondVO;
import com.clt.apps.opus.esm.clv.clvpractice2.clvpractice2.vo.CodeMgmtDTLVO;

/**
 * ALPS-Clvpractice2 Business Logic Command Interface<br>
 * - ALPS-Clvpractice2에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Nhat Nguyen
 * @since J2EE 1.6
 */

public interface CLVPractice2BC {

	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CodeMgmtCondVO	codeMgmtCondVO
	 * @return List<CodeMgmtCondVO>
	 * @exception EventException
	 */
	public List<CodeMgmtCondVO> searchCodeMgmt(CodeMgmtCondVO codeMgmtCondVO) throws EventException;
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CodeMgmtCondVO[] codeMgmtCondVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void multiCodeMgmt(CodeMgmtCondVO[] codeMgmtCondVO,SignOnUserAccount account) throws EventException;
	
	public String[] searchSubSystemCodeList() throws EventException;
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CodeMgmtDtlVO[] codeMgmtDtlVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public List<CodeMgmtDTLVO> searchCodeMgmtDtl(CodeMgmtDTLVO codeMgmtDtlVO) throws EventException;
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CodeMgmtDTLVO[] codeMgmtDtlVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void multiCodeMgmtDtl(CodeMgmtDTLVO[] codeMgmtDtlVO,SignOnUserAccount account) throws EventException;
	
}