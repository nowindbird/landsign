package com.example.langsign.dao
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


class ApiService {


    // 1. 定义API接口
    interface ApiService {
        // 登录接口：POST请求，参数为LoginRequest对象（会自动转为JSON）
        @POST("api/login")  // 接口路径
        fun login(@Body request: LoginRequest): Call<LoginResponse>
    }

    // 2. 创建Retrofit实例
    object RetrofitClient {
        // 基础URL（替换为你的后端服务器地址，必须以/结尾）
        private const val BASE_URL = "http://192.168.1.100:8080/"  // 本地测试用
        // 生产环境可能是："https://你的域名/"

        val apiService: ApiService by lazy {
            // 可选：添加日志拦截器（调试用）
            val logging = okhttp3.logging.HttpLoggingInterceptor().apply {
                level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
            }
            val client = okhttp3.OkHttpClient.Builder()
                .addInterceptor(logging)  // 添加日志拦截器
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)  // 关联okhttp客户端
                .addConverterFactory(GsonConverterFactory.create())  // 使用Gson解析JSON
                .build()
                .create(ApiService::class.java)
        }
    }
}