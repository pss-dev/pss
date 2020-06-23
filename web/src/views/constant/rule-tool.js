export default {
  rule: {
    read: 0x01,
    write: 0x02,
    delete: 0x04,
    verify: 0x08
  },

  source: {
    baseInfo: 1,
    product: 11,
    unit: 12,
    price: 13,
    customer: 14,
    supplier: 15,
    depot: 16,
    department: 17,
    branch: 18,
    orderForm: 2,
    manage: 3,
    role: 31,
    employee: 32,
    account: 33,
    statistic: 4,
    log: 41,
    revenue: 42,
  }
}