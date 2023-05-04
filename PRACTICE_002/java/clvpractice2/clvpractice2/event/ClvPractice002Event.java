/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : ClvPractice002Event.java
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

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.esm.clv.clvpractice2.clvpractice2.vo.CodeMgmtCondVO;


/**
 * CLV_Practice_002 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  CLV_Practice_002HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Nhat Nguyen
 * @see CLV_Practice_002HTMLAction 참조
 * @since J2EE 1.6
 */

public class ClvPractice002Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	CodeMgmtCondVO codeMgmtCondVO = null;
	
	/** Table Value Object Multi Data 처리 */
	CodeMgmtCondVO[] codeMgmtCondVOs = null;

	public ClvPractice002Event(){}
	
	public void setCodeMgmtCondVO(CodeMgmtCondVO codeMgmtCondVO){
		this. codeMgmtCondVO = codeMgmtCondVO;
	}

	public void setCodeMgmtCondVOS(CodeMgmtCondVO[] codeMgmtCondVOs){
		this. codeMgmtCondVOs = codeMgmtCondVOs;
	}

	public CodeMgmtCondVO getCodeMgmtCondVO(){
		return codeMgmtCondVO;
	}

	public CodeMgmtCondVO[] getCodeMgmtCondVOS(){
		return codeMgmtCondVOs;
	}

}