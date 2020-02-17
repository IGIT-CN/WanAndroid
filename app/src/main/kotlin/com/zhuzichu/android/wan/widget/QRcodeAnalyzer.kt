package com.zhuzichu.android.wan.widget

import android.graphics.ImageFormat
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer
import com.zhuzichu.android.shared.extension.logi

//todo 内存泄漏
class QRcodeAnalyzer(
    val closure: Result.() -> Unit
) : ImageAnalysis.Analyzer {
    private val reader: MultiFormatReader = MultiFormatReader()

    init {
        val map = mapOf<DecodeHintType, Collection<BarcodeFormat>>(
            Pair(DecodeHintType.POSSIBLE_FORMATS, arrayListOf(BarcodeFormat.QR_CODE))
        )
        reader.setHints(map)
    }

    override fun analyze(image: ImageProxy, rotationDegrees: Int) {
        if (ImageFormat.YUV_420_888 != image.format) {
            "expect YUV_420_888, now = ${image.format}".logi("BarcodeAnalyzer")
            return
        }
        val buffer = image.planes[0].buffer
        val data = ByteArray(buffer.remaining())
        val height = image.height
        val width = image.width
        buffer.get(data)
        //TODO 调整crop的矩形区域，目前是全屏（全屏有更好的识别体验，但是在部分手机上可能OOM）
        val source = PlanarYUVLuminanceSource(data, width, height, 0, 0, width, height, false)

        val bitmap = BinaryBitmap(HybridBinarizer(source))

        try {
            val result = reader.decode(bitmap)
            closure.invoke(result)
            "resolved!!! = $result".logi("BarcodeAnalyzer")
        } catch (e: Exception) {
            "Error decoding barcode".logi("BarcodeAnalyzer")
        }

    }
}