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

import Tool from '@/views/constant/tool.js'

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
      priceData: [],

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
      if (this.priceData.length != 10) {
        alert("===  price value 数量少于10 请添加 至十个");
        return;
      }

      var pricesValue = [];

      this.priceData.forEach((value) => {
        pricesValue.push({ price: value, value: 0 });
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
        actionType: Tool.actionType.add,
        parent: this.parent,
        units: [{
          default: true,
          crate: 1,
          actionType: Tool.actionType.add,
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
      var getInfoParams = this.getParameterForNewTable(this.getParentID());
      console.log("========= submitData ", productData);
      if (this.addInfo) {
        productInfoApi.addProductInfo(productData).then(
          () => {
            this.getProductInfo(getInfoParams);
          });
      }
      else {
        productInfoApi.modifyProductInfo(productData).then(
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
        .deleteProductInfo(deleteParams, this.selectedInfo)
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
      var previousParams = this.getParameterForNewTable(this.getParentID0(previousInfo.parent));

      this.getProductInfo(previousParams).then(() => {
        this.paths.pop();
        this.currentPage = previousInfo.page;
        this.fatherID = previousInfo.id;
      });
    },

    getProductInfo (params) {
      return productInfoApi.getProductInfo(params).then(
        (res) => {
          console.log("=======getProductInfo  ", res);
          this.setResponseResult(res.data);
        });
    },

    getPriceInfo () {
      return priceInfoApi.getPriceInfo().then(
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
