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
        v-for="item in editableTabs"
        :key="item.name"
        :label="item.title"
        :closable="!item.discloseable"
        :name="item.name"
      >
        <keep-alive>
          <component
            :is="item.componentName"
            :data="item.data"
            :permission="item.permission"
            @openOrderForm="addOrderForm"
          ></component>
        </keep-alive>
      </el-tab-pane>
    </el-tabs>
  </main>
</template>

<script>
let baseInfoMenuArr = [
];

let orderManageMenuArr = [
];

let manageSettingMenuArr = [
];

let statisticsgMenuArr = [
];

let firstMenuArr = [
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
import Role from "@/views/base-info/role-info/role-info";
import Account from "@/views/base-info/account-info/account-info";
import Customer from "@/views/base-info/company-info/customer-info";
import Supplier from "@/views/base-info/company-info/supplier-info";
import Department from "@/views/base-info/department-info/department-info";
import Branch from "@/views/base-info/branch-info/branch-info";
import PurchaseForm from "@/views/order-form/purchase-form";
import PurchaseReturnForm from "@/views/order-form/purchase-return-form";
import SalesReturnForm from "@/views/order-form/sales-return-form";
import SalesForm from "@/views/order-form/sales-form";
import FormSearch from "@/views/order-form/form-search";
import Log from "@/views/statistics/log/log";
import Revenue from "@/views/statistics/revenue/revenue"

import Tool from '@/views/constant/tool.js'
import RuleTool from '@/views/constant/rule-tool.js'

//import EmployeeApi from '@/api/employee-info-api/employeeInfoApi.js'

export default {
  components: {
    Product,
    Unit,
    Depot,
    Price,
    Employee,
    Role,
    Account,
    Customer,
    Supplier,
    Department,
    Branch,
    PurchaseForm,
    PurchaseReturnForm,
    SalesReturnForm,
    SalesForm,
    FormSearch,
    Log,
    Revenue
  },
  data () {
    return {
      activeIndex: "1-1",
      navData: [],
      editableTabsValue: "",
      editableTabs: []
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
      this.addTab(item);
    },

    handleClick (tab, event) {
      console.log(tab, event);
    },

    addTab ({ label, value }, data) {
      if (!label || !value) {
        this.$message({
          message: `名字为${label}，组件名为${value}tab，名字和组件名都不能为空！`,
          showClose: true
        });
        return;
      }

      let name = this.getNextComponentName(value);

      let val = {
        title: label,
        name: name,
        componentName: value,
        data: data,
      }

      this.editableTabs.push(val);
      this.editableTabsValue = name;
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
    },

    getNextComponentName (name) {
      let sameComponents = this.editableTabs.filter(tab => tab.name.indexOf(name) == 0);

      if (sameComponents.length < 1) {
        return name + "0";
      }

      let maxNumber = parseInt(sameComponents[0].name.substr(name.length));

      sameComponents.forEach((value) => {
        let number = parseInt(value.name.substr(name.length));

        if (number > maxNumber) {
          maxNumber = number;
        }
      });

      maxNumber += 1;

      return name + maxNumber;
    },

    addOrderForm (orderForm) {
      let newTab = {};

      switch (orderForm.type) {
        case Tool.orderFormType.purchaseForm:
          newTab = {
            label: "进货单",
            value: "PurchaseForm"
          }
          break;
        case Tool.orderFormType.salesForm:
          newTab = {
            label: "销售单",
            value: "SalesForm"
          };
          break;
        case Tool.orderFormType.purchaseReturn:
          newTab = {
            label: "入库退货单",
            value: "PurchaseReturnForm"
          };
          break;
        case Tool.orderFormType.salesReturn:
          newTab = {
            label: "销售退货单",
            value: "SalesReturnForm"
          };
          break;
      }

      this.addTab(newTab, orderForm);
    },

    init (permissions) {
      let orderformVisiabel = false;
      let permissionStore = {};

      permissions.forEach((permission) => {
        console.log("=====  permission ", permission);
        permissionStore[permission.resource] = permission.operator;

        switch (+permission.resource) {
          case RuleTool.resource.product:
            baseInfoMenuArr.push({
              label: "商品档案",
              value: "Product"
            });
            break;
          case RuleTool.resource.unit:
            baseInfoMenuArr.push({
              label: "商品计量单位",
              value: "Unit"
            });
            break;
          case RuleTool.resource.price:
            baseInfoMenuArr.push({
              label: "价格名称",
              value: "Price"
            });
            break;
          case RuleTool.resource.company:
            baseInfoMenuArr.push({
              label: "客户档案",
              value: "Customer"
            });
            baseInfoMenuArr.push({
              label: "供货商档案",
              value: "Supplier"
            });
            break;
          case RuleTool.resource.depot:
            baseInfoMenuArr.push({
              label: "存货仓库",
              value: "Depot"
            });
            break;
          case RuleTool.resource.department:
            baseInfoMenuArr.push({
              label: "部门档案",
              value: "Department"
            });
            break;
          case RuleTool.resource.branch:
            baseInfoMenuArr.push({
              label: "分支机构",
              value: "Branch"
            });
            break;
          case RuleTool.resource.orderForm:
            orderformVisiabel = true;
            break;
          case RuleTool.resource.role:
            manageSettingMenuArr.push({
              label: "角色管理",
              value: "Role"
            });
            break;
          case RuleTool.resource.employee:
            manageSettingMenuArr.push({
              label: "职员档案",
              value: "Employee"
            });
            break;
          case RuleTool.resource.account:
            manageSettingMenuArr.push({
              label: "账户信息",
              value: "Account"
            });
            break;
          case RuleTool.resource.log:
            statisticsgMenuArr.push({
              label: "日志",
              value: "Log"
            });
            break;
          case RuleTool.resource.revenue:
            statisticsgMenuArr.push({
              label: "营收",
              value: "Revenue"
            });
            break;
        }
      });

      this.$store.commit("setpermissions", permissionStore);

      console.log("=========permissionStore ", permissionStore);
      if (orderformVisiabel) {
        orderManageMenuArr = [
          {
            label: "进货单",
            value: "PurchaseForm"
          },
          {
            label: "销售单",
            value: "SalesForm"
          },
          {
            label: "入库退货单",
            value: "PurchaseReturnForm"
          },
          {
            label: "销售退货单",
            value: "SalesReturnForm"
          },
          {
            label: "单据查询",
            value: "FormSearch"
          }
        ];
      }

      if (baseInfoMenuArr.length > 0) {
        firstMenuArr.push({
          label: "基本资料",
          value: "baseInfo",
          children: baseInfoMenuArr
        });
      }

      if (orderManageMenuArr.length > 0) {
        firstMenuArr.push({
          label: "订单管理",
          value: "orderManage",
          children: orderManageMenuArr
        });
      }

      if (manageSettingMenuArr.length > 0) {
        firstMenuArr.push({
          label: "管理设置",
          value: "manageSetting",
          children: manageSettingMenuArr
        });
      }

      if (statisticsgMenuArr.length > 0) {
        firstMenuArr.push({
          label: "统计查询",
          value: "statistics",
          children: statisticsgMenuArr
        });
      }
    },
  },
  created () {
    // get all permission
    let perms = [
      { resource: "2", operator: 16 },
      { resource: "4", operator: 16 },
      { resource: "8", operator: 16 },
      { resource: "16", operator: 16 },
      { resource: "32", operator: 16 },
      { resource: "64", operator: 16 },
      { resource: "128", operator: 16 },
      { resource: "256", operator: 16 },
      { resource: "1024", operator: 16 },
      { resource: "2048", operator: 16 },
      { resource: "4096", operator: 16 },
      { resource: "16384", operator: 16 },
      { resource: "32768", operator: 16 },
    ]
    //EmployeeApi.getPermission().then((res) => {
    //  console.log(res.data);
    this.init(perms);
    this.navData = getNavData();
    //});
  }
};
</script>

<style lang="scss" scoped>
</style>
