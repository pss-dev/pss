<template>
	<main class="idnex-main">
		<div class="nav-temp">
			<a href="/dept">Department</a>
			---
			<a href="/order">OrderForm</a>
			---
			<a href="/company">Company</a>
			---
			<a href="/branch">Branch</a>
			---
			<a href="/price">Price</a>
			---
			<a href="/depot">Depot</a>
			---
			<a href="/unit">Unit</a>
			---
			<a href="/product">Product</a>
			---
			<a href="/account">Account</a>
			---
			<a href="/employee">Employee</a>
		</div>
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
					>{{ item.label }}</el-menu-item
				>
				<el-submenu v-if="item.children" :key="item.index" :index="item.index">
					<template slot="title">{{ item.label }}</template>
					<el-menu-item
						v-for="childItem in item.children"
						:key="childItem.index"
						:index="childItem.index"
						@click="openTab(childItem)"
						>{{ childItem.label }}</el-menu-item
					>
				</el-submenu>
			</template>
		</el-menu>
		<el-tabs
			v-model="editableTabsValue"
			type="card"
			closable
			@tab-remove="removeTab"
		>
			<el-tab-pane
				:key="item.name"
				v-for="item in editableTabs"
				:label="item.title"
				:name="item.name"
			>
				{{ item.content }}
			</el-tab-pane>
		</el-tabs>
	</main>
</template>

<script>
let baseInfoMenuArr = [
	{
		label: "商品档案",
	},
	{
		label: "商品类别",
	},
	{
		label: "商品计量单位",
	},
	{
		label: "价格名称",
	},
	{
		label: "客户档案",
	},
	{
		label: "供货商档案",
	},
	{
		label: "地区档案",
	},
	{
		label: "部门档案",
	},
	{
		label: "职员档案",
	},
	{
		label: "存货仓库",
	},
	{
		label: "角色管理",
	},
	{
		label: "账户选择",
	},
]

let orderManageMenuArr = [
	{
		label: "进货单",
	},
	{
		label: "销售单",
	},
	{
		label: "退货单",
	},
	{
		label: "单据查询",
	},
]
const firstMenuArr = [
	// value为组件名，决定了tab页内容
	{
		label: "基本资料",
		value: "baseInfo",
		children: baseInfoMenuArr,
	},
	{
		label: "订单管理",
		value: "orderManage",
		children: orderManageMenuArr,
	},
	{
		label: "管理设置",
		value: "manageSetting",
	},
	{
		label: "日志",
		value: "log",
	},
]

function getNavData() {
	let navArr = firstMenuArr.slice(0) // 浅克隆
	navArr.forEach((ele, index) => {
		ele.index = index + 1 + ""
		if (ele.children) {
			ele.children.forEach((childEle, childIndex) => {
				childEle.index = `${index + 1}-${childIndex + 1}`
			})
		}
	})
	console.log(navArr)
	return navArr
}

export default {
	data() {
		return {
			activeIndex: "0",
			navData: getNavData(),
			editableTabsValue: "2",
			editableTabs: [
				{
					title: "Tab 1",
					name: "1",
					content: "Tab 1 tabs与nav联动尚未开发",
				},
				{
					title: "Tab 2",
					name: "2",
					content: "Tab 2 tabs与nav联动尚未开发",
				},
			],
			tabIndex: 2,
		}
	},
	methods: {
		handleSelect(key, keyPath) {
			console.log(key, keyPath)
		},
		openTab(item) {
			this.$message({
				message: `打开名字为${item.label}，组件名为${item.value}tab，尚在开发`,
				showClose: true,
			})
		},
		handleClick(tab, event) {
			console.log(tab, event)
		},
		removeTab(targetName) {
			let tabs = this.editableTabs
			let activeName = this.editableTabsValue
			if (activeName === targetName) {
				tabs.forEach((tab, index) => {
					if (tab.name === targetName) {
						let nextTab = tabs[index + 1] || tabs[index - 1]
						if (nextTab) {
							activeName = nextTab.name
						}
					}
				})
			}

			this.editableTabsValue = activeName
			this.editableTabs = tabs.filter((tab) => tab.name !== targetName)
		},
	},
}
</script>

<style lang="scss" scoped>
.index-main {
}
</style>
