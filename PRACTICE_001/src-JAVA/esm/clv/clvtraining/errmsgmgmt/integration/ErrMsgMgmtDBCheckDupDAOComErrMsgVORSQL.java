/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : ErrMsgMgmtDBCheckDupDAOComErrMsgVORSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2023.04.24
*@LastModifier : 
*@LastVersion : 1.0
* 2023.04.24 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.integration ;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Nhat Nguyen
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class ErrMsgMgmtDBCheckDupDAOComErrMsgVORSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * te
	  * </pre>
	  */
	public ErrMsgMgmtDBCheckDupDAOComErrMsgVORSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("err_msg_cd",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.integration ").append("\n"); 
		query.append("FileName : ErrMsgMgmtDBCheckDupDAOComErrMsgVORSQL").append("\n"); 
		query.append("*/").append("\n"); 
	}
	
	public String getSQL(){
		return query.toString();
	}
	
	public HashMap<String,String[]> getParams() {
		return params;
	}

	/**
	 * Query 생성
	 */
	public void setQuery(){
		query.append("SELECT                                                                " ).append("\n"); 
		query.append("   	1                                                          " ).append("\n"); 
		query.append("FROM com_err_msg                                                      " ).append("\n"); 
		query.append("WHERE lang_tp_cd = 'ENG' AND" ).append("\n"); 
		query.append("      err_msg_cd LIKE '%'||@[err_msg_cd]||'%'" ).append("\n"); 

	}
}