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
              <el-table-column prop="name" label="角色列表"></el-table-column>
            </el-table>
          </el-main>
          <el-footer>
            <div class="role-footer">
              <el-button v-if="writePermission" @click="newRole">添加</el-button>
              <el-button v-if="deletePermission" @click="deleteInfo" type="danger">删除</el-button>
            </div>
          </el-footer>
        </el-container>
        <div class="role-pane"></div>
      </el-aside>
      <el-main class="rule-pane">
        <label v-if="isSelectedInfoInvalid()">请选择一个角色</label>
        <el-container v-if="!isSelectedInfoInvalid()" class="source-container">
          <el-header>
            <div class="role-name-pane">
              角色名称：
              <div class="role-name-input">
                <el-input v-model="selectedInfo.name" @input="nameChange"></el-input>
              </div>
            </div>
            <el-row class="role-name-pane">
              <el-col :span="3"></el-col>
              <el-col :span="6"></el-col>
            </el-row>
          </el-header>
          <el-main class="source-main">
            <div class="role-name-pane">权限设置：</div>
            <el-container>
              <el-aside width="200px">
                <el-container>
                  <el-main class="source-tree">
                    <el-tree
                      :data="treeData"
                      @node-click="handleNodeClick"
                      :expand-on-click-node="false"
                    ></el-tree>
                  </el-main>
                </el-container>
              </el-aside>
              <el-main>
                <el-card>
                  <div slot="header">
                    <div v-if="!selectedSourceValid()">请选择资源</div>
                    <span v-if="selectedSourceValid()">{{selectedSourceName}}</span>
                  </div>
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
                </el-card>
              </el-main>
            </el-container>
          </el-main>
          <el-footer>
            <div class="role-footer">
              <el-button v-if="writePermission" @click="submitData">保存</el-button>
            </div>
          </el-footer>
        </el-container>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import BseInfo from "../mixIns/base-info";
import TableBaseInfo from "@/views/mixIns/table-base-info.js";
import RuleTool from "@/views/constant/rule-tool.js";
import PermissionBase from "@/views/mixIns/permission-base.js";
//import Tool from '@/views/constant/tool.js'

import RoleInfoApi from "../../../api/role-info-api/roleInfoApi";

