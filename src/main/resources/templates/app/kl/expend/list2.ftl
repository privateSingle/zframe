<#--  dalele 2020-04-27 13:29:30-->
<!DOCTYPE html>
<html>
<head>
    <title>财产支出表列表</title>
    <#include "../../../resource.ftl"/>
    <script type="text/javascript" src="/app/kl/expend/js/list.js"></script>
</head>
<body>
<form class="layui-form " action="">
</form>
<div class="layui-btn-group">
    <@shiro.hasPermission name="expend:save">
    <#--   <input type="file" id="file" onchange="getFilePath('/app/kl/expend/import')" style="filter:alpha(opacity=0);opacity:0;width: 0;height: 0;"/> -->
    <#--   <button class="layui-btn" onclick="importExcel('/app/kl/expend/export');" style="margin-left: 5px!important;">-->
    <#--   <i class="fa fa-cloud-upload">&nbsp;</i>导入-->
    <#--   </button>-->
    </@shiro.hasPermission>
    <@shiro.hasPermission name="expend:update">
    <#--   <button class="layui-btn" onclick="exportExcel('/app/kl/expend/export');" style="margin-left: 5px!important;">-->
    <#--   <i class="fa fa-cloud-download">&nbsp;</i>导出-->
    <#--   </button>-->
    </@shiro.hasPermission>

</div>
<div class="layui-form ">
    <table class="layui-table" id="expendTable" zType="pageGrid"
           zProps="url:'/app/kl/expend/userListData?userId=${(model)!"-"}',checkbox:'true',pageColor:'#2991d9'">
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


            <th width="10%" param="{name:'expendName'}">支出名称</th>

            <th width="10%" param="{name:'expendPrice'}">支出金额</th>

            <th width="10%" param="{name:'expendDate'}">支出日期</th>

            <th width="10%" param="{name:'remark'}">备注</th>

            <th width="10%" param="{name:'addTime'}">添加时间</th>
            <!--isPrimary：渲染列-->
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
