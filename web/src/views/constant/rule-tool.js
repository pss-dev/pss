export default {
  rule: {
    read: 0x01,
    write: 0x02,
    delete: 0x04,
    verify: 0x08,
    admin: 0x10
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
