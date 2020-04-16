<template>
  <div>
    <base-info-header :paths="getPathsLabel()"></base-info-header>

    <base-info-table
      :titles="titData"
      :tableData="tableData"
      :showEdit="true"
      @handleCurrentChange="handleCurrentChange"
      @getChildData="getChildData"
      @pageChange="pageChange"
    ></base-info-table>

    <base-info-footer
      :totalSize="totalSize"
      :currentPage="currentPage"
      :selectedInfoInvalid="isSelectedInfoInvalid()"
      :previousDisable="!hasFatherInfo()"
      @newInfo="newInfo"
      @copyNew="copyNew"
      @edit="edit"
      @deleteInfo="deleteInfo"
      @previous="previous"
    ></base-info-footer>

    <product-unit-info-dialog
      @closeDialog="closeDialog"
      @submitData="submitData"
      :title="dialogTitle"
      :productUnitData="dialogData"
      v-if="showDialog"
    ></product-unit-info-dialog>
  </div>
</template>

<script>
import baseInfoHeader from "../components/base-info-header.vue"
import baseInfoTabler from "../components/base-info-table.vue"
import baseInfoFooter from "../components/base-info-footer.vue"
import bseInfo from '../mixIns/base-info'
import productUnitInfoDialog from "./components/product-unit-info-dialog.vue"
import productUnitInfoApi from "../../../api/productUnit-info-api/productUnitInfoApi.js"

export default {
  name: "productUnitInfo",
  mixins: [bseInfo],
  components: {
    "base-info-header": baseInfoHeader,
    "base-info-table": baseInfoTabler,
    "base-info-footer": baseInfoFooter,
    "product-unit-info-dialog": productUnitInfoDialog
  },

  data () {
    return {
      titData:
        [{ prop: "id", label: "编号" },
        { prop: "name", label: "名称" },
        { prop: "initials", label: "拼音码" }],
      tableData: [],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getProductUnitInfo(param).then(() => {
        this.addPaths();
        this.resetCurrentPage();
        this.fatherID = value.id;
      });
    },

    edit () {
      this.setDialogInfo("编辑", this.selectedInfo, false);
      this.showDialog = true;
    },

    newInfo () {
      var emptyDialogData = {
        id: '',
        name: '',
        initials: ''
      };

      this.setDialogInfo("空白新增", emptyDialogData, true);
      this.showDialog = true;
    },

    copyNew () {
      this.setDialogInfo("复制新增", this.selectedInfo, true);
      this.showDialog = true;
    },

    submitData (productUnitData, oldID) {
      var params = this.getParameterForNewTable(this.fatherID);

      if (this.addInfo) {
        productUnitInfoApi.addProductUnitInfo(params, productUnitData).then(
          (res) => {
            this.setResponseResult(res.data);
          });
      }
      else {
        params.oldID = oldID;
        productUnitInfoApi.modifyProductUnitInfo(params, productUnitData).then(
          (res) => {
            this.setResponseResult(res.data);
          });
      }
    },

    deleteInfo () {
      var deleteParams = {
        id: this.selectedInfo.id,
        fatherID: this.fatherID
      }

      productUnitInfoApi
        .deleteProductUnitInfo(deleteParams)
        .then((res) => {
          this.setResponseResult(res.data);
        });
    },

    previous () {
      if (this.paths.length < 1) {
        return;
      }

      var previousInfo = this.paths[this.paths.length - 1];
      var previousParams = this.getParameterForNewTable(previousInfo.id);

      this.getProductUnitInfo(previousParams).then(() => {
        this.paths.pop();
        this.currentPage = previousInfo.page;
        this.fatherID = previousInfo.id;
      });
    },

    getProductUnitInfo (params) {
      return productUnitInfoApi.getProductUnitInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },

    setResponseResult (data) {
      if (data.totalSize && data.result) {
        this.totalSize = data.totalSize;
        this.tableData = data.result;

        this.showDialog = false;
      }
    },
  },

  created: function () {
    var params = this.getParameterForNewTable(this.fatherID);

    this.getProductUnitInfo(params);
  }
}
</script>

<style>
</style>
