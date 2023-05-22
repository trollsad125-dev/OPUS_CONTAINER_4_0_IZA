/*=========================================================
*Copyright(c) 2023 CyberLogitec
*@FileName : ClvPractice003ViewAdapter.java
*@FileTitle : CLV Practice 3
*Open Issues :
*Change history :
*@LastModifyDate : 2023.05.22
*@LastModifier : 
*@LastVersion : 1.0
* 2023.05.22
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.event;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.vo.DetailVO;
import com.clt.apps.opus.esm.clv.clvtraining.clvpractice3.vo.SummaryVO;
import com.clt.framework.component.common.AbstractValueObject;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.ViewAdapter;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;

/**
 * 
 * 
 * @author Nhat Nguyen
 * @see ViewAdapter 참조
 * @since J2EE 1.6
 */
public class ClvPractice003ViewAdapter extends ViewAdapter {
    public ClvPractice003ViewAdapter(){
    	super();
    }

	/**  
	 * View Adapter 생성시 자동으로 호출된다.<br>
	 *  <br>
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return String
	 * @exception EventException
	 */	
    
    @SuppressWarnings("unchecked")
	public String makeXML(HttpServletRequest request, HttpServletResponse response) {
    	GeneralEventResponse eventResponse = (GeneralEventResponse)request.getAttribute("EventResponse"); 
    	FormCommand command = FormCommand.fromRequest(request);
 		StringBuilder strBuilder = new StringBuilder();
 		
 		String savedName = "ContractNoInquiry.csv";  
 		
    	
    	if(command.isCommand(FormCommand.SEARCH01)) {
    		savedName = "SummarySheet.csv";
    		List<SummaryVO> list = null;
    		SummaryVO vo = null;
     		list = ((List<SummaryVO>)eventResponse.getRsVoList());
     		try{		
        		
     			response.setContentType("application/vnd.ms.excel");
     			String strClient = request.getHeader("user-agent");
     
     			if (strClient.indexOf("MSIE 5.5") != -1) {
     				response.setHeader("Content-Type",
     						"doesn/matter; charset=euc-kr");
     				response.setHeader("Content-Disposition", "filename="
     						+ savedName + "; charset=euc-kr");
     			} else {
     				response.setHeader("Content-Type",
     						"application/octet-stream; charset=euc-kr");
     				response.setHeader("Content-Disposition",
     						"attachment;filename=" + savedName + ";");
     			} 			     		
         		
        		PrintWriter pout = response.getWriter();
        		strBuilder.append("Partner,Lane,Invoice No,Slip No,Approved,Curr.,Revenue,Expense,Customer/S.Provider");
        		strBuilder.append("\n");
        		strBuilder.append(",,,,,,,,Code,Name");
        		strBuilder.append("\n");
        		String rgx = "[,\\r\\n]";
        		for(int i = 0 ; i < list.size() ; i++){
        			vo = list.get(i);
        			
        			strBuilder.append(JSPUtil.getNull(vo.getJoCrrCd()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getRlaneCd()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getInvNo()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getCsrNo()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getAproFlg()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getLoclCurrCd()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getInvRevActAmt()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getInvExpActAmt()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getCustVndrCntCd()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getCustVndrEngNm()).trim().replaceAll(rgx, " "));
    				strBuilder.append("\n");
    			}
        		
    			pout.print(strBuilder.toString());
    			pout.flush();
    			pout.close();
        				    
            }    	
        	catch(Exception ex){
                log.error(ex.getMessage(), ex);
                throw new RuntimeException(ex.getMessage());
            }  
    	}else if(command.isCommand(FormCommand.SEARCH02)){
    		List<DetailVO> list = null;
    		savedName = "DetailSheet.csv";
    		DetailVO vo = null;
     		list = ((List<DetailVO>)eventResponse.getRsVoList());
     		try{		
        		
     			response.setContentType("application/vnd.ms.excel");
     			String strClient = request.getHeader("user-agent");
     
     			if (strClient.indexOf("MSIE 5.5") != -1) {
     				response.setHeader("Content-Type",
     						"doesn/matter; charset=euc-kr");
     				response.setHeader("Content-Disposition", "filename="
     						+ savedName + "; charset=euc-kr");
     			} else {
     				response.setHeader("Content-Type",
     						"application/octet-stream; charset=euc-kr");
     				response.setHeader("Content-Disposition",
     						"attachment;filename=" + savedName + ";");
     			} 			     		
         		
        		PrintWriter pout = response.getWriter();
        		strBuilder.append("Partner,Lane,Invoice No,Slip No,Approved,RevExp,Item,Curr.,Revenue,Expense,Customer/S.Provider");
        		strBuilder.append("\n");
        		strBuilder.append(",,,,,,,,,,Code,Name");
        		strBuilder.append("\n");
        		String rgx = "[,\\r\\n]";
        		for(int i = 0 ; i < list.size() ; i++){
        			vo = list.get(i);
        			
        			strBuilder.append(JSPUtil.getNull(vo.getJoCrrCd()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getRlaneCd()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getInvNo()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getCsrNo()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getAproFlg()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getRevExp()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getItem()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getLoclCurrCd()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getInvRevActAmt()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getInvExpActAmt()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getCustVndrCntCd()).trim().replaceAll(rgx, " "));
    				strBuilder.append(",");
    				strBuilder.append(JSPUtil.getNull(vo.getCustVndrEngNm()).trim().replaceAll(rgx, " "));
    				strBuilder.append("\n");
    			}
        		
    			pout.print(strBuilder.toString());
    			pout.flush();
    			pout.close();
        				    
            }    	
        	catch(Exception ex){
                log.error(ex.getMessage(), ex);
                throw new RuntimeException(ex.getMessage());
            }
    	}
		  	
    	return "";
    }
    
    /**
     * makeDataTag
     * @param List<AbstractValueObject> arg0
     * @param String arg1
     */
 	protected String makeDataTag(List<AbstractValueObject> arg0, String arg1) {
 		return null;
 	}
 
 	/**
     * makeDataTag
     * @param DBRowSet arg0
     * @param String arg1
     */
 	protected String makeDataTag(DBRowSet arg0, String arg1) {
 		return null;
 	}
 	
}
