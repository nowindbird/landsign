package com.example.langsign

import java.time.LocalDate
/*
 * 用户类
 * 定义了账户名称，账户账号，账户密码和电话号码
 */
class User {
    // 使用var定义可修改的属性，默认提供getter和setter
    var phoneNumber: String? = null  // 电话号码
    var accountNumber: String? = null  // 账户账号
    var password: String? = null  // 账户密码
    var userName: String? = null  // 账号名称
    var userGender: String? = null  // 性别（例如："男"、"女"、"保密"）
    var birthDate: LocalDate? = null  // 出生日期


    // 无参构造器
    constructor()

    // 全参构造器
    constructor(
        userName: String,
        accountNumber: String,
        password: String,
        phoneNumber: String,
        userGender: String?,
        birthDate: LocalDate?
    ) {
        this.userName = userName
        this.accountNumber = accountNumber
        this.password = password
        this.phoneNumber = phoneNumber
        this.userGender = userGender
        this.birthDate = birthDate
    }
    // 重写toString方法
    override fun toString(): String {
        return "User(" +
                "userName=$userName, " +
                "accountNumber=$accountNumber, " +
                "phoneNumber=******, " +
                "gender=$userGender, " +
                "birthDate=$birthDate"+
                "password=******" +
                ")"
    }
}
