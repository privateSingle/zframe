<#--  dalele 2020-04-26 23:46:00-->

<html>
<head>
    <title>财产收入表修改页面</title>
   <#include "../../../resource.ftl"/>
</head>
<script>
    //执行一个laydate实例
    laydate.render({
        elem: '#date1' //指定元素
    });
</script>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">
       <#include "common.ftl"/>
        <input type="hidden" name="id" value="${model.id}">
        <div class="page-footer">
            <div class="btn-list">
                <div class="btnlist">
                    <button class="layui-btn" lay-submit="" lay-filter="submit" data-url="/app/kl/income/update"><i class="fa fa-floppy-o">&nbsp;</i>保存</button>
                    <button class="layui-btn" onclick="$t.closeWindow();"><i class="fa fa-undo">&nbsp;</i>返回</button>
                </div>
            </div>
        </div>
    </form>
</div>

</body>
</html>

