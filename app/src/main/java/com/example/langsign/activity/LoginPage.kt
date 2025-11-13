package com.example.langsign.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.langsign.R
import com.example.langsign.User
import com.example.langsign.dao.LoginRequest
import com.example.langsign.dao.LoginResponse
import com.example.langsign.dao.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
        // 加载布局文件（确保布局文件名是 activity_login_page.xml）
        setContentView(R.layout.activity_login_page)
        // 初始化视图组件（通过 findViewById 绑定布局中的控件）
        initViews()
        // 设置按钮点击事件
        setClickListeners()
        //检测输入的是否正确,如果正确跳转并进入新页面，如果错误跳出报错信息


    }

    // 初始化视图组件
    private fun initViews() {
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView)
        registerTextView = findViewById(R.id.registerTextView)
        errorTextView = findViewById(R.id.errorTextView)
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
            user.userName=username
            user.password=password
            //为上传到后端包装数据
            val loginRequest =LoginRequest(username, password)
            //调用call接口
            val call = ApiService.RetrofitClient.apiService.login(loginRequest)
            call.enqueue(object : Callback<LoginResponse> {
                // 请求成功回调
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        loginResponse?.let {
                            if (it.success) {
                                // 登录成功，保存token等信息
                                // 这里可以使用SharedPreferences保存登录状态
                                Toast.makeText(this@LoginPage, "登录成功: ${it.message}", Toast.LENGTH_SHORT).show()

                                // 跳转到主页面(未写

                            } else {
                                // 服务器返回登录失败
                                errorTextView.text = it.message
                            }
                        } ?: run {
                            errorTextView.text = "登录失败，服务器返回空数据"
                        }
                    } else {
                        // HTTP状态码不是200-299
                        errorTextView.text = "登录失败，错误码: ${response.code()}"
                    }
                }

                // 请求失败回调
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // 网络错误等情况
                    errorTextView.text = "网络请求失败: ${t.message}"
                    t.printStackTrace() // 打印错误日志，方便调试
                }
            })
        }

        }

    }
