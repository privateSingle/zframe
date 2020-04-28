<#--  dalele 2020-04-27 13:29:30-->

<html>
<head>
    <title>还贷助手表详情页面</title>
   <#include "../../../resource.ftl"/>
</head>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">
              <div class="layui-form-item">
            <label class="layui-label-left">欠款总额<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.totalAmount)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">还款期限<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.deadline)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">还款日期<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.refundDate)!"-"}</label>
        </div>
          </form>
</div>

</body>
</html>
