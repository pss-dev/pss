<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
  >
    <el-tabs v-model="activeName">
      <el-tab-pane label="基本资料" name="first">
        <el-form
          :model="productData"
          ref="productData"
          :rules="rules"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="编号" prop="identifier">
            <el-input v-model="productData.identifier" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="名称" prop="name">
            <el-input v-model="productData.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="拼音码" prop="initials">
            <el-input v-model="productData.initials" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="规格" prop="specification">
            <el-input v-model="productData.specification" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="型号" prop="type">
            <el-input v-model="productData.type" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="产地" prop="address">
            <el-input v-model="productData.address" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="单位" name="second">
        <el-card class="unit-card" shadow="never">
          <el-row>
            <el-col :span="6">
              <div class="default-unit-label">默认收购单位：</div>
            </el-col>
            <el-col :span="6">
              <el-select size="small" v-model="productData.purchaseDefaultUnit" placeholder="请选择">
                <el-option
                  v-for="item in productData.units"
                  :key="item.unit.id"
                  :label="item.unit.name"
                  :value="item.unit"
                ></el-option>
              </el-select>
            </el-col>
            <el-col :span="6">
              <div class="default-unit-label">默认销售单位：</div>
            </el-col>
            <el-col :span="6">
              <el-select size="small" v-model="productData.sellDefaultUnit" placeholder="请选择">
                <el-option
                  v-for="item in productData.units"
                  :key="item.unit.id"
                  :label="item.unit.name"
                  :value="item.unit"
                ></el-option>
              </el-select>
            </el-col>
          </el-row>
        </el-card>

        <el-row>
          <el-col :span="24" class="add-unit-button">
            <el-button size="small" @click="addUnit">添加单位</el-button>
          </el-col>
        </el-row>

        <el-table :data="productData.units" height="400" style="width: 100%" border>
          <el-table-column prop="default" label="默认基本单位">
            <template slot-scope="scope">
              <span>{{scope.row.default}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="unitID" label="单位" width="130">
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
                type="number"
                v-model="scope.row.crate"
                placeholder="换算率"
                @input="unitInfoChange(scope.row)"
              ></el-input>
            </template>
          </el-table-column>

          <el-table-column
            v-for="(title, index) in priceTitles"
            v-bind="title"
            :key="title.prop"
            min-width="100px"
          >
            <template slot-scope="scope">
              <el-input v-model="scope.row.prices[index].value"></el-input>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="50">
            <template slot-scope="scope">
              <el-button
                :disabled="getDisable(scope.row)"
                @click.native.prevent="deleteRow(scope.row, scope.$index)"
                type="text"
                size="small"
              >移除</el-button>
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
import UnitSearchDialog from '../../../components/unit-search-dialog'
import unitApi from '../../../../api/unit-info-api/unitInfoApi.js'
import Tool from '@/views/constant/tool.js'

export default {
  name: "productInfoDialog",
  components: {
    "product-unit-search-dialog": UnitSearchDialog,
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
        identifier: [
          { required: true, message: '请输商品编号', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输商品名称', trigger: 'blur' }
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
      console.log("======= unitInfoChange ", row);

      if (row.actionType == 0) {
        row.actionType = Tool.actionType.update;
      }
    },

    addUnit () {
      var pricesValue = [];

      this.priceData.forEach((value) => {
        pricesValue.push({ price: value, value: 0 });
      });
      console.log("======addUnit ", Tool);
      let emptyUnitPrice = {
        unit: { id: null, name: '' },
        crate: 1,
        default: false,
        actionType: Tool.actionType.add,
        prices: pricesValue
      };

      this.productData.units.push(emptyUnitPrice);
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
      if (!this.unitsValid()) {
        this.$message({
          message: `有无效的单位，请删除或者修改之后再确定！`,
          showClose: true
        });

        return;
      }

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
      this.selectUnitData.unit = value;
      this.closeUnitDialog();
    },

    getProductUnitData () {
      unitApi.getUnitInfo().then((res) => {
        this.unitTableData = res.data;
        console.log("======getProductUnitData  ", res);
      });
    },

    unitsValid () {
      let valid = true;

      this.productData.units.forEach((unitPrice) => {
        if (unitPrice.unit.id == null) {
          valid = false;
        }
      });

      return valid;
    },

    getDisable (row) {
      return this.productData.units.length <= 1 || row.actionType != Tool.actionType.add || row.default == true;
    },

    deleteRow (row, index) {
      if (row.actionType == Tool.actionType.add) {
        this.productData.units.splice(index, 1);
      }
    },
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

.default-unit-label {
  height: 32px;
  line-height: 32px;
}

.add-unit-button {
  text-align: right;
  margin-bottom: 5px;
}

.unit-card {
  margin-bottom: 10px;
}
</style>
