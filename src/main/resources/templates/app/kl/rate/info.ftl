<#--  dalele 2020-04-27 13:29:30-->

<html>
<head>
    <title>利率表详情页面</title>
   <#include "../../../resource.ftl"/>
</head>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">
             <div class="layui-form-item">
            <label class="layui-label-left">id<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.id)!"-"}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">利率<span class="label_span">:</span></label>
            <label class="layui-label-right">${(model.rate)!"-"}</label>
        </div>
          </form>
</div>

</body>
</html>
