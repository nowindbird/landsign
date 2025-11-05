package com.example.langsign
/*这是一个普通用户定义类
定义了账户名称，账户账号，账户密码*/
class User {
    private var phoneNumber: String? = null  // 账户名称
    private var accountNumber: String? = null  // 账户账号
    private var password: String? = null  // 账户密码

    // 无参构造器
    constructor()


    constructor(userName: String, accountNumber: String, password: String) {
        this.phoneNumber = userName
        this.accountNumber = accountNumber
        this.password = password
    }

    // Getter/Setter
    fun getUserName(): String? {
        return phoneNumber
    }

    fun setUserName(userName: String) {
        this.phoneNumber = userName
    }

    fun getAccountNumber(): String? {
        return accountNumber
    }

    fun setAccountNumber(accountNumber: String) {
        this.accountNumber = accountNumber
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }
}