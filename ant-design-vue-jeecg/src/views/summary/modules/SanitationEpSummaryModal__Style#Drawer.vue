<template>
  <a-drawer
    :title="title"
    :width="width"
    placement="right"
    :closable="false"
    @close="close"
    :visible="visible">
  
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="环卫作业车辆" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'vehicle', validatorRules.vehicle]" placeholder="请输入环卫作业车辆" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="环卫作业人员" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'personTime', validatorRules.personTime]" placeholder="请输入环卫作业人员" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="生活垃圾无害化处理" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'garbageDisposal', validatorRules.garbageDisposal]" placeholder="请输入生活垃圾无害化处理" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="口罩废弃桶" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'kzKouzhFeiqt', validatorRules.kzKouzhFeiqt]" placeholder="请输入口罩废弃桶" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="运输量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'kzYunsl', validatorRules.kzYunsl]" placeholder="请输入运输量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="消杀-公厕" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'xsGongc', validatorRules.xsGongc]" placeholder="请输入消杀-公厕" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="消杀-垃圾站" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'xsLajz', validatorRules.xsLajz]" placeholder="请输入消杀-垃圾站" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="消杀-环卫作业车辆" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'xsHuanwcc', validatorRules.xsHuanwcc]" placeholder="请输入消杀-环卫作业车辆" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="消杀-果壳箱与垃圾桶" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'xsGuokx', validatorRules.xsGuokx]" placeholder="请输入消杀-果壳箱与垃圾桶" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="生活垃圾无害化处理" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'hjwsLajcl', validatorRules.hjwsLajcl]" placeholder="请输入生活垃圾无害化处理" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="单位内部疫情防控消杀处理情况" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'hjwsXiaoscc', validatorRules.hjwsXiaoscc]" placeholder="请输入单位内部疫情防控消杀处理情况" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="本单位职工涉湖北接触情况" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'hjwsHubeiJiecqk', validatorRules.hjwsHubeiJiecqk]" placeholder="请输入本单位职工涉湖北接触情况"></a-input>
        </a-form-item>
        <a-form-item label="口罩" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'fywzKouzh', validatorRules.fywzKouzh]" placeholder="请输入口罩" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="酒精" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'fywzJiuj', validatorRules.fywzJiuj]" placeholder="请输入酒精" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="温度计" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'fywzWendj', validatorRules.fywzWendj]" placeholder="请输入温度计" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="消毒液" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'fywzXiaody', validatorRules.fywzXiaody]" placeholder="请输入消毒液" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="人流量，设施消杀及运行情况" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'gongyFyqk', validatorRules.gongyFyqk]" placeholder="请输入人流量，设施消杀及运行情况"></a-input>
        </a-form-item>
        <a-form-item label="其它" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'other', validatorRules.other]" placeholder="请输入其它"></a-input>
        </a-form-item>
        
      </a-form>
    </a-spin>
    <a-button type="primary" @click="handleOk">确定</a-button>
    <a-button type="primary" @click="handleCancel">取消</a-button>
  </a-drawer>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  
  export default {
    name: "SanitationEpSummaryModal",
    components: { 
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
          vehicle: {rules: [
          ]},
          personTime: {rules: [
          ]},
          garbageDisposal: {rules: [
          ]},
          kzKouzhFeiqt: {rules: [
          ]},
          kzYunsl: {rules: [
          ]},
          xsGongc: {rules: [
          ]},
          xsLajz: {rules: [
          ]},
          xsHuanwcc: {rules: [
          ]},
          xsGuokx: {rules: [
          ]},
          hjwsLajcl: {rules: [
          ]},
          hjwsXiaoscc: {rules: [
          ]},
          hjwsHubeiJiecqk: {rules: [
          ]},
          fywzKouzh: {rules: [
          ]},
          fywzJiuj: {rules: [
          ]},
          fywzWendj: {rules: [
          ]},
          fywzXiaody: {rules: [
          ]},
          gongyFyqk: {rules: [
          ]},
          other: {rules: [
          ]},
        },
        url: {
          add: "/summary/sanitationEpSummary/add",
          edit: "/summary/sanitationEpSummary/edit",
        }
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'vehicle','personTime','garbageDisposal','kzKouzhFeiqt','kzYunsl','xsGongc','xsLajz','xsHuanwcc','xsGuokx','hjwsLajcl','hjwsXiaoscc','hjwsHubeiJiecqk','fywzKouzh','fywzJiuj','fywzWendj','fywzXiaody','gongyFyqk','other'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'vehicle','personTime','garbageDisposal','kzKouzhFeiqt','kzYunsl','xsGongc','xsLajz','xsHuanwcc','xsGuokx','hjwsLajcl','hjwsXiaoscc','hjwsHubeiJiecqk','fywzKouzh','fywzJiuj','fywzWendj','fywzXiaody','gongyFyqk','other'))
      }
      
    }
  }
</script>

<style lang="less" scoped>
/** Button按钮间距 */
  .ant-btn {
    margin-left: 30px;
    margin-bottom: 30px;
    float: right;
  }
</style>