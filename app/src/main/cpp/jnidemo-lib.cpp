#include <jni.h>
#include <string>
#include <log.h>

using namespace std;

//--------------------------函数声明------------------------start
//声明JNI函数
JNIEXPORT jstring invokeDynamicMethod(JNIEnv *env, jobject object);

JNIEXPORT jint getRandNumber(JNIEnv *env, jobject object);

JNIEXPORT jobject getStudent(JNIEnv *env, jobject object);

//声明普通函数
int generateRandNumber();

void onInvokeCallback(JNIEnv *env, jobject object, jint number);

jstring tojstring(JNIEnv *env, string s);
//--------------------------声明函数------------------------end

static const char *const class_JniDemoManager = "com/zhuzichu/android/wan/manager/JniDemoManager";

static const JNINativeMethod gMethods[] = {
        {"invokeDynamicMethod", "()Ljava/lang/String;", (jstring *) invokeDynamicMethod},
        {"getRandNumber",       "()I",                  (jint *) getRandNumber},
        {"getStudent",          "()Lcom/zhuzichu/android/wan/ui/jni/main/entity/BeanStudent;",
                                                        (jobject *) getStudent}
};

/**
 *
 * @param vm
 * @param reserved
 * @return
 */
JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    LOGI("JNI_OnLoad 开始了");
    JNIEnv *env = NULL;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) { //从JavaVM获取JNIEnv，一般使用1.4的版本
        return -1;
    }
    // 获取映射的java类
    jclass myClass = env->FindClass(class_JniDemoManager);
    if (myClass == NULL) {
        LOGE("不能找到类:%s\n", class_JniDemoManager);
        return -1;
    }
    // 通过RegisterNatives方法动态注册
    if (env->RegisterNatives(myClass, gMethods, sizeof(gMethods) / sizeof(gMethods[0])) < 0) {
        LOGE("注册方法失败!\n");
        return -1;
    }
    LOGI("JNI_OnLoad 结束了");
    return JNI_VERSION_1_6;
}

/**
 * 静态注册方法
 */
extern "C"
JNIEXPORT jstring JNICALL
Java_com_zhuzichu_android_wan_manager_JniDemoManager_invokeStaticMethod(
        JNIEnv *env, jobject object
) {
    jstring text = env->NewStringUTF(string("invokeStaticMethod 我是静态注册的方法").c_str());
    return text;
}


/**
 * 动态初测方法
 * @param env
 * @param object
 * @return
 */
JNIEXPORT jstring invokeDynamicMethod(JNIEnv *env, jobject object) {
    jstring text = env->NewStringUTF(string("invokeDynamicMethod 我是动态注册的方法").c_str());
    return text;
}


/**
 * 获取随机数回调给java端
 * @param env
 * @param object
 * @return
 */
JNIEXPORT jint getRandNumber(JNIEnv *env, jobject object) {
    int number = generateRandNumber();
    onInvokeCallback(env, object, number);
    return number;
}


/**
 * 创建一个Student的Java对象
 * @param env
 * @param object
 */
JNIEXPORT jobject getStudent(JNIEnv *env, jobject object) {
    jclass clazz = env->FindClass("com/zhuzichu/android/wan/ui/jni/main/entity/BeanStudent");
    jmethodID initId = env->GetMethodID(clazz, "<init>", "(Ljava/lang/String;I)V");
    jstring name = tojstring(env, "朱子楚" + to_string(generateRandNumber()) + "号");
    jobject student = env->NewObject(clazz, initId, name, generateRandNumber());
    return student;
}

/**
 * 调用java方法
 * @param env
 * @param object
 * @param text
 */
void onInvokeCallback(JNIEnv *env, jobject object, jint number) {
    jclass clazz = env->GetObjectClass(object);
    jmethodID methodId = env->GetMethodID(
            clazz,
            "onInvokeCallback",
            "(I)V"
    );
    env->CallVoidMethod(object, methodId, number);
}

int generateRandNumber() {
    return rand() % 100 + 50;
}

jstring tojstring(JNIEnv *env, string s) {
    return env->NewStringUTF(s.c_str());
}