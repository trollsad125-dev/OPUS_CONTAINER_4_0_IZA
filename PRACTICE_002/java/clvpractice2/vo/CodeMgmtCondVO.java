/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : CodeMgmtCondVO.java
*@FileTitle : CodeMgmtCondVO
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.08
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.08  
* 1.0 Creation
=========================================================*/

package com.clt.apps.opus.esm.clv.clvtraining.clvpractice2.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.clt.framework.component.common.AbstractValueObject;
import com.clt.framework.component.util.JSPUtil;

/**
 * Table Value Ojbect<br>
 * 관련 Event 에서 생성, 서버실행요청시 Data 전달역할을 수행하는 Value Object
 *
 * @author 
 * @since J2EE 1.6
 * @see AbstractValueObject
 */

public class CodeMgmtCondVO extends AbstractValueObject {

	private static final long serialVersionUID = 1L;
	
	private Collection<CodeMgmtCondVO> models = new ArrayList<CodeMgmtCondVO>();
	
	/* Column Info */
	private String updDt = null;
	/* Column Info */
	private String intgCdLen = null;
	/* Column Info */
	private String intgCdDesc = null;
	/* Column Info */
	private String searchtype = null;
	/* Column Info */
	private String ownrSubSysCd = null;
	/* Column Info */
	private String intgCdTpCd = null;
	/* Column Info */
	private String creDt = null;
	/* Column Info */
	private String mngTblNm = null;
	/* Page Number */
	private String pagerows = null;
	/* Column Info */
	private String intgCdId = null;
	/* VO Data Value( C:Creation, U:Update, D:Delete ) */
	private String ibflag = null;
	/* Column Info */
	private String intgCdUseFlg = null;
	/* Column Info */
	private String creUsrId = null;
	/* Column Info */
	private String intgCdNm = null;
	/* Column Info */
	private String codeVal = null;
	/* Column Info */
	private String subsystem = null;
	/* Column Info */
	private String updUsrId = null;

	/*	테이블 컬럼의 값을 저장하는 Hashtable */
	private HashMap<String, String> hashColumns = new LinkedHashMap<String, String>();

	/*	테이블 컬럼에 대응되는 멤버변수를 저장하는 Hashtable */
	private HashMap<String, String> hashFields = new LinkedHashMap<String, String>();
	
	public CodeMgmtCondVO() {}

	public CodeMgmtCondVO(String ibflag, String pagerows, String ownrSubSysCd, String intgCdId, String intgCdNm, String intgCdDesc, String intgCdTpCd, String mngTblNm, String intgCdLen, String intgCdUseFlg, String creUsrId, String creDt, String updUsrId, String updDt, String subsystem, String searchtype, String codeVal) {
		this.updDt = updDt;
		this.intgCdLen = intgCdLen;
		this.intgCdDesc = intgCdDesc;
		this.searchtype = searchtype;
		this.ownrSubSysCd = ownrSubSysCd;
		this.intgCdTpCd = intgCdTpCd;
		this.creDt = creDt;
		this.mngTblNm = mngTblNm;
		this.pagerows = pagerows;
		this.intgCdId = intgCdId;
		this.ibflag = ibflag;
		this.intgCdUseFlg = intgCdUseFlg;
		this.creUsrId = creUsrId;
		this.intgCdNm = intgCdNm;
		this.codeVal = codeVal;
		this.subsystem = subsystem;
		this.updUsrId = updUsrId;
	}
	
