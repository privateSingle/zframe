<#--  dalele 2020-04-27 13:29:30-->

<html>
<head>
    <title>财产支出表详情页面</title>
   <#include "../../../resource.ftl"/>
</head>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-label-left">支出名称<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.expendName)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">支出金额<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.expendPrice)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">支出日期<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.expendDate)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">备注<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.remark)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">添加时间<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.addTime)!"-"}</label>
        </div>
          </form>
</div>

</body>
</html>
