<template>
  <div class="base-info-header">
    <el-row>
      <el-col class="path-label-col" :span="16">
        <label class="path-label">{{this.paths}}</label>
      </el-col>
      <el-col v-if="showTypeVisiable" :span="8">
        <div class="show-typ-col">
          <el-switch
            class="show-typ"
            v-model="showType"
            active-text="按层显示"
            inactive-text="显示全部"
            @change="typeChange()"
          ></el-switch>
        </div>
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
.base-info-header {
  margin-top: 5px;
}

.path-label-col {
  text-align: left;
}

.path-label {
  margin-left: 20px;
}

.show-typ-col {
  text-align: right;
}

.show-typ {
  margin-right: 20px;
}
</style>
