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

        <a-form-item label="出动执法人员次数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'personTime', validatorRules.personTime]" placeholder="请输入出动执法人员次数" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="活禽宰杀流动摊贩管理" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'strvendPoultry', validatorRules.strvendPoultry]" placeholder="请输入活禽宰杀流动摊贩管理" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="贩卖野生动物流动摊贩管理" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'strvendWildlife', validatorRules.strvendWildlife]" placeholder="请输入贩卖野生动物流动摊贩管理" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="其它流动摊贩" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'strvendOther', validatorRules.strvendOther]" placeholder="请输入其它流动摊贩" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'remarks', validatorRules.remarks]" placeholder="请输入备注"></a-input>
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
    name: "CitymanagerEpSummaryModal",
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
          personTime: {rules: [
          ]},
          strvendPoultry: {rules: [
          ]},
          strvendWildlife: {rules: [
          ]},
          strvendOther: {rules: [
          ]},
          remarks: {rules: [
          ]},
        },
        url: {
          add: "/summary/citymanagerEpSummary/add",
          edit: "/summary/citymanagerEpSummary/edit",
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
          this.form.setFieldsValue(pick(this.model,'personTime','strvendPoultry','strvendWildlife','strvendOther','remarks'))
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
        this.form.setFieldsValue(pick(row,'personTime','strvendPoultry','strvendWildlife','strvendOther','remarks'))
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