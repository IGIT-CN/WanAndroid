package com.zhuzichu.android.wan.ui.account.login.fragment

import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentLoginBinding
import com.zhuzichu.android.wan.ui.account.login.viewmodel.ViewModelLogin
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase

class FragmentLogin : FragmentAnalyticsBase<FragmentLoginBinding, ViewModelLogin>() {

    override fun setLayoutId(): Int = R.layout.fragment_login

    override fun bindVariableId(): Int = BR.viewModel

}