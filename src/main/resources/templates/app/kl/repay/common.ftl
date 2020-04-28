<#--  还贷助手表公用页面-->
<#--  dalele 2020-04-27 13:29:30-->

              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">欠款总额<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="totalAmount" maxlength="10,2" lay-verify="required"
                 value="${(model.totalAmount)!""}"  placeholder="请输入欠款总额"  class="layui-input">
            </div>
        </div>

              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">还款期限<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="text"  name="deadline" maxlength="3" lay-verify="required"
                 value="${(model.deadline)!""}"  placeholder="请输入还款期限"  class="layui-input">
            </div>
        </div>

              <div class="layui-form-item">${(model.bucket)!""}
            <label class="layui-form-label">还款日期<span class="span_must">*</span></label>
            <div class="layui-input-normal">
                <input type="date"  name="refundDate" maxlength="20" lay-verify="required"
                 value="${(model.refundDate?string('yyyy-MM-dd'))!""}"  placeholder="请输入还款日期"  class="layui-input">
            </div>
