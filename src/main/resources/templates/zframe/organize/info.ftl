<!DOCTYPE html>
<html>
<head>
    <title>管理员列表</title>
    <#include "../../resource.ftl"/>
</head>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">
             <div class="layui-form-item">
            <label class="layui-label-left">部门标识<span class="label_span">:</span></label>
            <label class="layui-label-right">${model.orgId}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">部门编号<span class="label_span">:</span></label>
            <label class="layui-label-right">${model.orgCode}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">部门名称<span class="label_span">:</span></label>
            <label class="layui-label-right">${model.orgName}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">上一级部门标识<span class="label_span">:</span></label>
            <label class="layui-label-right">${model.parentOrgId}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">排序<span class="label_span">:</span></label>
            <label class="layui-label-right">${model.sortNo}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">是否显示  0：否 1：是<span class="label_span">:</span></label>
            <label class="layui-label-right">${model.state}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">部门等级串<span class="label_span">:</span></label>
            <label class="layui-label-right">${model.levelOrg}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">所属地区<span class="label_span">:</span></label>
            <label class="layui-label-right">${model.theArea}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">备注<span class="label_span">:</span></label>
            <label class="layui-label-right">${model.note}</label>
        </div>
              <div class="layui-form-item">
            <label class="layui-label-left">添加时间<span class="label_span">:</span></label>
            <label class="layui-label-right">${model.inDate}</label>
        </div>
          </form>
</div>
<script>
    $(document).ready(function () {
       var labels=$(".layui-label-right");
       for(var i=0;i<labels.length;i++){
           if($(labels[i]).html()==""){
               $(labels[i]).html("-");
           }
       }
    });
</script>
</body>
</html>
