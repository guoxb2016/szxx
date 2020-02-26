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

        <a-form-item label="县区" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-select-depart v-decorator="['xianqu', validatorRules.xianqu]" :trigger-change="true" customReturnField="departName"/>
        </a-form-item>
        <a-form-item label="公厕类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['leixing', validatorRules.leixing]" :trigger-change="true" dictCode="toilet_type" placeholder="请选择公厕类型"/>
        </a-form-item>
        <a-form-item label="公厕编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'bianhao', validatorRules.bianhao]" placeholder="请输入公厕编号"></a-input>
        </a-form-item>
        <a-form-item label="公厕名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'mingcheng', validatorRules.mingcheng]" placeholder="请输入公厕名称"></a-input>
        </a-form-item>
        <a-form-item label="地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'dizhi', validatorRules.dizhi]" placeholder="请输入地址"></a-input>
        </a-form-item>
        <a-form-item label="公厕类别" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'leibie', validatorRules.leibie]" placeholder="请输入公厕类别"></a-input>
        </a-form-item>
        <a-form-item label="启用年月" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-month placeholder="请选择启用年月"date-format="YYYY-MM" v-decorator="[ 'qiyongNianyue', validatorRules.qiyongNianyue]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="开工年月" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-month placeholder="请选择开工年月" date-format="YYYY-MM" v-decorator="[ 'kaigongNianyue', validatorRules.kaigongNianyue]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="建筑面积（㎡)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'mianji', validatorRules.mianji]" placeholder="请输入建筑面积（㎡)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="独立" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="radio" v-decorator="['jianzhuDuli', validatorRules.jianzhuDuli]" :trigger-change="true" dictCode="yes_or_no" placeholder="请选择独立"/>
        </a-form-item>
        <a-form-item label="附属" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="radio" v-decorator="['jianzhuFushu', validatorRules.jianzhuFushu]" :trigger-change="true" dictCode="yes_or_no" placeholder="请选择附属"/>
        </a-form-item>
        <a-form-item label="土建式" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="radio" v-decorator="['jiegouTujian', validatorRules.jiegouTujian]" :trigger-change="true" dictCode="yes_or_no" placeholder="请选择土建式"/>
        </a-form-item>
        <a-form-item label="装配式" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="radio" v-decorator="['jiegouZhuangpei', validatorRules.jiegouZhuangpei]" :trigger-change="true" dictCode="yes_or_no" placeholder="请选择装配式"/>
        </a-form-item>
        <a-form-item label="男蹲位数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'nan', validatorRules.nan]" placeholder="请输入男蹲位数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="女蹲位数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'nv', validatorRules.nv]" placeholder="请输入女蹲位数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="通用蹲位数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'tongyong', validatorRules.tongyong]" placeholder="请输入通用蹲位数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="无障碍蹲位数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'wuzhangai', validatorRules.wuzhangai]" placeholder="请输入无障碍蹲位数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="小便斗数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'xiaobiandou', validatorRules.xiaobiandou]" placeholder="请输入小便斗数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="附设功能（驿站、垃圾站、环卫管理用房等）" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'fushe', validatorRules.fushe]" placeholder="请输入附设功能（驿站、垃圾站、环卫管理用房等）"></a-input>
        </a-form-item>
        <a-form-item label="是否安装空调" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="radio" v-decorator="['kongtiao', validatorRules.kongtiao]" :trigger-change="true" dictCode="yes_or_no" placeholder="请选择是否安装空调"/>
        </a-form-item>
        <a-form-item label="是否安装智慧系统" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="radio" v-decorator="['zhihui', validatorRules.zhihui]" :trigger-change="true" dictCode="yes_or_no" placeholder="请选择是否安装智慧系统"/>
        </a-form-item>
        <a-form-item label="备注（进展情况）" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'remarks', validatorRules.remarks]" placeholder="请输入备注（进展情况）"></a-input>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'  
  import JSelectDepart from '@/components/jeecgbiz/JSelectDepart'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"
  import JMonth from '../../../components/jeecg/JMonth'

  export default {
    name: "ToiletSummaryModal",
    components: {
      JMonth,
      JDate,
      JSelectDepart,
      JDictSelectTag,
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
          xianqu: {rules: [
          ]},
          leixing: {rules: [
          ]},
          bianhao: {rules: [
          ]},
          mingcheng: {rules: [
          ]},
          dizhi: {rules: [
          ]},
          leibie: {rules: [
          ]},
          qiyongNianyue: {rules: [
          ]},
          kaigongNianyue: {rules: [
          ]},
          mianji: {rules: [
          ]},
          jianzhuDuli: {rules: [
          ]},
          jianzhuFushu: {rules: [
          ]},
          jiegouTujian: {rules: [
          ]},
          jiegouZhuangpei: {rules: [
          ]},
          nan: {rules: [
          ]},
          nv: {rules: [
          ]},
          tongyong: {rules: [
          ]},
          wuzhangai: {rules: [
          ]},
          xiaobiandou: {rules: [
          ]},
          fushe: {rules: [
          ]},
          kongtiao: {rules: [
          ]},
          zhihui: {rules: [
          ]},
          remarks: {rules: [
          ]},
        },
        url: {
          add: "/summary/toiletSummary/add",
          edit: "/summary/toiletSummary/edit",
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
          this.form.setFieldsValue(pick(this.model,'xianqu','leixing','bianhao','mingcheng','dizhi','leibie','qiyongNianyue','kaigongNianyue','mianji','jianzhuDuli','jianzhuFushu','jiegouTujian','jiegouZhuangpei','nan','nv','tongyong','wuzhangai','xiaobiandou','fushe','kongtiao','zhihui','remarks'))
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
        this.form.setFieldsValue(pick(row,'xianqu','leixing','bianhao','mingcheng','dizhi','leibie','qiyongNianyue','kaigongNianyue','mianji','jianzhuDuli','jianzhuFushu','jiegouTujian','jiegouZhuangpei','nan','nv','tongyong','wuzhangai','xiaobiandou','fushe','kongtiao','zhihui','remarks'))
      },

      
    }
  }
</script>