<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" :before-close="handleClose">
    <el-tabs v-model="activeName">
      <el-tab-pane label="基本资料" name="first">
        <el-form :model="productData" ref="productData" :rules="rules" class="demo-ruleForm">
          <el-form-item label="编号" :label-width="formLabelWidth">
            <el-input v-model="productData.identifier" autocomplete="off"></el-input>
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
            v-for="item in productData.units"
            :key="item.unit.id"
            :label="item.unit.name"
            :value="item.unit.id"
          ></el-option>
        </el-select>
        <label>默认销售单位</label>
        <el-select v-model="productData.sellDefaultUnit" placeholder="请选择">
          <el-option
            v-for="item in productData.units"
            :key="item.unit.id"
            :label="item.unit.name"
            :value="item.unit.id"
          ></el-option>
        </el-select>

        <el-button @click="addUnit">添加单位</el-button>

        <el-table :data="productData.units" height="400" style="width: 100%" border>
          <el-table-column prop="default" label="默认基本单位">
            <template slot-scope="scope">
              <span>{{scope.row.default}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="unitID" label="名称" width="130">
            <template slot-scope="scope">
              <el-input readonly v-model="scope.row.unit.name" placeholder="名称">
                <el-button
                  size="small"
                  @click="showUnitDialog(scope)"
                  slot="append"
                  icon="el-icon-search"
                ></el-button>
              </el-input>
            </template>
          </el-table-column>
          <el-table-column prop="crate" label="换算率">
            <template slot-scope="scope">
              <el-input
                v-model="scope.row.crate"
                placeholder="换算率"
                @input="unitInfoChange(scope.row)"
              ></el-input>
            </template>
          </el-table-column>

          <el-table-column prop="priceData[0]" :label="priceData[0].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.prices[0].value"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="priceData[1]" :label="priceData[1].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.prices[1].value"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="priceData[2]" :label="priceData[2].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.prices[2].value"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="priceData[3]" :label="priceData[3].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.prices[3].value"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="priceData[4]" :label="priceData[4].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.prices[4].value"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="priceData[5]" :label="priceData[5].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.prices[5].value"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="priceData[6]" :label="priceData[6].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.prices[6].value"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="priceData[7]" :label="priceData[7].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.prices[7].value"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="priceData[8]" :label="priceData[8].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.prices[8].value"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="priceData[9]" :label="priceData[9].label">
            <template slot-scope="scope">
              <el-input v-model="scope.row.prices[9].value"></el-input>
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
import unitApi from '../../../../api/unit-info-api/unitInfoApi.js'
import Tool from '@/views/constant/tool.js'

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
      activeName: 'first',
      formLabelWidth: '120px',
      selectUnitData: {},
      priceTitles: [{ prop: "prices[0]", label: this.priceData[0].label },
      { prop: "prices[1]", label: this.priceData[1].label },
      { prop: "prices[2]", label: this.priceData[2].label },
      { prop: "prices[3]", label: this.priceData[3].label },
      { prop: "prices[4]", label: this.priceData[4].label },
      { prop: "prices[5]", label: this.priceData[5].label },
      { prop: "prices[6]", label: this.priceData[6].label },
      { prop: "prices[7]", label: this.priceData[7].label },
      { prop: "prices[8]", label: this.priceData[8].label },
      { prop: "prices[9]", label: this.priceData[9].label }],
      rules: {
        id: [
          { required: true, message: '请输单位编号', trigger: 'blur' }
        ]
      },

      unitTableData: [],
    }
  },

  methods: {
    getScopeVModel (prices, id) {
      prices.forEach((priceValue) => {
        if (priceValue.price.id == id) {
          return priceValue;
        }
      });
    },

    closeDialog () {
      this.$emit('closeDialog');
    },

    unitInfoChange (row) {
      if (row.actionType == -1) {
        row.actionType = Tool.actionType.update;
      }
    },

    addUnit () {
      var pricesValue = [];

      this.priceData.forEach((value) => {
        pricesValue.push({ price: value, value: 0 });
      });
      console.log("======addUnit ", Tool);
      let emptyUnit = {
        unit: { id: -1, name: '' },
        crate: 1,
        default: false,
        actionType: Tool.actionType.add,
        prices: pricesValue
      };

      this.productData.units.push(emptyUnit);
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
          this.$emit("submitData", this.productData);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    submitUnitData (value) {
      this.selectUnitData.unit.id = value.id;
      this.selectUnitData.unit.name = value.name;
      this.closeUnitDialog();
    },

    getProductUnitData () {
      unitApi.getUnitInfo().then((res) => {
        this.unitTableData = res.data;
        console.log("======getProductUnitData  ", res);
      });
    }
  },

  created: function () {
    this.getProductUnitData();
    console.log("======= priceData ", this.priceData);
  }
}
</script>

<style>
.table-column {
  min-width: 100px;
}
</style>
