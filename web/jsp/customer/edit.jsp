<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath }/"> 
<TITLE>添加客户</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="css/Style.css" type=text/css rel=stylesheet>
<LINK href="css/Manage.css" type=text/css
	rel=stylesheet>


<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
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
</HEAD>
<BODY>
	<FORM id=form1 name=form1 enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/customer_update.action"
		method=post>
		<s:hidden name="cust_id"/>
		<s:hidden name="cust_image"/>

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
								<TD class=manageHead>当前位置：客户管理 &gt; 添加客户</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
						  
						    
							<TR>
								<td>客户名称：</td>
								<td>
								<s:textfield name="cust_name" theme="simple" cssClass="textbox" cssStyle="WIDTH: 180px"></s:textfield>
								<%-- <INPUT class=textbox id=sChannel2 value="<s:property value="model.cust_name"/>"
														style="WIDTH: 180px" maxLength=50 name="cust_name"> --%>
								</td>
								<td>客户级别 ：</td>
								<td>
								<select id="dict_level" name="baseDictLevel.dict_id">
									<option>-请选择-</option>
								</select>
								</td>
							</TR>
							
							<TR>
								
								<td>信息来源 ：</td>
								<td>
								<select id="dict_source" name="baseDictSource.dict_id">
									<option>-请选择-</option>
								</select>
								
								</td>
								<td>所属行业 ：</td>
								<td>
								<select id="dict_industry" name="baseDictIndustry.dict_id">
									<option>-请选择-</option>
								</select>
								</td>
							</TR>
							
							<TR>
								
								
								<td>固定电话 ：</td>
								<td>
								<s:textfield name="cust_phone" theme="simple" cssClass="textbox" cssStyle="WIDTH: 180px"></s:textfield>
								<!-- <INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_phone"> -->
								</td>
								<td>移动电话 ：</td>
								<td>
								<s:textfield name="cust_mobile" theme="simple" cssClass="textbox" cssStyle="WIDTH: 180px"></s:textfield>
								<!-- <INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_mobile"> -->
								</td>
								
							</TR>
							
							<tr>
								<td>客户资质图片：</td>
								<td>
								<INPUT class=textbox id=sChannel2 type="file"
														style="WIDTH: 180px" maxLength=50 name="upload">
								</td>
							</tr>
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value=" 保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
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
