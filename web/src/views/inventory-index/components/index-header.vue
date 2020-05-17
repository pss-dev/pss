<template>
	<header class="index-header">
		<div class="info">
			<div class="header-left">进销存logo</div>
			<div class="header-right" @click="logout()">登出</div>
		</div>
	</header>
</template>

<script>
export default {
	name: "IndexHeader",
	data() {
		return {}
	},
	methods: {
		logout() {
			this.$message({
				message: "尚未开发",
				showClose: true,
			})
		},
	},
}
</script>

<style lang="scss" scoped>
.index-header {
	margin-top: 30px;
	padding-right: 20px;
	min-height: 60px;
	border-bottom: 1px solid #999;
	.info {
		display: flex;
		align-items: center;
		padding: 10px 0;
	}
	.header-left {
		width: 80%;
		text-align: center;
	}
	.header-right {
		width: 20%;
		cursor: pointer;
	}
	.nav {
		padding: 10px 0;
	}
}
</style>
