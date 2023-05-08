/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLVPractice2BCImpl.java
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

import java.util.ArrayList;
import java.util.List;

import com.bluecast.util.DuplicateKeyException;
import com.clt.apps.opus.esm.clv.clvpractice2.clvpractice2.integration.CLVPractice2DBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.clvpractice2.clvpractice2.vo.CodeMgmtCondVO;
import com.clt.apps.opus.esm.clv.clvpractice2.clvpractice2.vo.CodeMgmtDTLVO;

/**
 * ALPS-CLVPractice2 Business Logic Command Interface<br>
 * - ALPS-CLVPractice2에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Nhat Nguyen
 * @since J2EE 1.6
 */
public class CLVPractice2BCImpl extends BasicCommandSupport implements CLVPractice2BC {

	// Database Access Object
	private transient CLVPractice2DBDAO dbDao = null;

	/**
	 * CLVPractice2BCImpl 객체 생성<br>
	 * CLVPractice2DBDAO를 생성한다.<br>
	 */
	public CLVPractice2BCImpl() {
		dbDao = new CLVPractice2DBDAO();
	}
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CodeMgmtCondVO codeMgmtCondVO
	 * @return List<CodeMgmtCondVO>
	 * @exception EventException
	 */
	public List<CodeMgmtCondVO> searchCodeMgmt(CodeMgmtCondVO codeMgmtCondVO) throws EventException {
		try {
			return dbDao.searchCodeMgmt(codeMgmtCondVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CodeMgmtCondVO[] codeMgmtCondVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void multiCodeMgmt(CodeMgmtCondVO[] codeMgmtCondVO, SignOnUserAccount account) throws EventException{
		try {
			String errFlg = "";
			String dupFlg = "";
			String intgCdId = "";
			List<CodeMgmtCondVO> insertVoList = new ArrayList<CodeMgmtCondVO>();
			List<CodeMgmtCondVO> updateVoList = new ArrayList<CodeMgmtCondVO>();
			List<CodeMgmtCondVO> deleteVoList = new ArrayList<CodeMgmtCondVO>();
			for ( int i=0; i<codeMgmtCondVO .length; i++ ) {
				if ( codeMgmtCondVO[i].getIbflag().equals("I")){
					codeMgmtCondVO[i].setCreUsrId(account.getUsr_id());
					insertVoList.add(codeMgmtCondVO[i]);
				} else if ( codeMgmtCondVO[i].getIbflag().equals("U")){
					codeMgmtCondVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(codeMgmtCondVO[i]);
				} else if ( codeMgmtCondVO[i].getIbflag().equals("D")){
					deleteVoList.add(codeMgmtCondVO[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				//Checking Duplication
				for( int i=0; i<insertVoList.size(); i++ ){
					dupFlg = dbDao.searchDupChkCodeMgmtCond(insertVoList.get(i));
					if ("Y".equals(dupFlg) ){
						errFlg = "Y";
						intgCdId = insertVoList.get(i).getIntgCdId();
					}
				}
				if( !"Y".equals(errFlg) ){
					dbDao.addmultiCodeMgmtS(insertVoList);
				}else{
					throw new DuplicateKeyException(new ErrorHandler("ERR12356",new String[]{intgCdId}).getMessage());
				}
				
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymultiCodeMgmtS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemultiCodeMgmtS(deleteVoList);
			}
		}catch(DuplicateKeyException de) {
			log.error("err " + de.toString(), de);
			throw new EventException(new ErrorHandler(de).getMessage(),de);
		} 
		catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	/**
	 * Search Sub System Cd in ComboBox Frontend
	 * 
	 * @param 
	 * @return String
	 * @exception EventException
	 */
	public String[] searchSubSystemCodeList() throws EventException {

		try {
			return dbDao.searchSubSystemCodeList();
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage());
		}
	}
	/**
	 * Search Search Code Detail
	 * 
	 * @param CodeMgmtDTLVO codeMgmtCondVO
	 * @return String
	 * @exception EventException
	 */
	@Override
	public List<CodeMgmtDTLVO> searchCodeMgmtDtl(CodeMgmtDTLVO codeMgmtDtlVO) throws EventException {
		try {
			return dbDao.searchCodeMgmtDtl(codeMgmtDtlVO);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage());
		}
	}
	/**
	 * Do Insert Delete Update 
	 * 
	 * @param CodeMgmtDTLVO[] codeMgmtDtlVO
	 * @param SignOnUserAccount account
	 * @exception EventException
	 */
	@Override
	public void multiCodeMgmtDtl(CodeMgmtDTLVO[] codeMgmtDtlVO, SignOnUserAccount account) throws EventException {
		try {
			String errFlg = "";
			String dupFlg = "";
			String intgCdId = "";
			List<CodeMgmtDTLVO> insertVoList = new ArrayList<CodeMgmtDTLVO>();
			List<CodeMgmtDTLVO> updateVoList = new ArrayList<CodeMgmtDTLVO>();
			List<CodeMgmtDTLVO> deleteVoList = new ArrayList<CodeMgmtDTLVO>();
			for ( int i=0; i<codeMgmtDtlVO .length; i++ ) {
				if ( codeMgmtDtlVO[i].getIbflag().equals("I")){
					insertVoList.add(codeMgmtDtlVO[i]);
				} else if ( codeMgmtDtlVO[i].getIbflag().equals("U")){
					updateVoList.add(codeMgmtDtlVO[i]);
				} else if ( codeMgmtDtlVO[i].getIbflag().equals("D")){
					deleteVoList.add(codeMgmtDtlVO[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				//Checking Duplication
				for( int i=0; i<insertVoList.size(); i++ ){
					dupFlg = dbDao.searchDupChkCodeMgmtDtl(insertVoList.get(i));
					if ("Y".equals(dupFlg) ){
						errFlg = "Y";
						intgCdId = insertVoList.get(i).getIntgCdValCtnt();
						break;
					}
				}
				if( !"Y".equals(errFlg) ){
					dbDao.addmultiCodeDtlS(insertVoList);
				}else{
					throw new DuplicateKeyException(new ErrorHandler("ERR12356",new String[]{intgCdId}).getMessage());
				}
				
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymultiCodeDtlS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemultiCodeDtlS(deleteVoList);
			}
		} 
		catch(DuplicateKeyException de) {
			log.error("err " + de.toString(), de);
			throw new EventException(new ErrorHandler(de).getMessage(),de);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
}