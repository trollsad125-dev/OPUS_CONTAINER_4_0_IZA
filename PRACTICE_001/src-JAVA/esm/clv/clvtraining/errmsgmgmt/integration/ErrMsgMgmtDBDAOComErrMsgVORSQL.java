<<<<<<< HEAD
/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : ErrMsgMgmtDBDAOComErrMsgVORSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2023.04.25
*@LastModifier : 
*@LastVersion : 1.0
* 2023.04.25 
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

public class ErrMsgMgmtDBDAOComErrMsgVORSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * te
	  * </pre>
	  */
	public ErrMsgMgmtDBDAOComErrMsgVORSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("err_msg",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("err_msg_cd",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.integration").append("\n"); 
		query.append("FileName : ErrMsgMgmtDBDAOComErrMsgVORSQL").append("\n"); 
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
=======
/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : ErrMsgMgmtDBDAOComErrMsgVORSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2023.04.25
*@LastModifier : 
*@LastVersion : 1.0
* 2023.04.25 
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

public class ErrMsgMgmtDBDAOComErrMsgVORSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * te
	  * </pre>
	  */
	public ErrMsgMgmtDBDAOComErrMsgVORSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("err_msg",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("err_msg_cd",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.clvtraining.errmsgmgmt.integration").append("\n"); 
		query.append("FileName : ErrMsgMgmtDBDAOComErrMsgVORSQL").append("\n"); 
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
>>>>>>> 159bd18fa674173bc8ecd373840ec047ae09d86d
		query.append("SELECT " ).append("\n"); 
		query.append("	EDW_UPD_DT" ).append("\n"); 
		query.append(",	UPD_DT" ).append("\n"); 
		query.append(",	UPD_USR_ID" ).append("\n"); 
		query.append(",	CRE_DT" ).append("\n"); 
		query.append(",	CRE_USR_ID" ).append("\n"); 
		query.append(",	ERR_DESC" ).append("\n"); 
		query.append(",	ERR_MSG" ).append("\n"); 
		query.append(",	ERR_LVL_CD" ).append("\n"); 
		query.append(",	ERR_TP_CD" ).append("\n"); 
		query.append(",	LANG_TP_CD" ).append("\n"); 
		query.append(",	ERR_MSG_CD" ).append("\n"); 
		query.append("FROM COM_ERR_MSG" ).append("\n"); 
		query.append("WHERE lang_tp_cd = 'ENG'" ).append("\n"); 
		query.append("#if (${err_msg_cd} != '') " ).append("\n"); 
		query.append("AND   err_msg_cd LIKE '%'||@[err_msg_cd]||'%'" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("#if (${err_msg} != '') " ).append("\n"); 
		query.append("and	UPPER(err_msg) like '%'||UPPER(@[err_msg])|| '%'" ).append("\n"); 
		query.append("#end" ).append("\n"); 

	}
}