	/**
	 * 테이블 컬럼에 저장할 값을 Hashtable<"column_name", "value"> 로 반환
	 * @return HashMap
	 */
	public HashMap<String, String> getColumnValues(){
		this.hashColumns.put("upd_dt", getUpdDt());
		this.hashColumns.put("intg_cd_len", getIntgCdLen());
		this.hashColumns.put("intg_cd_desc", getIntgCdDesc());
		this.hashColumns.put("searchtype", getSearchtype());
		this.hashColumns.put("ownr_sub_sys_cd", getOwnrSubSysCd());
		this.hashColumns.put("intg_cd_tp_cd", getIntgCdTpCd());
		this.hashColumns.put("cre_dt", getCreDt());
		this.hashColumns.put("mng_tbl_nm", getMngTblNm());
		this.hashColumns.put("pagerows", getPagerows());
		this.hashColumns.put("intg_cd_id", getIntgCdId());
		this.hashColumns.put("ibflag", getIbflag());
		this.hashColumns.put("intg_cd_use_flg", getIntgCdUseFlg());
		this.hashColumns.put("cre_usr_id", getCreUsrId());
		this.hashColumns.put("intg_cd_nm", getIntgCdNm());
		this.hashColumns.put("code_val", getCodeVal());
		this.hashColumns.put("subsystem", getSubsystem());
		this.hashColumns.put("upd_usr_id", getUpdUsrId());
		return this.hashColumns;
	}
	
	/**
	 * 컬럼명에 대응되는 멤버변수명을 저장하여 Hashtable<"column_name", "variable"> 로 반환   
	 * @return
	 */
	public HashMap<String, String> getFieldNames(){
		this.hashFields.put("upd_dt", "updDt");
		this.hashFields.put("intg_cd_len", "intgCdLen");
		this.hashFields.put("intg_cd_desc", "intgCdDesc");
		this.hashFields.put("searchtype", "searchtype");
		this.hashFields.put("ownr_sub_sys_cd", "ownrSubSysCd");
		this.hashFields.put("intg_cd_tp_cd", "intgCdTpCd");
		this.hashFields.put("cre_dt", "creDt");
		this.hashFields.put("mng_tbl_nm", "mngTblNm");
		this.hashFields.put("pagerows", "pagerows");
		this.hashFields.put("intg_cd_id", "intgCdId");
		this.hashFields.put("ibflag", "ibflag");
		this.hashFields.put("intg_cd_use_flg", "intgCdUseFlg");
		this.hashFields.put("cre_usr_id", "creUsrId");
		this.hashFields.put("intg_cd_nm", "intgCdNm");
		this.hashFields.put("code_val", "codeVal");
		this.hashFields.put("subsystem", "subsystem");
		this.hashFields.put("upd_usr_id", "updUsrId");
		return this.hashFields;
	}
	
	/**
	 * Column Info
	 * @return updDt
	 */
	public String getUpdDt() {
		return this.updDt;
	}
	
	/**
	 * Column Info
	 * @return intgCdLen
	 */
	public String getIntgCdLen() {
		return this.intgCdLen;
	}
	
	/**
	 * Column Info
	 * @return intgCdDesc
	 */
	public String getIntgCdDesc() {
		return this.intgCdDesc;
	}
	
	/**
	 * Column Info
	 * @return searchtype
	 */
	public String getSearchtype() {
		return this.searchtype;
	}
	
	/**
	 * Column Info
	 * @return ownrSubSysCd
	 */
	public String getOwnrSubSysCd() {
		return this.ownrSubSysCd;
	}
	
	/**
	 * Column Info
	 * @return intgCdTpCd
	 */
	public String getIntgCdTpCd() {
		return this.intgCdTpCd;
	}
	
	/**
	 * Column Info
	 * @return creDt
	 */
	public String getCreDt() {
		return this.creDt;
	}
	
	/**
	 * Column Info
	 * @return mngTblNm
	 */
	public String getMngTblNm() {
		return this.mngTblNm;
	}
	
	/**
	 * Page Number
	 * @return pagerows
	 */
	public String getPagerows() {
		return this.pagerows;
	}
	
	/**
	 * Column Info
	 * @return intgCdId
	 */
	public String getIntgCdId() {
		return this.intgCdId;
	}
	
	/**
	 * VO Data Value( C:Creation, U:Update, D:Delete )
	 * @return ibflag
	 */
	public String getIbflag() {
		return this.ibflag;
	}
	
