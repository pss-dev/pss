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
      :newInfoVisiable="true"
      :copyNewVisiable="false"
      :deleteInfoVisiable="false"
      :previousVisiable="false"
      :nextVisiable="false"
      @newInfo="newInfo"
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
      this.setDialogInfo("编辑价格名称", this.selectedInfo, false);
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
        });
    },
  },

  created: function () {
    this.getPriceInfo();
  }
}
</script>

<style>
</style>
