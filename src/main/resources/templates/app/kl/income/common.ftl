<#--  财产收入表公用页面-->
<#--  dalele 2020-04-26 23:46:00-->
              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">收入名称<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="incomeName" maxlength="128" lay-verify="required"
                 value="${(model.incomeName)!""}"  placeholder="请输入收入名称"  class="layui-input">
            </div>
        </div>

              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">收入类型<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="incomeType" maxlength="128" lay-verify="required"
                 value="${(model.incomeType)!""}"  placeholder="请输入收入类型"  class="layui-input">
            </div>
        </div>

              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">收入金额<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="incomePrice" maxlength="10,2" lay-verify="required"
                 value="${(model.incomePrice)!""}"  placeholder="请输入收入金额"  class="layui-input">
            </div>
        </div>

            <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">收入日期<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="date"  name="incomeDate" maxlength="20" lay-verify="required"
                 value="${(model.incomeDate?string('yyyy-MM-dd'))!""}"  placeholder="请输入收入日期"  class="layui-input" id="date1">
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
                 value="${(model.addTime?string('yyyy-MM-dd'))!""}"  placeholder="请输入添加时间"   class="layui-input">
            </div>


