/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLVPractice3BCImpl.java
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

import com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.integration.CLVPractice3DBDAO;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.vo.DetailVO;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.vo.SummaryVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;

/**
 * ALPS-CLVPractice3 Business Logic Command Interface<br>
 * - ALPS-CLVPractice3에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Nhat Nguyen
 * @since J2EE 1.6
 */
public class CLVPractice3BCImpl extends BasicCommandSupport implements CLVPractice3BC {

	// Database Access Object
	private transient CLVPractice3DBDAO dbDao = null;

	/**
	 * CLVPractice3BCImpl 객체 생성<br>
	 * CLVPractice3DBDAO를 생성한다.<br>
	 */
	public CLVPractice3BCImpl() {
		dbDao = new CLVPractice3DBDAO();
	}
	/**
	 * Search Joo Invoice
	 * 
	 * @param DetailVO detailVO
	 * @return List<DetailVO>
	 * @exception EventException
	 */
	public List<DetailVO> searchDetailVO(DetailVO detailVO) throws EventException {
		try {
			return dbDao.searchDetailVO(detailVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * Search Joo Invoice Detail
	 * 
	 * @param SummaryVO summaryVO
	 * @return List<SummaryVO>
	 * @exception EventException
	 */
	public List<SummaryVO> searchSummaryVO(SummaryVO summaryVO) throws EventException {
		try {
			return dbDao.searchSummaryVO(summaryVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	

	/**
	 * searching Joo Carrier Code list, it's used dropdownlist
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception EventException
	 */
	@Override
	public List<SummaryVO> searchJooCrrCds(SummaryVO summaryVo) throws EventException {
		try {
			return dbDao.searchJooCrrCds(summaryVo);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	/**
	 * searching Rev Lane Code list
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception EventException
	 */
	@Override
	public List<SummaryVO> searchRevLaneCds(SummaryVO summaryVo) throws EventException {
		try {
			return dbDao.searchRevLaneCds(summaryVo);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	/**
	 * searching Trade Code list
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception EventException
	 */
	@Override
	public List<SummaryVO> searchTrdCds(SummaryVO summaryVo) throws EventException {
		try {
			return dbDao.searchTradeCds(summaryVo);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
}