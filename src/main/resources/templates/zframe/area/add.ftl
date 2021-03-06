<!DOCTYPE html>
<html>
<head>
    <title>添加</title>
    <#include "../../resource.ftl"/>
</head>
<body>
<div class="layui-field-box">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">地区名称<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text" name="areaName" lay-verify="required" placeholder="请输入地区名称" maxlength="50" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地区行政编码<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text" name="xzCode" lay-verify="number" placeholder="请输入地区行政编码" maxlength="8" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">上级地区<span class="span_must">*</span></label>
            <div class="layui-input-inline" style="width:700px;">
                <div zType="linkSelectTool" zProps="url:'/area/normalList/',topId:'0000000000',name:'parentAreaIds[]'"
                     value="" class="layui-input-inline"></div>

            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <div zType="radioTool" zProps="codeName:'state'" name="state"
                     value="1" class="layui-input-inline"></div>
            </div>
        </div>

        <div class="page-footer">
            <div class="btn-list">
                <div class="btnlist">
                    <button class="layui-btn" lay-submit="" lay-filter="submit" data-url="/area/save"><i class="fa fa-floppy-o">&nbsp;</i>保存</button>
                    <button class="layui-btn" onclick="$t.closeWindow();"><i class="fa fa-undo">&nbsp;</i>返回</button>
                </div>
            </div>
        </div>

    </form>
</div>

</body>
</html>
