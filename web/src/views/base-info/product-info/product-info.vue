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

    <product-info-dialog
      @closeDialog="closeDialog"
      @submitData="submitData"
      :title="dialogTitle"
      :productData="dialogData"
      :priceData="priceData"
      v-if="showDialog"
    ></product-info-dialog>
  </div>
</template>

<script>
import baseInfoHeader from "../components/base-info-header.vue"
import baseInfoTabler from "../components/base-info-table.vue"
import baseInfoFooter from "../components/base-info-footer.vue"
import bseInfo from '../mixIns/base-info'
import productInfoDialog from "./components/product-info-dialog.vue"

import productInfoApi from "../../../api/product-info-api/productInfoApi.js"
import priceInfoApi from "../../../api/price-info-api/priceInfoApi.js"

export default {
  name: "productInfo",
  mixins: [bseInfo],
  components: {
    "base-info-header": baseInfoHeader,
    "base-info-table": baseInfoTabler,
    "base-info-footer": baseInfoFooter,
    "product-info-dialog": productInfoDialog
  },

  data () {
    return {
      priceData: [
        { id: "purchasePrice1", name: "采购价格1", label: "采购价格1" },
        { id: "purchasePrice2", name: "采购价格2", label: "采购价格2" },
        { id: "purchasePrice3", name: "采购价格3", label: "采购价格3" },
        { id: "highestPurchasePrice", name: "最高采购价", label: "最高采购价" },
        { id: "sellPrice1", name: "销售价格1", label: "销售价格1" },
        { id: "sellPrice2", name: "销售价格2", label: "销售价格2" },
        { id: "sellPrice3", name: "销售价格3", label: "销售价格3" },
        { id: "retailPrice", name: "零售价", label: "零售价" },
        { id: "lowestSellPrice", name: "最低销售价", label: "最低销售价" },
        { id: "highestSellPrice", name: "最高售价", label: "最高售价" },
      ],

      titData:
        [{ prop: "id", label: "编号" },
        { prop: "name", label: "名称" },
        { prop: "specification", label: "规格" },
        { prop: "type", label: "型号" },
        { prop: "unit[0].purchasePrice1", label: this.getPriceTitle("purchasePrice1") },
        { prop: "unit[0].purchasePrice2", label: this.getPriceTitle("purchasePrice2") },
        { prop: "unit[0].sellPrice1", label: this.getPriceTitle("sellPrice1") },
        { prop: "unit[0].sellPrice2", label: this.getPriceTitle("sellPrice2") },
        { prop: "stopPurchase", label: "停止采购" },],
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getProductInfo(param).then(() => {
        this.addPaths();
        this.resetCurrentPage();
        this.fatherID = value.id;
      });
    },

    getPriceTitle (id) {
      var priceDataValid = this.priceData && this.priceData.length == 10;
      var label = "";

      switch (id) {
        case "purchasePrice1":
          label = priceDataValid ? this.priceData[0].label : "采购价格1";
          break;
        case "purchasePrice2":
          label = priceDataValid ? this.priceData[1].label : "采购价格2";
          break;
        case "sellPrice1":
          label = priceDataValid ? this.priceData[4].label : "销售价格1";
          break;
        case "sellPrice2":
          label = priceDataValid ? this.priceData[5].label : "销售价格2";
          break;
      }

      return label;
    },

    edit () {
      this.setDialogInfo("编辑", this.selectedInfo, false);
      this.showDialog = true;
    },

    newInfo () {
      var emptyDialogData = {
        id: '',
        name: '',
        initials: '',
        specification: '',
        type: '',
        address: '',
        used: '',
        stopPurchase: '',
        stop: '',
        sellDefaultUnit: '',
        purchaseDefaultUnit: '',
        unit: [{
          unitID: 'psc',
          unitName: '1',
          crate: 1,
          purchasePrice1: '',
          purchasePrice2: '',
          purchasePrice3: '',
          highestPurchasePrice: '',
          sellPrice1: '',
          sellPrice2: '',
          sellPrice3: '',
          retailPrice: '',
          lowestSellPrice: '',
          highestSellPrice: '',
          default: true,
        }],
      };

      this.setDialogInfo("空白新增", emptyDialogData, true);
      this.showDialog = true;
    },

    copyNew () {
      this.setDialogInfo("复制新增", this.selectedInfo, true);
      this.showDialog = true;
    },

    submitData (productData, oldID) {
      var params = this.getParameterForNewTable(this.fatherID);

      if (this.addInfo) {
        productInfoApi.addProductInfo(params, productData).then(
          (res) => {
            this.setResponseResult(res.data);
          });
      }
      else {
        params.oldID = oldID;
        productInfoApi.modifyProductInfo(params, productData).then(
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

      productInfoApi
        .deleteProductInfo(deleteParams)
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

      this.getProductInfo(previousParams).then(() => {
        this.paths.pop();
        this.currentPage = previousInfo.page;
        this.fatherID = previousInfo.id;
      });
    },

    getProductInfo (params) {
      productInfoApi.getProductInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },

    getPriceInfo () {
      priceInfoApi.getPriceInfo().then(
        (res) => {
          this.priceData = res.data;
        }
      );
    },
  },

  created: function () {
    var params = this.getParameterForNewTable(this.fatherID);
    this.getProductInfo(params);
    this.getPriceInfo();
  }
}
</script>

<style>
</style>
