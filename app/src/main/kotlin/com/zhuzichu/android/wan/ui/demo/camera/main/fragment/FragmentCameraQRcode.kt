package com.zhuzichu.android.wan.ui.demo.camera.main.fragment

import android.Manifest
import android.graphics.Matrix
import android.os.Handler
import android.os.HandlerThread
import android.util.DisplayMetrics
import android.util.Rational
import android.view.Surface
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation
import androidx.appcompat.widget.AppCompatImageView
import androidx.camera.core.*
import androidx.databinding.library.baseAdapters.BR
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.zxing.Result
import com.tbruyelle.rxpermissions2.RxPermissions
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentCameraQrcodeBinding
import com.zhuzichu.android.wan.ui.demo.camera.main.viewmodel.ViewModelCameraQRcode
import com.zhuzichu.android.wan.widget.QRcodeAnalyzer
import kotlinx.android.synthetic.main.fragment_camera_qrcode.*

class FragmentCameraQRcode :
    FragmentAnalyticsBase<FragmentCameraQrcodeBinding, ViewModelCameraQRcode>() {

    private var isOpen = true

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_camera_qrcode

    private var lensFacing = CameraX.LensFacing.BACK

    override fun initView() {
        super.initView()

        scaleUpDowm(scan)
        RxPermissions(this).request(Manifest.permission.CAMERA)
            .autoDispose(viewModel)
            .subscribe {
                if (it) {
                    texture_view.post {
                        startCamera()
                    }
                } else {
                    back()
                }
            }

    }

    private fun scaleUpDowm(scan: AppCompatImageView) {
        val animation = ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f)
        animation.repeatCount = -1
        animation.repeatMode = Animation.RESTART
        animation.interpolator = LinearInterpolator()
        animation.duration = 1200
        scan.startAnimation(animation)
    }

    private fun startCamera() {
        val metrics = DisplayMetrics().also { texture_view.display.getRealMetrics(it) }
        val screenAspectRatio = Rational(metrics.widthPixels, metrics.heightPixels)

        val previewConfig = PreviewConfig.Builder().apply {
            setLensFacing(lensFacing)
            setTargetAspectRatio(screenAspectRatio)
            setTargetRotation(texture_view.display.rotation)
        }.build()

        val analysisConfig = ImageAnalysisConfig.Builder().apply {
            setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
            val analyzerThread = HandlerThread("BarcodeAnalyzer").apply { start() }
            setCallbackHandler(Handler(analyzerThread.looper))
        }.build()

        val preview = Preview(previewConfig)

        preview.setOnPreviewOutputUpdateListener {
            val parent = texture_view.parent as ViewGroup
            parent.removeView(texture_view)
            parent.addView(texture_view, 0)
            texture_view.surfaceTexture = it.surfaceTexture
            updateTransform()
        }

        val analysis = ImageAnalysis(analysisConfig)
        analysis.analyzer = QRcodeAnalyzer {
            if (isOpen) {
                showResult(this)
                isOpen = false
            }
        }
        CameraX.bindToLifecycle(this, preview, analysis)
    }

    private fun showResult(result: Result) {
        MaterialAlertDialogBuilder(requireContext()).setMessage(result.text).setOnDismissListener {
            isOpen = true
        }.setNegativeButton("取消") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }.create().show()
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