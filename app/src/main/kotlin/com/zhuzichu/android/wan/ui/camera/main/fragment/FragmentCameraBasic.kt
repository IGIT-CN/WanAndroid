package com.zhuzichu.android.wan.ui.camera.main.fragment

import android.Manifest
import android.graphics.Matrix
import android.util.Size
import android.view.Surface
import android.view.ViewGroup
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.databinding.library.baseAdapters.BR
import com.tbruyelle.rxpermissions2.RxPermissions
import com.uber.autodispose.android.autoDispose
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentCameraBasicBinding
import com.zhuzichu.android.wan.ui.camera.main.viewmodel.ViewModelCameraBasic
import kotlinx.android.synthetic.main.fragment_camera_basic.*

class FragmentCameraBasic :
    FragmentAnalyticsBase<FragmentCameraBasicBinding, ViewModelCameraBasic>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_camera_basic


    override fun initView() {
        super.initView()

        RxPermissions(this).request(Manifest.permission.CAMERA)
            .autoDispose(texture_view)
            .subscribe {
                if (it) {
                    startCamera()
                } else {
                    back()
                }
            }

    }

    private fun startCamera() {
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetResolution(Size(640, 480))
        }.build()
        val preview = Preview(previewConfig)
        preview.setOnPreviewOutputUpdateListener {
            val parent = texture_view.parent as ViewGroup
            parent.removeView(texture_view)
            parent.addView(texture_view, 0)
            texture_view.surfaceTexture = it.surfaceTexture
            updateTransform()
        }
        CameraX.bindToLifecycle(this, preview)
    }

    private fun updateTransform() {
        val matrix = Matrix()
        val centerX = texture_view.width / 2f
        val centerY = texture_view.height / 2f
        val rotationDegrees = when (texture_view.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)
        texture_view.setTransform(matrix)
    }
}