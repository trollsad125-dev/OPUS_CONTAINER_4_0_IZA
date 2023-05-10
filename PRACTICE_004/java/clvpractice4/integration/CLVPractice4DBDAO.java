/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLVPractice4DBDAO.java
*@FileTitle : FW_Practice_004
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.08
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.08 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.basic.CLVPractice4BCImpl;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.vo.JooCarrierVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;


/**
 * ALPS CLVPractice4DBDAO <br>
 * - ALPS-CLVPractice4 system Business Logic을 처리하기 위한 JDBC 작업수행.<br>
 * 
 * @author Nhat Nguyen
 * @see CLVPractice4BCImpl 참조
 * @since J2EE 1.6
 */
public class CLVPractice4DBDAO extends DBDAOSupport {

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	public List<JooCarrierVO> searchJooCarrier(JooCarrierVO jooCarrierVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<JooCarrierVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(jooCarrierVO != null){
				Map<String, String> mapVO = jooCarrierVO .getColumnValues();
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
				velParam.put("crrCds", Arrays.asList(jooCarrierVO.getJoCrrCd().split(",")));
				
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CLVPractice4DBDAOJooCarrierVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, JooCarrierVO .class);
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @exception DAOException
	 * @exception Exception
	 */
	public void addmultiJooCarrier(JooCarrierVO jooCarrierVO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		try {
			Map<String, String> mapVO = jooCarrierVO .getColumnValues();
			
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			
			SQLExecuter sqlExe = new SQLExecuter("");
			int result = sqlExe.executeUpdate((ISQLTemplate)new CLVPractice4DBDAOJooCarrierVOCSQL(), param, velParam);
			if(result == Statement.EXECUTE_FAILED)
					throw new DAOException("Fail to insert SQL");
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
	}
	
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	public int modifymultiJooCarrier(JooCarrierVO jooCarrierVO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		int result = 0;
		try {
			Map<String, String> mapVO = jooCarrierVO .getColumnValues();
			
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			
			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate((ISQLTemplate)new CLVPractice4DBDAOJooCarrierVOUSQL(), param, velParam);
			if(result == Statement.EXECUTE_FAILED)
					throw new DAOException("Fail to insert SQL");
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return result;
	}
	
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	public int removemultiJooCarrier(JooCarrierVO jooCarrierVO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		int result = 0;
		try {
			Map<String, String> mapVO = jooCarrierVO .getColumnValues();
			
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			
			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate((ISQLTemplate)new CLVPractice4DBDAOJooCarrierVODSQL(), param, velParam);
			if(result == Statement.EXECUTE_FAILED)
					throw new DAOException("Fail to insert SQL");
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return result;
	}

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List<JooCarrierVO> jooCarrierVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addmultiJooCarrierS(List<JooCarrierVO> jooCarrierVO) throws DAOException,Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(jooCarrierVO .size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new CLVPractice4DBDAOJooCarrierVOCSQL(), jooCarrierVO,null);
				for(int i = 0; i < insCnt.length; i++){
					if(insCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List<JooCarrierVO> jooCarrierVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] modifymultiJooCarrierS(List<JooCarrierVO> jooCarrierVO) throws DAOException,Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(jooCarrierVO .size() > 0){
				updCnt = sqlExe.executeBatch((ISQLTemplate)new CLVPractice4DBDAOJooCarrierVOUSQL(), jooCarrierVO,null);
				for(int i = 0; i < updCnt.length; i++){
					if(updCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}
	
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List<JooCarrierVO> jooCarrierVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] removemultiJooCarrierS(List<JooCarrierVO> jooCarrierVO) throws DAOException,Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(jooCarrierVO .size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new CLVPractice4DBDAOJooCarrierVODSQL(), jooCarrierVO,null);
				for(int i = 0; i < delCnt.length; i++){
					if(delCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}
	/**
	 * Search Rev Lane Cd 
	 * 
	 * @param JooCarrierVO jooCarrierVO
	 * @return List<JooCarrierVO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	public List<JooCarrierVO> searchRLaneCd(JooCarrierVO jooCarrierVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<JooCarrierVO> list = new ArrayList();

		try{
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CLVPractice4DBDAOSearchRLandCdRSQL(), null, null);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, JooCarrierVO .class);
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	 /**
	  * search carrier code
	  * 
	  * @param JooCarrierVO jooCarrierVO
	  * @return List<JooCarrierVO>
	  * @exception DAOException
	  */
	 @SuppressWarnings("unchecked")
	 public List<JooCarrierVO> searchCrrCd(JooCarrierVO jooCarrierVO) throws DAOException {
		 DBRowSet dbRowset = null;
		 List<JooCarrierVO> list = new ArrayList();
		 //query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		 
		 try{
			 if(jooCarrierVO != null){
				Map<String, String> mapVO = jooCarrierVO .getColumnValues();
				param.putAll(mapVO);
				
				velParam.putAll(mapVO);
			 }
			 dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CLVPractice4DBDAOSearchCrrCdRSQL(), param, velParam);
			 list = (List)RowSetUtil.rowSetToVOs(dbRowset, JooCarrierVO .class);
		 } catch(SQLException se) {
			 log.error(se.getMessage(),se);
			 throw new DAOException(new ErrorHandler(se).getMessage());
		 } catch(Exception ex) {
			 log.error(ex.getMessage(),ex);
			 throw new DAOException(new ErrorHandler(ex).getMessage());
		 }
		 return list;
	 }
	 /**
	  * search customer code
	  * 
	  * @param JooCarrierVO jooCarrierVO
	  * @return List<JooCarrierVO>
	  * @exception DAOException
	  */
	 @SuppressWarnings("unchecked")
	 public List<JooCarrierVO> searchCusCd(JooCarrierVO jooCarrierVO) throws DAOException {
		 DBRowSet dbRowset = null;
		 List<JooCarrierVO> list = new ArrayList();
		 //query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		 
		 try{
			 if(jooCarrierVO != null){
				Map<String, String> mapVO = jooCarrierVO .getColumnValues();
				param.putAll(mapVO);
				
				velParam.putAll(mapVO);
			 }
			 dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CLVPractice4DBDAOSearchCustCdRSQL(), param, velParam);
			 list = (List)RowSetUtil.rowSetToVOs(dbRowset, JooCarrierVO .class);
		 } catch(SQLException se) {
			 log.error(se.getMessage(),se);
			 throw new DAOException(new ErrorHandler(se).getMessage());
		 } catch(Exception ex) {
			 log.error(ex.getMessage(),ex);
			 throw new DAOException(new ErrorHandler(ex).getMessage());
		 }
		 return list;
	 }
	 /**
	  * search trade code
	  * 
	  * @param JooCarrierVO jooCarrierVO
	  * @return List<JooCarrierVO>
	  * @exception DAOException
	  */
	 @SuppressWarnings("unchecked")
	 public List<JooCarrierVO> searchTradeCd(JooCarrierVO jooCarrierVO) throws DAOException {
		 DBRowSet dbRowset = null;
		 List<JooCarrierVO> list = new ArrayList();
		 //query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		 
		 try{
			 if(jooCarrierVO != null){
				 Map<String, String> mapVO = jooCarrierVO .getColumnValues();
				 param.putAll(mapVO);
				 
				 velParam.putAll(mapVO);
			 }
			 dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CLVPractice4DBDAOSearchTradeCdRSQL(), param, velParam);
			 list = (List)RowSetUtil.rowSetToVOs(dbRowset, JooCarrierVO .class);
		 } catch(SQLException se) {
			 log.error(se.getMessage(),se);
			 throw new DAOException(new ErrorHandler(se).getMessage());
		 } catch(Exception ex) {
			 log.error(ex.getMessage(),ex);
			 throw new DAOException(new ErrorHandler(ex).getMessage());
		 }
		 return list;
	 }
	 /**
	  * search vendor code
	  * 
	  * @param JooCarrierVO jooCarrierVO
	  * @return List<JooCarrierVO>
	  * @exception DAOException
	  */
	 @SuppressWarnings("unchecked")
	 public List<JooCarrierVO> searchVndrCd(JooCarrierVO jooCarrierVO) throws DAOException {
		 DBRowSet dbRowset = null;
		 List<JooCarrierVO> list = new ArrayList();
		 //query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		 
		 try{
			 if(jooCarrierVO != null){
				Map<String, String> mapVO = jooCarrierVO .getColumnValues();
				param.putAll(mapVO);
				
				velParam.putAll(mapVO);
			 }
			 dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CLVPractice4DBDAOSearchVendorCdRSQL(), param, velParam);
			 list = (List)RowSetUtil.rowSetToVOs(dbRowset, JooCarrierVO .class);
		 } catch(SQLException se) {
			 log.error(se.getMessage(),se);
			 throw new DAOException(new ErrorHandler(se).getMessage());
		 } catch(Exception ex) {
			 log.error(ex.getMessage(),ex);
			 throw new DAOException(new ErrorHandler(ex).getMessage());
		 }
		 return list;
	 }
}