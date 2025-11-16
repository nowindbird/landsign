package com.example.langsign.dao
import com.example.langsign.entity.User
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import androidx.room.OnConflictStrategy
@Dao
interface UserDao {
    // 插入数据，onConflict 处理冲突策略
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    // 更新数据
    @Update
    suspend fun update(user: User)

    // 删除数据
    @Delete
    suspend fun delete(user: User)

    // 查询所有用户，返回Flow可观察数据变化
    @Query("SELECT * FROM users ORDER BY user_name ASC")
    fun getAllUsers(): Flow<List<User>>

    // 根据ID查询用户
    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Long): User?
}
