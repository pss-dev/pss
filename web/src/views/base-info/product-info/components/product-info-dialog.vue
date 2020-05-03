<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" :before-close="handleClose">
    <el-tabs v-model="activeName">
      <el-tab-pane label="基本资料" name="first">
        <el-form :model="productData" ref="productData" :rules="rules" class="demo-ruleForm">
          <el-form-item label="编号" prop="id" :label-width="formLabelWidth">
            <el-input v-model="productData.id" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="名称" :label-width="formLabelWidth">
            <el-input v-model="productData.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="拼音码" :label-width="formLabelWidth">
            <el-input v-model="productData.initials" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="规格" :label-width="formLabelWidth">
            <el-input v-model="productData.specification" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="型号" :label-width="formLabelWidth">
            <el-input v-model="productData.type" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="产地" :label-width="formLabelWidth">
            <el-input v-model="productData.address" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="单位" name="second">
        <label>默认收购单位</label>
        <el-select v-model="productData.purchaseDefaultUnit" placeholder="请选择">
          <el-option
            v-for="item in productData.unit"
            :key="item.unitID"
            :label="item.unitName"
            :value="item.unitID"
          ></el-option>
        </el-select>
        <label>默认销售单位</label>
        <el-select v-model="productData.sellDefaultUnit" placeholder="请选择">
          <el-option
            v-for="item in productData.unit"
            :key="item.unitID"
            :label="item.unitName"
            :value="item.unitID"
          ></el-option>
        </el-select>

        <el-button @click="addUnit">添加单位</el-button>

        <el-table :data="productData.unit" height="400" style="width: 100%" border>
          <el-table-column prop="default" label="默认基本单位">
            <template slot-scope="scope">
              <span>{{scope.row.default}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="unitID" label="编号" width="130">
            <template slot-scope="scope">
              <el-input v-model="scope.row.unitID" placeholder="编号">
                <el-button
                  size="small"
                  @click="showUnitDialog(scope)"
                  slot="append"
                  icon="el-icon-search"
                ></el-button>
              </el-input>
            </template>
          </el-table-column>
          <el-table-column prop="unitName" label="名称">
            <template slot-scope="scope">
              <span>{{scope.row.unitName}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="crate" label="换算率">
            <template slot-scope="scope">
              <el-input v-model="scope.row.crate" placeholder="换算率"></el-input>
            </template>
          </el-table-column>

          <el-table-column prop="purchasePrice1" :label="priceData[0].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.purchasePrice1"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="purchasePrice2" :label="priceData[1].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.purchasePrice2"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="purchasePrice3" :label="priceData[2].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.purchasePrice3"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="highestPurchasePrice" :label="priceData[3].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.highestPurchasePrice"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="sellPrice1" :label="priceData[4].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.sellPrice1"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="sellPrice2" :label="priceData[5].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.sellPrice2"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="sellPrice3" :label="priceData[6].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.sellPrice3"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="retailPrice" :label="priceData[7].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.retailPrice"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="lowestSellPrice" :label="priceData[8].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.lowestSellPrice"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="highestSellPrice" :label="priceData[9].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.highestSellPrice"></el-input>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
    <el-button @click="closeDialog">取 消</el-button>
    <el-button type="primary" @click="submitData">确 定</el-button>
    <product-unit-search-dialog
      :tableData="unitTableData"
      @closeDialog="closeUnitDialog"
      @submitData="submitUnitData"
      v-if="unitdialogVisible"
    ></product-unit-search-dialog>
  </el-dialog>
</template>

<script>
import productUnitSearchDialog from '../../../components/product-unit-search-dialog'
import productUnitApi from '../../../../api/productUnit-info-api/productUnitInfoApi.js'

export default {
  name: "productInfoDialog",
  components: {
    "product-unit-search-dialog": productUnitSearchDialog,
  },

  props: {
    "title": {
      type: String
    },

    "productData": {
      type: Object
    },

    "priceData": {
      type: Array
    }
  },

  data () {
    return {
      dialogVisible: true,
      unitdialogVisible: false,
      oldID: "",
      activeName: 'first',
      formLabelWidth: '120px',
      selectUnitData: {},
      rules: {
        id: [
          { required: true, message: '请输单位编号', trigger: 'blur' }
        ]
      },

      unitTableData:
        [{ id: "个", name: "个", },
        { id: "盒", name: "盒" },
        { id: "箱", name: "箱" },],
    }
  },

  methods: {
    closeDialog () {
      this.$emit('closeDialog');
    },

    addUnit () {
      let emptyUnit = {
        unitID: '',
        unitName: 'empty',
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
        default: false,
      };

      this.productData.unit.push(emptyUnit);
    },

    closeUnitDialog () {
      this.unitdialogVisible = false;
    },

    showUnitDialog (scopw) {
      this.selectUnitData = scopw.row;
      this.unitdialogVisible = true;
    },

    handleClose () {
      this.$emit('closeDialog')
    },

    submitData () {
      this.$refs['productData'].validate((valid) => {
        if (valid) {
          this.$emit("submitData", this.productData, this.oldID);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    submitUnitData (value) {
      this.selectUnitData.unitID = value.id;
      this.selectUnitData.unitName = value.name;
      this.closeUnitDialog();
    },

    getProductUnitData () {
      productUnitApi.getProductUnitData().then((res) => {
        this.unitTableData = res.data;
      });
    }
  },

  created: function () {
    if (this.productData) {
      this.oldID = this.productData.productID;
    }

    this.getProductUnitData();
  }
}
</script>

<style>
.table-column {
  min-width: 100px;
}
</style>
