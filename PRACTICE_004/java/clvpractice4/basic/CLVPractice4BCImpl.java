/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLVPractice4BCImpl.java
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

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.integration.CLVPractice4DBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.vo.JooCarrierVO;

/**
 * ALPS-CLVPractice4 Business Logic Command Interface<br>
 * - ALPS-CLVPractice4에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Nhat Nguyen
 * @since J2EE 1.6
 */
public class CLVPractice4BCImpl extends BasicCommandSupport implements CLVPractice4BC {

	// Database Access Object
	private transient CLVPractice4DBDAO dbDao = null;

	/**
	 * CLVPractice4BCImpl 객체 생성<br>
	 * CLVPractice4DBDAO를 생성한다.<br>
	 */
	public CLVPractice4BCImpl() {
		dbDao = new CLVPractice4DBDAO();
	}
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception EventException
	 */
	public List<JooCarrierVO> searchJooCarrier(JooCarrierVO jooCarrierVO) throws EventException {
		try {
			return dbDao.searchJooCarrier(jooCarrierVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param JooCarrierVO[] jooCarrierVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void multiJooCarrier(JooCarrierVO[] jooCarrierVO, SignOnUserAccount account) throws EventException{
		try {
			List<JooCarrierVO> insertVoList = new ArrayList<JooCarrierVO>();
			List<JooCarrierVO> updateVoList = new ArrayList<JooCarrierVO>();
			List<JooCarrierVO> deleteVoList = new ArrayList<JooCarrierVO>();
			for ( int i=0; i<jooCarrierVO .length; i++ ) {
				if ( jooCarrierVO[i].getIbflag().equals("I")){
					jooCarrierVO[i].setCreUsrId(account.getUsr_id());
					insertVoList.add(jooCarrierVO[i]);
				} else if ( jooCarrierVO[i].getIbflag().equals("U")){
					jooCarrierVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(jooCarrierVO[i]);
				} else if ( jooCarrierVO[i].getIbflag().equals("D")){
					deleteVoList.add(jooCarrierVO[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				dbDao.addmultiJooCarrierS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymultiJooCarrierS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemultiJooCarrierS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	/**
	 * = searching RLane Code list, it's used dropdown list
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception EventException
	 */
	@Override
	public List<JooCarrierVO> searchRLaneCd(JooCarrierVO jooCarrierVO) throws EventException {
		try {
			return dbDao.searchRLaneCd(jooCarrierVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	/**
	 * searching Carrier Code list, it's used dropdownlist
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception EventException
	 */
	@Override
	public List<JooCarrierVO> searchCrrCd(JooCarrierVO jooCarrierVO) throws EventException {
		try {
			return dbDao.searchCrrCd(jooCarrierVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	/**
	 * searching customer Code list, it's used checking invalid data
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception EventException
	 */
	@Override
	public List<JooCarrierVO> searchCusCd(JooCarrierVO jooCarrierVO) throws EventException {
		try {
			return dbDao.searchCusCd(jooCarrierVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	/**
	 * searching vendor Code list, it's used checking invalid data
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception EventException
	 */
	@Override
	public List<JooCarrierVO> searchVndrCd(JooCarrierVO jooCarrierVO) throws EventException {
		try {
			return dbDao.searchVndrCd(jooCarrierVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	/**
	 * searching trade Code list, it's used checking invalid data
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception EventException
	 */
	@Override
	public List<JooCarrierVO> searchTradeCd(JooCarrierVO jooCarrierVO) throws EventException {
		try {
			return dbDao.searchTradeCd(jooCarrierVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
}