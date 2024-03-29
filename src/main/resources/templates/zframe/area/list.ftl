<!DOCTYPE html>
<html>
<head>
    <title>地区管理</title>
<#include "../../resource.ftl"/>
    <script type="text/javascript" src="/zframe/area/js/list.js"></script>
</head>
<body>
<form class="layui-form " action="">
    <div class="layui-form-item">
        <label class="layui-form-label">地区名称:</label>
        <div class="layui-input-inline">
            <input type="text" name="areaName" placeholder="请输入地区名称" class="layui-input">
        </div>
        <label class="layui-form-label">地区行政编码:</label>
        <div class="layui-input-inline">
            <input type="text" name="xzCode" placeholder="请输入地区行政编码" class="layui-input">
        </div>
        <label class="layui-form-label">状态:</label>
        <div class="layui-input-inline">
            <div zType="selectTool" zProps="codeName:'state'" name="state" class="layui-input-inline">
            </div>
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn search-btn" table-id="AreaTable" lay-submit="" lay-filter="search"><i
                    class="fa fa-search">&nbsp;</i>查询
            </button>
            <button type="reset" class="layui-btn layui-btn-primary"><i class="fa fa-refresh">&nbsp;</i>重置</button>
        </div>
    </div>
</form>
<div class="layui-btn-group">
<@shiro.hasPermission name="area:save">
    <button class="layui-btn" onclick="add('/area/add')">
        <i class="fa fa-plus">&nbsp;</i>增加
    </button>
</@shiro.hasPermission>

<@shiro.hasPermission name="area:update">
    <button class="layui-btn" onclick="edit('AreaTable','/area/edit')">
        <i class="fa fa-pencil-square-o">&nbsp;</i>修改
    </button>
</@shiro.hasPermission>

<@shiro.hasPermission name="area:delete">

    <button class="layui-btn" onclick="deleteBatch('AreaTable','/area/delete');">
        <i class="fa fa-trash-o">&nbsp;</i>删除
    </button>
</@shiro.hasPermission>

    <button class="layui-btn" onclick="enable('AreaTable','/area/enable')">
        <i class="fa fa-check-square-o">&nbsp;</i>启用
    </button>
    <button class="layui-btn  layui-btn-danger" onclick="limit('AreaTable','/area/limit')">
        <i class="fa fa-expeditedssl">&nbsp;</i>禁用
    </button>
</div>

<div class="layui-form ">
    <table class="layui-table" id="AreaTable" zType="pageGrid"
           zProps="url:'/area/listData',checkbox:'true',pageColor:'#2991d9'">
        <thead>
        <tr>
            <!--复选框-->
            <th width="1%" param="{type:'checkbox'}">
                <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
            </th>
            <!--isPrimary：是否是主键-->
            <th width="10%" param="{name:'areaId',isPrimary:'true',hide:'true'}"></th>

            <th width="10%" param="{name:'areaName'}">地区名称</th>

            <th width="10%" param="{name:'xzCode'}">地区行政编码</th>

            <th width="10%" param="{name:'areaLevel',codeName:'areaLevel'}">行政级别</th>

            <th width="10%" param="{name:'parentAreaName'}">上级地区名称</th>

            <th width="10%" param="{name:'state',codeName:'state',render:'Render.customState'}">状态</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>