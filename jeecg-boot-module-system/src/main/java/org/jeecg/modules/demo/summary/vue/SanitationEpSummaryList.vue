<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->
    
    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('环卫防疫汇总表')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
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

  export default {
    name: "SanitationEpSummaryList",
    mixins:[JeecgListMixin],
    components: {
      SanitationEpSummaryModal
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
          {
            title:'生活垃圾无害化处理',
            align:"center",
            dataIndex: 'hjwsLajcl'
          },
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
            title:'人流量，设施消杀及运行情况',
            align:"center",
            dataIndex: 'gongyFyqk'
          },
          {
            title:'其它',
            align:"center",
            dataIndex: 'other'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/summary/sanitationEpSummary/list",
          delete: "/summary/sanitationEpSummary/delete",
          deleteBatch: "/summary/sanitationEpSummary/deleteBatch",
          exportXlsUrl: "/summary/sanitationEpSummary/exportXls",
          importExcelUrl: "summary/sanitationEpSummary/importExcel",
        },
        dictOptions:{
        },
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      initDictConfig(){
      }
       
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>