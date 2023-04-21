/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : ErrMsgMgmtBCImpl.java
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

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.integration.ErrMsgMgmtDBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.vo.ComErrMsgVO;

/**
 * ALPS-CLVTraining Business Logic Command Interface<br>
 * - ALPS-CLVTraining에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Nhat Nguyen
 * @since J2EE 1.6
 */
public class ErrMsgMgmtBCImpl extends BasicCommandSupport implements ErrMsgMgmtBC {

	// Database Access Object
	private transient ErrMsgMgmtDBDAO dbDao = null;

	/**
	 * ErrMsgMgmtBCImpl 객체 생성<br>
	 * ErrMsgMgmtDBDAO를 생성한다.<br>
	 */
	public ErrMsgMgmtBCImpl() {
		dbDao = new ErrMsgMgmtDBDAO();
	}
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param ComErrMsgVO comErrMsgVO
	 * @return List<ComErrMsgVO>
	 * @exception EventException
	 */
	public List<ComErrMsgVO> searchErrMsg(ComErrMsgVO comErrMsgVO) throws EventException {
		try {
			return dbDao.searchErrMsg(comErrMsgVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param ComErrMsgVO[] comErrMsgVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void multiErrMsg(ComErrMsgVO[] comErrMsgVO, SignOnUserAccount account) throws EventException{
		try {
			List<ComErrMsgVO> insertVoList = new ArrayList<ComErrMsgVO>();
			List<ComErrMsgVO> updateVoList = new ArrayList<ComErrMsgVO>();
			List<ComErrMsgVO> deleteVoList = new ArrayList<ComErrMsgVO>();
			for ( int i=0; i<comErrMsgVO .length; i++ ) {
				if ( comErrMsgVO[i].getIbflag().equals("I")){
 					comErrMsgVO[i].setUpdUsrId(account.getUsr_id());
					insertVoList.add(comErrMsgVO[i]);
				} else if ( comErrMsgVO[i].getIbflag().equals("U")){
					comErrMsgVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(comErrMsgVO[i]);
				} else if ( comErrMsgVO[i].getIbflag().equals("D")){
					deleteVoList.add(comErrMsgVO[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				dbDao.addmultiErrMsgS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {	
				dbDao.modifymultiErrMsgS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemultiErrMsgS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
}