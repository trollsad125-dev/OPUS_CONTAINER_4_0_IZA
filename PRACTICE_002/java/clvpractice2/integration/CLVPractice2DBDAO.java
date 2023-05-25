/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLVPractice2DBDAO.java
*@FileTitle : CLV Practice 2
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.04
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.04 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.clvpractice2.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.clvtraining.clvpractice2.basic.CLVPractice2BCImpl;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice2.vo.CodeMgmtCondVO;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice2.vo.CodeMgmtDTLVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;


/**
 * ALPS CLVPractice2DBDAO <br>
 * - ALPS-CLVPractice2 system Business Logic을 처리하기 위한 JDBC 작업수행.<br>
 * 
 * @author Nhat Nguyen
 * @see CLVPractice2BCImpl 참조
 * @since J2EE 1.6
 */
public class CLVPractice2DBDAO extends DBDAOSupport {

	/**
	 * Search Code Management
	 * 
	 * @param CodeMgmtCondVO codeMgmtCondVO
	 * @return List<CodeMgmtCondVO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	public List<CodeMgmtCondVO> searchCodeMgmt(CodeMgmtCondVO codeMgmtCondVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CodeMgmtCondVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(codeMgmtCondVO != null){
				Map<String, String> mapVO = codeMgmtCondVO .getColumnValues();
			
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CLVPractice2DBDAOCodeMgmtCondVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CodeMgmtCondVO .class);
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
	 * Add Code Management
	 * 
	 * @param CodeMgmtCondVO codeMgmtCondVO
	 * @exception DAOException
	 * @exception Exception
	 */
	public void addmultiCodeMgmt(CodeMgmtCondVO codeMgmtCondVO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		try {
			Map<String, String> mapVO = codeMgmtCondVO .getColumnValues();
			
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			
			SQLExecuter sqlExe = new SQLExecuter("");
			int result = sqlExe.executeUpdate((ISQLTemplate)new CLVPractice2DBDAOCodeMgmtCondVOCSQL(), param, velParam);
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
	 * Update Code Management
	 * 
	 * @param CodeMgmtCondVO codeMgmtCondVO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	public int modifymultiCodeMgmt(CodeMgmtCondVO codeMgmtCondVO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		int result = 0;
		try {
			Map<String, String> mapVO = codeMgmtCondVO .getColumnValues();
			
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			
			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate((ISQLTemplate)new CLVPractice2DBDAOCodeMgmtCondVOUSQL(), param, velParam);
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
	 * Delete Code Management
	 * 
	 * @param CodeMgmtCondVO codeMgmtCondVO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	public int removemultiCodeMgmt(CodeMgmtCondVO codeMgmtCondVO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		int result = 0;
		try {
			Map<String, String> mapVO = codeMgmtCondVO .getColumnValues();
			
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			
			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate((ISQLTemplate)new CLVPractice2DBDAOCodeMgmtCondVODSQL(), param, velParam);
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
	 * Insert Array Code Management
	 * 
	 * @param List<CodeMgmtCondVO> codeMgmtCondVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addmultiCodeMgmtS(List<CodeMgmtCondVO> codeMgmtCondVO) throws DAOException,Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(codeMgmtCondVO .size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new CLVPractice2DBDAOCodeMgmtCondVOCSQL(), codeMgmtCondVO,null);
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
	 * Update Array Code Management
	 * 
	 * @param List<CodeMgmtCondVO> codeMgmtCondVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] modifymultiCodeMgmtS(List<CodeMgmtCondVO> codeMgmtCondVO) throws DAOException,Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(codeMgmtCondVO .size() > 0){
				updCnt = sqlExe.executeBatch((ISQLTemplate)new CLVPractice2DBDAOCodeMgmtCondVOUSQL(), codeMgmtCondVO,null);
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
	 * Delete Array Code Management
	 * 
	 * @param List<CodeMgmtCondVO> codeMgmtCondVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] removemultiCodeMgmtS(List<CodeMgmtCondVO> codeMgmtCondVO) throws DAOException,Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(codeMgmtCondVO .size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new CLVPractice2DBDAOCodeMgmtCondVODSQL(), codeMgmtCondVO,null);
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
	 * Search Sub System Code List
	 *
	 * @return String[]
	 * @exception SQLException
	 * @exception DAOException
	 * @exception Exception
	 */
	public String[] searchSubSystemCodeList() throws DAOException {
		String[] list = null;
		try {
			DBRowSet dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new CLVPractice2DBDAOsearchSubSystemCdRSQL(), null, null);
			list = new String[dbRowset.getRowCount()];
			int idx = 0;
			while(dbRowset.next()){
				list[idx++] = dbRowset.getString("sub_sys_cd");
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	/**
	 * Search Code Management Detail
	 * 
	 * @param CodeMgmtDTLVO codeMgmtDtlVO
	 * @return String[]
	 * @exception SQLException
	 * @exception DAOException
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CodeMgmtDTLVO> searchCodeMgmtDtl(CodeMgmtDTLVO codeMgmtDtlVO) throws DAOException {
			DBRowSet dbRowset = null;
			List<CodeMgmtDTLVO> list = null;
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();

			try{
				if(codeMgmtDtlVO != null){
					Map<String, String> mapVO = codeMgmtDtlVO .getColumnValues();
				
					param.putAll(mapVO);
					velParam.putAll(mapVO);
				}
				dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CLVPractice2DBDAOCodeMgmtDTLVORSQL(), param, null);
				list = (List)RowSetUtil.rowSetToVOs(dbRowset, CodeMgmtDTLVO.class);
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
	 * Insert Array Code Management Detail
	 * 
	 * @param List <CodeMgmtCondVO> codeMgmtCondVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addmultiCodeDtlS(List<CodeMgmtDTLVO> codeMgmtDtlVO)
			throws DAOException, Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (codeMgmtDtlVO.size() > 0) {
				insCnt = sqlExe.executeBatch((ISQLTemplate) new CLVPractice2DBDAOCodeMgmtDTLVOCSQL(),codeMgmtDtlVO, null);
				for (int i = 0; i < insCnt.length; i++) {
					if (insCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No" + i + " SQL");
				}
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}

	/**
	 * Update Code Management Detail
	 * 
	 * @param List <CodeMgmtCondVO> codeMgmtCondVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] modifymultiCodeDtlS(List<CodeMgmtDTLVO> codeMgmtDtlVO) throws DAOException, Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (codeMgmtDtlVO.size() > 0) {
				updCnt = sqlExe.executeBatch((ISQLTemplate) new CLVPractice2DBDAOCodeMgmtDTLVOUSQL(),codeMgmtDtlVO, null);
				for (int i = 0; i < updCnt.length; i++) {
					if (updCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No" + i + " SQL");
				}
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}

	/**
	 * Delete Code Management Detail
	 * 
	 * @param List <CodeMgmtCondVO> codeMgmtCondVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] removemultiCodeDtlS(List<CodeMgmtDTLVO> codeMgmtDtlVO)
			throws DAOException, Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if (codeMgmtDtlVO.size() > 0) {
				delCnt = sqlExe
						.executeBatch(
								(ISQLTemplate) new CLVPractice2DBDAOCodeMgmtDTLVODSQL(),
								codeMgmtDtlVO, null);
				for (int i = 0; i < delCnt.length; i++) {
					if (delCnt[i] == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No" + i + " SQL");
				}
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}

	/**
	 * Search Duplicate Code in COM_INTG_CD
	 * 
	 * @param CodeMgmtCondVO inputVO
	 * @return String
	 * @throws DAOException
	 * @exception SQLException
	 * @exception DAOException
	 * @exception Exception
	 * 
	 */
	public String searchDupChkCodeMgmtCond(CodeMgmtCondVO inputVO) throws DAOException {
		DBRowSet dbRowset = null;
		String dupFlg = "";
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (inputVO != null) {
				Map<String, String> mapVO = inputVO.getColumnValues();

				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("")
					.executeQuery(
							(ISQLTemplate) new CLVPractice2DBDAOCheckDuplicateRSQL(),
							param, velParam);
			if (dbRowset != null && dbRowset.next()) {
				dupFlg = dbRowset.getString(1);
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}

		return dupFlg;
	}

	/**
	 * Search Duplicate Code in COM_INTG_CD_DTL
	 * 
	 * @param CodeMgmtCondVO inputVO
	 * @return String
	 * @throws DAOException
	 * @exception SQLException
	 * @exception DAOException
	 * @exception Exception
	 * 
	 */
	public String searchDupChkCodeMgmtDtl(CodeMgmtDTLVO inputVO) throws DAOException {
		DBRowSet dbRowset = null;
		String dupFlg = "";
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (inputVO != null) {
				Map<String, String> mapVO = inputVO.getColumnValues();

				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("")
					.executeQuery(
							(ISQLTemplate) new CLVPractice2DBDAOCheckDuplicateDtlRSQL(),
							param, velParam);
			if (dbRowset != null && dbRowset.next()) {
				dupFlg = dbRowset.getString(1);
			}
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}

		return dupFlg;
	}
	
}