<!DOCTYPE html>
<html>
<head>
    <title>操作日志</title>
    <#include "../../resource.ftl"/>
    <script type="text/javascript" src="/zframe/log/js/list.js"></script>
</head>
<body>
<form class="layui-form " action="">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名:</label>
        <div class="layui-input-inline">
            <input type="text" name="username" placeholder="请输入用户名" class="layui-input">
        </div>
        <label class="layui-form-label">操作时间:</label>
        <div class="layui-input-inline">
            <input class="layui-input" placeholder="开始日" id="LAY_demorange_s" name="startDate">
        </div>
        <div class="layui-input-inline">
            <input class="layui-input" placeholder="截止日" id="LAY_demorange_e" name="endDate">
        </div>
        <div class="layui-input-inline">
            <button class="layui-btn search-btn" table-id="syslogTable" lay-submit="" lay-filter="search"><i
                    class="fa fa-search">&nbsp;</i>查询
            </button>
            <button type="reset" class="layui-btn layui-btn-primary"><i class="fa fa-refresh">&nbsp;</i>重置</button>
        </div>
    </div>
</form>

<div class="layui-form ">
    <table class="layui-table" id="syslogTable" zType="pageGrid"
           zProps="url:'/sys/log/listData',checkbox:'true',pageColor:'#2991d9',pageSize:10">
        <thead>
        <tr>
            <!--复选框-->
            <th width="1%" param="{type:'checkbox'}">
                <input type="checkbox" lay-skin="primary" lay-filter="allChoose">
            </th>
            <!--isPrimary：是否是主键-->
            <th width="10%" param="{name:'id',isPrimary:'true',hide:'true'}">用户ID</th>
            <th width="10%" param="{name:'username'}">用户名</th>
            <th width="10%" param="{name:'operation'}">用户操作</th>
            <th width="10%" param="{name:'method'}">请求方法</th>
            <th width="10%" param="{name:'params'}">请求参数</th>
            <th width="10%" param="{name:'ip',render:'Render.customState'}">ip地址</th>
            <th width="10%" param="{name:'createDate'}">创建时间</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>