	/**
	 * Column Info
	 * @return intgCdUseFlg
	 */
	public String getIntgCdUseFlg() {
		return this.intgCdUseFlg;
	}
	
	/**
	 * Column Info
	 * @return creUsrId
	 */
	public String getCreUsrId() {
		return this.creUsrId;
	}
	
	/**
	 * Column Info
	 * @return intgCdNm
	 */
	public String getIntgCdNm() {
		return this.intgCdNm;
	}
	
	/**
	 * Column Info
	 * @return codeVal
	 */
	public String getCodeVal() {
		return this.codeVal;
	}
	
	/**
	 * Column Info
	 * @return subsystem
	 */
	public String getSubsystem() {
		return this.subsystem;
	}
	
	/**
	 * Column Info
	 * @return updUsrId
	 */
	public String getUpdUsrId() {
		return this.updUsrId;
	}
	

	/**
	 * Column Info
	 * @param updDt
	 */
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	
	/**
	 * Column Info
	 * @param intgCdLen
	 */
	public void setIntgCdLen(String intgCdLen) {
		this.intgCdLen = intgCdLen;
	}
	
	/**
	 * Column Info
	 * @param intgCdDesc
	 */
	public void setIntgCdDesc(String intgCdDesc) {
		this.intgCdDesc = intgCdDesc;
	}
	
	/**
	 * Column Info
	 * @param searchtype
	 */
	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}
	
	/**
	 * Column Info
	 * @param ownrSubSysCd
	 */
	public void setOwnrSubSysCd(String ownrSubSysCd) {
		this.ownrSubSysCd = ownrSubSysCd;
	}
	
	/**
	 * Column Info
	 * @param intgCdTpCd
	 */
	public void setIntgCdTpCd(String intgCdTpCd) {
		this.intgCdTpCd = intgCdTpCd;
	}
	
	/**
	 * Column Info
	 * @param creDt
	 */
	public void setCreDt(String creDt) {
		this.creDt = creDt;
	}
	
	/**
	 * Column Info
	 * @param mngTblNm
	 */
	public void setMngTblNm(String mngTblNm) {
		this.mngTblNm = mngTblNm;
	}
	
	/**
	 * Page Number
	 * @param pagerows
	 */
	public void setPagerows(String pagerows) {
		this.pagerows = pagerows;
	}
	
	/**
	 * Column Info
	 * @param intgCdId
	 */
	public void setIntgCdId(String intgCdId) {
		this.intgCdId = intgCdId;
	}
	
	/**
	 * VO Data Value( C:Creation, U:Update, D:Delete )
	 * @param ibflag
	 */
	public void setIbflag(String ibflag) {
		this.ibflag = ibflag;
	}
	
	/**
	 * Column Info
	 * @param intgCdUseFlg
	 */
	public void setIntgCdUseFlg(String intgCdUseFlg) {
		this.intgCdUseFlg = intgCdUseFlg;
	}
	
	/**
	 * Column Info
	 * @param creUsrId
	 */
	public void setCreUsrId(String creUsrId) {
		this.creUsrId = creUsrId;
	}
	
	/**
	 * Column Info
	 * @param intgCdNm
	 */
	public void setIntgCdNm(String intgCdNm) {
		this.intgCdNm = intgCdNm;
	}
	
	/**
	 * Column Info
	 * @param codeVal
	 */
	public void setCodeVal(String codeVal) {
		this.codeVal = codeVal;
	}
	
	/**
	 * Column Info
	 * @param subsystem
	 */
	public void setSubsystem(String subsystem) {
		this.subsystem = subsystem;
	}
	
	/**
	 * Column Info
	 * @param updUsrId
	 */
	public void setUpdUsrId(String updUsrId) {
		this.updUsrId = updUsrId;
	}
	
