/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : ErrMsgMgmtDBDAOComErrMsgVOCSQL.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2023.04.19
*@LastModifier : 
*@LastVersion : 1.0
* 2023.04.19 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.integration;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Nhat Nguyen
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class ErrMsgMgmtDBDAOComErrMsgVOCSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * 
	  * </pre>
	  */
	public ErrMsgMgmtDBDAOComErrMsgVOCSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.integration").append("\n"); 
		query.append("FileName : ErrMsgMgmtDBDAOComErrMsgVOCSQL").append("\n"); 
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
		
	}
}