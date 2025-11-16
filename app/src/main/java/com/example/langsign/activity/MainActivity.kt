package com.example.langsign.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.langsign.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 初始化视图
        val appLogo = findViewById<ImageView>(R.id.app_logo)
        val appName = findViewById<TextView>(R.id.app_name)
        // 1. 执行依次滑入动画
        startSlideInAnimations(appLogo, appName)
    }

    // 依次滑入动画（Logo先动，文字后动）
    private fun startSlideInAnimations(logo: ImageView, name: TextView) {
        val slideInTop = AnimationUtils.loadAnimation(this, R.anim.slide_in_top)
        val slideInBottom = AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom)

        // Logo滑入动画
        logo.startAnimation(slideInTop)

        // 文字滑入动画（监听Logo动画结束，确保顺序连贯性）
        slideInTop.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                // Logo滑入结束后，文字开始滑入
                name.startAnimation(slideInBottom)
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        // 2. 文字滑入结束后，执行发光动画
        slideInBottom.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                startGlowAnimation(logo)
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
    }

    // 发光动画（Logo缩放+阴影增强）
    private fun startGlowAnimation(logo: ImageView) {
        val glowAnim = AnimationUtils.loadAnimation(this, R.anim.glow_animation)
        // 给Logo添加阴影（增强发光效果）
        logo.elevation = 20f  // 提升阴影高度（需API 21+）
        logo.startAnimation(glowAnim)

        // 3. 发光动画结束后，执行开门跳转
        glowAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                // 延迟300ms让发光效果收尾，再执行开门动画
                Handler(Looper.getMainLooper()).postDelayed({
                    jumpToLogin()
                }, 300)
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
    }

    // 开门式跳转到登录页
    private fun jumpToLogin() {
        val intent = Intent(this, LoginPage::class.java)
        startActivity(intent)
        // 应用过渡动画：启动页分左右开门退出，登录页淡入
        overridePendingTransition(
            R.anim.login_enter,  // 登录页进入动画
            R.anim.door_open_left // 启动页退出动画（左门）
            // 若需双开门效果，可自定义Transition实现左右同时分开
        )
        finish() // 关闭启动页，防止返回
    }

    companion object {
        // 无需硬编码总时长，动画流程通过监听自动衔接
    }
}