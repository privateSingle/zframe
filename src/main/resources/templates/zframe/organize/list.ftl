<!DOCTYPE html>
<html>
<head>
    <title>部门管理</title>
    <#include "../../resource.ftl"/>
    <script type="text/javascript" src="/zframe/organize/js/list.js"></script>
</head>
<body>
<form class="layui-form " action="">
    <div class="layui-form-item">
        <label class="layui-form-label">名称:</label>
        <div class="layui-input-inline">
            <input type="text" name="orgName" placeholder="部门名称" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="text" name="orgCode" placeholder="部门编号" class="layui-input">
        </div>

        <div class="layui-input-inline">
            <button class="layui-btn search-btn" table-id="organizeTable" lay-submit="" lay-filter="search"><i
                    class="fa fa-search">&nbsp;</i>查询
            </button>
            <button type="reset" class="layui-btn layui-btn-primary"><i class="fa fa-refresh">&nbsp;</i>重置</button>
        </div>
    </div>
</form>
<div class="layui-btn-group">

<@shiro.hasPermission name="organize:save">
    <button class="layui-btn" onclick="addPage('/organize/add')">
        <i class="fa fa-plus">&nbsp;</i>增加
    </button>
</@shiro.hasPermission>

<@shiro.hasPermission name="organize:update">
    <button class="layui-btn" onclick="editPage('organizeTable','/organize/edit')">
        <i class="fa fa-pencil-square-o">&nbsp;</i>修改
    </button>
</@shiro.hasPermission>

<@shiro.hasPermission name="organize:delete">
    <button class="layui-btn layui-btn-delete" onclick="deleteBatch('批量删除','organizeTable','/organize/delete');">
        <i class="fa fa-trash-o">&nbsp;</i>删除
    </button>
</@shiro.hasPermission>
</div>
<div class="layui-form ">
    <table class="layui-table" id="organizeTable" zType="pageGrid"
           zProps="url:'/organize/listData',checkbox:'true',pageColor:'#2991d9'">
        <thead>
        <tr>
            <!--复选框-->
            <th width="1%" param="{type:'checkbox'}">
                <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
            </th>
            <th width="10%" param="{name:'orgId',isPrimary:'true',hide:'true'}">部门id</th>
            <!--isPrimary：是否是主键-->
            <th width="10%" param="{name:'orgName'}">部门名称</th>
            <th width="10%" param="{name:'orgCode'}">部门编号</th>
            <th width="10%" param="{name:'parentOrgName'}">父部门名称</th>
            <th width="10%" param="{name:'parentOrgCode'}">上一级部门编号</th>

            <th width="10%" param="{operate:'true',buttons:'Render.edit,Render.delete'}">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>