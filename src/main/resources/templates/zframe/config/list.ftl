<!DOCTYPE html>
<html>
<head>
    <title>系统参数</title>
    <#include "../../resource.ftl"/>
    <script type="text/javascript" src="/zframe/config/js/list.js"></script>
</head>
<body>
<form class="layui-form " action="">
    <div class="layui-form-item">
        <label class="layui-form-label">参数名:</label>
        <div class="layui-input-inline">
            <input type="text" name="code"  placeholder="请输入参数名" class="layui-input">
        </div>

        <div class="layui-input-inline">
            <button class="layui-btn search-btn" table-id="SysConfigTable" lay-submit="" lay-filter="search"><i class="fa fa-search">&nbsp;</i>查询
            </button>
            <button type="reset" class="layui-btn layui-btn-primary"><i class="fa fa-refresh">&nbsp;</i>重置</button>
        </div>
    </div>
</form>
<div class="layui-btn-group">
        <button class="layui-btn" onclick="add('/sys/config/add')">
            <i class="fa fa-plus">&nbsp;</i>增加
        </button>
        <button class="layui-btn" onclick="edit('SysConfigTable','/sys/config/edit')" >
            <i class="fa fa-pencil-square-o">&nbsp;</i>修改
        </button>
        <button class="layui-btn" onclick="deleteBatch('SysConfigTable','/sys/config/delete');">
            <i class="fa fa-trash-o">&nbsp;</i>删除
        </button>
</div>
<div class="layui-form ">
    <table class="layui-table" id="SysConfigTable" zType="pageGrid"
           zProps="url:'/sys/config/listData',checkbox:'true',pageColor:'#2991d9'">
        <thead>
        <tr>
            <!--复选框-->
            <th width="1%" param="{type:'checkbox'}">
                <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
            </th>
            <!--isPrimary：是否是主键-->
            <th width="10%" param="{name:'id',isPrimary:'true',hide:'true'}">主键</th>

		    <th width="10%" param="{name:'code'}">参数名</th>
			            			
		    <th width="10%" param="{name:'value'}">参数值</th>
			            			
		    <th width="10%" param="{name:'remark'}">备注</th>
			<!--isPrimary：渲染列-->
            <th width="10%" param="{name:'status',codeName:'state',render:'Render.customState'}">状态</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>