<#--  dalele 2020-04-27 13:29:30-->
<!DOCTYPE html>
<html>
<head>
    <title>利率表列表</title>
    <#include "../../../resource.ftl"/>
    <script type="text/javascript" src="/app/kl/rate/js/list.js"></script>
</head>
<body>
<form class="layui-form " action="">
</form>
<div class="layui-btn-group">
               <@shiro.hasPermission name="rate:update">
        <button class="layui-btn" onclick="editPage('rateTable','/app/kl/rate/edit')">
            <i class="fa fa-pencil-square-o">&nbsp;</i>修改
        </button>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="rate:save">
     <#--   <input type="file" id="file" onchange="getFilePath('/app/kl/rate/import')" style="filter:alpha(opacity=0);opacity:0;width: 0;height: 0;"/> -->
     <#--   <button class="layui-btn" onclick="importExcel('/app/kl/rate/export');" style="margin-left: 5px!important;">-->
     <#--   <i class="fa fa-cloud-upload">&nbsp;</i>导入-->
     <#--   </button>-->
        </@shiro.hasPermission>
        <@shiro.hasPermission name="rate:update">
     <#--   <button class="layui-btn" onclick="exportExcel('/app/kl/rate/export');" style="margin-left: 5px!important;">-->
     <#--   <i class="fa fa-cloud-download">&nbsp;</i>导出-->
     <#--   </button>-->
       </@shiro.hasPermission>

</div>
<div class="layui-form ">
    <table class="layui-table" id="rateTable" zType="pageGrid"
           zProps="url:'/app/kl/rate/listData',checkbox:'true',pageColor:'#2991d9'">
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


            <th width="10%" param="{name:'rate'}">利率</th>
			                        <!--isPrimary：渲染列-->
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
