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
          :isSelectedLeaf="isSelectedLeaf()"
          :previousDisable="!hasFatherInfo()"
          :deleteInfoVisiable="deletePermission"
          :copyNewVisiable="writePermission"
          :newInfoVisiable="writePermission"
          :editVisiable="writePermission"
          :isProduct="true"
          :stopPurchaseDisable="getStopPurchaseStatus()"
          :uploadVisiable="true"
          :parentIsNull="parent == null"
          :uploadAction="'/api/1.0/product/upload'"
          :uploadData="getParent()"
          @newInfo="newInfo"
          @copyNew="copyNew"
          @edit="edit"
          @deleteInfo="deleteInfo"
          @previous="previous"
          @next="getChildData"
          @stopPurchase="stopPurchase"
          @getDataTemplate="getDataTemplate"
          @generatePrice="generatePrice"
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
    <generate-price-dialog
      :title="'更新商品价格'"
      @closeDialog="closeGeneratePrice"
      @submitData="submitGeneratePriceData"
      :priceData="priceData"
      v-if="generatePriceDialogVisiabel"
    ></generate-price-dialog>
  </div>
</template>

<script>
import BaseInfoHeader from "../components/base-info-header.vue"
import BaseInfoTabler from "../components/base-info-table.vue"
import BaseInfoFooter from "../components/base-info-footer.vue"
import ProductInfoDialog from "./components/product-info-dialog.vue"
import GeneratePriceDialog from "./components/generate-price-dialog.vue"

import BseInfo from '../mixIns/base-info.js'
import TableBaseInfo from '@/views/mixIns/table-base-info.js'
import PermissionBase from '@/views/mixIns/permission-base.js'

import productInfoApi from "../../../api/product-info-api/productInfoApi.js"
import priceInfoApi from "../../../api/price-info-api/priceInfoApi.js"

import Tool from '@/views/constant/tool.js'
import RuleTool from '@/views/constant/rule-tool.js'

export default {
  name: "productInfo",
  mixins: [BseInfo, TableBaseInfo, PermissionBase],
  components: {
    "base-info-header": BaseInfoHeader,
    "base-info-table": BaseInfoTabler,
    "base-info-footer": BaseInfoFooter,
    "product-info-dialog": ProductInfoDialog,
    "generate-price-dialog": GeneratePriceDialog
  },

  data () {
    return {
      priceData: [],
      // priceData: [
      //   { id: 0, name: "采购价格1", label: "采购价格1" },
      //   { id: 1, name: "采购价格2", label: "采购价格2" },
      //   { id: 2, name: "采购价格3", label: "采购价格3" },
      //   { id: 3, name: "最高采购价", label: "最高采购价" },
      //   { id: 4, name: "销售价格1", label: "销售价格1" },
      //   { id: 5, name: "销售价格2", label: "销售价格2" },
      //   { id: 6, name: "销售价格3", label: "销售价格3" },
      //   { id: 7, name: "零售价", label: "零售价" },
      //   { id: 8, name: "最低销售价", label: "最低销售价" },
      //   { id: 9, name: "最高售价", label: "最高售价" },
      // ],

      titData: [{ prop: "identifier", label: "编号" },
      { prop: "name", label: "名称" },
      { prop: "specification", label: "规格" },
      { prop: "type", label: "型号" },
      ],
      booleanData: { prop: "stopPurchase", label: "停止采购" },

      generatePriceDialogVisiabel: false,
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
        sellDefaultUnit: null,
        purchaseDefaultUnit: null,
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
      this.setDialogInfo("复制新增", this.cloneSelectedInfoData(true), true);
      this.showDialog = true;
    },

    submitData (productData) {
      console.log("========= submitData ", productData);

      productInfoApi.checkProductDuplicate(productData).then((res) => {
        if (res.data == true) {
          this.$message({
            message: "商品编号重复",
            type: "error",
            showClose: true
          });
        }
        else {
          var getInfoParams = this.getParameterForNewTable(this.getParentID());
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
        }
      });
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
    },

    getParent () {
      let pvalue = null;

      if (this.getParentID() != -1 && this.getParentID() != null) {
        pvalue = this.getParentID();
      }

      return { parentId: pvalue };
    },

    getDataTemplate () {
      window.open(productInfoApi.templateUrl);
    },

    generatePrice () {
      this.generatePriceDialogVisiabel = true;
    },

    closeGeneratePrice () {
      this.generatePriceDialogVisiabel = false;
    },

    submitGeneratePriceData (model) {
      console.log("=========submitGeneratePriceData  ", model);
      model.parentProduct = this.parent;
      productInfoApi.generatePrice(model).then(() => {
        var getInfoParams = this.getParameterForNewTable(this.getParentID());
        this.getProductInfo(getInfoParams);
        this.closeGeneratePrice();
      });
    }
  },

  created: function () {
    this.loadData();
    this.getPriceInfo();
    this.initPermission(RuleTool.resource.product);
  }

}
</script>

<style>
</style>
