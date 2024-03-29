<!DOCTYPE html>
<html>
<head>
    <title>菜单列表</title>
    <#include "../../resource.ftl"/>
    <script type="text/javascript" src="/zframe/menu/js/list.js"></script>
</head>
<body>
<form class="layui-form " action="">
    <div class="layui-form-item">
        <label class="layui-form-label">类型</label>
        <div zType="selectTool" zProps="codeName:'menuType',search:'true'" name="type" value="" lay-verify=""
             class="layui-input-inline"></div>
        <label class="layui-form-label">菜单名称:</label>
        <div class="layui-input-inline">
            <input type="text" name="name" placeholder="请输入菜单名称" class="layui-input">
        </div>
        <label class="layui-form-label">上级菜单:</label>
        <div class="layui-input-inline">
            <input value="" id="parent" zType="treeTool" zProps="url:'/sys/menu/select',name:'parentId'"
                   placeholder="请选择上级菜单" class="layui-input"/>
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn search-btn" table-id="menuTable" lay-submit="" lay-filter="search"><i
                    class="fa fa-search">&nbsp;</i>查询
            </button>
            <button type="reset"  class="layui-btn layui-btn-primary"><i class="fa fa-refresh">&nbsp;</i>重置</button>
        </div>
    </div>
</form>
<div class="layui-btn-group">

<@shiro.hasPermission name="sys:menu:save">
    <button class="layui-btn" onclick="add('/sys/menu/add')">
        <i class="fa fa-plus">&nbsp;</i>增加
    </button>
</@shiro.hasPermission>

<@shiro.hasPermission name="sys:menu:update">
    <button class="layui-btn" onclick="edit('menuTable','/sys/menu/edit')" style="margin-left: 5px!important;">
        <i class="fa fa-pencil-square-o">&nbsp;</i>修改
    </button>
</@shiro.hasPermission>

<@shiro.hasPermission name="sys:menu:delete">
    <button class="layui-btn" onclick="deleteBatch('menuTable','/sys/menu/delete');"
            style="margin-left: 5px!important;">
        <i class="fa fa-trash-o">&nbsp;</i>删除
    </button>
</@shiro.hasPermission>


</div>
<div class="layui-form ">
    <table class="layui-table" id="menuTable" zType="pageGrid"
           zProps="url:'/sys/menu/listData',checkbox:'true',pageColor:'#2991d9'">
        <thead>
        <tr>
            <!--复选框-->
            <th width="1%" param="{type:'checkbox'}">
                <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
            </th>
            <!--isPrimary：是否是主键-->
            <th width="10%" param="{name:'menuId',isPrimary:'true',hide:'true'}">菜单ID</th>
            <th width="10%" param="{name:'name',sort:'true'}">菜单名称</th>
            <th width="10%" param="{name:'parentName'}">上级菜单</th>
            <th width="10%" param="{name:'icon',render:'Render.customIcon'}">菜单图标</th>
            <th width="10%" param="{name:'url'}">菜单URL</th>
            <th width="10%" param="{name:'perms'}">授权标识</th>
            <th width="10%" param="{name:'type',render:'Render.customState'}">类型</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>