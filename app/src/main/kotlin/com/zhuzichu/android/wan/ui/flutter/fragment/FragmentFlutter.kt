package com.zhuzichu.android.wan.ui.flutter.fragment

import androidx.fragment.app.Fragment
import com.zhuzichu.android.wan.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.bindArgument
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentFlutterBinding
import com.zhuzichu.android.wan.ui.flutter.viewmodel.ViewModelFlutter
import io.flutter.embedding.android.FlutterFragment

class FragmentFlutter : FragmentAnalyticsBase<FragmentFlutterBinding, ViewModelFlutter>() {

    companion object {
        const val ROUTE = "route"
        const val ROUTE_MAIN = "route_main"
    }

    val route: String? by bindArgument()

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_flutter

    override fun initView() {
        super.initView()
        val flutterFragment = FlutterFragment.NewEngineFragmentBuilder()
            .initialRoute(route.toString())
            .build<FlutterFragment>()
        childFragmentManager.beginTransaction()
            .replace(R.id.container,flutterFragment as Fragment)
            .commitAllowingStateLoss()
    }

}