export default {
  name: "RoleInfo",

  mixins: [BseInfo, TableBaseInfo, PermissionBase],

  components: {},

  props: {},

  data () {
    return {
      titData: [{ prop: "name", label: "名称" }],

      tableData: [],

      treeData: [
        {
          label: "基本信息",
          key: RuleTool.resource.baseInfo,
          children: [
            {
              label: "商品档案",
              key: RuleTool.resource.product
            },
            {
              label: "计量单位",
              key: RuleTool.resource.unit
            },
            {
              label: "价格名称",
              key: RuleTool.resource.price
            },
            {
              label: "来往单位",
              key: RuleTool.resource.company
            },
            {
              label: "仓库信息",
              key: RuleTool.resource.depot
            },
            {
              label: "部门",
              key: RuleTool.resource.department
            },
            {
              label: "分支机构",
              key: RuleTool.resource.branch
            }
          ]
        },
        {
          label: "单据",
          key: RuleTool.resource.orderForm
        },
        {
          label: "管理设置",
          key: RuleTool.resource.manage,
          children: [
            {
              label: "角色管理",
              key: RuleTool.resource.role
            },
            {
              label: "员工信息",
              key: RuleTool.resource.employee
            },
            {
              label: "账户信息",
              key: RuleTool.resource.account
            }
          ]
        },
        {
          label: "统计查询",
          key: RuleTool.resource.statistic,
          children: [
            {
              label: "日志",
              key: RuleTool.resource.log
            },
            {
              label: "营收",
              key: RuleTool.resource.revenue
            }
          ]
        }
      ],

      selectedSource: -1,
      selectedSourceName: "",
      selectedSourceRule: {},

      readChecked: false,
      writeChecked: false,
      deleteChecked: false,
      verifyChecked: false,
      verifyVisiable: false
    };
  },

  methods: {
    handleCurrentChange (data) {
      this.selectedInfo = data;
      this.selectedSource = -1;
      this.selectedSourceName = "";
      this.selectedSourceRule = {};
    },

    nameChange () { },

    newRole () {
      let newRole = {
        id: null,
        name: "新角色1",
        permissions: []
      };

      this.tableData.push(newRole);
      this.$refs["roleTable"].setCurrentRow(newRole);
    },

    submitData () {
      console.log("=====  submit ", this.selectedInfo);

      RoleInfoApi.checkRoleDuplicate(this.selectedInfo).then(res => {
        if (res.data == false) {
          this.$message({
            message: "角色名称重复",
            type: "error",
            showClose: true
          });
        } else {
          if (this.selectedInfo && this.selectedInfo.id != null) {
            RoleInfoApi.setRoleInfo(this.selectedInfo).then(() => {
              this.getRoleInfo();
            });
          } else {
            RoleInfoApi.addRoleInfo(this.selectedInfo).then(() => {
              this.getRoleInfo();
            });
          }
        }
      });
    },

    deleteInfo () {
      RoleInfoApi.deleteRoleInfo(this.selectedInfo).then(() => {
        this.getRoleInfo();
      });
    },

    getRoleInfo () {
      RoleInfoApi.getRoleInfo().then(res => {
        console.log("==========getRoleInfo  ", res);
        this.setResponseResult(res.data);
        this.handleCurrentChange(null);
      });
    },

    handleNodeClick (data) {
      this.selectedSource = data.key;
      this.selectedSourceName = data.label;

      let permissions = this.selectedInfo.permissions.filter(function (rule) {
        return rule.resource == data.key;
      });

      if (permissions.length == 1) {
        this.selectedSourceRule = permissions[0];
      } else {
        let rule = this.getNewRule(this.selectedSource);
        this.selectedInfo.permissions.push(rule);
        this.selectedSourceRule = rule;
      }

      if (this.selectedSource == RuleTool.resource.orderForm) {
        this.verifyVisiable = true;
      } else {
        this.verifyVisiable = false;
      }

      this.setRuleCheckBoxValue();

      console.log("==handleNodeClick ", this.selectedInfo);
    },

    getNewRule (sourceValue) {
      let rule = {
        id: null,
        resource: sourceValue,
        operator: 0
      };

      return rule;
    },

    selectedSourceValid () {
      return this.selectedSource != -1;
    },

    handleRuleChange () { },

    setRuleCheckBoxValue () {
      this.readChecked =
        (this.selectedSourceRule.operator & RuleTool.rule.read) != 0;
      this.writeChecked =
        (this.selectedSourceRule.operator & RuleTool.rule.write) != 0;
      this.deleteChecked =
        (this.selectedSourceRule.operator & RuleTool.rule.delete) != 0;
      this.verifyChecked =
        (this.selectedSourceRule.operator & RuleTool.rule.verify) != 0;
    },

    readChange (value) {
      if (value) {
        this.selectedSourceRule.operator =
          this.selectedSourceRule.operator | RuleTool.rule.read;
      } else {
        this.selectedSourceRule.operator =
          this.selectedSourceRule.operator & ~RuleTool.rule.read;
      }

      this.handleRuleChange();

      console.log("======= this.selectedSourceRule ", this.selectedSourceRule);
    },

    writeChange (value) {
      if (value) {
        this.selectedSourceRule.operator =
          this.selectedSourceRule.operator | RuleTool.rule.write;
      } else {
        this.selectedSourceRule.operator =
          this.selectedSourceRule.operator & ~RuleTool.rule.write;
      }

      this.handleRuleChange();

      console.log("======= this.selectedSourceRule ", this.selectedSourceRule);
    },

    deleteChange (value) {
      if (value) {
        this.selectedSourceRule.operator =
          this.selectedSourceRule.operator | RuleTool.rule.delete;
      } else {
        this.selectedSourceRule.operator =
          this.selectedSourceRule.operator & ~RuleTool.rule.delete;
      }

      this.handleRuleChange();

      console.log("======= this.selectedSourceRule ", this.selectedSourceRule);
    },

    verifyChange (value) {
      if (value) {
        this.selectedSourceRule.operator =
          this.selectedSourceRule.operator | RuleTool.rule.verify;
      } else {
        this.selectedSourceRule.operator =
          this.selectedSourceRule.operator & ~RuleTool.rule.verify;
      }

      this.handleRuleChange();

      console.log("======= this.selectedSourceRule ", this.selectedSourceRule);
    }
  },

  created: function () {
    this.getRoleInfo();
    this.initPermission(RuleTool.resource.role);
  }
};
</script>

<style>
.role-name-pane {
  line-height: 40px;
}

.role-cointainer {
  width: 400px;
  min-height: 450px;
  text-align: center;
  border-right: 1px solid gray;
}

.source-container {
  height: 100%;
}

.role-name-input {
  width: 150px;
  display: inline-block;
}

.role-footer {
  line-height: 60px;
}

.rule-pane {
  padding-bottom: 0px;
}

.source-tree {
  padding-left: 0px;
}

.role-name-pane {
  padding-left: 6px;
}
</style>
