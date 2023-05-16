/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLVPractice3DBDAO.java
*@FileTitle : CLV Practice 3
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.11
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.11 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.integration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.basic.CLVPractice3BCImpl;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.vo.DetailVO;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.vo.SummaryVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;



/**
 * ALPS CLVPractice3DBDAO <br>
 * - ALPS-CLVPractice3 system Business Logic을 처리하기 위한 JDBC 작업수행.<br>
 * 
 * @author Nhat Nguyen
 * @see CLVPractice3BCImpl 참조
 * @since J2EE 1.6
 */
public class CLVPractice3DBDAO extends DBDAOSupport {

	
	private static final long serialVersionUID = 1L;
	/**
	 * Search Joo Invoice Detail
	 * 
	 * @param DetailVO detailVO
	 * @return List<DetailVO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	public List<DetailVO> searchDetailVO(DetailVO detailVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<DetailVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(detailVO != null){
				Map<String, String> mapVO = detailVO .getColumnValues();
			
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CLVPractice3DBDAODetailVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, DetailVO .class);
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
	 * Search JOO_INVOICE 
	 * 
	 * @param SummaryVO summaryVO
	 * @return List<SummaryVO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	public List<SummaryVO> searchSummaryVO(SummaryVO summaryVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<SummaryVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(summaryVO != null){
				Map<String, String> mapVO = summaryVO .getColumnValues();
			
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CLVPractice3DBDAOSummaryVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, SummaryVO .class);
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
	  * search joo carrier code
	  * 
	  * @param JooCarrierVO jooCarrierVO
	  * @return List<JooCarrierVO>
	  * @exception DAOException
	  */
	 @SuppressWarnings("unchecked")
	 public List<SummaryVO> searchJooCrrCds(SummaryVO jooCarrierVO) throws DAOException {
		 DBRowSet dbRowset = null;
		 List<SummaryVO> list = new ArrayList<>();
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
			 dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CLVPractice3DBDAOSearchJooCrrCdsRSQL(), param, velParam);
			 list = (List)RowSetUtil.rowSetToVOs(dbRowset, SummaryVO .class);
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
	  * search Rev Lane code
	  * 
	  * @param JooCarrierVO jooCarrierVO
	  * @return List<JooCarrierVO>
	  * @exception DAOException
	  */
	 @SuppressWarnings("unchecked")
	 public List<SummaryVO> searchRevLaneCds(SummaryVO summaryVO) throws DAOException {
		 DBRowSet dbRowset = null;
		 List<SummaryVO> list = new ArrayList<>();
		 //query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		 
		 try{
			 if(summaryVO != null){
				Map<String, String> mapVO = summaryVO .getColumnValues();
				param.putAll(mapVO);
				
				velParam.putAll(mapVO);
				velParam.put("crrCds", Arrays.asList(summaryVO.getJoCrrCd().split(",")));
			 }
			 dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CLVPractice3DBDAOSearchRLaneCdRSQL(), param, velParam);
			 list = (List)RowSetUtil.rowSetToVOs(dbRowset, SummaryVO .class);
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
	  * search Trade code
	  * 
	  * @param JooCarrierVO jooCarrierVO
	  * @return List<JooCarrierVO>
	  * @exception DAOException
	  */
	 @SuppressWarnings("unchecked")
	 public List<SummaryVO> searchTradeCds(SummaryVO summaryVO) throws DAOException {
		 DBRowSet dbRowset = null;
		 List<SummaryVO> list = new ArrayList<>();
		 //query parameter
		 Map<String, Object> param = new HashMap<String, Object>();
		 //velocity parameter
		 Map<String, Object> velParam = new HashMap<String, Object>();
		 
		 try{
			 if(summaryVO != null){
				Map<String, String> mapVO = summaryVO .getColumnValues();
				param.putAll(mapVO);
				
				velParam.putAll(mapVO);
				velParam.put("crrCds", Arrays.asList(summaryVO.getJoCrrCd().split(",")));
				
			 }
			 dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CLVPractice3DBDAOSearchTrdCdsRSQL(), param, velParam);
			 list = (List)RowSetUtil.rowSetToVOs(dbRowset, SummaryVO .class);
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