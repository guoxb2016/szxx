<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :md="6" :sm="8">
            <a-form-item label="所属区县">
              <j-select-depart v-model="queryParam.sysOrgName"  customReturnField="departName"/>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" v-has="'ss:export'" icon="download" @click="handleExportXls('环卫信息表')">导出</a-button>
     <!-- <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>-->
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{fixed:true,selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        :scroll="tableScroll"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="图片不存在" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="uploadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <sanitationSummary-modal ref="modalForm" @ok="modalFormOk"></sanitationSummary-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import SanitationSummaryModal from './modules/SanitationSummaryModal'
  import {initDictOptions, filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import JSelectDepart from '@/components/jeecgbiz/JSelectDepart'

  export default {
    name: "SanitationSummaryList",
    mixins:[JeecgListMixin],
    components: {
      SanitationSummaryModal,
      JSelectDepart
    },
    data () {
      return {
        description: '环卫信息表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'所属区县',
            align:"center",
            dataIndex: 'sysOrgName',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['sysOrgName'], text+"")
              }
            }
          },
          {
            title:'环卫管理人员',
            align:"center",
            dataIndex: 'hwglry'
          },
          {
            title:'保洁员（操作工）',
            align:"center",
            dataIndex: 'grBaojy'
          },
          {
            title:'扫洒司机',
            align:"center",
            dataIndex: 'grSijiSais'
          },
          {
            title:'垃圾运输司机',
            align:"center",
            dataIndex: 'grSijiLajysh'
          },
          {
            title:'男',
            align:"center",
            dataIndex: 'grNan'
          },
          {
            title:'女',
            align:"center",
            dataIndex: 'grNv'
          },
          {
            title:'45岁及以下',
            align:"center",
            dataIndex: 'grLessand45'
          },
          {
            title:'45岁以上',
            align:"center",
            dataIndex: 'grMore45'
          },
          {
            title:'临时工',
            align:"center",
            dataIndex: 'grLingshg'
          },
          {
            title:'正式工',
            align:"center",
            dataIndex: 'grZhengshg'
          },
          {
            title:'干扫车',
            align:"center",
            dataIndex: 'chlGans'
          },
          {
            title:'洗扫车',
            align:"center",
            dataIndex: 'chlXis'
          },
          {
            title:'真空吸尘车',
            align:"center",
            dataIndex: 'chlXich'
          },
          {
            title:'扫道小型车',
            align:"center",
            dataIndex: 'chlSaodXiao'
          },
          {
            title:'扫道大型车',
            align:"center",
            dataIndex: 'chlSaodDa'
          },
          {
            title:'洒水小型车',
            align:"center",
            dataIndex: 'chlSashXiao'
          },
          {
            title:'洒水大型车',
            align:"center",
            dataIndex: 'chlSashDa'
          },
          {
            title:'雾炮车',
            align:"center",
            dataIndex: 'chlWuc'
          },
          {
            title:'垃圾运输车',
            align:"center",
            dataIndex: 'chlLajysh'
          },
          {
            title:'电动保洁车',
            align:"center",
            dataIndex: 'chlDiandbj'
          },
          {
            title:'移动厕所车',
            align:"center",
            dataIndex: 'ydcsc'
          },
          {
            title:'护栏清洗车',
            align:"center",
            dataIndex: 'hlqxc'
          },
          {
            title:'普通垃圾站',
            align:"center",
            dataIndex: 'shshLajzh'
          },
          {
            title:'分类垃圾站',
            align:"center",
            dataIndex: 'shshFenleilajzh'
          },
          {
            title:'一类环卫公厕',
            align:"center",
            dataIndex: 'shshHuanwgcYil'
          },
          {
            title:'二类环卫公厕',
            align:"center",
            dataIndex: 'shshHuanwgcErl'
          },
          {
            title:'三类环卫公厕',
            align:"center",
            dataIndex: 'shshHuanwgcSl'
          },
          {
            title:'社会公厕',
            align:"center",
            dataIndex: 'shshShehgc'
          },
          {
            title:'分类果壳箱',
            align:"center",
            dataIndex: 'shshGuokx'
          },
          {
            title:'分类垃圾桶',
            align:"center",
            dataIndex: 'shshLajt'
          },
          {
            title:'严控区道路数量',
            align:"center",
            dataIndex: 'bjYlShul'
          },
          {
            title:'严控区道路长度（m）',
            align:"center",
            dataIndex: 'bjYlChangd'
          },
          {
            title:'严控区道路面积（m²）',
            align:"center",
            dataIndex: 'bjYlMianj'
          },
          {
            title:'严控区道路经费标准（元）',
            align:"center",
            dataIndex: 'bjYlJinfbzh'
          },
          {
            title:'控制区道路数量',
            align:"center",
            dataIndex: 'bjErlShul'
          },
          {
            title:'控制区道路长度（m）',
            align:"center",
            dataIndex: 'bjErlChangd'
          },
          {
            title:'控制区道路面积（m²）',
            align:"center",
            dataIndex: 'bjErlMianj'
          },
          {
            title:'控制区道路经费标准（元）',
            align:"center",
            dataIndex: 'bjErlJinfbzh'
          },
          {
            title:'一般区道路数量',
            align:"center",
            dataIndex: 'bjSslShul'
          },
          {
            title:'一般区道路长度（m）',
            align:"center",
            dataIndex: 'bjSslChangd'
          },
          {
            title:'一般区道路面积（m²）',
            align:"center",
            dataIndex: 'bjSslMianj'
          },
          {
            title:'一般区道路经费标准（元）',
            align:"center",
            dataIndex: 'bjSslJinfbzh'
          },
          {
            title:'社区数量',
            align:"center",
            dataIndex: 'bjShqShul'
          },
          {
            title:'社区面积（m²）',
            align:"center",
            dataIndex: 'bjShqMianj'
          },
          {
            title:'社区经费标准（元）',
            align:"center",
            dataIndex: 'bjShqJinfbzh'
          },
          {
            title:'道路总面积（m²）',
            align:"center",
            dataIndex: 'bjZongmj'
          },
          {
            title:'服务外包面积（m²）',
            align:"center",
            dataIndex: 'bjWaibmj'
          },
          {
            title:'机械化清扫面积（m²）',
            align:"center",
            dataIndex: 'bjJixhQingsMianj'
          },
          {
            title:'机械化清扫长度（m）',
            align:"center",
            dataIndex: 'bjJixhQingsCang'
          },
          {
            title:'市场化率',
            align:"center",
            dataIndex: 'bjShichh'
          },
          {
            title:'机械化率',
            align:"center",
            dataIndex: 'bjJixieh'
          },
          {
            title:'市场化作业单位',
            align:"center",
            dataIndex: 'bjShichDanwei'
          },
          {
            title:'服务外包经费标准（m）',
            align:"center",
            dataIndex: 'bjWaibJinfbzh'
          },
         /* {
            title:'道路清扫保洁环卫市场化类别',
            align:"center",
            dataIndex: 'shichhLeib'
          },*/
          {
            title:'道路清扫保洁环卫市场化作业单位',
            align:"center",
            dataIndex: 'shichhZuiyeDanwei'
          },
          {
            title:'经费标准（元）',
            align:"center",
            dataIndex: 'shichhJinfeiBiaozh'
          },
      /*    {
            title:'公厕管理环卫市场化',
            align:"center",
            dataIndex: 'shichhgcLeib'
          },*/
          {
            title:'公厕管理环卫市场化作业单位',
            align:"center",
            dataIndex: 'shichhgcZuiyeDanwei'
          },
          {
            title:'经费标准（元）',
            align:"center",
            dataIndex: 'shichhgcJinfeiBiaozh'
          },
     /*     {
            title:'垃圾站环卫市场化',
            align:"center",
            dataIndex: 'shichhljzLeib'
          },*/
          {
            title:'垃圾站环卫市场化作业单位',
            align:"center",
            dataIndex: 'shichhljzZuiyeDanwei'
          },
          {
            title:'经费标准（元）',
            align:"center",
            dataIndex: 'shichhljzJinfeiBiaozh'
          },
      /*    {
            title:'垃圾运输环卫市场化',
            align:"center",
            dataIndex: 'shichhljysLeib'
          },*/
          {
            title:'垃圾运输环卫市场化作业单位',
            align:"center",
            dataIndex: 'shichhljysZuiyeDanwei'
          },
          {
            title:'经费标准（元）',
            align:"center",
            dataIndex: 'shichhljysJinfeiBiaozh'
          },
      /*    {
            title:'垃圾分类环卫市场化',
            align:"center",
            dataIndex: 'shichhljflLeib'
          },*/
          {
            title:'垃圾分类环卫市场化作业单位',
            align:"center",
            dataIndex: 'shichhljflZuiyeDanwei'
          },
          {
            title:'经费标准（元）',
            align:"center",
            dataIndex: 'shichhljflJinfeiBiaozh'
          },
    /*      {
            title:'其他环卫市场化',
            align:"center",
            dataIndex: 'shichhqtLeib'
          },*/
          {
            title:'其他环卫市场化作业单位',
            align:"center",
            dataIndex: 'shichhqtZuiyeDanwei'
          },
          {
            title:'经费标准（元）',
            align:"center",
            dataIndex: 'shichhqtJinfeiBiaozh'
          },
          {
            title:'单位组织体检人数',
            align:"center",
            dataIndex: 'fulTijian'
          },
          {
            title:'一年四节节日福利标准',
            align:"center",
            dataIndex: 'fulJiejia'
          },
          {
            title:'购意外伤害险人数	',
            align:"center",
            dataIndex: 'fulYiwaiBaoxian'
          },
          {
            title:'社会保险缴纳人数',
            align:"center",
            dataIndex: 'fulShehuiBaoxian'
          },
          {
            title:'意外伤害险购买金额（元）',
            align:"center",
            dataIndex: 'fulYiwaiBaoxianJinge'
          },
          {
            title:'基础工资标准（元）',
            align:"center",
            dataIndex: 'fulGongzbiaozh'
          },
          {
            title:'工龄工资标准（元）',
            align:"center",
            dataIndex: 'fulGonglgzbiaozh'
          },
          {
            title:'缴纳公积金人数',
            align:"center",
            dataIndex: 'fulGongjijing'
          },
          {
            title:'上报时间',
            align:"center",
            dataIndex: 'createTime'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/summary/sanitationSummary/listByPerm",
          delete: "/summary/sanitationSummary/delete",
          deleteBatch: "/summary/sanitationSummary/deleteBatch",
          exportXlsUrl: "/summary/sanitationSummary/exportXls3",
          importExcelUrl: "summary/sanitationSummary/importExcel",
        },
        dictOptions:{
         sysOrgName:[],
        },
        tableScroll:{x :81*147+50}
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      initDictConfig(){
        initDictOptions('sys_depart,depart_name,id').then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'sysOrgName', res.result)
          }
        })
      }

    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>