<#--  财产支出表公用页面-->
<#--  dalele 2020-04-27 13:29:30-->
              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">支出名称<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="expendName" maxlength="64" lay-verify="required"
                 value="${(model.expendName)!""}"  placeholder="请输入支出名称"  class="layui-input">
            </div>
        </div>

              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">支出金额<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="expendPrice" maxlength="10,2" lay-verify="required"
                 value="${(model.expendPrice)!""}"  placeholder="请输入支出金额"  class="layui-input">
            </div>
        </div>

              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">支出日期<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="date"  name="expendDate" maxlength="20" lay-verify="required"
                 value="${(model.expendDate?string('yyyy-MM-dd'))!""}"  placeholder="请输入支出日期"  class="layui-input">
            </div>
        </div>

              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">备注<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="remark" maxlength="500" lay-verify="required"
                 value="${(model.remark)!""}"  placeholder="请输入备注"  class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">添加时间<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="date"  name="addTime" maxlength="20" lay-verify="required"
                 value="${(model.addTime?string('yyyy-MM-dd'))!""}"  placeholder="请输入添加时间"  class="layui-input">
            </div>
