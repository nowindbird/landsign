package com.example.langsign

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


// 数据类：用于存储用户信息


class LoginPage : AppCompatActivity() {
    // 声明视图组件
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var forgotPasswordTextView: TextView
    private lateinit var registerTextView: TextView

    // 用户对象
    private val user = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 加载布局文件（确保布局文件名是 activity_login_page.xml）
        setContentView(R.layout.activity_login_page)
        // 初始化视图组件（通过 findViewById 绑定布局中的控件）
        initViews()
        // 设置按钮点击事件
        setClickListeners()
        IsinputRight()

    }

    // 初始化视图组件
    private fun initViews() {
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView)
        registerTextView = findViewById(R.id.registerTextView)
    }

    // 设置点击事件
    private fun setClickListeners() {
        // 登录按钮点击事件
        loginButton.setOnClickListener {
            val username = usernameEditText.text?.toString()?.trim() ?: ""
            val password = passwordEditText.text?.toString()?.trim() ?: ""

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // 保存用户输入
            user.setUserName(username)
            user.setUserName(password)
        }

    }
    //前端输入时限定
    private fun IsinputRight(){

    }
}