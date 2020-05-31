<template>
  <main class="idnex-main">
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo nav"
      mode="horizontal"
      @select="handleSelect"
    >
      <template v-for="item in navData">
        <el-menu-item
          v-if="!item.children"
          :key="item.index"
          :index="item.index"
          @click="openTab(item)"
        >{{ item.label }}</el-menu-item>
        <el-submenu v-if="item.children" :key="item.index" :index="item.index">
          <template slot="title">{{ item.label }}</template>
          <el-menu-item
            v-for="childItem in item.children"
            :key="childItem.index"
            :index="childItem.index"
            @click="openTab(childItem)"
          >{{ childItem.label }}</el-menu-item>
        </el-submenu>
      </template>
    </el-menu>
    <el-tabs v-model="editableTabsValue" type="card" @tab-remove="removeTab">
      <el-tab-pane
        :key="item.name"
        v-for="item in editableTabs"
        :label="item.title"
        :closable="!item.discloseable"
        :name="item.name"
      >
        <keep-alive>
          <component :is="item.name"></component>
        </keep-alive>
      </el-tab-pane>
    </el-tabs>
  </main>
</template>

<script>
let baseInfoMenuArr = [
  {
    label: "商品档案",
    value: "Product",
  },
  {
    label: "商品计量单位",
    value: "Unit"
  },
  {
    label: "价格名称",
    value: "Price"
  },
  {
    label: "客户档案",
    value: "Customer"
  },
  {
    label: "供货商档案",
    value: "Supplier"
  },
  {
    label: "存货仓库",
    value: "Depot"
  },
  {
    label: "部门档案",
    value: "Department"
  },
  {
    label: "分支机构",
    value: "Branch"
  },
];

let orderManageMenuArr = [
  {
    label: "进货单",
    value: "PurchaseForm"
  },
  {
    label: "销售单",
    value: "SalesForm"
  },
  {
    label: "退货单",
    value: "ReturnForm"
  },
  {
    label: "单据查询"
  }
];

let manageSettingMenuArr = [
  {
    label: "角色管理",
    value: "Ruler"
  },
  {
    label: "职员档案",
    value: "Employee"
  },
  {
    label: "账户信息",
    value: "Account"
  },
];

let statisticsgMenuArr = [
  {
    label: "日志"
  },
  {
    label: "营收"
  }
];

const firstMenuArr = [
  // value为组件名，决定了tab页内容
  {
    label: "基本资料",
    value: "baseInfo",
    children: baseInfoMenuArr
  },
  {
    label: "订单管理",
    value: "orderManage",
    children: orderManageMenuArr
  },
  {
    label: "管理设置",
    value: "manageSetting",
    children: manageSettingMenuArr
  },
  {
    label: "统计查询",
    value: "statistics",
    children: statisticsgMenuArr
  }
];

function getNavData () {
  let navArr = firstMenuArr.slice(0); // 浅克隆
  navArr.forEach((ele, index) => {
    ele.index = index + 1 + "";
    if (ele.children) {
      ele.children.forEach((childEle, childIndex) => {
        childEle.index = `${index + 1}-${childIndex + 1}`;
      });
    }
  });
  console.log(navArr);
  return navArr;
}

import Product from "@/views/base-info/product-info/product-info";
import Unit from "@/views/base-info/unit-info/unit-info";
import Depot from "@/views/base-info/depot-info/depot-info";
import Price from "@/views/base-info/price-info/price-info";
import Employee from "@/views/base-info/employee-info/employee-info";
import Ruler from "@/views/base-info/ruler-info/ruler-info";
import Account from "@/views/base-info/account-info/account-info";
import Customer from "@/views/base-info/company-info/customer-info";
import Supplier from "@/views/base-info/company-info/supplier-info";
import Department from "@/views/base-info/department-info/department-info";
import Branch from "@/views/base-info/branch-info/branch-info";
import PurchaseForm from "@/views/order-form/purchase-form";
import ReturnForm from "@/views/order-form/return-form";
import SalesForm from "@/views/order-form/sales-form";
export default {
  components: {
    Product,
    Unit,
    Depot,
    Price,
    Employee,
    Ruler,
    Account,
    Customer,
    Supplier,
    Department,
    Branch,
    PurchaseForm,
    ReturnForm,
    SalesForm
  },
  data () {
    return {
      activeIndex: "1-1",
      navData: getNavData(),
      editableTabsValue: "Product",
      editableTabs: [
        {
          title: "商品档案",
          name: "Product",
        }
      ]
    };
  },
  methods: {
    handleSelect (key, keyPath) {
      console.log(key, keyPath);
    },
    judgeIsAdd (tabName) {
      return this.editableTabs.findIndex(ele => ele.name === tabName) < 0;
    },
    openTab (item) {
      if (this.judgeIsAdd(item.value)) {
        this.addTab(item);
      }
      else {
        this.editableTabsValue = item.value;
      }
    },
    handleClick (tab, event) {
      console.log(tab, event);
    },
    addTab ({ label, value }) {
      if (!label || !value) {
        this.$message({
          message: `名字为${label}，组件名为${value}tab，名字和组件名都不能为空！`,
          showClose: true
        });
        return;
      }
      this.editableTabs.push({
        title: label,
        name: value,
      });
      this.editableTabsValue = value;
    },
    removeTab (targetName) {
      let tabs = this.editableTabs;
      let activeName = this.editableTabsValue;
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.name === targetName) {
            let nextTab = tabs[index + 1] || tabs[index - 1];
            if (nextTab) {
              activeName = nextTab.name;
            }
          }
        });
      }

      this.editableTabsValue = activeName;
      this.editableTabs = tabs.filter(tab => tab.name !== targetName);
    }
  },
  created () {
    // console.log(this.navData)
  }
};
</script>

<style lang="scss" scoped>
</style>
