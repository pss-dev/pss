<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    :before-close="handleClose">

    <search-base
    :titles="titleData"
    :tableData="tableData"
    :previousDisable="!hasFatherInfo()"
    :totalSize="totalSize"
    :currentPage="currentPage"
    @handleCurrentChange="handleCurrentChange"
    @getChildData="getChildData"
    @pageChange="pageChange"
    @pageSizeChange="pageSizeChange"
    @previous="previous">
    </search-base>
  </el-dialog>
</template>

<script>
  import departmentInfoApi from "../../api/department-info-api/departmentInfoApi.js"
  import searchBase from "./search-base.vue"

  export default {
    name: "departmentInfo",

    components: {
      "search-base": searchBase
    },

    data() {
      return {
        dialogTitle: "部门查询",
        dialogVisible: true,
        
        fatherID: "",
        paths: [], // for previous

        selectedInfo: null,

        currentPage: 1,
        pageSize: 30,
        totalSize: 1024,

        titleData: [{prop:"id", label:"编号"},
                  {prop:"name", label:"名称"}],
                  
        tableData: [{id:"aaa", name: "bbb"},
                    {id:"aaa0", name: "bbb1"},
                    {id:"aaa2", name: "bbb3"},
                    {id:"aaa4", name: "bbb5"}],
        }
    },
    
    methods: {
      handleClose() {
        this.$emit('closeDialog')
      },
      
      pageChange(value) {
        var pageParams = {
          departmentID: this.fatherID,
          currentPage: value,
          pageSize: this.pageSize
        };
        
        this.getDepartmentInfo(pageParams).then(() => {
          this.currentPage = value;
        });
      },

      pageSizeChange(value) {
        this.pageSize = value;
        
        this.pageChange(this.currentPage);
      },

      handleCurrentChange(value) {
        this.selectedInfo = value;
      },

      getChildData(value) {
        var param = {
          departmentID: value.departmentID,
          currentPage: 1,
          pageSize: this.pageSize
        };

        this.getDepartmentInfo(param).then(() => {
          this.paths.push({
            departmentID: this.fatherID,
            currentPage: this.currentPage
          });
          this.currentPage = 1;
          this.fatherID = value.departmentID;
        });
      },

      previous() {
        if(this.paths.length < 1) {
          return;
        }

        var previousInfo = this.paths.pop();
        var previousParams = {
          departmentID: previousInfo.departmentID,
          currentPage: previousInfo.page,
          pageSize: this.pageSize
        };
        
        this.getDepartmentInfo(previousParams).then(() => {
          this.currentPage = previousInfo.page;
          this.fatherID = previousInfo.departmentID;
        });
      },


      getDepartmentInfo(params) {
        return departmentInfoApi.getDepartmentInfo(params).then(
          (res) => {
            this.setResponseResult(res.data);
        });
      },

      setResponseResult(data) {
        if(data.totalSize && data.result) {
          this.totalSize = data.totalSize;
          this.tableData = data.result;
        }
      },

      isSelectedInfoInvalid() {
        return this.selectedInfo == null;
      },
      
      hasFatherInfo() {
        return this.paths.length > 0;
      }
    },

    created: function() {
      // var params = {
      //   departmentID: this.fatherID,
      //   currentPage: this.currentPage,
      //   pageSize: this.pageSize
      // };

      //this.getDepartmentInfo(params);
    }
  }
</script>

<style>
</style>
