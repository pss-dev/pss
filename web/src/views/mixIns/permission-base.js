
import RuleTool from '@/views/constant/rule-tool.js'

export default {
  data () {
    return {
      readPermission: false,
      writePermission: false,
      deletePermission: false,
      verifyPermission: false
    }
  },

  methods: {
    initPermission (resource) {
      let productPermission = this.$store.getters.getpermissions[resource];

      if (productPermission != null && productPermission != undefined) {
        this.readPermission = RuleTool.haveReadPermission(productPermission);
        this.writePermission = RuleTool.haveWritePermission(productPermission);
        this.deletePermission = RuleTool.haveDeletePermission(productPermission);
        this.verifyPermission = RuleTool.haveVerifyPermission(productPermission);
      }
    },
  }
}