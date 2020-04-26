<!DOCTYPE html>
<html>
<head>
    <title>系统状态</title>
    <#include "../../resource.ftl"/>
    <script type="text/javascript" src="/zframe/sysenv/js/index.js"></script>
</head>
<body>
<div class="layui-form ">
            <div class="layui-form ">
                <table class="layui-table" id="roleTable" zType="pageGrid"
                       zProps="url:'/sys/env/listData',checkbox:'false',pageColor:'#2991d9'">
                    <thead>
                    <tr>
                        <th width="30%" param="{name:'item',align:'center'}">ITEM</th>
                        <th width="70%" param="{name:'value',align:'center'}">VALUE</th>
                    </tr>
                    </thead>
                </table>
            </div>
</div>
</body>
</html>