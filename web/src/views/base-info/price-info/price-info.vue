<template>
  <div>
    <el-container>
      <el-header height="30"></el-header>
      <el-main>
        <base-info-table
          :titles="titData"
          :tableData="tableData"
          @handleCurrentChange="handleCurrentChange"
          @pageChange="pageChange"
        ></base-info-table>
      </el-main>
      <el-footer>
        <base-info-footer
          :totalSize="totalSize"
          :currentPage="currentPage"
          :selectedInfoInvalid="isSelectedInfoInvalid()"
          :newInfoVisiable="false"
          :copyNewVisiable="false"
          :deleteInfoVisiable="false"
          :previousVisiable="false"
          :nextVisiable="false"
          :editVisiable="writePermission"
          @newInfo="newInfo"
          @edit="edit"
        ></base-info-footer>
      </el-footer>
    </el-container>

    <price-info-dialog
      @closeDialog="closeDialog"
      @submitData="submitData"
      :title="dialogTitle"
      :priceData="dialogData"
      v-if="showDialog"
    ></price-info-dialog>
  </div>
</template>

<script>
import BaseInfoTabler from "../components/base-info-table.vue"
import BaseInfoFooter from "../components/base-info-footer.vue"
import PriceInfoDialog from "./components/price-info-dialog.vue"

import BseInfo from '../mixIns/base-info'
import TableBaseInfo from '@/views/mixIns/table-base-info.js'
import PermissionBase from '@/views/mixIns/permission-base.js'
import RuleTool from '@/views/constant/rule-tool.js'

import priceInfoApi from "../../../api/price-info-api/priceInfoApi.js"

export default {
  name: "priceInfo",
  mixins: [BseInfo, TableBaseInfo, PermissionBase],
  components: {
    "base-info-table": BaseInfoTabler,
    "base-info-footer": BaseInfoFooter,
    "price-info-dialog": PriceInfoDialog
  },

  data () {
    return {
      titData:
        [{ prop: "name", label: "名称" },
        { prop: "label", label: "显示名称" }],
      tableData: [],
    }
  },

  methods: {
    newInfo () {
      var priceData = {
        id: null,
        name: '',
        label: ''
      };

      this.setDialogInfo("空白新增", priceData, true);
      this.showDialog = true;
    },

    edit () {
      this.setDialogInfo("编辑价格名称", this.cloneSelectedInfoData(), false);
      this.showDialog = true;
    },

    submitData (priceData) {
      if (this.addInfo) {
        priceInfoApi.addPriceInfo(priceData).then(
          () => {
            this.getPriceInfo();
          });
      }
      else {
        priceInfoApi.modifyPriceInfo(priceData).then(
          () => {
            this.getPriceInfo();
          });
      }
    },

    getPriceInfo () {
      priceInfoApi.getPriceInfo().then(
        (res) => {
          this.setResponseResult(res.data);
          this.closeDialog();
        });
    },
  },

  created: function () {
    this.getPriceInfo();
    this.initPermission(RuleTool.resource.price);
  }
}
</script>

<style>
</style>
