package com.example.langsign.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.langsign.R
import com.example.langsign.User
import com.example.langsign.service.LoginServiceImpl
class LoginPage : AppCompatActivity() {
    // 声明视图组件
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var forgotPasswordTextView: TextView
    private lateinit var registerTextView: TextView
    private lateinit var errorTextView: TextView

    // 用户对象
    private val user = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 加载布局文件
        setContentView(R.layout.activity_login_page)
        // 初始化视图组件（通过 findViewById 绑定布局中的控件）
        this.initViews()
        // 导入实现类
        val loginService = LoginServiceImpl()
        // 设置按钮点击事件
        loginService.setClickListeners( loginButton,usernameEditText, passwordEditText, this, user)
        //检测输入的是否正确,如果正确跳转并进入新页面，如果错误跳出报错信息


    }

    // 初始化视图组件
    private fun initViews() {
        //输入用户名的框口
        usernameEditText = findViewById(R.id.usernameEditText)
        //输入密码的框口
        passwordEditText = findViewById(R.id.passwordEditText)
        //登录按钮
        loginButton = findViewById(R.id.loginButton)
        //忘记密码的文本框
        forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView)
        //
        registerTextView = findViewById(R.id.registerTextView)
        //报错文本
        errorTextView = findViewById(R.id.errorTextView)
    }

}




