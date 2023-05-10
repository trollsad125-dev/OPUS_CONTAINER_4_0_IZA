/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLVPractice4DBDAOSearchRLandCdRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.10
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.10 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.integration ;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Nhat Nguyen
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class CLVPractice4DBDAOSearchRLandCdRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * select vsl_slan_cd as rlane_cd
	  * from mdm_rev_lane
	  * WHERE delt_flg = 'N'
	  * </pre>
	  */
	public CLVPractice4DBDAOSearchRLandCdRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.clvtraining.clvpractice4.integration ").append("\n"); 
		query.append("FileName : CLVPractice4DBDAOSearchRLandCdRSQL").append("\n"); 
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
		query.append("select vsl_slan_cd as rlane_cd" ).append("\n"); 
		query.append("from mdm_rev_lane" ).append("\n"); 
		query.append("WHERE delt_flg = 'N'" ).append("\n"); 

	}
}