cmake_minimum_required(VERSION 3.4.1)

add_library(
        native-lib
        SHARED
        native-lib.cpp
)

add_library(
        jnidemo-lib
        SHARED
        jnidemo-lib.cpp
)

add_library(
        common-lib
        SHARED
        common/common.cpp
)

#导入头文件
include_directories(include)
include_directories(common)

#导入库文件
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -L${CMAKE_SOURCE_DIR}/libs/${ANDROID_ABI}")

find_library(
        log-lib
        log
)

find_library(
        jnigraphics-lib
        jnigraphics
)

target_link_libraries(
        native-lib
        common-lib
        ${jnigraphics-lib}
        ${log-lib}
        opencv_java4
)

target_link_libraries(
        common-lib
        ${jnigraphics-lib}
        ${log-lib}
        opencv_java4
)

target_link_libraries(
        jnidemo-lib
        ${log-lib}
)