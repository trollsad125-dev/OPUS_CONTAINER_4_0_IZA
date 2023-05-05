<<<<<<< HEAD
/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : ErrMsgMgmtDBDAOComErrMsgVOCSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2023.04.24
*@LastModifier : 
*@LastVersion : 1.0
* 2023.04.24 
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
		params.put("err_tp_cd",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("err_lvl_cd",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("err_msg_cd",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("upd_usr_id",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("err_desc",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("cre_usr_id",new String[]{arrTmp[0],arrTmp[1]});

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
=======
/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : ErrMsgMgmtDBDAOComErrMsgVOCSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2023.04.24
*@LastModifier : 
*@LastVersion : 1.0
* 2023.04.24 
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
		params.put("err_tp_cd",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("err_lvl_cd",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("err_msg_cd",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("upd_usr_id",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("err_desc",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("cre_usr_id",new String[]{arrTmp[0],arrTmp[1]});

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
>>>>>>> 159bd18fa674173bc8ecd373840ec047ae09d86d
		query.append("INSERT INTO com_err_msg (                                             " ).append("\n"); 
		query.append(" err_msg_cd                                        ,                   " ).append("\n"); 
		query.append(" lang_tp_cd                                        ,  " ).append("\n"); 
		query.append(" err_lvl_cd                                         ,                   " ).append("\n"); 
		query.append(" err_tp_cd                                         ,                   " ).append("\n"); 
		query.append(" err_msg                                           ,                   " ).append("\n"); 
		query.append(" err_desc                                          ,                   " ).append("\n"); 
		query.append(" cre_usr_id                                        ,                   " ).append("\n"); 
		query.append(" cre_dt                                            ,                   " ).append("\n"); 
		query.append(" upd_usr_id                                        ,                   " ).append("\n"); 
		query.append(" upd_dt                                                                " ).append("\n"); 
		query.append("  ) VALUES (                                                           " ).append("\n"); 
		query.append(" @[err_msg_cd]                                                 ,                   " ).append("\n"); 
		query.append(" 'ENG'                                                 ,         " ).append("\n"); 
		query.append(" @[err_lvl_cd]                                                 ,                " ).append("\n"); 
		query.append(" @[err_tp_cd]                                                 ,                   " ).append("\n"); 
		query.append(" @[err_msg]                                                 ,                   " ).append("\n"); 
		query.append(" @[err_desc]                                                 ,                   " ).append("\n"); 
		query.append(" @[cre_usr_id]                                                 ,                   " ).append("\n"); 
		query.append(" sysdate                                           ,                   " ).append("\n"); 
		query.append(" @[upd_usr_id]                                                 ,                   " ).append("\n"); 
		query.append(" sysdate                                                               " ).append("\n"); 
		query.append(" )" ).append("\n"); 

	}
}