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

	private transient ErrMsgMgmtDBDAO dbDao = null;


	public ErrMsgMgmtBCImpl() {
		dbDao = new ErrMsgMgmtDBDAO();
	}
	/**
	 * Search
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
	 * Insert - Delete - Update 
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
					comErrMsgVO[i].setCreUsrId(account.getUsr_id());
 					comErrMsgVO[i].setUpdUsrId(account.getUsr_id());
					insertVoList.add(comErrMsgVO[i]);
				} else if ( comErrMsgVO[i].getIbflag().equals("U")){
					comErrMsgVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(comErrMsgVO[i]);
				} else if ( comErrMsgVO[i].getIbflag().equals("D")){
					deleteVoList.add(comErrMsgVO[i]);
				}
			}
			String dupFlg="";
			if ( insertVoList.size() > 0 ) {
				for (int i = 0; i < insertVoList.size(); i++) {
					dupFlg = dbDao.searchDupChkErrCd(insertVoList.get(i));
					String errCode = insertVoList.get(i).getErrMsgCd();
					if ("1".equalsIgnoreCase(dupFlg)) {
						// Throw DAOException with ERR12356
						throw new DAOException(new ErrorHandler("ERR12356",new String[] { errCode }).getMessage());
					}
				}
				//Check Duplication Error Message
				if(!"1".equalsIgnoreCase(dupFlg)){
					dbDao.addMultiErrMsgS(insertVoList);
				}
			}
			
			if ( updateVoList.size() > 0 ) {	
				dbDao.modifyMultiErrMsgS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removeMultiErrMsgS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
}