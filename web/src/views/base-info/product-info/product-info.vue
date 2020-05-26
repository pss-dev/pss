<template>
  <div>
    <base-info-header :paths="getPathsLabel()"></base-info-header>

    <base-info-table
      :titles="titData"
      :tableData="tableData"
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
import BaseInfoHeader from "../components/base-info-header.vue"
import BaseInfoTabler from "../components/base-info-table.vue"
import BaseInfoFooter from "../components/base-info-footer.vue"
import BseInfo from '../mixIns/base-info'
import ProductInfoDialog from "./components/product-info-dialog.vue"

import productInfoApi from "../../../api/product-info-api/productInfoApi.js"
import priceInfoApi from "../../../api/price-info-api/priceInfoApi.js"

export default {
  name: "productInfo",
  mixins: [BseInfo],
  components: {
    "base-info-header": BaseInfoHeader,
    "base-info-table": BaseInfoTabler,
    "base-info-footer": BaseInfoFooter,
    "product-info-dialog": ProductInfoDialog
  },

  data () {
    return {
      priceData: [
        { id: 0, name: "采购价格1", label: "采购a价格1" },
        { id: 1, name: "采购价格2", label: "采购v价格2" },
        { id: 2, name: "采购价格3", label: "采购b价格3" },
        { id: 3, name: "最高采购价", label: "最高a采购价" },
        { id: 4, name: "销售价格1", label: "销售价d格1" },
        { id: 5, name: "销售价格2", label: "销售s价格2" },
        { id: 6, name: "销售价格3", label: "销售a价格3" },
        { id: 7, name: "零售价", label: "零售价" },
        { id: 8, name: "最低销售价", label: "最低销售价" },
        { id: 9, name: "最高售价", label: "最高售价" },
      ],

      titData: []
    }
  },

  methods: {
    getChildData (value) {
      var param = this.getParameterForNewTable(value.id);

      this.getProductInfo(param).then(() => {
        this.addPaths();
        this.resetCurrentPage();
        this.parent = value;
      });
    },

    edit () {
      this.setDialogInfo("编辑", this.selectedInfo, false);
      this.showDialog = true;
    },

    newInfo () {
      var pricesValue = [];

      this.priceData.forEach((value) => {
        pricesValue.push({ price: value, number: 0 });
      });

      var emptyDialogData = {
        id: null,
        identifier: '',
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
        actionType: 0,
        parent: this.parent,
        units: [{
          default: true,
          crate: 1,
          actionType: 0,
          unit: { id: null, name: '' },
          prices: pricesValue,
        }],
      };
      console.log("============emptyDialogData  ", emptyDialogData);
      this.setDialogInfo("空白新增", emptyDialogData, true);
      this.showDialog = true;
    },

    copyNew () {
      this.setDialogInfo("复制新增", this.selectedInfo, true);
      this.showDialog = true;
    },

    submitData (productData) {
      var params = {};
      var getInfoParams = this.getParameterForNewTable(this.getParentID());

      if (this.addInfo) {
        this.setDefaultID(productData);

        productInfoApi.addProductInfo(params, productData).then(
          () => {
            this.getProductInfo(getInfoParams);
          });
      }
      else {
        productInfoApi.modifyProductInfo(params, productData).then(
          () => {
            this.getProductInfo(getInfoParams);
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
        .then(() => {
          var params = this.getParameterForNewTable(this.getParentID());
          this.getProductInfo(params);
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
          console.log("= ====getPriceInfo ", res);

          if (res.data) {
            this.priceData = res.data;
          }

        }
      );
    },
  },

  created: function () {
    var params = this.getParameterForNewTable(this.getParentID());
    this.getProductInfo(params);
    this.getPriceInfo();

    if (this.tableData && this.tableData.length > 0) {
      this.titData =
        [{ prop: "identifier", label: "编号" },
        { prop: "name", label: "名称" },
        { prop: "specification", label: "规格" },
        { prop: "type", label: "型号" },
        { prop: "units[0].prices[0].price.id", label: this.priceData[0].label },
        { prop: "units[0].prices[1].price.id", label: this.priceData[1].label },
        { prop: "units[0].prices[4].price.id", label: this.priceData[4].label },
        { prop: "units[0].prices[5].price.id", label: this.priceData[5].label },
        { prop: "stopPurchase", label: "停止采购" }];
    }
    else {
      this.titData =
        [{ prop: "identifier", label: "编号" },
        { prop: "name", label: "名称" },
        { prop: "specification", label: "规格" },
        { prop: "type", label: "型号" }];
    }
  }
}
</script>

<style>
</style>
