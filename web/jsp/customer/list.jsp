<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		//发送ajax请求
		var url = "${pageContext.request.contextPath}/baseDict_findById.action";
		//加载客户所属行业
		$.post(url,{"dict_type_code":"001"},function(data){
			//data:[{"dict_enable":"1","dict_id":"6","dict_item_code":"","dict_item_name":"电话营销","dict_memo":"","dict_sort":1,"dict_type_code":"002","dict_type_name":"客户信息来源"},{"dict_enable":"1","dict_id":"7","dict_item_code":"","dict_item_name":"网络营销","dict_memo":"","dict_sort":2,"dict_type_code":"002","dict_type_name":"客户信息来源"}]
			$(data).each(function(i,n){
				//得到下拉列表
				$("#dict_industry").append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>");
			});
			$("#dict_industry option[value='${baseDictIndustry.dict_id}']").attr("selected",true);
		},"json");
		//加载客户来源
		$.post(url,{"dict_type_code":"002"},function(data){
			//data:[{"dict_enable":"1","dict_id":"6","dict_item_code":"","dict_item_name":"电话营销","dict_memo":"","dict_sort":1,"dict_type_code":"002","dict_type_name":"客户信息来源"},{"dict_enable":"1","dict_id":"7","dict_item_code":"","dict_item_name":"网络营销","dict_memo":"","dict_sort":2,"dict_type_code":"002","dict_type_name":"客户信息来源"}]
			$(data).each(function(i,n){
				//得到下拉列表
				$("#dict_source").append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>");
			});
			$("#dict_source option[value='${baseDictSource.dict_id}']").attr("selected",true);
		},"json");
		//加载客户的级别
		$.post(url,{"dict_type_code":"006"},function(data){
			//data:[{"dict_enable":"1","dict_id":"6","dict_item_code":"","dict_item_name":"电话营销","dict_memo":"","dict_sort":1,"dict_type_code":"002","dict_type_name":"客户信息来源"},{"dict_enable":"1","dict_id":"7","dict_item_code":"","dict_item_name":"网络营销","dict_memo":"","dict_sort":2,"dict_type_code":"002","dict_type_name":"客户信息来源"}]
			$(data).each(function(i,n){
				//得到下拉列表
				$("#dict_level").append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>");
			});
			$("#dict_level option[value='${baseDictLevel.dict_id}']").attr("selected",true);
		},"json");
	});
</script>
<SCRIPT language=javascript>
	function to_page(page){
		if(page){
			$("#page").val(page);
		}
		if($("#page").val()>parseInt("${totalPage}")){
			alert("不能超过总页数！");
			return;
		} 
		document.customerForm.submit();
		
	}
</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath }/customer_findByPage.action"
		method=post>
		
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>客户名称：</TD>
													<TD><INPUT class=textbox id=sChannel2
														value='<s:property value="model.cust_name"/>'
														style="WIDTH: 80px" maxLength=50 name="cust_name"></TD>
													<TD>客户来源：</TD>
													<TD><select id="dict_source"
														name="baseDictSource.dict_id">
															<option value="">-请选择-</option>
													</select></TD>
													<TD>客户级别：</TD>
													<TD><select id="dict_level"
														name="baseDictLevel.dict_id">
															<option value="">-请选择-</option>
													</select></TD>
													<TD>客户行业：</TD>
													<TD><select id="dict_industry"
														name="baseDictIndustry.dict_id">
															<option value="">-请选择-</option>
													</select></TD>
													<TD><INPUT class=button id=sButton2 type=submit
														value=" 筛选 " name=sButton2></TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户名称</TD>
													<TD>客户级别</TD>
													<TD>客户来源</TD>
													<TD>所属行业</TD>
													<TD>电话</TD>
													<TD>手机</TD>
													<TD>操作</TD>
												</TR>
												<s:iterator value="list">
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD><s:property value="cust_name"/> </TD>
													<TD><s:property value="baseDictLevel.dict_item_name"/></TD>
													<TD><s:property value="baseDictSource.dict_item_name"/></TD>
													<TD><s:property value="baseDictIndustry.dict_item_name"/></TD>
													<TD><s:property value="cust_phone"/></TD>
													<TD><s:property value="cust_mobile"/></TD>
													<TD>  
													<a href="${pageContext.request.contextPath }/customer_edit?cust_id=<s:property value="cust_id"/>">修改</a>
													&nbsp;&nbsp;
													<a href="${pageContext.request.contextPath }/customer_delete?cust_id=<s:property value="cust_id"/>">删除</a>
													</TD>
												</TR>
												
												</s:iterator>
							<%--  <s:debug></s:debug> --%> 
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD><SPAN id=pagelink>
											<DIV
												style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B><s:property value="count"/> </B>]条记录,共[<B><s:property value="totalPage"/> </B>]页
												,每页显示
												<select name="pageSize" onchange="to_page()">
													<option value="2" <s:if test="pageSize==2">selected</s:if>>2</option>
													<option value="3" <s:if test="pageSize==3">selected</s:if>>3</option>
													<option value="5" <s:if test="pageSize==5">selected</s:if>>5</option>
													
													
												</select>
												条
												<s:if test="currentPage!=1">
												[<A href="javascript:to_page(<s:property value="currentPage-1"/>)">前一页</A>]
												</s:if>
												<B>
													<s:iterator var="i" begin="1" end="totalPage">
														<s:if test="#i==currentPage">
															<s:property value="#i"/>
														</s:if>
														<s:else>
															<a href="javascript:to_page(<s:property value="#i"/>)"><s:property value="#i"/></a>
														</s:else>
													</s:iterator>
												
												</B>
												<s:if test="currentPage!=totalPage">
												[<A href="javascript:to_page(<s:property value="currentPage+1"/>)">后一页</A>] 
												</s:if>
												到
												<input type="text" size="3" id="page" name="currentPage" />
												页
												
												<input type="button" value="Go" onclick="to_page()"/>
											</DIV>
									</SPAN></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
