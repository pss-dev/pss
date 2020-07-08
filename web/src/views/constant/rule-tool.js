export default {
  rule: {
    read: 0x01,
    write: 0x02,
    delete: 0x04,
    verify: 0x08,
    admin: 0x10
  },

  haveReadPermission: function (op) {
    return op & this.rule.read == this.rule.read || op & this.rule.admin == this.rule.admin;
  },

  haveWritePermission: function (op) {
    return op & this.rule.write == this.rule.write || op & this.rule.admin == this.rule.admin;
  },

  haveDeletePermission: function (op) {
    return op & this.rule.delete == this.rule.delete || op & this.rule.admin == this.rule.admin;
  },

  haveVerifyPermission: function (op) {
    return op & this.rule.admin == this.rule.admin || op & this.rule.admin == this.rule.admin;
  },

  resource: {
    baseInfo: "1",
    product: "2",
    unit: "4",
    price: "8",
    company: "16",
    depot: "32",
    department: "64",
    branch: "128",
    orderForm: "256",
    manage: "512",
    role: "1024",
    employee: "2048",
    account: "4096",
    statistic: "8192",
    log: "16384",
    revenue: "32768",
  }
}
