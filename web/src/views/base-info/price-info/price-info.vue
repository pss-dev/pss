<template>
  <div>
    <base-info-header :paths="getPathsLabel()"></base-info-header>

    <base-info-table
      :titles="titData"
      :tableData="tableData"
      @handleCurrentChange="handleCurrentChange"
      @pageChange="pageChange"
    ></base-info-table>

    <base-info-footer
      :totalSize="totalSize"
      :currentPage="currentPage"
      :selectedInfoInvalid="isSelectedInfoInvalid()"
      :newInfoVisiable="false"
      :copyNewVisiable="false"
      :deleteInfoVisiable="false"
      :previousVisiable="false"
      :nextVisiable="false"
      @edit="edit"
    ></base-info-footer>

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
import BaseInfoHeader from "../components/base-info-header.vue"
import BaseInfoTabler from "../components/base-info-table.vue"
import BaseInfoFooter from "../components/base-info-footer.vue"
import BseInfo from '../mixIns/base-info'
import PriceInfoDialog from "./components/price-info-dialog.vue"

import priceInfoApi from "../../../api/price-info-api/priceInfoApi.js"

export default {
  name: "priceInfo",
  mixins: [BseInfo],
  components: {
    "base-info-header": BaseInfoHeader,
    "base-info-table": BaseInfoTabler,
    "base-info-footer": BaseInfoFooter,
    "price-info-dialog": PriceInfoDialog
  },

  data () {
    return {
      titData:
        [{ prop: "name", label: "名称" },
        { prop: "label", label: "显示名称" }],
      tableData: [{ id: "high", name: "最高售价", label: "最高售价" }],
    }
  },

  methods: {
    edit () {
      this.setDialogInfo("编辑价格名称", this.selectedInfo, false);
      this.showDialog = true;
    },

    submitData (priceData) {
      var params = this.getParameterForNewTable(this.fatherID);

      priceInfoApi.modifyPriceInfo(params, priceData).then(
        (res) => {
          this.setResponseResult(res.data);
        });

    },

    getPriceInfo (params) {
      priceInfoApi.getPriceInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },
  },

  created: function () {
    var params = this.getParameterForNewTable(this.fatherID);

    this.getPriceInfo(params);
  }
}
</script>

<style>
</style>
