<template>
	<div class="inventory-login">
		<header>
			进销存登录
		</header>
		<main>
			<el-form
				:model="ruleForm"
				:rules="rules"
				ref="ruleForm"
				label-width="100px"
				class="demo-ruleForm"
			>
				<el-form-item label="用户名" prop="userName">
					<el-input
						v-model="ruleForm.userName"
						clearable
						placeholder="请输入用户名"
					></el-input>
				</el-form-item>
				<el-form-item label="密码" prop="password">
					<el-input
						placeholder="请输入密码"
						v-model="ruleForm.password"
						show-password
					></el-input>
				</el-form-item>
			</el-form>
			<el-button class="login-button" @click="login('ruleForm')" type="primary"
				>登录</el-button
			>
		</main>
	</div>
</template>

<script>
export default {
	name: "InventoryLogin",
	data() {
		return {
			ruleForm: {
				userName: "",
				password: "",
			},
			rules: {
				userName: [
					{ required: true, message: "用户名不能为空", trigger: "blur" },
				],
				password: [
					{ required: true, message: "密码不能为空", trigger: "blur" },
				],
			},
		}
	},
	methods: {
		login(formName) {
			this.$message({
				message: `尚未开发完成`,
				showClose: true,
			})
			this.$refs[formName].validate((valid) => {
				if (valid) {
					alert("submit!")
				} else {
					console.log("error submit!!")
					return false
				}
			})
		},
	},
}
</script>

<style lang="scss" scoped>
.inventory-login {
	margin-top: 30px;
	main {
		position: fixed;
		top: 40%;
		left: 50%;
		transform: translate(-50%, -50%);
	}
	.block_input-div {
		display: flex;
		align-items: center;
		margin-bottom: 20px;
		.label {
			width: 80px;
		}
	}
	.el-input {
		width: 200px;
	}
	.login-button {
		margin-top: 10px;
	}
}
</style>
<style lang="scss">
.inventory-login {
	.el-form-item.is-required:not(.is-no-asterisk)
		.el-form-item__label-wrap
		> .el-form-item__label:before,
	.el-form-item.is-required:not(.is-no-asterisk) > .el-form-item__label:before {
		color: transparent;
	}
}
</style>
