package com.example.langsign.dao

// 登录接口响应数据模型
data class LoginResponse(
    val success: Boolean,
    val msg: String,
    val token: String?  // 可为null（登录失败时可能没有token）
)

// 登录请求参数模型（传给后端的用户名和密码）
data class LoginRequest(
    val username: String,
    val password: String
)