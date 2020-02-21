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

        <a-form-item label="保洁员（操作工）" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'grBaojy', validatorRules.grBaojy]" placeholder="请输入保洁员（操作工）" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="扫洒司机" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'grSijiSais', validatorRules.grSijiSais]" placeholder="请输入扫洒司机" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="垃圾运输司机" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'grSijiLajysh', validatorRules.grSijiLajysh]" placeholder="请输入垃圾运输司机" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="男" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'grNan', validatorRules.grNan]" placeholder="请输入男" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="女" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'grNv', validatorRules.grNv]" placeholder="请输入女" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="45岁及以下" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'grLessand45', validatorRules.grLessand45]" placeholder="请输入45岁及以下" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="45岁以上" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'grMore45', validatorRules.grMore45]" placeholder="请输入45岁以上" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="临时工" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'grLingshg', validatorRules.grLingshg]" placeholder="请输入临时工" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="正式工" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'grZhengshg', validatorRules.grZhengshg]" placeholder="请输入正式工" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="环卫管理人员" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'grHuanwgl', validatorRules.grHuanwgl]" placeholder="请输入环卫管理人员" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="干扫车" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'chlGans', validatorRules.chlGans]" placeholder="请输入干扫车" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="洗扫车" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'chlXis', validatorRules.chlXis]" placeholder="请输入洗扫车" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="真空吸尘车" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'chlXich', validatorRules.chlXich]" placeholder="请输入真空吸尘车" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="扫道小型车" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'chlSaodXiao', validatorRules.chlSaodXiao]" placeholder="请输入扫道小型车" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="扫道大型车" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'chlSaodDa', validatorRules.chlSaodDa]" placeholder="请输入扫道大型车" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="洒水小型车" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'chlSashXiao', validatorRules.chlSashXiao]" placeholder="请输入洒水小型车" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="洒水大型车" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'chlSashDa', validatorRules.chlSashDa]" placeholder="请输入洒水大型车" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="雾炮车" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'chlWuc', validatorRules.chlWuc]" placeholder="请输入雾炮车" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="垃圾运输车" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'chlLajysh', validatorRules.chlLajysh]" placeholder="请输入垃圾运输车" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="电动保洁车" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'chlDiandbj', validatorRules.chlDiandbj]" placeholder="请输入电动保洁车" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="普通垃圾站" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'shshLajzh', validatorRules.shshLajzh]" placeholder="请输入普通垃圾站" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="分类垃圾站" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'shshFenleilajzh', validatorRules.shshFenleilajzh]" placeholder="请输入分类垃圾站" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="一类环卫公厕" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'shshHuanwgcYil', validatorRules.shshHuanwgcYil]" placeholder="请输入一类环卫公厕" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="二类环卫公厕" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'shshHuanwgcErl', validatorRules.shshHuanwgcErl]" placeholder="请输入二类环卫公厕" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="社会公厕" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'shshShehgc', validatorRules.shshShehgc]" placeholder="请输入社会公厕" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="分类果壳箱" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'shshGuokx', validatorRules.shshGuokx]" placeholder="请输入分类果壳箱" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="分类垃圾桶" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'shshLajt', validatorRules.shshLajt]" placeholder="请输入分类垃圾桶" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="一类道路数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjYlShul', validatorRules.bjYlShul]" placeholder="请输入一类道路数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="一类道路长度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjYlChangd', validatorRules.bjYlChangd]" placeholder="请输入一类道路长度" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="一类道路面积" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjYlMianj', validatorRules.bjYlMianj]" placeholder="请输入一类道路面积" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="一类道路经费标准" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjYlJinfbzh', validatorRules.bjYlJinfbzh]" placeholder="请输入一类道路经费标准" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="二类道路数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjErlShul', validatorRules.bjErlShul]" placeholder="请输入二类道路数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="二类道路长度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjErlChangd', validatorRules.bjErlChangd]" placeholder="请输入二类道路长度" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="二类道路面积" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjErlMianj', validatorRules.bjErlMianj]" placeholder="请输入二类道路面积" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="二类道路经费标准" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjErlJinfbzh', validatorRules.bjErlJinfbzh]" placeholder="请输入二类道路经费标准" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="三四类道路数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjSslShul', validatorRules.bjSslShul]" placeholder="请输入三四类道路数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="三四类道路长度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjSslChangd', validatorRules.bjSslChangd]" placeholder="请输入三四类道路长度" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="三四类道路面积" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjSslMianj', validatorRules.bjSslMianj]" placeholder="请输入三四类道路面积" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="三四类道路经费标准" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjSslJinfbzh', validatorRules.bjSslJinfbzh]" placeholder="请输入三四类道路经费标准" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="社区数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjShqShul', validatorRules.bjShqShul]" placeholder="请输入社区数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="社区面积" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjShqMianj', validatorRules.bjShqMianj]" placeholder="请输入社区面积" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="社区经费标准" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjShqJinfbzh', validatorRules.bjShqJinfbzh]" placeholder="请输入社区经费标准" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="道路总面积" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjZongmj', validatorRules.bjZongmj]" placeholder="请输入道路总面积" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="服务外包面积" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjWaibmj', validatorRules.bjWaibmj]" placeholder="请输入服务外包面积" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="机械化清扫面积" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjJixhQingsMianj', validatorRules.bjJixhQingsMianj]" placeholder="请输入机械化清扫面积" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="机械化清扫长度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjJixhQingsCang', validatorRules.bjJixhQingsCang]" placeholder="请输入机械化清扫长度" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="市场化率" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjShichh', validatorRules.bjShichh]" placeholder="请输入市场化率" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="机械化率" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'bjJixieh', validatorRules.bjJixieh]" placeholder="请输入机械化率" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="市场化作业单位" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'bjShichDanwei', validatorRules.bjShichDanwei]" placeholder="请输入市场化作业单位"></a-input>
        </a-form-item>
        <a-form-item label="服务外包经费标准" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'bjWaibJinfbzh', validatorRules.bjWaibJinfbzh]" placeholder="请输入服务外包经费标准"></a-input>
        </a-form-item>
        <a-form-item label="环卫市场化类别" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'shichhLeib', validatorRules.shichhLeib]" placeholder="请输入环卫市场化类别"></a-input>
        </a-form-item>
        <a-form-item label="环卫市场化作业单位" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'shichhZuiyeDanwei', validatorRules.shichhZuiyeDanwei]" placeholder="请输入环卫市场化作业单位"></a-input>
        </a-form-item>
        <a-form-item label="经费标准" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'shichhJinfeiBiaozh', validatorRules.shichhJinfeiBiaozh]" placeholder="请输入经费标准"></a-input>
        </a-form-item>
        <a-form-item label="单位组织体检人数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'fulTijian', validatorRules.fulTijian]" placeholder="请输入单位组织体检人数" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="节日福利标准（包括春节、端午节、中秋节及环卫工人节）	" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'fulJiejia', validatorRules.fulJiejia]" placeholder="请输入节日福利标准（包括春节、端午节、中秋节及环卫工人节）	"></a-input>
        </a-form-item>
        <a-form-item label="购意外伤害险人数	" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'fulYiwaiBaoxian', validatorRules.fulYiwaiBaoxian]" placeholder="请输入购意外伤害险人数	" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="社会保险缴纳人数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'fulShehuiBaoxian', validatorRules.fulShehuiBaoxian]" placeholder="请输入社会保险缴纳人数" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="意外伤害险购买金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'fulYiwaiBaoxianJinge', validatorRules.fulYiwaiBaoxianJinge]" placeholder="请输入意外伤害险购买金额" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="基础工资标准" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'fulGongzbiaozh', validatorRules.fulGongzbiaozh]" placeholder="请输入基础工资标准"></a-input>
        </a-form-item>
        <a-form-item label="工龄工资 标准" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'fulGonglgzbiaozh', validatorRules.fulGonglgzbiaozh]" placeholder="请输入工龄工资 标准"></a-input>
        </a-form-item>
        <a-form-item label="缴纳公积金人数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'fulGongjijing', validatorRules.fulGongjijing]" placeholder="请输入缴纳公积金人数" style="width: 100%"/>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: "SanitationSummaryModal",
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
          grBaojy: {rules: [
          ]},
          grSijiSais: {rules: [
          ]},
          grSijiLajysh: {rules: [
          ]},
          grNan: {rules: [
          ]},
          grNv: {rules: [
          ]},
          grLessand45: {rules: [
          ]},
          grMore45: {rules: [
          ]},
          grLingshg: {rules: [
          ]},
          grZhengshg: {rules: [
          ]},
          grHuanwgl: {rules: [
          ]},
          chlGans: {rules: [
          ]},
          chlXis: {rules: [
          ]},
          chlXich: {rules: [
          ]},
          chlSaodXiao: {rules: [
          ]},
          chlSaodDa: {rules: [
          ]},
          chlSashXiao: {rules: [
          ]},
          chlSashDa: {rules: [
          ]},
          chlWuc: {rules: [
          ]},
          chlLajysh: {rules: [
          ]},
          chlDiandbj: {rules: [
          ]},
          shshLajzh: {rules: [
          ]},
          shshFenleilajzh: {rules: [
          ]},
          shshHuanwgcYil: {rules: [
          ]},
          shshHuanwgcErl: {rules: [
          ]},
          shshShehgc: {rules: [
          ]},
          shshGuokx: {rules: [
          ]},
          shshLajt: {rules: [
          ]},
          bjYlShul: {rules: [
          ]},
          bjYlChangd: {rules: [
          ]},
          bjYlMianj: {rules: [
          ]},
          bjYlJinfbzh: {rules: [
          ]},
          bjErlShul: {rules: [
          ]},
          bjErlChangd: {rules: [
          ]},
          bjErlMianj: {rules: [
          ]},
          bjErlJinfbzh: {rules: [
          ]},
          bjSslShul: {rules: [
          ]},
          bjSslChangd: {rules: [
          ]},
          bjSslMianj: {rules: [
          ]},
          bjSslJinfbzh: {rules: [
          ]},
          bjShqShul: {rules: [
          ]},
          bjShqMianj: {rules: [
          ]},
          bjShqJinfbzh: {rules: [
          ]},
          bjZongmj: {rules: [
          ]},
          bjWaibmj: {rules: [
          ]},
          bjJixhQingsMianj: {rules: [
          ]},
          bjJixhQingsCang: {rules: [
          ]},
          bjShichh: {rules: [
          ]},
          bjJixieh: {rules: [
          ]},
          bjShichDanwei: {rules: [
          ]},
          bjWaibJinfbzh: {rules: [
          ]},
          shichhLeib: {rules: [
          ]},
          shichhZuiyeDanwei: {rules: [
          ]},
          shichhJinfeiBiaozh: {rules: [
          ]},
          fulTijian: {rules: [
          ]},
          fulJiejia: {rules: [
          ]},
          fulYiwaiBaoxian: {rules: [
          ]},
          fulShehuiBaoxian: {rules: [
          ]},
          fulYiwaiBaoxianJinge: {rules: [
          ]},
          fulGongzbiaozh: {rules: [
          ]},
          fulGonglgzbiaozh: {rules: [
          ]},
          fulGongjijing: {rules: [
          ]},
        },
        url: {
          add: "/summary/sanitationSummary/add",
          edit: "/summary/sanitationSummary/edit",
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
          this.form.setFieldsValue(pick(this.model,'grBaojy','grSijiSais','grSijiLajysh','grNan','grNv','grLessand45','grMore45','grLingshg','grZhengshg','grHuanwgl','chlGans','chlXis','chlXich','chlSaodXiao','chlSaodDa','chlSashXiao','chlSashDa','chlWuc','chlLajysh','chlDiandbj','shshLajzh','shshFenleilajzh','shshHuanwgcYil','shshHuanwgcErl','shshShehgc','shshGuokx','shshLajt','bjYlShul','bjYlChangd','bjYlMianj','bjYlJinfbzh','bjErlShul','bjErlChangd','bjErlMianj','bjErlJinfbzh','bjSslShul','bjSslChangd','bjSslMianj','bjSslJinfbzh','bjShqShul','bjShqMianj','bjShqJinfbzh','bjZongmj','bjWaibmj','bjJixhQingsMianj','bjJixhQingsCang','bjShichh','bjJixieh','bjShichDanwei','bjWaibJinfbzh','shichhLeib','shichhZuiyeDanwei','shichhJinfeiBiaozh','fulTijian','fulJiejia','fulYiwaiBaoxian','fulShehuiBaoxian','fulYiwaiBaoxianJinge','fulGongzbiaozh','fulGonglgzbiaozh','fulGongjijing'))
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
        this.form.setFieldsValue(pick(row,'grBaojy','grSijiSais','grSijiLajysh','grNan','grNv','grLessand45','grMore45','grLingshg','grZhengshg','grHuanwgl','chlGans','chlXis','chlXich','chlSaodXiao','chlSaodDa','chlSashXiao','chlSashDa','chlWuc','chlLajysh','chlDiandbj','shshLajzh','shshFenleilajzh','shshHuanwgcYil','shshHuanwgcErl','shshShehgc','shshGuokx','shshLajt','bjYlShul','bjYlChangd','bjYlMianj','bjYlJinfbzh','bjErlShul','bjErlChangd','bjErlMianj','bjErlJinfbzh','bjSslShul','bjSslChangd','bjSslMianj','bjSslJinfbzh','bjShqShul','bjShqMianj','bjShqJinfbzh','bjZongmj','bjWaibmj','bjJixhQingsMianj','bjJixhQingsCang','bjShichh','bjJixieh','bjShichDanwei','bjWaibJinfbzh','shichhLeib','shichhZuiyeDanwei','shichhJinfeiBiaozh','fulTijian','fulJiejia','fulYiwaiBaoxian','fulShehuiBaoxian','fulYiwaiBaoxianJinge','fulGongzbiaozh','fulGonglgzbiaozh','fulGongjijing'))
      },

      
    }
  }
</script>