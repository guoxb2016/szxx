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
      <a-button type="primary" v-has="'ses:export'" icon="download" @click="handleExportXls('环卫防疫汇总表')">导出</a-button>
      <a-button type="primary" v-has="'ses:export:one'" icon="download" @click="handleExportXls1('环卫防疫汇总表')">导出</a-button>
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

    <sanitationEpSummary-modal ref="modalForm" @ok="modalFormOk"></sanitationEpSummary-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import SanitationEpSummaryModal from './modules/SanitationEpSummaryModal'
  import {initDictOptions, filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import JSelectDepart from '@/components/jeecgbiz/JSelectDepart'

  export default {
    name: "SanitationEpSummaryList",
    mixins:[JeecgListMixin],
    components: {
      SanitationEpSummaryModal,
      JSelectDepart
    },
    data () {
      return {
        description: '环卫防疫汇总表管理页面',
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
            title:'环卫作业车辆',
            align:"center",
            dataIndex: 'vehicle'
          },
          {
            title:'环卫作业人员',
            align:"center",
            dataIndex: 'personTime'
          },
          {
            title:'生活垃圾无害化处理',
            align:"center",
            dataIndex: 'garbageDisposal'
          },
          {
            title:'口罩废弃桶',
            align:"center",
            dataIndex: 'kzKouzhFeiqt'
          },
          {
            title:'运输量',
            align:"center",
            dataIndex: 'kzYunsl'
          },
          {
            title:'消杀-公厕',
            align:"center",
            dataIndex: 'xsGongc'
          },
          {
            title:'消杀-垃圾站',
            align:"center",
            dataIndex: 'xsLajz'
          },
          {
            title:'消杀-环卫作业车辆',
            align:"center",
            dataIndex: 'xsHuanwcc'
          },
          {
            title:'消杀-果壳箱与垃圾桶',
            align:"center",
            dataIndex: 'xsGuokx'
          },
         /* {
            title:'生活垃圾无害化处理',
            align:"center",
            dataIndex: 'hjwsLajcl'
          },*/
          {
            title:'单位内部疫情防控消杀处理情况',
            align:"center",
            dataIndex: 'hjwsXiaoscc'
          },
          {
            title:'本单位职工涉湖北接触情况',
            align:"center",
            dataIndex: 'hjwsHubeiJiecqk'
          },
          {
            title:'口罩',
            align:"center",
            dataIndex: 'fywzKouzh'
          },
          {
            title:'酒精',
            align:"center",
            dataIndex: 'fywzJiuj'
          },
          {
            title:'温度计',
            align:"center",
            dataIndex: 'fywzWendj'
          },
          {
            title:'消毒液',
            align:"center",
            dataIndex: 'fywzXiaody'
          },
          {
            title:'人流量、设施消杀及运行情况',
            align:"center",
            dataIndex: 'gongyFyqk'
          },
          {
            title:'其他',
            align:"center",
            dataIndex: 'other'
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
          list: "/summary/sanitationEpSummary/listByPerm",
          delete: "/summary/sanitationEpSummary/delete",
          deleteBatch: "/summary/sanitationEpSummary/deleteBatch",
          exportXlsUrl1: "/summary/sanitationEpSummary/exportXls",
          exportXlsUrl: "/summary/citymanagerEpSummary/exportXls3",
          importExcelUrl: "summary/sanitationEpSummary/importExcel",
        },
        dictOptions:{
         sysOrgName:[],
        },
        tableScroll:{x :19*147+50}
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