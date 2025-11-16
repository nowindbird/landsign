package com.example.langsign.client

// 登录接口响应数据模型
data class LoginResponse(
    val success: Boolean,
    val msg: String,
    val token: String? ,
    val message:String?
)

// 登录请求参数模型（传给后端的用户名和密码）
data class LoginRequest(
    val username: String,
    val password: String
)