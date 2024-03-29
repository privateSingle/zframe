<!DOCTYPE html>
<html>
<head>
    <title>字典管理</title>
    <#include "../../resource.ftl"/>
    <script type="text/javascript" src="/zframe/commpara/js/list.js"></script>
</head>
<body>
<form class="layui-form " action="">
    <div class="layui-form-item">
        <label class="layui-form-label">参数编码:</label>
        <div class="layui-input-inline">
            <input type="text" name="paraCodeVague" placeholder="请输入名称" class="layui-input">
        </div>

        <div class="layui-input-inline">
            <button class="layui-btn search-btn" table-id="commparaTable" lay-submit="" lay-filter="search"><i
                    class="fa fa-search">&nbsp;</i>查询
            </button>
            <button type="reset" class="layui-btn layui-btn-primary"><i class="fa fa-refresh">&nbsp;</i>重置</button>
        </div>
    </div>
</form>
<div class="layui-btn-group">
    <@shiro.hasPermission name="commpara:save">
        <button class="layui-btn" onclick="addPage('/commpara/add')">
            <i class="fa fa-plus">&nbsp;</i>增加
        </button>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="commpara:update">
        <button class="layui-btn" onclick="editPage('commparaTable','/commpara/edit')">
            <i class="fa fa-pencil-square-o">&nbsp;</i>修改
        </button>
        <button class="layui-btn layui-btn-green" onclick="updateState('批量启用','commparaTable','/commpara/enable')">
            <i class="fa fa-check-square-o">&nbsp;</i>启用
        </button>
        <button class="layui-btn  layui-btn-danger" onclick="updateState('批量禁用','commparaTable','/commpara/limit')">
            <i class="fa fa-expeditedssl">&nbsp;</i>禁用
        </button>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="commpara:delete">
        <button class="layui-btn layui-btn-delete" onclick="deleteBatch('批量删除','commparaTable','/commpara/delete');">
            <i class="fa fa-trash-o">&nbsp;</i>删除
        </button>
    </@shiro.hasPermission>
</div>
<div class="layui-form ">
    <table class="layui-table" id="commparaTable" zType="pageGrid"
           zProps="url:'/commpara/listData',checkbox:'true',pageColor:'#2991d9'">
        <thead>
        <tr>
            <!--复选框-->
            <th width="1%" param="{type:'checkbox'}">
                <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
            </th>
            <!--isPrimary：是否是主键-->
            <th width="10%" param="{name:'paraId',isPrimary:'true',hide:'true'}">主键</th>


            <th width="10%" param="{name:'paraCode'}">参数编码</th>

            <th width="10%" param="{name:'paraName'}">参数名称</th>

            <th width="10%" param="{name:'paraKey'}">参数值</th>

            <th width="10%" param="{name:'sortNo'}">排序</th>

            <!--isPrimary：渲染列-->
            <th width="10%" param="{name:'state',codeName:'state',render:'Render.customState'}">状态</th>
            <th width="10%" param="{operate:'true',buttons:'Render.state,Render.edit,Render.delete'}">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>