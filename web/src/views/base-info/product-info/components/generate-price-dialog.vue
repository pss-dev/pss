<template>
  <el-dialog
    :modal="true"
    :close-on-click-modal="false"
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose"
    width="1000px"
  >
    <el-row>
      <el-col :span="5">
        <el-select
          size="small"
          v-model="generatePriceModel.targetPrice"
          value-key="id"
          placeholder="请选择"
        >
          <el-option v-for="item in priceData" :key="item.id" :label="item.label" :value="item"></el-option>
        </el-select>
      </el-col>
      <el-col :span="1">
        <div class="label-class">=</div>
      </el-col>
      <el-col :span="5">
        <el-select
          size="small"
          v-model="generatePriceModel.originalPrice"
          value-key="id"
          placeholder="请选择"
        >
          <el-option v-for="item in priceData" :key="item.id" :label="item.label" :value="item"></el-option>
        </el-select>
      </el-col>
      <el-col :span="2">
        <el-select
          size="small"
          v-model="generatePriceModel.calculate"
          value-key="id"
          placeholder="请选择"
        >
          <el-option v-for="item in calculations" :key="item" :label="item" :value="item"></el-option>
        </el-select>
      </el-col>
      <el-col :span="3">
        <el-input class="multiple-input" v-model="generatePriceModel.multiple" autocomplete="off"></el-input>
      </el-col>
      <el-col :span="2">
        <div class="label-class">四舍五入</div>
      </el-col>
      <el-col :span="3">
        <el-input v-model="generatePriceModel.decimal" type="number" autocomplete="off"></el-input>
      </el-col>
      <el-col :span="2">
        <div class="label-class">位小数</div>
      </el-col>
    </el-row>

    <dialog-footer @closeDialog="closeDialog" @submitData="submitData"></dialog-footer>
  </el-dialog>
</template>

<script>
import BaseInfoDialogFooter from "@/views/base-info/components/base-info-dialog-footer.vue"

export default {
  name: "priceInfoDialog",
  components: {
    "dialog-footer": BaseInfoDialogFooter
  },
  props: {
    title: {
      type: String
    },

    priceData: {
      type: Array
    }
  },
  data () {
    return {
      dialogVisible: true,
      generatePriceModel: {
        targetPrice: this.priceData[0],
        originalPrice: this.priceData[0],
        calculate: "*",
        multiple: 0,
        decimal: 0
      },
      calculations: ["*", "/"]
    }
  },

  methods: {
    closeDialog () {
      console.log(this.form);
      this.$emit('closeDialog');
    },

    handleClose () {
      this.$emit('closeDialog')
    },

    submitData () {
      this.$emit("submitData", this.generatePriceModel);
    }
  },

  created: function () {
  }
}
</script>

<style scoped lang="scss">
.label-class {
  line-height: 32px;
}

/deep/ .el-input__inner {
  height: 32px;
}
</style>
