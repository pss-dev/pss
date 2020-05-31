<template>
  <div>
    <base-info-header :paths="getPathsLabel()" :showTypeVisiable="false"></base-info-header>

    <base-info-table
      :titles="titData"
      :tableData="tableData"
      @handleCurrentChange="handleCurrentChange"
      @pageChange="pageChange"
    ></base-info-table>

    <base-info-footer
      :totalSize="totalSize"
      :currentPage="currentPage"
      :selectedInfoInvalid="isSelectedInfoInvalid()"
      :previousVisiable="false"
      :nextVisiable="false"
      @newInfo="newInfo"
      @copyNew="copyNew"
      @deleteInfo="deleteInfo"
    ></base-info-footer>

    <ruler-info-dialog
      @closeDialog="closeDialog"
      @submitData="submitData"
      :title="dialogTitle"
      :RulerData="dialogData"
      v-if="showDialog"
    ></ruler-info-dialog>
  </div>
</template>

<script>
import BaseInfoHeader from "../components/base-info-header.vue"
import BaseInfoTabler from "../components/base-info-table.vue"
import BaseInfoFooter from "../components/base-info-footer.vue"
import BseInfo from '../mixIns/base-info'
import RulerInfoDialog from './components/ruler-info-dialog'

import RulerInfoApi from '../../../api/ruler-info-api/rulerInfoApi'

export default {
  name: "RulerInfo",

  mixins: [BseInfo],

  components: {
    "base-info-header": BaseInfoHeader,
    "base-info-table": BaseInfoTabler,
    "base-info-footer": BaseInfoFooter,
    "ruler-info-dialog": RulerInfoDialog
  },

  props: {
  },

  data () {
    return {
      titData:
        [{ prop: "name", label: "名称" }],
    }
  },

  methods: {
    edit () {
      this.setDialogInfo("编辑", this.selectedInfo, false);
      this.showDialog = true;
    },

    newInfo () {
      var emptyDialogData = {
        id: null,
        name: '',
        account: '',
        password: '',
        branch: { id: null, name: '' },
        department: { id: null, name: '' },
      };

      this.setDialogInfo("空白新增", emptyDialogData, true);
      this.showDialog = true;
    },

    copyNew () {
      this.setDialogInfo("复制新增", this.selectedInfo, true);
      this.showDialog = true;
    },

    submitData (rulerData) {
      if (this.addInfo) {
        RulerInfoApi.addRulerInfo(rulerData).then(
          (res) => {
            this.setResponseResult(res.data);
          });
      }
      else {
        RulerInfoApi.modifyRulerInfo(rulerData).then(
          (res) => {
            this.setResponseResult(res.data);
          });
      }
    },

    deleteInfo () {
      RulerInfoApi
        .deleteRulerInfo(this.selectedInfo)
        .then((res) => {
          this.setResponseResult(res.data);
        });
    },

    getRulerInfo (params) {
      RulerInfoApi.getRulerInfo(params).then(
        (res) => {
          this.setResponseResult(res.data);
        });
    },
  },

  created: function () {
    var params = this.getParameterForNewTable(this.getParentID());

    this.getRulerInfo(params);
  }
}
</script>

<style>
</style>
