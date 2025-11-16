package com.example.langsign.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDate

/**
 * 用户实体类，对应users
 */
@Entity(tableName = "users")
@TypeConverters(LocalDateConverter::class) // 用于转换 LocalDate 类型
data class User(
    // 主键：自增ID（唯一标识用户，Room 必需）
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    // 电话号码（保留可空，允许用户不填写）
    @ColumnInfo(name = "phone_number") // 数据库列名用下划线风格
    var phoneNumber: String? = null,

    // 账户账号（保留可空，根据业务是否必填调整）
    @ColumnInfo(name = "account_number")
    var accountNumber: String? = null,

    // 密码（建议非空，这里保留可空以匹配你的原始定义，实际应设为非空）
    var password: String? = null,

    // 账号名称（保留可空）
    @ColumnInfo(name = "user_name")
    var userName: String? = null,

    // 性别（用字符串，保留你定义的"男"/"女"/"保密"格式）
    @ColumnInfo(name = "user_gender")
    var userGender: String? = null,

    // 出生日期（LocalDate 类型，通过转换器支持数据库存储）
    @ColumnInfo(name = "birth_date")
    var birthDate: LocalDate? = null
)

/**
 * LocalDate 类型转换器：将 LocalDate 转为 String 存入数据库，读取时转回
 */
class LocalDateConverter {
    // 存储时：LocalDate → String（格式：yyyy-MM-dd）
    @TypeConverter
    fun toDatabaseValue(date: LocalDate?): String? {
        return date?.toString()
    }

    // 读取时：String → LocalDate
    @TypeConverter
    fun fromDatabaseValue(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }
}