/**
	 * Request 의 데이터를 추출하여 VO 의 멤버변수에 설정.
	 * @param request
	 */
	public void fromRequest(HttpServletRequest request) {
		fromRequest(request,"");
	}

	/**
	 * Request 의 데이터를 추출하여 VO 의 멤버변수에 설정.
	 * @param request
	 */
	public void fromRequest(HttpServletRequest request, String prefix) {
		setUpdDt(JSPUtil.getParameter(request, prefix + "upd_dt", ""));
		setIntgCdLen(JSPUtil.getParameter(request, prefix + "intg_cd_len", ""));
		setIntgCdDesc(JSPUtil.getParameter(request, prefix + "intg_cd_desc", ""));
		setSearchtype(JSPUtil.getParameter(request, prefix + "searchtype", ""));
		setOwnrSubSysCd(JSPUtil.getParameter(request, prefix + "ownr_sub_sys_cd", ""));
		setIntgCdTpCd(JSPUtil.getParameter(request, prefix + "intg_cd_tp_cd", ""));
		setCreDt(JSPUtil.getParameter(request, prefix + "cre_dt", ""));
		setMngTblNm(JSPUtil.getParameter(request, prefix + "mng_tbl_nm", ""));
		setPagerows(JSPUtil.getParameter(request, prefix + "pagerows", ""));
		setIntgCdId(JSPUtil.getParameter(request, prefix + "intg_cd_id", ""));
		setIbflag(JSPUtil.getParameter(request, prefix + "ibflag", ""));
		setIntgCdUseFlg(JSPUtil.getParameter(request, prefix + "intg_cd_use_flg", ""));
		setCreUsrId(JSPUtil.getParameter(request, prefix + "cre_usr_id", ""));
		setIntgCdNm(JSPUtil.getParameter(request, prefix + "intg_cd_nm", ""));
		setCodeVal(JSPUtil.getParameter(request, prefix + "code_val", ""));
		setSubsystem(JSPUtil.getParameter(request, prefix + "subsystem", ""));
		setUpdUsrId(JSPUtil.getParameter(request, prefix + "upd_usr_id", ""));
	}

	/**
	 * Request 의 데이터를 VO 배열로 변환하여 반환.
	 * @param request
	 * @return CodeMgmtCondVO[]
	 */
	public CodeMgmtCondVO[] fromRequestGrid(HttpServletRequest request) {
		return fromRequestGrid(request, "");
	}

	/**
	 * Request 넘어온 여러 건 DATA를 VO Class 에 담는다. 
	 * @param request
	 * @param prefix
	 * @return CodeMgmtCondVO[]
	 */
	public CodeMgmtCondVO[] fromRequestGrid(HttpServletRequest request, String prefix) {
		CodeMgmtCondVO model = null;
		
		String[] tmp = request.getParameterValues(prefix + "ibflag");
  		if(tmp == null)
   			return null;

  		int length = request.getParameterValues(prefix + "ibflag").length;
  
		try {
			String[] updDt = (JSPUtil.getParameter(request, prefix	+ "upd_dt", length));
			String[] intgCdLen = (JSPUtil.getParameter(request, prefix	+ "intg_cd_len", length));
			String[] intgCdDesc = (JSPUtil.getParameter(request, prefix	+ "intg_cd_desc", length));
			String[] searchtype = (JSPUtil.getParameter(request, prefix	+ "searchtype", length));
			String[] ownrSubSysCd = (JSPUtil.getParameter(request, prefix	+ "ownr_sub_sys_cd", length));
			String[] intgCdTpCd = (JSPUtil.getParameter(request, prefix	+ "intg_cd_tp_cd", length));
			String[] creDt = (JSPUtil.getParameter(request, prefix	+ "cre_dt", length));
			String[] mngTblNm = (JSPUtil.getParameter(request, prefix	+ "mng_tbl_nm", length));
			String[] pagerows = (JSPUtil.getParameter(request, prefix	+ "pagerows", length));
			String[] intgCdId = (JSPUtil.getParameter(request, prefix	+ "intg_cd_id", length));
			String[] ibflag = (JSPUtil.getParameter(request, prefix	+ "ibflag", length));
			String[] intgCdUseFlg = (JSPUtil.getParameter(request, prefix	+ "intg_cd_use_flg", length));
			String[] creUsrId = (JSPUtil.getParameter(request, prefix	+ "cre_usr_id", length));
			String[] intgCdNm = (JSPUtil.getParameter(request, prefix	+ "intg_cd_nm", length));
			String[] codeVal = (JSPUtil.getParameter(request, prefix	+ "code_val", length));
			String[] subsystem = (JSPUtil.getParameter(request, prefix	+ "subsystem", length));
			String[] updUsrId = (JSPUtil.getParameter(request, prefix	+ "upd_usr_id", length));
			
			for (int i = 0; i < length; i++) {
				model = new CodeMgmtCondVO();
				if (updDt[i] != null)
					model.setUpdDt(updDt[i]);
				if (intgCdLen[i] != null)
					model.setIntgCdLen(intgCdLen[i]);
				if (intgCdDesc[i] != null)
					model.setIntgCdDesc(intgCdDesc[i]);
				if (searchtype[i] != null)
					model.setSearchtype(searchtype[i]);
				if (ownrSubSysCd[i] != null)
					model.setOwnrSubSysCd(ownrSubSysCd[i]);
				if (intgCdTpCd[i] != null)
					model.setIntgCdTpCd(intgCdTpCd[i]);
				if (creDt[i] != null)
					model.setCreDt(creDt[i]);
				if (mngTblNm[i] != null)
					model.setMngTblNm(mngTblNm[i]);
				if (pagerows[i] != null)
					model.setPagerows(pagerows[i]);
				if (intgCdId[i] != null)
					model.setIntgCdId(intgCdId[i]);
				if (ibflag[i] != null)
					model.setIbflag(ibflag[i]);
				if (intgCdUseFlg[i] != null)
					model.setIntgCdUseFlg(intgCdUseFlg[i]);
				if (creUsrId[i] != null)
					model.setCreUsrId(creUsrId[i]);
				if (intgCdNm[i] != null)
					model.setIntgCdNm(intgCdNm[i]);
				if (codeVal[i] != null)
					model.setCodeVal(codeVal[i]);
				if (subsystem[i] != null)
					model.setSubsystem(subsystem[i]);
				if (updUsrId[i] != null)
					model.setUpdUsrId(updUsrId[i]);
				models.add(model);
			}

		} catch (Exception e) {
			return null;
		}
		return getCodeMgmtCondVOs();
	}

	/**
	 * VO 배열을 반환
	 * @return CodeMgmtCondVO[]
	 */
	public CodeMgmtCondVO[] getCodeMgmtCondVOs(){
		CodeMgmtCondVO[] vos = (CodeMgmtCondVO[])models.toArray(new CodeMgmtCondVO[models.size()]);
		return vos;
	}
	
	/**
	 * VO Class의 내용을 String으로 변환
	 */
	public String toString() {
		   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE );
	   }

	/**
	* 포맷팅된 문자열에서 특수문자 제거("-","/",",",":")
	*/
	public void unDataFormat(){
		this.updDt = this.updDt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdLen = this.intgCdLen .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdDesc = this.intgCdDesc .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.searchtype = this.searchtype .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.ownrSubSysCd = this.ownrSubSysCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdTpCd = this.intgCdTpCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.creDt = this.creDt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.mngTblNm = this.mngTblNm .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.pagerows = this.pagerows .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdId = this.intgCdId .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.ibflag = this.ibflag .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdUseFlg = this.intgCdUseFlg .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.creUsrId = this.creUsrId .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.intgCdNm = this.intgCdNm .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.codeVal = this.codeVal .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.subsystem = this.subsystem .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.updUsrId = this.updUsrId .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
	}
}
