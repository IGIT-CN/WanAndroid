package com.zhuzichu.android.wan.repository

import com.zhuzichu.android.wan.db.AppDatabase
import com.zhuzichu.android.wan.db.DaoKeyword
import com.zhuzichu.android.wan.db.DaoUser
import com.zhuzichu.android.wan.db.entity.DOKeyword
import com.zhuzichu.android.wan.db.entity.DOUser
import io.reactivex.Flowable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface LocalRepository {

    fun saveUser(user: DOUser)

    fun saveKeyword(keyword: DOKeyword)

    fun getKeywords(): Flowable<List<DOKeyword>>

    fun deleteKeyword(list: List<DOKeyword>)
}

class LocalRepositoryImpl : LocalRepository {

    private val database = AppDatabase.getInstance()

    private val daoUser by lazy { database.daoUser() }

    private val daoKeyword by lazy { database.daoKeyword() }

    override fun saveUser(user: DOUser) {
        GlobalScope.launch {
            daoUser.insert(user)
        }
    }

    override fun saveKeyword(keyword: DOKeyword) {
        GlobalScope.launch {
            daoKeyword.insert(keyword)
        }
    }

    override fun getKeywords(): Flowable<List<DOKeyword>> {
        return daoKeyword.queryKeywords()
    }

    override fun deleteKeyword(list: List<DOKeyword>) {
        GlobalScope.launch {
            daoKeyword.deleteKeyword(list)
        }
    }
}

