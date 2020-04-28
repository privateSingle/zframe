<#--  dalele 2020-04-26 23:46:00-->
<!DOCTYPE html>
<html>
<head>
    <title>财产收入表列表</title>
    <#include "../../../resource.ftl"/>
    <script type="text/javascript" src="/app/kl/income/js/list.js">
    </script>
</head>
<body>
<form class="layui-form " action="">
</form>
<div class="layui-btn-group">
        <@shiro.hasPermission name="income:save">
     <#--   <input type="file" id="file" onchange="getFilePath('/app/kl/income/import')" style="filter:alpha(opacity=0);opacity:0;width: 0;height: 0;"/> -->
     <#--   <button class="layui-btn" onclick="importExcel('/app/kl/income/export');" style="margin-left: 5px!important;">-->
     <#--   <i class="fa fa-cloud-upload">&nbsp;</i>导入-->
     <#--   </button>-->
        </@shiro.hasPermission>
        <@shiro.hasPermission name="income:update">
     <#--   <button class="layui-btn" onclick="exportExcel('/app/kl/income/export');" style="margin-left: 5px!important;">-->
     <#--   <i class="fa fa-cloud-download">&nbsp;</i>导出-->
     <#--   </button>-->
       </@shiro.hasPermission>

</div>
<div class="layui-form ">
    <table class="layui-table" id="incomeTable" zType="pageGrid"
           zProps="url:'/app/kl/income/userListData?userId=${(model)!"-"}',checkbox:'true',pageColor:'#2991d9'">
        <thead>
         <input type="hidden" id="page">
         <input type="hidden" id="limit">
        <tr>
            <!--复选框-->
            <th width="1%" param="{type:'checkbox'}"  id="th1">
                <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
            </th>
            			            <!--isPrimary：是否是主键-->
            <th width="10%" param="{name:'id',isPrimary:'true',hide:'true'}">id</th>


		          <th width="10%" param="{name:'incomeName'}">收入名称</th>

		          <th width="10%" param="{name:'incomeType'}">收入类型</th>

		          <th width="10%" param="{name:'incomePrice'}">收入金额</th>

		          <th width="10%" param="{name:'incomeDate'}">收入日期</th>

		          <th width="10%" param="{name:'remark'}">备注</th>

		          <th width="10%" param="{name:'addTime'}">添加时间</th>
			                        <!--isPrimary：渲染列-->
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
