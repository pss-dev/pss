<template>
  <div>
    <el-container height="600px">
      <el-aside class="role-cointainer">
        <el-container class="source-container">
          <el-main>
            <el-table
              ref="roleTable"
              :data="tableData"
              highlight-current-row
              @current-change="handleCurrentChange"
              style="width: 100%"
            >
              <el-table-column prop="name" label="名称"></el-table-column>
            </el-table>
          </el-main>
          <el-footer>
            <el-row>
              <el-col :span="12">
                <el-button @click="newRole">添加</el-button>
              </el-col>
              <el-col :span="12">
                <el-button>删除</el-button>
              </el-col>
            </el-row>
          </el-footer>
        </el-container>
        <div class="role-pane"></div>
      </el-aside>
      <el-main class="rule-pane">
        <label v-if="isSelectedInfoInvalid()">please select a role</label>
        <el-container v-if="!isSelectedInfoInvalid()" class="source-container">
          <el-header>
            <el-row class="role-name-pane">
              <el-col :span="3">角色名称：</el-col>
              <el-col :span="6">
                <el-input v-model="selectedInfo.name" @input="nameChange"></el-input>
              </el-col>
            </el-row>
          </el-header>
          <el-main class="source-main">
            <el-container>
              <el-aside width="200px">
                <el-tree
                  :data="treeData"
                  @node-click="handleNodeClick"
                  :expand-on-click-node="false"
                ></el-tree>
              </el-aside>
              <el-main>
                <el-card>
                  <div v-if="selectedSourceValid()">
                    <el-checkbox @change="readChange" v-model="readChecked">读</el-checkbox>
                    <el-checkbox @change="writeChange" v-model="writeChecked">写</el-checkbox>
                    <el-checkbox @change="deleteChange" v-model="deleteChecked">删</el-checkbox>
                    <el-checkbox
                      @change="verifyChange"
                      v-model="verifyChecked"
                      v-if="verifyVisiable"
                    >审核</el-checkbox>
                  </div>
                  <div v-if="!selectedSourceValid()">请选择资源</div>
                </el-card>
              </el-main>
            </el-container>
          </el-main>
          <el-footer>
            <el-row>
              <el-col :span="4">
                <el-button @click="submitData">保存</el-button>
              </el-col>
            </el-row>
          </el-footer>
        </el-container>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import BseInfo from '../mixIns/base-info'
import TableBaseInfo from '@/views/mixIns/table-base-info.js'
import RuleTool from '@/views/constant/rule-tool.js'
import Tool from '@/views/constant/tool.js'

import RoleInfoApi from '../../../api/role-info-api/roleInfoApi'

