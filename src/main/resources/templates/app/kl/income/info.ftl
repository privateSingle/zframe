<#--  dalele 2020-04-26 23:46:00-->

<html>
<head>
    <title>财产收入表详情页面</title>
   <#include "../../../resource.ftl"/>
</head>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">
              <div class="layui-form-item">
            <label class="layui-label-left">收入名称<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.incomeName)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">收入类型<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.incomeType)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">收入金额<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.incomePrice)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">收入日期<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.incomeDate)!"-"}</label>
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
