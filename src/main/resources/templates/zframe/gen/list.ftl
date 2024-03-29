
<html>
<head>
    <title>代码生成器</title>
    <#include "../../resource.ftl"/>
    <script type="text/javascript" src="/zframe/gen/js/list.js"></script>
</head>
<body>
<form class="layui-form " action="">
    <div class="layui-form-item">
        <label class="layui-form-label">表名:</label>
        <div class="layui-input-inline">
            <input type="text" name="tableName"  placeholder="请输入表名" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn search-btn" table-id="genTable" lay-submit="" lay-filter="search">
                <i class="fa fa-search">&nbsp;</i>查询
            </button>
            <button type="reset" class="layui-btn layui-btn-primary"><i class="fa fa-refresh">&nbsp;</i>重置</button>
        </div>
    </div>
</form>
<div class="layui-btn-group">
    <button class="layui-btn" onclick="generator('genTable')">  <i class="fa fa-rocket">&nbsp;</i>生成代码</button>
</div>
<div class="layui-form ">
    <table class="layui-table" id="genTable" zType="pageGrid"
           zProps="url:'/sys/generator/listData',checkbox:'true',pageColor:'#2991d9',pageSize:'10'">
        <thead>
        <tr>
            <!--复选框-->
            <th width="1%" param="{type:'checkbox'}">
                <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
            </th>
            <!--isPrimary：是否是主键-->
            <th width="10%" param="{name:'tableName',isPrimary:'true'}">表名</th>
            <th width="10%" param="{name:'engine'}">Engine</th>
            <th width="10%" param="{name:'tableComment'}">表备注</th>
            <th width="10%" param="{name:'createTime'}">创建时间</th>

        </tr>
        </thead>
    </table>
</div>
</body>
</html>