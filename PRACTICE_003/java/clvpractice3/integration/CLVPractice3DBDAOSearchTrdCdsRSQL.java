/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CLVPractice3DBDAOSearchTrdCdsRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.15
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.15 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.integration;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Nhat Nguyen
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class CLVPractice3DBDAOSearchTrdCdsRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * Search Trade Code
	  * </pre>
	  */
	public CLVPractice3DBDAOSearchTrdCdsRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("rlane_cd",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.integration").append("\n"); 
		query.append("FileName : CLVPractice3DBDAOSearchTrdCdsRSQL").append("\n"); 
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
		query.append("SELECT TRD_CD TRD_CD" ).append("\n"); 
		query.append("FROM JOO_CARRIER" ).append("\n"); 
		query.append("WHERE 1=1" ).append("\n"); 
		query.append("#if (${jo_crr_cd} != '' && ${jo_crr_cd} != 'ALL')" ).append("\n"); 
		query.append("and jo_crr_cd in (" ).append("\n"); 
		query.append("	#foreach($key IN ${crrCds})" ).append("\n"); 
		query.append("		#if($velocityCount < $crrCds.size()) " ).append("\n"); 
		query.append("			'$key', " ).append("\n"); 
		query.append("		#else " ).append("\n"); 
		query.append("			'$key' " ).append("\n"); 
		query.append("		#end " ).append("\n"); 
		query.append("	#end" ).append("\n"); 
		query.append(")" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("#if (${rlane_cd} != ''&& ${rlane_cd} != 'ALL')" ).append("\n"); 
		query.append("and UPPER(rlane_cd) like UPPER('%'||@[rlane_cd]||'%')" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("GROUP BY TRD_CD" ).append("\n"); 

	}
}