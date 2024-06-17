cmake_minimum_required(VERSION 3.18.1)

project("botsort")

# Set C++ standard
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

add_subdirectory(BOT)

add_library(${CMAKE_PROJECT_NAME} SHARED
        # List C/C++ source files with relative paths to this CMakeLists.txt.
        native-lib.cpp
        botsort_tracking_example.cpp
        )

include_directories(
        ./3rdpart
        ${CMAKE_CURRENT_SOURCE_DIR}/3rdpart/opencv/include
)
# Find and link OpenCV
add_library(lib_opencv SHARED IMPORTED )

set_target_properties(lib_opencv PROPERTIES IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_java4.so
        )

target_link_libraries(${CMAKE_PROJECT_NAME}
        # List libraries link to the target library
        BOT
        lib_opencv
        android
        log
        c++_shared)