export default {
  name: "RoleInfo",

  mixins: [BseInfo, TableBaseInfo],

  components: {
  },

  props: {
  },

  data () {
    return {
      titData:
        [{ prop: "name", label: "名称" }],

      tableData: [],

      treeData: [
        {
          label: '基本信息',
          key: RuleTool.source.baseInfo,
          children: [{
            label: '商品档案',
            key: RuleTool.source.product,
          },
          {
            label: '计量单位',
            key: RuleTool.source.unit,
          },
          {
            label: '价格名称',
            key: RuleTool.source.price,
          },
          {
            label: '来往单位',
            key: RuleTool.source.company,
          },
          {
            label: '仓库信息',
            key: RuleTool.source.depot,
          },
          {
            label: '部门',
            key: RuleTool.source.department,
          },
          {
            label: '分支机构',
            key: RuleTool.source.branch,
          },]
        },
        {
          label: '单据',
          key: RuleTool.source.orderForm,
        },
        {
          label: '管理设置',
          key: RuleTool.source.manage,
          children: [{
            label: '角色管理',
            key: RuleTool.source.role,
          },
          {
            label: '员工信息',
            key: RuleTool.source.employee,
          },
          {
            label: '账户信息',
            key: RuleTool.source.account,
          }]
        },
        {
          label: '统计查询',
          key: RuleTool.source.statistic,
          children: [{
            label: '日志',
            key: RuleTool.source.log,
          },
          {
            label: '营收',
            key: RuleTool.source.revenue,
          }]
        },
      ],

      selectedSource: -1,
      selectedSourceRule: {},

      readChecked: false,
      writeChecked: false,
      deleteChecked: false,
      verifyChecked: false,
      verifyVisiable: false,
    }
  },

  methods: {
    handleCurrentChange (data) {
      this.selectedInfo = data;
      this.selectedSource = -1;
      this.selectedSourceRule = {};
    },

    nameChange () {
      if (this.selectedInfo.actionType != Tool.actionType.add) {
        this.selectedInfo.actionType = Tool.actionType.update;
      }
    },

    newRole () {
      let newRole = {
        id: null,
        name: "新角色1",
        actionType: Tool.actionType.add,
        rules: []
      };

      this.tableData.push(newRole);
      this.$refs["roleTable"].setCurrentRow(newRole);
    },

    submitData () {
      console.log("=====  submit ", this.selectedInfo);
      RoleInfoApi.setRoleInfo(this.selectedInfo).then(
        () => {
          this.getRoleInfo();
        });
    },

    deleteInfo () {
      RoleInfoApi
        .deleteRoleInfo(this.selectedInfo)
        .then(() => {
          this.getRoleInfo();
        });
    },

    getRoleInfo () {
      RoleInfoApi.getRoleInfo().then(
        (res) => {
          this.setResponseResult(res.data);
          this.handleCurrentChange(null);
        });
    },

    handleNodeClick (data) {
      this.selectedSource = data.key;

      let rules = this.selectedInfo.rules.filter(function (rule) {
        return rule.source == data.key;
      });

      if (rules.length == 1) {
        this.selectedSourceRule = rules[0];
      }
      else {
        let rule = this.getNewRule(this.selectedSource);
        this.selectedInfo.rules.push(rule);
        this.selectedSourceRule = rule;
      }

      if (this.selectedSource == RuleTool.source.orderForm) {
        this.verifyVisiable = true;
      }
      else {
        this.verifyVisiable = false;
      }

      this.setRuleCheckBoxValue();

      console.log("==handleNodeClick ", this.selectedInfo);
    },

    getNewRule (sourceValue) {
      let rule = {
        id: null,
        source: sourceValue,
        value: 0,
        actionType: Tool.actionType.add,
      };

      return rule;
    },

    selectedSourceValid () {
      return this.selectedSource != -1;
    },

    handleRuleChange () {
      if (this.selectedSourceRule.actionType != Tool.actionType.add) {
        this.selectedSourceRule.actionType = Tool.actionType.update;
      }
    },

    setRuleCheckBoxValue () {
      this.readChecked = (this.selectedSourceRule.value & RuleTool.rule.read) != 0;
      this.writeChecked = (this.selectedSourceRule.value & RuleTool.rule.write) != 0;
      this.deleteChecked = (this.selectedSourceRule.value & RuleTool.rule.delete) != 0;
      this.verifyChecked = (this.selectedSourceRule.value & RuleTool.rule.verify) != 0;
    },

    readChange (value) {
      if (value) {
        this.selectedSourceRule.value = this.selectedSourceRule.value | RuleTool.rule.read;
      }
      else {
        this.selectedSourceRule.value = this.selectedSourceRule.value & (~RuleTool.rule.read);
      }

      this.handleRuleChange();

      console.log("======= this.selectedSourceRule ", this.selectedSourceRule);
    },

    writeChange (value) {
      if (value) {
        this.selectedSourceRule.value = this.selectedSourceRule.value | RuleTool.rule.write;
      }
      else {
        this.selectedSourceRule.value = this.selectedSourceRule.value & (~RuleTool.rule.write);
      }

      this.handleRuleChange();

      console.log("======= this.selectedSourceRule ", this.selectedSourceRule);
    },

    deleteChange (value) {
      if (value) {
        this.selectedSourceRule.value = this.selectedSourceRule.value | RuleTool.rule.delete;
      }
      else {
        this.selectedSourceRule.value = this.selectedSourceRule.value & (~RuleTool.rule.delete);
      }

      this.handleRuleChange();

      console.log("======= this.selectedSourceRule ", this.selectedSourceRule);
    },

    verifyChange (value) {
      if (value) {
        this.selectedSourceRule.value = this.selectedSourceRule.value | RuleTool.rule.verify;
      }
      else {
        this.selectedSourceRule.value = this.selectedSourceRule.value & (~RuleTool.rule.verify);
      }

      this.handleRuleChange();

      console.log("======= this.selectedSourceRule ", this.selectedSourceRule);
    }
  },

  created: function () {
    this.getRoleInfo();
  }
}
</script>

<style>
.role-name-pane {
  line-height: 40px;
}

.role-cointainer {
  width: 400px;
  min-height: 400px;
  text-align: center;
  border-right: 1px solid gray;
}

.source-container {
  height: 100%;
}

.el-footer {
  height: 32px;
}

.rule-pane {
  padding-bottom: 0px;
}
</style>
