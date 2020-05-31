<template>
  <div>
    <el-row>
      <el-col :span="18">
        <label>{{this.paths}}</label>
      </el-col>
      <el-col v-if="showTypeVisiable" :span="6">
        <el-switch
          v-model="showType"
          active-text="按层显示"
          inactive-text="显示全部"
          @change="typeChange()"
        ></el-switch>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "baseInfoHeader",

  props: {
    "paths": {
      type: String
    },
    "showTypeVisiable": {
      type: Boolean,
      default: true
    },
  },

  data () {
    return {
      showType: true,
    }
  },

  methods: {
    typeChange () {
      this.$emit('showTypeChange', this.showType);
    }
  }
}
</script>

<style>
</style>
