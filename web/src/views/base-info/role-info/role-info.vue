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
                <el-button @click="newInfo">添加</el-button>
              </el-col>
              <el-col :span="12">
                <el-button>删除</el-button>
              </el-col>
            </el-row>
          </el-footer>
        </el-container>
        <div class="role-pane"></div>
      </el-aside>
      <el-main>
        <label v-if="isSelectedInfoInvalid()">please select a role</label>
        <el-container v-if="!isSelectedInfoInvalid()" class="source-container">
          <el-header>
            <el-row class="role-name-pane">
              <el-col :span="3">角色名称：</el-col>
              <el-col :span="6">
                <el-input v-model="selectedInfo.name"></el-input>
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
                  <el-checkbox>读</el-checkbox>
                  <el-checkbox>写</el-checkbox>
                  <el-checkbox>删</el-checkbox>
                  <el-checkbox>审核</el-checkbox>
                </el-card>
              </el-main>
            </el-container>
          </el-main>
          <el-footer>
            <el-row>
              <el-col :span="4">
                <el-button>保存</el-button>
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

      tableData: [{ id: null, name: "role1" }],

      treeData: [{
        label: '基本信息',
        key: 0,
        value: 0,
        children: [{
          label: '商品档案',
          key: 1,
          value: 0,
        }]
      }],
    }
  },

  methods: {
    handleCurrentChange (data) {
      this.selectedInfo = data;
      console.log("========  handleCurrentChange ", data);
    },

    newInfo () {
      let newRole = {
        id: null,
        name: "新角色1"
      };

      this.tableData.push(newRole);
      this.$refs["roleTable"].setCurrentRow(newRole);

    },

    submitData (roleName) {
      console.log("===== ", roleName);
      this.closeDialog();
    },

    deleteInfo () {
      RoleInfoApi
        .deleteRoleInfo(this.selectedInfo)
        .then((res) => {
          this.setResponseResult(res.data);
        });
    },

    getRoleInfo (params) {
      RoleInfoApi.getRoleInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
          this.closeDialog();
        });
    },

    handleNodeClick (data) {
      console.log("====== handleNodeClick ", data);
    }
  },

  created: function () {
    var params = this.getParameterForNewTable(this.getParentID());

    this.getroleInfo(params);
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

.el-main {
  padding-bottom: 0px;
}
</style>
