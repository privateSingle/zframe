<!DOCTYPE html>
<html>
<head>
    <title>管理员列表</title>
    <#include "../../resource.ftl"/>
    <script type="text/javascript" src="/zframe/user/js/list.js"></script>
</head>
<body>
<form class="layui-form " action="">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名:</label>
        <div class="layui-input-inline">
            <input type="text" name="username" placeholder="请输入用户名" class="layui-input">
        </div>
        <label class="layui-form-label">手机号:</label>
        <div class="layui-input-inline">
            <input type="text" name="mobile" placeholder="请输入手机号" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn search-btn" table-id="userTable" lay-submit="" lay-filter="search"><i class="fa fa-search">&nbsp;</i>查询
            </button>
            <button type="reset" class="layui-btn layui-btn-primary"><i class="fa fa-refresh">&nbsp;</i>重置</button>
        </div>
    </div>
</form>
<div class="layui-btn-group">
<@shiro.hasPermission name="sys:user:save">
    <button class="layui-btn" onclick="add('/sys/user/add')"><i class="fa fa-plus">&nbsp;</i>增加</button>
</@shiro.hasPermission>
<@shiro.hasPermission name="sys:user:update">
    <button class="layui-btn" onclick="edit('userTable','/sys/user/edit')" style="margin-left: 5px!important;">
        <i class="fa fa-pencil-square-o">&nbsp;</i>修改
    </button>
</@shiro.hasPermission>
<@shiro.hasPermission name="sys:user:delete">
    <button class="layui-btn" onclick="deleteBatch('userTable','/sys/user/delete');" style="margin-left: 5px!important;">
        <i class="fa fa-trash-o">&nbsp;</i>删除
    </button>
</@shiro.hasPermission>
    <button class="layui-btn" onclick="initPassword('userTable','/sys/user/initPassword');" style="margin-left: 5px!important;">
        <i class="fa fa-refresh">&nbsp;</i>初始化密码 123456
    </button>
    <input type="file" id="file" onchange="getFilePath('/sys/user/import')" style="filter:alpha(opacity=0);opacity:0;width: 0;height: 0;"/>
    <button class="layui-btn" onclick="importExcel('/sys/user/export');" style="margin-left: 5px!important;">
        <i class="fa fa-cloud-upload">&nbsp;</i>导入
    </button>
    <button class="layui-btn" onclick="exportExcel('/sys/user/export');" style="margin-left: 5px!important;">
        <i class="fa fa-cloud-download">&nbsp;</i>导出
    </button>

</div>
<div class="layui-form ">
    <table class="layui-table" id="userTable" zType="pageGrid"
           zProps="url:'/sys/user/listData',checkbox:'true',pageColor:'#2991d9'">
        <thead>
        <input type="hidden" id="page">
        <input type="hidden" id="limit">
        <tr>
            <!--复选框-->
            <th width="1%" param="{type:'checkbox'}">
                <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
            </th>
            <!--isPrimary：是否是主键-->
            <th width="10%" param="{name:'userId',isPrimary:'true',hide:'true'}">用户ID</th>
            <th width="10%" param="{name:'username',sort:'true'}">用户名</th>
            <th width="10%" param="{name:'email',sort:'false'}">邮箱</th>
            <th width="10%" param="{name:'mobile',sort:'true'}">手机号</th>
            <th width="10%" param="{name:'createTime',sort:'true'}">创建时间</th>
        </tr>
        </thead>
    </table>
</div>

</body>
</html>