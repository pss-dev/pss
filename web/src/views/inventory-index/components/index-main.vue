<template>
  <main class="idnex-main">
    <el-menu :default-active="activeIndex"
             class="el-menu-demo nav"
             mode="horizontal"
             @select="handleSelect">
      <template v-for="item in navData">
        <el-menu-item v-if="!item.children"
                      :key="item.index"
                      :index="item.index"
                      @click="openTab(item)">{{ item.label }}</el-menu-item>
        <el-submenu v-if="item.children"
                    :key="item.index"
                    :index="item.index">
          <template slot="title">{{ item.label }}</template>
          <el-menu-item v-for="childItem in item.children"
                        :key="childItem.index"
                        :index="childItem.index"
                        @click="openTab(childItem)">{{ childItem.label }}</el-menu-item>
        </el-submenu>
      </template>
    </el-menu>
    <el-tabs v-model="editableTabsValue"
             type="card"
             @tab-remove="removeTab">
      <el-tab-pane :key="item.name"
                   v-for="item in editableTabs"
                   :label="item.title"
                   :closable="!item.discloseable"
                   :name="item.name">
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
    discloseable: true
  },
  {
    label: "商品类别",
    value: "Unit"
  },
  {
    label: "商品计量单位",
    value: "Depot"
  },
  {
    label: "价格名称"
  },
  {
    label: "客户档案"
  },
  {
    label: "供货商档案"
  },
  {
    label: "地区档案"
  },
  {
    label: "部门档案"
  },
  {
    label: "职员档案"
  },
  {
    label: "存货仓库"
  },
  {
    label: "角色管理"
  },
  {
    label: "账户选择"
  }
];

let orderManageMenuArr = [
  {
    label: "进货单"
  },
  {
    label: "销售单"
  },
  {
    label: "退货单"
  },
  {
    label: "单据查询"
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
    value: "manageSetting"
  },
  {
    label: "日志",
    value: "log"
  }
];

function getNavData() {
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
export default {
  components: {
    Product,
    Unit,
    Depot
  },
  data() {
    return {
      activeIndex: "1-1",
      navData: getNavData(),
      editableTabsValue: "Product",
      editableTabs: [
        {
          title: "商品档案",
          name: "Product",
          discloseable: true
        }
      ]
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    judgeIsAdd(tabName) {
      return this.editableTabs.findIndex(ele => ele.name === tabName) < 0;
    },
    openTab(item) {
      if (this.judgeIsAdd(item.value)) {
        this.addTab(item);
      } else {
        this.editableTabsValue = item.value;
      }
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    addTab({ label, value, discloseable }) {
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
        discloseable
      });
      this.editableTabsValue = value;
    },
    removeTab(targetName) {
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
  created() {
    // console.log(this.navData)
  }
};
</script>

<style lang="scss" scoped>
</style>
