<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="部门名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-select-depart v-decorator="['sysOrgCode', validatorRules.sysOrgCode]" :trigger-change="true"  customReturnField="orgCode"/>
        </a-form-item>
        <a-form-item label="道路名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'name', validatorRules.name]" placeholder="请输入道路名称"></a-input>
        </a-form-item>
        <a-form-item label="道路类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag v-decorator="[ 'type', validatorRules.type]" placeholder="请选择道路类型" :trigger-change="true" type="list" dictCode="road_type"></j-dict-select-tag>
        </a-form-item>
        <a-form-item label="起止点" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'startEnd', validatorRules.startEnd]" placeholder="请输入起止点"></a-input>
        </a-form-item>
        <a-form-item label="长度（m）" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'length', validatorRules.length]" placeholder="请输入长度（m）"></a-input>
        </a-form-item>
        <a-form-item label="面积（m²）" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'area', validatorRules.area]" placeholder="请输入面积（m²）"></a-input>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'remark', validatorRules.remark]" placeholder="请输入备注"></a-input>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JSelectDepart from '@/components/jeecgbiz/JSelectDepart'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"

  export default {
    name: "NcRoadModal",
    components: {
      JSelectDepart,
      JDictSelectTag
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
          sysOrgName: {rules: [
          ]},
          name: {rules: [
          ]},
          type: {rules: [
          ]},
          startEnd: {rules: [
          ]},
          length: {rules: [
          ]},
          area: {rules: [
          ]},
          remark: {rules: [
          ]},
        },
        url: {
          add: "/check/ncRoad/add",
          edit: "/check/ncRoad/edit",
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
          this.form.setFieldsValue(pick(this.model,'sysOrgName','name','type','startEnd','length','area','remark'))
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
        this.form.setFieldsValue(pick(row,'sysOrgName','name','type','startEnd','length','area','remark'))
      },

      
    }
  }
</script>