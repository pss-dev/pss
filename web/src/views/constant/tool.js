export default {
  actionType: {
    add: 1,
    delete: 2,
    update: 3
  },

  componentType: {
    product: 1,
    unit: 2,
    depot: 3,
    price: 4,
    employee: 5,
    ruler: 6,
    account: 7,
    customer: 8,
    supplier: 9,
    department: 10,
    branch: 11,
    purchaseForm: 12,
    purchaseReturnForm: 13,
    salesForm: 14,
    salesReturnForm: 15,
    log: 16
  },

  orderFormType: {
    purchaseForm: 1,
    salesForm: 2,
    purchaseReturn: 3,
    salesReturn: 4
  },

  orderFormStatus: {
    draft: 1,
    verify: 2,
  }
}