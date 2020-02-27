<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :md="6" :sm="8">
            <a-form-item label="所属区县">
              <j-select-depart v-model="queryParam.xianqu" customReturnField="departName"/>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="公厕类型">
              <j-dict-select-tag placeholder="请选择公厕类型" v-model="queryParam.leixing" dictCode="toilet_type"/>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :md="12" :sm="16">
              <a-form-item label="启用年月">
                <j-date placeholder="请选择开始日期" class="query-group-cust" v-model="queryParam.qiyongNianyue_begin"></j-date>
                <span class="query-group-split-cust"></span>
                <j-date placeholder="请选择结束日期" class="query-group-cust" v-model="queryParam.qiyongNianyue_end"></j-date>
              </a-form-item>
            </a-col>
            <a-col :md="12" :sm="16">
              <a-form-item label="开工年月">
                <j-date placeholder="请选择开始日期" class="query-group-cust" v-model="queryParam.kaigongNianyue_begin"></j-date>
                <span class="query-group-split-cust"></span>
                <j-date placeholder="请选择结束日期" class="query-group-cust" v-model="queryParam.kaigongNianyue_end"></j-date>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="独立">
                <j-dict-select-tag placeholder="请选择独立" v-model="queryParam.jianzhuDuli" dictCode="yes_or_no"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="附属">
                <j-dict-select-tag placeholder="请选择附属" v-model="queryParam.jianzhuFushu" dictCode="yes_or_no"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="土建式">
                <j-dict-select-tag placeholder="请选择土建式" v-model="queryParam.jiegouTujian" dictCode="yes_or_no"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="装配式">
                <j-dict-select-tag placeholder="请选择装配式" v-model="queryParam.jiegouZhuangpei" dictCode="yes_or_no"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="是否安装空调">
                <j-dict-select-tag placeholder="请选择是否安装空调" v-model="queryParam.kongtiao" dictCode="yes_or_no"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
              <a-form-item label="是否安装智慧系统">
                <j-dict-select-tag placeholder="请选择是否安装智慧系统" v-model="queryParam.zhihui" dictCode="yes_or_no"/>
              </a-form-item>
            </a-col>
          </template>
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
      <a-button type="primary" v-has="'ts:export'" icon="download" @click="handleExportXls('新建公厕统计明细表')">导出</a-button>
      <!--<a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
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

    <toiletSummary-modal ref="modalForm" @ok="modalFormOk"></toiletSummary-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ToiletSummaryModal from './modules/ToiletSummaryModal'
  import JDictSelectTag from '@/components/dict/JDictSelectTag.vue'
  import JDate from '@/components/jeecg/JDate.vue'
  import JSelectDepart from '@/components/jeecgbiz/JSelectDepart'
  import {initDictOptions, filterMultiDictText} from '@/components/dict/JDictSelectUtil'

  export default {
    name: "ToiletSummaryList",
    mixins:[JeecgListMixin],
    components: {
      JDictSelectTag,
      JDate,
      ToiletSummaryModal,
      JSelectDepart
    },
    data () {
      return {
        description: '新建公厕统计明细表管理页面',
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
            title:'县区',
            align:"center",
            dataIndex: 'xianqu',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['xianqu'], text+"")
              }
            }
          },
          {
            title:'公厕类型',
            align:"center",
            dataIndex: 'leixing',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['leixing'], text+"")
              }
            }
          },
          {
            title:'公厕编号',
            align:"center",
            dataIndex: 'bianhao'
          },
          {
            title:'公厕名称',
            align:"center",
            dataIndex: 'mingcheng'
          },
          {
            title:'地址',
            align:"center",
            dataIndex: 'dizhi'
          },
          {
            title:'公厕类别',
            align:"center",
            dataIndex: 'leibie'
          },
          {
            title:'启用年月',
            align:"center",
            dataIndex: 'qiyongNianyue',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'开工年月',
            align:"center",
            dataIndex: 'kaigongNianyue',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'建筑面积（㎡)',
            align:"center",
            dataIndex: 'mianji'
          },
          {
            title:'独立',
            align:"center",
            dataIndex: 'jianzhuDuli',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['jianzhuDuli'], text+"")
              }
            }
          },
          {
            title:'附属',
            align:"center",
            dataIndex: 'jianzhuFushu',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['jianzhuFushu'], text+"")
              }
            }
          },
          {
            title:'土建式',
            align:"center",
            dataIndex: 'jiegouTujian',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['jiegouTujian'], text+"")
              }
            }
          },
          {
            title:'装配式',
            align:"center",
            dataIndex: 'jiegouZhuangpei',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['jiegouZhuangpei'], text+"")
              }
            }
          },
          {
            title:'男蹲位数量',
            align:"center",
            dataIndex: 'nan'
          },
          {
            title:'女蹲位数量',
            align:"center",
            dataIndex: 'nv'
          },
          {
            title:'通用蹲位数量',
            align:"center",
            dataIndex: 'tongyong'
          },
          {
            title:'无障碍蹲位数量',
            align:"center",
            dataIndex: 'wuzhangai'
          },
          {
            title:'小便斗数量',
            align:"center",
            dataIndex: 'xiaobiandou'
          },
          {
            title:'附设功能（驿站、垃圾站、环卫管理用房等）',
            align:"center",
            dataIndex: 'fushe'
          },
          {
            title:'是否安装空调',
            align:"center",
            dataIndex: 'kongtiao',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['kongtiao'], text+"")
              }
            }
          },
          {
            title:'是否安装智慧系统',
            align:"center",
            dataIndex: 'zhihui',
            customRender:(text)=>{
              if(!text){
                return ''
              }else{
                return filterMultiDictText(this.dictOptions['zhihui'], text+"")
              }
            }
          },
          {
            title:'备注（进展情况）',
            align:"center",
            dataIndex: 'remarks'
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
          list: "/summary/toiletSummary/listByPerm",
          delete: "/summary/toiletSummary/delete",
          deleteBatch: "/summary/toiletSummary/deleteBatch",
          exportXlsUrl: "/summary/toiletSummary/exportXls2",
          importExcelUrl: "summary/toiletSummary/importExcel",
        },
        dictOptions:{
         xianqu:[],
         leixing:[],
         jianzhuDuli:[],
         jianzhuFushu:[],
         jiegouTujian:[],
         jiegouZhuangpei:[],
         kongtiao:[],
         zhihui:[],
        },
        tableScroll:{x :22*147+50}
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
            this.$set(this.dictOptions, 'xianqu', res.result)
          }
        })
        initDictOptions('toilet_type').then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'leixing', res.result)
          }
        })
        initDictOptions('yes_or_no').then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'jianzhuDuli', res.result)
          }
        })
        initDictOptions('yes_or_no').then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'jianzhuFushu', res.result)
          }
        })
        initDictOptions('yes_or_no').then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'jiegouTujian', res.result)
          }
        })
        initDictOptions('yes_or_no').then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'jiegouZhuangpei', res.result)
          }
        })
        initDictOptions('yes_or_no').then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'kongtiao', res.result)
          }
        })
        initDictOptions('yes_or_no').then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'zhihui', res.result)
          }
        })
      }

    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>