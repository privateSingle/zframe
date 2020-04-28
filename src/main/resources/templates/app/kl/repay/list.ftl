<#--  dalele 2020-04-27 13:29:30-->
<!DOCTYPE html>
<html>
<head>
    <title>还贷助手表列表</title>
    <#include "../../../resource.ftl"/>
    <script type="text/javascript" src="/app/kl/repay/js/list.js"></script>
</head>
<body>
<form class="layui-form " action="">
</form>
<div class="layui-btn-group">
        <@shiro.hasPermission name="repay:save">
        <button class="layui-btn" onclick="addPage('/app/kl/repay/add')">
            <i class="fa fa-plus">&nbsp;</i>增加
        </button>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="repay:update">
        <button class="layui-btn" onclick="editPage('repayTable','/app/kl/repay/edit')">
            <i class="fa fa-pencil-square-o">&nbsp;</i>修改
        </button>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="repay:delete">
         <button class="layui-btn layui-btn-delete" onclick="deleteBatch('删除','repayTable','/app/kl/repay/delete');">
            <i class="fa fa-trash-o">&nbsp;</i>删除
        </button>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="repay:save">
     <#--   <input type="file" id="file" onchange="getFilePath('/app/kl/repay/import')" style="filter:alpha(opacity=0);opacity:0;width: 0;height: 0;"/> -->
     <#--   <button class="layui-btn" onclick="importExcel('/app/kl/repay/export');" style="margin-left: 5px!important;">-->
     <#--   <i class="fa fa-cloud-upload">&nbsp;</i>导入-->
     <#--   </button>-->
        </@shiro.hasPermission>
        <@shiro.hasPermission name="repay:update">
     <#--   <button class="layui-btn" onclick="exportExcel('/app/kl/repay/export');" style="margin-left: 5px!important;">-->
     <#--   <i class="fa fa-cloud-download">&nbsp;</i>导出-->
     <#--   </button>-->
       </@shiro.hasPermission>

</div>
<div class="layui-form ">
    <table class="layui-table" id="repayTable" zType="pageGrid"
           zProps="url:'/app/kl/repay/listData',checkbox:'true',pageColor:'#2991d9'">
        <thead>
         <input type="hidden" id="page">
         <input type="hidden" id="limit">
        <tr>
            <!--复选框-->
            <th width="1%" param="{type:'checkbox'}">
                <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
            </th>
            			            <!--isPrimary：是否是主键-->
            <th width="10%" param="{name:'id',isPrimary:'true',hide:'true'}">id</th>


		          <th width="10%" param="{name:'totalAmount'}">欠款总额</th>

		          <th width="10%" param="{name:'deadline'}">还款期限</th>

		          <th width="10%" param="{name:'refundDate'}">还款日期</th>
			                        <!--isPrimary：渲染列-->
            <th width="10%" param="{operate:'true',buttons:'Render.state,Render.info,Render.edit,Render.delete,Render.infos'}">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
