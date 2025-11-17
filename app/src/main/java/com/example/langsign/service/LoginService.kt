package com.example.langsign.service

import android.app.Activity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.langsign.User
import com.example.langsign.client.ApiService
import com.example.langsign.client.LoginRequest


// 定义接口
interface LoginService {
    fun setClickListeners(bt1: Button, name: EditText, password: EditText, activity: Activity, user1:User)
    fun checkBothNotEmpty(st1: String, st2: String,  activity: Activity): Int
    fun packMessage(username: String, userPassword: String)
    fun errorMessage(code: Int, activity: Activity)
}

// 实现接口
class LoginServiceImpl : LoginService {
    override fun setClickListeners(bt1: Button, name: EditText, password: EditText, activity: Activity,  user1: User) {
        bt1.setOnClickListener {

            // 获取输入框内容并处理空值
            val username = name.text?.toString()?.trim() ?: ""
            val userPassword = password.text?.toString()?.trim() ?: ""
            // 在监听器内部调用检查方法（修复作用域问题）
            errorMessage(checkBothNotEmpty(username, userPassword,  activity),activity)
            user1.userName = username
            user1.password = userPassword
            packMessage(username, userPassword)
        }
    }

    override fun checkBothNotEmpty(st1: String, st2: String,  activity: Activity):Int {
        // 检查字符串是否为空（参数改为String类型，与调用处匹配）
        if(st1.isNotEmpty() && st2.isNotEmpty()){
            return 1
        }
        return 0
    }
    override fun packMessage(username: String, userPassword: String){
        val loginRequest =LoginRequest(username, userPassword)
        val call = ApiService.RetrofitClient.apiService.login(loginRequest)
    }
    override fun errorMessage(code:Int,activity: Activity){
        if(code==1){
            Toast.makeText(activity, "密码和账号不能为空", Toast.LENGTH_SHORT).show()
        }
    }
}
