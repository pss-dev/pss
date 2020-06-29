<template>
  <div>
    <el-container>
      <el-header height="30">
        <base-info-header :paths="getPathsLabel()" @showTypeChange="showTypeChange"></base-info-header>
      </el-header>
      <el-main>
        <base-info-table
          :titles="titData"
          :booleanData="booleanData"
          :tableData="tableData"
          @handleCurrentChange="handleCurrentChange"
          @getChildData="getChildData"
        ></base-info-table>
      </el-main>
      <el-footer>
        <base-info-footer
          :totalSize="totalSize"
          :currentPage="currentPage"
          :selectedInfoInvalid="isSelectedInfoInvalid()"
          :previousDisable="!hasFatherInfo()"
          :isProduct="true"
          :stopPurchaseDisable="getStopPurchaseStatus()"
          @newInfo="newInfo"
          @copyNew="copyNew"
          @edit="edit"
          @deleteInfo="deleteInfo"
          @previous="previous"
          @next="getChildData"
          @stopPurchase="stopPurchase"
        ></base-info-footer>
      </el-footer>
    </el-container>
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
import ProductInfoDialog from "./components/product-info-dialog.vue"

import BseInfo from '../mixIns/base-info.js'
import TableBaseInfo from '@/views/mixIns/table-base-info.js'

import productInfoApi from "../../../api/product-info-api/productInfoApi.js"
import priceInfoApi from "../../../api/price-info-api/priceInfoApi.js"

import Tool from '@/views/constant/tool.js'

export default {
  name: "productInfo",
  mixins: [BseInfo, TableBaseInfo],
  components: {
    "base-info-header": BaseInfoHeader,
    "base-info-table": BaseInfoTabler,
    "base-info-footer": BaseInfoFooter,
    "product-info-dialog": ProductInfoDialog
  },

  data () {
    return {
      priceData: [
        { id: 0, name: "采购价格1", label: "采购价格1" },
        { id: 1, name: "采购价格2", label: "采购价格2" },
        { id: 2, name: "采购价格3", label: "采购价格3" },
        { id: 3, name: "最高采购价", label: "最高采购价" },
        { id: 4, name: "销售价格1", label: "销售价格1" },
        { id: 5, name: "销售价格2", label: "销售价格2" },
        { id: 6, name: "销售价格3", label: "销售价格3" },
        { id: 7, name: "零售价", label: "零售价" },
        { id: 8, name: "最低销售价", label: "最低销售价" },
        { id: 9, name: "最高售价", label: "最高售价" },
      ],

      titData: [{ prop: "identifier", label: "编号" },
      { prop: "name", label: "名称" },
      { prop: "specification", label: "规格" },
      { prop: "type", label: "型号" },
      ],

      booleanData: { prop: "stopPurchase", label: "停止采购" }
    }
  },

  methods: {
    isSelectedLeaf () {
      return this.selectedInfo != null && this.selectedInfo.haveChildren == false;
    },

    getChildData (value) {
      value = value == null ? this.selectedInfo : value;
      var param = this.getParameterForNewTable(value.id);

      this.getProductInfo(param).then(() => {
        this.addPaths();
        this.resetCurrentPage();
        this.parent = value;
      });
    },

    edit () {
      this.setDialogInfo("编辑", this.cloneSelectedInfoData(), false);
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
        stopPurchase: false,
        sellDefaultUnit: {},
        purchaseDefaultUnit: {},
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
      this.setDialogInfo("复制新增", this.cloneSelectedInfoData(), true);
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
      productInfoApi
        .deleteProductInfo(this.selectedInfo)
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
        this.setPerviousInfo();
      });
    },

    stopPurchase () {
      this.selectedInfo.actionType = Tool.actionType.update;
      this.selectedInfo.stopPurchase = true;
      console.log("======stopPurchase  ", this.selectedInfo);
      var getInfoParams = this.getParameterForNewTable(this.getParentID());

      productInfoApi.modifyProductInfo(this.selectedInfo).then(
        () => {
          this.getProductInfo(getInfoParams);
        });
    },

    getStopPurchaseStatus () {
      return this.selectedInfo && this.selectedInfo.stopPurchase == true;
    },

    getProductInfo (params) {
      return productInfoApi.getProductInfo(params).then(
        (res) => {
          console.log("=======getProductInfo  ", res);
          this.setResponseResult(res.data);
          this.closeDialog();
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

    loadData () {
      var params = this.getParameterForNewTable(this.getParentID());
      this.getProductInfo(params);
    }
  },

  created: function () {
    this.loadData();
    this.getPriceInfo();
  }

}
</script>

<style>
</style>
